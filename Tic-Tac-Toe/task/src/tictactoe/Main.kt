package tictactoe

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val values = scanner.nextLine()
    println("---------")
    println("| ${values[0]} ${values[1]} ${values[2]} |")
    println("| ${values[3]} ${values[4]} ${values[5]} |")
    println("| ${values[6]} ${values[7]} ${values[8]} |")
    println("---------")
}