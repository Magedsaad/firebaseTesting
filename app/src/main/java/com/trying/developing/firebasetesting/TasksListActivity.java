package com.trying.developing.firebasetesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TasksListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TaskAdapter adapter;
    List<Tasks>tasksList;
    FirebaseDatabase FDB;
    DatabaseReference DBR;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);

        recyclerView=(RecyclerView) findViewById(R.id.testingss);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        mAuth = FirebaseAuth.getInstance();



        tasksList=new ArrayList<>();
        adapter=new TaskAdapter(tasksList);

        FDB=FirebaseDatabase.getInstance();

        GetDataFirebase();

    }


    void GetDataFirebase (){


        FirebaseUser currentUser = mAuth.getCurrentUser();
        final String currentid=currentUser.getUid();

        DBR=FDB.getReference("tasks").child(currentid);

        DBR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Tasks data=dataSnapshot.getValue(Tasks.class);

                //Toast.makeText(getApplicationContext(),tas,Toast.LENGTH_SHORT).show();
                tasksList.add(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    public  class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

        List<Tasks> data=new ArrayList<>();

        public TaskAdapter(List<Tasks> tasks){
            this.data=tasks;


        }




        @Override
        public TaskAdapter.TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.taskslistitem,parent,false);

            return new TaskViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskAdapter.TaskViewHolder holder, int position) {

            Tasks tasks=data.get(position);
            holder.taskName.setText(tasks.getmTaskname());
            //Toast.makeText(getApplication(),tasks.getmTaskname(),Toast.LENGTH_SHORT).show();
           // Toast.makeText(getApplicationContext(),holder.taskName.getText(),Toast.LENGTH_SHORT).show();


        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class TaskViewHolder extends RecyclerView.ViewHolder {

            TextView taskName;

            public TaskViewHolder(View itemView) {
                super(itemView);
                taskName=(TextView) itemView.findViewById(R.id.tasksnameId);
            }
        }
    }


}
