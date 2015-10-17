package com.udacity.gradle.androidjokelibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static String JOKE_KEY ="JOKE KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String joke = intent.getStringExtra(JOKE_KEY);

        setContentView(R.layout.activity_joke);
        TextView tv = (TextView)findViewById(R.id.joke);
        if (tv != null) {
            if (joke.length() > 0)
                tv.setText(joke);
            else tv.setText(R.string.no_joke);
        }
    }
}
