package me.deo.dekotpiler.model.structure

import me.deo.dekotpiler.model.type.KtReferenceType
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.model.type.KtTyped

class KtClass(
    override val type: KtReferenceType,
    val functions: MutableList<KtFunction>,
    val mode: Type
) : KtTyped {

    enum class Type {
        Class,
        Interface,
        Enum,
        Annotation
    }

}