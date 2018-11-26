package io.development.millionlady.airapp_wear;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import io.development.millionlady.airapp_wear.XMLStructure.Channel;

public class MainFragment extends Fragment {
    private Channel data;

    private TextView day;
    private TextView val;
    private TextView location;
    private TextView time;
    private ImageView imageView;

    private Handler handler;

    private JavaGetRequest request;
    private boolean b;

    public MainFragment() {
        // Required empty public constructor
        System.out.println("e");

        handler = new Handler();

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

                        System.out.println("end");
                        Thread.sleep(6000);
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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        day = view.findViewById(R.id.day);
        val = view.findViewById(R.id.AQIValue);
        location = view.findViewById(R.id.location);
        time = view.findViewById(R.id.time);
        imageView = view.findViewById(R.id.imageView);


        while (!b);

        int val = -1;
        switch (data.getItems().get(data.getItems().size() - 1).getDesc()) {
            case "Hazardous": val++;
            case "Very Unhealthy": val++;
            case "Unhealthy": val++;
            case "Unhealthy for Sensitive Groups": val++;
            case "Moderate": val++;
            case "Good": val++;
        }

        if (b) {
            this.val.setText(Integer.toString(data.getItems().get(data.getItems().size() - 1).getAqi()));
            this.day.setText(data.getItems().get(data.getItems().size() - 1).getDay());
            this.location.setText(data.getTitle());
            this.time.setText(data.getItems().get(data.getItems().size() - 1).getTime());
            this.imageView.setColorFilter(getResources().getIntArray(R.array.quality)[val]);
        }
        System.out.println("a");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                System.out.println("to smo");
            }
        }, 1);

        return view;
    }
}
