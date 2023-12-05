package day3

import readFile

//533784
fun day3Task1(): Int {
    val numbers: List<Char> = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    val lines =
        readFile("D:\\AdventOfCode\\2023\\AOC_23\\AdventOfCode\\out\\production\\AdventOfCode\\day3\\input3.txt")
    var totalSum = 0
    val connectedNumbersIndexes = mutableListOf<Pair<Int, Int>>()
    var tempIndexBig = 0
    lines.forEach { line ->
        var tempIndex = 0
        line.forEach {
            if (it != '.' && !numbers.contains(it)) {
                totalSum += getConnectedNumbers(
                    connectedNumbersIndexes,
                    numbers,
                    lines,
                    tempIndexBig,
                    tempIndex
                )
            }
            tempIndex++
        }
        tempIndexBig++
    }
    return totalSum
}

fun getConnectedNumbers(
    connectedNumbersIndexes: MutableList<Pair<Int, Int>>,
    numbers: List<Char>,
    lines: List<String>,
    i: Int,
    j: Int
): Int {
    val connectedNumbers = mutableListOf<Int>()
    val indexes = mutableListOf<Pair<Int, Int>>()
    listOf(
        Pair(i - 1, j - 1),
        Pair(i, j - 1),
        Pair(i + 1, j - 1),
        Pair(i - 1, j),
        Pair(i + 1, j),
        Pair(i - 1, j + 1),
        Pair(i, j + 1),
        Pair(i + 1, j + 1),
    ).forEach {
        try {
            lines[it.first][it.second]
            indexes.add(it)
        } catch (e: Exception) {
        }
    }
    indexes.forEach {
        if (numbers.contains(lines[it.first][it.second])) {
            if (!connectedNumbersIndexes.any { ind -> ind.first == it.first && ind.second == it.second }) {
                connectedNumbers.add(findNumber(connectedNumbersIndexes, numbers, lines, it.first, it.second))
            }
        }
    }
    return connectedNumbers.sum()
}

fun findNumber(
    connectedNumbersIndexes: MutableList<Pair<Int, Int>>,
    numbers: List<Char>,
    lines: List<String>,
    i: Int,
    j: Int
): Int {
    var numberAsString = ""
    var tempIndex = 0
    val tempIndexList = mutableListOf<Pair<Int, Int>>()
    lines[i].forEach {
        if (tempIndex >= j) {
            if (numbers.contains(it)) {
                numberAsString += it
                tempIndexList.add(Pair(i, tempIndex))
            } else {
                if (numberAsString.isNotEmpty()) {
                    connectedNumbersIndexes.addAll(tempIndexList)
                    return numberAsString.toInt()
                }
            }
        }
        if (tempIndex < j) {
            if (numbers.contains(it) && it!='\n') {
                numberAsString += it
                tempIndexList.add(Pair(i, tempIndex))
            } else {
                tempIndexList.clear()
                numberAsString = ""
            }
        }
        tempIndex++
    }
    return numberAsString.toInt()
}