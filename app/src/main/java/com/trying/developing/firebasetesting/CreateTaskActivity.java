package com.trying.developing.firebasetesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText memberEmail;
    private EditText tasksname;
    private EditText deadlinetask;
    private EditText taskdescription;
    private DatabaseReference mUserDatabase;
    private DatabaseReference mUserdDatabase;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        memberEmail = (EditText) findViewById(R.id.memberEmailid);
        tasksname = (EditText) findViewById(R.id.tasknameid);
        deadlinetask = (EditText) findViewById(R.id.taskdeadline);
        taskdescription = (EditText) findViewById(R.id.taskDescription);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        String current_id = user.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("tasks").child(current_id);

        mUserdDatabase = FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void createTask(View view) {
        final HashMap<String, String> taskMap = new HashMap<>();
        // String ss=memberEmail.getText().toString();
        taskMap.put("email", memberEmail.getText().toString());
        taskMap.put("daskdeadline", deadlinetask.getText().toString());
        taskMap.put("taskdescription", taskdescription.getText().toString());
        taskMap.put("taskname", tasksname.getText().toString());


        final Query userQuery = mUserdDatabase.orderByChild("email").equalTo(memberEmail.getText().toString());
        Log.e("error", userQuery.toString());

        userQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    String id = child.getKey();

                    Toast.makeText(CreateTaskActivity.this, id, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mUserDatabase.setValue(taskMap);


    }

}