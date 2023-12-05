package day2

import java.io.File

const val RED_KEY = "red"
const val BLUE_KEY = "blue"
const val GREEN_KEY = "green"

fun day2Task1(red: Int, green: Int, blue: Int): Int {
    val lines =
        readFile("D:\\AdventOfCode\\2023\\AOC_23\\AdventOfCode\\out\\production\\AdventOfCode\\day2\\input2.txt")
    val colors = mapOf(Pair(red, "red"), Pair(blue, "blue"), Pair(green, "green"))
    var totalSum = 0
    lines.forEach { line ->
        val transformedLine = parseLine(line)
        var isValid = true
        transformedLine.second.forEach { turn ->
            colors.forEach { color ->
                turn.forEach {
                    if (color.value == it.second) {
                        if (isValid) {
                            isValid = color.key >= it.first
                        }
                    }
                }
            }
        }
        if (isValid) {
            totalSum += transformedLine.first
        }
    }
    return totalSum
}

fun day2Task2(): Int {
    val lines =
        readFile("D:\\AdventOfCode\\2023\\AOC_23\\AdventOfCode\\out\\production\\AdventOfCode\\day2\\input2.txt")
    var totalSum = 0
    lines.forEach { line ->
        val transformedLine = parseLine(line)
        val colors = mutableMapOf(Pair(RED_KEY, 0), Pair(BLUE_KEY, 0), Pair(GREEN_KEY, 0))
        transformedLine.second.forEach { turn ->
            colors.forEach { color ->
                turn.forEach {
                    if (color.key == it.second) {
                        if (color.value < it.first) {
                            colors.replace(color.key, it.first)
                        }
                    }
                }
            }
        }
        totalSum += colors[RED_KEY]!! * colors[BLUE_KEY]!! * colors[GREEN_KEY]!!
    }
    return totalSum
}

fun parseLine(line: String): Pair<Int, List<List<Pair<Int, String>>>> {
    val index = Regex("[0-9]+").findAll(line)
        .map(MatchResult::value)
        .toList().first().toInt()
    val cubes = line.substringAfter(':')
    val rounds = cubes.split(Regex(";"))
    val turns = mutableListOf<List<String>>()
    rounds.map {
        turns.add(it.split(Regex(",")))
    }
    val transformedData = turns.map { turn ->
        turn.map {
            val number = Regex("[0-9]+").findAll(it)
                .map(MatchResult::value)
                .toList().first()
            val color = Regex("[a-z]+").findAll(it)
                .map(MatchResult::value)
                .toList().first()
            Pair(number.toInt(), color)
        }
    }
    return Pair(index, transformedData)
}

fun readFile(filePath: String): List<String> {
    val lines: List<String>
    try {
        lines = File(filePath).readLines()
    } catch (e: Exception) {
        println(e.message)
        return emptyList()
    }
    return lines
}
