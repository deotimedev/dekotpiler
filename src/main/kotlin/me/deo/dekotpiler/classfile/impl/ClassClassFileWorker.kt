package me.deo.dekotpiler.classfile.impl

import me.deo.dekotpiler.classfile.ClassFileWorker
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.entities.ClassFile
import org.benf.cfr.reader.entities.Method
import org.springframework.stereotype.Component

@Component
class ClassClassFileWorker : ClassFileWorker {
    override fun work(classFile: ClassFile): Map<Method, Op04StructuredStatement> =
        classFile.methods.associateWith { it.analysis }
}