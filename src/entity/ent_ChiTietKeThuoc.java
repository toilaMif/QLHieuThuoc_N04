package entity;

import java.time.LocalDate;
import java.util.Objects;

public class ent_ChiTietKeThuoc {
    private String maKe;
    private String maThuoc;
    private String tenThuoc;
    private String donVi;
    private String danhMuc;
    private LocalDate hanSuDung;
    private int soLuong;

    public ent_ChiTietKeThuoc() {
        super();
    }

    public ent_ChiTietKeThuoc(String maKe, String maThuoc, String tenThuoc, String donVi,
                               String danhMuc, LocalDate hanSuDung, int soLuong) {
        super();
        this.maKe = maKe;
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.donVi = donVi;
        this.danhMuc = danhMuc;
        this.hanSuDung = hanSuDung;
        this.soLuong = soLuong;
    }

    public String getMaKe() {
        return maKe;
    }

    public void setMaKe(String maKe) {
        this.maKe = maKe;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public LocalDate getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(LocalDate hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "ent_ChiTietKeThuoc [maKe=" + maKe + ", maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc
                + ", donVi=" + donVi + ", danhMuc=" + danhMuc + ", hanSuDung=" + hanSuDung + ", soLuong=" + soLuong + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKe, maThuoc);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ent_ChiTietKeThuoc other = (ent_ChiTietKeThuoc) obj;
        return Objects.equals(maKe, other.maKe) &&
               Objects.equals(maThuoc, other.maThuoc);
    }
}
