package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.udacity.gradle.androidjokelibrary.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.Callback{

    private ProgressBar spinner;
    /*
     * keep track of visibility for when screen rotation happens.
     * using setRetailInstance to keep the value across rotations.
     * onCreate is not called upon rotation but onCreateView is called.
     */
    int spinner_visibility = View.GONE;
    public MainActivityFragment() {
    }
    @Override
    public void validateResponse(String joke){
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        spinner_visibility = View.GONE;
        spinner.setVisibility(spinner_visibility);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle bundle){
        setRetainInstance(true);
        spinner_visibility = View.GONE;
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        spinner=(ProgressBar)root.findViewById(R.id.progressBar);
        spinner.setVisibility(spinner_visibility);
        Log.e("<<< CALLING ME >>", "OnCreateView");

/*
No ads paid version
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
*/
        Button tellJokeBtn = (Button) root.findViewById(R.id.tellJokeButton);
        tellJokeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                spinner_visibility = View.VISIBLE;
                spinner.setVisibility(spinner_visibility);
                getJoke();
            }
        });

        return root;
    }
    public void getJoke(){


//        Joker joker =  new Joker();
//        String joke = joker.getJoke();
        //spinner.setVisibilityView.VISIBLE);
        EndpointsAsyncTask jokeAsyncTask =  new EndpointsAsyncTask();
        jokeAsyncTask.setCallback(this);
        jokeAsyncTask.execute(new Pair<Context, String>(getActivity(), "To GCE Server"));
/*
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, joke);
        startActivity(intent);
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "To GCE Server"));
*/

    }

}
