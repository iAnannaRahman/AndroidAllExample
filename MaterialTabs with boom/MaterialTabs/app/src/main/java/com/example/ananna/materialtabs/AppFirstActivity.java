package com.example.ananna.materialtabs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ananna.materialtabs.adapter.ViewPagerAdapter;
import com.example.ananna.materialtabs.fragment.CallFragment;
import com.example.ananna.materialtabs.fragment.ChatFragment;
import com.example.ananna.materialtabs.fragment.StatusFragment;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

/**
 * Created by Ananna on 10/31/2017.
 */

public class AppFirstActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BoomMenuButton bmb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage_layout);
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
      bmb =  (BoomMenuButton) findViewById(R.id.btn_Boom);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .normalImageRes(R.drawable.dolphin)
                    .normalText("Menu"+(i+1));
            bmb.addBuilder(builder);
        }

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolBar);
        setDataViewPager();
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setDataViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ChatFragment(), "Chat");
        adapter.addFragment(new CallFragment(), "Call");
        adapter.addFragment(new StatusFragment(), "Status");
        viewPager.setAdapter(adapter);
    }
}
