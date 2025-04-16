package entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ent_PhieuDoi {
    private String maPhieuDoi;
    private LocalDate ngayLap;
    private String maNV;
    private String maKH;
    private Double tongTien;

    public ent_PhieuDoi() {
    }

    public ent_PhieuDoi(String maPhieuDoi, LocalDate ngayLap, String maNV, String maKH, Double tongTien) {
        this.maPhieuDoi = maPhieuDoi;
        this.ngayLap = ngayLap;
        this.maNV = maNV;
        this.maKH = maKH;
        this.tongTien = tongTien;
    }

    public String getMaPhieuDoi() {
        return maPhieuDoi;
    }

    public void setMaPhieuDoi(String maPhieuDoi) {
        this.maPhieuDoi = maPhieuDoi;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "ent_PhieuDoi{" +
                "maPhieuDoi='" + maPhieuDoi + '\'' +
                ", ngayLap=" + ngayLap +
                ", maNV='" + maNV + '\'' +
                ", maKH='" + maKH + '\'' +
                ", tongTien=" + tongTien +
                '}';
    }
}
