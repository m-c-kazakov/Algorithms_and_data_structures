package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import org.utils.FileWriterUtils
import java.util.*

fun main() {
    // MeasureTheTimeOfAlgorithms

    val fileReaderUtils =
        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/9_Linear_sorting/src/main/resources/sorting-tests/0.random")
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

            val outValue = map.value["out"]!!
                .split(" ")
                .filter { it.isNotBlank() }
                .toList()
                .toString()

            val measureExecutionTimeBucketSort =
                executionTimeMeter.measureExecutionTime("bucketSort", toList.size.toString(), 300) {
                    val bucketSort = bucketSort(toList)
                    println("isValid=${bucketSort.toString() == outValue}")
                }
            println(measureExecutionTimeBucketSort)
            executedTime.add(measureExecutionTimeBucketSort)

            val measureExecutionTimeRadixSort =
                executionTimeMeter.measureExecutionTime("radixSort", toList.size.toString(), 300) {
                    val radixSort = radixSort(
                        numbers = toList.map { it.toString() },
                        maxIndex = Collections
                            .max(toList)
                            .toString().length
                    )
                    println("isValid=${radixSort.toString() == outValue}")
                }
            println(measureExecutionTimeRadixSort)
            executedTime.add(measureExecutionTimeRadixSort)

            executedTime.add("\n")

        }
    FileWriterUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/9_Linear_sorting/src/main/resources/measureTheTimeOfAlgorithms")
        .write(executedTime.joinToString(separator = "\n"))
}