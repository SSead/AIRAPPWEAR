package io.development.millionlady.airapp_wear;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class JavaGetRequest {

    private HttpURLConnection con;
    private String url;

    public JavaGetRequest() {
        url = new String();
    }

    public String post() throws MalformedURLException,
            ProtocolException, IOException {

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("POST");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

            return content.toString();
        } finally {

            con.disconnect();
        }
    }

    public void setUrl(String url) {
        this.url = url;

    }
}