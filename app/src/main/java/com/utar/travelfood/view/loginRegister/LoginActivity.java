package com.utar.travelfood.view.loginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.utar.travelfood.R;
import com.utar.travelfood.view.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    Button registerButton;
    ProgressBar progressBar;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Auto login
//        sharedPreferences = this.getSharedPreferences("my.edu.utar.travelfood", Context.MODE_PRIVATE);
//        if (sharedPreferences.getBoolean("loginState", false)) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }

        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar2);

        // Add listeners
        emailEditText.addTextChangedListener(createTextWatcher(emailEditText));
        passwordEditText.addTextChangedListener(createTextWatcher(passwordEditText));

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onLoginClicked(View view) {
        String username = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty()) { // check if username is empty
            emailEditText.setError("Email must not be empty!");
        } else if (password.isEmpty()) { // check if password is empty
            passwordEditText.setError("Password must not be empty!");
        } else { // check if username and password exists in firebase
            mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) { // login successful
                                performLogin();
                            } else { // login failed
                                Toast.makeText(LoginActivity.this, "Login failed. Try again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    // Listener
    private TextWatcher createTextWatcher(EditText editText) {
        return new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editText.setError(null);
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
    }

    public void performLogin() {
        //sharedPreferences.edit().putBoolean("loginState", true).apply();

        emailEditText.setEnabled(false);
        passwordEditText.setEnabled(false);
        loginButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        // Show progress bar for 2 seconds, then go to main activity
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }, 2000);
    }
}
