package com.ananna.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;

import java.util.Calendar;
import java.util.Date;

public class TaskActivity extends AppCompatActivity implements RadialTimePickerDialogFragment.OnTimeSetListener{
    private TextView tvTaskTime;
    private String FRAG_TAG_TIME_PICKER = "StartTime";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Date currentTime = Calendar.getInstance().getTime();
        tvTaskTime = (TextView) findViewById(R.id.tvTaskTime);
        tvTaskTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                        .setOnTimeSetListener(TaskActivity.this)
                        .setStartTime(10, 10)
                        .setDoneText("OK")
                        .setCancelText("Cancel")
                        .setThemeLight().setTitleText("Start Time");
                rtpd.show(getSupportFragmentManager(), FRAG_TAG_TIME_PICKER);
            }
        });
    }

    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
        Toast.makeText(this, "StartTime", Toast.LENGTH_SHORT).show();

        tvTaskTime.setText(""+hourOfDay+":"+minute);
    }
}
