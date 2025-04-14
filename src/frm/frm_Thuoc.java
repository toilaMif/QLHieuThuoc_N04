package frm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.dao_thuoc;
import entity.ent_thuoc;
import frm_default.frm_default2;
import hieuUng.ImageResizer;
//import hieuUng.XuatExcel;

public class frm_Thuoc extends frm_default2 implements ActionListener {
    private dao_thuoc thuocDAO;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public frm_Thuoc() {
        setTitle("Thuốc");
        jTenTrang.setText("Thuốc");
        jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuThuoc.png", 50, 50));

        timkiem1.setText("Mã thuốc");
        timkiem2.setText("Tên thuốc");
        jpHead.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        tieuDeCot = new String[] { "Mã thuốc", "Tên thuốc", "Đơn vị", "Danh mục", "Giá nhập", "Giá bán", "Hạn sử dụng", "Nhà cung cấp", "Xuất xứ" };

        tableModel.setColumnIdentifiers(tieuDeCot);
        tableModel.setRowCount(0);

        table.setRowHeight(30);

        // Kết nối DAO
        thuocDAO = new dao_thuoc();
        loadData();

        btnXem.setEnabled(false);

        // Gắn sự kiện cho các nút
        btnXoarong.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXem.addActionListener(this);
        btnSua.addActionListener(this);
        btnThem.addActionListener(this);
        btnXuat.addActionListener(this);

        setVisible(true);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        ArrayList<ent_thuoc> danhSachThuoc = thuocDAO.getAllThuoc();
        for (ent_thuoc thuoc : danhSachThuoc) {
            tableModel.addRow(new Object[] {
                thuoc.getMaThuoc(),
                thuoc.getTenThuoc(),
                thuoc.getTenDonVi(),
                thuoc.getTenDanhMuc(),
                thuoc.getGiaNhap() != null ? thuoc.getGiaNhap() : "",
                thuoc.getGiaBan(),
                thuoc.getHanSuDung() != null ? thuoc.getHanSuDung().format(FORMATTER) : "",
                thuoc.getTenNCC(),
                thuoc.getXuatXu()
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
            String maThuocSearch = jtftimkiem1.getText().trim().toLowerCase();
            String tenThuocSearch = jtftimkiem2.getText().trim().toLowerCase();

            if (maThuocSearch.equals("nhập dữ liệu")) {
                maThuocSearch = "";
            }
            if (tenThuocSearch.equals("nhập dữ liệu")) {
                tenThuocSearch = "";
            }

            ArrayList<ent_thuoc> filteredList = new ArrayList<>();
            for (ent_thuoc thuoc : thuocDAO.getAllThuoc()) {
                boolean matchesMaThuoc = maThuocSearch.isEmpty() || thuoc.getMaThuoc().toLowerCase().contains(maThuocSearch);
                boolean matchesTenThuoc = tenThuocSearch.isEmpty() || thuoc.getTenThuoc().toLowerCase().contains(tenThuocSearch);

                if (matchesMaThuoc && matchesTenThuoc) {
                    filteredList.add(thuoc);
                }
            }

            tableModel.setRowCount(0);
            for (ent_thuoc thuoc : filteredList) {
                tableModel.addRow(new Object[] {
                    thuoc.getMaThuoc(),
                    thuoc.getTenThuoc(),
                    thuoc.getTenDonVi(),
                    thuoc.getTenDanhMuc(),
                    thuoc.getGiaNhap() != null ? thuoc.getGiaNhap() : "",
                    thuoc.getGiaBan(),
                    thuoc.getHanSuDung() != null ? thuoc.getHanSuDung().format(FORMATTER) : "",
                    thuoc.getTenNCC(),
                    thuoc.getXuatXu()
                });
            }

            if (filteredList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thuốc nào khớp với tìm kiếm!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                loadData();
            }
        } else if (o.equals(btnThem)) {
            this.dispose();
            new frm_ChiTietThuoc();
        } else if (o.equals(btnSua)) {
            int row = table.getSelectedRow();
            if (row != -1) {
                String maThuoc = table.getValueAt(row, 0).toString();
                this.dispose();
                frm_ChiTietThuoc chiTietForm = new frm_ChiTietThuoc();
                // Select the row in frm_ChiTietThuoc (requires additional logic in frm_ChiTietThuoc)
                JOptionPane.showMessageDialog(this, "Vui lòng chỉnh sửa thông tin trong form chi tiết!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa!", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else if (o.equals(btnXem)) {
            System.out.println("xem");
        } else if (o.equals(btnXoa)) {
            int row = table.getSelectedRow();
            if (row != -1) {
                String maThuoc = table.getValueAt(row, 0).toString();
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa thuốc này?",
                        "Xác nhận xóa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean isDeleted = thuocDAO.deleteThuoc(maThuoc);
                    if (isDeleted) {
                        JOptionPane.showMessageDialog(this, "Xóa thuốc thành công!", "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE);
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thuốc không thành công!", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa!", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else if (o.equals(btnXuat)) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí lưu file");
            fileChooser.setSelectedFile(new File("DanhSachThuoc.xlsx"));
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xlsx"));

            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                String[] columns = { "Mã thuốc", "Tên thuốc", "Đơn vị", "Danh mục", "Giá nhập", "Giá bán", "Hạn sử dụng", "Nhà cung cấp", "Xuất xứ" };
                ArrayList<Object[]> data = new ArrayList<>();
                for (int i = 0; i < table.getRowCount(); i++) {
                    data.add(new Object[] {
                        table.getValueAt(i, 0).toString(),
                        table.getValueAt(i, 1).toString(),
                        table.getValueAt(i, 2).toString(),
                        table.getValueAt(i, 3).toString(),
                        table.getValueAt(i, 4).toString(),
                        table.getValueAt(i, 5).toString(),
                        table.getValueAt(i, 6).toString(),
                        table.getValueAt(i, 7).toString(),
                        table.getValueAt(i, 8).toString()
                    });
                }

                // Placeholder for Excel export (uncomment when XuatExcel is available)
                // XuatExcel.exportData(filePath, columns, data);
                JOptionPane.showMessageDialog(this, "Chức năng xuất Excel chưa được triển khai!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_Thuoc().setVisible(true));
    }
}