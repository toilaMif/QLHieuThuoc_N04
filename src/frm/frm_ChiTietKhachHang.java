package frm;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics;


import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import dao.dao_khachHang;
import entity.ent_khachHang;
import frm_default.frm_default3;
import hieuUng.ImageResizer;

public class frm_ChiTietKhachHang extends frm_default3 implements ActionListener, MouseListener {
	private dao_khachHang khachHangDAO;

	private static final String MA_KHACH_HANG_REGEX = "^KH\\d{3,4}$";
	private static final String HO_TEN_REGEX = "^([A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴĐÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸ][a-zàáạảãâầấậẩẫăằắặẳẵđèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹ]*(\\s[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴĐÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸ][a-zàáạảãâầấậẩẫăằắặẳẵđèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹ]*)*)+$";
	private static final String SO_DIEN_THOAI_REGEX = "^(0)(\\d{9})$";
	private static final String GIOI_TINH_REGEX = "^(Nam|Nữ)$";

	public frm_ChiTietKhachHang() {
		setTitle("Chi Tiết Khách Hàng");
		jlTieuDeTrang.setText("Chi Tiết Khách Hàng");

		tieuDeCot = new String[] { "Mã khách hàng", "Họ và tên", "Số điện thoại", "Giới tính" };
		tableModel.setColumnIdentifiers(tieuDeCot);
		tableModel.setRowCount(0);
		table.setRowHeight(30);
		table.addMouseListener(this);
		taoThongTin();

		khachHangDAO = new dao_khachHang();
		loadData();

		removeOldActionListenersAndAdd(btnSua);
		removeOldActionListenersAndAdd(btnThem);
		removeOldActionListenersAndAdd(btnThemAnh);
		removeOldActionListenersAndAdd(btnImport);
		removeOldActionListenersAndAdd(btnLuu);
		removeOldActionListenersAndAdd(btnThemAnh);

	}

	public frm_ChiTietKhachHang(String maKh, String hoTen, String soDT, String gioiTinh) {
		this();
		Component[] components = thongtin.getComponents();
		int fieldIndex = 0;
		String[] data = { maKh, hoTen, soDT, gioiTinh };

		for (Component comp : components) {
			if (comp instanceof JTextField) {
				JTextField textField = (JTextField) comp;
				textField.setText(data[fieldIndex]);
				fieldIndex++;
			}
		}
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
		ArrayList<ent_khachHang> danhSachKhachHang = khachHangDAO.getAllKhachHang();
		for (ent_khachHang kh : danhSachKhachHang) {
			tableModel.addRow(new Object[] { kh.getMaKh(), kh.getHoTen(), kh.getsDT(), kh.getGioiTinh() });
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		if (row != -1) {
			String value = table.getValueAt(row, 0).toString();
			String imagePath = "/IMG_KhachHang/" + value + ".jpg";
			ImageIcon icon = ImageResizer.resizeImage(imagePath, width, height);

			if (icon != null) {
				anh.setIcon(icon);
			} else {

				ImageIcon defaultIcon = ImageResizer.resizeImage("/image/ThemANh.png", width, height);
				anh.setIcon(defaultIcon);
			}

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

	public void checkAndHighlightRow() {
		JTextField maKhField = fieldMap.get("mãkháchhàng");
		JTextField hoTenField = fieldMap.get("họvàtên");
		JTextField sDTField = fieldMap.get("sốđiệnthoại");
		JTextField gioiTinhField = fieldMap.get("giớitính");

		if (maKhField == null || hoTenField == null || sDTField == null || gioiTinhField == null) {
			return;
		}

		String maKh = maKhField.getText().trim();
		String hoTen = hoTenField.getText().trim();
		String sDT = sDTField.getText().trim();
		String gioiTinh = gioiTinhField.getText().trim();

		for (int i = 0; i < table.getRowCount(); i++) {
			String maKhTable = table.getValueAt(i, 0) != null ? table.getValueAt(i, 0).toString().trim() : "";
			String hoTenTable = table.getValueAt(i, 1) != null ? table.getValueAt(i, 1).toString().trim() : "";
			String sDTTable = table.getValueAt(i, 2) != null ? table.getValueAt(i, 2).toString().trim() : "";
			String gioiTinhTable = table.getValueAt(i, 3) != null ? table.getValueAt(i, 3).toString().trim() : "";

			boolean isMatch = true;
			if (!maKh.isEmpty() && !maKh.equals(maKhTable))
				isMatch = false;
			if (!hoTen.isEmpty() && !hoTen.equals(hoTenTable))
				isMatch = false;
			if (!sDT.isEmpty() && !sDT.equals(sDTTable))
				isMatch = false;
			if (!gioiTinh.isEmpty() && !gioiTinh.equals(gioiTinhTable))
				isMatch = false;

			if (isMatch) {
				table.setRowSelectionInterval(i, i);
				table.scrollRectToVisible(table.getCellRect(i, 0, true));
				return;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object o = e.getSource();

		if (o.equals(btnXoaRong)) {
			this.dispose();
			new frm_KhachHang();
		} else if (o.equals(btnLuu)) {
			int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu dữ liệu?", "Xác nhận lưu",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (confirm == JOptionPane.YES_OPTION) {
				for (int i = 0; i < table.getRowCount(); i++) {
					String maKh = table.getValueAt(i, 0).toString().trim();
					String hoTen = table.getValueAt(i, 1).toString().trim();
					String sDT = table.getValueAt(i, 2).toString().trim();
					String gioiTinh = table.getValueAt(i, 3).toString().trim();

					// Kiểm tra dữ liệu
					if (maKh.isEmpty() || hoTen.isEmpty() || sDT.isEmpty() || gioiTinh.isEmpty()) {
						JOptionPane.showMessageDialog(this,
								"Dòng " + (i + 1) + " chứa dữ liệu trống. Vui lòng kiểm tra lại!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (!maKh.matches(MA_KHACH_HANG_REGEX)) {
						JOptionPane.showMessageDialog(this,
								"Dòng " + (i + 1) + ": Mã khách hàng không hợp lệ (VD: KH001).", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (!hoTen.matches(HO_TEN_REGEX)) {
						JOptionPane.showMessageDialog(this,
								"Dòng " + (i + 1) + ": Tên khách hàng không hợp lệ (VD: Nguyễn Thành Trung).", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (!sDT.matches(SO_DIEN_THOAI_REGEX)) {
						JOptionPane.showMessageDialog(this,
								"Dòng " + (i + 1) + ": Số điện thoại không hợp lệ (phải có 10 chữ số).", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (!gioiTinh.equalsIgnoreCase("Nam") && !gioiTinh.equalsIgnoreCase("Nữ")) {
						JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Giới tính phải là 'Nam' hoặc 'Nữ'.",
								"Lỗi", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Sau khi kiểm tra dữ liệu xong, thực hiện lưu
					ent_khachHang kh = khachHangDAO.getKhachHangByMa(maKh);

					if (kh != null) {
						// Cập nhật nếu có thay đổi
						boolean hasChanges = false;
						if (!hoTen.equals(kh.getHoTen()))
							hasChanges = true;
						if (!sDT.equals(kh.getsDT()))
							hasChanges = true;
						if (!gioiTinh.equals(kh.getGioiTinh()))
							hasChanges = true;

						if (hasChanges) {
							kh.setHoTen(hoTen);
							kh.setsDT(sDT);
							kh.setGioiTinh(gioiTinh);
							khachHangDAO.updateKhachHang(kh);
						}
					} else {
						// Thêm mới
						ent_khachHang newKhachHang = new ent_khachHang(maKh, hoTen, sDT, gioiTinh);
						khachHangDAO.addKhachHang(newKhachHang);
					}
				}

				loadData(); // Tải lại dữ liệu từ cơ sở dữ liệu
				JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		else if (o.equals(btnThem)) {
			if (!validateFields(true))
				return;

			String maKh = fieldMap.get("mãkháchhàng").getText().trim();
			String hoTen = fieldMap.get("họvàtên").getText().trim();
			String sDT = fieldMap.get("sốđiệnthoại").getText().trim();
			String gioiTinh = fieldMap.get("giớitính").getText().trim();

			// Kiểm tra mã khách hàng đã tồn tại trong bảng chưa
			for (int i = 0; i < table.getRowCount(); i++) {
				if (table.getValueAt(i, 0).toString().equals(maKh)) {
					JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			// Lưu ảnh nếu có
			try {
				Icon icon = anh.getIcon();
				if (icon instanceof ImageIcon) {
					BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
							BufferedImage.TYPE_INT_RGB);
					Graphics g = bufferedImage.createGraphics();
					icon.paintIcon(null, g, 0, 0);
					g.dispose();

					File folder = new File("./src/IMG_KhachHang");
					if (!folder.exists()) {
						folder.mkdirs(); // tạo thư mục nếu chưa có
					}

					File outputfile = new File(folder, maKh + ".jpg");
					ImageIO.write(bufferedImage, "jpg", outputfile);
					
					System.out.println(outputfile+"");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Lỗi khi lưu ảnh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Thêm vào bảng
			tableModel.addRow(new Object[] { maKh, hoTen, sDT, gioiTinh });

			clearFields();
			JOptionPane.showMessageDialog(this, "Đã thêm khách hàng vào bảng và lưu ảnh thành công!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (o.equals(btnSua)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!validateFields(false))
				return;

			String maKh = fieldMap.get("mãkháchhàng").getText().trim();
			String hoTen = fieldMap.get("họvàtên").getText().trim();
			String sDT = fieldMap.get("sốđiệnthoại").getText().trim();
			String gioiTinh = fieldMap.get("giớitính").getText().trim();

			// Cập nhật thông tin trên bảng
			tableModel.setValueAt(maKh, selectedRow, 0);
			tableModel.setValueAt(hoTen, selectedRow, 1);
			tableModel.setValueAt(sDT, selectedRow, 2);
			tableModel.setValueAt(gioiTinh, selectedRow, 3);

			JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin khách hàng trên bảng!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (o.equals(btnImport)) {
			JFileChooser fileChooser = new JFileChooser();
			int result = fileChooser.showOpenDialog(this);

			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				String fileName = file.getName().toLowerCase();

				try {
					ArrayList<ent_khachHang> ds = new ArrayList<>();

					if (fileName.endsWith(".csv")) {
						ds = readFromCSV(file);
					} else if (fileName.endsWith(".xlsx")) {
						ds = readFromExcel(file);
					} else {
						JOptionPane.showMessageDialog(this, "Chỉ hỗ trợ file CSV hoặc Excel (.xlsx)", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					for (ent_khachHang kh : ds) {
						// Nếu chưa có trong bảng mới thêm
						boolean exists = false;
						for (int i = 0; i < tableModel.getRowCount(); i++) {
							if (tableModel.getValueAt(i, 0).toString().equals(kh.getMaKh())) {
								exists = true;
								break;
							}
						}
						if (!exists) {
							tableModel.addRow(
									new Object[] { kh.getMaKh(), kh.getHoTen(), kh.getsDT(), kh.getGioiTinh() });
						}
					}

					JOptionPane.showMessageDialog(this, "Đã import thành công!", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi import!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (o.equals(btnThemAnh)) {
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
		}

	}

	private ArrayList<ent_khachHang> readFromCSV(File file) throws IOException {
		ArrayList<ent_khachHang> ds = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			String[] parts = line.split(",");
			if (parts.length >= 4) {
				String maKH = parts[0].trim();
				String hoTen = parts[1].trim();
				String sDT = parts[2].trim();
				String gioiTinh = parts[3].trim();
				ds.add(new ent_khachHang(maKH, hoTen, sDT, gioiTinh));
			}
		}
		br.close();
		return ds;
	}

	private ArrayList<ent_khachHang> readFromExcel(File file) throws IOException {
		ArrayList<ent_khachHang> ds = new ArrayList<>();
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);

		for (Row row : sheet) {
			if (row.getRowNum() == 0)
				continue; // Bỏ qua tiêu đề

			Cell cell0 = row.getCell(0);
			Cell cell1 = row.getCell(1);
			Cell cell2 = row.getCell(2);
			Cell cell3 = row.getCell(3);

			if (cell0 != null && cell1 != null && cell2 != null && cell3 != null) {
				String maKH = cell0.toString().trim();
				String hoTen = cell1.toString().trim();
				String sDT = cell2.toString().trim();
				String gioiTinh = cell3.toString().trim();
				ds.add(new ent_khachHang(maKH, hoTen, sDT, gioiTinh));
			}
		}

		workbook.close();
		fis.close();
		return ds;
	}

	private boolean validateFields(boolean isAdding) {
		JTextField maKhField = fieldMap.get("mãkháchhàng");
		JTextField hoTenField = fieldMap.get("họvàtên");
		JTextField sDTField = fieldMap.get("sốđiệnthoại");
		JTextField gioiTinhField = fieldMap.get("giớitính");

		String maKh = maKhField.getText().trim();
		String hoTen = hoTenField.getText().trim();
		String sDT = sDTField.getText().trim();
		String gioiTinh = gioiTinhField.getText().trim();

		if (maKh.isEmpty() || hoTen.isEmpty() || sDT.isEmpty() || gioiTinh.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (isAdding && !maKh.matches(MA_KHACH_HANG_REGEX)) {
			JOptionPane.showMessageDialog(this, "Mã KH phải có dạng KH + 4 số (VD: KH0001)", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			maKhField.requestFocus();
			return false;
		}

		if (!hoTen.matches(HO_TEN_REGEX)) {
			JOptionPane.showMessageDialog(this, "Họ tên chỉ được chứa chữ cái (VD: Nguyễn Thành Trung)", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			hoTenField.requestFocus();
			return false;
		}

		if (!sDT.matches(SO_DIEN_THOAI_REGEX)) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ (VD: 0912345678)", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			sDTField.requestFocus();
			return false;
		}

		if (!gioiTinh.matches(GIOI_TINH_REGEX)) {
			JOptionPane.showMessageDialog(this, "Giới tính phải là Nam, Nữ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			gioiTinhField.requestFocus();
			return false;
		}

		return true;
	}

	private void clearFields() {
		for (JTextField field : fieldMap.values()) {
			field.setText("");
		}
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
		new frm_ChiTietKhachHang();
	}
}