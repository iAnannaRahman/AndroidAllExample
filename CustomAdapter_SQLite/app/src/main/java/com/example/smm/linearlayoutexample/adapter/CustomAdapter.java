package com.example.smm.linearlayoutexample.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smm.linearlayoutexample.R;
import com.example.smm.linearlayoutexample.model.Student;

import java.util.ArrayList;

/**
 * Created by SMM on 12-Oct-17.
 */

public class CustomAdapter extends BaseAdapter {

    public Context c;
    ArrayList<Student> arrayListStd;

    public CustomAdapter(Context c, ArrayList<Student> arrayListStd){
        this.c = c;
        this.arrayListStd = arrayListStd;
    }

    @Override
    public int getCount() {
        return arrayListStd.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View custom_view = inflater.inflate(R.layout.custom_list_item, null);

        TextView tVName = custom_view.findViewById(R.id.textViewName);
        TextView tVCGPA = custom_view.findViewById(R.id.textViewCGPA);
        ImageView iVDial = custom_view.findViewById(R.id.imageViewCall);
        tVName.setText("Name: "+arrayListStd.get(position).getUsername());
        tVCGPA.setText("CGPA: "+arrayListStd.get(position).getCgpa());

        iVDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(c);
                dialog.setTitle("Call").setMessage("Do you want to call this student?").setCancelable(false);
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String phoneNo = arrayListStd.get(position).getPhoneNo();
                        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                        dialIntent.setData(Uri.parse("tel:"+phoneNo));
                        c.startActivity(dialIntent);
                    }
                });

                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                    }
                });
                dialog.show();

            }
        });
        return custom_view;
    }
}
