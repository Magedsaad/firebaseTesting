package com.trying.developing.firebasetesting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mUserEmail, mPassword;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mUserEmail = (EditText) findViewById(R.id.createUserEmailid);
        mPassword = (EditText) findViewById(R.id.createPasswordid);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        FirebaseUser user = mAuth.getInstance().getCurrentUser();
//        String mail=user.getEmail();

    }

    public void signup(View view) {

        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        final String email = mUserEmail.getText().toString();
        String password = mPassword.getText().toString();
        final String position = radioButton.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();

                            writeNewUser(user.getUid(), position, email);

                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.

                            Log.e("red",task.getResult().toString());
                            Toast.makeText(RegisterActivity.this, task.getResult()+"Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void writeNewUser(String userId, String position, String email) {
        Users user = new Users(position, email);

        mDatabase.child("users").child(userId).setValue(user);
    }
}
