package entity;

import java.util.Objects;

public class ent_KeThuoc {
    private String maKe;
    private String tenKe;
    private int sucChuaToiDa;
    private int soLuong;

    public ent_KeThuoc() {
        super();
    }

    public ent_KeThuoc(String maKe, String tenKe, int sucChuaToiDa, int soLuong) {
        super();
        this.maKe = maKe;
        this.tenKe = tenKe;
        this.sucChuaToiDa = sucChuaToiDa;
        this.soLuong = soLuong;
    }

    public String getMaKe() {
        return maKe;
    }

    public void setMaKe(String maKe) {
        this.maKe = maKe;
    }

    public String getTenKe() {
        return tenKe;
    }

    public void setTenKe(String tenKe) {
        this.tenKe = tenKe;
    }

    public int getSucChuaToiDa() {
        return sucChuaToiDa;
    }

    public void setSucChuaToiDa(int sucChuaToiDa) {
        this.sucChuaToiDa = sucChuaToiDa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "ent_KeThuoc [maKe=" + maKe + ", tenKe=" + tenKe + ", sucChuaToiDa=" + sucChuaToiDa + ", soLuong=" + soLuong + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKe);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ent_KeThuoc other = (ent_KeThuoc) obj;
        return Objects.equals(maKe, other.maKe);
    }
}
