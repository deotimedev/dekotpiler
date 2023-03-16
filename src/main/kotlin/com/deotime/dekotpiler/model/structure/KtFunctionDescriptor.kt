package com.deotime.dekotpiler.model.structure

import com.deotime.dekotpiler.model.type.KtReferenceType
import com.deotime.dekotpiler.model.type.KtType

interface KtFunctionDescriptor {

    val name: String
    val descriptor: String
    val returns: KtType
    val enclosing: KtReferenceType
    val parameters: List<KtType>

    data class Raw(
        override val name: String,
        override val descriptor: String,
        override val returns: KtType,
        override val enclosing: KtReferenceType,
        override val parameters: List<KtType>,
    ) : KtFunctionDescriptor

}