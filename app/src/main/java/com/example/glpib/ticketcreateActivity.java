package com.example.glpib;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class ticketcreateActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText name, description;
    private DatabaseReference mbase;
    private String task_key = "Task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketcreate);

    }

    public void back(View view) {
        Intent intent = new Intent(ticketcreateActivity.this , ticketsListActivity.class);
        ticketcreateActivity.this.startActivity(intent);
    }
    public void init(){
        name = findViewById(R.id.ticketname);
        description = findViewById(R.id.ticketdescription);
        mbase = FirebaseDatabase.getInstance().getReference(task_key);
        mAuth = FirebaseAuth.getInstance();
    }
    public static int generateRandomInt(int upperRange){
        Random random = new Random();
        return random.nextInt(upperRange);
    }
    public void createticket(View view) {
        try{
            init();
            String nextname = name.getText().toString();
            String nextdescription = description.getText().toString();
            if (TextUtils.isEmpty(nextname) || TextUtils.isEmpty(nextdescription)){
                Toast.makeText(getApplicationContext(), "Заполните поля", Toast.LENGTH_SHORT).show();
                return;
            }
            FirebaseUser cUser = mAuth.getCurrentUser();
            Date date = new Date();
            String nextdate = date.toString();
            String nextid = Integer.toString(generateRandomInt(1000000));
            String nextstatus = "Новая";
            String creator = cUser.getEmail().toString();
            String executor = "admin@a.ru";
            task nexttask = new task(nextid,nextname,nextdescription,nextstatus,creator,executor,nextdate);
            mbase.child(nextid.toString()).setValue(nexttask);
            Toast.makeText(getApplicationContext(), "Заявка создана", Toast.LENGTH_SHORT).show();
            back(view);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show();
        }

    }
}