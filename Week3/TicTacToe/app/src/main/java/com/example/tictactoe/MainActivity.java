package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView player1Text, player2Text;
    Button resetBtn;
    Button buttons[][]=new Button[3][3];
    private boolean player1Turn=true;

    private int player1Point;
    private int player2Point;
    private int roundCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Text=findViewById(R.id.txtPlayer1);
        player2Text=findViewById(R.id.txtPlayer2);
        resetBtn=findViewById(R.id.btnReset);

        for (int i=0; i<3;i++){
            for (int j=0;j<3;j++){
                String btnID="btn"+i+j;
                int resid=getResources().getIdentifier(btnID,"id",getPackageName());
                buttons[i][j]=findViewById(resid);
            }
        }

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1Point=0;
                player2Point=0;
                player1Turn=true;
                roundCount=0;
                uptadeBoard();
                resetBoar();
            }
        });
    }

    public void uptadeBoard(){
        player1Text.setText("1. Oyuncu: "+player1Point);
        player2Text.setText("2. Oyuncu: "+player2Point);
    }
    public void resetBoar(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setText("");
                buttons[i][j].setClickable(true);
            }
        }
    }

    public void onclick(View view) {
        Button clickedBtn= (Button) view;
        clickedBtn.setClickable(false);
        if (player1Turn){
            clickedBtn.setText("X");
        }else{
            clickedBtn.setText("O");
        }

        if (checkWin()){

            //Player1 kazanma durumu
            if (player1Turn){
                player1Point++;
                player1Text.setText("1. Oyuncu: "+player1Point);
            }
            //Player2 kazanma durumu
            else{
                player2Point++;
                player2Text.setText("2. Oyuncu: "+player2Point);
            }
            resetBoar();
        }else {
            player1Turn=!player1Turn;
        }
        roundCount++;
    }
    private boolean checkWin(){
        String field[][]=new String[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }

        for (int i=0;i<3;i++){
            if (field[i][0].equals(field[i][1]) &&
                    field[i][0].equals(field[i][2]) &&
                    !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i=0;i<3;i++){
            if (field[0][i].equals(field[1][i]) &&
                    field[0][i].equals(field[2][i]) &&
                    !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1]) &&
                field[0][0].equals(field[2][2])&&
                !field[0][0].equals("")){
            return true;
        }
        if (field[0][2].equals(field[1][1]) &&
                field[0][2].equals(field[2][0])&&
                !field[0][2].equals("")){
            return true;
        }
        return false;

    }
}
