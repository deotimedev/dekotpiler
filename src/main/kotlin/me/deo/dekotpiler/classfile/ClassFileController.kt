package me.deo.dekotpiler.classfile

import me.deo.dekotpiler.model.statements.KtBlockStatement
import org.benf.cfr.reader.entities.ClassFile
import org.benf.cfr.reader.entities.Method

interface ClassFileController {
    fun analyze(classFile: ClassFile): Map<Method, KtBlockStatement?>
}