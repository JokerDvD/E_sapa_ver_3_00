package com.example.admin.e_sapa_ver_3_00.RecourseFile.Parsers;


import com.example.admin.e_sapa_ver_3_00.RecourseFile.dbObject.dbdataObject;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.resourceFile;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by admin on 01.09.2015.
 */
public class SaxParser extends DefaultHandler {


    public ArrayList resultList;
    private dbdataObject object;

    StringBuilder builder;

    @Override

    public void startDocument() throws SAXException {
        object = new dbdataObject();
        resultList = new ArrayList();
    }

    @Override

    public void startElement(String uri, String localName, String qName,

                             Attributes attributes) throws SAXException {
    }

    @Override

    public void endElement(String uri, String localName, String qName)

            throws SAXException {
        if (localName.equals("BatchStatus")) {
            resourceFile.BatchStatus = builder.toString();
        } else if (localName.equals("IsValid")) {
            resourceFile.IsValid = Boolean.parseBoolean(builder.toString());
        } else if (localName.equals("StartDate")) {
            resourceFile.StartDate = builder.toString();
        } else if (localName.equals("UnitType")) {
            resourceFile.UnitType = builder.toString();
        } else if (localName.equals("BrandDescription")) {
            resourceFile.BrandDescription = builder.toString();
        } else if (localName.equals("BrandCode")) {
            resourceFile.BrandCode = builder.toString();
        } else if (localName.equals("Market")) {
            resourceFile.Market = builder.toString();
        } else if (localName.equals("CodentifyValidity")) {
            resourceFile.CodentifyValidity = builder.toString();
        } else if (localName.equals("NbHistoryRecords")) {
            resourceFile.NbHistoryRecords = builder.toString();
        }
    }

    @Override

    public void characters(char[] ch, int start, int length)

            throws SAXException {
        String tempString = new String(ch, start, length);
        builder = new StringBuilder();
        builder.append(tempString);
    }

}