package com.deotime.dekotpiler.model.attribute

// todo not sure if class modifiers (enum, annotation, inner, etc) should be included here
enum class KtModifier {
    Expect,
    Actual,
    Final,
    Open,
    Abstract,
    Sealed,
    Const,
    External,
    Override,
    Lateinit,
    Tailrec,
    Vararg,
    Suspend,
    Inner,
    Enum,
    Annotation,
    Value,
    Fun,
    Companion,
    Inline,
    Noinline,
    Crossinline,
    Reified,
    Infix,
    Operator,
    Data,
    In,
    Out;
}