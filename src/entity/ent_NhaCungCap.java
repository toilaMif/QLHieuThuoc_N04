package entity;

import java.util.Objects;

public class ent_NhaCungCap {
    private String maNCC;
    private String tenNCC;
    private String diaChi;
    private String sDT;

    public ent_NhaCungCap() {
        super();
    }

    public ent_NhaCungCap(String maNCC, String tenNCC, String diaChi, String sDT) {
        super();
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.sDT = sDT;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    @Override
    public String toString() {
        return "ent_NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", sDT=" + sDT + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNCC);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ent_NhaCungCap other = (ent_NhaCungCap) obj;
        return Objects.equals(maNCC, other.maNCC);
    }
}
