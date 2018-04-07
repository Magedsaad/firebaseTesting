//package com.trying.developing.firebasetesting;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//public class AllTasksActivity extends AppCompatActivity {
//
//
//    private FirebaseAuth mAuth;
//    private DatabaseReference mTasksDatabase;
//   // private RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_all_tasks);
//        mAuth = FirebaseAuth.getInstance();
//
////        recyclerView=(RecyclerView) findViewById(R.id.tasksList);
////        recyclerView.setHasFixedSize(true);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        final String currentid=currentUser.getUid();
//        mTasksDatabase= FirebaseDatabase.getInstance().getReference().child("tasks").child(currentid);
//
//        Toast.makeText(this,currentid,Toast.LENGTH_SHORT).show();
//
//
////        FirebaseRecyclerAdapter<Tasks,TasksViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Tasks, TasksViewHolder>(
////                Tasks.class,
////                R.layout.taskslistitem,
////                TasksViewHolder.class,
////                mTasksDatabase
////
////
////        ) {
////            @Override
////            protected void populateViewHolder(final TasksViewHolder viewHolder, Tasks model, int position) {
////
////
////
////                mTasksDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(DataSnapshot dataSnapshot) {
////                            String taskname =dataSnapshot.child("mTaskname").getValue().toString();
////                            Log.e("tasksss", taskname);
////                            Toast.makeText(getApplicationContext(), taskname, Toast.LENGTH_SHORT).show();
////                            viewHolder.setTaskname(taskname);
////                        }
////
////
////                    @Override
////                    public void onCancelled(DatabaseError databaseError) {
////
////                    }
////                });
////            }
////        };
////
////        recyclerView.setAdapter(firebaseRecyclerAdapter);
////    }
// @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//
//    }
//
//    public static class TasksViewHolder extends RecyclerView.ViewHolder{
//
//
//        View mView;
//
//        public TasksViewHolder(View itemView) {
//            super(itemView);
//            mView=itemView;
//        }
//
//        public void setTaskname(String s) {
//            TextView taskName=(TextView) mView.findViewById(R.id.tasknameid);
//            taskName.setText(s);
//        }
//    }
//
//}
