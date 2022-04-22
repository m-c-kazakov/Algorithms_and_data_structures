package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils

// MergeSort

fun mergeSort(
    numbers: MutableList<Long>,
    middleIndex: Int = numbers.size / 2,
    leftArray: MutableList<Long> = numbers.filterIndexed { index, _ -> index < middleIndex }.toMutableList(),
    rightArray: MutableList<Long> = numbers.filterIndexed { index, _ -> index >= middleIndex }.toMutableList(),
): List<Long> {
    // println("numbers=$numbers START")
    if (numbers.size == 1) {
        return numbers
    }

    val sortedLeftArray = mergeSort(numbers = leftArray)
    val sortedRightArray = mergeSort(numbers = rightArray)

    val sortedList: MutableList<Long> = mutableListOf()

    var leftCursor = 0
    var rightCursor = 0
    // println("sortedLeftArray=$sortedLeftArray")
    // println("sortedRightArray=$sortedRightArray")
    while (leftCursor + rightCursor < sortedLeftArray.size + sortedRightArray.size) {
        // println("sortedList=$sortedList leftCursor=$leftCursor leftValue=${sortedLeftArray[leftCursor]} rightCursor=$rightCursor rightValue=${sortedRightArray[rightCursor]}")
        if (sortedLeftArray[leftCursor] <= sortedRightArray[rightCursor]) {
            sortedList.add(sortedLeftArray[leftCursor])
            leftCursor++
            if (leftCursor >= sortedLeftArray.size) {
                sortedList.addAll(sortedRightArray.filterIndexed { index, l -> index>= rightCursor })
                break
            }
        } else {
            sortedList.add(sortedRightArray[rightCursor])
            rightCursor++
            if (rightCursor >= sortedRightArray.size) {
                sortedList.addAll(sortedLeftArray.filterIndexed { index, l -> index>=leftCursor })
                break
            }
        }
    }


    // println("numbers=$sortedList END")
    return sortedList
}


//fun main() {
//    val fileReaderUtils =
//        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/8_Quick_and_external_sorting/src/main/resources/sorting-tests/0.random")
//    val readDirectory = fileReaderUtils.readDirectory()
//
//    readDirectory.toSortedMap().forEach { map ->
//        println(map.key)
//        val executionTimeMeter = ExecutionTimeMeter()
//        val inValue = map.value["in"]
////        println(inValue)
//        val toList = inValue!!.split(" ").filter { it.isNotBlank() }.map { s -> s.toLong() }.toMutableList()
//
//        val measureExecutionTime = executionTimeMeter.measureExecutionTime("mergeSort", toList.size.toString()) {
//            val mergeSort = mergeSort(toList)
//            val outValue = map.value["out"]!!.split(" ").filter { it.isNotBlank() }.toList().toString()
////            if (mergeSort.size < 20) {
////                println(inValue)
////            }
////            println(inValue)
////            println(mergeSort)
////            println(map.value["out"])
////            println(outValue)
//                    println("isValid=${mergeSort.toString() == outValue}")
//        }
//
//        println(measureExecutionTime)
//
//    }
//}