package com.example.appdoctruyenhay.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appdoctruyenhay.R;
import com.example.appdoctruyenhay.moldel.Taikhoan;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Taikhoan> listTaikhoan;

    public CustomAdapter(Context context, int layout, List<Taikhoan> listTaikhoan) {
        this.context = context;
        this.layout = layout;
        this.listTaikhoan = listTaikhoan;
    }

    @Override
    public int getCount() {
        return listTaikhoan.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
           // convertView=LayoutInflater.from(context).inflate(R.layout.item_nguoidung,parent,false);
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(layout,null);
            viewHolder=new ViewHolder();
            viewHolder.tvTentaikhoan=(TextView)convertView.findViewById(R.id.tv_tentaikhoanitem);
            viewHolder.tvMatkhau=(TextView)convertView.findViewById(R.id.tv_matkhauitem);
            viewHolder.tvEmail=(TextView)convertView.findViewById(R.id.tv_emailitem);
            viewHolder.tvphanquen=(TextView)convertView.findViewById(R.id.tv_phanquyenitem);
            viewHolder.tvID=(TextView)convertView.findViewById(R.id.tv_id);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Taikhoan taikhoan=listTaikhoan.get(position);
        viewHolder.tvID.setText(taikhoan.getmId()+"");
        viewHolder.tvTentaikhoan.setText(taikhoan.getmTenTaiKhoan());
        viewHolder.tvMatkhau.setText(taikhoan.getmMatKhau());
        viewHolder.tvEmail.setText(taikhoan.getmEmail());
        viewHolder.tvphanquen.setText(taikhoan.getmPhanQuyen()+"");
        return convertView;
    }

    public class ViewHolder{//chỉ khai báo những view mà item đang sử dụng
        private TextView tvID;
        private TextView tvTentaikhoan;
        private TextView tvMatkhau;
        private TextView tvEmail;
        private TextView tvphanquen;

    }
}
