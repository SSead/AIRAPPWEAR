package io.development.millionlady.airapp_wear;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.wearable.activity.WearableActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import io.development.millionlady.airapp_wear.XMLStructure.Channel;

public class MainActivity extends WearableActivity {

    private static HttpURLConnection con;

    private Activity activity;
    private android.app.FragmentManager fragmentManager;
    private FrameLayout frameLayout;

    MainFragment mainFragment;
    HealthFragment healthFragment;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        activity = this;

        //day = findViewById(R.id.day);
        //val = findViewById(R.id.AQIValue);
        //location = findViewById(R.id.location);
        //time = findViewById(R.id.time);




        mainFragment = new MainFragment();
        healthFragment = new HealthFragment();

        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content, mainFragment).commit();




        frameLayout = findViewById(R.id.content);
        frameLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeBottom() {
                fragmentManager.beginTransaction().replace(R.id.content, mainFragment).commit();
            }
            public void onSwipeTop() {
                System.out.println("swipe top");
                fragmentManager.beginTransaction().replace(R.id.content, healthFragment).commit();
            }});

        // Enables Always-on
        setAmbientEnabled();
    }
}
