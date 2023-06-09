package com.example.glpib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private List<task> listTemp;
    private String task_key = "Task";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_list);
        init();
        getdata();
        itemClick();
    }
    private void init(){
        listView = findViewById(R.id.ticketslist);
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
                    task ticket = ds.getValue(task.class);
                    listData.add(ticket.Status + " " + ticket.Name);
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

    public void newticket(View view) {
        Intent intent = new Intent(ticketsListActivity.this , ticketcreateActivity.class);
        ticketsListActivity.this.startActivity(intent);

    }

    public void gotoMenu(View view) {
        Intent intent = new Intent(ticketsListActivity.this , userMenuActivity.class);
        ticketsListActivity.this.startActivity(intent);
    }
    public void itemClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                task ticket = listTemp.get(i);
                Intent intent = new Intent(ticketsListActivity.this, ticketinfoActivity.class);
                intent.putExtra("tikId", ticket.Id);
                intent.putExtra("tikName", ticket.Name);
                intent.putExtra("tikDesk", ticket.Description);
                intent.putExtra("tikCr", ticket.Creator);
                intent.putExtra("tikExec", ticket.Executor);
                intent.putExtra("tikDate", ticket.Date);
                intent.putExtra("tikStat", ticket.Status);
                startActivity(intent);

            }
        });
    }
}