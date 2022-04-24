package org.utils

import java.io.File
import java.io.FileReader

class FileReaderUtils(private val path: String) {
    private fun read(readPath: String): String {
        val readLines = FileReader(readPath).readLines()
        return if (readLines.size > 1) {
            return readLines[1]
        } else {
            return readLines[0]
        }
    }

    fun readDirectory(): Map<String, Map<String, String>> {
        return hashMapOf<String, Map<String, String>>().apply {
            // example "/home/maxim/IdeaProjects/Algorithms_and_data_structures/1_Loops_and_recursion/src/main/resources/$path"
            File(path)
                .listFiles()
                .forEach {
                    this.merge(
                        it.name.replace("(.in)|(.out)".toRegex(), ""),
                        hashMapOf(it.name.let {
                            if(it.contains("in")) "in" else "out"
                        } to read(path + "/" + it.name))
                    ) { old, new -> old.plus(buildMap { putAll(new) }) }
                }
        }
    }
}

//fun main() {
//    val fileReader = FileReader()
//    val readDirectory = fileReader.readDirectory("0.String")
//    println(readDirectory)
//
//
//}