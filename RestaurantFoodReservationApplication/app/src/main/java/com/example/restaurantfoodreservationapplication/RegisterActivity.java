package com.example.restaurantfoodreservationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button Btn;
    FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    TextView edtName, edtEmail, edtPassword, edtPhone, edtPosition;
    Button cirRegisterButton;
    ImageView gotoLogin1;
    TextView gotoLogin;
    EditText editTextEmail, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        changeStatusBarColor();

        // taking FirebaseAuth instance:
        mAuth = FirebaseAuth.getInstance();

        // initialising views through id defined above
        edtName = (TextView) findViewById(R.id.editTextName);
        edtEmail = (TextView) findViewById(R.id.editTextEmail);
        edtPassword = (TextView) findViewById(R.id.editTextPassword);
        edtPhone = (TextView) findViewById(R.id.editTextMobile);
        edtPosition = (TextView) findViewById(R.id.editTextPosition);

        cirRegisterButton = (Button) findViewById(R.id.cirRegisterButton);
        gotoLogin = (TextView) findViewById(R.id.gotologin);
        gotoLogin1 = (ImageView) findViewById(R.id.gotologin1);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        //firebase + set on click listener on button Register
        cirRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewEmployee();
            }
        });

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        gotoLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //firebase:
        //88


    }

    public void registerNewEmployee(){
        //ghi đỡ mail với pass trước, còn 3 cái kia từ từ ghi sau
        // show the visibility of progress bar to show loading
        // Take the value of two edit texts in Strings
        String email,password;
        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();

        // Validations for input email ad password:
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Please enter new employee's email.", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Please enter new employee's password.", Toast.LENGTH_LONG).show();
            return;
        }
        // create new employee's account:
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registration Succeeded.", Toast.LENGTH_LONG).show();
                    //if the employee's account created intent to login activity
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("User");
                    //get all the value:
                    String ename,eemail,epassword,ephone,eposition;
                    ename = edtName.getText().toString();
                    eemail = edtEmail.getText().toString();
                    epassword = edtPassword.getText().toString();
                    ephone = edtPhone.getText().toString();
                    eposition = edtPosition.getText().toString();

                    UserHelperClass helperClass = new UserHelperClass(ename, eemail, epassword, ephone, eposition);
                    reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(helperClass);
                    Intent intent = new Intent(RegisterActivity.this, DatMonActivity.class);
                    startActivity(intent);
                }
                else {
                    //Registering Failed:
                    Toast.makeText(getApplicationContext(), "Registering Failed."+"Try it later.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}