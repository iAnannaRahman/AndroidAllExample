package com.example.smm.linearlayoutexample;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.smm.linearlayoutexample.adapter.CustomAdapter;
import com.example.smm.linearlayoutexample.database.DatabaseHelper;
import com.example.smm.linearlayoutexample.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText eTUserName, eTPassword, eTCgpa, eTPhoneNo;
    ListView lVStudents;
    ArrayList<Student> arrayListStudents;
    CustomAdapter adapter;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTUserName = (EditText) findViewById(R.id.editTextUserName);
        eTPassword = (EditText) findViewById(R.id.editTextPassword);
        eTCgpa = (EditText) findViewById(R.id.editTextCGPA);
        eTPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);

        lVStudents = (ListView) findViewById(R.id.listViewStudents);
        arrayListStudents = new ArrayList<>();

        dbHelper = new DatabaseHelper(MainActivity.this);
        arrayListStudents = dbHelper.getAllStudentData();

        adapter = new CustomAdapter(MainActivity.this, arrayListStudents);
        lVStudents.setAdapter(adapter);

        lVStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Student Details");
                dialog.setMessage(arrayListStudents.get(position).toStringForDialog());
                dialog.setCancelable(false);

                dialog.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });
                dialog.show();
            }
        });

        lVStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Delete");
                dialog.setMessage("Do you want to delete this student?");
                dialog.setCancelable(false);

                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        if(dbHelper.deleteStudent(arrayListStudents.get(position).getId())){
                            Toast.makeText(MainActivity.this, "Data is deleted successfully", Toast.LENGTH_LONG).show();
                            updateAdapter();
                        }else{
                            Toast.makeText(MainActivity.this, "Data is not deleted successfully", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();

                return false;
            }
        });

    }


    public void saveData(View view) {
        Student std = new Student();

        String userName = eTUserName.getText().toString().trim();
        String phoneNo = eTPhoneNo.getText().toString().trim();
        String password = eTPassword.getText().toString();
        String cg = eTCgpa.getText().toString();

        boolean error = false;

        //CGPA Validation
        if (cg.isEmpty()) {
            eTCgpa.setError("The password is missing!");
        } else {
            Float cgpa = Float.parseFloat(cg);

            if (cgpa > 1.00 && cgpa <= 4.00) {
                std.setCgpa(cgpa);
            } else {
                error = true;
                eTCgpa.setError("Invalid input!");
            }
        }

        //Username Validation
        if (userName.isEmpty()) {
            error = true;
            eTUserName.setError("User Name is Missing!");
        } else if (userName.length() < 7) {
            error = true;
            eTUserName.setError("User Name is too short!");
        } else {
            std.setUsername(userName);
        }

        //Password Validation
        if (password.length() < 5) {
            error = true;
            eTPassword.setError("Password too short!");
        } else {
            std.setPassword(password);
        }

        //Mobile Number Validation
        if (phoneNo.isEmpty()) {
            error = true;
            eTPhoneNo.setError("Mobile no. is missing!");
        } else if (phoneNo.length() < 11) {
            error = true;
            eTPhoneNo.setError("The number is too short!");
        } else if (phoneNo.length() == 14 || phoneNo.length() == 11) {
            if (phoneNo.length() == 14) {
                if (phoneNo.startsWith("+88017") || phoneNo.startsWith("+88019") || phoneNo.startsWith("+88018") || phoneNo.startsWith("+88016") || phoneNo.startsWith("+88015")) {
                    std.setPhoneNo(phoneNo);
                } else {
                    error = true;
                    eTPhoneNo.setError("Invalid number!");
                }
            }
            if (phoneNo.length() == 11) {
                if (phoneNo.startsWith("017") || phoneNo.startsWith("019") || phoneNo.startsWith("018") || phoneNo.startsWith("016") || phoneNo.startsWith("015")) {
                    std.setPhoneNo(phoneNo);
                } else {
                    error = true;
                    eTPhoneNo.setError("Invalid number!");
                }
            }
        } else {
            error = true;
            eTPhoneNo.setError("Invalid number!");
        }

        if (error) {
            Toast.makeText(MainActivity.this, "Input all fields properly!", Toast.LENGTH_LONG).show();
        } else {
            dbHelper.insertStudent(std);
            updateAdapter();
            Toast.makeText(MainActivity.this, std.toString(), Toast.LENGTH_LONG).show();
            ClearFields();
        }

    }

    private void updateAdapter() {
        arrayListStudents.clear();
        arrayListStudents.addAll(dbHelper.getAllStudentData());
        adapter.notifyDataSetChanged();
    }

    private void ClearFields() {
        eTUserName.setText(null);
        eTPassword.setText(null);
        eTPhoneNo.setText(null);
        eTCgpa.setText(null);
    }

    public void ClearData(View view) {
        eTUserName.setText(null);
        eTPassword.setText(null);
        eTPhoneNo.setText(null);
        eTCgpa.setText(null);
    }
}
