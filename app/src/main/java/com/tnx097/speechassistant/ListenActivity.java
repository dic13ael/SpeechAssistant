package com.tnx097.speechassistant;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class ListenActivity extends AppCompatActivity {

private String message;
private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        setMessage("Följ med mig så ska jag visa ert bord!");
        textview = (TextView)findViewById(R.id.text_listen);
        textview.setText("");
        displayText(textview);


    }
    public void setMessage(String m){
        message = m;
    }

    public void displayText(TextView text) {

        StringBuilder subtext = new StringBuilder(500);
        for(int i = 0; i<message.length(); i++){
            subtext.append(message.charAt(i));
            text.setText(subtext.toString());

        }
    }


}
