package com.android.swapnil.mirage;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * This method will fetch the data from assets folder and
 * return the XML in string format.
 * Created by swapnil on 24/09/2016.
 */
public class Util {
    public static String getXml(Context context,String fileName) {
        String xmlString = null;
        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(fileName+".xml");
            int length = is.available();
            byte[] data = new byte[length];
            is.read(data);
            xmlString = new String(data);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return xmlString;
    }
}
