package com.android.swapnil.mirage;


import android.app.Dialog;
import android.content.Context;
import android.view.Window;

/**
 * Created by swapnil on 24/09/2016.
 */
public class CustomProgressDialog {

    private Context ctx;

    public CustomProgressDialog(Context context){
        ctx = context;
    }

    public void showDialog(){
        final Dialog dialog = new Dialog(ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_progress_dialog);
        dialog.show();
    }
}
