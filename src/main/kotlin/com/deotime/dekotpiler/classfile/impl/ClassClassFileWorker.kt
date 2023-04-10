package com.deotime.dekotpiler.classfile.impl

import com.deotime.dekotpiler.classfile.ClassFileWorker
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.entities.ClassFile
import org.benf.cfr.reader.entities.Method
import org.koin.core.annotation.Single

@Single
class ClassClassFileWorker : ClassFileWorker {
    override fun work(classFile: ClassFile): Map<Method, Op04StructuredStatement> =
        classFile.methods.associateWith { it.analysis }
}