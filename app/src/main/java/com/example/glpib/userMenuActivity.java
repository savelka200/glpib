package com.example.glpib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class userMenuActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser cUser = mAuth.getCurrentUser();
        name = findViewById(R.id.userNameText);
        name.setText(cUser.getEmail());
        String email = cUser.getEmail();
        if (email != "admin@a.ru") {

        }


    }

    public void goback(View view) {
        Intent intent = new Intent(userMenuActivity.this , ticketsListActivity.class);
        userMenuActivity.this.startActivity(intent);
    }

    public void logoff(View view) {
        try {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(userMenuActivity.this , MainActivity.class);
            userMenuActivity.this.startActivity(intent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();

        }
    }

    public void gotoCreateUser(View view) {
        Intent intent = new Intent(userMenuActivity.this , createUserActivity.class);
        userMenuActivity.this.startActivity(intent);
    }

    public void gotorate(View view) {
        Intent intent = new Intent(userMenuActivity.this , ratingActivity.class);
        userMenuActivity.this.startActivity(intent);
    }

    public void gotousers(View view) {
        Intent intent = new Intent(userMenuActivity.this , userListActivity.class);
        userMenuActivity.this.startActivity(intent);
    }
}