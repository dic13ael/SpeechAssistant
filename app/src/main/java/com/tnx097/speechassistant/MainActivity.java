package com.tnx097.speechassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ListenActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
    public void restaurangClick(View v){
        Intent intent = new Intent(this,RestaurantActivity.class);
        startActivity(intent);

    }
}