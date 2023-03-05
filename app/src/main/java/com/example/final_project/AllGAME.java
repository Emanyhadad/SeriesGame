package com.example.final_project;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.final_project.databinding.ActivityDataBaseBinding;

import java.util.ArrayList;

public class AllGAME extends AppCompatActivity {
    ActivityDataBaseBinding binding;

    MyDatabase myDB;
    ArrayList <ModelClass> list;
    RecyclerViewAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDataBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //مؤشر على قاعدة البيانات
        myDB=new MyDatabase(AllGAME.this);
        storeDateInArrays();

        list = myDB.getAllGames();

        //كائن من الادابتر اله سياق ومدخلات المصفوفة
        customAdapter=new RecyclerViewAdapter(AllGAME.this,list);
        //لاعطاء الريسايكلر طول المصفوفة
        binding.recyclerView.setHasFixedSize(true);
        //مدير الادابتر من كل النواحي
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        binding.recyclerView.setAdapter(customAdapter);


    }

    void storeDateInArrays(){
        //مؤشر قراءة لحقول قاعدة البيانات
        Cursor cursor=myDB.readAllDate();
        //اذا كان فارغ
        if (cursor.getCount()==0){
            Toast.makeText(this, "NO DATA ..", Toast.LENGTH_SHORT).show();
        }else {
            //استورد جميع الألعاب
            myDB.getAllGames();
        }
    }
}