package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import java.util.*

tailrec fun shellSort(numbers: MutableList<Long>, cursorCount: Int = 2): List<Long> {
    val distance = numbers.size / cursorCount
//    println("distance=$distance")
    (0..distance)
        .asSequence()
        .flatMap { (it..numbers.size step distance).asSequence() }
        .filter { it+distance < numbers.size }
        .forEach { index ->
            var cursor = index
            do {
                if (numbers[cursor + distance] < numbers[cursor]) {
                    Collections.swap(numbers, cursor + distance, cursor)
                    cursor -= distance
                } else {
                    break
                }

            } while (cursor >=0)
        }

    if (numbers.size == cursorCount) {
        return numbers
    }


    val newCursorCount = if (numbers.size < cursorCount*2) cursorCount*2 else numbers.size


    return shellSort(numbers=numbers, cursorCount=newCursorCount)
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

        val measureExecutionTime = executionTimeMeter.measureExecutionTime("shellSort", toList.size.toString()) {
            val shellSort = shellSort(toList)
            val outValue = map.value["out"]!!.split(" ").filter { it.isNotBlank() }.toList().toString()
//            if (shellSort.size < 20) {
//                println(inValue)
//            }
//            println(inValue)
//            println(shellSort)
//            println(map.value["out"])
//            println(outValue)
            println("isValid=${shellSort.toString() == outValue}")
        }

//        executedTime.add(measureExecutionTime)
        println(measureExecutionTime)

    }
}