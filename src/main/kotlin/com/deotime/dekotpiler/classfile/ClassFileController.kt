package com.deotime.dekotpiler.classfile

import com.deotime.dekotpiler.model.statements.KtBlockStatement
import org.benf.cfr.reader.entities.ClassFile
import org.benf.cfr.reader.entities.Method

interface ClassFileController {
    fun analyze(classFile: ClassFile): Map<Method, KtBlockStatement?>
}