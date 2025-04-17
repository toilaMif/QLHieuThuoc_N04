package entity;

import java.time.LocalDate;

public class ent_HoaDon {
    private String maHoaDon;
    private LocalDate ngayLap;
    private String maNV;
    private String nguoiLap;
    private String khachHang;
    private String sdtKhachHang;
    private double thue;
    private String hinhThucThanhToan;
    private double tongTien;

    // Constructor đầy đủ
    public ent_HoaDon(String maHoaDon, LocalDate ngayLap, String maNV, String nguoiLap,
                      String khachHang, String sdtKhachHang, double thue,
                      String hinhThucThanhToan, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.maNV = maNV;
        this.nguoiLap = nguoiLap;
        this.khachHang = khachHang;
        this.sdtKhachHang = sdtKhachHang;
        this.thue = thue;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.tongTien = tongTien;
    }
    public ent_HoaDon(String maHoaDon, LocalDate ngayLap, String nguoiLap,
            String khachHang, double thue,
            String hinhThucThanhToan, double tongTien) {
this.maHoaDon = maHoaDon;
this.ngayLap = ngayLap;
this.maNV = maNV;
this.nguoiLap = nguoiLap;
this.khachHang = khachHang;
this.sdtKhachHang = sdtKhachHang;
this.thue = thue;
this.hinhThucThanhToan = hinhThucThanhToan;
this.tongTien = tongTien;
}

    // Getter và Setter cho maHoaDon
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    // Getter và Setter cho ngayLap
    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    // Getter và Setter cho maNV
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    // Getter và Setter cho nguoiLap
    public String getNguoiLap() {
        return nguoiLap;
    }

    public void setNguoiLap(String nguoiLap) {
        this.nguoiLap = nguoiLap;
    }

    // Getter và Setter cho khachHang
    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    // Getter và Setter cho sdtKhachHang
    public String getSdtKhachHang() {
        return sdtKhachHang;
    }

    public void setSdtKhachHang(String sdtKhachHang) {
        this.sdtKhachHang = sdtKhachHang;
    }

    // Getter và Setter cho thue
    public double getThue() {
        return thue;
    }

    public void setThue(double thue) {
        this.thue = thue;
    }

    // Getter và Setter cho hinhThucThanhToan
    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    // Getter và Setter cho tongTien
    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "Hóa đơn [" +
                "Mã: " + maHoaDon +
                ", Ngày lập: " + ngayLap +
                ", Mã NV: " + maNV +
                ", Người lập: " + nguoiLap +
                ", Khách hàng: " + khachHang +
                ", SĐT KH: " + sdtKhachHang +
                ", Thuế: " + thue +
                ", Hình thức thanh toán: " + hinhThucThanhToan +
                ", Tổng tiền: " + tongTien +
                "]";
    }
}
