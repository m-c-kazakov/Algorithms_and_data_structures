package org.algorithms.LuckyTicket

import org.algorithms.FileReader
import kotlin.math.pow


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

fun main() {
    val fileReader = FileReader()
    /**
     * n=1
    Для in=1 luckyTicketsCount=10 isValid=true время выполнения 0 секунд и 5 msec
    n=2
    Для in=2 luckyTicketsCount=670 isValid=true время выполнения 0 секунд и 2 msec
    n=3
    Для in=3 luckyTicketsCount=55252 isValid=true время выполнения 0 секунд и 29 msec
    n=4
    Для in=4 luckyTicketsCount=4816030 isValid=true время выполнения 1 секунд и 216 msec
    n=5
    Для in=5 luckyTicketsCount=432457640 isValid=true время выполнения 145 секунд и 431 msec
     */
    fileReader.readDirectory("1.Tickets").toSortedMap().map { entry -> entry.value }.forEach { map ->
        val start = System.currentTimeMillis()
        val n = map.get("in")
        println("n=$n")
        val maxSize = (10.0.pow(n!!.toDouble()) - 1).toInt()
        val findLuckyTicket = reflectionExecute(maxSize = maxSize)
        val end = System.currentTimeMillis()
        val msec = (end - start) % 1000
        val sec = (end - start) / 1000
        val isValid = findLuckyTicket == map.get("out")!!.toInt()
        println("Для in=$n luckyTicketsCount=$findLuckyTicket isValid=$isValid время выполнения $sec секунд и $msec msec")


    }
}