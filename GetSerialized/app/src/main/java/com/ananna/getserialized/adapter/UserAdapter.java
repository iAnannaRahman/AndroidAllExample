package com.ananna.getserialized.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ananna.getserialized.R;
import com.ananna.getserialized.model.User;

import java.util.ArrayList;

/**
 * Created by Ananna on 10/25/2017.
 */

public class UserAdapter extends BaseAdapter {
    Context c;
    ArrayList<User> arrayList;


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View customView = inflater.inflate(R.layout.grid_view_item,null);
        TextView tvUserName = customView.findViewById(R.id.tvUserName);
        TextView tvUserPhone = customView.findViewById(R.id.tvUserPhone);
        TextView tvUserEmail = customView.findViewById(R.id.tvUserEmail);
        /*ImageView imageUser = customView.findViewById(R.id.gridImage);
        TextView textUser = customView.findViewById(R.id.textGridName);

        imageUser.setImageResource(arrayList.get(position).getImageID());
        textUser.setText(arrayList.get(position).getName());*/
        tvUserName.setText(arrayList.get(position).getUserName());
        tvUserEmail.setText(arrayList.get(position).getEmail());
        tvUserPhone.setText(arrayList.get(position).getPhone());
        return customView;
    }



    public UserAdapter(Context c, ArrayList<User> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }
    public void RemoveAll(){
        arrayList.clear();
    }
}
