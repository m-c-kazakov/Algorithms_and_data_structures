package org.algorithms

import java.io.File

class FileReader {
    fun read(path: String): String {
        return this::class.java.classLoader.getResource(path).readText()
    }

    fun readDirectory(path: String): Map<String, Map<String, String>> {
        return hashMapOf<String, Map<String, String>>().apply {
            File("/home/maxim/IdeaProjects/Algorithms_and_data_structures/1_Loops_and_recursion/src/main/resources/$path")
                .listFiles()
                .forEach {
                    this.merge(
                        it.name.replace("(.in)|(.out)".toRegex(), ""),
                        hashMapOf(it.name to read(path + "/" + it.name))
                    ) { old, new -> old.plus(buildMap { putAll(new) }) }
                }
        }
    }
}

fun main() {
    val fileReader = FileReader()
    val readDirectory = fileReader.readDirectory("0.String")
    println(readDirectory)


}