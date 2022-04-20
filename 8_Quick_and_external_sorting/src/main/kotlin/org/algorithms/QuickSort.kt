package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import java.util.*

fun quickSort(numbers: MutableList<Long>,
              startIndex: Int = 0,
              endIndex:Int = numbers.size-1): List<Long> {
    if (startIndex >= endIndex) {
//        println("END startIndex(=$startIndex) == endIndex(=$endIndex)")
        return numbers
    }
//    println("Numbers=$numbers Start startIndex=$startIndex endIndex=$endIndex")

    val baseValue = numbers[startIndex]
    var i = startIndex
    var j = endIndex


    do {
        if (baseValue < numbers[i] && baseValue > numbers[j]) {
            Collections.swap(numbers, i, j)
        } else if (baseValue < numbers[i] && baseValue < numbers[j]) {
            j--
        } else {
            i++
        }
    } while (i < j)

//    println("Numbers=$numbers Median startIndex=$startIndex i=$i endIndex=$endIndex j=$j")

    do {
        if (baseValue > numbers[j]) {
            Collections.swap(numbers, startIndex, j)
            break
        } else {
            j--
        }
    } while (j > startIndex)
//    println("Numbers=$numbers before quickSort startIndex=$startIndex i=$i endIndex=$endIndex j=$j")
    quickSort(numbers=numbers, startIndex=startIndex, endIndex=j-1)
    quickSort(numbers=numbers, startIndex=j+1, endIndex=endIndex)


//    println("Numbers=$numbers End")
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

        val measureExecutionTime = executionTimeMeter.measureExecutionTime("quickSort", toList.size.toString(), timeOutSec = 100) {
            val quickSort = quickSort(toList)
            val outValue = map.value["out"]!!.split(" ").filter { it.isNotBlank() }.toList().toString()
//            if (quickSort.size < 20) {
//                println(inValue)
//            }
//            println(inValue)
//            println(quickSort)
//            println(map.value["out"])
//            println(outValue)
            println("isValid=${quickSort.toString() == outValue}")
        }

        println(measureExecutionTime)

    }
}