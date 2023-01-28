package me.deo.dekotpiler.util

import com.github.javaparser.ast.Node

inline fun <reified T : Node> Node.findAll() = findAll(T::class.java)