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
import android.widget.TextView;

import java.io.IOException;

import io.development.millionlady.airapp_wear.XMLStructure.Channel;

public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Channel data;

    private TextView day;
    private TextView val;
    private TextView location;
    private TextView time;

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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


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


        while (!b);
        if (b) {
            val.setText(Integer.toString(data.getItems().get(data.getItems().size() - 1).getAqi()));
            day.setText(data.getItems().get(data.getItems().size() - 1).getDay());
            location.setText(data.getTitle());
            time.setText(data.getItems().get(data.getItems().size() - 1).getTime());
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
