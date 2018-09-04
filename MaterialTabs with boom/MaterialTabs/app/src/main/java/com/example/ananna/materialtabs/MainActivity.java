package com.example.ananna.materialtabs;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.example.ananna.materialtabs.adapter.ViewPagerAdapter;
import com.example.ananna.materialtabs.fragment.CallFragment;
import com.example.ananna.materialtabs.fragment.ChatFragment;
import com.example.ananna.materialtabs.fragment.StatusFragment;


public class MainActivity extends AppCompatActivity   {

    ImageView imageCricketer ;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageCricketer = (ImageView) findViewById(R.id.image);
//imageCricketer.setImageResource((android.support.v4.R.artfort.)getImageID());
        imageCricketer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Intent intent= new Intent(MainActivity.this,AppFirstActivity.class);
               startActivity(intent);
            }
        });

    }




}
