package com.tnx097.speechassistant;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MessageActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private String words;
    private TextToSpeech myTTS;
    private boolean isSpeaking = false;
    private Vibrator vib;
    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

      /*//button animation
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(250); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate

        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in*/

        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, 0);

        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              if (timer != null) { timer.cancel();}

                if (!isSpeaking){

                isSpeaking = true;
                EditText enteredText = (EditText) findViewById(R.id.meddelandeText);
                words = enteredText.getText().toString();
               /* int sec = 1 + words.length() / 6;

                // Repeat animation for some time
                int sec = 1 + words.length() / 6;
                animation.setRepeatCount(sec);
                button.startAnimation(animation);*/

                button.setText("Stoppa");
                button.setBackgroundColor(getResources().getColor(R.color.red));
                vib.vibrate(50);
                speak(words);

               timer = new CountDownTimer(5000, 50) {

                    @Override
                    public void onTick(long arg0) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onFinish() {
                        button.setBackgroundColor(getResources().getColor(R.color.buttonGreen));
                        button.setText("Spela upp");
                        vib.vibrate(50);
                        isSpeaking = false;
                    }
                }.start();

            }

            else {

                    speak(" ");
                    button.setBackgroundColor(getResources().getColor(R.color.buttonGreen));
                    button.setText("Spela upp");
                    vib.vibrate(50);
                    isSpeaking = false;
                }
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


    @Override
    public void onBackPressed() {
        myTTS.stop();
        super.onBackPressed();
    }

    public void onInit(int initStatus) {
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
            } else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }


    public void listen() {
        Intent intent = new Intent(this, ListenActivity3.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_start, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myTTS.shutdown();
       if(timer != null){ timer.cancel();}
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionbar_start:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                break;
            case android.R.id.home:
                myTTS.stop();


        }
        return super.onOptionsItemSelected(item);
    }
}

