package com.udacity.gradle.builditbigger;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.androidjokelibrary.JokeActivity;
import com.udacity.gradle.builditbigger.Utilities;

import java.lang.Override;
import java.lang.String;

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
    private String deviceID = AdRequest.DEVICE_ID_EMULATOR;;
    private InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
    }
    @Override
    public void validateResponse(String joke){
        if ((getActivity() == null) || (joke == null))
        {
            Log.e("Error", "Null "+joke);
            return;
        }
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
    if (!Build.HARDWARE.contains("goldfish")){
        String uniqueId = Settings.Secure.getString(getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        deviceID = Utilities.xlateToDeviceID(uniqueId);
        Log.e("MSG ","Not running emulator");
    }
    Log.e("DeviceId ", deviceID);

    super.onCreate(bundle);
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
/*
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
*/
        spinner=(ProgressBar)root.findViewById(R.id.progressBar);
        spinner.setVisibility(spinner_visibility);

        Button tellJokeBtn = (Button) root.findViewById(R.id.tellJokeButton);
        tellJokeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    getJoke();
                }
            }
        });

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.banner_ad_unit_id) /*"ca-app-pub-3940256099942544/6300978111"*/);
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        requestNewInterstitial();
                        getJoke();
                    }
                });
        requestNewInterstitial();

        return root;
    }

    //Ad related
    //"B7ACA8BFA9381BAB320C32F403523DAC"
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(deviceID /*"B7ACA8BFA9381BAB320C32F403523DAC"*//*AdRequest.DEVICE_ID_EMULATOR*/)
                .build();
        //Log.e("Error",AdRequest.DEVICE_ID_EMULATOR);
        mInterstitialAd.loadAd(adRequest);
    }
    public void getJoke(){
        spinner_visibility = View.VISIBLE;
        spinner.setVisibility(spinner_visibility);

        EndpointsAsyncTask jokeAsyncTask =  new EndpointsAsyncTask();
        jokeAsyncTask.setCallback(this);
        jokeAsyncTask.execute(new Pair<Context, String>(getActivity(), "GCEServer"));

    }

}
