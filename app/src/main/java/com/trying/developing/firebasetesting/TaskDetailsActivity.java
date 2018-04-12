package com.trying.developing.firebasetesting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class TaskDetailsActivity extends AppCompatActivity {
    Tasks tasks ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detais);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            tasks= (Tasks) bundle.getSerializable("targetTask");
            Log.e("dsadsa",tasks.getMemberEmail()+tasks.getId());

        }
    }
}
