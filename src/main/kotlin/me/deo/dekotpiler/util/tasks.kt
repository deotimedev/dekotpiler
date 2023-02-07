package me.deo.dekotpiler.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import java.io.File
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/**
 * Tasks: A profiler from wish
 */

// TODO maybe find a suitable way to implement with w/ DI

@PublishedApi
internal val taskResults: MutableList<Pair<String, Duration>> = mutableListOf()

@PublishedApi
internal var debugTasks = false

inline fun <R> CoroutineScope.taskAsync(name: String, crossinline closure: () -> R) =
    async { task(name, closure) }

@OptIn(ExperimentalTime::class)
inline fun <R> task(name: String, closure: () -> R): R {
    if (debugTasks) println("Starting task \"$name\"...")
    val result: R
    val duration = measureTime { result = closure() }
    if (debugTasks) println("Task \"$name\" finished in $duration")
    taskResults += name to duration
    return result
}

internal fun exportTasks() {
    val tasksFolder = File(File("").absolutePath, "/tasks")
    if (!tasksFolder.exists()) tasksFolder.mkdirs()
    listOf("tasks-${System.currentTimeMillis()}", "recent").forEach {
        val file = File(tasksFolder, "$it.txt")
        file.writeText(taskResults.joinToString("\n") { (name, time) -> "Task \"$name\" took $time" })
    }
}