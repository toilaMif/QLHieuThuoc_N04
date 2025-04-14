package frm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.dao_NhaCungCap;
import entity.ent_NhaCungCap;
import frm_default.frm_default3;

public class frm_ChiTietNhaCungCap extends frm_default3 implements ActionListener, MouseListener {
	private dao_NhaCungCap nhaCungCapDAO;

	public frm_ChiTietNhaCungCap() {
		setTitle("Chi Tiết Nhà Cung Cấp");
		jlTieuDeTrang.setText("Chi Tiết Nhà Cung Cấp");

		tieuDeCot = new String[] { "Mã nhà cung cấp", "Tên Nhà cung cấp", "Địa chỉ", "Số điện thoại" };
		header.setFont(new Font("Arial", Font.BOLD, 20));
		tableModel.setColumnIdentifiers(tieuDeCot);
		tableModel.setRowCount(0);
		table.setRowHeight(30);
		table.addMouseListener(this);
		taoThongTin();

		nhaCungCapDAO = new dao_NhaCungCap();
		loadData();

		removeOldActionListenersAndAdd(btnSua);
		removeOldActionListenersAndAdd(btnThem);
		removeOldActionListenersAndAdd(btnLuu);
	}

	public frm_ChiTietNhaCungCap(String maNCC, String tenNCC, String diaChi, String sdt) {
		this(); // Gọi constructor mặc định

		JTextField maNCCField  = fieldMap.get("mãnhàcungcấp");
		JTextField tenNCCField = fieldMap.get("tênnhàcungcấp");
		JTextField diaChiField = fieldMap.get("địachỉ");
		JTextField sdtField    = fieldMap.get("sốđiệnthoại");

		if (maNCCField != null)  maNCCField.setText(maNCC);
		if (tenNCCField != null) tenNCCField.setText(tenNCC);
		if (diaChiField != null) diaChiField.setText(diaChi);
		if (sdtField != null)    sdtField.setText(sdt);

		checkAndHighlightRow();
	}

	private void removeOldActionListenersAndAdd(javax.swing.JButton button) {
		for (ActionListener al : button.getActionListeners()) {
			button.removeActionListener(al);
		}
		button.addActionListener(this);
	}

	private void loadData() {
		tableModel.setRowCount(0);
		ArrayList<ent_NhaCungCap> danhSach = nhaCungCapDAO.getAllNhaCungCap();
		for (ent_NhaCungCap ncc : danhSach) {
			tableModel.addRow(new Object[] {
				ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getsDT()
			});
		}
	}

	public void checkAndHighlightRow() {
		JTextField maNCCField  = fieldMap.get("mãnhàcungcấp");
		JTextField tenNCCField = fieldMap.get("tênnhàcungcấp");
		JTextField diaChiField = fieldMap.get("địachỉ");
		JTextField sdtField    = fieldMap.get("sốđiệnthoại");

		if (maNCCField == null || tenNCCField == null || diaChiField == null || sdtField == null) return;

		String maNCC  = maNCCField.getText().trim();
		String tenNCC = tenNCCField.getText().trim();
		String diaChi = diaChiField.getText().trim();
		String sdt    = sdtField.getText().trim();

		for (int i = 0; i < table.getRowCount(); i++) {
			boolean isMatch = true;

			String rowMa    = table.getValueAt(i, 0).toString().trim();
			String rowTen   = table.getValueAt(i, 1).toString().trim();
			String rowDia   = table.getValueAt(i, 2).toString().trim();
			String rowSDT   = table.getValueAt(i, 3).toString().trim();

			if (!maNCC.isEmpty() && !maNCC.equals(rowMa)) isMatch = false;
			if (!tenNCC.isEmpty() && !tenNCC.equals(rowTen)) isMatch = false;
			if (!diaChi.isEmpty() && !diaChi.equals(rowDia)) isMatch = false;
			if (!sdt.isEmpty() && !sdt.equals(rowSDT)) isMatch = false;

			if (isMatch) {
				table.setRowSelectionInterval(i, i);
				table.scrollRectToVisible(table.getCellRect(i, 0, true));
				return;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		if (row != -1) {
			fieldMap.get("mãnhàcungcấp").setText(table.getValueAt(row, 0).toString());
			fieldMap.get("tênnhàcungcấp").setText(table.getValueAt(row, 1).toString());
			fieldMap.get("địachỉ").setText(table.getValueAt(row, 2).toString());
			fieldMap.get("sốđiệnthoại").setText(table.getValueAt(row, 3).toString());
		}
	}
	private boolean validateFields(boolean isAdding) {
	    String maNCC = fieldMap.get("mãnhàcungcấp").getText().trim();
	    String tenNCC = fieldMap.get("tênnhàcungcấp").getText().trim();
	    String sDT = fieldMap.get("sốđiệnthoại").getText().trim();
	    String diaChi = fieldMap.get("địachỉ").getText().trim();

	    if (maNCC.isEmpty() || tenNCC.isEmpty() || sDT.isEmpty() || diaChi.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    return validateData(maNCC, tenNCC, sDT, diaChi);
	}

	private boolean validateData(String maNCC, String tenNCC, String sDT, String diaChi) {
	    if (!maNCC.matches("NCC\\d{3,4}")) {
	        JOptionPane.showMessageDialog(this, "Mã nhà cung cấp phải theo định dạng NCCxxx", "Lỗi",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    if (!tenNCC.matches("^[\\p{L} .'-]+$")) {
	        JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không hợp lệ!", "Lỗi",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    if (!sDT.matches("0\\d{9}")) {
	        JOptionPane.showMessageDialog(this, "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng 0!", "Lỗi",
	                JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    return true;
	}

	private void clearFields() {
	    for (JTextField field : fieldMap.values()) {
	        field.setText("");
	    }
	}


	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
	    super.actionPerformed(e);
	    Object o = e.getSource();

	    if (o.equals(btnThem)) {
	        if (!validateFields(true))
	            return;

	        String maNCC = fieldMap.get("mãnhàcungcấp").getText().trim();
	        String tenNCC = fieldMap.get("tênnhàcungcấp").getText().trim();
	        String sDT = fieldMap.get("sốđiệnthoại").getText().trim();
	        String diaChi = fieldMap.get("địachỉ").getText().trim();

	        for (int i = 0; i < table.getRowCount(); i++) {
	            if (table.getValueAt(i, 0).toString().equals(maNCC)) {
	                JOptionPane.showMessageDialog(this, "Mã nhà cung cấp đã tồn tại!", "Lỗi",
	                        JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	        }

	        ent_NhaCungCap newNCC = new ent_NhaCungCap(maNCC, tenNCC, sDT, diaChi);
	        nhaCungCapDAO.addNhaCungCap(newNCC);

	        loadData();
	        clearFields();
	        JOptionPane.showMessageDialog(this, "Đã thêm nhà cung cấp thành công!", "Thông báo",
	                JOptionPane.INFORMATION_MESSAGE);
	    }

	    else if (o.equals(btnSua)) {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow == -1) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp cần sửa!", "Lỗi",
	                    JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        if (!validateFields(false))
	            return;

	        String maNCC = fieldMap.get("mãnhàcungcấp").getText().trim();
	        String tenNCC = fieldMap.get("tênnhàcungcấp").getText().trim();
	        String sDT = fieldMap.get("sốđiệnthoại").getText().trim();
	        String diaChi = fieldMap.get("địachỉ").getText().trim();

	        String originalMaNCC = table.getValueAt(selectedRow, 0).toString();

	        ent_NhaCungCap ncc = nhaCungCapDAO.getNhaCungCapByMa(originalMaNCC);
	        if (ncc != null) {
	            ncc.setMaNCC(maNCC);
	            ncc.setTenNCC(tenNCC);
	            ncc.setsDT(sDT);
	            ncc.setDiaChi(diaChi);
	            nhaCungCapDAO.updateNhaCungCap(ncc);

	            loadData();
	            JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin nhà cung cấp!", "Thông báo",
	                    JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy nhà cung cấp trong CSDL!", "Lỗi",
	                    JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    else if (o.equals(btnLuu)) {
	        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu tất cả dữ liệu?",
	                "Xác nhận lưu", JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            boolean hasError = false;
	            for (int i = 0; i < table.getRowCount(); i++) {
	                String maNCC = table.getValueAt(i, 0).toString().trim();
	                String tenNCC = table.getValueAt(i, 1).toString().trim();
	                String sDT = table.getValueAt(i, 2).toString().trim();
	                String diaChi = table.getValueAt(i, 3).toString().trim();

	                if (!validateData(maNCC, tenNCC, sDT, diaChi)) {
	                    JOptionPane.showMessageDialog(this, "Dữ liệu dòng " + (i + 1) + " không hợp lệ!", "Lỗi",
	                            JOptionPane.ERROR_MESSAGE);
	                    hasError = true;
	                    break;
	                }

	                ent_NhaCungCap ncc = nhaCungCapDAO.getNhaCungCapByMa(maNCC);
	                if (ncc != null) {
	                    ncc.setTenNCC(tenNCC);
	                    ncc.setsDT(sDT);
	                    ncc.setDiaChi(diaChi);
	                    nhaCungCapDAO.updateNhaCungCap(ncc);
	                } else {
	                    nhaCungCapDAO.addNhaCungCap(new ent_NhaCungCap(maNCC, tenNCC, sDT, diaChi));
	                }
	            }

	            if (!hasError) {
	                loadData();
	                JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công!", "Thông báo",
	                        JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
	    }

	    else if (o.equals(btnXoaRong)) {
	        this.dispose();
	        new frm_NhaCungCap();
	    }
	}


	public static void main(String[] args) {
		new frm_ChiTietNhaCungCap().setVisible(true);
	}
}
