package entity;

import java.time.LocalDate;
import java.util.Objects;

public class ent_thuoc {
    private String maThuoc;
    private String tenThuoc;
    private String tenDonVi; 
    private String tenDanhMuc; 
    private String tenNCC; 
    private LocalDate hanSuDung; 
    private Double giaNhap; 
    private Double giaBan;
    private String xuatXu;

    public String getXuatXu() {
		return xuatXu;
	}

	public void setXuatXu(String xuatXu) {
		this.xuatXu = xuatXu;
	}

	// Constructor mặc định
    public ent_thuoc() {
        super();
    }

    // Constructor đầy đủ tham số
   

    // Getter và Setter cho maThuoc
    public String getMaThuoc() {
        return maThuoc;
    }

    public ent_thuoc(String maThuoc, String tenThuoc, String tenDonVi, String tenDanhMuc, String tenNCC,
			LocalDate hanSuDung, Double giaNhap, Double giaBan, String xuatXu) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.tenDonVi = tenDonVi;
		this.tenDanhMuc = tenDanhMuc;
		this.tenNCC = tenNCC;
		this.hanSuDung = hanSuDung;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.xuatXu = xuatXu;
	}

	public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    // Getter và Setter cho tenThuoc
    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    // Getter và Setter cho tenDonVi
    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    // Getter và Setter cho tenDanhMuc
    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    // Getter và Setter cho tenNCC
    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    // Getter và Setter cho hanSuDung
    public LocalDate getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(LocalDate hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    // Getter và Setter cho giaNhap
    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }

    // Getter và Setter cho giaBan
    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    @Override
	public String toString() {
		return "ent_thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", tenDonVi=" + tenDonVi + ", tenDanhMuc="
				+ tenDanhMuc + ", tenNCC=" + tenNCC + ", hanSuDung=" + hanSuDung + ", giaNhap=" + giaNhap + ", giaBan="
				+ giaBan + ", xuatXu=" + xuatXu + "]";
	}

    // Phương thức hashCode
    @Override
    public int hashCode() {
        return Objects.hash(maThuoc);
    }

    // Phương thức equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ent_thuoc other = (ent_thuoc) obj;
        return Objects.equals(maThuoc, other.maThuoc);
    }
}