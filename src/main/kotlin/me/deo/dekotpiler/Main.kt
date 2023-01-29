package me.deo.dekotpiler

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.expr.ConditionalExpr
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.expr.VariableDeclarationExpr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import me.deo.dekotpiler.decompile.CFRDecompilerEngine
import me.deo.dekotpiler.decompile.DecompilerEngine
import me.deo.dekotpiler.file.FileSelector
import me.deo.dekotpiler.metadata.MetadataReader
import me.deo.dekotpiler.metadata.MetadataResolver
import me.deo.dekotpiler.parsing.Analyzer
import me.deo.dekotpiler.processing.TranslationHelper
import me.deo.dekotpiler.processing.Translator
import me.deo.dekotpiler.util.getValue
import me.deo.dekotpiler.util.findAll
import org.benf.cfr.reader.bytecode.analysis.parse.expression.TernaryExpression
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.stereotype.Component
import java.io.File

@Component
class Main(
    private val metadataResolver: MetadataResolver,
    private val fileSelector: FileSelector,
    private val reader: MetadataReader,
    private val engines: List<DecompilerEngine>,
    private val analyzer: Analyzer,
    private val translationHelper: TranslationHelper,
    private val contextFactory: Translator.Context.Factory
) : CommandLineRunner {
    // This will eventually be replaced by a CLI
    override fun run(vararg args: String): Unit = runBlocking(Dispatchers.Default) {
        // using cfr for testing
        val cfr = engines.filterIsInstance<CFRDecompilerEngine>().first()

        // testing
        val file = File(File("").absolutePath, "/build/classes/kotlin/main/me/deo/dekotpiler/TestKt.class")

        val metadata by async { reader.read(metadataResolver.resolve(file)) }
        val rawData by async { cfr.decompile(file) }
        val parsed = analyzer.parse(rawData)

        parsed.getClassByName("TestKt").get().methods.forEach { method ->
            val context = contextFactory.create(parsed)
            with(translationHelper) { context.translateMethod(method) }.also { println(it) }
        }

    }

    private companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplicationBuilder(App::class.java)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .headless(false)
                .run()
        }

        @SpringBootApplication
        class App

    }
}