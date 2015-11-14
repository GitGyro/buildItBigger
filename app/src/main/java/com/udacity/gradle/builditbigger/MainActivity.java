package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.udacity.gradle.androidjokelibrary.JokeActivity;

public class MainActivity extends ActionBarActivity implements EndpointsAsyncTask.Callback{

    @Override
    public void validateResponse(String joke){
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY,joke);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    public void tellJoke(View view){

        Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();
        Joker joker =  new Joker();
        String joke = joker.getJoke();
        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();

        EndpointsAsyncTask jokeAsyncTask =  new EndpointsAsyncTask();
        jokeAsyncTask.setCallback(this);
        jokeAsyncTask.execute(new Pair<Context, String>(this, "To GCE Server"));

        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "To GCE Server"));


    }
*/

}
