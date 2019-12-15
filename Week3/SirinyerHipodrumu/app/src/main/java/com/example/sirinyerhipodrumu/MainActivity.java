package com.example.sirinyerhipodrumu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView sahBaturImg,gulBaturImg;
    TextView finishText;
    Button startBtn;

    Handler handler=new Handler();
    Timer timer;

    TextToSpeech mT2S;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sahBaturImg=findViewById(R.id.imgSahBatur);
        gulBaturImg=findViewById(R.id.imgGulBatur);
        finishText=findViewById(R.id.txtFinish);
        startBtn=findViewById(R.id.btnStart);

        gulBaturImg.setX(0.0f);
        gulBaturImg.setY(500.0f);
        sahBaturImg.setX(0.0f);
        sahBaturImg.setY(300.0f);

        mT2S=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (!(status==TextToSpeech.ERROR))
                    mT2S.setLanguage(new Locale("tr","TR"));
                else
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    Random rnd = new Random();
    public void start(View view) {
        startBtn.setClickable(false);
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        sahBaturImg.setX(sahBaturImg.getX()+(float) rnd.nextInt(100));
                        gulBaturImg.setX(gulBaturImg.getX()+(float) rnd.nextInt(100));

                        if ((sahBaturImg.getX()+sahBaturImg.getWidth())>
                                (gulBaturImg.getX()+gulBaturImg.getWidth()))
                            speaker("Şah Batur Önde");
                        else if ((sahBaturImg.getX()+sahBaturImg.getWidth())<
                                (gulBaturImg.getX()+gulBaturImg.getWidth()))
                            speaker("Gül Batur Önde");
                        else
                            speaker("Başa Baş");

                        //Sah Batur Kazanma Durumu
                        if ((sahBaturImg.getX()+sahBaturImg.getWidth())>=finishText.getX()
                                && sahBaturImg.getX()>gulBaturImg.getX()){
                            timer.cancel();
                            setAlert("Şah Batur Kazandı!");
                            speaker("Şah Batur Kazandı!");
                        }
                        //Gül Batur Kazanma Durumu
                        else if ((gulBaturImg.getX()+gulBaturImg.getWidth())>=finishText.getX()
                                && gulBaturImg.getX()>sahBaturImg.getX()){
                            timer.cancel();
                            setAlert("Gül Batur Kazandı");
                            speaker("Gül Batur Kazandı");
                        }
                        //Berabere Durumu
                        else if ((sahBaturImg.getX()+sahBaturImg.getWidth())==finishText.getX()&&
                        sahBaturImg.getX()==gulBaturImg.getX()){
                            setAlert("Berabere");
                            speaker("Berabere!");
                        }
                    }
                });
            }
        },0,1000);
    }
    private void setAlert(String message){

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        // Set Dialog Title
        alertDialog.setTitle("Sonuçlar!");
        // Set Dialog Message
        alertDialog.setMessage(message);
        // Set OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                sahBaturImg.setX(0.0f);
                gulBaturImg.setX(0.0f);
                startBtn.setClickable(true);
            }
        });
        // Show Alert Message
        alertDialog.show();
    }

    private void speaker(String text){
        mT2S.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }
}
