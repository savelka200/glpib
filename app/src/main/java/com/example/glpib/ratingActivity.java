package com.example.glpib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ratingActivity extends AppCompatActivity {
    private ListView listView;

    private DatabaseReference myBase;
    private ArrayAdapter adapter;
    private List<String> listData;
    private List<rating> listTemp;

    private String task_key = "Rating";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        init();
        getdata();
    }
    private void init(){
        listView = findViewById(R.id.ratelist);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        myBase = FirebaseDatabase.getInstance().getReference(task_key);
    }
    private void getdata(){
        ValueEventListener eventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listData.size() > 0) listData.clear();
                if(listTemp.size() > 0) listTemp.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    rating ticket = ds.getValue(rating.class);
                    listData.add("Заявка№ " +ticket.id + " Оценка:" + ticket.rate);
                    listTemp.add(ticket);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myBase.addValueEventListener(eventListener);
    }

    public void goback(View view) {
        Intent intent = new Intent(ratingActivity.this, userMenuActivity.class);
        startActivity(intent);
    }
}