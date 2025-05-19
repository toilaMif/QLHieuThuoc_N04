package frm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import dao.dao_KeThuoc;
import entity.ent_KeThuoc;
import frm_default.frm_default2;
import hieuUng.ImageResizer;
import hieuUng.XuatExcel;
import hieuUng.menu;

public class frm_keThuoc extends frm_default2 {

	private dao_KeThuoc keThuocDAO;

	public frm_keThuoc() {
		setTitle("Kệ thuốc");
		jTenTrang.setText("Kệ thuốc");
		jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuKeThuoc.png", 50, 50));

		jpHead.setLayout(new FlowLayout(FlowLayout.CENTER, 62, 10));
		timkiem1.setText("Mã Kệ");
		timkiem2.setText("Tên kệ");

		tieuDeCot = new String[] { "Mã Kệ", "Tên kệ", "Sức chứa tối đa", "Số lượng thuốc" };
		tableModel.setColumnIdentifiers(tieuDeCot);
		tableModel.setRowCount(0);
		table.setRowHeight(30);

		keThuocDAO = new dao_KeThuoc();
		loadData();

		btnXem.addActionListener(this);
		btnXoarong.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXuat.addActionListener(this);
		
		
	}

	public static void main(String[] args) {
		new frm_keThuoc();
	}

	private void loadData() {
		tableModel.setRowCount(0);
		ArrayList<ent_KeThuoc> danhSachKe = keThuocDAO.getAllKeThuoc();
		for (ent_KeThuoc ke : danhSachKe) {
			tableModel.addRow(new Object[] { ke.getMaKe(), ke.getTenKe(), ke.getSucChuaToiDa(), ke.getSoLuong() });
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object o = e.getSource();

		if (o.equals(btnXoarong)) {
			jtftimkiem1.setText("Nhập dữ liệu");
			jtftimkiem2.setText("Nhập dữ liệu");
			loadData();
		} else if (o.equals(btnXem)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				String maKe = table.getValueAt(row, 0).toString();
				frm_ChiTiecKeThuoc.maKe_Chon = maKe;
				this.dispose();
				new frm_ChiTiecKeThuoc();
			}else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xem!", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			}
		}

		else if (o.equals(btnTimKiem)) {
			String maKe = jtftimkiem1.getText().trim().toLowerCase();
			String tenKe = jtftimkiem2.getText().trim().toLowerCase();

			if (maKe.equals("nhập dữ liệu"))
				maKe = "";
			if (tenKe.equals("nhập dữ liệu"))
				tenKe = "";

			ArrayList<ent_KeThuoc> filtered = new ArrayList<>();
			for (ent_KeThuoc ke : keThuocDAO.getAllKeThuoc()) {
				boolean matchMa = maKe.isEmpty() || ke.getMaKe().toLowerCase().contains(maKe);
				boolean matchTen = tenKe.isEmpty() || ke.getTenKe().toLowerCase().contains(tenKe);

				if (matchMa && matchTen)
					filtered.add(ke);
			}

			tableModel.setRowCount(0);
			for (ent_KeThuoc ke : filtered) {
				tableModel.addRow(new Object[] { ke.getMaKe(), ke.getTenKe(), ke.getSucChuaToiDa(), ke.getSoLuong() });
			}

			if (filtered.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy kệ thuốc phù hợp!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				loadData();
			}
		} else if (o.equals(btnThem)) {
			this.dispose();
			new frm_ThemKe(); // form chi tiết để thêm
		} else if (o.equals(btnSua)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				String maKe = table.getValueAt(row, 0).toString();
				String tenKe = table.getValueAt(row, 1).toString();
				int sucChua = Integer.parseInt(table.getValueAt(row, 2).toString());
				int soLuong = Integer.parseInt(table.getValueAt(row, 3).toString());

				this.dispose();
				new frm_ThemKe(maKe, tenKe, sucChua, soLuong).setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa!", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				String maKe = table.getValueAt(row, 0).toString();
				int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa kệ thuốc này?",
						"Xác nhận xóa", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					boolean isDeleted = keThuocDAO.deleteKeThuoc(maKe);
					if (isDeleted) {
						JOptionPane.showMessageDialog(this, "Xóa thành công!");
						loadData();
					} else {
						JOptionPane.showMessageDialog(this, "Không thể xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa!", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (o.equals(btnXuat)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Chọn vị trí lưu file");
			fileChooser.setSelectedFile(new File("KeThuoc.xlsx"));
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xlsx"));

			int result = fileChooser.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String filePath = selectedFile.getAbsolutePath();
				if (!filePath.endsWith(".xlsx"))
					filePath += ".xlsx";

				String[] columns = { "Mã Kệ", "Tên kệ", "Sức chứa tối đa", "Số lượng thuốc" };
				ArrayList<Object[]> data = new ArrayList<>();

				for (int i = 0; i < table.getRowCount(); i++) {
					Object[] rowData = new Object[columns.length];
					for (int j = 0; j < columns.length; j++) {
						rowData[j] = table.getValueAt(i, j);
					}
					data.add(rowData);
				}

				XuatExcel.exportData(filePath, columns, data);
			}
		}
	}
}
