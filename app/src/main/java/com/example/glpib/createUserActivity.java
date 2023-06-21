package com.example.glpib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createUserActivity extends AppCompatActivity {
    private EditText email, password;
    private Spinner spinner;
    private DatabaseReference myBase;

    private String USER_KEY = "USER";
    private FirebaseAuth mAuth, mAuth2;

    FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
            .setDatabaseUrl("https://console.firebase.google.com/project/glpib-585cb")
            .setApiKey("AIzaSyBBAglT7kapJPLrS9QSzODn0W8Ymxsa538")
            .setApplicationId("glpib-585cb").build();
    String[] rights = { "user", "technician"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Spinner spinner = findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, rights);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);
    }

    public void goback(View view) {
        Intent intent = new Intent(createUserActivity.this , userMenuActivity.class);
        createUserActivity.this.startActivity(intent);
    }

    public void createUser(View view) {
        try {
            FirebaseApp myApp = FirebaseApp.initializeApp(getApplicationContext(), firebaseOptions, "AnyAppName");
            mAuth2 = FirebaseAuth.getInstance(myApp);
            spinner = findViewById(R.id.spinner);
            email = findViewById(R.id.ldaplogin);
            password = findViewById(R.id.ldappassword);
            mAuth2.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString());
            mAuth2.signOut();
            String tabemail = email.getText().toString();
            String tabspin = spinner.getSelectedItem().toString();
            user nuser = new user(tabemail, tabspin);
            myBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
            myBase.child(tabemail).setValue(nuser);

        }
        catch (Exception e){
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show();
        }

    }
}