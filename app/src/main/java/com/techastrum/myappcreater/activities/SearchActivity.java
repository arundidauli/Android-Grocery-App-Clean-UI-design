package com.techastrum.myappcreater.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.techastrum.myappcreater.Interface.StudentCreateListener;
import com.techastrum.myappcreater.R;
import com.techastrum.myappcreater.adapter.StudentListRecyclerViewAdapter;
import com.techastrum.myappcreater.fragmentdialog.StudentCreateDialogFragment;
import com.techastrum.myappcreater.model.Student;
import com.techastrum.myappcreater.server.Config;
import com.techastrum.myappcreater.utils.DatabaseQueryClass;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private List<Student> studentList = new ArrayList<>();
    private TextView studentListEmptyTextView;
    private RecyclerView recyclerView;
    private StudentListRecyclerViewAdapter studentListRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText search=findViewById(R.id.et_search);
        ImageView clear_text=findViewById(R.id.clear_text);

        clear_text.setOnClickListener(v ->search.setText(""));
        Logger.addLogAdapter(new AndroidLogAdapter());

        recyclerView = (RecyclerView) findViewById(R.id.rv_service);


        studentListRecyclerViewAdapter = new StudentListRecyclerViewAdapter(this, studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(studentListRecyclerViewAdapter);



    }










}

