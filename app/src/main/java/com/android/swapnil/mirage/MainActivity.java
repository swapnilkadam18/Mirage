package com.android.swapnil.mirage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.swapnil.com.android.swapnil.interfaces.PublishResult;
import com.android.swapnil.xmlparsing.ParseXml;
import com.android.swapnil.xmlparsing.XmlPojo;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PublishResult, View.OnClickListener {

    ArrayList<XmlPojo> xmlData;

    private Button next;

    private TextView question;

    private int i = 0;
    private XmlPojo xmlPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xmlData = new ArrayList<>();
        LoadXml loadXml = new LoadXml(this);
        loadXml.execute("question_set");

        question = (TextView) findViewById(R.id.question);
        next = (Button) findViewById(R.id.nextQuestion);

        next.setOnClickListener(this);
    }


    @Override
    public void showXml(ArrayList<XmlPojo> xmlData) {
        this.xmlData = xmlData;
        loadData(this.xmlData);
    }

    private void loadData(ArrayList<XmlPojo> xmlData) {
           xmlPojo = xmlData.get(i);
            loadQuestion(xmlPojo);
    }

    @Override
    public void onClick(View view) {
        if(i < xmlData.size()){

            xmlPojo = xmlData.get(i);
            loadQuestion(xmlPojo);
        }
    }

    private void loadQuestion(XmlPojo xmlPojo) {
        question.setText(xmlPojo.getQuestion());
        i++;
    }
}
