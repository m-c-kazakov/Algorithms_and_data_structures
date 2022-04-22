package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import org.utils.FileWriterUtils

fun main() {
    // MeasureTheTimeOfAlgorithms

    val fileReaderUtils =
        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/8_Quick_and_external_sorting/src/main/resources/sorting-tests/0.random")
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

            val measureExecutionTimeQuickSort = executionTimeMeter.measureExecutionTime("quickSort", toList.size.toString(), 300) {
                quickSort(toList)
            }
            executedTime.add(measureExecutionTimeQuickSort)

            val measureExecutionTimeMergeSort = executionTimeMeter.measureExecutionTime("mergeSort", toList.size.toString(), 300) {
                mergeSort(toList)
            }
            executedTime.add(measureExecutionTimeMergeSort)

            executedTime.add("\n")

        }
    FileWriterUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/8_Quick_and_external_sorting/src/main/resources/measureTheTimeOfAlgorithms")
        .write(executedTime.joinToString(separator = "\n"))
}