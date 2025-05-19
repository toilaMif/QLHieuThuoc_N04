package frm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.dao_NhanVien;
import entity.ent_NhanVien;
import frm_default.frm_default2;
import hieuUng.ImageResizer;
import hieuUng.XuatExcel;

public class frm_NhanVien extends frm_default2 {

    private dao_NhanVien nhanVienDAO;

    public frm_NhanVien() {
        setTitle("Nhân viên");
        jTenTrang.setText("Nhân viên");
        jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuNhanVien.png", 50, 50));

        timkiem1.setText("Mã Nhân Viên");
        timkiem2.setText("Họ Tên");
        jpHead.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        tieuDeCot = new String[] { "Mã nhân viên", "Họ tên", "Số điện thoại", "Email", "Ngày sinh", "Ngày vào làm", "Chức Vụ", "Giới Tính" };
        tableModel.setColumnIdentifiers(tieuDeCot);
        tableModel.setRowCount(0);
        table.setRowHeight(30);

        nhanVienDAO = new dao_NhanVien();
        loadData();

        btnXem.setEnabled(false);
        btnXoarong.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXem.addActionListener(this);
        btnSua.addActionListener(this);
        btnThem.addActionListener(this);
        btnXuat.addActionListener(this);
    }

    public static void main(String[] args) {
        new frm_NhanVien();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        ArrayList<ent_NhanVien> danhSachNhanVien = nhanVienDAO.getAllNhanVien();
        for (ent_NhanVien nv : danhSachNhanVien) {
            tableModel.addRow(new Object[] {
                nv.getMaNV(), nv.getHoTen(), nv.getsDT(), nv.getEmail(),
                nv.getNgaySinh(), nv.getNgayVaoLam(), nv.getChucVu(), nv.getGioiTinh()
            });
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
        } else if (o.equals(btnTimKiem)) {
            String maNVSearch = jtftimkiem1.getText().trim().toLowerCase();
            String hoTenSearch = jtftimkiem2.getText().trim().toLowerCase();

            if (maNVSearch.equals("nhập dữ liệu")) maNVSearch = "";
            if (hoTenSearch.equals("nhập dữ liệu")) hoTenSearch = "";

            ArrayList<ent_NhanVien> filteredList = new ArrayList<>();
            for (ent_NhanVien nv : nhanVienDAO.getAllNhanVien()) {
                boolean matchesMaNV = maNVSearch.isEmpty() || nv.getMaNV().toLowerCase().contains(maNVSearch);
                boolean matchesHoTen = hoTenSearch.isEmpty() || nv.getHoTen().toLowerCase().contains(hoTenSearch);

                if (matchesMaNV && matchesHoTen) {
                    filteredList.add(nv);
                }
            }

            tableModel.setRowCount(0);
            for (ent_NhanVien nv : filteredList) {
                tableModel.addRow(new Object[] {
                    nv.getMaNV(), nv.getHoTen(), nv.getsDT(), nv.getEmail(),
                    nv.getNgaySinh(), nv.getNgayVaoLam(), nv.getChucVu(), nv.getGioiTinh()
                });
            }

            if (filteredList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên nào khớp với tìm kiếm!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            }
        } else if (o.equals(btnThem)) {
            this.dispose();
            new frm_ChiTietNhanVien();
        } else if (o.equals(btnSua)) {
            int row = table.getSelectedRow();
            if (row != -1) {
            	if (row >= 0) {
            	    String maNV      = table.getValueAt(row, 0).toString(); // Mã nhân viên
            	    String hoTen     = table.getValueAt(row, 1).toString(); // Họ tên
            	    String soDT      = table.getValueAt(row, 2).toString(); // Số điện thoại
            	    String email     = table.getValueAt(row, 3).toString(); // Email
            	    String ngaySinh  = table.getValueAt(row, 4).toString(); // Ngày sinh
            	    String ngayVao   = table.getValueAt(row, 5).toString(); // Ngày vào làm
            	    String chucVu    = table.getValueAt(row, 6).toString(); // Chức vụ
            	    String gioiTinh  = table.getValueAt(row, 7).toString(); // Giới tính

            	    this.dispose(); // Đóng form hiện tại

            	    // Gọi constructor đầy đủ 8 tham số
            	    new frm_ChiTietNhanVien(maNV, hoTen, soDT, email, ngaySinh, ngayVao, chucVu, gioiTinh).setVisible(true);
            	}

            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else if (o.equals(btnXoa)) {
            int row = table.getSelectedRow();
            if (row != -1) {
                String maNV = table.getValueAt(row, 0).toString();

                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean isDeleted = nhanVienDAO.deleteNhanVien(maNV);
                    if (isDeleted) {
                        JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa nhân viên không thành công!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else if (o.equals(btnXuat)) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí lưu file");
            fileChooser.setSelectedFile(new File("NhanVien.xlsx"));
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xlsx"));

            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                String[] columns = { "Mã nhân viên", "Họ tên", "Số điện thoại", "Email", "Ngày sinh", "Ngày vào làm", "Chức vụ", "Giới tính" };
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
