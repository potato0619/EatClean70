package com.example.eatclean2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eatclean2.DBMonan.queryData;
import com.example.eatclean2.Model.Monan;
import com.example.eatclean2.adapter.ListviewAdapter;
import com.example.eatclean2.adapter.adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ChitietThudonActivity extends AppCompatActivity {

    public static int id=0;
    static RecyclerView recyclerview;
    static ArrayList<Monan> monans;
    EditText editText;

    static adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_thudon);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",0);

        queryData queryData=new queryData(this,id);
       // queryData.xoabang();
         monans=queryData.getAlldata();

         editText=findViewById(R.id.ed_tk);

         editText.setOnKeyListener(new View.OnKeyListener() {
             @Override
             public boolean onKey(View view, int i, KeyEvent keyEvent) {
                 String key = editText.getText().toString();

                 if(i==KeyEvent.KEYCODE_ENTER&& !key.equals("")) {


                     ArrayList<Monan> monanArrayList = new ArrayList<>();
                     for (Monan a : monans) {
                         if (a.getTen().contains(key)) {
                             monanArrayList.add(a);
                         }
                     }
                     adapter=new adapter(monanArrayList,ChitietThudonActivity.this);
                     LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ChitietThudonActivity.this);
                     linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                     recyclerview.setLayoutManager(linearLayoutManager);
                     recyclerview.setAdapter(adapter);


                     Toast.makeText(ChitietThudonActivity.this, editText.getText().toString(), Toast.LENGTH_LONG).show();
                     return true;
                 }
                 if(key.equals("")){
                     adapter=new adapter(monans,ChitietThudonActivity.this);
                     LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ChitietThudonActivity.this);
                     linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                     recyclerview.setLayoutManager(linearLayoutManager);
                     recyclerview.setAdapter(adapter);
                    // return true;
                 }
                     return false;
                 }

         });

        recyclerview=findViewById(R.id.recyclerview);
        adapter=new adapter(monans,ChitietThudonActivity.this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ChitietThudonActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(adapter);




        FloatingActionButton floatingActionButton=findViewById(R.id.add);
        // button thêm món ăn
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChitietThudonActivity.this,AddActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}