package com.example.eatclean2.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatclean2.ChitietThudonActivity;
import com.example.eatclean2.Model.Monan;
import com.example.eatclean2.R;
import com.example.eatclean2.SuaActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder>{

    ArrayList<Monan> arrayList=new ArrayList<>();
     ChitietThudonActivity context;

    public adapter(ArrayList<Monan> arrayList, ChitietThudonActivity context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.dong_1monan,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Monan monan=arrayList.get(position);
        Bitmap bitmap= BitmapFactory.decodeFile(monan.getImgae());
        Log.d("image"," dia chi:"+ monan.getImgae());
        holder.image_sp.setImageBitmap(bitmap);

        holder.tendiadiem.setText(monan.getTen());
        holder.diachi.setText(monan.getMota());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_sp, close;
        TextView tendiadiem;
        TextView diachi;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            image_sp = itemView.findViewById(R.id.image);
            tendiadiem = itemView.findViewById(R.id.tv_ten);
            diachi = itemView.findViewById(R.id.diadiem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  Toast.makeText(context,arrayList.get(getPosition()).getImgae(),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(context, SuaActivity.class);
                    intent.putExtra("monan",arrayList.get(getPosition()));
                    intent.putExtra("i",ChitietThudonActivity.id);
                    intent.putExtra("vitri",getPosition());

                    context.startActivity(intent);

                }
            });
        }
        }

    }