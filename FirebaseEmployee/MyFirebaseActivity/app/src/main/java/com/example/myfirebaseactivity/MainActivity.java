package com.example.myfirebaseactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static  String TAG;
    private Button addEmployee;
    private EditText firstName;
    private EditText lastName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        // Get an instance of the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Writing to database - key == message, value = "Hello World"
       /* Everything in these comments is from the class firebase example
       DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "string.class: " + String.class);
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });  end of class example */

        addEmployee = findViewById(R.id.add_Btn);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);

        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String first_Name = firstName.getText().toString();
                String last_Name = lastName.getText().toString();
                    if(first_Name.isEmpty() || last_Name.isEmpty())
                    {
                        Toast.makeText(MainActivity.this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                    }

                    else
                    {
                        DatabaseReference employeesRef = FirebaseDatabase.getInstance().getReference().child("employees");
                        Employee newEmployee = new Employee(first_Name,last_Name);
                        String fullName = first_Name + " " + last_Name;
                        employeesRef.child(fullName).setValue(newEmployee);
                        Toast.makeText(MainActivity.this, "Success! New Employee Added", Toast.LENGTH_SHORT).show();
                    }
            }
        });






        DatabaseReference employeesRef = FirebaseDatabase.getInstance().getReference().child("employees");


        employeesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Convert the data snapshot to a list of employees
                List<Employee> employees = new ArrayList<>();
                for (DataSnapshot employeeSnapshot : dataSnapshot.getChildren()) {
                    Employee employee = employeeSnapshot.getValue(Employee.class);
                    employees.add(employee);
                }


                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                EmployeeAdapter adapter = new EmployeeAdapter(employees);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });









    }
}