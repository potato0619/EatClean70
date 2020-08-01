package com.example.eatclean2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eatclean2.DBMonan.queryData;
import com.example.eatclean2.Model.Monan;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    Spinner spinner;
    EditText ed_tenmon,ed_mota;
    ImageView imageView;
    Button but_add;
    static String path="/storage/emulated/0/DCIM/Camera/rap2.jpg";

    static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        spinner=findViewById(R.id.spinner);
        final ArrayList<String> strings=new ArrayList<>();
        strings.add("buổi sáng");
        strings.add("buổi trưa");
        strings.add("buổi tối");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,strings);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        ed_tenmon=findViewById(R.id.ed_tenMon);
        ed_mota=findViewById(R.id.ed_mota);
        imageView=findViewById(R.id.image);
        but_add=findViewById(R.id.but);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long i) {

                Log.d("idd", ""+(position+1));
                id=position+1;


//                String temp=strings.get(position);
//                if(temp.equals("buổi sáng")){
//                    id=1;
//                }else if(temp.equals("buổi trưa")){
//                    id=2;
//                }else if(temp.equals("buổi tối")){
//                    id=3;
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,11);

            }
        });
        but_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ed_tenmon.getText().toString().isEmpty()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(AddActivity.this);
                    builder.setTitle("thông báo");
                    builder.setMessage("Dữ liệu liệu tên món để trống !");
                    builder.setPositiveButton("xác nhận", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }else{
                    String tenmon=ed_tenmon.getText().toString();
                    String mota=ed_mota.getText().toString();
                    Log.d("iddd", id+" ");
                    queryData queryData=new queryData(AddActivity.this,id);
                    Monan monan=new Monan(0,tenmon,mota,path);

                    queryData.insertinto(monan);
                    if(id==ChitietThudonActivity.id) {
                        ChitietThudonActivity.monans.add(monan);
                        ChitietThudonActivity.adapter.notifyItemInserted(ChitietThudonActivity.monans.size() + 1);
                    }
                    Toast.makeText(AddActivity.this,"thành công",Toast.LENGTH_LONG).show();
                    finish();


                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==11&&resultCode==RESULT_OK){
            Uri uri=data.getData();

            path=getRealPathFromURI(uri);
            Log.d("aa", path);

            try {
                InputStream fileInputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(fileInputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
}