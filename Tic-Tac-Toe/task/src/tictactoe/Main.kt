package tictactoe

import java.util.*
import kotlin.math.abs

val scanner = Scanner(System.`in`)
var grid = arrayOf(
        charArrayOf('_', '_', '_'),
        charArrayOf('_', '_', '_'),
        charArrayOf('_', '_', '_')
)
var result = ""

fun main() {
    val values = scanner.nextLine()
    fillGridWithValues(values)
    displayGrid()
    makeMove()
    displayGrid()
    calculateResult()
    displayResult()
}

fun calculateResult() {
    val oCount = grid.map { it.count { it == 'O' } }.sum()
    val xCount = grid.map { it.count { it == 'X' } }.sum()
    val drawCount = grid.map { it.count { it == '_' } }.sum()
    val xWins = checkIfUserWin('X')
    val oWins = checkIfUserWin('O')

    result = if (abs(oCount - xCount) > 1 || xWins && oWins) {
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

fun fillGridWithValues(values: String) {
    grid[0][0] = values[0]
    grid[0][1] = values[1]
    grid[0][2] = values[2]
    grid[1][0] = values[3]
    grid[1][1] = values[4]
    grid[1][2] = values[5]
    grid[2][0] = values[6]
    grid[2][1] = values[7]
    grid[2][2] = values[8]
}

fun checkIfUserWin(symbol: Char): Boolean {
    return when {
        grid[0][0] == grid[0][1] && grid[0][0] == grid[0][2] && grid[0][0] == symbol -> true
        grid[1][0] == grid[1][1] && grid[1][0] == grid[1][2] && grid[1][0] == symbol -> true
        grid[2][0] == grid[2][1] && grid[2][0] == grid[2][2] && grid[2][0] == symbol -> true
        grid[0][0] == grid[1][0] && grid[0][0] == grid[2][0] && grid[0][0] == symbol -> true
        grid[0][1] == grid[1][1] && grid[0][1] == grid[2][1] && grid[0][1] == symbol -> true
        grid[0][2] == grid[1][2] && grid[0][2] == grid[2][2] && grid[0][2] == symbol -> true
        grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] == symbol -> true
        grid[2][0] == grid[1][1] && grid[2][0] == grid[0][2] && grid[2][0] == symbol -> true
        else -> false
    }
}

fun displayGrid() {
    println("---------")
    println("| ${grid[0][0]} ${grid[0][1]} ${grid[0][2]} |")
    println("| ${grid[1][0]} ${grid[1][1]} ${grid[1][2]} |")
    println("| ${grid[2][0]} ${grid[2][1]} ${grid[2][2]} |")
    println("---------")
}

fun displayResult() {
    println(result)
}

fun makeMove() {
    do {
        print("Enter the coordinates: ")
        val x = scanner.next()
        val y = scanner.next()
        val isValidCoordinates = isValidateCoordinates(x, y)
        if (!isValidCoordinates) {
            continue
        }
        grid[x.toInt() - 1][y.toInt() - 1] = 'X'
    } while (!isValidCoordinates)
}

fun isValidateCoordinates(x: String, y: String): Boolean {
    return when {
        x.length != 1 || y.length != 1 || !x[0].isDigit() || !y[0].isDigit() -> {
            println("You should enter numbers!")
            false
        }
        x.toInt() !in 1..3 || y.toInt() !in 1..3 -> {
            println("Coordinates should be from 1 to 3!")
            false
        }
        grid[x.toInt() - 1][y.toInt() - 1] != '_' -> {
            println("This cell is occupied! Choose another one!")
            false
        }
        else -> true
    }
}