package me.deo.dekotpiler.mapping

import me.deo.dekotpiler.model.type.KtNothingType
import me.deo.dekotpiler.model.type.KtType
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.RawJavaType
import org.benf.cfr.reader.bytecode.analysis.types.TypeConstants
import org.springframework.stereotype.Component

@Component
internal class TypeMappingsImpl : TypeMappings {

    override fun mapping(java: JavaTypeInstance) = when (java) {
        TypeConstants.OBJECT -> KtType.Any
        TypeConstants.STRING -> KtType.String
        TypeConstants.CLASS -> KtType.JClass
        RawJavaType.VOID -> KtType.Unit
        RawJavaType.INT -> KtType.Int
        RawJavaType.LONG -> KtType.Long
        RawJavaType.SHORT -> KtType.Short
        RawJavaType.BYTE -> KtType.Byte
        RawJavaType.FLOAT -> KtType.Float
        RawJavaType.DOUBLE -> KtType.Double
        RawJavaType.BOOLEAN -> KtType.Boolean
        RawJavaType.CHAR -> KtType.Char
        else -> when (java.rawName) {
            "java.lang.Void" -> KtNothingType
            "kotlin.reflect.KClass" -> KtType.KClass
            else -> null
        }
    }?.nullable()
}