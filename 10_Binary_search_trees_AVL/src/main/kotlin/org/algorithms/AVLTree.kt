package org.algorithms

import org.utils.ExecutionTimeMeter
import org.utils.FileReaderUtils
import kotlin.math.abs


class Node(
    val key: Long, var leftLeaf: Node? = null, var rightLeaf: Node? = null
) {
    val leftLeafSize: Int
        get() = leftLeaf?.height ?: 0
    val rightLeafSize: Int
        get() = rightLeaf?.height ?: 0

    val height: Int
        get() = maxOf(leftLeafSize, rightLeafSize) + 1
}

class AVLTree(private var root: Node) {

//    fun get(key: Long):Node {
//
//    }

    fun add(key: Long) {

    }

    private fun addValue(grandParent:Node, parent: Node, child: Node): Boolean {
        return if (child.key < parent.key) {
            if (parent.leftLeaf != null) {
                addValue(parent, parent.leftLeaf!!, child)
            } else if (abs(parent.leftLeafSize - parent.rightLeafSize) < 2) {
                parent.leftLeaf = Node(child.key)
                true
            } else {
                false
            }
        } else if (child.key > parent.key) {
            if (parent.rightLeaf != null) {
                addValue(parent, parent.rightLeaf!!, child)
            } else if (abs(parent.leftLeafSize - parent.rightLeafSize) < 2) {
                parent.rightLeaf = Node(child.key)
                true
            } else {
                false
            }
        } else {
            throw RuntimeException("Не корректное выполнение rootNode=$parent key=${child.key}")
        }
    }

    // малый правый поворот
    private fun smallRightTurn(grandparent: Node, parent:Node, child: Node) {

        // TODO


    }

    fun delete(key: Long) {

    }
}

fun aVLTree(numbers: List<Long>): List<Long> {
    return numbers
}

fun main() {
    val fileReaderUtils =
        FileReaderUtils("/home/maxim/IdeaProjects/Algorithms_and_data_structures/10_Binary_search_trees_AVL/src/main/resources/sorting-tests/0.random")
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
                executionTimeMeter.measureExecutionTime("radixSort", toList.size.toString(), timeOutSec = 100) {
                    val radixSort = aVLTree(
                        toList
                    )
                    val outValue = map.value["out"]!!
                        .split(" ")
                        .filter { it.isNotBlank() }
                        .toList()
                        .toString()
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