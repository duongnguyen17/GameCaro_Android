package com.example.gamecaro_android.models;

import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;

public class Game {
    // kích thước bàn cờ  BOARD_SIZE X BOARD_SIZE
    public static final int BOARD_SIZE = 3;

    public Player player1;
    public Player player2;
    public Player currPlayer;
    public Cell[][] cells;
    public MutableLiveData<Player> winner = new MutableLiveData<>();

    public Game() {
        player1 = new Player("X");
        player2 = new Player("O");
        currPlayer = player1;
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
    }

    // kiểm tra kết thúc game
    public boolean isEnd() {
        if (check()) {
            winner.setValue(currPlayer);
            return true;
        }
        if (isFull()) {
            winner.setValue(null);
            return true;
        }
        return false;
    }


    private boolean check() {
        if (equalCell(cells[0][0], cells[0][1], cells[0][2])) {
            return true;
        }
        if (equalCell(cells[1][0], cells[1][1], cells[1][2])) {
            return true;
        }
        if (equalCell(cells[2][0], cells[2][1], cells[2][2])) {
            return true;
        }
        if (equalCell(cells[0][0], cells[1][0], cells[2][0])) {
            return true;
        }
        if (equalCell(cells[0][1], cells[1][1], cells[2][1])) {
            return true;
        }
        if (equalCell(cells[0][2], cells[1][2], cells[2][2])) {
            return true;
        }
        if (equalCell(cells[0][0], cells[1][1], cells[2][2])) {
            return true;
        }
        if (equalCell(cells[2][0], cells[1][1], cells[0][2])) {
            return true;
        }
        return false;
    }

    // kiểm tra full bàn cờ
    private boolean isFull() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell == null || cell.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    ////kiểm tra 3 ô cùng kí tự
    private boolean equalCell(Cell... cells) {
        if (cells == null || cells.length == 0) {
            return false;
        }
        for (Cell cell : cells) {
            if (cell == null || cell.player.value == null || cell.player.value.length() == 0) {
                return false;
            }
        }
        for (int i = 1; i < cells.length; ++i) {
            if (!cells[0].player.value.equals(cells[i].player.value)) {
                return false;
            }
        }
        return true;
    }

    // đổi người chơi
    public void switchPlayer() {
        currPlayer = currPlayer == player1 ? player2 : player1;
    }

    // reset
    public void reset() {
        currPlayer = player1;
        for(Cell[] row : cells){
            Arrays.fill(row, null);
        }
    }
}
