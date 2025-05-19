package entity;

public class ent_DanhMucThuoc {
    private String maDM;
    private String tenDM;

    public ent_DanhMucThuoc(String maDM, String tenDM) {
        this.maDM = maDM;
        this.tenDM = tenDM;
    }

    // Getters and setters
    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }
}
