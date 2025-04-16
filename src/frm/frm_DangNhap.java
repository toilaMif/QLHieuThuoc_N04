package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import hieuUng.GradientPanel;
import hieuUng.ImageResizer;
import hieuUng.doiThuoc;
import hieuUng.nutBam;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;

import controller.ConnectDB;
import dao.dao_taiKhoan;
import entity.ent_taiKhoan;
import frm_default.frm_default;
import frm_default.frm_default2;
import frm_default.frm_default_ThongKe;
import frm_default.frm_default_timKiem;

public class frm_DangNhap extends JFrame implements ActionListener, MouseListener {

	private JPanel jpEast;
	private JLabel jlbDN;

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel jlbBH;
	private JLabel jlbtk;
	private JLabel jlbql;
	private JLabel labelThuonghieu;
	private JLabel labelAVT;
	private GradientPanel jpCen;
	private GradientPanel jpCenA1;
	private GradientPanel jpCenA2;
	private GradientPanel jpCenA3;
	private GradientPanel thuonghieu;
	private GradientPanel jpDN;
	private GradientPanel jpAVT;
	private JTextField textField;
	private JPasswordField textField_1;
	private dao_taiKhoan listTK;
	private JButton btnDangNhap, btnQuenMatKhau;
	private ArrayList<ent_taiKhoan> account;

	public frm_DangNhap() {
//		
		ConnectDB.getConnection();
		listTK = new dao_taiKhoan();
		account = listTK.getAllTaiKhoan();
//		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Đăng Nhập");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(0x013034));
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());

		jpCen = new GradientPanel();
		jpCen.setGradientColors(new Color(0x1F4842), new Color(0x1F4842));
		jpCen.setCornerRadius(50);
		jpCen.setLayout(null);

		jpCenA1 = new GradientPanel();
		jpCenA1.setGradientColors(new Color(0xBCD365), new Color(0xBCD365));
		jpCenA1.setCornerRadius(10);
		jpCenA1.setLayout(new BorderLayout());
		jpCenA1.setBackground(new Color(0xBCD365));
		jpCenA1.setBounds(130, 30, 350, 250);

		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image/DNanh1.png"));
		Image img = imageIcon.getImage().getScaledInstance(345, 245, Image.SCALE_REPLICATE);

		label1 = new JLabel();
		label1.setIcon(new ImageIcon(img));

		label1.setHorizontalAlignment(SwingConstants.CENTER);
		jpCenA1.add(label1, BorderLayout.CENTER);

		jpCenA2 = new GradientPanel(); // 10
		jpCenA2.setGradientColors(new Color(0xBCD365), new Color(0xBCD365));
		jpCenA2.setCornerRadius(10);
		jpCenA2.setLayout(new BorderLayout());
		jpCenA2.setBackground(new Color(0xBCD365));
		jpCenA2.setBounds(485, 280, 350, 250);

		ImageIcon imageIcon1 = new ImageIcon(getClass().getResource("/image/TK.jpg"));
		Image img1 = imageIcon1.getImage().getScaledInstance(345, 245, Image.SCALE_REPLICATE);

		label2 = new JLabel();
		label2.setIcon(new ImageIcon(img1));

		label2.setHorizontalAlignment(SwingConstants.CENTER);
		jpCenA2.add(label2, BorderLayout.CENTER);

		jpCenA3 = new GradientPanel(); // 10
		jpCenA3.setGradientColors(new Color(0xBCD365), new Color(0xBCD365));
		jpCenA3.setCornerRadius(10);
		jpCenA3.setLayout(new BorderLayout());
		jpCenA3.setBackground(new Color(0xBCD365));
		jpCenA3.setBounds(130, 530, 350, 250);

		ImageIcon imageIcon11 = new ImageIcon(getClass().getResource("/image/Anhdn3.png"));
		Image img11 = imageIcon11.getImage().getScaledInstance(345, 245, Image.SCALE_REPLICATE);

		label3 = new JLabel();
		label3.setIcon(new ImageIcon(img11));

		label3.setHorizontalAlignment(SwingConstants.CENTER);
		jpCenA3.add(label3, BorderLayout.CENTER);

		jlbBH = new JLabel("Bán Hàng");
		jlbBH.setForeground(new Color(0xBCD365));
		jlbBH.setFont(new Font("Arial", Font.BOLD, 45));
		jlbBH.setBounds(500, 120, 300, 60);

		jlbtk = new JLabel("Thống kê");
		jlbtk.setForeground(new Color(0xBCD365));
		jlbtk.setFont(new Font("Arial", Font.BOLD, 45));
		jlbtk.setBounds(260, 380, 300, 60);
		jpCen.add(jlbtk);

		jlbql = new JLabel("Quản lý");
		jlbql.setForeground(new Color(0xBCD365));
		jlbql.setFont(new Font("Arial", Font.BOLD, 45));
		jlbql.setBounds(500, 640, 300, 60);
		jpCen.add(jlbql);

		thuonghieu = new GradientPanel(); // 30
		thuonghieu.setGradientColors(Color.white, Color.white);
		thuonghieu.setCornerRadius(30);
		thuonghieu.setBackground(Color.white);
		thuonghieu.setBounds(500, 780, 400, 50);
		labelThuonghieu = new JLabel("Với quản lý hiệu thuốc tây TMT");
		labelThuonghieu.setForeground(new Color(0xBCD365));
		labelThuonghieu.setFont(new Font("Arial", Font.BOLD, 25));
		thuonghieu.add(labelThuonghieu);

		jpCen.add(jpCenA1);
		jpCen.add(jpCenA2);
		jpCen.add(jpCenA3);
		jpCen.add(jlbBH);
		jpCen.add(thuonghieu);

		jpEast = new JPanel();
		jpEast.setPreferredSize(new Dimension(550, 500));
		jpEast.setBackground(new Color(0x013034));
		jpEast.setLayout(null);

		jlbDN = new JLabel("Đăng Nhập");
		jlbDN.setForeground(new Color(0xBCD365));
		jlbDN.setFont(new Font("Arial", Font.BOLD, 45));
		jlbDN.setBounds(59, 93, 300, 60);

		jpEast.add(jlbDN);

		jpDN = new GradientPanel(); // 40
		jpDN.setGradientColors(new Color(0xB5E61D), new Color(0xB5E61D));
		jpDN.setCornerRadius(40);
		jpDN.setBackground(new Color(0xB5E61D));
		jpDN.setBounds(50, 150, 450, 600);
		jpDN.setLayout(null);
		jpDN.setLayout(null);

		// Tạo JPanel chứa ảnh
		jpAVT = new GradientPanel(); // 130
		jpAVT.setGradientColors(Color.white, Color.white);
		jpAVT.setCornerRadius(130);
		jpAVT.setForeground(new Color(0xBCD365));
		jpAVT.setBounds(160, 40, 130, 130);
		jpAVT.setLayout(new BorderLayout());

		// Load ảnh
		URL imgUrl = getClass().getResource("/image/456212.png");
		if (imgUrl == null) {
			System.out.println("Không tìm thấy ảnh!");
		} else {
			ImageIcon imageIcon111 = new ImageIcon(imgUrl);
			Image img111 = imageIcon111.getImage().getScaledInstance(90, 90, Image.SCALE_REPLICATE);

			labelAVT = new JLabel(new ImageIcon(img111));
			labelAVT.setHorizontalAlignment(SwingConstants.CENTER);

			jpAVT.add(labelAVT, BorderLayout.CENTER);
		}

		jpDN.setLayout(null);
		jpDN.add(jpAVT);

		jpEast.add(jpDN);

		JLabel lblNewLabel = new JLabel("Đăng nhập để sử dụng");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(139, 180, 199, 19);
		jpDN.add(lblNewLabel);

		GradientPanel panel = new GradientPanel();
		panel.setLayout(new BorderLayout());
		panel.setBounds(60, 235, 360, 79);
		panel.setGradientColors(Color.white, Color.white);
		panel.setCornerRadius(30);

		jpDN.add(panel);

		JLabel lblNewLabel_1 = new JLabel("      ");
		lblNewLabel_1.setIcon(ImageResizer.resizeImage("/image/DangNhap.png", 40, 40));
		panel.add(lblNewLabel_1, BorderLayout.WEST);


		textField = new JTextField("Tài Khoản");
		textField.setFont(new Font("Arial", Font.PLAIN, 30)); 
		textField.setBorder(BorderFactory.createEmptyBorder()); 
		textField.setColumns(10);
		textField.setForeground(Color.GRAY); 
		
		textField.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (textField.getText().equals("Tài Khoản")) {
		            textField.setText("");
		            textField.setForeground(Color.BLACK); 
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (textField.getText().isEmpty()) {
		            textField.setText("Tài Khoản");
		            textField.setForeground(Color.GRAY); 
		        }
		    }
		});

		panel.add(textField, BorderLayout.CENTER);


		GradientPanel panel_2 = new GradientPanel();
		panel_2.setBounds(60, 328, 360, 79);
		panel_2.setGradientColors(Color.white, Color.white);
		panel_2.setCornerRadius(30);
		jpDN.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("      ");
		lblNewLabel_2.setIcon(ImageResizer.resizeImage("/image/ổkhoa.png", 40, 40));
		
		panel_2.add(lblNewLabel_2, BorderLayout.WEST);

		textField_1 = new JPasswordField("Mật Khẩu");
		textField_1.setFont(new Font("Arial", Font.PLAIN, 30));
		textField_1.setBorder(BorderFactory.createEmptyBorder());
		textField_1.setColumns(10);
		textField_1.setForeground(Color.GRAY);
		textField_1.setEchoChar((char) 0); // Ban đầu hiện placeholder

		textField_1.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (new String(textField_1.getPassword()).equals("Mật Khẩu")) {
		            textField_1.setText("");
		            textField_1.setForeground(Color.BLACK);
		            textField_1.setEchoChar('•'); // Ẩn mật khẩu
		        }
		    }

		    @Override
		    public void focusLost(FocusEvent e) {
		        if (new String(textField_1.getPassword()).trim().isEmpty()) {
		            textField_1.setText("Mật Khẩu");
		            textField_1.setForeground(Color.GRAY);
		            textField_1.setEchoChar((char) 0);
		        }
		    }
		});
		panel_2.add(textField_1, BorderLayout.CENTER);

		// Nút bật/tắt hiển thị mật khẩu
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(ImageResizer.resizeImage("/image/matTat.png", 40, 40));
		btnNewButton_1.setForeground(Color.white);
		btnNewButton_1.setBackground(new Color(0x1F4842));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);

		btnNewButton_1.addActionListener(new ActionListener() {
		    private boolean isPasswordVisible = false;

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        isPasswordVisible = !isPasswordVisible; // Đảo trạng thái trước khi cập nhật UI
		        if (isPasswordVisible) {
		            textField_1.setEchoChar((char) 0);
		            btnNewButton_1.setIcon(ImageResizer.resizeImage("/image/mắtMK.png", 40, 40));
		        } else {
		            textField_1.setEchoChar('•');
		            btnNewButton_1.setIcon(ImageResizer.resizeImage("/image/matTat.png", 40, 40));
		        }
		    }
		});
		panel_2.add(btnNewButton_1, BorderLayout.EAST);

		// Nút Đăng Nhập
		btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.setFont(new Font("Arial", Font.BOLD, 36));
		btnDangNhap.setBounds(100, 430, 300, 65);
		btnDangNhap.setUI(new nutBam(30));
		btnDangNhap.setForeground(Color.white);
		btnDangNhap.setBackground(new Color(0x1F4842));
		btnDangNhap.setContentAreaFilled(false); // Để giữ màu nền
		btnDangNhap.setBorderPainted(false);
		btnDangNhap.setFocusPainted(false);
		btnDangNhap.addActionListener(this);
		btnDangNhap.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	btnDangNhap.setBackground(new Color(0xAABB55));
		    	btnDangNhap.repaint();
		    }

		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	btnDangNhap.setBackground(new Color(0x1F4842));
		    	btnDangNhap.repaint();
		    }
		});

		jpDN.add(btnDangNhap);

		btnQuenMatKhau = new JButton("Quên Mật khẩu");
		btnQuenMatKhau.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		btnQuenMatKhau.setEnabled(false);
		btnQuenMatKhau.setBounds(241, 504, 209, 31);

		btnQuenMatKhau.setBackground(new Color(0x1F4842));
		btnQuenMatKhau.setForeground(Color.WHITE);
		btnQuenMatKhau.setOpaque(false);
		btnQuenMatKhau.setBorderPainted(false);
		btnQuenMatKhau.setFocusPainted(false);

		btnQuenMatKhau.addActionListener(this);
		btnQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				JOptionPane.showMessageDialog(null, "Thông tin quên mật khẩu của bạn đã được gửi đến quản lý", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		jpDN.add(btnQuenMatKhau);
		
		getContentPane().add(jpCen, BorderLayout.CENTER);
		getContentPane().add(jpEast, BorderLayout.EAST);

		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 Object o = e.getSource();
		 if(o.equals(btnDangNhap)) {
			 login();
		 }
	}

	
	public void login() {
//	    String userName = textField.getText();
//	    String pass = new String(textField_1.getPassword());
		String userName = "trung123";
				 String pass = "123";
	    try {
	        ent_taiKhoan tk = listTK.getTaiKhoanByUserName(userName);
	        if (tk != null && tk.getMatKhau().equals(pass)) {
	            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	            frm_default.maTK_DaDangNhap = tk.getMaTK();
	            frm_default2.maTK_DaDangNhap = tk.getMaTK();
	            frm_default_timKiem.maTK_DaDangNhap = tk.getMaTK();
	            frm_default_ThongKe.maTK_DaDangNhap = tk.getMaTK();
	            frm_home.maTK_DaDangNhap = tk.getChucVu();
	           doiThuoc.maTaiKhoanDangNhap = tk.getMaTK();
	           frm_DoiThuoc.maTaiKhoanDangNhap = tk.getMaTK();
	            new frm_home(); 
	            this.dispose();
	        } else {
	            JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi đăng nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	    }
	}


	public static void main(String[] args) {
		new frm_DangNhap();
	}
}