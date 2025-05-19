package entity;

import java.util.Objects;

public class ent_khachHang {
	private String maKh;
	private String hoTen;
	private String sDT;
	private String gioiTinh;
	

	public ent_khachHang(String maKh) {
		super();
		this.maKh = maKh;
	}


	public ent_khachHang(String maKh, String hoTen, String sDT, String gioiTinh) {
		super();
		this.maKh = maKh;
		this.hoTen = hoTen;
		this.sDT = sDT;
		this.gioiTinh = gioiTinh;
	}


	public ent_khachHang() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getMaKh() {
		return maKh;
	}


	public void setMaKh(String maKh) {
		this.maKh = maKh;
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


	public String getGioiTinh() {
		return gioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maKh);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ent_khachHang other = (ent_khachHang) obj;
		return Objects.equals(maKh, other.maKh);
	}


	@Override
	public String toString() {
		return "ent_khachHang [maKh=" + maKh + ", hoTen=" + hoTen + ", sDT=" + sDT + ", gioiTinh=" + gioiTinh + "]";
	}

}
