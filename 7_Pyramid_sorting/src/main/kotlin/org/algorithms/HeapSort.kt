package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import java.util.*

tailrec fun heapSort(
    numbers: MutableList<Long>,
    index: Int = numbers.size - 1,
    size: Int = numbers.size - 1
): List<Long> {
    if (size == 0) {
        // весь массив был отсортирован
        return numbers
    } else if (index < 0) {
        // Найден максимальный элемент
        // Будет положен в конец видимой части массива
        Collections.swap(numbers, 0, size)
        return heapSort(numbers, index=size-1, size=size-1)
    }

    val leftIndex: Int = index * 2 + 1
    val rightIndex: Int = index * 2 + 2

    var maxValueIndex = index
    if (leftIndex <= size && numbers[leftIndex] > numbers[index]) maxValueIndex=leftIndex
    if (rightIndex <= size && numbers[rightIndex] > numbers[index]) maxValueIndex=rightIndex

    Collections.swap(numbers, maxValueIndex, index)

    return heapSort(numbers=numbers, index=maxValueIndex-1, size=size)
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
                executionTimeMeter.measureExecutionTime("heapSort", toList.size.toString()) {
                    val heapSort = heapSort(toList)
                    val outValue = map.value["out"]!!
                        .split(" ")
                        .filter { it.isNotBlank() }
                        .toList()
                        .toString()
//            if (heapSort.size < 20) {
//                println(inValue)
//            }
//            println(inValue)
//            println(heapSort)
//            println(map.value["out"])
//            println(outValue)
                    println("isValid=${heapSort.toString() == outValue}")
                }

            println(measureExecutionTime)

        }
}