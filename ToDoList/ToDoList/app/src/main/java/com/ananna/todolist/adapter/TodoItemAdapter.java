package com.ananna.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ananna.todolist.R;
import com.ananna.todolist.model.ToDoItems;

import java.util.ArrayList;

/**
 * Created by Ananna on 10/25/2017.
 */

public class TodoItemAdapter extends BaseAdapter {
    Context c;
    ArrayList<ToDoItems> arrayList;

    public TodoItemAdapter(Context c, ArrayList<ToDoItems> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }

    public void clearAll() {
         arrayList.clear();
    }

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
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.checkbox_list_item_layout,null);
        TextView textToDoTitle = customView.findViewById(R.id.todoItem);
        CheckBox ckbItem = customView.findViewById(R.id.ckbtodoItem);

        ckbItem.setChecked(arrayList.get(position).isChecked());
        textToDoTitle.setText(arrayList.get(position).getToDo());

        return customView;
    }


}
