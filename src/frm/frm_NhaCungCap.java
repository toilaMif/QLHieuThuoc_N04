package frm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import dao.dao_NhaCungCap;
import entity.ent_NhaCungCap;
import frm_default.frm_default2;
import hieuUng.ImageResizer;
import hieuUng.XuatExcel;

public class frm_NhaCungCap extends frm_default2 {
    private dao_NhaCungCap nhaCungCapDAO;

    public frm_NhaCungCap() {
        setTitle("Nhà cung cấp");
        jTenTrang.setText("Nhà cung cấp");
        jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuNCC.png", 50, 50));

        timkiem1.setText("Mã NCC");
        timkiem2.setText("Tên NCC");
        jpHead.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        tieuDeCot = new String[] {"Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại"};
        tableModel.setColumnIdentifiers(tieuDeCot);
        tableModel.setRowCount(0);
        table.setRowHeight(30);

        nhaCungCapDAO = new dao_NhaCungCap();
        loadData();

        btnXem.setEnabled(false);
        btnXoarong.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnSua.addActionListener(this);
        btnThem.addActionListener(this);
        btnXuat.addActionListener(this);
    }

    public static void main(String[] args) {
        new frm_NhaCungCap();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        ArrayList<ent_NhaCungCap> list = nhaCungCapDAO.getAllNhaCungCap();
        for (ent_NhaCungCap ncc : list) {
            tableModel.addRow(new Object[] {
                ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getsDT()
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
            String ma = jtftimkiem1.getText().trim().toLowerCase();
            String ten = jtftimkiem2.getText().trim().toLowerCase();

            if (ma.equals("nhập dữ liệu")) ma = "";
            if (ten.equals("nhập dữ liệu")) ten = "";

            ArrayList<ent_NhaCungCap> filtered = new ArrayList<>();
            for (ent_NhaCungCap ncc : nhaCungCapDAO.getAllNhaCungCap()) {
                boolean matchMa = ma.isEmpty() || ncc.getMaNCC().toLowerCase().contains(ma);
                boolean matchTen = ten.isEmpty() || ncc.getTenNCC().toLowerCase().contains(ten);

                if (matchMa && matchTen)
                    filtered.add(ncc);
            }

            tableModel.setRowCount(0);
            for (ent_NhaCungCap ncc : filtered) {
                tableModel.addRow(new Object[] {
                    ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(), ncc.getsDT()
                });
            }

            if (filtered.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhà cung cấp phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadData();
            }
        } else if (o.equals(btnThem)) {
            this.dispose();
            new frm_ChiTietNhaCungCap(); // Form thêm mới
        } else if (o.equals(btnSua)) {
            int row = table.getSelectedRow();
            if (row != -1) {
                String maNCC = table.getValueAt(row, 0).toString();
                String tenNCC = table.getValueAt(row, 1).toString();
                String diaChi = table.getValueAt(row, 2).toString();
                String soDT = table.getValueAt(row, 3).toString();

                this.dispose();
                new frm_ChiTietNhaCungCap(maNCC, tenNCC, diaChi, soDT).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else if (o.equals(btnXoa)) {
            int row = table.getSelectedRow();
            if (row != -1) {
                String maNCC = table.getValueAt(row, 0).toString();
                int confirm = JOptionPane.showConfirmDialog(this, "Xóa nhà cung cấp này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (nhaCungCapDAO.deleteNhaCungCap(maNCC)) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công!");
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else if (o.equals(btnXuat)) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí lưu file");
            fileChooser.setSelectedFile(new File("NhaCungCap.xlsx"));
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xlsx"));

            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) filePath += ".xlsx";

                String[] columns = { "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại" };
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
