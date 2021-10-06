package com.example.gamecaro_android.models;

public class Cell {

    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || player.value == null || player.value == "";
    }
}
