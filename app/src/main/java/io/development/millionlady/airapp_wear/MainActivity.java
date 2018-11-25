package io.development.millionlady.airapp_wear;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import io.development.millionlady.airapp_wear.XMLStructure.Channel;

public class MainActivity extends WearableActivity {

    private TextView day;
    private TextView val;
    private TextView location;
    private TextView time;
    private static HttpURLConnection con;
    private JavaGetRequest request;
    private Channel data;
    private boolean b;

    private Handler handler;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        activity = this;
        handler = new Handler();

        day = findViewById(R.id.day);
        val = findViewById(R.id.AQIValue);
        location = findViewById(R.id.location);
        time = findViewById(R.id.time);


        request = new JavaGetRequest();
        data = new Channel();

        b = false;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String content;
                request.setUrl("http://dosairnowdata.org/dos/RSS/Sarajevo/Sarajevo-PM2.5.xml");

                try {
                    while (true) {
                        content = request.post();
                        //System.out.println(content);

                        data = XMLToObject.toChannel(content);
                        //val.setText(Integer.toString(data.getItems().get(data.getItems().size() - 1).getAqi()));
                        System.out.println(Integer.toString(data.getItems().get(data.getItems().size() - 1).getAqi()));
                        b = true;

                        Thread.sleep(60000);
                    }
                } catch (IOException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (b) {
                    val.setText(Integer.toString(data.getItems().get(data.getItems().size() - 1).getAqi()));
                    day.setText(data.getItems().get(data.getItems().size() - 1).getDay());
                    location.setText(data.getTitle());
                    time.setText(data.getItems().get(data.getItems().size() - 1).getTime());
                }
            }
        }, 2000);
        // Enables Always-on
        setAmbientEnabled();
    }
}
