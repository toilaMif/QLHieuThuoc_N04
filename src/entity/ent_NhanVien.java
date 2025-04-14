package entity;

import java.time.LocalDate;
import java.util.Objects;

public class ent_NhanVien {
    private String maNV;
    private String hoTen;
    private String sDT;
    private String email;
    private LocalDate ngaySinh;
    private LocalDate ngayVaoLam;
    private String chucVu;
    private String gioiTinh;

    public ent_NhanVien() {
        super();
    }

    public ent_NhanVien(String maNV, String hoTen, String sDT, String email, LocalDate ngaySinh,
                        LocalDate ngayVaoLam, String chucVu, String gioiTinh) {
        super();
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.sDT = sDT;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.ngayVaoLam = ngayVaoLam;
        this.chucVu = chucVu;
        this.gioiTinh = gioiTinh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "ent_NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", sDT=" + sDT + ", email=" + email + ", ngaySinh="
                + ngaySinh + ", ngayVaoLam=" + ngayVaoLam + ", chucVu=" + chucVu + ", gioiTinh=" + gioiTinh + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNV);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ent_NhanVien other = (ent_NhanVien) obj;
        return Objects.equals(maNV, other.maNV);
    }
}
