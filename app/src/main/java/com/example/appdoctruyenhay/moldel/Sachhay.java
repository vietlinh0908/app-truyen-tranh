package com.example.appdoctruyenhay.moldel;

public class Sachhay {


    private int mID;
    private String mTentruyen;
    private String mTacgia;
    private String mTentap;
    private String mNamsanxuat;
    private byte[] hinh;

    public Sachhay(int mID, String mTentruyen, String mTacgia, String mTentap, String mNamsanxuat, byte[] hinh) {
        this.mID = mID;
        this.mTentruyen = mTentruyen;
        this.mTacgia = mTacgia;
        this.mTentap = mTentap;
        this.mNamsanxuat = mNamsanxuat;
        this.hinh = hinh;
    }

    public Sachhay(String mTentruyen, String mTacgia, String mTentap, String mNamsanxuat, byte[] hinh) {
        this.mTentruyen = mTentruyen;
        this.mTacgia = mTacgia;
        this.mTentap = mTentap;
        this.mNamsanxuat = mNamsanxuat;
        this.hinh = hinh;
    }

    public Sachhay() {
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmTentruyen() {
        return mTentruyen;
    }

    public void setmTentruyen(String mTentruyen) {
        this.mTentruyen = mTentruyen;
    }

    public String getmTacgia() {
        return mTacgia;
    }

    public void setmTacgia(String mTacgia) {
        this.mTacgia = mTacgia;
    }

    public String getmTentap() {
        return mTentap;
    }

    public void setmTentap(String mTentap) {
        this.mTentap = mTentap;
    }

    public String getmNamsanxuat() {
        return mNamsanxuat;
    }

    public void setmNamsanxuat(String mNamsanxuat) {
        this.mNamsanxuat = mNamsanxuat;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
