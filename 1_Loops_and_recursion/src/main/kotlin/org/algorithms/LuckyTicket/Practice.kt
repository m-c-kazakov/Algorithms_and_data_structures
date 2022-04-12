package org.algorithms.LuckyTicket

import kotlinx.coroutines.runBlocking
import kotlin.math.pow

class Practice {

    fun sumResult(number: Int): Int = number.toString().sumOf { c: Char -> c.digitToInt() }

    fun execute(maxSize: Int): Int {
        var result = 0
        (0..maxSize).forEach { i ->
            val sumOfI = i.toString().sumOf { c: Char -> c.digitToInt() }
            (0..maxSize).forEach { j ->
                if (sumOfI == j.toString().sumOf { c: Char -> c.digitToInt() }) result++
            }
        }

        return result
    }

    fun executeStream(maxSize: Int): Int {
        return (0..maxSize)
            .map { i ->
                i.toString().sumOf { c: Char -> c.digitToInt() }
            }
            .flatMap { sumOfI: Int ->
                (0..maxSize)
                    .filter { j -> j.toString().sumOf { c: Char -> c.digitToInt() } == sumOfI }
            }
            .size

    }


    fun reflectionExecute(
        maxSize: Int,
        step: Int = 1,
        leftSumResult: Int = 0,
        rightSumResult: Int = 0
    ): Int {

        when (step) {
            1 -> return (0..maxSize).sumOf { leftValue ->
                reflectionExecute(
                    maxSize = maxSize,
                    step = step + 1,
                    leftSumResult = leftValue.toString().sumOf { c: Char -> c.digitToInt() },
                    rightSumResult = rightSumResult
                )
            }

            2 -> return (0..maxSize).sumOf { rightValue ->
                reflectionExecute(
                    maxSize = maxSize,
                    step = step + 1,
                    leftSumResult = leftSumResult,
                    rightSumResult = rightValue.toString().sumOf { c: Char -> c.digitToInt() }
                )
            }
            else -> return if (leftSumResult == rightSumResult) 1 else 0
        }
    }

    tailrec fun tailRecExecute(
        maxSize: Int,
        leftValue: Int,
        rightValue: Int,
        leftSumResult: Int = -1,
        luckyResult: Int = 0
    ): Int {
        if (leftValue < 0) {
            return luckyResult
        }
        if (rightValue >= 0) {
            val newLeftSumResult: Int =
                if (leftSumResult >= 0) leftSumResult else leftValue.toString().sumOf { c: Char -> c.digitToInt() }
            val newRightSumResult: Int = rightValue.toString().sumOf { c: Char -> c.digitToInt() }
            var newLuckyResult = luckyResult
            if (newLeftSumResult == newRightSumResult) {
                newLuckyResult++
            }
            return tailRecExecute(
                maxSize = maxSize,
                leftValue = leftValue,
                rightValue = rightValue - 1,
                leftSumResult = leftSumResult,
                luckyResult = newLuckyResult
            )
        }

        return tailRecExecute(
            maxSize = maxSize,
            leftValue = leftValue - 1,
            rightValue = maxSize,
            luckyResult = luckyResult
        )


    }
}

fun main() = runBlocking() {
    // sec=0 and msec=3 - reflectionExecute
    // sec=0 and msec=3 - execute
//    val n = 1
//    val expected = 10

    // sec=1 and msec=162 reflectionExecute
    // sec=1 and msec=840 - execute
    // sec=2 and msec=560 tailRecExecute
    // sec=2 and msec=403
    val n = 4
    val expected = 4816030

//    val n = 5
//    val expected = 432457640


//    val n = 10
//    val expected = 3081918923741896840

    val start = System.currentTimeMillis()
    val maxSize = (10.0.pow(n.toDouble()) - 1).toInt()
//    val result = execute(maxSize)
//    val result = executeStream(maxSize)
    val result = reflectionExecute(maxSize = (10.0.pow(n.toDouble()) - 1).toInt())
//    val result = tailRecExecute(maxSize = maxSize, leftValue = maxSize, rightValue = maxSize, leftSumResult = maxSize.toString().sumOf { c: Char -> c.digitToInt() })
    val end = System.currentTimeMillis()
    val msec = (end - start) % 1000
    val sec = (end - start) / 1000
    println("sec=$sec and msec=$msec")
    println("For n=$n expected=$expected and actual=$result")


}