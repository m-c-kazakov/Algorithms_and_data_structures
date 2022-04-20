package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import java.util.*


fun selectionSort(numbers: MutableList<Long>): List<Long> {

    (0 until numbers.size).forEach { index ->
        var minValueIndex = index
        (index until numbers.size).forEach { cursor ->
            if (numbers[minValueIndex] > numbers[cursor]) {
                minValueIndex = cursor
            }
        }
        Collections.swap(numbers, minValueIndex, index)
    }
    return numbers
}

fun main() {
    val fileReaderUtils =
        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/7_Pyramid_sorting/src/main/resources/sorting-tests/0.random")
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
                executionTimeMeter.measureExecutionTime("selectionSort", toList.size.toString()) {
                    val selectionSort = selectionSort(toList)
                    val outValue = map.value["out"]!!
                        .split(" ")
                        .filter { it.isNotBlank() }
                        .toList()
                        .toString()
//            if (selectionSort.size < 20) {
//                println(inValue)
//            }
//            println(inValue)
//            println(selectionSort)
//            println(map.value["out"])
//            println(outValue)
                    println("isValid=${selectionSort.toString() == outValue}")
                }

            println(measureExecutionTime)

        }
}