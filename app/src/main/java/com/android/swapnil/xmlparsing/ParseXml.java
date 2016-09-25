package com.android.swapnil.xmlparsing;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by swapnil on 25/09/2016.
 */
public class ParseXml {

    static final String KEY_SET = "set"; // parent node
    static final String KEY_QUESTION = "question";
    static final String KEY_ANSWER_SET = "answer_set";
    static final String KEY_OPTION = "option";

    public Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }
        // return DOM
        return doc;
    }

    public String getValue(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }

    public NodeList getValueOfNested(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return n;
    }

    public final String getElementValue( Node elem ) {
        Node child;
        if( elem != null){
            if (elem.hasChildNodes()){
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    public ArrayList<XmlPojo> getParsedXml(NodeList nl){

        ArrayList<XmlPojo> mapItems = new ArrayList<XmlPojo>();

        for(int i = 0; i < nl.getLength(); i++){
            XmlPojo xmlPojo = new XmlPojo();

            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            xmlPojo.setQuestion(getValue(e, KEY_QUESTION));

            ArrayList<String> optionList = new ArrayList<>();
            NodeList answerSetNode = getValueOfNested(e,KEY_OPTION);
            for(int j = 0; j < answerSetNode.getLength(); j++){
                Element element = (Element) answerSetNode.item(j);
                optionList.add(getValue(e, KEY_OPTION));
            }
            xmlPojo.setEmojiNames(optionList);

            // adding HashList to ArrayList
            mapItems.add(xmlPojo);
        }

        return mapItems;
    }
}
