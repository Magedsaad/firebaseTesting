package com.trying.developing.firebasetesting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mUserEmail;
    private EditText mUserPassword;
    private String mEmail;
    private String mPassword;
    private DatabaseReference mUserDatabase;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mUserEmail=(EditText) findViewById(R.id.LoginUserEmailid);
        mUserPassword=(EditText) findViewById(R.id.LoginPasswordid);
        user= mAuth.getCurrentUser();

        String current_id=user.getUid();
        mUserDatabase= FirebaseDatabase.getInstance().getReference().child("users").child(current_id);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }


    public void signin(View view){

        mEmail=mUserEmail.getText().toString();
        mPassword=mUserPassword.getText().toString();


        mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information



Toast.makeText(MainActivity.this,"sss",Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }


    public void task(View view){

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String position=dataSnapshot.child("position").getValue().toString();
//                                    Toast.makeText(MainActivity.this,position,Toast.LENGTH_LONG).show();
                if(position.equals("Team Leader")) {

                    Intent intent = new Intent(MainActivity.this, CreateTaskActivity.class);

                    intent.putExtra("theposition",position);

                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void Alltasks(View view){


        Intent intent=new Intent(this,TasksListActivity.class);
        startActivity(intent);


    }

    public void createaccount(View view){

        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }
}
