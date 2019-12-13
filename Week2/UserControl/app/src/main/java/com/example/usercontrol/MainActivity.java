package com.example.usercontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    TextView resultText;
    Button loginBtn;
    String username="IEEE_CS_Android";
    String password="IEEE_123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameText=findViewById(R.id.edtIUsername);
        passwordText=findViewById(R.id.edtPassword);
        resultText=findViewById(R.id.txtResult);

    }

    public void tiklandi(View view) {
        if (usernameText.getText().toString().equals(username) && passwordText.getText().toString().equals(password)){
            resultText.setText("Username ve Password Doğru");
        }else{
            resultText.setText("Username ve ya Password Yanlış");
        }
    }
}
