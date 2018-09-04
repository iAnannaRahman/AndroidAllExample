package com.ananna.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ananna.retrofit.adapter.DataAdapter;
import com.ananna.retrofit.model.AndroidVersion;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<AndroidVersion> versionArrayList;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadJSON();

    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.learn2crack.com").addCallAdapterFactory(Gsonn);
        RequestInterface request = retrofit.create(RequestInterface.class);
        call<JSONResponse>
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager((getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }
}
