import day2.day2Task1
import day2.day2Task2
import day3.day3Task1
import day3.day3Task2
import java.io.File

fun main(args: Array<String>) {
    println(day3Task2())
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