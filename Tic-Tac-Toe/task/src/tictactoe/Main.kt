package tictactoe

import java.util.*
import kotlin.math.absoluteValue

fun main() {
    val scanner = Scanner(System.`in`)
    val values = scanner.nextLine()
    println("---------")
    println("| ${values[0]} ${values[1]} ${values[2]} |")
    println("| ${values[3]} ${values[4]} ${values[5]} |")
    println("| ${values[6]} ${values[7]} ${values[8]} |")
    println("---------")
    println(getResult(values))
}

fun getResult(values: String): String {
    val oCount = values.count { it == 'O' }
    val xCount = values.count { it == 'X' }
    val drawCount = values.count { it == '_' }
    val xWins = checkIfUserWin('X', values)
    val oWins = checkIfUserWin('O', values)

    return if ((oCount - xCount).absoluteValue > 1 || xWins && oWins) {
        "Impossible"
    } else if (xWins) {
        "X wins"
    } else if (oWins) {
        "O wins"
    } else if (drawCount == 0) {
        "Draw"
    } else {
        "Game not finished"
    }
}

fun checkIfUserWin(symbol: Char, values: String): Boolean {
    return when {
        values[0] == values[1] && values[0] == values[2] && values[0] == symbol -> true
        values[3] == values[4] && values[3] == values[5] && values[3] == symbol -> true
        values[6] == values[7] && values[6] == values[8] && values[6] == symbol -> true
        values[0] == values[3] && values[0] == values[6] && values[0] == symbol -> true
        values[1] == values[4] && values[1] == values[7] && values[1] == symbol -> true
        values[2] == values[5] && values[2] == values[8] && values[2] == symbol -> true
        values[0] == values[4] && values[0] == values[8] && values[0] == symbol -> true
        values[6] == values[4] && values[6] == values[2] && values[6] == symbol -> true
        else -> false
    }
}