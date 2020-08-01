package com.example.eatclean2.DBMonan;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import com.example.eatclean2.Model.Monan;

import java.util.ArrayList;

public class queryData {
    DBMonan dataMonan;
    String table="";
    public queryData(Context context, int id) {
        dataMonan=new DBMonan(context,"diadiem.sqlite",null,1);
        // tao bang
        dataMonan.QueryData("CREATE TABLE IF NOT EXISTS buoisang(id INTEGER PRIMARY KEY AUTOINCREMENT, tenmonan VARCHAR(100),mota  VARCHAR(250), image  VARCHAR(200))");
        dataMonan.QueryData("CREATE TABLE IF NOT EXISTS buoitrua(id INTEGER PRIMARY KEY AUTOINCREMENT, tenmonan VARCHAR(100),mota  VARCHAR(250), image  VARCHAR(200))");
        dataMonan.QueryData("CREATE TABLE IF NOT EXISTS buoichieu(id INTEGER PRIMARY KEY AUTOINCREMENT, tenmonan VARCHAR(100),mota  VARCHAR(250), image  VARCHAR(200))");
        switch (id){
            case 1:
                table="buoisang";
                break;
            case 2:
                table="buoitrua";
                break;
            case 3:
                table="buoichieu";
                break;
        }

    }
    public ArrayList<Monan> getAlldata(){
        ArrayList<Monan> arrayList=new ArrayList<>();


        Cursor data=dataMonan.getData("Select * from "+table);
        arrayList.clear();
        while (data.moveToNext()) {

            int id1=data.getInt(0);
            String ten=data.getString(1);
            String mota=data.getString(2);
            String image=data.getString(3);

            Monan Monan=new Monan(id1,ten,mota,image);
            arrayList.add(Monan);

        }
            return arrayList;
    }
    public ArrayList<Monan> getAlldata3table() {
        ArrayList<Monan> arrayList = new ArrayList<>();
        ArrayList<Monan> a1 = new ArrayList<>();
        ArrayList<Monan> a2= new ArrayList<>();
        ArrayList<Monan> a3 = new ArrayList<>();

        Cursor data = dataMonan.getData("Select * from buoisang");
        arrayList.clear();
        while (data.moveToNext()) {
            int id1 = data.getInt(0);
            String ten = data.getString(1);
            String mota = data.getString(2);
            String image = data.getString(3);

            Monan Monan = new Monan(id1, ten, mota, image);
            a1.add(Monan);

        }
        Cursor data1 = dataMonan.getData("Select * from buoitrua");
        arrayList.clear();
        while (data1.moveToNext()) {
            int id1 = data1.getInt(0);
            String ten = data1.getString(1);
            String mota = data1.getString(2);
            String image = data1.getString(3);

            Monan Monan = new Monan(id1, ten, mota, image);
            a2.add(Monan);

        }
        Cursor data2 = dataMonan.getData("Select * from buoichieu");
        arrayList.clear();
        while (data2.moveToNext()) {
            int id1 = data2.getInt(0);
            String ten = data2.getString(1);
            String mota = data2.getString(2);
            String image = data2.getString(3);
            Monan Monan = new Monan(id1, ten, mota, image);
            a1.add(Monan);

        }
        for (Monan a: a1
             ) {
            arrayList.add(a);
        }
        for (Monan a: a2
        ) {
            arrayList.add(a);
        }

        for (Monan a: a3
        ) {
            arrayList.add(a);
        }


        return arrayList;
    }

//    public Monan get1data(int id){
//        Cursor cursor=Monan.getData("Select * from "+table+" where id="+id);
//
//        Monan monan=new Monan(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
//        return diadiem;
//    }
    public void insertinto(Monan monan){

        dataMonan.QueryData("insert into "+table+" values ("+null+",'"+monan.getTen()+"','"+monan.getMota()+"','"+monan.getImgae()+"')");
        Log.d("sql", "insert into "+table+" values ("+monan.getTen()+","+monan.getMota()+","+monan.getImgae()+")");

    }
    public void xoa(int id){


        dataMonan.QueryData("delete from '"+table+"' where id='"+id+"'");
        Log.d("sql", "delete from "+table+" where id="+id);
    }
    public void update(Monan monan){
        Log.d("sql", "update "+table+" set (tenmonan="+"'"+monan.getTen()+"',"+"mota='"+monan.getMota()+"image='"+monan.getImgae()+"') where id ='"+monan.getId()+"'");
        dataMonan.QueryData("update "+table+" set tenmonan="+"'"+monan.getTen()+"',"+"mota='"+monan.getMota()+"',image='"+monan.getImgae()+"' where id ='"+monan.getId()+"'");

    }
    public void xoabang(){
        dataMonan.QueryData("DROP TABLE buoisang");
    }



}
