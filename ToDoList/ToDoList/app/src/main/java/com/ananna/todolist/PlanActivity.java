package com.ananna.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ananna.todolist.model.Plan;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;

import java.util.Calendar;
import java.util.Date;

public class PlanActivity extends AppCompatActivity implements CalendarDatePickerDialogFragment.OnDateSetListener{

    private TextView tvPlanStart;
    private Plan plan =new Plan();
    private String FRAG_TAG_DATE_PICKER ="Date";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        tvPlanStart = (TextView) findViewById(R.id.tvPlanStart);

        tvPlanStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(PlanActivity.this)
                        .setFirstDayOfWeek(Calendar.SUNDAY)
                        .setDoneText("Yay")
                        .setCancelText("Nop")
                        .setThemeDark();
                cdp.show(getSupportFragmentManager(), FRAG_TAG_DATE_PICKER);
            }
        });

    }


    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {


        
    }
}
