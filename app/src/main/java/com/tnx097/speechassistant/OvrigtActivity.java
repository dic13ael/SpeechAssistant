package com.tnx097.speechassistant;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class OvrigtActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
private String words;
    private TextToSpeech myTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovrigt);

        //button animation
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(250); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate

        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in






        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);

        startActivityForResult(checkTTSIntent, 0);

        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText enteredText = (EditText)findViewById(R.id.edtInput);
                words = enteredText.getText().toString();

                String words2 = words.replace("å", "oa");
                String words3 = words2.replace("ä", "ae");
                String words4 = words3.replace("ö", "oe");

                // Repeat animation for some time
                int sec=1+words4.length()/6;
                animation.setRepeatCount(sec);
                button.startAnimation(animation);


                speak(words4);



            }
        });

        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                listen();

            }
        });
    }

    private void speak(String s) {
        myTTS.speak(s, TextToSpeech.QUEUE_FLUSH, null);

    }

    public void onInit(int initStatus) {
        if (initStatus == TextToSpeech.SUCCESS) {
            myTTS.setLanguage(new Locale("sv", "SE"));

        } else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                myTTS = new TextToSpeech(this, this);

            }
            else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }


    public void listen(){
        Intent intent = new Intent(this, ListenActivity2.class);
        startActivity(intent);
    }

}
