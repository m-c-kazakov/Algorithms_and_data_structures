package org.algorithms

import kotlinx.coroutines.withTimeoutOrNull
import java.io.File
import kotlin.system.measureTimeMillis

class FileWriter(val path: String) {


    fun write(text: String) {
        File(path).writeText(text)
    }

    suspend fun measureExecutionTime(
        functionName: String,
        amountOfData: String,
        timeOut: Long = 180000L,
        function: Runnable
    ): String {
        val withTimeoutOrNull = withTimeoutOrNull(timeOut) {
            measureTimeMillis { function.run() }
        }

        return "Название: $functionName; Количество данных: $amountOfData; Время выполнения: ${
            return if (withTimeoutOrNull != null) {
                "$withTimeoutOrNull msec"
            } else {
                "> $timeOut msec"
            }
        }"
    }
}

fun main() {
    val testText = "text for test qweqwe"
    FileWriter("/home/maxim/IdeaProjects/Algorithms_and_data_structures/utils/src/main/resources/test").write(testText)
}