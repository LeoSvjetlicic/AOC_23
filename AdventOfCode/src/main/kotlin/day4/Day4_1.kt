package day4

import readFile

fun day4Task1(): Int {
    val lines =
        readFile("D:\\AdventOfCode\\2023\\AOC_23\\AdventOfCode\\out\\production\\AdventOfCode\\day4\\input.txt")
    var totalSum = 0

    lines.forEach {
        val numbers = parseLine(it)
        var numbersHit = 0
        numbers.second.forEach { num ->
            if (numbers.first.contains(num)) {
                numbersHit++
            }
        }
        println(numbersHit)
        println(calculateResult(numbersHit))
        totalSum += calculateResult(numbersHit)
    }
    return totalSum
}

fun calculateResult(num: Int): Int {
    var final = 1
    for (i in 1 until num) {
        final *= 2
    }
    return if (num == 0) {
        0
    } else {
        final
    }
}

fun parseLine(line: String): Pair<List<Int>, List<Int>> {
    val numbers = line.substringAfter(':').trim()
    val splitNumbers = numbers.split('|')
    val winningNumbers = splitNumbers.first().split(' ').filterNot { it.isBlank() }
    val myNumbers = splitNumbers.last().split(' ').filterNot { it.isBlank() }
    println(Pair(winningNumbers.map { it.toInt() }, myNumbers.map { it.toInt() }))
    return Pair(winningNumbers.map { it.toInt() }, myNumbers.map { it.toInt() })
}