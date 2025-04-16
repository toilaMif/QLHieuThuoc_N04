package entity;

public class ent_ChiTietHoaDon {
    private String maHoaDon;
    private String tenThuoc;
    private int soLuong;
    private String donVi;
    private double giaBan;

    public ent_ChiTietHoaDon(String maHoaDon, String tenThuoc, int soLuong, String donVi, double giaBan) {
        this.maHoaDon = maHoaDon;
        this.tenThuoc = tenThuoc;
        this.soLuong = soLuong;
        this.donVi = donVi;
        this.giaBan = giaBan;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    @Override
    public String toString() {
        return "Chi tiết hóa đơn [" +
                "Mã hóa đơn: " + maHoaDon +
                ", Tên thuốc: " + tenThuoc +
                ", Số lượng: " + soLuong +
                ", Đơn vị: " + donVi +
                ", Giá bán: " + giaBan +
                "]";
    }
}
