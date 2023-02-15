package me.deo.dekotpiler.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.concurrent.atomic.AtomicBoolean
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

@PublishedApi
internal const val MaxLogs = 25
@PublishedApi
internal var appliedShutdownHook = AtomicBoolean(false)

@PublishedApi
internal fun exportShutdownHook() {
    Runtime.getRuntime().addShutdownHook(Thread {
        exportTasks()
    })
}

inline fun <R> CoroutineScope.taskAsync(name: String, crossinline closure: () -> R) =
    async { task(name, closure) }

@OptIn(ExperimentalTime::class)
inline fun <R> task(name: String, closure: () -> R): R {
    if (!appliedShutdownHook.getAndSet(true))
        exportShutdownHook()
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
    tasksFolder.listFiles()?.toList()?.let { files ->
        if (files.size > MaxLogs)
            files.sortedBy {
                Files.readAttributes(it.toPath(), BasicFileAttributes::class.java).creationTime().toMillis()
            }.take(files.size - MaxLogs).forEach { it.delete() }
    }
    listOf("tasks-${System.currentTimeMillis()}", "recent").forEach {
        val file = File(tasksFolder, "$it.txt")
        file.writeText(taskResults.joinToString("\n") { (name, time) -> "Task \"$name\" took $time" })
    }
}