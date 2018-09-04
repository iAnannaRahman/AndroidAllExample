package com.example.ananna.universityinformationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ananna.universityinformationapp.model.Student;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    EditText editTPassword, editTPhone, editTCGPA, editTName;
    private Button btnSave;
    private Button btnClear;
    private ListView listStudent;

    private ArrayList<Student> studentArrayList;
    ArrayAdapter<Student> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);
        editTName = (EditText) findViewById(R.id.editTName);
        editTPassword = (EditText) findViewById(R.id.editTPassword);
        editTPhone = (EditText) findViewById(R.id.editTPhone);
        editTCGPA = (EditText) findViewById(R.id.editTCGPA);
        listStudent = (ListView) findViewById(R.id.listStudents);
        studentArrayList = new ArrayList<Student>();
        adapter = new ArrayAdapter<Student>(this,R.layout.list_item, studentArrayList);

        listStudent.setAdapter(adapter);
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(view);

            }
        });
        btnClear = (Button) findViewById(R.id.buttonClear);
        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clearData(view);
            }
        });

    }

    public void saveData(View view) {
        boolean error = false;

        Student std = new Student();

        // std.setUserName(editTName.getText().toString());
        String userName = editTName.getText().toString().trim();

        if (userName.isEmpty()) {
            editTName.setError("Insert a Valid Name");
            error = true;
        } else if (userName.length() < 4) {
            editTName.setError("Too Short");
            error = true;
        } else {
            std.setStudentName(userName);


        }

        std.setStudentDepartmentId(editTPassword.getText().toString());
        //region CGPA
      /*  Float cGPA = Float.parseFloat(editTCGPA.getText().toString().trim());
        if (cGPA <= 4 && cGPA > 0) {
            std.setUserCGPA(cGPA);

        } else {
            error = true;
            editTCGPA.setError("Invalid CGPA");
        }*/
        //endregion
        String studentPhone = editTPhone.getText().toString().trim();

        if (studentPhone.isEmpty()) {
            editTPhone.setError("Insert a Phone Number");
            error = true;
        } else if (studentPhone.length() == 11) {

            if (studentPhone.startsWith("017") || studentPhone.startsWith("019") || studentPhone.startsWith("018") || studentPhone.startsWith("016") || studentPhone.startsWith("015"))
                std.setStudentPhone(editTPhone.getText().toString());

            else {
                editTPhone.setError("Invalid Phone Number");
                error = true;
            }
        } else if (studentPhone.length() == 14) {

            if (studentPhone.startsWith("+88017") || studentPhone.startsWith("+88019") || studentPhone.startsWith("+88018") || studentPhone.startsWith("+88016") || studentPhone.startsWith("+88015"))
                std.setStudentPhone(editTPhone.getText().toString());
            else {
                editTPhone.setError("Invalid Phone Number");
                error = true;
            }
        } else {
            editTPhone.setError("Invalid Phone Number");
            error = true;
        }

        if (error) {
            Toast.makeText(this, "Wrong Information Inserted", Toast.LENGTH_LONG).show();
        } else {
            studentArrayList.add(std);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, std.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void clearData(View view) {
        editTName.setText("");
        editTPhone.setText("");
        editTCGPA.setText("");
        editTPassword.setText("");
        Toast.makeText(this, "Cleared All ", Toast.LENGTH_LONG).show();
    }

}