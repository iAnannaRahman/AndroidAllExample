package com.example.ananna.universityinformationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ananna.universityinformationapp.R;
import com.example.ananna.universityinformationapp.model.Student;

import java.util.ArrayList;

public class DepartmentActivity extends AppCompatActivity {
    /*EditText editTPassword, editTPhone, editTCGPA, editTName;
    private Button btnSave;
    private Button btnClear;*/
    private ListView listStudent;

    private ArrayList<Student> studentArrayList;
    ArrayAdapter<Student> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.department_list_activity);

    }
}
