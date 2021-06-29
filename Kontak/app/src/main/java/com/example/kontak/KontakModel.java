package com.example.kontak;

public class KontakModel {

    String id;
    String nama;
    String notelp;

    public KontakModel(String id, String nama, String notelp) {
        this.id = id;
        this.nama = nama;
        this.notelp = notelp;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }
}
