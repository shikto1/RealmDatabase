package com.walletmix.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEt;
    private EditText ageEt;
    private Button addStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        nameEt = findViewById(R.id.studentNameEt);
        ageEt = findViewById(R.id.studentAgeEt);

        addStudentButton = findViewById(R.id.addStudent);
        addStudentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = nameEt.getText().toString();
        int age = Integer.parseInt(ageEt.getText().toString());
        Student student = new Student("123", name, age);
        RealmService.sharedService.addStudent(student);
    }
}
