package com.example.ananna.materialtabs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ananna.materialtabs.R;

/**
 * Created by Ananna on 10/28/2017.
 */

public class StatusFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle)
    {
        return inflater.inflate(R.layout.status_fragment_layout,null);
    }
}
