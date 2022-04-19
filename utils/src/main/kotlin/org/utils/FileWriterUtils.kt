package org.utils

import kotlinx.coroutines.withTimeoutOrNull
import java.io.File
import kotlin.system.measureTimeMillis

class FileWriterUtils(private val path: String) {

    fun write(text: String) {
        File(path).writeText(text)
    }
}

//fun main() {
//    val testText = "text for test qweqwe"
//    FileWriter("/home/maxim/IdeaProjects/Algorithms_and_data_structures/utils/src/main/resources/test").write(testText)
//}