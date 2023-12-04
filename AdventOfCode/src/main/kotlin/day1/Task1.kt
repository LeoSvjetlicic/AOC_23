package day1

import java.io.File

fun calibrateDocumentTask1(data: List<String>): Int {
    var totalSum = 0
    data.forEach { string ->
        val firstNumber = string.find { it.isDigit() }
        val lastNumber = string.reversed().find { it.isDigit() }
        if (firstNumber != null && lastNumber != null) {
            totalSum += (firstNumber + lastNumber).toInt()
        }
    }
    return totalSum
}

fun calibrateDocumentTask2(data: List<String>): Int {
    val numbersAsString = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    var totalSum = 0

    for (string in data) {
        println(string)
        val firstNumber = string.find { it.isDigit() }
        val lastNumber = string.findLast { it.isDigit() }

        val firstNumberPair = if (firstNumber != null) {
            Pair(string.indexOf(firstNumber), firstNumber.toString())
        } else {
            Pair(string.length, null)
        }

        val lastNumberPair = if (lastNumber != null) {
            Pair(string.lastIndexOf(lastNumber), lastNumber.toString())
        } else {
            Pair(-1, null)
        }

        val firstNumberAsString = findFirstWordIndex(numbersAsString, string)
        val lastNumberAsString = findLastWordIndex(numbersAsString, string)

        val sorted =
            listOf(
                firstNumberPair,
                lastNumberPair,
                firstNumberAsString,
                lastNumberAsString
            ).dropWhile { it.first < 0 }.sortedBy { it.first }

        if (sorted.isEmpty()) {
            continue
        }

        val firstNonNullPair = sorted.first { it.second != null }
        val lastNonNullPair = sorted.last { it.second != null }

        totalSum += (numberAsWordToStringDigit(
            numbersAsString,
            firstNonNullPair.second!!
        ) + numberAsWordToStringDigit(numbersAsString, lastNonNullPair.second!!)).toInt()
    }
    return totalSum
}

fun numberAsWordToStringDigit(numbersAsWords: List<String>, number: String): String {
    try {
        val tempNumber = number.toInt()
        return tempNumber.toString()
    } catch (e: Exception) {
        numbersAsWords.forEachIndexed { i, word ->
            if (word == number) {
                return (i + 1).toString()
            }
        }
        return ""
    }
}

fun findFirstWordIndex(wordList: List<String>, targetString: String): Pair<Int, String?> {
    var tempPair = Pair<Int, String?>(targetString.length, null)
    for (word in wordList) {
        val index = targetString.indexOf(word, ignoreCase = true)
        if (index != -1 && tempPair.first > index ) {
            tempPair = Pair(index, word)
        }
    }
    return tempPair
}

fun findLastWordIndex(wordList: List<String>, targetString: String): Pair<Int, String?> {
    var tempPair = Pair<Int, String?>(-1, null)
    for (word in wordList) {
        val index = targetString.lastIndexOf(word, ignoreCase = true)
        if (index != -1 && tempPair.first < index) {
            tempPair = Pair(index, word)
        }
    }
    return tempPair
}

fun readFile(filePath: String): List<String> {
    val lines: List<String>
    try {
        lines = File(filePath).readLines()
    } catch (e: Exception) {
        return emptyList()
    }
    return lines
}

private operator fun Char.plus(c: Char): String {
    return this.toString() + c.toString()
}
