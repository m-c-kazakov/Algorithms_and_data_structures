package org.utils

import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

class ExecutionTimeMeter {

    fun measureExecutionTime(
        functionName: String,
        amountOfData: String,
        timeOutSec: Long = 10,
        function: Runnable
    ): String = runBlocking {
        val start = System.currentTimeMillis()

        val thread = thread {
            function.run()
        }

        var result: Boolean
        val end = (System.currentTimeMillis() - start) / 1000
        do {
            result = timeOutSec > (System.currentTimeMillis() - start) / 1000
            if (!result) {
                thread.stop()
            }
        } while (thread.isAlive && result)

        val executionTime = if (result) "$end sec ${(System.currentTimeMillis() - start) % 1000} msec" else "> $timeOutSec sec"

        return@runBlocking "Название: $functionName; Количество данных: $amountOfData; Время выполнения: $executionTime"
    }
}