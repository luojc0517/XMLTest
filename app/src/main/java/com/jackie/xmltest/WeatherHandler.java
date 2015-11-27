package com.jackie.xmltest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Law on 2015/11/27.
 */
public class WeatherHandler extends DefaultHandler {
    private String nodeName;
    private StringBuilder date;
    private StringBuilder high;
    private StringBuilder low;
    private StringBuilder type;
    private StringBuilder fengxiang;
    private StringBuilder fengli;

    @Override
    public void startDocument() throws SAXException {
        date = new StringBuilder();
        high = new StringBuilder();
        low = new StringBuilder();
        type = new StringBuilder();
        fengxiang = new StringBuilder();
        fengli = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (nodeName.equals("date")) {
            date.append(ch, start, length);
        }
        if (nodeName.equals("high")) {
            high.append(ch, start, length);
        }
        if (nodeName.equals("low")) {
            low.append(ch, start, length);
        }
        if (nodeName.equals("type")) {
            type.append(ch, start, length);
        }
        if (nodeName.equals("fengxiang")) {
            fengxiang.append(ch, start, length);
        }
        if (nodeName.equals("fengli")) {
            fengli.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("weather".equals(localName)) {
            Log.d("jackie", "date:----------------" + date.toString().trim());
            Log.d("jackie", "high:----------------" + high.toString().trim());
            Log.d("jackie", "low:-----------------" + low.toString().trim());
            Log.d("jackie", "type:-----------------" + type.toString().trim());
            Log.d("jackie", "fengxiang:-----------------" + fengxiang.toString().trim());
            Log.d("jackie", "fengli:-----------------" + fengli.toString().trim());
            //最后清空StringBuilder
            date.setLength(0);
            high.setLength(0);
            low.setLength(0);
            type.setLength(0);
            fengxiang.setLength(0);
            fengli.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
