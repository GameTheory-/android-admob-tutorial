package com.gt.admobtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private AdView adView;
    // Test Ad Unit ID
    private static final String adUnitID = "ca-app-pub-3940256099942544/6300978111";
    // Test App ID
    private static final String appID = "ca-app-pub-3940256099942544~3347511713";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start our ad view method below.
        startAdView();
    }

    // This method will load our ad view.
    private void startAdView() {
        MobileAds.initialize(this, appID);
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(adUnitID);
        LinearLayout layout = findViewById(R.id.ad_view);
        AdListener listener = new AdListener() {
            @Override
            public void onAdLoaded() {
                adView.setVisibility(View.VISIBLE);
                super.onAdLoaded();
            }
        };
        adView.setAdListener(listener);
        adView.setVisibility(View.GONE);
        layout.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    // Pause ad view when leaving the activity.
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    // Resume ad view when returning to the activity.
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    // Destroy the ad view when the activity is destroyed.
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

}
