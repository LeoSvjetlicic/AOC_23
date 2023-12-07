import day4.day4Task2
import java.io.File

fun main(args: Array<String>) {
    println(day4Task2())
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