package com.tnx097.speechassistant;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ListenActivity4 extends AppCompatActivity {

private String message;
private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
       /* setMessage("Följ med mig så ska jag visa ert bord!");
        textview = (TextView)findViewById(R.id.text_listen);
        textview.setText("");
        displayText(textview);*/
        textview = (TextView)findViewById(R.id.text_listen);
        listen();

        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listen();
            }
        });


    }
    /*public void setMessage(String m){
        message = m;
    }

    public void displayText(TextView text) {

        StringBuilder subtext = new StringBuilder(500);
        for(int i = 0; i<message.length(); i++){
            subtext.append(message.charAt(i));
            text.setText(subtext.toString());

        }
    }
*/
    public void listen(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Säg något!");
        try {

            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {

            Toast.makeText(getApplicationContext(),
                    "Din mobil stödjer inte igenkänning av tal!",
                    Toast.LENGTH_SHORT).show();
        }
    }
public void onActivityResult(int request_code, int result_code, Intent i){

    super.onActivityResult(request_code, result_code, i);
    switch(request_code){
        case 100: if (result_code == RESULT_OK && i != null){

            ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            textview.setText(result.get(0));
        }
            break;
    }

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.actionbar_start,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionbar_start:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

}
