package com.jackie.xmltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.response.AsyncHttpResponseHandler;
import net.callumtaylor.asynchttp.response.StringResponseHandler;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient("http://wthrcdn.etouch.cn/WeatherApi?citykey=101010100");
        asyncHttpClient.get(new StringResponseHandler() {
            @Override
            public void onSuccess() {
                String response = getContent();
                try {
                    XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                    XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                    xmlPullParser.setInput(new StringReader(response));
                    int eventType = xmlPullParser.getEventType();
                    String date = "";
                    String high = "";
                    String low = "";
                    String type = "";
                    String fengxiang = "";
                    String fengli = "";
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        String nodeName = xmlPullParser.getName();
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                if ("date".equals(nodeName)) {
                                    date = xmlPullParser.nextText();
                                }
                                if ("high".equals(nodeName)) {
                                    high = xmlPullParser.nextText();
                                }
                                if ("low".equals(nodeName)) {
                                    low = xmlPullParser.nextText();
                                }
                                if ("type".equals(nodeName)) {
                                    type = xmlPullParser.nextText();
                                }
                                if ("fengxiang".equals(nodeName)) {
                                    fengxiang = xmlPullParser.nextText();
                                }
                                if ("fengli".equals(nodeName)) {
                                    fengli = xmlPullParser.nextText();
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                if ("weather".equals(nodeName)) {
                                    Log.d("jackie_xmltest", "date:----------------" + date);
                                    Log.d("jackie_xmltest", "high:----------------" + high);
                                    Log.d("jackie_xmltest", "low:-----------------" + low);
                                    Log.d("jackie_xmltest", "type:-----------------" + type);
                                    Log.d("jackie_xmltest", "fengxiang:-----------------" + fengxiang);
                                    Log.d("jackie_xmltest", "fengli:-----------------" + fengli);
                                }
                                break;
                            default:
                                break;
                        }
                        eventType = xmlPullParser.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
