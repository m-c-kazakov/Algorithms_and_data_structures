package org.algorithms

import java.math.BigInteger


/**
 *
 * Числа Фибоначчи — элементы числовой последовательности в которой первые два числа равны 0 и 1, а каждое последующее число равно сумме двух предыдущих чисел
 *
 *  На первой строчке записано целое число N >= 0.
 *  Найти и вывести на экран точное значение N-ого числа Фибоначчи.
 *
 *  Решить задачу разными способами.
 *  OK 1. Через рекурсию.
 *  2. Через итерацию
 *  3. Через формулу золотого сечения.
 *  4. Через возведение матрицы в степень.
 */

fun iteration(n: BigInteger): BigInteger {

    return when (n) {
        BigInteger.ZERO -> BigInteger.ZERO
        BigInteger.ONE -> BigInteger.ONE
        else -> {
            var first: BigInteger = BigInteger.ZERO
            var second: BigInteger = BigInteger.ONE
            var index = BigInteger.ZERO

            do {
                index += BigInteger.ONE
                val new_first = second
                val new_second = first + second
                first = new_first
                second = new_second
            } while (index < n)
            return first
        }


    }


}

tailrec fun recursion(n: BigInteger, currentIndex: BigInteger = BigInteger.ZERO, first: BigInteger = BigInteger.ZERO, second: BigInteger = BigInteger.ONE
): BigInteger {

    return when (n) {
        BigInteger.ZERO -> BigInteger.ZERO
        BigInteger.ONE -> BigInteger.ONE
        currentIndex -> first
        else -> recursion(n = n, currentIndex = currentIndex + BigInteger.ONE, first = second, second = first + second)
    }

}

fun main() {
    val fileReader = FileReader()
    /**
     */
    val resultMap: MutableMap<String, String> = HashMap()

    fileReader.readDirectory("4.Fibo").toSortedMap().map { entry -> entry.value }.forEach { map ->
        // iteration Для номера=1000000 isValid=true время выполнения 14 секунд и 157 msec
        // recursion Для номера=1000000 isValid=true время выполнения 11 секунд и 557 msec
        val start = System.currentTimeMillis()
        val n = map.get("in")
        val out = map.get("out")
        val result = recursion(n=BigInteger.valueOf(n!!.toLong()))
//        val result = iteration(n = BigInteger.valueOf(n!!.toLong()))
//        println("out=$out result=$result")

        val end = System.currentTimeMillis()
        val msec = (end - start) % 1000
        val sec = (end - start) / 1000
        val isValid = result == BigInteger(out.toString())
        println("Для номера=$n isValid=$isValid время выполнения $sec секунд и $msec msec")
    }
}