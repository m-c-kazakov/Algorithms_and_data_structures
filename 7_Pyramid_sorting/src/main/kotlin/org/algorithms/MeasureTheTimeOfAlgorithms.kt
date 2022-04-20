package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import org.utils.FileWriterUtils

fun main() {
    // MeasureTheTimeOfAlgorithms

    val fileReaderUtils =
        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/7_Pyramid_sorting/src/main/resources/sorting-tests/0.random")
    val readDirectory = fileReaderUtils.readDirectory()

    val executedTime = mutableListOf<String>()
    readDirectory
        .toSortedMap()
        .forEach { map ->
            println(map.key)
            val executionTimeMeter = ExecutionTimeMeter()
            val inValue = map.value["in"]
            val toList = inValue!!
                .split(" ")
                .filter { it.isNotBlank() }
                .map { s -> s.toLong() }
                .toMutableList()

            val measureExecutionTimeInsertionSort = executionTimeMeter.measureExecutionTime("selectionSort", toList.size.toString(), 300) {
                selectionSort(toList)
            }
            executedTime.add(measureExecutionTimeInsertionSort)

            val measureExecutionTimeBubbleSort = executionTimeMeter.measureExecutionTime("heapSort", toList.size.toString(), 300) {
                heapSort(toList)
            }
            executedTime.add(measureExecutionTimeBubbleSort)

            executedTime.add("\n")

        }
    FileWriterUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/7_Pyramid_sorting/src/main/resources/measureTheTimeOfAlgorithms")
        .write(executedTime.joinToString(separator = "\n"))
}