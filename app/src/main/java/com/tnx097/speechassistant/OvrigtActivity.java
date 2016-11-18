package com.tnx097.speechassistant;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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




        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);

        startActivityForResult(checkTTSIntent, 0);

        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText enteredText = (EditText)findViewById(R.id.edtInput);
                words = enteredText.getText().toString();
                Log.d("myapp", "1");
                speak(words);
                Log.d("myapp", "2");

            }
        });
    }

    private void speak(String s) {
        Log.d("myapp", "3");
        myTTS.speak(s, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onInit(int initStatus) {
        Log.d("myapp", "4");
        if (initStatus == TextToSpeech.SUCCESS) {
            myTTS.setLanguage(Locale.getDefault());
        } else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("myapp", "5");
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

}
