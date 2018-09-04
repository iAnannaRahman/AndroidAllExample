package com.ananna.vulakkar.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ananna.vulakkar.R;

public class StatusFragment extends Fragment {

    private TextView tvTCounter;

    private CountDownTimer countDownTimer;
    private long timeleft;
    private boolean isPaused = false;
    private TextView tvSetNumber;
    private int number;
    private long wTimer;
    private int setsNumber;
    private boolean isFromworkout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View view = inflater.inflate(R.layout.first_fragment_layout, null);

        return view;

    }
}