package tictactoe

import java.util.*
import kotlin.math.abs

val scanner = Scanner(System.`in`)
var grid = arrayOf(
        charArrayOf(' ', ' ', ' '),
        charArrayOf(' ', ' ', ' '),
        charArrayOf(' ', ' ', ' ')
)
var result = ""
var isGameFinished = false
var currentPlayer = ' '

fun main() {
    displayGrid()
    while(!isGameFinished) {
        makeMove()
        displayGrid()
        calculateResult()
    }
    displayResult()
}

fun calculateResult() {
    val oCount = grid.map { it.count { it == 'O' } }.sum()
    val xCount = grid.map { it.count { it == 'X' } }.sum()
    val drawCount = grid.map { it.count { it == ' ' } }.sum()
    val wins = checkIfUserWin(currentPlayer)

    result = if (abs(oCount - xCount) > 1) {
        isGameFinished = true
        "Impossible"
    } else if (wins) {
        isGameFinished = true
        "$currentPlayer wins"
    } else if (drawCount == 0) {
        isGameFinished = true
        "Draw"
    } else {
        "Game not finished"
    }
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
    currentPlayer = if (currentPlayer == ' ' || currentPlayer == 'O') {
        'X'
    } else {
        'O'
    }

    do {
        print("Enter the coordinates: ")
        val x = scanner.next()
        val y = scanner.next()
        val isValidCoordinates = isValidateCoordinates(x, y)
        if (!isValidCoordinates) {
            continue
        }
        grid[x.toInt() - 1][y.toInt() - 1] = currentPlayer
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
        grid[x.toInt() - 1][y.toInt() - 1] != ' ' -> {
            println("This cell is occupied! Choose another one!")
            false
        }
        else -> true
    }
}