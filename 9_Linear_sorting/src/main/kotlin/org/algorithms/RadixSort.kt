package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils

fun radixSort(numbers: MutableList<Long>): List<Long> {
    return numbers
}

fun main() {
    val fileReaderUtils =
        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/8_Quick_and_external_sorting/src/main/resources/sorting-tests/0.random")
    val readDirectory = fileReaderUtils.readDirectory()

    readDirectory.toSortedMap().forEach { map ->
        println(map.key)
        val executionTimeMeter = ExecutionTimeMeter()
        val inValue = map.value["in"]
//        println(inValue)
        val toList = inValue!!.split(" ").filter { it.isNotBlank() }.map { s -> s.toLong() }.toMutableList()

        val measureExecutionTime = executionTimeMeter.measureExecutionTime("radixSort", toList.size.toString(), timeOutSec = 100) {
            val radixSort = radixSort(toList)
            val outValue = map.value["out"]!!.split(" ").filter { it.isNotBlank() }.toList().toString()
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