package com.example.emailcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText emailText;
    Button controlBtn;
    TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText=findViewById(R.id.edtEmail);
        controlBtn=findViewById(R.id.btnControl);
        resultText=findViewById(R.id.txtResult);

        controlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uzun yoldan yapım
//                char[] charArray= emailText.getText().toString().toCharArray();
//                String emailAddress="";
//                boolean isHaveAt=false;
//                for (int i=0;i<emailText.getText().length();i++){
//                    if (charArray[i]=='@')
//                        isHaveAt=true;
//                    if (isHaveAt)
//                        emailAddress+=charArray[i];
//                }
//                if (emailAddress.equals("@gmail.com"))
//                    resultText.setText("Google Adresi");
//                else if (emailAddress.equals("@hotmail.com"))
//                    resultText.setText("Microsoft Adresi");
//                else if (emailAddress.equals("@facebook.com"))
//                    resultText.setText("Facebook Adresi");
//                else
//                    resultText.setText("Diğer Adresler");

                if (emailText.getText().toString().endsWith("@gmail.com"))
                    resultText.setText("Google Adresi");
                else if (emailText.getText().toString().endsWith("@hotmail.com"))
                    resultText.setText("Microsoft Adresi");
                else if (emailText.getText().toString().endsWith("@facebook.com"))
                    resultText.setText("Facebook Adresi");
                else
                    resultText.setText("Diğer Adresler");
            }
        });
    }
}
