package com.example.glpib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public user nowUser;
    private FirebaseAuth mAuth;
    private EditText email, password;
    private DatabaseReference myBase;
    private String USER_KEY = "USER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mAuth = FirebaseAuth.getInstance();
    }
    public void init(){
        email = findViewById(R.id.ldaplogin);
        password = findViewById(R.id.ldappassword);
        myBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void dlpiauth(View view) {
        try {
            init();
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Вы вошли", Toast.LENGTH_SHORT).show();
                        nowUser = new user(email.getText().toString());
                        Intent intent = new Intent(MainActivity.this , ticketsListActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Введите логин и пароль", Toast.LENGTH_SHORT).show();
        }



    }

}