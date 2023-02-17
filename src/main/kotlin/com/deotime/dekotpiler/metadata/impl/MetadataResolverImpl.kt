package com.deotime.dekotpiler.metadata.impl

import com.deotime.dekotpiler.metadata.MetadataResolver
import kotlinx.metadata.jvm.Metadata
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes
import org.springframework.stereotype.Component

@Component
internal class MetadataResolverImpl : MetadataResolver {
    override fun resolve(clazz: ByteArray): Metadata {
        val reader = ClassReader(clazz)
        val visitor = MetadataClassVisitor()
        reader.accept(visitor, Opcodes.ASM7)
        return visitor.metadata()
    }

    class MetadataClassVisitor : ClassVisitor(Opcodes.ASM7) {

        private val annotationVisitor = MetadataAnnotationVisitor()

        private var kind = 1
        private var version = intArrayOf()
        private var data1 = mutableListOf<String>()
        private var data2 = mutableListOf<String>()
        private var packageName = ""
        private var extraString = ""
        private var extraInt = 0

        fun metadata() = Metadata(
            kind,
            version,
            data1.toTypedArray(),
            data2.toTypedArray(),
            extraString,
            packageName,
            extraInt
        )

        override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor? =
            if (descriptor == MetadataDescriptor) annotationVisitor
            else super.visitAnnotation(descriptor, visible)

        inner class MetadataAnnotationVisitor : AnnotationVisitor(Opcodes.ASM7) {
            override fun visit(name: String, value: Any) {
                when (name) {
                    "k" -> kind = value as Int
                    "mv" -> version = value as IntArray
                    "xs" -> extraString = value as String
                    "xi" -> extraInt = value as Int
                    "pn" -> packageName = value as String
                }
            }

            override fun visitArray(name: String) = object : AnnotationVisitor(Opcodes.ASM7) {
                private val holder = when (name) {
                    "d1" -> data1
                    "d2" -> data2
                    else -> error("Unknown metadata array \"$name\"")
                }

                override fun visit(_name: String?, value: Any) {
                    holder += value as String
                }
            }
        }
    }

    companion object {
        const val MetadataDescriptor = "Lkotlin/Metadata;"
    }
}