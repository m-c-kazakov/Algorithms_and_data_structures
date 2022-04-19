package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import org.utils.FileWriterUtils
import java.util.*

fun bubbleSort(numbers: MutableList<Long>): List<Long> {
    var isChangeExist: Boolean
    do {
        isChangeExist = false
        (0 until numbers.size - 1).forEach { el ->
            if (numbers[el] > numbers[el + 1]) {
                Collections.swap(numbers, el, el + 1)
                isChangeExist = true
            }
        }
    } while (isChangeExist)

    return numbers
}


fun main() {
    val fileReaderUtils =
        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/6_Simple_sorting/src/main/resources/0.random")
    val readDirectory = fileReaderUtils.readDirectory()

    val executedTime = mutableListOf<String>()
    readDirectory.toSortedMap().forEach { map ->
        println(map.key)
        val executionTimeMeter = ExecutionTimeMeter()
        val inValue = map.value["in"]
        val toList = inValue!!.split(" ").filter { it.isNotBlank() }.map { s -> s.toLong() }.toMutableList()

        val measureExecutionTime = executionTimeMeter.measureExecutionTime("bubbleSort", toList.size.toString()) {
            val bubbleSort = bubbleSort(toList)
            val outValue = map.value["out"]!!.split(" ").filter { it.isNotBlank() }.toList().toString()
//            println("result=$bubbleSort")
//            println("out=${map.value["out"]}")
//            println("outValue=$outValue")
            println("isValid=${bubbleSort.toString() == outValue}")
        }

        executedTime.add(measureExecutionTime)
        println(measureExecutionTime)

    }
//    FileWriterUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/6_Simple_sorting/src/main/resources/bubbleSortResult")
//        .write(executedTime.joinToString(separator = "\n"))
}