package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import java.util.*
import java.util.Objects.isNull


data class Bucket(var value: Long? = null, var nextBucket: Bucket? = null)

class BucketArray(
    private val size: Int = 0,
    private val array: Array<Bucket> = Array(size = size) { Bucket() }
) {

    fun add(index: Int, value: Long) {
        val currentBucket = array[index]
        addValue(currentBucket, index, value)
    }

    private fun addValue(currentBucket: Bucket, index: Int, value: Long) {
        if (isNull(currentBucket.value)) {
            currentBucket.value = value
        } else if (value < currentBucket.value!!) {
            array[index] = Bucket(value, currentBucket)
        } else if (isNull(currentBucket.nextBucket)) {
            currentBucket.nextBucket = Bucket(value)
        } else {
            recursiveAddValue(currentBucket, value)
        }
    }

    private fun recursiveAddValue(previousBucket: Bucket, value: Long) {
        if (value < previousBucket.nextBucket?.value!!) {
            previousBucket.apply {
                this.nextBucket = Bucket(value, previousBucket.nextBucket)
            }
        } else if (isNull(previousBucket.nextBucket!!.nextBucket)) {
            previousBucket.nextBucket?.apply {
                this.nextBucket = Bucket(value)
            }
        } else {
            recursiveAddValue(previousBucket.nextBucket!!, value)
        }

    }

    fun toList(): List<Long> {
        val mutableListOf = mutableListOf<Long>()

        array.forEach {
            if (it.nextBucket == null) {
                it.value?.let { it1 -> mutableListOf.add(it1) }
            } else {
                var currentBucket: Bucket? = it
                do {
                    currentBucket?.value?.let { value -> mutableListOf.add(value) }
                    currentBucket = currentBucket?.nextBucket
                } while (currentBucket != null)
            }
        }

        return mutableListOf.toList()

    }
}


fun bucketSort(numbers: MutableList<Long>): List<Long> {
    val maxValue = Collections.max(numbers)

    val hashFun =
        { arrayValue: Long, arraySize: Long, maxArrayValue: Long -> arrayValue * arraySize / (maxArrayValue + 1) }

    return BucketArray(numbers.size)
        .also { backedArray ->
            numbers.forEach { value ->
                val hashFunResult = hashFun(value, numbers.size.toLong(), maxValue)

                backedArray.add(hashFunResult.toInt(), value)
            }
        }
        .toList()
}

//fun main() {
//    val fileReaderUtils =
//        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/9_Linear_sorting/src/main/resources/sorting-tests/0.random")
//    val readDirectory = fileReaderUtils.readDirectory()
//
//    readDirectory
//        .toSortedMap()
//        .forEach { map ->
//            println(map.key)
//            val executionTimeMeter = ExecutionTimeMeter()
//            val inValue = map.value["in"]
////        println(inValue)
//            val toList = inValue!!
//                .split(" ")
//                .filter { it.isNotBlank() }
//                .map { s -> s.toLong() }
//                .toMutableList()
//
//            val measureExecutionTime =
//                executionTimeMeter.measureExecutionTime("bucketSort", toList.size.toString(), timeOutSec = 100) {
//                    val bucketSort = bucketSort(toList)
//                    val outValue = map.value["out"]!!
//                        .split(" ")
//                        .filter { it.isNotBlank() }
//                        .toList()
//                        .toString()
////            if (bucketSort.size < 20) {
////                println(inValue)
////            }
////            println(inValue)
////            println(bucketSort)
////            println(map.value["out"])
////            println(outValue)
//                    println("isValid=${bucketSort.toString() == outValue}")
//                }
//
//            println(measureExecutionTime)
//
//        }
//}