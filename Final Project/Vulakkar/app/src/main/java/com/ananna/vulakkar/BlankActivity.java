package com.ananna.vulakkar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BlankActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(BlankActivity.this);
        tv.setText("Blank activity");
        setContentView(tv);
    }
}

