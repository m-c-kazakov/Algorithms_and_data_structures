package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import org.utils.FileWriterUtils

fun main() {
    // MeasureTheTimeOfAlgorithms

    val fileReaderUtils =
        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/6_Simple_sorting/src/main/resources/0.random")
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

            val measureExecutionTimeBubbleSort = executionTimeMeter.measureExecutionTime("bubbleSort", toList.size.toString(), 300) {
                bubbleSort(toList)
            }
            executedTime.add(measureExecutionTimeBubbleSort)

            val measureExecutionTimeInsertionSort = executionTimeMeter.measureExecutionTime("insertionSort", toList.size.toString(), 300) {
                insertionSort(toList)
            }
            executedTime.add(measureExecutionTimeInsertionSort)

            val measureExecutionTimeShellSort = executionTimeMeter.measureExecutionTime("shellSort", toList.size.toString(), 300) {
                shellSort(toList)
            }
            executedTime.add(measureExecutionTimeShellSort)

            executedTime.add("\n")

        }
    FileWriterUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/6_Simple_sorting/src/main/resources/measureTheTimeOfAlgorithms")
        .write(executedTime.joinToString(separator = "\n"))
}