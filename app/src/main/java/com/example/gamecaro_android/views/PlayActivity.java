package com.example.gamecaro_android.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.gamecaro_android.R;
import com.example.gamecaro_android.databinding.ActivityPlayBinding;
import com.example.gamecaro_android.models.Player;
import com.example.gamecaro_android.viewmodel.GameViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;


public class PlayActivity extends AppCompatActivity {

    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlayBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_play);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.init();
        binding.setGameViewModel(gameViewModel);
        gameViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    private void onGameWinnerChanged(Player winner) {
        String winnerTemp = winner == null || winner.value == "" || winner.value.length() == 0 ? "Draw" : winner.value + " win";
//        Toast.makeText(getApplicationContext(), winnerTemp, Toast.LENGTH_SHORT).show();
        new MaterialAlertDialogBuilder(PlayActivity.this).setTitle("End game!").setMessage(winnerTemp).setPositiveButton("Play again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gameViewModel.resetGame();
            }
        }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PlayActivity.this.finish();
                System.exit(0);
            }
        }).show();
    }
}