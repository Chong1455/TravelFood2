package com.utar.travelfood.view.loginRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.utar.travelfood.R;

public class RegisterActivity extends AppCompatActivity {


    EditText emailEditText;
    EditText passwordEditText;
    EditText cfmPasswordEditText;
    Button registerButton;
    Button backButton;
    ProgressBar progressBar;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        cfmPasswordEditText = findViewById(R.id.cfmPasswordEditText);
        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.backButton);
        progressBar = findViewById(R.id.progressBar2);

        // Add listeners
        emailEditText.addTextChangedListener(createTextWatcher(emailEditText));
        passwordEditText.addTextChangedListener(createTextWatcher(passwordEditText));
        cfmPasswordEditText.addTextChangedListener(createTextWatcher(cfmPasswordEditText));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onRegisterClicked(View view) {
        String username = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String cfmpassword = cfmPasswordEditText.getText().toString();

        if (username.isEmpty()) { // check if username is empty
            emailEditText.setError("Email must not be empty!");
        } else if (password.isEmpty()) { // check if password is empty
            passwordEditText.setError("Password must not be empty!");
        } else if (password.length() <= 5) { // check if password is less than 6 characters
            passwordEditText.setError("Password must be at least 6 characters!");
        } else if (!cfmpassword.equals(password)) { // check if retype password same as password
            cfmPasswordEditText.setError("Password must be the same!");
        } else { // register username and password in firebase
            mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) { // login successful
                                performRegister();
                            } else { // login failed
                                Toast.makeText(RegisterActivity.this, "Register failed. Try again!", Toast.LENGTH_SHORT).show();
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

    public void performRegister() {

        emailEditText.setEnabled(false);
        passwordEditText.setEnabled(false);
        cfmPasswordEditText.setEnabled(false);
        registerButton.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        // Show progress bar for 2 seconds, then go to main activity
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }, 2000);
    }
}