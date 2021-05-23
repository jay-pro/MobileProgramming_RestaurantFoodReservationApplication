package com.example.restaurantfoodreservationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    TextView edtEmail, edtPassword;
    Button cirLoginButton;
    TextView register_navigate, skip;
    ImageView register_plus;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon color
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);

        // taking FirebaseAuth instance:
        mAuth = FirebaseAuth.getInstance();

        // initialising views through id defined above
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        edtEmail = findViewById(R.id.editTextEmail);
        edtPassword = findViewById(R.id.editTextPassword);
        cirLoginButton = findViewById(R.id.cirLoginButton);
        register_navigate = findViewById(R.id.register_navigate);
        register_plus = findViewById(R.id.regis1);
        skip = findViewById(R.id.skip);

        //firebase + set on click listener on button Register
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginEmployee();
            }
        });

        register_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        register_navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, DatMonActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginEmployee(){
        // show the visibility of progress bar to show loading
        // Take the value of two edit texts in Strings
        String email,password;
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        // validations for input email and password
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Please enter the employee's email.", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Please enter the employee's password.", Toast.LENGTH_LONG).show();
            return;
        }
        //login existing account
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Login Succeeded.", Toast.LENGTH_LONG).show();
                    //hide the progress bar if log in is successful
                    //intent to DatMonActivity
                    Intent intent = new Intent(LoginActivity.this, DatMonActivity.class);
                    startActivity(intent);
                }
                else{
                    //log in failed:
                    Toast.makeText(getApplicationContext(), "Login Failed.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}
