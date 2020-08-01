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

public class SuaActivity extends AppCompatActivity {

    Spinner spinner;
    EditText ed_tenmon,ed_mota;
    ImageView imageView;
    Button but_add,but_xoa;
    static String path="/storage/emulated/0/DCIM/Camera/rap2.jpg";

   static int id;

   static int vitri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        Intent intent=getIntent();
        //lay thong tin mon an tu ds cac mon an
        final Monan monan= (Monan) intent.getSerializableExtra("monan");
        id=intent.getIntExtra("i",0);
        vitri=intent.getIntExtra("vitri",0);



        spinner=findViewById(R.id.spinner);
        final ArrayList<String> strings=new ArrayList<>();

        if(id==1){
            strings.add("buổi sáng");
        }else if(id==2){
            strings.add("buổi trưa");
        }else if(id==3){
            strings.add("buổi tối");
        }



        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,strings);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Toast.makeText(SuaActivity.this,id+"",Toast.LENGTH_LONG).show();

        spinner.setSelection(2);

        spinner.setAdapter(arrayAdapter);


        ed_tenmon=findViewById(R.id.ed_tenMon);
        ed_mota=findViewById(R.id.ed_mota);
        imageView=findViewById(R.id.image);
        but_add=findViewById(R.id.but1);
        but_xoa=findViewById(R.id.but);
//dua ten mon an va mo ta len man hinh
        ed_mota.setText(monan.getMota());
        ed_tenmon.setText(monan.getTen());
//doc file anh doc dang bitmap dang nhi phan
        Bitmap bitmap=BitmapFactory.decodeFile(monan.getImgae());
        //day anh len man hinh
        imageView.setImageBitmap(bitmap);

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
                    AlertDialog.Builder builder=new AlertDialog.Builder(SuaActivity.this);
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
                    queryData queryData=new queryData(SuaActivity.this,id);
                    //luu mon an vao 1 model
                    Monan monan1=new Monan(monan.getId(),tenmon,mota,monan.getImgae());

                    queryData.update(monan1);

                    //load ve ds thuc don
                    ChitietThudonActivity.monans.set(vitri,monan1);
                    ChitietThudonActivity.adapter.notifyItemChanged(vitri);
                    Toast.makeText(SuaActivity.this,"thành công",Toast.LENGTH_LONG).show();
                    finish();


                }
            }
        });
        but_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(SuaActivity.this);
                builder.setTitle("thông báo");
                builder.setMessage("bạn có muốn xóa mục này");
                builder.setPositiveButton("xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        queryData queryData=new queryData(SuaActivity.this,id);
//goi cau lenh delete
                        queryData.xoa(monan.getId());

                        ChitietThudonActivity.monans.remove(vitri);
                        ChitietThudonActivity.adapter.notifyItemRemoved(vitri);
                        ChitietThudonActivity.adapter.notifyDataSetChanged();
                        Toast.makeText(SuaActivity.this,"thành công",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

                builder.show();
            }
        });

    }
    //
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
    //lay duong dan file anh
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