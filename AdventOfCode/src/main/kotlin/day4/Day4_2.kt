package day4

import readFile

fun day4Task2(): Int {
    val lines: MutableList<String> =
        readFile("D:\\AdventOfCode\\2023\\AOC_23\\AdventOfCode\\out\\production\\AdventOfCode\\day4\\input.txt").toMutableList()

    var index = 0
    var lastSize = 0
    val winingNumbers = mutableMapOf<String, List<String>>()
    do {
        lastSize = lines.size

        val linesToAdd: MutableList<String> = mutableListOf()

        for (i in index until lines.size) {
            val line = lines[i]
            val numbers = parseLine2(line)
            val intersection = numbers.first.intersect(numbers.second.toSet())
            index++
            if (intersection.isNotEmpty()) {
                if (winingNumbers[getLineNumber(line)] == null) {
                    val sublistCopy = lines.subList(i + 1, i + 1 + intersection.size).toList()
                    winingNumbers[getLineNumber(line)] = sublistCopy
                }
                linesToAdd.addAll(winingNumbers[getLineNumber(line)]!!)
            }
        }
        lines.addAll(linesToAdd)
    } while (lines.size != lastSize)

    return lines.size
}

fun getLineNumber(line: String): String {
    return line.substringBefore(':')
}

fun parseLine2(line: String): Pair<List<Int>, List<Int>> {
    val numbers = line.substringAfter(':').trim()
    val splitNumbers = numbers.split('|')
    val leftNumbers = splitNumbers.first().split(' ').filterNot { it.isBlank() }.map { it.toInt() }
    val rightNumbers = splitNumbers.last().split(' ').filterNot { it.isBlank() }.map { it.toInt() }
    return Pair(leftNumbers, rightNumbers)
}