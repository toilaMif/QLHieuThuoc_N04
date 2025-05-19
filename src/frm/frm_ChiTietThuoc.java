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

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.dao_thuoc;
import entity.ent_thuoc;
import frm_default.frm_default3;
import hieuUng.ImageResizer;

public class frm_ChiTietThuoc extends frm_default3 implements ActionListener, MouseListener {
    private dao_thuoc thuocDAO;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Regular expressions for validation
    // Regular expressions for validation
    private static final String MA_THUOC_REGEX = "^T\\d{3,4}$";
    private static final String TEN_THUOC_REGEX = "^[A-Za-z0-9\\s]+$";
    private static final String DON_VI_REGEX = "^[A-Za-z\\s]+$";
    private static final String DANH_MUC_REGEX = "^[A-Za-z\\s]+$";
    private static final String GIA_REGEX = "^\\d+(\\.\\d{1,2})?$"; // For both giaNhap and giaBan
    private static final String NGAY_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    private static final String NHA_CUNG_CAP_REGEX = "^[A-Za-z0-9\\s]+$";
    private static final String XUAT_XU_REGEX = "^[A-Za-z\\s]+$";

    public frm_ChiTietThuoc() {
        setTitle("Chi Tiết Thuốc");
        jlTieuDeTrang.setText("Chi Tiết Thuốc");
        // Updated to include giaNhap
        tieuDeCot = new String[] {"Mã thuốc", "Tên thuốc", "Đơn vị", "Danh mục", "Giá nhập", "Giá bán", "Hạn sử dụng", "Nhà cung cấp", "Xuất xứ"};
        header.setFont(new Font("Arial", Font.BOLD, 20));

        tableModel.setColumnIdentifiers(tieuDeCot);
        tableModel.setRowCount(0);
        table.setRowHeight(30);
        table.addMouseListener(this);
        taoThongTin();

        thuocDAO = new dao_thuoc();
        loadData();

        removeOldActionListenersAndAdd(btnSua);
        removeOldActionListenersAndAdd(btnThem);
        removeOldActionListenersAndAdd(btnThemAnh);
        removeOldActionListenersAndAdd(btnLuu);

        setVisible(true);
    }

    private void removeOldActionListenersAndAdd(javax.swing.JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
        button.addActionListener(this);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        ArrayList<ent_thuoc> danhSachThuoc = thuocDAO.getAllThuoc();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (ent_thuoc thuoc : danhSachThuoc) {
            tableModel.addRow(new Object[] {
                thuoc.getMaThuoc(),
                thuoc.getTenThuoc(),
                thuoc.getTenDonVi(),
                thuoc.getTenDanhMuc(),
                thuoc.getGiaNhap() != null ? thuoc.getGiaNhap() : "",
                thuoc.getGiaBan(),
                thuoc.getHanSuDung() != null ? thuoc.getHanSuDung().format(formatter) : "",
                thuoc.getTenNCC(),
                thuoc.getXuatXu()
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        if (row != -1) {
            String maThuoc = table.getValueAt(row, 0).toString();
            String imagePath = "/img_thuoc/" + maThuoc + ".png";
            ImageIcon icon = ImageResizer.resizeImage(imagePath, width, height);

            if (icon != null) {
                anh.setIcon(icon);
            } else {
                ImageIcon defaultIcon = ImageResizer.resizeImage("/image/ThemANh.png", width, height);
                anh.setIcon(defaultIcon);
            }

            // Populate text fields based on fieldMap keys
            fieldMap.get("mãthuốc").setText(table.getValueAt(row, 0).toString());
            fieldMap.get("tênthuốc").setText(table.getValueAt(row, 1).toString());
            fieldMap.get("đơnvị").setText(table.getValueAt(row, 2).toString());
            fieldMap.get("danhmục").setText(table.getValueAt(row, 3).toString());
            fieldMap.get("giánhập").setText(table.getValueAt(row, 4).toString());
            fieldMap.get("giábán").setText(table.getValueAt(row, 5).toString());
            fieldMap.get("hạnsửdụng").setText(table.getValueAt(row, 6).toString());
            fieldMap.get("nhàcungcấp").setText(table.getValueAt(row, 7).toString());
            fieldMap.get("xuấtxứ").setText(table.getValueAt(row, 8).toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        Object o = e.getSource();

        if (o.equals(btnThem)) {
            if (!validateFields(true)) return;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String maThuoc = fieldMap.get("mãthuốc").getText().trim();
            String tenThuoc = fieldMap.get("tênthuốc").getText().trim();
            String donVi = fieldMap.get("đơnvị").getText().trim();
            String danhMuc = fieldMap.get("danhmục").getText().trim();
            String giaNhapStr = fieldMap.get("giánhập").getText().trim();
            String giaBanStr = fieldMap.get("giábán").getText().trim();
            String hanSuDungStr = fieldMap.get("hạnsửdụng").getText().trim();
            String nhaCungCap = fieldMap.get("nhàcungcấp").getText().trim();
            String xuatXu = fieldMap.get("xuấtxứ").getText().trim();

            try {
                Double giaNhap = giaNhapStr.isEmpty() ? null : Double.parseDouble(giaNhapStr);
                double giaBan = Double.parseDouble(giaBanStr);
                LocalDate hanSuDung = LocalDate.parse(hanSuDungStr, formatter);

                // Check for duplicate maThuoc
                for (int i = 0; i < table.getRowCount(); i++) {
                    if (table.getValueAt(i, 0).toString().equals(maThuoc)) {
                        JOptionPane.showMessageDialog(this, "Mã thuốc đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Save image if provided
                Icon icon = anh.getIcon();
                if (icon != null && icon instanceof ImageIcon) {
                    try {
                        BufferedImage bufferedImage = new BufferedImage(
                            icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
                        bufferedImage.getGraphics().drawImage(((ImageIcon) icon).getImage(), 0, 0, null);

                        File folder = new File("./src/img_thuoc");
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }

                        File outputFile = new File(folder, maThuoc + ".png");
                        ImageIO.write(bufferedImage, "png", outputFile);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Lỗi khi lưu ảnh: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                ent_thuoc newThuoc = new ent_thuoc(maThuoc, tenThuoc, donVi, danhMuc, nhaCungCap, hanSuDung, giaNhap, giaBan, xuatXu);
                if (thuocDAO.addThuoc(newThuoc)) {
                    tableModel.addRow(new Object[] { maThuoc, tenThuoc, donVi, danhMuc, giaNhap, giaBan, hanSuDungStr, nhaCungCap, xuatXu });
                    clearFields();
                    JOptionPane.showMessageDialog(this, "Đã thêm thuốc thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thuốc thất bại! Kiểm tra dữ liệu đầu vào.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá nhập hoặc giá bán phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Hạn sử dụng không đúng định dạng YYYY-MM-DD!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (o.equals(btnSua)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc cần sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!validateFields(false)) return;

            String maThuoc = fieldMap.get("mãthuốc").getText().trim();
            String tenThuoc = fieldMap.get("tênthuốc").getText().trim();
            String donVi = fieldMap.get("đơnvị").getText().trim();
            String danhMuc = fieldMap.get("danhmục").getText().trim();
            String giaNhapStr = fieldMap.get("giánhập").getText().trim();
            String giaBanStr = fieldMap.get("giábán").getText().trim();
            String hanSuDungStr = fieldMap.get("hạnsửdụng").getText().trim();
            String nhaCungCap = fieldMap.get("nhàcungcấp").getText().trim();
            String xuatXu = fieldMap.get("xuấtxứ").getText().trim();

            try {
                Double giaNhap = giaNhapStr.isEmpty() ? null : Double.parseDouble(giaNhapStr);
                double giaBan = Double.parseDouble(giaBanStr);
                LocalDate hanSuDung = LocalDate.parse(hanSuDungStr, FORMATTER);

                ent_thuoc thuoc = new ent_thuoc(maThuoc, tenThuoc, donVi, danhMuc, nhaCungCap, hanSuDung, giaNhap, giaBan, xuatXu);
                if (thuocDAO.updateThuoc(thuoc)) {
                    tableModel.setValueAt(maThuoc, selectedRow, 0);
                    tableModel.setValueAt(tenThuoc, selectedRow, 1);
                    tableModel.setValueAt(donVi, selectedRow, 2);
                    tableModel.setValueAt(danhMuc, selectedRow, 3);
                    tableModel.setValueAt(giaNhap, selectedRow, 4);
                    tableModel.setValueAt(giaBan, selectedRow, 5);
                    tableModel.setValueAt(hanSuDungStr, selectedRow, 6);
                    tableModel.setValueAt(nhaCungCap, selectedRow, 7);
                    tableModel.setValueAt(xuatXu, selectedRow, 8);

                    JOptionPane.showMessageDialog(this, "Đã cập nhật thông tin thuốc!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thuốc thất bại! Kiểm tra dữ liệu đầu vào.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá nhập hoặc giá bán phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Hạn sử dụng không đúng định dạng YYYY-MM-DD!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (o.equals(btnLuu)) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu dữ liệu?", "Xác nhận lưu",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                boolean allSuccess = true;

                for (int i = 0; i < table.getRowCount(); i++) {
                    String maThuoc = table.getValueAt(i, 0).toString().trim();
                    String tenThuoc = table.getValueAt(i, 1).toString().trim();
                    String donVi = table.getValueAt(i, 2).toString().trim();
                    String danhMuc = table.getValueAt(i, 3).toString().trim();
                    String giaNhapStr = table.getValueAt(i, 4).toString().trim();
                    String giaBanStr = table.getValueAt(i, 5).toString().trim();
                    String hanSuDungStr = table.getValueAt(i, 6).toString().trim();
                    String nhaCungCap = table.getValueAt(i, 7).toString().trim();
                    String xuatXu = table.getValueAt(i, 8).toString().trim();

                    if (!validateData(maThuoc, tenThuoc, donVi, danhMuc, giaNhapStr, giaBanStr, hanSuDungStr, nhaCungCap, xuatXu)) {
                        JOptionPane.showMessageDialog(this, "Dữ liệu dòng " + (i + 1) + " không hợp lệ!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        Double giaNhap = giaNhapStr.isEmpty() ? null : Double.parseDouble(giaNhapStr);
                        double giaBan = Double.parseDouble(giaBanStr);
                        LocalDate hanSuDung = LocalDate.parse(hanSuDungStr, formatter);

                        ent_thuoc thuoc = thuocDAO.getThuocByMa(maThuoc);
                        if (thuoc != null) {
                            thuoc.setTenThuoc(tenThuoc);
                            thuoc.setTenDonVi(donVi);
                            thuoc.setTenDanhMuc(danhMuc);
                            thuoc.setGiaNhap(giaNhap);
                            thuoc.setGiaBan(giaBan);
                            thuoc.setHanSuDung(hanSuDung);
                            thuoc.setTenNCC(nhaCungCap);
                            thuoc.setXuatXu(xuatXu);
                            if (!thuocDAO.updateThuoc(thuoc)) {
                                allSuccess = false;
                            }
                        } else {
                            ent_thuoc newThuoc = new ent_thuoc(maThuoc, tenThuoc, donVi, danhMuc,
                                nhaCungCap, hanSuDung, giaNhap, giaBan, xuatXu);
                            if (!thuocDAO.addThuoc(newThuoc)) {
                                allSuccess = false;
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Giá nhập hoặc giá bán dòng " + (i + 1) + " phải là số!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(this, "Hạn sử dụng dòng " + (i + 1) +
                            " không đúng định dạng YYYY-MM-DD!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (allSuccess) {
                    loadData();
                    JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Lưu dữ liệu thất bại! Kiểm tra dữ liệu đầu vào.", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        else if (o.equals(btnThemAnh)) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn ảnh");
            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "bmp", "gif");
            fileChooser.setFileFilter(imageFilter);

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String imagePath = selectedFile.getAbsolutePath();
                ImageIcon icon = ImageResizer.resizeImage(imagePath, width, height);
                if (icon != null) {
                    anh.setIcon(icon);
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể tải ảnh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private boolean validateFields(boolean isAdding) {
        JTextField maThuocField = fieldMap.get("mãthuốc");
        JTextField tenThuocField = fieldMap.get("tênthuốc");
        JTextField donViField = fieldMap.get("đơnvị");
        JTextField danhMucField = fieldMap.get("danhmục");
        JTextField giaNhapField = fieldMap.get("giánhập");
        JTextField giaBanField = fieldMap.get("giábán");
        JTextField hanSuDungField = fieldMap.get("hạnsửdụng");
        JTextField nhaCungCapField = fieldMap.get("nhàcungcấp");
        JTextField xuatXuField = fieldMap.get("xuấtxứ");

        String maThuoc = maThuocField.getText().trim();
        String tenThuoc = tenThuocField.getText().trim();
        String donVi = donViField.getText().trim();
        String danhMuc = danhMucField.getText().trim();
        String giaNhap = giaNhapField.getText().trim();
        String giaBan = giaBanField.getText().trim();
        String hanSuDung = hanSuDungField.getText().trim();
        String nhaCungCap = nhaCungCapField.getText().trim();
        String xuatXu = xuatXuField.getText().trim();

        // Allow giaNhap to be optional
        if (maThuoc.isEmpty() || tenThuoc.isEmpty() || donVi.isEmpty() || danhMuc.isEmpty() ||
            giaBan.isEmpty() || hanSuDung.isEmpty() || nhaCungCap.isEmpty() || xuatXu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin (trừ giá nhập)!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return validateData(maThuoc, tenThuoc, donVi, danhMuc, giaNhap, giaBan, hanSuDung, nhaCungCap, xuatXu);
    }

    private boolean validateData(String maThuoc, String tenThuoc, String donVi, String danhMuc,
                                String giaNhap, String giaBan, String hanSuDung, String nhaCungCap, String xuatXu) {
        if (!maThuoc.matches(MA_THUOC_REGEX)) {
            JOptionPane.showMessageDialog(this, "Mã thuốc phải có dạng T + 3-4 số (VD: T001)", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!tenThuoc.matches(TEN_THUOC_REGEX)) {
            JOptionPane.showMessageDialog(this, "Tên thuốc không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!donVi.matches(DON_VI_REGEX)) {
            JOptionPane.showMessageDialog(this, "Đơn vị không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!danhMuc.matches(DANH_MUC_REGEX)) {
            JOptionPane.showMessageDialog(this, "Danh mục không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!giaNhap.isEmpty() && !giaNhap.matches(GIA_REGEX)) {
            JOptionPane.showMessageDialog(this, "Giá nhập phải là số dương hoặc để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!giaBan.matches(GIA_REGEX)) {
            JOptionPane.showMessageDialog(this, "Giá bán phải là số dương", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!hanSuDung.matches(NGAY_REGEX)) {
            JOptionPane.showMessageDialog(this, "Hạn sử dụng phải có định dạng YYYY-MM-DD", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!nhaCungCap.matches(NHA_CUNG_CAP_REGEX)) {
            JOptionPane.showMessageDialog(this, "Nhà cung cấp không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!xuatXu.matches(XUAT_XU_REGEX)) {
            JOptionPane.showMessageDialog(this, "Xuất xứ không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearFields() {
        for (JTextField field : fieldMap.values()) {
            field.setText("");
        }
        anh.setIcon(ImageResizer.resizeImage("/image/ThemANh.png", width, height));
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new frm_ChiTietThuoc().setVisible(true));
    }
}