package hieuUng;

import javax.swing.*;

import dao.dao_KeThuoc;
import entity.ent_KeThuoc;
import frm.frm_DatCanhBao;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class menuKeThuoc extends JPanel {
	private static final Color DEFAULT_COLOR = new Color(0x013034);
	private static final Color ACTIVE_COLOR = new Color(0xA4C639);
	private static final Color HOVER_COLOR = new Color(0x005F5B);
	private static dao_KeThuoc dao;

	private ArrayList<JMenuItem> allMenuItems = new ArrayList<>();
	private Map<JButton, JPopupMenu> menuMap = new HashMap<>();
	private JButton activeButton = null;

	public menuKeThuoc(Object[][] menuItems) {
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

			mainButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String parentMenuText = mainButton.getText();
					System.out.println("Bạn đã click: " + parentMenuText);
					frm_DatCanhBao.tenke_make.setText(parentMenuText);
				}
			});
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

	public static void main(String[] args) {
		dao = new dao_KeThuoc();
		ArrayList<ent_KeThuoc> danhSachKe = dao.getAllKeThuoc();
		System.out.println("\n📋 Danh sách kệ thuốc:");
		for (ent_KeThuoc ke : danhSachKe) {
		}

		ArrayList<Object[]> menuItemsList = new ArrayList<>();

		for (ent_KeThuoc ke : danhSachKe) {
			String tenMenu = ke.getTenKe() + " - " + ke.getMaKe();
			String iconPath = "/image/menuKeThuoc.png";
			menuItemsList.add(new Object[] { tenMenu, iconPath });
		}

		Object[][] menuItems = new Object[menuItemsList.size()][2];
		for (int i = 0; i < menuItemsList.size(); i++) {
			menuItems[i] = menuItemsList.get(i);
		}

		JFrame frame = new JFrame("Menu Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		frame.setLocationRelativeTo(null);
		frame.add(new menuKeThuoc(menuItems));
		frame.setVisible(true);
	}
}
