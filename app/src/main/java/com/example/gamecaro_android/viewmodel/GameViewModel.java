package com.example.gamecaro_android.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gamecaro_android.models.Cell;
import com.example.gamecaro_android.models.Game;
import com.example.gamecaro_android.models.Player;
import com.example.gamecaro_android.views.PlayActivity;

import java.util.Arrays;


public class GameViewModel extends ViewModel {
    private Game game;
    public ObservableArrayMap<String, String> cells;

    public void init() {
        game = new Game();
        cells = new ObservableArrayMap<>();
    }

    public void onClickCell(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currPlayer);
            cells.put(Integer.toString(row) + Integer.toString(column), game.currPlayer.value);
            if (game.isEnd()) {

            } else
                game.switchPlayer();
        }

    }

    public MutableLiveData<Player> getWinner() {
        return game.winner;
    }

    public void resetGame() {
        cells.clear();
        game.reset();
    }
}
