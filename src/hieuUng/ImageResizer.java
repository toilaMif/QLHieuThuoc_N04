package hieuUng;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class ImageResizer {

    // Load ảnh từ resource nội bộ (ví dụ: ảnh trong thư mục /resources)
	public static ImageIcon resizeImage(String path, int width, int height) {
	    URL imageUrl = ImageResizer.class.getResource(path);
	    if (imageUrl != null) {
	        ImageIcon imageIcon = new ImageIcon(imageUrl);
	        Image img = imageIcon.getImage();

	        // Giữ tỷ lệ khung hình
	        double ratio = (double) img.getWidth(null) / (double) img.getHeight(null);
	        if (width / height > ratio) {
	            width = (int) (height * ratio);
	        } else {
	            height = (int) (width / ratio);
	        }

	        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	        return new ImageIcon(img);
	    } else {
	        System.err.println("Không tìm thấy hình ảnh nội bộ: " + path);
	        return null;
	    }
	}

    // Load ảnh từ file hệ thống (đường dẫn tuyệt đối như C:/...)
    public static ImageIcon resizeImageFromFilePath(String filePath, int width, int height) {
        File file = new File(filePath);
        if (file.exists()) {
            ImageIcon imageIcon = new ImageIcon(filePath);
            Image img = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            System.err.println("Không tìm thấy hình ảnh từ máy: " + filePath);
            return null;
        }
    }
}
