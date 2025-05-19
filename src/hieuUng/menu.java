package hieuUng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class menu extends JPanel {
    private static final Color DEFAULT_COLOR = new Color(0x013034);
    private static final Color ACTIVE_COLOR = new Color(0xA4C639);
    private static final Color HOVER_COLOR = new Color(0x005F5B);

    private ArrayList<JMenuItem> allMenuItems = new ArrayList<>();
    private Map<JButton, JPopupMenu> menuMap = new HashMap<>();
    private JButton activeButton = null;

    public menu(Object[][] menuItems) {
        setLayout(new GridLayout(0, 1, 5, 5));
        setBackground(DEFAULT_COLOR);
        createMenu(menuItems);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                closeAllMenus();
            }
        });
    }

    private void createMenu(Object[][] menuItems) {
        for (Object[] menu : menuItems) {
            String menuText = (String) menu[0];
            String menuIconPath = (String) menu[1];
            JButton mainButton = createButton(menuText, menuIconPath);
            JPopupMenu subMenu = new JPopupMenu();
            subMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            for (Object[] subItem : (Object[][]) menu[2]) {
                String subText = (String) subItem[0];
                String subIconPath = (String) subItem[1];
                String className = (String) subItem[2];

                ImageIcon icon = (subIconPath != null) ? ImageResizer.resizeImage(subIconPath, 40, 40) : null;
                JMenuItem subButton = new JMenuItem(subText, icon);
                subButton.setFont(new Font("Arial", Font.PLAIN, 18));
                subButton.setBackground(DEFAULT_COLOR);
                subButton.setForeground(Color.WHITE);

                subButton.addActionListener(e -> {
                    resetMenuColors();
                    subButton.setBackground(ACTIVE_COLOR);
                    openPage(className);
                    closeAllMenus();
                });

                allMenuItems.add(subButton);
                subMenu.add(subButton);
            }

            mainButton.addActionListener(e -> toggleSubMenu(mainButton, subMenu));
            mainButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (mainButton != activeButton) {
                        mainButton.setBackground(HOVER_COLOR);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (mainButton != activeButton) {
                        mainButton.setBackground(DEFAULT_COLOR);
                    }
                }
            });

            menuMap.put(mainButton, subMenu);
            add(mainButton);
        }
    }

    private JButton createButton(String text, String iconPath) {
        ImageIcon icon = (iconPath != null) ? ImageResizer.resizeImage(iconPath, 40, 40) : null;
        JButton button = new JButton(text, icon);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(DEFAULT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(15);
        return button;
    }

    private void toggleSubMenu(JButton mainButton, JPopupMenu subMenu) {
        if (subMenu.isVisible()) {
            closeAllMenus();
        } else {
            closeAllMenus();
            subMenu.show(mainButton, mainButton.getWidth(), 0);
            mainButton.setBackground(ACTIVE_COLOR);
            activeButton = mainButton;
        }
    }

    private void closeAllMenus() {
        for (JButton button : menuMap.keySet()) {
            menuMap.get(button).setVisible(false);
            button.setBackground(DEFAULT_COLOR);
        }
        activeButton = null;
    }

    private void resetMenuColors() {
        for (JMenuItem item : allMenuItems) {
            item.setBackground(DEFAULT_COLOR);
        }
    }

    private void openPage(String className) {
        try {
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            String fullClassName = className.startsWith("frm.") ? className : "frm." + className;
            Class<?> cls = Class.forName(fullClassName);

            if (!JFrame.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("Class không phải là JFrame: " + fullClassName);
            }

            JFrame frame = (JFrame) cls.getDeclaredConstructor().newInstance();

            if (currentFrame != null) {
                currentFrame.dispose();
            }

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể mở trang: " + className );
        }
    }

    public static void main(String[] args) {
        Object[][] menuItems = {
            { "Thuốc", "/image/menuThuoc.png", new Object[][] {
                { "Cập nhật", "/image/bútchi.png", "frm_Thuoc" },
                { "Tìm kiếm", "/image/menuSearch.png", "frm_TimKiemThuoc" },
                { "Đặt thuốc", "/image/bútchi.png", "frm_DatThuoc" },
                { "Đổi thuốc", "/image/bútchi.png", "frm_DoiThuoc" },
                { "Trả thuốc", "/image/bútchi.png", "frm_TraThuoc" },
                { "Thống kê", "/image/menuThongKe.png", "frm_ThongKeThuoc" }
            }},
            { "Hóa đơn", "/image/menuHoaDon.png", new Object[][] {
                { "Cập nhật", "/image/bútchi.png", "frm_HoaDon" },
                { "Tìm kiếm", "/image/menuSearch.png", "frm_TimKiemHoaDon" },
                { "Lập hóa đơn", "/image/menuTaoPhieu.png", "frm_LapHoaDon" },
                { "Thống kê", "/image/menuThongKe.png", "frm_ThongKeHoaDon" }
            }},
            { "Nhân viên", "/image/menuNhanVien.png", new Object[][] {
                { "Cập nhật", "/image/bútchi.png", "frm_NhanVien" },
                { "Tìm kiếm", "/image/menuSearch.png", "frm_TimKiemNhanVien" },
                { "Thống kê", "/image/menuThongKe.png", "frm_ThongKeNhanVien" }
            }},
            { "Khách hàng", "/image/menuKhachHang.png", new Object[][] {
                { "Cập nhật", "/image/bútchi.png", "frm_KhachHang" },
                { "Tìm kiếm", "/image/menuSearch.png", "frm_TimKiemKhachHang" },
                { "Thống kê", "/image/menuThongKe.png", "frm_ThongKeKhachHang" }
            }},
            { "Nhà cung cấp", "/image/menuNCC.png", new Object[][] {
                { "Cập nhật", "/image/bútchi.png", "frm_NhaCungCap" },
                { "Tìm kiếm", "/image/menuSearch.png", "frm_TimKiemNhaCungCap" }
            }},
            { "Kệ thuốc", "/image/menuKeThuoc.png", new Object[][] {
                { "Cập nhật", "/image/bútchi.png", "frm_keThuoc" },
                { "Đặt cảnh báo", "/image/bútchi.png", "frm_DatCanhBao" },
                { "Gán thuốc", "/image/bútchi.png", "frm_GanThuoc" }
            }},
            { "Phiếu nhập hàng", "/image/munuPhieuNH.png", new Object[][] {
                { "Cập nhật", "/image/bútchi.png", "frm_PhieuNhapHang" },
                { "Lập phiếu nhập", "/image/menuTaoPhieu.png", "frm_LapPhieuNhap" }
            }},
            { "Tài khoản", "/image/menuTaiKhoan.png", new Object[][] {
                { "Cập nhật", "/image/bútchi.png", "frm_TaiKhoan" }
            }}
        };

        JFrame frame = new JFrame("Menu Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new menu(menuItems));
        frame.setVisible(true);
    }
}
