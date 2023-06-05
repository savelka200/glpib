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

public class ticketsListActivity extends AppCompatActivity {
    private ListView listView;
    private DatabaseReference myBase;
    private ArrayAdapter adapter;
    private List<String> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_list);
        init();
        getdata();
    }
    private void init(){
        listView = findViewById(R.id.ticketslist);
        listData = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        myBase = FirebaseDatabase.getInstance().getReference();
    }
    private void getdata(){
        ValueEventListener eventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listData.size() > 0) listData.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    task ticket = ds.getValue(task.class);
                    listData.add(ticket.Status + " " + ticket.Name);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myBase.addValueEventListener(eventListener);
    }

    public void newticket(View view) {
        Intent intent = new Intent(ticketsListActivity.this , ticketcreateActivity.class);
        ticketsListActivity.this.startActivity(intent);

    }
}