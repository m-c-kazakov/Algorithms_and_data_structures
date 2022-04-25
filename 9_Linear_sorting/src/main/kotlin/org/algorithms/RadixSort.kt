package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import java.util.*
import kotlin.math.max

/**
 * Поразрядная сортировка
 * Это сортировка подсчетом для каждого разряда от меньшего к большему
 */
fun radixSort(numbers: List<String>, digitOfNumber: Int = 0, maxIndex: Int = Collections.max(numbers).toString().length): List<String> {
    if (digitOfNumber >= maxIndex) {
        return numbers
    }

    // размер массива = количество цифр в разряде от 0 до 9
    val countOfNumbers = MutableList(10) { _ -> 0 }
    numbers.forEach {
        val digitOrNull = it.reversed().getOrElse(digitOfNumber){'0'}
        countOfNumbers[digitOrNull.digitToInt()] += 1
    }

    val indexesToInsert = MutableList(10) { _ -> 0 }
    (0 until countOfNumbers.size).forEach {
        if (it == 0) {
            indexesToInsert[it] = countOfNumbers[it]
        } else {
            indexesToInsert[it] = indexesToInsert[it - 1] + countOfNumbers[it]
        }
    }

    val sortedList = MutableList(numbers.size) { index ->
        ""
    }

    numbers.reversed().forEach {
        val digitOrNull = it.reversed().getOrElse(digitOfNumber) { '0' }
        sortedList[indexesToInsert[digitOrNull.digitToInt()]-1] = it
        indexesToInsert[digitOrNull.digitToInt()] -= 1
    }

//    println("sortedList=$sortedList")
    return radixSort(numbers=sortedList, digitOfNumber=digitOfNumber+1, maxIndex=maxIndex)
}

fun main() {
    val fileReaderUtils =
        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/9_Linear_sorting/src/main/resources/sorting-tests/0.random")
    val readDirectory = fileReaderUtils.readDirectory()

    readDirectory
        .toSortedMap()
        .forEach { map ->
            println(map.key)
            val executionTimeMeter = ExecutionTimeMeter()
            val inValue = map.value["in"]
//        println(inValue)
            val toList = inValue!!
                .split(" ")
                .filter { it.isNotBlank() }
                .map { s -> s.toLong() }
                .toMutableList()

            val measureExecutionTime =
                executionTimeMeter.measureExecutionTime("radixSort", toList.size.toString(), timeOutSec = 100) {
                    val radixSort = radixSort(
                        toList
                            .map { it.toString() }
                            .toList()
                    )
                    val outValue = map.value["out"]!!
                        .split(" ")
                        .filter { it.isNotBlank() }
                        .toList()
                        .toString()
//            if (radixSort.size < 20) {
//                println(inValue)
//            }
//            println(inValue)
//            println(radixSort)
//            println(map.value["out"])
//            println(outValue)
                    println("isValid=${radixSort.toString() == outValue}")
                }

            println(measureExecutionTime)

        }
}