package com.example.eatclean2.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eatclean2.Model.Monan;
import com.example.eatclean2.R;

import java.util.ArrayList;

public class ListviewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Monan> arrayList=new ArrayList<>();

    public ListviewAdapter(Context context, ArrayList<Monan> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final viewhodoler viewhodoler;
        if(view==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.dong_1monan,null);

            viewhodoler=new viewhodoler();
            viewhodoler.imageView=view.findViewById(R.id.image);
            viewhodoler.tenmonsn=view.findViewById(R.id.tv_ten);
            viewhodoler.mota=view.findViewById(R.id.diadiem);

            view.setTag(viewhodoler);


        }else {
            viewhodoler= (ListviewAdapter.viewhodoler) view.getTag();
        }

        Monan monan=arrayList.get(i);

        Log.d("a1", monan.getImgae());

        Bitmap bitmap= BitmapFactory.decodeFile(monan.getImgae()+"");
        viewhodoler.imageView.setImageBitmap(bitmap);

      //  viewhodoler.imageView.setImageResource(R.drawable.monan);
        viewhodoler.tenmonsn.setText(monan.getTen());
        viewhodoler.mota.setText(monan.getMota());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(context,SP_Activity.class);
               // Bundle bundle=new Bundle();
                // bundle.

                //intent.putExtra("data",arrayList.get(i));
                //context.startActivity(intent);
            }
        });


        return view;
    }
    class viewhodoler{
        ImageView imageView;
        TextView tenmonsn;
        TextView mota;

    }
}
