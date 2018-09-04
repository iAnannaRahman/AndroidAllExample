package com.ananna.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.widget.ListView;
import android.widget.Toast;

import com.ananna.todolist.database.DBHelper;
import com.facebook.stetho.Stetho;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BoomMenuButton bmb;
    private DBHelper db;
    private ListView listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listItems = (ListView) findViewById(R.id.listTodo);
        db = new DBHelper(this);
        Stetho.initializeWithDefaults(this);

        bmb = (BoomMenuButton) findViewById(R.id.btn_Boom);

        HamButton.Builder builder = new HamButton.Builder()
                .normalImageRes(R.drawable.dolphin)
                .normalTextRes(R.string.ToDo)
                .subNormalTextRes(R.string.to_do_something);
        bmb.addBuilder(builder);

        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.dolphin)
                .normalTextRes(R.string.Task)
                .subNormalTextRes(R.string.to_do_something);

        bmb.addBuilder(builder);

        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.dolphin)
                .normalTextRes(R.string.event)
                .subNormalTextRes(R.string.to_do_something);
        bmb.addBuilder(builder);

        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.dolphin)
                .normalTextRes(R.string.plan)
                .subNormalTextRes(R.string.to_do_something);
        bmb.addBuilder(builder);


        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {

            bmb.setOnBoomListener(new OnBoomListener() {

                @Override
                public void onClicked(int index, BoomButton boomButton) {
                    if (index == 0) {
                        jump(ToDoActivity.class);
                    } else if (index == 1) {
                        jump(TaskActivity.class);
                    } else if (index == 2) {
                        jump(EventActivity.class);
                    } else if (index == 3) {
                        jump(PlanActivity.class);
                    }

                    Toast.makeText(MainActivity.this, Integer.valueOf(index) + " CLICKED", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onBackgroundClick() {

                }

                @Override
                public void onBoomWillHide() {

                }

                @Override
                public void onBoomDidHide() {

                }

                @Override
                public void onBoomWillShow() {

                }

                @Override
                public void onBoomDidShow() {

                }
            });

        }


    }

    public void jump(Class T) {
        Intent intent = new Intent(MainActivity.this, T);
        startActivity(intent);
    }
}
