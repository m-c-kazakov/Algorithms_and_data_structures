package org.algorithms

import java.math.BigInteger
import java.util.PrimitiveIterator


/**
 * Простое число — натуральное (целое положительное) число, имеющее ровно два различных натуральных делителя — единицу и самого себя.
 * Другими словами, число x является простым, если оно больше 1 и при этом делится без остатка только на 1 и на x. К примеру, 5 — простое число, а 6 не является простым числом, так как, помимо 1 и 6, оно также делится на 2 и на 3.
 *
 * На первой строчке записано целое число N >= 1.
 * Найти количество простых чисел от 1 до N.
 *
 * Решить задачу разными способами.
 * 1. Через перебор делителей.
 * 2. Несколько оптимизаций перебора делителей
 * 3. Решето Эратосфена со сложностью O(n log log n).
 * 4. Решето Эратосфена с оптимизацией памяти: битовая матрица, по 32 значения в одном int
 * 5. Решето Эратосфена со сложностью O(n)
 */

// 1. Через перебор делителей.
fun bruteForceOfDivisors(n: Int): Int {
    // Для номера=100000 isValid=true время выполнения 1 секунд и 555 msec

    var count = 0
    for (i in 2..n) {
        var result = true
        for (j in 2 until i) {
            if (i%j == 0) {
                result = false
                break
            }
        }
        if (result) {
            count++
        }
    }

    return count
}


/**
 * Выписать подряд все целые числа от двух до n (2, 3, 4, …, n).
 * Пусть переменная p изначально равна двум — первому простому числу.
 * Зачеркнуть в списке числа от 2p до n считая шагами по p (это будут числа кратные p: 2p, 3p, 4p, …).
 * Найти первое незачёркнутое число в списке, большее чем p, и присвоить значению переменной p это число.
 * Повторять шаги 3 и 4, пока возможно.
 */
fun theSieveOfEratosthenes(n: Int): Int {

    /**
     * Для номера=100000 isValid=true время выполнения 0 секунд и 274 msec
     * Для номера=1000000 isValid=true время выполнения 10 секунд и 735 msec
     */
    println("N=$n")
    val primitiveNumbers = mutableListOf<Int>()

    for (candidate in 2..n) {
//        println("candidate=$candidate")
        val any = primitiveNumbers.any { candidate % it == 0 }
        if (!any) primitiveNumbers.add(candidate) else continue
    }

//    println(primitiveNumbers)
    return primitiveNumbers.size


}

tailrec fun recursionTheSieveOfEratosthenes(n: Int, candidate: Int=2, primitiveNumbers: MutableList<Int> = mutableListOf()): Int {

    if (candidate > n) {
        return primitiveNumbers.size
    }
    val any = primitiveNumbers.any { candidate % it == 0 }
    if (!any) primitiveNumbers.add(candidate)
    val newCandidate = if (candidate==2) candidate+1 else candidate+2
    return recursionTheSieveOfEratosthenes(n=n, candidate=newCandidate, primitiveNumbers=primitiveNumbers)
}




fun main() {
    val fileReader = FileReader()
    /**
     */

    fileReader.readDirectory("5.Primes").toSortedMap().map { entry -> entry.value }.forEach { map ->

        val start = System.currentTimeMillis()
        val n = map.get("in")
        val out = map.get("out")
        val result = recursionTheSieveOfEratosthenes(n!!.toInt())
        println("out=$out result=$result")

        val end = System.currentTimeMillis()
        val msec = (end - start) % 1000
        val sec = (end - start) / 1000
        val isValid = result == out!!.toInt()
        println("Для номера=$n isValid=$isValid время выполнения $sec секунд и $msec msec")
    }
}