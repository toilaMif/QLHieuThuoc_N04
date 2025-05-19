package frm;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.dao_NhanVien;
import entity.ent_NhanVien;
import frm_default.frm_default3;
import hieuUng.ImageResizer;

public class frm_ChiTietNhanVien extends frm_default3 implements ActionListener, MouseListener {
	private dao_NhanVien nhanVienDAO;

	// Regular expressions for validation
	private static final String MA_NHAN_VIEN_REGEX = "^NV\\d{3,4}$";
	private static final String HO_TEN_REGEX = "^([A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴĐÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸ][a-zàáạảãâầấậẩẫăằắặẳẵđèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹ]*(\\s[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴĐÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸ][a-zàáạảãâầấậẩẫăằắặẳẵđèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹ]*)*)+$";
	private static final String SO_DIEN_THOAI_REGEX = "^(0)(\\d{9})$";
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	private static final String NGAY_REGEX = "^\\d{4}-\\d{2}-\\d{2}$"; // Format: YYYY-MM-DD
	private static final String GIOI_TINH_REGEX = "^(Nam|Nữ)$";

	public frm_ChiTietNhanVien() {
		setTitle("Chi Tiết Nhân Viên");
		jlTieuDeTrang.setText("Chi Tiết Nhân Viên");
		tieuDeCot = new String[] { "Mã nhân viên", "Họ tên", "Số điện thoại", "Email", "Ngày sinh", "Ngày vào làm",
				"Chức Vụ", "Giới Tính" };
		header.setFont(new Font("Arial", Font.BOLD, 20));

		tableModel.setColumnIdentifiers(tieuDeCot);
		tableModel.setRowCount(0);
		table.setRowHeight(30);
		table.addMouseListener(this);
		taoThongTin();

		nhanVienDAO = new dao_NhanVien();
		loadData();

		removeOldActionListenersAndAdd(btnSua);
		removeOldActionListenersAndAdd(btnThem);
		removeOldActionListenersAndAdd(btnThemAnh);
		removeOldActionListenersAndAdd(btnLuu);
	}

	public frm_ChiTietNhanVien(String maNV, String hoTen, String soDT, String email, String ngaySinh, String ngayVaoLam,
			String chucVu, String gioiTinh) {
		this(); // Gọi constructor mặc định để thiết lập giao diện và load dữ liệu

		JTextField maNVField      = fieldMap.get("mãnhânviên");
		JTextField hoTenField     = fieldMap.get("họtên");
		JTextField sDTField       = fieldMap.get("sốđiệnthoại");
		JTextField emailField     = fieldMap.get("email");
		JTextField ngaySinhField  = fieldMap.get("ngàysinh");
		JTextField ngayVaoField   = fieldMap.get("ngàyvàolàm");
		JTextField chucVuField    = fieldMap.get("chứcvụ");
		JTextField gioiTinhField  = fieldMap.get("giớitính");


		if (maNVField != null)
			maNVField.setText(maNV);
		if (hoTenField != null)
			hoTenField.setText(hoTen);
		if (sDTField != null)
			sDTField.setText(soDT);
		if (emailField != null)
			emailField.setText(email);
		if (ngaySinhField != null)
			ngaySinhField.setText(ngaySinh);
		if (ngayVaoField != null)
			ngayVaoField.setText(ngayVaoLam);
		if (chucVuField != null)
			chucVuField.setText(chucVu);
		if (gioiTinhField != null)
			gioiTinhField.setText(gioiTinh);

		checkAndHighlightRow();
	}

	public void checkAndHighlightRow() {
		JTextField maNVField      = fieldMap.get("mãnhânviên");
		JTextField hoTenField     = fieldMap.get("họtên");
		JTextField sDTField       = fieldMap.get("sốđiệnthoại");
		JTextField emailField     = fieldMap.get("email");
		JTextField ngaySinhField  = fieldMap.get("ngàysinh");
		JTextField ngayVaoField   = fieldMap.get("ngàyvàolàm");
		JTextField chucVuField    = fieldMap.get("chứcvụ");
		JTextField gioiTinhField  = fieldMap.get("giớitính");

	    if (maNVField == null || hoTenField == null || sDTField == null || emailField == null
	            || ngaySinhField == null || ngayVaoField == null || chucVuField == null || gioiTinhField == null) {
	        return;
	    }

	    String maNV     = maNVField.getText().trim();
	    String hoTen    = hoTenField.getText().trim();
	    String soDT     = sDTField.getText().trim();
	    String email    = emailField.getText().trim();
	    String ngaySinh = ngaySinhField.getText().trim();
	    String ngayVao  = ngayVaoField.getText().trim();
	    String chucVu   = chucVuField.getText().trim();
	    String gioiTinh = gioiTinhField.getText().trim();

	    for (int i = 0; i < table.getRowCount(); i++) {
	        boolean isMatch = true;

	        String[] rowValues = new String[8];
	        for (int j = 0; j < 8; j++) {
	            rowValues[j] = table.getValueAt(i, j) != null ? table.getValueAt(i, j).toString().trim() : "";
	        }

	        if (!maNV.isEmpty()     && !maNV.equals(rowValues[0])) isMatch = false;
	        if (!hoTen.isEmpty()    && !hoTen.equals(rowValues[1])) isMatch = false;
	        if (!soDT.isEmpty()     && !soDT.equals(rowValues[2])) isMatch = false;
	        if (!email.isEmpty()    && !email.equals(rowValues[3])) isMatch = false;
	        if (!ngaySinh.isEmpty() && !ngaySinh.equals(rowValues[4])) isMatch = false;
	        if (!ngayVao.isEmpty()  && !ngayVao.equals(rowValues[5])) isMatch = false;
	        if (!chucVu.isEmpty()   && !chucVu.equals(rowValues[6])) isMatch = false;
	        if (!gioiTinh.isEmpty() && !gioiTinh.equals(rowValues[7])) isMatch = false;

	        if (isMatch) {
	            table.setRowSelectionInterval(i, i);
	            table.scrollRectToVisible(table.getCellRect(i, 0, true));
	            return;
	        }
	    }
	}


	private void removeOldActionListenersAndAdd(javax.swing.JButton button) {
		for (ActionListener al : button.getActionListeners()) {
			button.removeActionListener(al);
		}
		button.addActionListener(this);
	}

	private void loadData() {
		tableModel.setRowCount(0);
		ArrayList<ent_NhanVien> danhSachNhanVien = nhanVienDAO.getAllNhanVien();
		for (ent_NhanVien nv : danhSachNhanVien) {
			tableModel.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getsDT(), nv.getEmail(), nv.getNgaySinh(),
					nv.getNgayVaoLam(), nv.getChucVu(), nv.getGioiTinh() });
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		if (row != -1) {
			String value = table.getValueAt(row, 0).toString();
			String imagePath = "/IMG_NhanVien/" + value + ".png";
			ImageIcon icon = ImageResizer.resizeImage(imagePath, width, height);

			if (icon != null) {
				anh.setIcon(icon);
			} else {

				ImageIcon defaultIcon = ImageResizer.resizeImage("/image/ThemANh.png", width, height);
				anh.setIcon(defaultIcon);
			}

			fieldMap.get("mãnhânviên").setText(table.getValueAt(row, 0).toString());
			fieldMap.get("họtên").setText(table.getValueAt(row, 1).toString());
			fieldMap.get("sốđiệnthoại").setText(table.getValueAt(row, 2).toString());
			fieldMap.get("email").setText(table.getValueAt(row, 3).toString());
			fieldMap.get("ngàysinh").setText(table.getValueAt(row, 4).toString());
			fieldMap.get("ngàyvàolàm").setText(table.getValueAt(row, 5).toString());
			fieldMap.get("chứcvụ").setText(table.getValueAt(row, 6).toString());
			fieldMap.get("giớitính").setText(table.getValueAt(row, 7).toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			if (!validateFields(true))
				return;

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String maNV = fieldMap.get("mãnhânviên").getText().trim();
			String hoTen = fieldMap.get("họtên").getText().trim();
			String sDT = fieldMap.get("sốđiệnthoại").getText().trim();
			String email = fieldMap.get("email").getText().trim();
			String ngaySinhStr = fieldMap.get("ngàysinh").getText().trim();
			String ngayVaoLamStr = fieldMap.get("ngàyvàolàm").getText().trim();
			String chucVu = fieldMap.get("chứcvụ").getText().trim();
			String gioiTinh = fieldMap.get("giớitính").getText().trim();

			try {
				LocalDate ngaySinh = LocalDate.parse(ngaySinhStr, formatter);
				LocalDate ngayVaoLam = LocalDate.parse(ngayVaoLamStr, formatter);

				for (int i = 0; i < table.getRowCount(); i++) {
					if (table.getValueAt(i, 0).toString().equals(maNV)) {
						JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				// Create and save the staff member to the database first
				ent_NhanVien newNhanVien = new ent_NhanVien(maNV, hoTen, sDT, email, ngaySinh, ngayVaoLam, chucVu,
						gioiTinh);
				nhanVienDAO.addNhanVien(newNhanVien);

				// Image saving code
				try {
					Icon icon = anh.getIcon();
					if (icon instanceof ImageIcon) {
						BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
								BufferedImage.TYPE_INT_RGB);
						Graphics g = bufferedImage.createGraphics();
						icon.paintIcon(null, g, 0, 0);
						g.dispose();

						File folder = new File("./src/IMG_NhanVien");
						if (!folder.exists()) {
							folder.mkdirs();
						}

						File outputfile = new File(folder, maNV + ".jpg");
						ImageIO.write(bufferedImage, "jpg", outputfile);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Lỗi khi lưu ảnh! Nhân viên đã được thêm nhưng không có ảnh.",
							"Cảnh báo", JOptionPane.WARNING_MESSAGE);
				}

				loadData(); // Reload data from database instead of manually adding to table
				clearFields();
				JOptionPane.showMessageDialog(this, "Đã thêm nhân viên thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (DateTimeParseException ex) {
				JOptionPane.showMessageDialog(this, "Ngày tháng không đúng định dạng YYYY-MM-DD!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		else if (o.equals(btnSua)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!validateFields(false))
				return;

			String maNV = fieldMap.get("mãnhânviên").getText().trim();
			String hoTen = fieldMap.get("họtên").getText().trim();
			String sDT = fieldMap.get("sốđiệnthoại").getText().trim();
			String email = fieldMap.get("email").getText().trim();
			String ngaySinhStr = fieldMap.get("ngàysinh").getText().trim();
			String ngayVaoLamStr = fieldMap.get("ngàyvàolàm").getText().trim();
			String chucVu = fieldMap.get("chứcvụ").getText().trim();
			String gioiTinh = fieldMap.get("giớitính").getText().trim();

			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ngaySinh = LocalDate.parse(ngaySinhStr, formatter);
				LocalDate ngayVaoLam = LocalDate.parse(ngayVaoLamStr, formatter);

				// Get the original staff ID from the table
				String originalMaNV = table.getValueAt(selectedRow, 0).toString();

				// Update in the database
				ent_NhanVien nv = nhanVienDAO.getNhanVienByMa(originalMaNV);
				if (nv != null) {
					nv.setMaNV(maNV); // Also update the ID if changed
					nv.setHoTen(hoTen);
					nv.setsDT(sDT);
					nv.setEmail(email);
					nv.setNgaySinh(ngaySinh);
					nv.setNgayVaoLam(ngayVaoLam);
					nv.setChucVu(chucVu);
					nv.setGioiTinh(gioiTinh);
					nhanVienDAO.updateNhanVien(nv);

					// Update image filename if ID has changed
					if (!originalMaNV.equals(maNV)) {
						File oldImageFile = new File("./src/IMG_NhanVien/" + originalMaNV + ".jpg");
						if (oldImageFile.exists()) {
							File newImageFile = new File("./src/IMG_NhanVien/" + maNV + ".jpg");
							oldImageFile.renameTo(newImageFile);
						}
					}

					// Save current image
					try {
						Icon icon = anh.getIcon();
						if (icon instanceof ImageIcon) {
							BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
									BufferedImage.TYPE_INT_RGB);
							Graphics g = bufferedImage.createGraphics();
							icon.paintIcon(null, g, 0, 0);
							g.dispose();

							File folder = new File("./src/IMG_NhanVien");
							if (!folder.exists()) {
								folder.mkdirs();
							}

							File outputfile = new File(folder, maNV + ".jpg");
							ImageIO.write(bufferedImage, "jpg", outputfile);
						}
					} catch (IOException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(this,
								"Lỗi khi lưu ảnh! Thông tin nhân viên đã được cập nhật nhưng ảnh không được lưu.",
								"Cảnh báo", JOptionPane.WARNING_MESSAGE);
					}

					loadData(); // Reload from database
					JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin nhân viên!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên trong cơ sở dữ liệu!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (DateTimeParseException ex) {
				JOptionPane.showMessageDialog(this, "Ngày tháng không đúng định dạng YYYY-MM-DD!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (o.equals(btnLuu)) {
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu tất cả dữ liệu?",
					"Xác nhận lưu", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				boolean hasError = false;

				for (int i = 0; i < table.getRowCount(); i++) {
					String maNV = table.getValueAt(i, 0).toString().trim();
					String hoTen = table.getValueAt(i, 1).toString().trim();
					String sDT = table.getValueAt(i, 2).toString().trim();
					String email = table.getValueAt(i, 3).toString().trim();
					String ngaySinhStr = table.getValueAt(i, 4).toString().trim();
					String ngayVaoLamStr = table.getValueAt(i, 5).toString().trim();
					String chucVu = table.getValueAt(i, 6).toString().trim();
					String gioiTinh = table.getValueAt(i, 7).toString().trim();

					if (!validateData(maNV, hoTen, sDT, email, ngaySinhStr, ngayVaoLamStr, gioiTinh)) {
						JOptionPane.showMessageDialog(this, "Dữ liệu dòng " + (i + 1) + " không hợp lệ!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						hasError = true;
						break;
					}

					try {
						LocalDate ngaySinh = LocalDate.parse(ngaySinhStr, formatter);
						LocalDate ngayVaoLam = LocalDate.parse(ngayVaoLamStr, formatter);

						ent_NhanVien nv = nhanVienDAO.getNhanVienByMa(maNV);
						if (nv != null) {
							nv.setHoTen(hoTen);
							nv.setsDT(sDT);
							nv.setEmail(email);
							nv.setNgaySinh(ngaySinh);
							nv.setNgayVaoLam(ngayVaoLam);
							nv.setChucVu(chucVu);
							nv.setGioiTinh(gioiTinh);
							nhanVienDAO.updateNhanVien(nv);
						} else {
							ent_NhanVien newNhanVien = new ent_NhanVien(maNV, hoTen, sDT, email, ngaySinh, ngayVaoLam,
									chucVu, gioiTinh);
							nhanVienDAO.addNhanVien(newNhanVien);
						}
					} catch (DateTimeParseException ex) {
						JOptionPane.showMessageDialog(this,
								"Dữ liệu ngày tháng dòng " + (i + 1) + " không đúng định dạng YYYY-MM-DD!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						hasError = true;
						break;
					}
				}

				if (!hasError) {
					loadData();
					JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

		else if (o.equals(btnThemAnh)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Chọn ảnh");
			FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png",
					"bmp", "gif");
			fileChooser.setFileFilter(imageFilter);

			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String imagePath = selectedFile.getAbsolutePath();
				ImageIcon icon = ImageResizer.resizeImageFromFilePath(imagePath, width, height);
				if (icon != null) {
					anh.setIcon(icon);
				}
			}
		} else if (o.equals(btnXoaRong)) {
			this.dispose();
			new frm_NhanVien();
		}
	}

	private boolean validateFields(boolean isAdding) {
		JTextField maNVField = fieldMap.get("mãnhânviên");
		JTextField hoTenField = fieldMap.get("họtên");
		JTextField sDTField = fieldMap.get("sốđiệnthoại");
		JTextField emailField = fieldMap.get("email");
		JTextField ngaySinhField = fieldMap.get("ngàysinh");
		JTextField ngayVaoLamField = fieldMap.get("ngàyvàolàm");
		JTextField chucVuField = fieldMap.get("chứcvụ");
		JTextField gioiTinhField = fieldMap.get("giớitính");

		String maNV = maNVField.getText().trim();
		String hoTen = hoTenField.getText().trim();
		String sDT = sDTField.getText().trim();
		String email = emailField.getText().trim();
		String ngaySinh = ngaySinhField.getText().trim();
		String ngayVaoLam = ngayVaoLamField.getText().trim();
		String chucVu = chucVuField.getText().trim();
		String gioiTinh = gioiTinhField.getText().trim();

		if (maNV.isEmpty() || hoTen.isEmpty() || sDT.isEmpty() || email.isEmpty() || ngaySinh.isEmpty()
				|| ngayVaoLam.isEmpty() || chucVu.isEmpty() || gioiTinh.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return validateData(maNV, hoTen, sDT, email, ngaySinh, ngayVaoLam, gioiTinh);
	}

	private boolean validateData(String maNV, String hoTen, String sDT, String email, String ngaySinh,
			String ngayVaoLam, String gioiTinh) {
		if (!maNV.matches(MA_NHAN_VIEN_REGEX)) {
			JOptionPane.showMessageDialog(this, "Mã NV phải có dạng NV + 3-4 số (VD: NV001)", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!hoTen.matches(HO_TEN_REGEX)) {
			JOptionPane.showMessageDialog(this, "Họ tên không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!sDT.matches(SO_DIEN_THOAI_REGEX)) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 số bắt đầu bằng 0", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!email.matches(EMAIL_REGEX)) {
			JOptionPane.showMessageDialog(this, "Email không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!ngaySinh.matches(NGAY_REGEX) || !ngayVaoLam.matches(NGAY_REGEX)) {
			JOptionPane.showMessageDialog(this, "Ngày phải có định dạng YYYY-MM-DD", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!gioiTinh.matches(GIOI_TINH_REGEX)) {
			JOptionPane.showMessageDialog(this, "Giới tính phải là Nam hoặc Nữ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void clearFields() {
		for (JTextField field : fieldMap.values()) {
			field.setText("");
		}
		// Reset the image to default
		ImageIcon defaultIcon = ImageResizer.resizeImage("/image/ThemANh.png", width, height);
		anh.setIcon(defaultIcon);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static void main(String[] args) {
		new frm_ChiTietNhanVien();
	}
}