package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import java.util.*

fun insertionSort(numbers: MutableList<Long>): List<Long> {

    for (i in 0 until numbers.size-1) {
        for (j in i downTo 0) {
            if (numbers[j] > numbers[j + 1]) {
                Collections.swap(numbers, j, j + 1)
            }
        }
    }
    return numbers
}

fun main() {
    val fileReaderUtils =
        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/6_Simple_sorting/src/main/resources/0.random")
    val readDirectory = fileReaderUtils.readDirectory()

//    val executedTime = mutableListOf<String>()
    readDirectory.toSortedMap().forEach { map ->
        println(map.key)
        val executionTimeMeter = ExecutionTimeMeter()
        val inValue = map.value["in"]
//        println(inValue)
        val toList = inValue!!.split(" ").filter { it.isNotBlank() }.map { s -> s.toLong() }.toMutableList()

        val measureExecutionTime = executionTimeMeter.measureExecutionTime("insertionSort", toList.size.toString()) {
            val insertionSort = insertionSort(toList)
            val outValue = map.value["out"]!!.split(" ").filter { it.isNotBlank() }.toList().toString()
//            println(insertionSort)
//            println(outValue)
            println(insertionSort.toString() == outValue)
        }

//        executedTime.add(measureExecutionTime)
        println(measureExecutionTime)

    }
}