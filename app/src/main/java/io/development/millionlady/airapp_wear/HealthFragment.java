package io.development.millionlady.airapp_wear;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

import io.development.millionlady.airapp_wear.XMLStructure.Channel;

public class HealthFragment extends Fragment {
    private Channel data;

    private TextView quality;
    private TextView desc;

    private Handler handler;

    private JavaGetRequest request;
    private boolean b;


    public HealthFragment() {
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

        View view =  inflater.inflate(R.layout.fragment_health, container, false);

        quality = view.findViewById(R.id.quality);
        desc = view.findViewById(R.id.desc);

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

        if (val != -1) {
            quality.setText(getResources().getStringArray(R.array.title)[val]);
            quality.setBackgroundColor(getResources().getIntArray(R.array.quality)[val]);
            desc.setText(getResources().getStringArray(R.array.desc)[val]);


        }

        return view;
    }
}
