package org.algorithms.LuckyTicket

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.algorithms.FileReader

// Хранит результат предыдущих вычислений
// Индекс указывается на N
// Значение это коллекция, где каждый элемент это количество возможных комбинацией чисел половины билета, которые в сумме дают число представленное в коллекции
var resultMap: MutableMap<Int, List<Int>> = HashMap()

fun getCombinationCount(n: Int) {
    if (n <= 0) {
        throw IllegalAccessException("Не допустимое N=$n")
    } else if (1 == n) {
        resultMap[n] = listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    } else {
        // Рекурсивно вызывать функцию, пока не будут значения для всех нужных n
        var ints = resultMap[n - 1]
        if (ints.isNullOrEmpty()) {
            getCombinationCount(n - 1)
            ints = resultMap[n - 1]
        }
        val listForNCountCombination = MutableList<Int>(n * 9 + 1) { 0 }
        (0..9).forEach {
            var index = it
            for (value in ints!!) {
                listForNCountCombination[index] += value
                index++
            }
        }
        resultMap[n] = listForNCountCombination


    }
    assert(resultMap[n]!!.size == 9 * n + 1)
}



fun getLuckyTicketCount(n: Int): Int {
    getCombinationCount(n)
    return resultMap[n]!!.sumOf { i -> i * i }
}

fun main() = runBlocking {
    val fileReader = FileReader()
    /**
    Для in=4 luckyTicketsCount=4816030 isValid=true время выполнения 0 секунд и 14 msec
    Для in=3 luckyTicketsCount=55252 isValid=true время выполнения 0 секунд и 0 msec
    Для in=2 luckyTicketsCount=670 isValid=true время выполнения 0 секунд и 0 msec
    Для in=1 luckyTicketsCount=10 isValid=true время выполнения 0 секунд и 0 msec
    Для in=10 luckyTicketsCount=-1271881592 isValid=false время выполнения 0 секунд и 1 msec
    Для in=9 luckyTicketsCount=-978059040 isValid=false время выполнения 0 секунд и 0 msec
    Для in=8 luckyTicketsCount=1988466590 isValid=false время выполнения 0 секунд и 0 msec
    Для in=7 luckyTicketsCount=-865764600 isValid=false время выполнения 0 секунд и 1 msec
    Для in=6 luckyTicketsCount=926464756 isValid=false время выполнения 0 секунд и 0 msec
    Для in=5 luckyTicketsCount=432457640 isValid=true время выполнения 0 секунд и 0 msec
     */
    fileReader.readDirectory("1.Tickets").map { entry -> entry.value }.forEach { map ->
        launch {
            val start = System.currentTimeMillis()
            val n = map.get("in")
            val findLuckyTicket = getLuckyTicketCount(n!!.toInt())
            val end = System.currentTimeMillis()
            val msec = (end - start) % 1000
            val sec = (end - start) / 1000
            val isValid = findLuckyTicket.toLong() == map.get("out")!!.toLong()
            println("Для in=$n luckyTicketsCount=$findLuckyTicket isValid=$isValid время выполнения $sec секунд и $msec msec")

        }
    }
}


