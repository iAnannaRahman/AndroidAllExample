package com.ananna.vulakkar;


import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
    private static int tabIndex = 0;
    private TabHost tabHost;
    private LayoutInflater inflater;
    private EditText etTabName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = getTabHost();
        inflater = getLayoutInflater();
        addTab();

        ((Button) findViewById(R.id.add_tab)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);

                View dialogView = inflater.inflate(R.layout.layout_ad_add_tab, null);
                dialogBuilder.setView(dialogView);
                etTabName = (EditText) dialogView.findViewById(R.id.etTabName);
                AlertDialog alertDialog =dialogBuilder.create();

                alertDialog.setButton("Continue.", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // here you can add functions
                        tabIndex++;
                        addTab(dialog);
                    }
                });


                alertDialog.show();

            }
        });
    }
    private void addTab() {
        LayoutInflater layoutInflate = LayoutInflater.from(MainActivity.this);
        //etTabName = (EditText) findViewById(R.id.etTabName);
        Button tabBtn = (Button) layoutInflate.inflate(R.layout.tab_event, null);
        if (etTabName != null && !TextUtils.isEmpty(etTabName.getText().toString()))
            tabBtn.setText( etTabName.getText().toString()+" " + tabIndex);
        else
            tabBtn.setText( "Tab" + tabIndex);
        Intent tabIntent = new Intent(MainActivity.this, BlankActivity.class);

        setupTab(tabBtn, tabIntent, "Tab " + tabIndex);
    }

    private void addTab(DialogInterface dialog) {
        LayoutInflater layoutInflate = LayoutInflater.from(MainActivity.this);
        //etTabName = (EditText) findViewById(R.id.etTabName);
        Button tabBtn = (Button) layoutInflate.inflate(R.layout.tab_event, null);
        tabBtn.setText( "TAB" + tabIndex);
        Intent tabIntent = new Intent(MainActivity.this, BlankActivity.class);

        setupTab(tabBtn, tabIntent, "Tab " + tabIndex);
    }

    protected void setupTab(View tabBtn, Intent setClass, String tag) {
        TabSpec setContent = tabHost.newTabSpec(tag).setIndicator(tabBtn).setContent(setClass);
        tabHost.addTab(setContent);
    }
}
