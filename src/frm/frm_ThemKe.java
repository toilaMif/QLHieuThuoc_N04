package frm;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.dao_KeThuoc;
import entity.ent_KeThuoc;
import frm_default.frm_default3;

public class frm_ThemKe extends frm_default3 implements ActionListener, MouseListener {
	private static final String MA_KE_REGEX = "^[A-Za-z]{1,2}\\d{3,4}$";
	private static final String TEN_KE_REGEX = "^[\\p{L}0-9\\s]+$";
	private static final String SO_LUONG_REGEX = "^\\d+$";


	private dao_KeThuoc keThuocDAO;

    public frm_ThemKe() {
        setTitle("Trang Thêm Kệ");
        jlTieuDeTrang.setText("Thêm Kệ Thuốc");

        tieuDeCot = new String[] {"Mã Kệ", "Tên Kệ", "Sức Chứa Tối Đa", "Số Lượng Thuốc"};
        tableModel.setColumnIdentifiers(tieuDeCot);
        tableModel.setRowCount(0);
        table.setRowHeight(30);
        table.addMouseListener(this);
        taoThongTin();

        removeOldActionListenersAndAdd(btnThem);
        removeOldActionListenersAndAdd(btnSua);
        removeOldActionListenersAndAdd(btnLuu);
        removeOldActionListenersAndAdd(btnXoaRong);
        
        keThuocDAO = new dao_KeThuoc();
        loadData();
    }
    public frm_ThemKe(String maKe, String tenKe, int sucChuaToiDa, int soLuong) {
        this();
        Component[] components = thongtin.getComponents();
        int fieldIndex = 0;
        String[] data = { maKe, tenKe, String.valueOf(sucChuaToiDa), String.valueOf(soLuong) };

        for (Component comp : components) {
            if (comp instanceof JTextField) {
                JTextField textField = (JTextField) comp;
                textField.setText(data[fieldIndex]);
                fieldIndex++;
            }
        }
        checkAndHighlightRow(maKe);
    }

    // Sửa lại phương thức checkAndHighlightRow để dùng mã kệ
    public void checkAndHighlightRow(String maKeTarget) {
        for (int i = 0; i < table.getRowCount(); i++) {
            String maKeTable = table.getValueAt(i, 0) != null ? table.getValueAt(i, 0).toString().trim() : "";

            if (maKeTable.equals(maKeTarget)) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(table.getCellRect(i, 0, true));
                return;
            }
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        ArrayList<ent_KeThuoc> danhSachKe = keThuocDAO.getAllKeThuoc();
        for (ent_KeThuoc ke : danhSachKe) {
            tableModel.addRow(new Object[] {
                ke.getMaKe(), ke.getTenKe(), ke.getSucChuaToiDa(), ke.getSoLuong()
            });
        }
    }
    private void removeOldActionListenersAndAdd(javax.swing.JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
        button.addActionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        Object source = e.getSource();

        if (source.equals(btnXoaRong)) {
        	this.dispose();
			new frm_keThuoc();
        } else if (source.equals(btnThem)) {
            if (!validateFields(true)) return;

            String maKe = fieldMap.get("mãkệ").getText().trim();
            String tenKe = fieldMap.get("tênkệ").getText().trim();
            String sucChua = fieldMap.get("sứcchứatốiđa").getText().trim();
            String soLuong = fieldMap.get("sốlượngthuốc").getText().trim();

            for (int i = 0; i < table.getRowCount(); i++) {
                if (table.getValueAt(i, 0).toString().equals(maKe)) {
                    JOptionPane.showMessageDialog(this, "Mã kệ đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            tableModel.addRow(new Object[] {maKe, tenKe, sucChua, soLuong});
            clearFields();
            JOptionPane.showMessageDialog(this, "Đã thêm kệ vào bảng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (source.equals(btnSua)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn kệ cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validateFields(false)) return;

            String maKe = fieldMap.get("mãkệ").getText().trim();
            String tenKe = fieldMap.get("tênkệ").getText().trim();
            String sucChua = fieldMap.get("sứcchứatốiđa").getText().trim();
            String soLuong = fieldMap.get("sốlượngthuốc").getText().trim();

            tableModel.setValueAt(maKe, selectedRow, 0);
            tableModel.setValueAt(tenKe, selectedRow, 1);
            tableModel.setValueAt(sucChua, selectedRow, 2);
            tableModel.setValueAt(soLuong, selectedRow, 3);

            JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin kệ!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (source.equals(btnLuu)) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu dữ liệu?", "Xác nhận lưu",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    String maKe = table.getValueAt(i, 0).toString().trim();
                    String tenKe = table.getValueAt(i, 1).toString().trim();
                    String sucChua = table.getValueAt(i, 2).toString().trim();
                    String soLuong = table.getValueAt(i, 3).toString().trim();

                    if (maKe.isEmpty() || tenKe.isEmpty() || sucChua.isEmpty() || soLuong.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + " chứa dữ liệu trống!", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!maKe.matches(MA_KE_REGEX)) {
                        JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Mã kệ không hợp lệ (VD: K001)", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!tenKe.matches(TEN_KE_REGEX)) {
                        JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Tên kệ không hợp lệ!", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!sucChua.matches(SO_LUONG_REGEX) || Integer.parseInt(sucChua) <= 0) {
                        JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Sức chứa phải là số dương!", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!soLuong.matches(SO_LUONG_REGEX) || Integer.parseInt(soLuong) < 0) {
                        JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Số lượng phải là số không âm!", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (Integer.parseInt(soLuong) > Integer.parseInt(sucChua)) {
                        JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + ": Số lượng vượt quá sức chứa!", "Lỗi",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private boolean validateFields(boolean isAdding) {
        JTextField maKeField = fieldMap.get("mãkệ");
        JTextField tenKeField = fieldMap.get("tênkệ");
        JTextField sucChuaField = fieldMap.get("sứcchứatốiđa");
        JTextField soLuongField = fieldMap.get("sốlượngthuốc");

        String maKe = maKeField.getText().trim();
        String tenKe = tenKeField.getText().trim();
        String sucChua = sucChuaField.getText().trim();
        String soLuong = soLuongField.getText().trim();

        if (maKe.isEmpty() || tenKe.isEmpty() || sucChua.isEmpty() || soLuong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (isAdding && !maKe.matches(MA_KE_REGEX)) {
            JOptionPane.showMessageDialog(this, "Mã kệ phải có dạng K + 3 số (VD: K001)", "Lỗi", JOptionPane.ERROR_MESSAGE);
            maKeField.requestFocus();
            return false;
        }

        if (!tenKe.matches(TEN_KE_REGEX)) {
            JOptionPane.showMessageDialog(this, "Tên kệ chỉ được chứa chữ cái và số! (VD: Kệ A)", "Lỗi", JOptionPane.ERROR_MESSAGE);
            tenKeField.requestFocus();
            return false;
        }

        if (!sucChua.matches(SO_LUONG_REGEX) || Integer.parseInt(sucChua) <= 0) {
            JOptionPane.showMessageDialog(this, "Sức chứa tối đa phải là số dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            sucChuaField.requestFocus();
            return false;
        }

        if (!soLuong.matches(SO_LUONG_REGEX) || Integer.parseInt(soLuong) < 0) {
            JOptionPane.showMessageDialog(this, "Số lượng thuốc phải là số không âm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            soLuongField.requestFocus();
            return false;
        }

        if (Integer.parseInt(soLuong) > Integer.parseInt(sucChua)) {
            JOptionPane.showMessageDialog(this, "Số lượng thuốc không được vượt quá sức chứa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            soLuongField.requestFocus();
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

    public static void main(String[] args) {
        new frm_ThemKe();
    }
}
