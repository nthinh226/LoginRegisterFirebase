package com.nhom1.loginregisterfirebase.models;

public class Post {
    private int sothunhat;
    private int sothuhai;

    public Post(int sothunhat, int sothuhai) {
        this.sothunhat = sothunhat;
        this.sothuhai = sothuhai;
    }

    public int getSothunhat() {
        return sothunhat;
    }

    public void setSothunhat(int sothunhat) {
        this.sothunhat = sothunhat;
    }

    public int getSothuhai() {
        return sothuhai;
    }

    public void setSothuhai(int sothuhai) {
        this.sothuhai = sothuhai;
    }
}
