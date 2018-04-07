package com.trying.developing.firebasetesting;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

        mUserDatabase = FirebaseDatabase.getInstance().getReference();
        mUserdDatabase = FirebaseDatabase.getInstance().getReference().child("users");

    }

    public void createTask(View view) {


        final String mMemberEmail =memberEmail.getText().toString();
        final String mTasksName =tasksname.getText().toString();
        final String mTaskDsc=taskdescription.getText().toString();
        final String mTaskDeadline=deadlinetask.getText().toString();


        final Query userQuery = mUserdDatabase.orderByChild("email").equalTo(memberEmail.getText().toString());
        Log.e("error", userQuery.toString());

        userQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (final DataSnapshot child : dataSnapshot.getChildren()) {

                    final String id = child.getKey();


                    Toast.makeText(CreateTaskActivity.this, id, Toast.LENGTH_SHORT).show();


                    mAuth = FirebaseAuth.getInstance();
                    user = mAuth.getCurrentUser();

                    final String current_id = user.getUid();




                    final Tasks tasks=new Tasks(mMemberEmail,mTasksName,mTaskDsc,mTaskDeadline);

                    mUserDatabase.child("tasks").child(current_id).child(id).push().setValue(tasks).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            mAuth = FirebaseAuth.getInstance();
                            user = mAuth.getCurrentUser();

                            String current_id = user.getUid();

                            Tasks tasks=new Tasks(mMemberEmail,mTasksName,mTaskDsc,mTaskDeadline);

                            String id = child.getKey();

                            mUserDatabase.child("tasks").child(id).child(current_id).push().setValue(tasks);

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



//        mUserDatabase.setValue(taskMap);


    }


//    public void WriteNewTask(String current_id,String member_id,String memberEmail,String taskname,String taskDsc,String taskDeadline){
//
//
//        Tasks tasks=new Tasks(memberEmail,taskname,taskDsc,taskDeadline);
//
//        mUserDatabase.child("tasks").child(current_id).child(member_id).setValue(tasks).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                MemberReciveTask("member","id","email","taskname","task","deadline");
//            }
//        });
//
//    }
//
//    public void MemberReciveTask(String member_id,String current_id,String memberEmail,String taskname,String taskDsc,String taskDeadline){
//
//        Tasks tasks=new Tasks(memberEmail,taskname,taskDsc,taskDeadline);
//
//        mUserDatabase.child("tasks").child(member_id).child(current_id).setValue(tasks);
//
//    }

}