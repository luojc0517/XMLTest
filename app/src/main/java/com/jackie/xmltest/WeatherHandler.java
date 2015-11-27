package com.jackie.xmltest;

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
        date=new StringBuilder();
        high=new StringBuilder();
        low=new StringBuilder();
        type=new StringBuilder();
        fengxiang=new StringBuilder();
        fengli=new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
