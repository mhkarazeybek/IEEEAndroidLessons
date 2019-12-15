package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView player1Text;
    private TextView player2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Text = findViewById(R.id.txtPlayer1);
        player2Text = findViewById(R.id.txtPlayer2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "btn" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.btnReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1Points=0;
                player2Points=0;
                resetBoard();
                updatePointsText();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }


        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
        setColor();

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        setAlert("1. Oyuncu kazandı!");
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        setAlert("2. Oyuncu kazandı!");
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        setAlert("Berabere!");
        resetBoard();
    }

    private void updatePointsText() {
        player1Text.setText("1. Oyuncu: " + player1Points);
        player2Text.setText("2. Oyuncu: " + player2Points);
    }

    private void resetBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
        setColor();
    }
    private void setColor(){
        if (player1Turn){
            player1Text.setTextColor(Color.RED);
            player2Text.setTextColor(Color.BLACK);
        }else {
            player2Text.setTextColor(Color.RED);
            player1Text.setTextColor(Color.BLACK);
        }
    }

    private void setAlert(String msg){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // alert dialog başlığını tanımlıyoruz.
        alertDialogBuilder.setTitle("Game Over !");

        // alert dialog özelliklerini oluşturuyoruz.
        alertDialogBuilder
                .setMessage("Durum: "+msg)
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher_round)
                .setNeutralButton("TAMAM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
