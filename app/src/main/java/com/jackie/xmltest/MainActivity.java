package com.jackie.xmltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.callumtaylor.asynchttp.AsyncHttpClient;
import net.callumtaylor.asynchttp.response.AsyncHttpResponseHandler;
import net.callumtaylor.asynchttp.response.StringResponseHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient("http://wthrcdn.etouch.cn/WeatherApi?citykey=101010100");
        asyncHttpClient.get(new StringResponseHandler() {
            @Override
            public void onSuccess() {
                response = getContent();
            }
        });
        findViewById(R.id.btnPull).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseXmlWithPull(response);
            }
        });
        findViewById(R.id.btnSAX).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseXmlWithSAX(response);
            }
        });
    }

    private void parseXmlWithPull(String response) {
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
                            Log.d("jackie", "date:----------------" + date);
                            Log.d("jackie", "high:----------------" + high);
                            Log.d("jackie", "low:-----------------" + low);
                            Log.d("jackie", "type:-----------------" + type);
                            Log.d("jackie", "fengxiang:-----------------" + fengxiang);
                            Log.d("jackie", "fengli:-----------------" + fengli);
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

    private void parseXmlWithSAX(String response) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
            WeatherHandler weatherHandler = new WeatherHandler();
            xmlReader.setContentHandler(weatherHandler);
            xmlReader.parse(new InputSource(new StringReader(response)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
