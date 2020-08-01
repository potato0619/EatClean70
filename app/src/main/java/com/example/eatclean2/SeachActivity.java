package com.example.eatclean2;
//thu vien duoc su dung
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eatclean2.DBMonan.queryData;
import com.example.eatclean2.Model.Monan;
import com.example.eatclean2.adapter.adapter;
import com.example.eatclean2.adapter.adapter2;

import java.util.ArrayList;

public class SeachActivity extends AppCompatActivity {
    public static int id=0;
    static RecyclerView recyclerview;
    static ArrayList<Monan> monans;
    EditText editText;

    static com.example.eatclean2.adapter.adapter2 adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
//goi lan dau tien

            queryData queryData=new queryData(this,id);
            //khoi tao query trorng modeldb
            // queryData.xoabang();
            monans=queryData.getAlldata3table();//lay ds mon an

            editText=findViewById(R.id.ed_tk);

            editText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    String key = editText.getText().toString();
//khoi chay khi nhan enter
                    if(i==KeyEvent.KEYCODE_ENTER&& !key.equals("")) {

                        ArrayList<Monan> monanArrayList = new ArrayList<>();
                        //cau lenh tim kiem mon an
                        for (Monan a : monans) {
                            if (a.getMota().contains(key) || a.getTen().contains(key)) {
                                monanArrayList.add(a);
                            }
                        }
                        //dua du lieu mon an len man hinh
                       adapter2=new adapter2(monanArrayList,SeachActivity.this);
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SeachActivity.this);
                        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                        recyclerview.setLayoutManager(linearLayoutManager);
                        recyclerview.setAdapter(adapter2);


                        Toast.makeText(SeachActivity.this, editText.getText().toString(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                    //neu key rong ko hien thi gi
                    if(key.equals("")){
                        //adapter=new adapter(monans,SeachActivity.this);
                        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SeachActivity.this);
                        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                        recyclerview.setLayoutManager(linearLayoutManager);
                        recyclerview.setAdapter(adapter2);
                        // return true;
                    }
                    return false;
                }

            });
        //hien thi du lieu cho man hinh khoi tao
            recyclerview=findViewById(R.id.recyclerview);
           //truyen du lieu vao
            adapter2=new adapter2(new ArrayList<Monan>(),SeachActivity.this);
            //khoi  tao ko co mon an nao trong ds new ArrayList<Monan>()
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(SeachActivity.this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerview.setLayoutManager(linearLayoutManager);
            recyclerview.setAdapter(adapter2);
    }
}