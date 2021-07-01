package com.example.appdoctruyenhay.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.util.SizeF;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appdoctruyenhay.DangnhapActivity;
import com.example.appdoctruyenhay.moldel.Taikhoan;

import java.util.ArrayList;
import java.util.List;

public class database extends SQLiteOpenHelper {
    private static String DATABASE_NAME="doctruyen";

    private static String TABLE_TAIKHOAN="taikhoan";
    private static String ID_TAI_KHOAN="idtaikhoan";
    private static String TEN_TAI_KHOAN="tentaikhoan";
    private static String MAT_KHAU="matkhau";
    private static String PHAN_QUYEN="phanquyen";
    private static String EMAIL="email";
    private static int VERSION= 1;

    private static String TABLE_TRUYEN="truyen";
    private static String ID_TRUYEN="idtruyen";
    private static String TEN_TRUYEN="tieude";
    private static String NOI_DUNG="noidung";
    private static String IMAGE="anh";
    private Context context;

    private String SQLQuery="CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+ " TEXT UNIQUE, "
            +MAT_KHAU+ " TEXT, "
            +EMAIL+ " TEXT, "
            +PHAN_QUYEN+ " INTEGER ) ";
    private String SQLQuery1="CREATE TABLE "+ TABLE_TRUYEN +" ( "+ID_TRUYEN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TRUYEN+ " TEXT UNIQUE, "
            +NOI_DUNG+ " TEXT, "
            + IMAGE + " TEXT, "+ ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN+" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+"))";
    private String SQLQuery2="INSERT INTO taikhoan VALUES( null,'admin','admin','admin@gmail.com',2)";
    private String SQLQuery3="INSERT INTO taikhoan VALUES( null,'vu','vu','vu@gmail.com',1)";
    //private String SQLQuery4=" CREATE TABLE IF NOT EXISTS TruyenHay( Id INTEGER PRIMARY KEY AUTOINCREMENT , TenTruyen VARCHAR(150), TenTacGia VARCHAR(50), TenTap VARCHAR(50), NamSX VARCHAR(50),  HinhAnh BLOB)";
    public database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    public void QueryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }
    public void INSERT_TRUYEN_HAY(String tentruyen, String tentacgia,String tentap,String namsx, byte[] hinh){
        SQLiteDatabase database= getWritableDatabase();
        ///Vì đây là nhập từ bên ngoài và lưu vào sqlite nên đặt các thuộc tính là dấu ?
        String sql= "INSERT INTO TruyenHay VALUES(null, ?, ?, ?, ?, ?)";
        ///biên dịch chuỗi lệnh của mình
        SQLiteStatement statement =database.compileStatement(sql);
        statement.clearBindings();
        ///phải giải thích đc kiểu byte[] trên câu lệnh
        statement.bindString(1,tentruyen);
        statement.bindString(2,tentacgia);
        statement.bindString(3,tentap);
        statement.bindString(4,namsx);
        statement.bindBlob(5,hinh);

        statement.executeInsert();

    }

    public  Cursor GetData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);
       // db.execSQL(SQLQuery4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //phương thức lấy tất cả tài khoản
    public  Cursor getData(){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+ TABLE_TAIKHOAN,null);
        return res;
    }
    ///phương thức thêm tài khoản vào database
    public void AddTaikhoan(Taikhoan taikhoan){
        SQLiteDatabase db=this.getWritableDatabase();
        ////thực hiện insert thông qua ContentValues
        ContentValues values=new ContentValues();
        values.put(TEN_TAI_KHOAN,taikhoan.getmTenTaiKhoan());
        values.put(MAT_KHAU,taikhoan.getmMatKhau());
        values.put(EMAIL,taikhoan.getmEmail());
        values.put(PHAN_QUYEN,taikhoan.getmPhanQuyen());
        db.insert(TABLE_TAIKHOAN,null,values);
        ///đóng lại khi không dùng
        db.close();
        Log.e("ADD Tai Khoan","TC");
    }
    public boolean updateTaikhoan(String idtaikhoan , String tentaikhoan,String matkhau  , String email, String phanQuyen) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tentaikhoan",tentaikhoan);
        contentValues.put("matkhau",matkhau);
        contentValues.put("email",email);
        contentValues.put("phanquyen",phanQuyen);
        long result =db.update( "taikhoan",contentValues,"idtaikhoan = ? ",new String[]{idtaikhoan});
        //new Integer[] {idtaikhoan
        if(result==-1)
        {return  false;}
        else { return true;}
    }

    public boolean deleteTaikhoan(String id ) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ID_TAI_KHOAN, id);

        // long result =MyDB.delete( "sanpham",String.valueOf(new String[]{"id = ? "}),new String[] {id});
        long result =db.delete( "taikhoan","idtaikhoan=?",new String[]{id}) ;
        if(result==-1) return  false ;
        else return true ;
        // DB.close();
    }

    public List<Taikhoan> getAllTaikhoan(){
        List<Taikhoan> listTaikhoan=new ArrayList<>();
        String selectQuery="SELECT * FROM "+ TABLE_TAIKHOAN;
        SQLiteDatabase db =this.getWritableDatabase();//cho phep doc và chỉnh sửa
        Cursor cursor= db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Taikhoan taikhoan=new Taikhoan();
                taikhoan.setmId(cursor.getInt(0));
                taikhoan.setmTenTaiKhoan(cursor.getString(1));
                taikhoan.setmMatKhau(cursor.getString(2));
                taikhoan.setmEmail(cursor.getString(3));
                taikhoan.setmPhanQuyen(cursor.getInt(4));
                listTaikhoan.add(taikhoan);

            }while (cursor.moveToNext());
        }
        db.close();
        return listTaikhoan;
    }

    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase db =   this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from taikhoan where tentaikhoan=? and matkhau=?", new String[]{username,password});
        if(cursor.getCount()>0) return true;
        else return false ;
    }

    public Boolean datlai(String taikhoan,String matkhau)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("matkhau",matkhau);
        long result =MyDB.update( "taikhoan",contentValues,"tentaikhoan = ? ",new String[] {taikhoan});
        if(result==-1) return  false ;
        else return true ;
    }
}
