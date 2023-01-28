package me.deo.dekotpiler

import com.github.javaparser.ast.stmt.TryStmt
import java.io.FileNotFoundException
import java.io.Serializable
import kotlin.random.Random

fun test() {
    val num = Random.nextInt()
    when (num) {
        0 -> println("Number is false")
        1, 100 -> println("Number is true")
        else -> println("Number is a troolean+")
    }
}