package com.example.ananna.linearlayoutday2;

import android.provider.ContactsContract;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ananna.linearlayoutday2.model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTPassword, editTPhone, editTCGPA, editTName;
    private Button btnSave;
    private Button btnClear;
    private ListView listStudent;
    private ArrayList<Student> studentArrayList;
    ArrayAdapter<Student>  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTName = (EditText) findViewById(R.id.editTName);
        editTPassword = (EditText) findViewById(R.id.editTPassword);
        editTPhone = (EditText) findViewById(R.id.editTPhone);
        editTCGPA = (EditText) findViewById(R.id.editTCGPA);
        listStudent = (ListView) findViewById(R.id.listStudents);
        studentArrayList = new ArrayList<>();
        adapter = new ArrayAdapter<Student>(MainActivity.this,R.layout.list_item,studentArrayList);

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
            std.setUserName(userName);

            clearData(view);
        }

        std.setUserPassword(editTPassword.getText().toString());
        Float cGPA = Float.parseFloat(editTCGPA.getText().toString().trim());
        if (cGPA <= 4 && cGPA > 0) {
            std.setUserCGPA(cGPA);

        } else {
            error = true;
            editTCGPA.setError("Invalid CGPA");
        }
        String userPhone = editTPhone.getText().toString().trim();

        if (userPhone.isEmpty()) {
            editTPhone.setError("Insert a Phone Number");
            error = true;
        } else if (userPhone.length() == 11) {

            if (userPhone.startsWith("017") || userPhone.startsWith("019") || userPhone.startsWith("018") || userPhone.startsWith("016") || userPhone.startsWith("015"))
                std.setUserPhone(editTPhone.getText().toString());

            else {
                editTPhone.setError("Invalid Phone Number");
                error = true;
            }
        } else if (userPhone.length() == 14) {

            if (userPhone.startsWith("+88017") || userPhone.startsWith("+88019") || userPhone.startsWith("+88018") || userPhone.startsWith("+88016") || userPhone.startsWith("+88015"))
                std.setUserPhone(editTPhone.getText().toString());
            else {
                editTPhone.setError("Invalid Phone Number");
                error = true;
            }
        } else {
            editTPhone.setError("Invalid Phone Number");
            error = true;
        }
        if (error) {
            Toast.makeText(MainActivity.this, "Wrong Information Inserted", Toast.LENGTH_LONG).show();
        } else {
            adapter.add(std);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, std.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void clearData(View view) {
        editTName.setText("");
        editTPhone.setText("");
        editTCGPA.setText("");
        editTPassword.setText("");
        Toast.makeText(MainActivity.this, "Cleared All ", Toast.LENGTH_LONG).show();
    }

}
