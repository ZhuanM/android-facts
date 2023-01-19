package com.example.facts_android_f95565;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText username, password;
    Button register, login;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        db = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(SignUpActivity.this, "Please enter your username!", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(SignUpActivity.this, "Please enter your password!", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(user) && TextUtils.isEmpty(pass)) {
                    Toast.makeText(SignUpActivity.this, "Please enter the fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Successful params
                    Boolean userExists = db.checkUsernameExists(user);
                    if (userExists == false) {
                        Boolean successfulRegister = db.registerUser(user, pass);
                        if (successfulRegister) {
                            Toast.makeText(SignUpActivity.this, "Account created!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}