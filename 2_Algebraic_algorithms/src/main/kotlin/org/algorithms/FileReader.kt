package org.algorithms

import java.io.File

class FileReader {
    fun read(path: String): String {
        return this::class.java.classLoader.getResource(path).readText()
    }

    fun readDirectory(path: String): Map<Int, Map<String, String>> {
        return hashMapOf<Int, Map<String, String>>().apply {
            File("/home/maxim/IdeaProjects/Algorithms_and_data_structures/2_Algebraic_algorithms/src/main/resources/$path")
                .listFiles()
                .forEach {
                    this.merge(
                        it.name.replace("(.in)|(.out)".toRegex(), "").replace("test.", "").toInt(),
                        hashMapOf(it.name.let {
                            if(it.contains("in")) "in" else "out"
                        } to read(path + "/" + it.name))
                    ) { old, new -> old.plus(buildMap { putAll(new) }) }
                }
        }
    }
}

fun main() {
    val fileReader = FileReader()
    val readDirectory = fileReader.readDirectory("4.Fibo")
    println(readDirectory.toSortedMap().keys)


}