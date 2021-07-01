package com.example.appdoctruyenhay.moldel;

public class Taikhoan {
    //các biến
    private int mId;
    private String mTenTaiKhoan;
    private String mMatKhau;
    private String mEmail;
    private int mPhanQuyen;

    public Taikhoan(int mId, String mTenTaiKhoan, String mMatKhau, String mEmail, int mPhanQuyen) {
        this.mId = mId;
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mMatKhau = mMatKhau;
        this.mEmail = mEmail;
        this.mPhanQuyen = mPhanQuyen;
    }

    public Taikhoan() {
    }

    public Taikhoan(String mTenTaiKhoan, String mEmail) {
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mEmail = mEmail;
    }

    public Taikhoan(String mTenTaiKhoan, String mMatKhau, String mEmail, int mPhanQuyen) {
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mMatKhau = mMatKhau;
        this.mEmail = mEmail;
        this.mPhanQuyen = mPhanQuyen;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTenTaiKhoan() {
        return mTenTaiKhoan;
    }

    public void setmTenTaiKhoan(String mTenTaiKhoan) {
        this.mTenTaiKhoan = mTenTaiKhoan;
    }

    public String getmMatKhau() {
        return mMatKhau;
    }

    public void setmMatKhau(String mMatKhau) {
        this.mMatKhau = mMatKhau;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmPhanQuyen() {
        return mPhanQuyen;
    }

    public void setmPhanQuyen(int mPhanQuyen) {
        this.mPhanQuyen = mPhanQuyen;
    }
}
