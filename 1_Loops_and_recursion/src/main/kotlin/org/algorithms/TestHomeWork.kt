package org.algorithms

fun main() {
    val fileReader = FileReader()
    val readDirectory = fileReader.readDirectory("0.String")

    readDirectory.map { entry -> entry.value }.forEach{
        map ->
        if (map.get("in")!!.length == map.get("out")!!.toInt()) {
//            println("Значения равны: in=${map.get("in")!!.length} out=${map.get("out")!!.toInt()}")
        } else {
//            println("Значения не равны: in.value=${map.get("in")} in=${map.get("in")!!.length}; out.value=${map.get("out")} out=${map.get("out")!!.toInt()}")
            throw RuntimeException("Не верно определено количество символов")
        }
    }


}