package org.algorithms.LuckyTicket

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.algorithms.FileReader
import java.util.function.Supplier
import java.util.stream.Stream


class Ticket(val leftSum: Int = 0, val rightSum: Int = 0) {

    suspend fun isEquals(): Boolean {
        return leftSum == rightSum
    }

    suspend fun sum(number: Int, isLeft: Supplier<Boolean>): Ticket {
        return if (isLeft.get()) {
            Ticket(leftSum = leftSum + number, rightSum = rightSum)
        } else {
            Ticket(leftSum = leftSum, rightSum = rightSum + number)
        }
    }
}

class RecursiveSearch(val n: Int) {
    suspend fun findLuckyTicket(
        index: Int = 1, ticket: Ticket = Ticket()
    ): Int {
        // TODO Сделать хвостовую рекурсию
        return if (index > n * 2) {
            if (ticket.isEquals()) 1 else 0
        } else {
            return (0..9)
                .map { i ->
                    findLuckyTicket(index = index + 1, ticket = ticket.sum(i) {
                        index <= n
                    })
                }.sum()
        }
    }

}

fun main() = runBlocking {
    val fileReader = FileReader()
    /**
    Для in=4 luckyTicketsCount=4816030 время выполнения 3 секунд и 37 msec
    Для in=3 luckyTicketsCount=55252 время выполнения 0 секунд и 27 msec
    Для in=2 luckyTicketsCount=670 время выполнения 0 секунд и 1 msec
    Для in=1 luckyTicketsCount=10 время выполнения 0 секунд и 0 msec
     */
    fileReader.readDirectory("1.Tickets").map { entry -> entry.value }.forEach { map ->
        launch {
            val start = System.currentTimeMillis()
            val n = map.get("in")
            val luckyTicketsCount = RecursiveSearch(n!!.toInt())
            val findLuckyTicket = luckyTicketsCount.findLuckyTicket()
            val end = System.currentTimeMillis()
            val msec = (end - start) % 1000
            val sec = (end - start) / 1000
            val isValid = findLuckyTicket == map.get("out")!!.toInt()
            println("Для in=$n luckyTicketsCount=$findLuckyTicket isValid=$isValid время выполнения $sec секунд и $msec msec")

        }


    }
}

