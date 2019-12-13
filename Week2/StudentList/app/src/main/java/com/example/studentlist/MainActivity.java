package com.example.studentlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView studentList= findViewById(R.id.lstStudent);

        //küçük veri kaydetme alanımıza private mod da oluşturduk
        sharedPreferences=this.getSharedPreferences("com.example.studentlist", Context.MODE_PRIVATE);

        //kaydedilmiş veriyi çağırdık yoksa ""(boş) değer getir dedik.
        String savedStudent= sharedPreferences.getString("savedStudent","");


        //Yeni liste tanımladık
        ArrayList<String> studentNames= new ArrayList<>();

        //kaydedilmiş veri var ise
        if (savedStudent!=""){
            //output String => savedStudent = "[Muhammed Hüseyin, Deniz, Aleyna, Mustafa, .... Furkan]"

            //String değeri "," e göre ağırdık
            String students[]=savedStudent.split(",");
            for (String student : students) {
                //sonda ve baştaki "]" parantezleri sildik
                if (student.startsWith("["))
                    student= student.substring(1,student.length());
                if (student.endsWith("]"))
                    student= student.substring(0,student.length()-1);

                //her elamanı listeye ekledik
                studentNames.add(student);
            }
        }
        else{
            studentNames.add("Muhammed Hüseyin");
            studentNames.add("Deniz");
            studentNames.add("Aleyna");
            studentNames.add("Mustafa");
            studentNames.add("Betül");
            studentNames.add("Beyza");
            studentNames.add("Ahmed");
            studentNames.add("Erdem");
            studentNames.add("Kutay");
            studentNames.add("Aslı");
            studentNames.add("Zeynep");
            studentNames.add("İlyas");
            studentNames.add("Onur");
            studentNames.add("Oktay");
            studentNames.add("İsmail");
            studentNames.add("Tarık");
            studentNames.add("Kadirhan");
            studentNames.add("Furkan");
            //Listeyi küçük veri kaydetme alanımıza kaydettik. "savedStudent" olarak
            sharedPreferences.edit().putString("savedStudent",studentNames.toString()).apply();
        }
        // getApplicationContext(), MainActivity.this,  this

        //ArrayAdapter oluşturup listView'a bağladık
        ArrayAdapter adapter= new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,studentNames);
        studentList.setAdapter(adapter);
    }
}
