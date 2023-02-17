package me.deo.dekotpiler.classfile

import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.entities.ClassFile
import org.benf.cfr.reader.entities.Method

interface ClassFileWorker {
    fun work(classFile: ClassFile): Map<Method, Op04StructuredStatement?>
}