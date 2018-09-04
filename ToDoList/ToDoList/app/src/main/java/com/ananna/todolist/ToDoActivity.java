package com.ananna.todolist;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.ananna.todolist.adapter.TodoItemAdapter;
import com.ananna.todolist.database.DBHelper;
import com.ananna.todolist.model.ToDo;
import com.ananna.todolist.model.ToDoItems;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;


public class ToDoActivity extends AppCompatActivity {


    private EditText etToDoTitle;
    private EditText etAddToDo;
    private Button btnAddItem;
    ArrayList<ToDoItems> toDoItems = null;
    private ListView listItems;
    private FloatingActionButton btnSaveToDo;
    private DBHelper db;
    private TodoItemAdapter toDoItemAdapter;
    private ToDo aToDO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        etToDoTitle = (EditText) findViewById(R.id.etToDoTitle);
        etAddToDo = (EditText) findViewById(R.id.etAddToDo);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        listItems = (ListView) findViewById(R.id.listTodo);
        btnSaveToDo = (FloatingActionButton) findViewById(R.id.btnSaveToDo);
        db = new DBHelper(this);
        aToDO = new ToDo();
        aToDO.setTitle("");
        aToDO.setItems(new ArrayList<ToDoItems>());
        toDoItems = new ArrayList<>();
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToDoItems anItem = new ToDoItems();
                anItem.setToDo(etAddToDo.getText().toString());

                if (TextUtils.isEmpty(anItem.getToDo())) {
                    Toast.makeText(ToDoActivity.this, "Please add description", Toast.LENGTH_SHORT).show();
                    return;
                }
                toDoItems.add(anItem);
                aToDO.setItems(toDoItems);

                etAddToDo.setText("");

                toDoItemAdapter = new TodoItemAdapter(ToDoActivity.this, toDoItems);
                listItems.setAdapter(toDoItemAdapter);

            }
        });


        btnSaveToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etToDoTitle.getText().toString())) {
                    aToDO.setTitle(Calendar.getInstance().getTime().toString());
                }else{
                    aToDO.setTitle(etToDoTitle.getText().toString());
                }
               boolean result = db.addToDo(aToDO);
                if(result){
                    Toast.makeText(ToDoActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    toDoItemAdapter.clearAll();
                    toDoItemAdapter.notifyDataSetChanged();
                    aToDO = new ToDo();
                    aToDO.setItems(new ArrayList<ToDoItems>());
                    toDoItems = new ArrayList<>();
                }else{
                    Toast.makeText(ToDoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                
            }
        });


    }


}
