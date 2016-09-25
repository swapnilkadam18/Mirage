package com.android.swapnil.mirage;

import android.content.Context;
import android.os.AsyncTask;

import com.android.swapnil.com.android.swapnil.interfaces.PublishResult;
import com.android.swapnil.xmlparsing.ParseXml;
import com.android.swapnil.xmlparsing.XmlPojo;
import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * String : data to be parsed(params)
 * Integer : data to be shown as progress
 * Void : result to be shared
 * Created by swapnil on 24/09/2016.
 */
public class LoadXml extends AsyncTask<String,Integer,ArrayList<XmlPojo>> {

    private Context ctx;
    private ArrayList<XmlPojo> parsedXml;
    private ParseXml parseXml;
    PublishResult publishResult;

    public LoadXml(Context context){
        ctx = context;
        publishResult = (PublishResult) ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        parseXml = new ParseXml();
    }

    @Override
    protected ArrayList<XmlPojo> doInBackground(String... strings) {
        String xmlData = Util.getXml(ctx,"question_set");
        Document doc = parseXml.getDomElement(xmlData);
        NodeList nl = doc.getElementsByTagName("set");
        parsedXml = parseXml.getParsedXml(nl);
        return parsedXml;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<XmlPojo> aVoid) {
        super.onPostExecute(aVoid);
        publishResult.showXml(aVoid);
    }
}
