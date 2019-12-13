package com.example.lottery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int sansliSayi;
    int sansliSayilar[]=new int[6];
    TextView txt1,txt2,txt3,txt4,txt5,txt6,resultText;
    int sayac;
    int turSayisi;
    Random rnd=new Random();

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1=findViewById(R.id.textView1);
        txt2=findViewById(R.id.textView2);
        txt3=findViewById(R.id.textView3);
        txt4=findViewById(R.id.textView4);
        txt5=findViewById(R.id.textView5);
        txt6=findViewById(R.id.textView6);
        resultText=findViewById(R.id.txtResult);
        sayac=0;
        turSayisi=1;
    }

    private void start(){
        handler= new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                while (sayac<6) {
                    sansliSayi = rnd.nextInt(50) + 1;
                    if (!contains(sansliSayi)) {
                        sansliSayilar[sayac] = sansliSayi;
                        sayac++;
                    }
                }
                Arrays.sort(sansliSayilar);
                txt1.setText(""+sansliSayilar[0]);
                txt2.setText(""+sansliSayilar[1]);
                txt3.setText(""+sansliSayilar[2]);
                txt4.setText(""+sansliSayilar[3]);
                txt5.setText(""+sansliSayilar[4]);
                txt6.setText(""+sansliSayilar[5]);
                sayac=0;
                turSayisi++;
                if (turSayisi%20==0){
                    handler.removeCallbacks(runnable);
                    for (int i : sansliSayilar) {
                        resultText.setText(resultText.getText()+""+i+"-");
                    }
                    resultText.setText(resultText.getText().toString().substring(0,resultText.getText().length()-1)+"\n");
                    return;
                }
                handler.postDelayed(runnable,250);

            }
        };
        handler.post(runnable);
    }

    public void cekilisYap(View view) {
        start();
    }

    //ürettiğimiz sayıyı daha önce üretmiş miyiz?
    private boolean contains(int sayi){
        for (int i=0;i<6;i++){
            if (sansliSayilar[i]==sayi)
                return true;
        }
        return false;
    }
}
