package com.android.swapnil.xmlparsing;

import java.util.ArrayList;

/**
 * Created by swapnil on 25/09/2016.
 */
public class XmlPojo {
    private String question;
    private ArrayList<String> emojiNames;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getEmojiNames() {
        return emojiNames;
    }

    public void setEmojiNames(ArrayList<String> emojiNames) {
        this.emojiNames = emojiNames;
    }
}
