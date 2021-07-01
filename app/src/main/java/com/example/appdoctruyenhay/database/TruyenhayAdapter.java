package com.example.appdoctruyenhay.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyenhay.R;
import com.example.appdoctruyenhay.moldel.Sachhay;

import java.util.List;

public class TruyenhayAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Sachhay> sachhayList;
    public TruyenhayAdapter(Context context, int layout, List<Sachhay> sachhayList) {
        this.context = context;
        this.layout = layout;
        this.sachhayList = sachhayList;
    }


    @Override
    public int getCount() {
        return sachhayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTentruyen,txtTentacgia,txtTentap,txtNamsx;
        ImageView imgHinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(layout,null);

            holder.txtTentruyen=(TextView)convertView.findViewById(R.id.tv_tentruyen);
            holder.txtTentacgia=(TextView)convertView.findViewById(R.id.tv_tacgia);
            holder.txtTentap=(TextView)convertView.findViewById(R.id.tv_tentap);
            holder.txtNamsx=(TextView)convertView.findViewById(R.id.tv_namsx);
            holder.imgHinh=(ImageView)convertView.findViewById(R.id.imgv_anhitem);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        Sachhay sachhay=sachhayList.get(position);

        holder.txtTentruyen.setText(sachhay.getmTentruyen());
        holder.txtTentacgia.setText(sachhay.getmTacgia());
        holder.txtTentap.setText(sachhay.getmTentap());
        holder.txtNamsx.setText(sachhay.getmNamsanxuat());
        ///chuyển mảng byte[] sang kiểu bitmap
        byte[] hinhAnh= sachhay.getHinh();
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imgHinh.setImageBitmap(bitmap);
        return convertView;
    }
}
