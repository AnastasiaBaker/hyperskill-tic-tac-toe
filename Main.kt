package tictactoe

fun main() {
    val cells = Array(3) { Array(3) { '_' } }
    var endText = ""

    printField(cells)

    while (endText.isEmpty()) {
        print("Enter the coordinates: ")

        val xY = readLine()!!.split(" ")

        when {
            !Regex("""\d\s\d""").matches(xY.joinToString(" ")) -> println("You should enter numbers!")
            !Regex("""[1-3]\s[1-3]""").matches(xY.joinToString(" ")) -> println("Coordinates should be from 1 to 3!")
            cells[xY[0].toInt() - 1][xY[1].toInt() - 1] != '_' -> println("This cell is occupied! Choose another one!")
            else -> cells[xY[0].toInt() - 1][xY[1].toInt() - 1] = turn(cells)
        }

        printField(cells)

        endText = when {
            won('X', cells) -> "X wins"
            won('O', cells) -> "O wins"
            draw(cells) -> "Draw"
            else -> ""
        }
    }

    println(endText)
}

fun turn(cells: Array<Array<Char>>): Char {
    var xCount = 0
    var oCount = 0

    for (i in cells.indices) for (c in cells[i]) if (c == 'X') xCount++ else if (c == 'O') oCount++

    return if (xCount == oCount) 'X' else 'O'
}

fun printField(cells: Array<Array<Char>>) {
    println("---------")

    for (row in cells) println("| ${row.joinToString(" ")} |")

    println("---------")
}

fun draw(cells: Array<Array<Char>>): Boolean {
    for (i in cells.indices) for (c in cells[i]) if (c == '_') return false
    return true
}

fun won(player: Char, cells: Array<Array<Char>>): Boolean {
    for (row in cells) if (row.all { it == player }) return true
    for (i in 0..2) if (cells[0][i] == player && cells[0][i] == cells[1][i] && cells[1][i] == cells[2][i]) return true
    if (cells[0][0] == player && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2] ||
            cells[0][2] == player && cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) return true
    return false
}