package com.example.glpib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ticketinfoActivity extends AppCompatActivity {
    TextView tikId, tikName, tikDesk, tikCr, tikExec, tikDate, tikStat;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myBase;

    DatabaseReference myBase2;
    FirebaseAuth mAuth;
    String taskKey = "Task";
    String curId;
    String tabspin;

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketinfo);
        init();
        getIntentMain();
    }
    public void init(){
        String[] rate = { "1", "2", "3", "4", "5"};
        spinner = findViewById(R.id.ratingspin);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, rate);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);
        mAuth = FirebaseAuth.getInstance();
        myBase = FirebaseDatabase.getInstance().getReference(taskKey);
        myBase2 = FirebaseDatabase.getInstance().getReference("Rating");
        tikId = findViewById(R.id.ticketId);
        tikName = findViewById(R.id.ticketName);
        tikDesk = findViewById(R.id.ticketDesc);
        tikCr = findViewById(R.id.ticketCreate);
        tikExec = findViewById(R.id.ticketExec);
        tikDate = findViewById(R.id.ticketDate);
        tikStat = findViewById(R.id.ticketState);
    }
    public void getIntentMain(){
        Intent intent = getIntent();
        curId = intent.getStringExtra("tikId");
        tikId.setText(intent.getStringExtra("tikId"));
        tikName.setText("Название: " + intent.getStringExtra("tikName"));
        tikDesk.setText("Описание: " + intent.getStringExtra("tikDesk"));
        tikCr.setText("Создатель: " + intent.getStringExtra("tikCr"));
        tikExec.setText("Исполнитель: " + intent.getStringExtra("tikExec"));
        tikDate.setText("Дата создания: " + intent.getStringExtra("tikDate"));
        tikStat.setText("Статус : " + intent.getStringExtra("tikStat"));
    }

    public void closetask(View view) {
        myBase.child(curId).child("Status").setValue("Закрыта");
        Intent intent = new Intent(ticketinfoActivity.this, ticketsListActivity.class);
        startActivity(intent);
    }

    public void inwork(View view) {
        myBase.child(curId).child("Status").setValue("В работе");
        Intent intent = new Intent(ticketinfoActivity.this, ticketsListActivity.class);
        startActivity(intent);
    }

    public void goback(View view) {
        Intent intent = new Intent(ticketinfoActivity.this, ticketsListActivity.class);
        startActivity(intent);
    }

    public void setRate(View view) {
        tabspin = spinner.getSelectedItem().toString();
        rating rate = new rating(curId,tabspin);

        myBase2.child(curId).setValue(rate);

    }
}