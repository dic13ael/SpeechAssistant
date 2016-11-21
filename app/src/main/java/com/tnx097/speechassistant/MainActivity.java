package com.tnx097.speechassistant;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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

    public void restaurangClick(View v) {
        Intent intent = new Intent(this, RestaurantActivity.class);
        startActivity(intent);

    }

    public void ovrigtClick(View v) {
        Intent intent = new Intent(this, OvrigtActivity.class);
        startActivity(intent);
    }

    public void personClick(View v) {
        Intent intent = new Intent(this, PersonActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionbar_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);

               // Toast.makeText(this,"Settings clicked",Toast.LENGTH_LONG).show();break;
        }
        return super.onOptionsItemSelected(item);
    }
}