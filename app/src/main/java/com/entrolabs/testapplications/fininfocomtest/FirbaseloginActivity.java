package com.entrolabs.testapplications.fininfocomtest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.entrolabs.testapplications.databinding.ActivityFirbaseloginBinding;
import com.google.firebase.auth.FirebaseAuth;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirbaseloginActivity extends AppCompatActivity {
    ActivityFirbaseloginBinding binding;
    String email, password;
    private FirebaseAuth mAuth;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirbaseloginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(FirbaseloginActivity.this);
        addInitialData();
        initViews();

    }


    private void initViews() {
        //  mAuth = FirebaseAuth.getInstance();
       /* binding.txtNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirbaseloginActivity.this, FirebasesignupActivity.class));
                finish();
            }
        });*/
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputs();
            }
        });
/*
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = binding.edtUserName.getText().toString().trim();
                password = binding.edtPassword.getText().toString().trim();
                if (email.isEmpty()) {
                    binding.edtUserName.setError("Can't be empty");
                    binding.edtUserName.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    binding.edtPassword.setError("Can't be empty");
                    binding.edtPassword.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.edtUserName.setError("Enter valid email");
                    binding.edtUserName.requestFocus();
                }
                if (password.length() < 6) {
                    binding.edtPassword.setError("Enter minimum length is 6");
                    binding.edtPassword.requestFocus();
                }
                binding.progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(FirbaseloginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                binding.progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(FirbaseloginActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                    //  startActivity(new Intent(FirbaseloginActivity.this, PushnotificationActivity.class));
                                    //  startActivity(new Intent(FirbaseloginActivity.this, Main2Activity.class));
                                    startActivity(new Intent(FirbaseloginActivity.this, DataActivity.class));
                                    finish();
                                }
                                if (!task.isSuccessful()) {
                                    binding.progressBar.setVisibility(View.GONE);
                                    Toast.makeText(FirbaseloginActivity.this, "Aunthentication failed" + task.getException(), Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

            }
        });
*/
    }

    private void validateInputs() {
        email = binding.edtUserName.getText().toString().trim();
        password = binding.edtPassword.getText().toString().trim();

        if (!isValidUsername(email)) {
            binding.edtUserName.setError("Username must be at least 10 characters long");
            return;
        }

        if (!isValidPassword(password)) {
            binding.edtPassword.setError("Password must be at least 7 characters long, with one uppercase letter, one special character, and one numeric digit");
            return;
        }
     /*   binding.progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(FirbaseloginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        binding.progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(FirbaseloginActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                            //  startActivity(new Intent(FirbaseloginActivity.this, PushnotificationActivity.class));
                            //  startActivity(new Intent(FirbaseloginActivity.this, Main2Activity.class));
                            startActivity(new Intent(FirbaseloginActivity.this, DataActivity.class));
                            finish();
                        }
                        if (!task.isSuccessful()) {
                            binding.progressBar.setVisibility(View.GONE);
                            Toast.makeText(FirbaseloginActivity.this, "Aunthentication failed" + task.getException(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

*/
        // If all validations pass
        Intent intent = new Intent(FirbaseloginActivity.this, DataActivity.class);
        startActivity(intent);
        Toast.makeText(FirbaseloginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();


    }

    private boolean isValidUsername(String username) {
        return !TextUtils.isEmpty(username) && username.length() >= 10;
    }

    private boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password) || password.length() < 7) {
            return false;
        }
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[@#$%^&+=])(?=.*[0-9]).{7,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


    private void addInitialData() {
        if (databaseHelper.getAllData().isEmpty()) {
            databaseHelper.addDummyData(new DummyData("Alice", "Description", 30, "New York"));
            databaseHelper.addDummyData(new DummyData("Bob", "Description", 25, "Los Angeles"));
            databaseHelper.addDummyData(new DummyData("Charlie", "Description", 35, "Chicago"));
            databaseHelper.addDummyData(new DummyData("Dave", "Description", 28, "Houston"));
        }
    }

}