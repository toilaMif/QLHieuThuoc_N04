package frm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import dao.dao_taiKhoan;
import entity.ent_taiKhoan;
import frm_default.frm_default3;

public class frm_TaiKhoan extends frm_default3 implements ActionListener, MouseListener {

	private dao_taiKhoan taiKhoanDAO;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_2;
	private JLabel lblNewLabel_3;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	public frm_TaiKhoan() {
		khungAnh.setBounds(44, 337, 298, 435);
		setTitle("Tài Khoản");
		jlTieuDeTrang.setText("Tài Khoản");

		btnXoaRong.addActionListener(this);

		// Cấu hình bảng
		tieuDeCot = new String[] { "Mã tài khoản", "Tên đăng nhập", "Mật khẩu", "Chức vụ" };
		tableModel.setColumnIdentifiers(tieuDeCot);
		table.setRowHeight(30);

		taoThongTin();

		btnThem.addActionListener(e -> themTaiKhoan());
		btnXoaRong.addActionListener(this);

		// Kết nối DAO
		taiKhoanDAO = new dao_taiKhoan();
		loadData();
		table.addMouseListener(this);

	}

	private void loadData() {

		tableModel.setRowCount(0);

		ArrayList<ent_taiKhoan> danhSachTaiKhoan = taiKhoanDAO.getAllTaiKhoan();
		for (ent_taiKhoan tk : danhSachTaiKhoan) {
			tableModel.addRow(new Object[] {

					tk.getMaTK(), tk.getTenDangNhap(), tk.getMatKhau(), tk.getChucVu() });
		}
	}

	private void themTaiKhoan() {
		String maNV = textField.getText().trim();
		String tenDN = textField_1.getText().trim();
		String matKhau = textField_2.getText().trim();
		String maChucVu = textField_3.getText().trim();

		// Kiểm tra rỗng
		if (maNV.isEmpty() || tenDN.isEmpty() || matKhau.isEmpty() || maChucVu.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Kiểm tra tài khoản đã tồn tại
		if (taiKhoanDAO.getTaiKhoanByUserName(tenDN) != null) {
			JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Tạo đối tượng tài khoản
		ent_taiKhoan taiKhoan = new ent_taiKhoan(maNV, tenDN, matKhau, maChucVu);

		// Gọi DAO để thêm tài khoản vào cơ sở dữ liệu
		if (taiKhoanDAO.createTaiKhoan(taiKhoan)) {
			JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!", "Thành công",
					JOptionPane.INFORMATION_MESSAGE);
			loadData(); // Cập nhật lại bảng
		} else {
			JOptionPane.showMessageDialog(this, "Thêm tài khoản thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		new frm_TaiKhoan();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			themTaiKhoan();
		} else if (o.equals(btnSua)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Component[] components = thongtin.getComponents();
			ArrayList<String> data = new ArrayList<>();

			for (Component comp : components) {
				if (comp instanceof JTextField) {
					JTextField textField = (JTextField) comp;
					data.add(textField.getText().trim());
				}
			}
			if (data.size() == 4) {
				String maNV = data.get(0);
				String tenDN = data.get(1);
				String pass = data.get(2);
				String chucVu = data.get(3);

				// Kiểm tra xem các trường có bị trống không
				if (maNV.isEmpty() || tenDN.isEmpty() || pass.isEmpty() || chucVu.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Hộp thoại xác nhận sửa thông tin
				int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa thông tin khách hàng này?",
						"Xác nhận sửa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {
					// Cập nhật dữ liệu trên bảng
					tableModel.setValueAt(tenDN, selectedRow, 1);
					tableModel.setValueAt(pass, selectedRow, 2);
					tableModel.setValueAt(chucVu, selectedRow, 3);

					// Cập nhật vào bảng
					ent_taiKhoan tk = taiKhoanDAO.getTaiKhoanByUserName(tenDN);
					if (tk != null) {
						tk.setTenDangNhap(tenDN);
						;
						tk.setMatKhau(pass);
						tk.setChucVu(chucVu);

					}
				} else {
					System.out.println("Người dùng đã hủy thao tác sửa.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Không thể lấy đủ dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} else if (o.equals(btnLuu)) {
			System.out.println("Luu");

			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu dữ liệu?", "Xác nhận lưu",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (confirm == JOptionPane.YES_OPTION) {
				for (int i = 0; i < table.getRowCount(); i++) {
					String maNV = table.getValueAt(i, 0).toString();
					String tenDN = table.getValueAt(i, 1).toString();
					String pass = table.getValueAt(i, 2).toString();
					String chucVu = table.getValueAt(i, 3).toString();

					ent_taiKhoan kh = taiKhoanDAO.getTaiKhoanByUserName(tenDN);

					if (kh != null) {
						boolean hasChanges = false;

						if (!tenDN.equals(kh.getTenDangNhap())) {
							hasChanges = true;
						}
						if (!pass.equals(kh.getMatKhau())) {
							hasChanges = true;
						}
						if (!chucVu.equals(kh.getChucVu())) {
							hasChanges = true;
						}

						if (hasChanges) {
							kh.setTenDangNhap(tenDN);
							kh.setMatKhau(pass);
							kh.setChucVu(chucVu);

							boolean isUpdated = taiKhoanDAO.updateTaiKhoan(kh);

							if (isUpdated) {
								System.out.println("Cập nhật tài khoản với mã nhân viên: " + maNV + " thành công.");
							} else {
								System.out.println("Cập nhật tài khoản với mã nhân viên: " + maNV + " thất bại.");
							}
						}
					}
				}

				loadData();
				JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				System.out.println("Người dùng đã hủy thao tác lưu.");
			}
		} else if (o.equals(btnXoaRong)) {
			this.dispose();
			new frm_home();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if (row != -1) {
			Component[] components = thongtin.getComponents();
			int fieldIndex = 0;

			for (Component comp : components) {
				if (comp instanceof JTextField) {
					JTextField textField = (JTextField) comp;
					textField.setText(table.getValueAt(row, fieldIndex).toString());
					fieldIndex++;
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}