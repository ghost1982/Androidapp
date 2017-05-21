package com.example.administrator.ccccc;

/**
 * Created by Administrator on 5/19/2017.
 */
public class sanpham {
    public Integer idsp;
    public String tensp;
    public String hinh;

    public sanpham(Integer idsp, String tensp, String hinh) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.hinh = hinh;

    }

    public String getimage_url() {
        return hinh;
    }

    public void setimage_url(String hinh) {
        this.hinh = hinh;
    }

}
