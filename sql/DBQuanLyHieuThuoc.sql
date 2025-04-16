--CREATE DATABASE QuanLyHieuThuoc;
USE QuanLyHieuThuoc;




CREATE TABLE ChucVu (
    maChucVu VARCHAR(20) PRIMARY KEY,
    tenChucVu NVARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE NhanVien (
    maNV VARCHAR(20) PRIMARY KEY,
    hoTen NVARCHAR(100) NOT NULL,
    gioiTinh NVARCHAR(10) NOT NULL DEFAULT N'Khác',
    sDT VARCHAR(15) UNIQUE,
    email VARCHAR(100) UNIQUE,
    ngaySinh DATE,
    ngayVaoLam DATE NOT NULL,
    maChucVu VARCHAR(20) NOT NULL,
    FOREIGN KEY (maChucVu) REFERENCES ChucVu(maChucVu) ON DELETE CASCADE
);

CREATE TABLE TaiKhoan (
    maNV VARCHAR(20) PRIMARY KEY, 
    tenDN VARCHAR(50) UNIQUE NOT NULL,
    matKhau VARCHAR(255) NOT NULL,
    maChucVu VARCHAR(20) NOT NULL,
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV) ON DELETE CASCADE,
    FOREIGN KEY (maChucVu) REFERENCES ChucVu(maChucVu)
);


CREATE TABLE KhachHang (
    maKH VARCHAR(20) PRIMARY KEY,
    hoTen NVARCHAR(100) NOT NULL,
    gioiTinh NVARCHAR(10) NOT NULL DEFAULT N'Khác',
    sDT VARCHAR(15) UNIQUE NOT NULL
);

CREATE TABLE DanhMucThuoc (
    maDM VARCHAR(20) PRIMARY KEY,
    tenDM NVARCHAR(100) NOT NULL
);

CREATE TABLE DonVi (
    maDV VARCHAR(20) PRIMARY KEY,
    tenDV NVARCHAR(50) NOT NULL
);
CREATE TABLE NhaCungCap (
    maNCC VARCHAR(20) PRIMARY KEY,
    tenNCC NVARCHAR(100) NOT NULL,
    diaChi NVARCHAR(255) NOT NULL,
    sDT VARCHAR(15) NOT NULL
);

CREATE TABLE Thuoc (
    maThuoc VARCHAR(20) PRIMARY KEY,
    tenThuoc NVARCHAR(100) NOT NULL,
    maDonVi VARCHAR(20) NOT NULL,
    maDM VARCHAR(20) NOT NULL,
    maNCC VARCHAR(20) NOT NULL,
    hanSuDung DATE NOT NULL,
    giaNhap DECIMAL(10,2) NOT NULL,
    giaBan DECIMAL(10,2) NOT NULL,
	xuatXu NVARCHAR(100) NOT NULL,
    FOREIGN KEY (maDonVi) REFERENCES DonVi(maDV),
    FOREIGN KEY (maDM) REFERENCES DanhMucThuoc(maDM),
    FOREIGN KEY (maNCC) REFERENCES NhaCungCap(maNCC)
);

CREATE TABLE PhieuNhapHang (
    maPhieuNhap VARCHAR(20) PRIMARY KEY,
    maNCC VARCHAR(20) NOT NULL,
    ngayNhap DATE NOT NULL,
    maNV VARCHAR(20) NOT NULL,
    tongTien DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (maNCC) REFERENCES NhaCungCap(maNCC),
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
);

CREATE TABLE CTPhieuNhapHang (
    maPhieuNhap VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL,
    maDonVi VARCHAR(20),
    giaNhap DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (maPhieuNhap, maThuoc),
    FOREIGN KEY (maPhieuNhap) REFERENCES PhieuNhapHang(maPhieuNhap),
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc),
    FOREIGN KEY (maDonVi) REFERENCES DonVi(maDV)
);

CREATE TABLE Thue (
    maThue VARCHAR(20) PRIMARY KEY,
    tenThue NVARCHAR(50) NOT NULL,
    mucThue DECIMAL(10,2) NOT NULL
);

CREATE TABLE HoaDon (
    maHoaDon VARCHAR(20) PRIMARY KEY,
    ngayLap DATE NOT NULL, 
    tongTien DECIMAL(10,2) NOT NULL,
    maNV VARCHAR(20) NOT NULL,
    maKH VARCHAR(20) NOT NULL,
    maThue VARCHAR(20),
	hinhThucThanhToan NVARCHAR(50) NOT NULL,
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH),
    FOREIGN KEY (maThue) REFERENCES Thue(maThue)
);


CREATE TABLE CTHoaDon (
    maHoaDon VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL,
    maDonVi VARCHAR(20),
    giaBan DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (maHoaDon, maThuoc),
    FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc),
    FOREIGN KEY (maDonVi) REFERENCES DonVi(maDV)
);


CREATE TABLE PhieuDatHang (
    maPhieuDat VARCHAR(20) PRIMARY KEY,
    ngayLap DATE NOT NULL,
    maNV VARCHAR(20) NOT NULL,
    maKH VARCHAR(20) NOT NULL,
    tongTien DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);

CREATE TABLE CTPhieuDatHang (
    maPhieuDat VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL,
    maDonVi VARCHAR(20),
    giaBan DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (maPhieuDat, maThuoc),
    FOREIGN KEY (maPhieuDat) REFERENCES PhieuDatHang(maPhieuDat),
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc),
    FOREIGN KEY (maDonVi) REFERENCES DonVi(maDV)
);


CREATE TABLE PhieuDoiHang (
    maPhieuDoi VARCHAR(20) PRIMARY KEY,
    ngayLap DATE NOT NULL,
    maNV VARCHAR(20) NOT NULL,
    maKH VARCHAR(20) NOT NULL,
    tongTien DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);

CREATE TABLE CTPhieuDoiHang (
    maPhieuDoi VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL,
    maDonVi VARCHAR(20),
    giaBan DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (maPhieuDoi, maThuoc),
    FOREIGN KEY (maPhieuDoi) REFERENCES PhieuDoiHang(maPhieuDoi),
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc),
    FOREIGN KEY (maDonVi) REFERENCES DonVi(maDV)
);


CREATE TABLE PhieuTraHang (
    maPhieuTra VARCHAR(20) PRIMARY KEY,
    ngayLap DATE NOT NULL,
    maNV VARCHAR(20) NOT NULL,
    maKH VARCHAR(20) NOT NULL,
    tongTien DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);

CREATE TABLE CTPhieuTraHang (
    maPhieuTra VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL,
    maDonVi VARCHAR(20),
    giaBan DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (maPhieuTra, maThuoc),
    FOREIGN KEY (maPhieuTra) REFERENCES PhieuTraHang(maPhieuTra),
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc),
    FOREIGN KEY (maDonVi) REFERENCES DonVi(maDV)
);


CREATE TABLE KeThuoc (
    maKe VARCHAR(20) PRIMARY KEY,
    tenKe NVARCHAR(100) NOT NULL,
    sucChuaToDa INT NOT NULL,
    soLuong INT NOT NULL
);

CREATE TABLE CTKeThuoc (
    maKe VARCHAR(20),
    maThuoc VARCHAR(20),
    soLuong INT NOT NULL,
    maDonVi VARCHAR(20),
    maDM VARCHAR(20) NOT NULL,
    hanSuDung DATE NOT NULL,
    PRIMARY KEY (maKe, maThuoc),
    FOREIGN KEY (maKe) REFERENCES KeThuoc(maKe),
    FOREIGN KEY (maThuoc) REFERENCES Thuoc(maThuoc),
    FOREIGN KEY (maDonVi) REFERENCES DonVi(maDV),
    FOREIGN KEY (maDM) REFERENCES DanhMucThuoc(maDM) 
);
-- Data 
--Chức vụ
INSERT INTO ChucVu (maChucVu, tenChucVu) VALUES ('CV001', N'Quản lý');
INSERT INTO ChucVu (maChucVu, tenChucVu) VALUES ('CV002', N'Nhân viên');

--Nhân viên
INSERT INTO NhanVien (maNV, hoTen, gioiTinh, sDT, email, ngaySinh, ngayVaoLam, maChucVu) 
VALUES 
    ('NV001', N'Nguyễn Thành Trung', N'Nam', '0396611627', 'nguyenthanhtrung22092004@gmail.com', '2004-09-22', '2024-03-28', 'CV001'),
    ('NV002', N'Thái Văn Trung', N'Nam', '0312345678', 'tvt@gmail.com', '2004-02-15', '2024-03-28', 'CV002'),
    ('NV003', N'Phạm Viết Dũng Minh', N'Nam', '0335642867', 'PVDM@gmail.com', '2004-12-02', '2024-03-28', 'CV002'),
    ('NV004', N'Nguyễn Minh Quân', N'Nam', '0987654321', 'minhquan.nguyen@email.com', '1990-05-15', '2023-01-10', 'CV002'),
    ('NV005', N'Lê Thị Mai Anh', N'Nữ', '0976543210', 'maianh.le@email.com', '1995-07-20', '2023-02-15', 'CV002'),
    ('NV006', N'Trần Hoàng Nam', N'Nam', '0965432109', 'hoangnam.tran@email.com', '1988-09-25', '2023-03-12', 'CV002'),
    ('NV007', N'Phạm Ngọc Hân', N'Nữ', '0954321098', 'ngochan.pham@email.com', '1992-12-10', '2023-04-05', 'CV002'),
    ('NV008', N'Đỗ Tuấn Kiệt', N'Nam', '0943210987', 'tuankiet.do@email.com', '1985-03-30', '2023-05-18', 'CV002'),
    ('NV009', N'Võ Thùy Linh', N'Nữ', '0932109876', 'thuylinh.vo@email.com', '1998-08-22', '2023-06-22', 'CV002'),
    ('NV010', N'Bùi Khánh An', N'Khác', '0921098765', 'khanhan.bui@email.com', '1991-11-05', '2023-07-30', 'CV002');





-- Tài khoản
INSERT INTO TaiKhoan (maNV, tenDN, matKhau, maChucVu)  
VALUES 
    ('NV001', 'trung123', '123', 'CV001'),
    ('NV002', 'vantrung123', '456', 'CV002'),
    ('NV003', 'dungminh123', '789', 'CV002'),
    ('NV004', 'minhquan90', 'quan@123', 'CV002'),
    ('NV005', 'maianh95', 'mai@456', 'CV002'),
    ('NV006', 'hoangnam88', 'nam@789', 'CV002'),
    ('NV007', 'ngochan92', 'han@321', 'CV002'),
    ('NV008', 'tuankiet85', 'kiet@654', 'CV002'),
    ('NV009', 'thuylinh98', 'linh@987', 'CV002'),
    ('NV010', 'khanhan91', 'an@000', 'CV002');

--khách hàng
INSERT INTO KhachHang (maKH, hoTen, gioiTinh, sDT) VALUES
('KH001', N'Nguyễn Thị Mai Anh', N'Nữ', '0987654321'),
('KH002', N'Trần Văn Bình', N'Nam', '0987654322'),
('KH003', N'Lê Thị Cẩm Tú', N'Nữ', '0987654323'),
('KH004', N'Phạm Đức Duy', N'Nam', '0987654324'),
('KH005', N'Hoàng Thị E', N'Nữ', '0987654325'),
('KH006', N'Vũ Minh Phúc', N'Nam', '0987654326'),
('KH007', N'Đặng Ngọc Gia', N'Nữ', '0987654327'),
('KH008', N'Bùi Văn Hiếu', N'Nam', '0987654328'),
('KH009', N'Đỗ Thị Kỳ', N'Nữ', '0987654329'),
('KH010', N'Ngô Quang Lâm', N'Nam', '0987654330'),
('KH011', N'Dương Thị Mỹ Linh', N'Nữ', '0987654331'),
('KH012', N'Trịnh Văn Nam', N'Nam', '0987654332'),
('KH013', N'Lý Thị Ngọc', N'Nữ', '0987654333'),
('KH014', N'Chu Văn Phong', N'Nam', '0987654334'),
('KH015', N'Phan Thị Quỳnh', N'Nữ', '0987654335'),
('KH016', N'Võ Đức Sơn', N'Nam', '0987654336'),
('KH017', N'Mai Thị Thanh', N'Nữ', '0987654337'),
('KH018', N'Đinh Văn Thắng', N'Nam', '0987654338'),
('KH019', N'Lương Thị Thu', N'Nữ', '0987654339'),
('KH020', N'Quách Minh Tiến', N'Nam', '0987654340'),
('KH021', N'Triệu Thị Trang', N'Nữ', '0987654341'),
('KH022', N'Phùng Văn Trường', N'Nam', '0987654342'),
('KH023', N'Kiều Thị Tuyết', N'Nữ', '0987654343'),
('KH024', N'Tô Văn Tài', N'Nam', '0987654344'),
('KH025', N'Thạch Thị Uyên', N'Nữ', '0987654345'),
('KH026', N'Hà Văn Vinh', N'Nam', '0987654346'),
('KH027', N'Lữ Thị Xuân', N'Nữ', '0987654347'),
('KH028', N'Cao Văn Yên', N'Nam', '0987654348'),
('KH029', N'Trần Minh Anh', N'Nam', '0987654349'),
('KH030', N'Nguyễn Thị Bảo Ngọc', N'Nữ', '0987654350'),
('KH031', N'Lê Văn Cảnh', N'Nam', '0987654351'),
('KH032', N'Phạm Thị Diễm', N'Nữ', '0987654352'),
('KH033', N'Hoàng Đức Duy', N'Nam', '0987654353'),
('KH034', N'Đặng Thị Hạnh', N'Nữ', '0987654354'),
('KH035', N'Vũ Minh Hiếu', N'Nam', '0987654355'),
('KH036', N'Bùi Thị Kim', N'Nữ', '0987654356'),
('KH037', N'Ngô Văn Lộc', N'Nam', '0987654357'),
('KH038', N'Dương Thị Mai', N'Nữ', '0987654358'),
('KH039', N'Đào Văn Nam', N'Nam', '0987654359'),
('KH040', N'Vương Thị Ngân', N'Nữ', '0987654360'),
('KH041', N'Lâm Văn Phúc', N'Nam', '0987654361'),
('KH042', N'Trương Thị Quyên', N'Nữ', '0987654362'),
('KH043', N'Hồ Đức Sáng', N'Nam', '0987654363'),
('KH044', N'Châu Thị Thanh', N'Nữ', '0987654364'),
('KH045', N'Lưu Văn Thành', N'Nam', '0987654365'),
('KH046', N'Nguyễn Thị Thu Thủy', N'Nữ', '0987654366'),
('KH047', N'Trần Minh Trí', N'Nam', '0987654367'),
('KH048', N'Lê Thị Tuyết Trinh', N'Nữ', '0987654368'),
('KH049', N'Phạm Văn Tuấn', N'Nam', '0987654369'),
('KH050', N'Hoàng Thị Vân', N'Nữ', '0987654370'),
('KH051', N'Võ Minh Vương', N'Nam', '0987654371'),
('KH052', N'Đặng Thị Xuân', N'Nữ', '0987654372'),
('KH053', N'Bùi Văn Yên', N'Nam', '0987654373'),
('KH054', N'Ngô Thị Ánh', N'Nữ', '0987654374'),
('KH055', N'Dương Văn Bảo', N'Nam', '0987654375'),
('KH056', N'Lý Thị Cẩm Nhung', N'Nữ', '0987654376'),
('KH057', N'Chu Đình Dũng', N'Nam', '0987654377'),
('KH058', N'Phan Thị Hạnh', N'Nữ', '0987654378'),
('KH059', N'Vũ Minh Hoàng', N'Nam', '0987654379'),
('KH060', N'Mai Thị Hương', N'Nữ', '0987654380'),
('KH061', N'Đinh Văn Khải', N'Nam', '0987654381'),
('KH062', N'Lương Thị Lan', N'Nữ', '0987654382'),
('KH063', N'Quách Văn Minh', N'Nam', '0987654383'),
('KH064', N'Triệu Thị Ngọc', N'Nữ', '0987654384'),
('KH065', N'Phùng Đức Phát', N'Nam', '0987654385'),
('KH066', N'Kiều Thị Quế', N'Nữ', '0987654386'),
('KH067', N'Tô Văn Sơn', N'Nam', '0987654387'),
('KH068', N'Thạch Thị Thanh', N'Nữ', '0987654388'),
('KH069', N'Hà Văn Thắng', N'Nam', '0987654389'),
('KH070', N'Lữ Thị Thu', N'Nữ', '0987654390'),
('KH071', N'Cao Văn Tiến', N'Nam', '0987654391'),
('KH072', N'Trần Thị Trang', N'Nữ', '0987654392'),
('KH073', N'Nguyễn Văn Trường', N'Nam', '0987654393'),
('KH074', N'Lê Thị Tuyết', N'Nữ', '0987654394'),
('KH075', N'Phạm Đình Vũ', N'Nam', '0987654395'),
('KH076', N'Hoàng Thị Xuyến', N'Nữ', '0987654396'),
('KH077', N'Võ Minh Yến', N'Nam', '0987654397'),
('KH078', N'Đặng Thị Anh', N'Nữ', '0987654398'),
('KH079', N'Bùi Văn Bình', N'Nam', '0987654399'),
('KH080', N'Ngô Thị Cẩm', N'Nữ', '0987654400'),
('KH081', N'Dương Văn Dũng', N'Nam', '0987654401'),
('KH082', N'Lý Thị Diễm', N'Nữ', '0987654402'),
('KH083', N'Chu Minh Đức', N'Nam', '0987654403'),
('KH084', N'Phan Thị Hà', N'Nữ', '0987654404'),
('KH085', N'Vũ Văn Hải', N'Nam', '0987654405'),
('KH086', N'Mai Thị Hồng', N'Nữ', '0987654406'),
('KH087', N'Đinh Quang Khánh', N'Nam', '0987654407'),
('KH088', N'Lương Thị Lan', N'Nữ', '0987654408'),
('KH089', N'Quách Văn Minh', N'Nam', '0987654409'),
('KH090', N'Triệu Ngọc Nhi', N'Nữ', '0987654410'),
('KH091', N'Phùng Văn Phúc', N'Nam', '0987654411'),
('KH092', N'Kiều Thị Quỳnh', N'Nữ', '0987654412'),
('KH093', N'Tô Đình Sơn', N'Nam', '0987654413'),
('KH094', N'Thạch Thị Thanh', N'Nữ', '0987654414'),
('KH095', N'Hà Văn Thành', N'Nam', '0987654415'),
('KH096', N'Lữ Thị Thu', N'Nữ', '0987654416'),
('KH097', N'Cao Minh Tiến', N'Nam', '0987654417'),
('KH098', N'Trần Thị Trang', N'Nữ', '0987654418'),
('KH099', N'Nguyễn Văn Trung', N'Nam', '0987654419'),
('KH100', N'Lê Thị Tú', N'Nữ', '0987654420'),
('KH101', N'Phạm Đức Anh', N'Nam', '0987654421'),
('KH102', N'Hoàng Thị Bích', N'Nữ', '0987654422'),
('KH103', N'Võ Minh Cường', N'Nam', '0987654423'),
('KH104', N'Đặng Thị Diệu', N'Nữ', '0987654424'),
('KH105', N'Bùi Văn Đạt', N'Nam', '0987654425'),
('KH106', N'Ngô Thị Hạnh', N'Nữ', '0987654426'),
('KH107', N'Dương Minh Hiếu', N'Nam', '0987654427'),
('KH108', N'Lý Thị Kim', N'Nữ', '0987654428'),
('KH109', N'Chu Văn Long', N'Nam', '0987654429'),
('KH110', N'Phan Thị Mai', N'Nữ', '0987654430'),
('KH111', N'Vũ Đình Nam', N'Nam', '0987654431'),
('KH112', N'Mai Thị Ngân', N'Nữ', '0987654432'),
('KH113', N'Đinh Văn Phúc', N'Nam', '0987654433'),
('KH114', N'Lương Thị Quyên', N'Nữ', '0987654434'),
('KH115', N'Quách Minh Sáng', N'Nam', '0987654435'),
('KH116', N'Triệu Thị Thanh', N'Nữ', '0987654436'),
('KH117', N'Phùng Văn Thành', N'Nam', '0987654437'),
('KH118', N'Kiều Thị Thủy', N'Nữ', '0987654438'),
('KH119', N'Tô Đình Trí', N'Nam', '0987654439'),
('KH120', N'Thạch Thị Trinh', N'Nữ', '0987654440'),
('KH121', N'Hà Văn Tuấn', N'Nam', '0987654441'),
('KH122', N'Lữ Thị Vân', N'Nữ', '0987654442'),
('KH123', N'Cao Minh Vương', N'Nam', '0987654443'),
('KH124', N'Trần Thị Xuân', N'Nữ', '0987654444'),
('KH125', N'Nguyễn Văn Yên', N'Nam', '0987654445'),
('KH126', N'Lê Thị Ánh', N'Nữ', '0987654446'),
('KH127', N'Phạm Đình Bảo', N'Nam', '0987654447'),
('KH128', N'Hoàng Thị Cẩm', N'Nữ', '0987654448'),
('KH129', N'Võ Văn Dũng', N'Nam', '0987654449'),
('KH130', N'Đặng Thị Diễm', N'Nữ', '0987654450'),
('KH131', N'Bùi Minh Đức', N'Nam', '0987654451'),
('KH132', N'Ngô Thị Hà', N'Nữ', '0987654452'),
('KH133', N'Dương Văn Hải', N'Nam', '0987654453'),
('KH134', N'Lý Thị Hồng', N'Nữ', '0987654454'),
('KH135', N'Chu Quang Khánh', N'Nam', '0987654455'),
('KH136', N'Phan Thị Lan', N'Nữ', '0987654456'),
('KH137', N'Vũ Văn Minh', N'Nam', '0987654457'),
('KH138', N'Mai Thị Nhi', N'Nữ', '0987654458'),
('KH139', N'Đinh Văn Phúc', N'Nam', '0987654459'),
('KH140', N'Lương Thị Quỳnh', N'Nữ', '0987654460'),
('KH141', N'Quách Đình Sơn', N'Nam', '0987654461'),
('KH142', N'Triệu Thị Thanh', N'Nữ', '0987654462'),
('KH143', N'Phùng Văn Thành', N'Nam', '0987654463'),
('KH144', N'Kiều Thị Thu', N'Nữ', '0987654464'),
('KH145', N'Tô Minh Tiến', N'Nam', '0987654465'),
('KH146', N'Thạch Thị Trang', N'Nữ', '0987654466'),
('KH147', N'Hà Văn Trung', N'Nam', '0987654467'),
('KH148', N'Lữ Thị Tú', N'Nữ', '0987654468'),
('KH149', N'Cao Đức Anh', N'Nam', '0987654469'),
('KH150', N'Trần Thị Bích', N'Nữ', '0987654470'),
('KH151', N'Nguyễn Minh Cường', N'Nam', '0987654471'),
('KH152', N'Lê Thị Diệu', N'Nữ', '0987654472'),
('KH153', N'Phạm Văn Đạt', N'Nam', '0987654473'),
('KH154', N'Hoàng Thị Hạnh', N'Nữ', '0987654474'),
('KH155', N'Võ Minh Hiếu', N'Nam', '0987654475'),
('KH156', N'Đặng Thị Kim', N'Nữ', '0987654476'),
('KH157', N'Bùi Văn Long', N'Nam', '0987654477'),
('KH158', N'Ngô Thị Mai', N'Nữ', '0987654478'),
('KH159', N'Dương Đình Nam', N'Nam', '0987654479'),
('KH160', N'Lý Thị Ngân', N'Nữ', '0987654480');


INSERT INTO DanhMucThuoc (maDM, tenDM) VALUES
('DM001', N'Thuốc ho cảm cúm'),
('DM002', N'Thuốc bổ gan'),
('DM003', N'Thuốc hỗ trợ xương khớp'),
('DM004', N'Sữa'),
('DM005', N'Thuốc tiểu đường'),
('DM006', N'Thuốc tim mạch'),
('DM007', N'Thuốc hỗ trợ sức khỏe'),
('DM008', N'Thuốc xoang dị ứng');


--DonVi
INSERT INTO DonVi (maDV, tenDV) VALUES
('DV001', N'Viên'),
('DV002', N'Chai'),
('DV003', N'Tuýp'),
('DV004', N'Hộp'),
('DV005', N'Gói'),
('DV006', N'Xịt'),
('DV007', N'Máy'),
('DV008', N'Vòng'),
('DV009', N'Bút'),
('DV010', N'Kẹo'),
('DV011', N'Cao dán'),
('DV012', N'Viên đặt');

--Nhà cung cấp
INSERT INTO NhaCungCap (maNCC, tenNCC, diaChi, sDT) VALUES
('NCC001', N'Công ty TNHH Thực phẩm Sài Gòn', N'123 Nguyễn Văn Linh, Quận 7, TP.HCM', '02838271234'),
('NCC002', N'Công ty CP Vật liệu xây dựng Hà Nội', N'456 Trần Duy Hưng, Cầu Giấy, Hà Nội', '02438245678'),
('NCC003', N'Cửa hàng Điện máy Xanh Miền Bắc', N'789 Lê Văn Lương, Thanh Xuân, Hà Nội', '02435678901'),
('NCC004', N'Xưởng may mặc Phú Thái', N'12/34 Ngô Quyền, Đà Nẵng', '02363612345'),
('NCC005', N'Công ty TNHH Thiết bị y tế Việt Mỹ', N'45 Lý Thường Kiệt, Hoàn Kiếm, Hà Nội', '02438234567'),
('NCC006', N'Cửa hàng Văn phòng phẩm Hồng Hà', N'78 Lê Lợi, Hải Châu, Đà Nẵng', '02363567890'),
('NCC007', N'Công ty CP Thủy sản Miền Trung', N'Khu công nghiệp Hòa Khánh, Liên Chiểu, Đà Nẵng', '02363789123'),
('NCC008', N'Nhà phân phối Đồ gia dụng Sunhouse', N'234 Phan Đăng Lưu, Phú Nhuận, TP.HCM', '02838456789'),
('NCC009', N'Xưởng gỗ nội thất Minh Long', N'56 Nguyễn Tất Thành, Hải Phòng', '02253678901'),
('NCC010', N'Công ty TNHH Điện tử FPT', N'Lô 24, Công viên phần mềm Quang Trung, Q.12, TP.HCM', '02838901234'),
('NCC011', N'Cửa hàng Thiết bị vệ sinh Toàn Thắng', N'89 Trần Phú, Vinh, Nghệ An', '02383678901'),
('NCC012', N'Công ty CP Dệt may Hòa Thọ', N'KCN Hòa Khánh, Đà Nẵng', '02363890123'),
('NCC013', N'Nhà cung cấp Vật tư nông nghiệp An Giang', N'12 Nguyễn Trãi, Long Xuyên, An Giang', '02963890123'),
('NCC014', N'Công ty TNHH Thương mại Minh Phú', N'34 Lê Duẩn, Buôn Ma Thuột, Đắk Lắk', '02623890123'),
('NCC015', N'Đại lý Phân bón Hữu Nghị', N'78 Nguyễn Huệ, Thủ Dầu Một, Bình Dương', '02743890123'),
('NCC016', N'Công ty CP Thực phẩm dinh dưỡng Nutifood', N'281-283 Lý Thường Kiệt, P.15, Q.11, TP.HCM', '02839671234'),
('NCC017', N'Xưởng sản xuất Bao bì Nhựa Việt', N'Lô B5, KCN Biên Hòa 2, Đồng Nai', '02513890123'),
('NCC018', N'Cửa hàng Vật liệu xây dựng Hải Long', N'45 Trần Hưng Đạo, Nha Trang, Khánh Hòa', '02583890123'),
('NCC019', N'Công ty TNHH Thiết bị công nghiệp Sài Gòn', N'123 Lê Thánh Tôn, Q.1, TP.HCM', '02838234567'),
('NCC020', N'Nhà phân phối Sơn Kova chính hãng', N'56 Lê Văn Việt, Q.9, TP.HCM', '02838901234'),
('NCC021', N'Công ty CP Bia Sài Gòn - Miền Trung', N'1 Nguyễn Tất Thành, Đà Nẵng', '02363567890'),
('NCC022', N'Đại lý Gas Petrolimex Hà Nội', N'234 Trần Khát Chân, Hai Bà Trưng, Hà Nội', '02438245678'),
('NCC023', N'Cửa hàng Thiết bị điện Hải Phòng', N'78 Lạch Tray, Ngô Quyền, Hải Phòng', '02253678901'),
('NCC024', N'Công ty TNHH Vật tư y tế Bảo Ngọc', N'45 Nguyễn Chí Thanh, Đống Đa, Hà Nội', '02435678901'),
('NCC025', N'Xưởng sản xuất Đồ gỗ mỹ nghệ Bình Dương', N'KCN Mỹ Phước, Bến Cát, Bình Dương', '02743890123'),
('NCC026', N'Công ty CP Phân bón và Hóa chất Cần Thơ', N'Lô A1, KCN Trà Nóc, Cần Thơ', '02923890123'),
('NCC027', N'Nhà cung cấp Thủy hải sản Cửu Long', N'Chợ đầu mối Nông sản Thủ Đức, TP.HCM', '02838901234'),
('NCC028', N'Cửa hàng Vật tư nông nghiệp Tây Nguyên', N'12 Nguyễn Tất Thành, Pleiku, Gia Lai', '02693890123'),
('NCC029', N'Công ty TNHH Thiết bị văn phòng Hồng Đức', N'34 Lê Lợi, TP. Vũng Tàu', '02543890123'),
('NCC030', N'Xưởng may Thời trang Việt Tiến', N'KCN Vĩnh Lộc, Bình Chánh, TP.HCM', '02838901234'),
('NCC031', N'Công ty CP Vật liệu chống thấp Bình Minh', N'78 Lê Trọng Tấn, Thanh Xuân, Hà Nội', '02438245678'),
('NCC032', N'Nhà phân phối Sữa Vinamilk khu vực miền Nam', N'10 Tân Trào, Tân Phú, TP.HCM', '02838271234'),
('NCC033', N'Cửa hàng Thiết bị điện nước Hoàng Long', N'45 Nguyễn Văn Cừ, Ninh Kiều, Cần Thơ', '02923678901'),
('NCC034', N'Công ty TNHH Linh kiện điện tử Nhật Minh', N'Lô 25, KCN Hiệp Phước, Nhà Bè, TP.HCM', '02838901234'),
('NCC035', N'Đại lý Xăng dầu Dầu khí Miền Trung', N'123 Hùng Vương, Đà Nẵng', '02363612345'),
('NCC036', N'Xưởng sản xuất Nhựa Đồng Nai', N'KCN Biên Hòa 1, Đồng Nai', '02513890123'),
('NCC037', N'Công ty CP Thép Pomina', N'KCN Phú Mỹ 1, Bà Rịa - Vũng Tàu', '02543890123'),
('NCC038', N'Nhà cung cấp Vật tư xây dựng Đại Thành', N'34 Nguyễn Văn Linh, Đà Nẵng', '02363567890'),
('NCC039', N'Cửa hàng Thiết bị y tế Minh Tâm', N'78 Lý Thường Kiệt, Quy Nhơn, Bình Định', '02563890123'),
('NCC040', N'Công ty TNHH Bao bì Toàn Cầu', N'KCN Tân Tạo, Bình Tân, TP.HCM', '02838901234'),
('NCC041', N'Đại lý Phân bón NPK Bình Điền', N'12 Nguyễn Văn Cừ, Vĩnh Long', '02703890123'),
('NCC042', N'Xưởng sản xuất Đồ gỗ nội thất Hòa Phát', N'KCN Đồng Văn, Hà Nam', '02263890123'),
('NCC043', N'Công ty CP Vật liệu xây dựng Cosevco', N'45 Trường Chinh, Đống Đa, Hà Nội', '02438245678'),
('NCC044', N'Nhà phân phối Điện tử Samsung Việt Nam', N'Lô A1, KCN Yên Phong, Bắc Ninh', '02223890123'),
('NCC045', N'Cửa hàng Vật tư nông nghiệp Đồng Tháp', N'78 Nguyễn Sinh Sắc, Cao Lãnh, Đồng Tháp', '02773890123'),
('NCC046', N'Công ty TNHH Thiết bị công nghiệp Việt Nhật', N'123 Lê Văn Lương, Nhà Bè, TP.HCM', '02838901234'),
('NCC047', N'Xưởng may Xuất khẩu Thái Tuấn', N'KCN Tân Bình, TP.HCM', '02838271234'),
('NCC048', N'Đại lý Gas Petrolimex Đà Nẵng', N'34 Hàm Nghi, Đà Nẵng', '02363612345'),
('NCC049', N'Công ty CP Thực phẩm Acecook Việt Nam', N'KCN Tân Bình, TP.HCM', '02838234567'),
('NCC050', N'Nhà cung cấp Vật liệu chống cháy An Phúc', N'56 Lê Văn Khương, Q.12, TP.HCM', '02838901234'),
('NCC051', N'Công ty TNHH Thiết bị vệ sinh INAX', N'78 Lê Văn Lương, Thanh Xuân, Hà Nội', '02435678901'),
('NCC052', N'Xưởng sản xuất Đồ nhựa gia dụng Minh Khôi', N'KCN Tân Tạo, Bình Tân, TP.HCM', '02838901234'),
('NCC053', N'Đại lý Phân bón Đầu trâu Bình Điền', N'12 Nguyễn Văn Linh, Cần Thơ', '02923678901'),
('NCC054', N'Công ty CP Vật liệu xây dựng Hà Tiên', N'KCN Hiệp Phước, Nhà Bè, TP.HCM', '02838901234'),
('NCC055', N'Nhà phân phối Sơn Dulux khu vực miền Bắc', N'234 Trần Duy Hưng, Cầu Giấy, Hà Nội', '02438245678'),
('NCC056', N'Cửa hàng Thiết bị điện nước Thành Công', N'45 Nguyễn Văn Cừ, Vĩnh Long', '02703890123'),
('NCC057', N'Công ty TNHH Linh kiện máy tính FPT', N'Lô 24, Công viên phần mềm Quang Trung, Q.12, TP.HCM', '02838901234'),
('NCC058', N'Xưởng may Công nghiệp Việt Thắng', N'KCN Vĩnh Lộc, Bình Chánh, TP.HCM', '02838901234'),
('NCC059', N'Đại lý Xăng dầu Dầu khí Hải Phòng', N'78 Lạch Tray, Ngô Quyền, Hải Phòng', '02253678901'),
('NCC060', N'Công ty CP Thủy sản Minh Phú', N'KCN Trà Nóc, Cần Thơ', '02923890123'),
('NCC061', N'Nhà cung cấp Vật tư xây dựng Phúc Thịnh', N'34 Nguyễn Văn Linh, Đà Nẵng', '02363567890'),
('NCC062', N'Cửa hàng Thiết bị y tế Bảo Việt', N'45 Lý Thường Kiệt, Quy Nhơn, Bình Định', '02563890123'),
('NCC063', N'Công ty TNHH Bao bì Nhựa Tân Tiến', N'KCN Biên Hòa 2, Đồng Nai', '02513890123'),
('NCC064', N'Đại lý Phân bón Văn Điển', N'12 Nguyễn Trãi, Thanh Xuân, Hà Nội', '02438245678'),
('NCC065', N'Xưởng sản xuất Đồ gỗ mỹ nghệ Hải Long', N'KCN Mỹ Phước, Bến Cát, Bình Dương', '02743890123'),
('NCC066', N'Công ty CP Vật liệu xây dựng Vinaconex', N'Lô A1, KCN Yên Phong, Bắc Ninh', '02223890123'),
('NCC067', N'Nhà phân phối Điện tử LG Việt Nam', N'123 Lê Văn Lương, Nhà Bè, TP.HCM', '02838901234'),
('NCC068', N'Cửa hàng Vật tư nông nghiệp An Giang', N'78 Nguyễn Văn Cừ, Long Xuyên, An Giang', '02963890123'),
('NCC069', N'Công ty TNHH Thiết bị công nghiệp Đông Á', N'34 Lê Duẩn, Buôn Ma Thuột, Đắk Lắk', '02623890123'),
('NCC070', N'Xưởng may Xuất khẩu Nhật Bản', N'KCN Tân Bình, TP.HCM', '02838271234');


--Thuoc
INSERT INTO Thuoc (maThuoc, tenThuoc, maDonVi, maDM, maNCC, hanSuDung, giaNhap, giaBan, xuatXu) VALUES
('T001', N'Viên uống Milk Thistle Extract Puritan’s Pride 1000 mg, 180 viên', 'DV001', 'DM002', 'NCC001', '2026-12-31', 375994.40, 469993.00, N'Mỹ'),
('T002', N'Viên uống hỗ trợ chức năng gan Liver Hydrolysate của Nhật, 180 viên', 'DV001', 'DM002', 'NCC002', '2026-12-31', 683200.00, 854000.00, N'Nhật Bản'),
('T003', N'Viên uống thanh lọc, giải độc gan Liv 52 DS', 'DV001', 'DM002', 'NCC003', '2026-12-31', 135999.20, 169999.00, N'Việt Nam'),
('T004', N'Viên hỗ trợ cai rượu Kudzu Root Swanson của Mỹ', 'DV001', 'DM002', 'NCC004', '2026-12-31', 240000.00, 300000.00, N'Mỹ'),
('T005', N'Dung dịch uống Arkopharma Détoxifiant Hépatique của Pháp', 'DV002', 'DM002', 'NCC005', '2026-12-31', 374400.00, 468000.00, N'Pháp'),
('T006', N'Bột nghệ giải rượu Ukon No Chikara Nhật Bản', 'DV004', 'DM002', 'NCC006', '2026-12-31', 443200.00, 554000.00, N'Nhật Bản'),
('T007', N'Viên uống hỗ trợ thải độc gan Swisse Liver Detox Úc, 120 viên', 'DV001', 'DM002', 'NCC007', '2026-12-31', 334400.00, 418000.00, N'Úc'),
('T008', N'Nước tinh chất nghệ Matsukiyo Turmeric hỗ trợ giải rượu, mát gan', 'DV002', 'DM002', 'NCC008', '2026-12-31', 268000.00, 335000.00, N'Nhật Bản'),
('T009', N'Nước uống hỗ trợ mát gan giải rượu Condition Hàn Quốc, 100ml', 'DV002', 'DM002', 'NCC009', '2026-12-31', 47200.00, 59000.00, N'Hàn Quốc'),
('T010', N'Liver Detox Healthy Care 100 viên của Úc', 'DV001', 'DM002', 'NCC010', '2026-12-31', 270400.00, 338000.00, N'Úc'),
('T011', N'Zeria Hepalyse Hi Plus dạng nước hỗ trợ bổ gan của Nhật', 'DV002', 'DM002', 'NCC011', '2026-12-31', 596800.00, 746000.00, N'Nhật Bản'),
('T012', N'Viên uống giải độc gan Puritan''s Pride L-Arginine 1000mg', 'DV001', 'DM002', 'NCC012', '2026-12-31', 389600.00, 487000.00, N'Mỹ'),
('T013', N'Viên uống hỗ trợ gan Thompson''s One-A-Day Milk Thistle 42000mg', 'DV001', 'DM002', 'NCC013', '2026-12-31', 355200.00, 444000.00, N'Úc'),
('T014', N'Viên uống hỗ trợ giải độc gan Hewel 30 viên của Mỹ', 'DV001', 'DM002', 'NCC014', '2026-12-31', 222400.00, 278000.00, N'Mỹ'),
('T015', N'Viên uống giải rượu DHC Nhật Bản', 'DV001', 'DM002', 'NCC015', '2026-12-31', 270400.00, 338000.00, N'Nhật Bản'),
('T016', N'Viên uống Milk Thistle Blackmores của Úc, hộp 42 viên', 'DV001', 'DM002', 'NCC016', '2026-12-31', 271200.00, 339000.00, N'Úc'),
('T017', N'Viên uống hỗ trợ bổ gan Shijimi Orihiro của Nhật', 'DV001', 'DM002', 'NCC017', '2026-12-31', 456000.00, 570000.00, N'Nhật Bản'),
('T018', N'Nước uống hỗ trợ thải độc gan Milical Extra Artichaut Detox', 'DV002', 'DM002', 'NCC018', '2026-12-31', 260800.00, 326000.00, N'Pháp'),
('T019', N'Viên uống giải rượu CPillbox Kanpai Ukon Nhật Bản', 'DV001', 'DM002', 'NCC019', '2026-12-31', 120800.00, 151000.00, N'Nhật Bản'),
('T020', N'Viên uống hỗ trợ gan của Mỹ Olympian Labs Liver Detox', 'DV001', 'DM002', 'NCC020', '2026-12-31', 470400.00, 588000.00, N'Mỹ'),
('T021', N'Trà diếp cá hỗ trợ thải độc Orihiro Nhật Bản', 'DV005', 'DM002', 'NCC021', '2026-12-31', 136000.00, 170000.00, N'Nhật Bản'),
('T022', N'Viên uống giải độc gan GNC Milk Thistle 200mg của Mỹ', 'DV001', 'DM002', 'NCC022', '2026-12-31', 1300000.00, 1625000.00, N'Mỹ'),
('T023', N'Viên uống Swisse High Strength Milk Thistle của Úc', 'DV001', 'DM002', 'NCC023', '2026-12-31', 396000.00, 495000.00, N'Úc'),
('T024', N'Viên uống hỗ trợ chức năng gan Hepalyse Nhật Bản', 'DV001', 'DM002', 'NCC024', '2026-12-31', 600800.00, 751000.00, N'Nhật Bản'),
('T025', N'Viên uống hỗ trợ gan Hep-Forte 500 viên của Mỹ', 'DV001', 'DM002', 'NCC025', '2026-12-31', 1884800.00, 2356000.00, N'Mỹ'),
('T026', N'Viên uống hỗ trợ bổ gan Dr. Liver', 'DV001', 'DM002', 'NCC026', '2026-12-31', 876000.00, 1095000.00, N'Việt Nam'),
('T027', N'Viên uống hỗ trợ thải độc gan Thompson''s Liver Cleanse của Úc', 'DV001', 'DM002', 'NCC027', '2026-12-31', 449600.00, 562000.00, N'Úc'),
('T028', N'Viên uống cà gai leo Tuệ Linh hỗ trợ bảo vệ gan', 'DV001', 'DM002', 'NCC028', '2026-12-31', 145600.00, 182000.00, N'Việt Nam'),
('T029', N'Vitabiotics Liveril - Viên giải độc gan, tăng cường sức khỏe', 'DV001', 'DM002', 'NCC029', '2026-12-31', 392000.00, 490000.00, N'Vương quốc Anh'),
('T030', N'GNC Milk Thistle 1300mg - tăng cường chức năng gan 60 viên', 'DV001', 'DM002', 'NCC030', '2026-12-31', 1280800.00, 1601000.00, N'Mỹ'),
('T031', N'Viên uống hỗ trợ giải độc gan Tuệ Linh, lọ 60 viên', 'DV001', 'DM002', 'NCC031', '2026-12-31', 192000.00, 240000.00, N'Việt Nam'),
('T032', N'Viên uống hỗ trợ tăng cường chức năng gan Hamogan Tuệ Linh', 'DV001', 'DM002', 'NCC032', '2026-12-31', 184000.00, 230000.00, N'Việt Nam'),
('T033', N'Viên uống hỗ trợ giảm mỡ gan Cholessen, hộp 30 viên', 'DV001', 'DM002', 'NCC033', '2026-12-31', 104000.00, 130000.00, N'Việt Nam'),
('T034', N'Viên uống hỗ trợ tăng cường chức năng gan HB Arginmilk Plus', 'DV001', 'DM002', 'NCC034', '2026-12-31', 520000.00, 650000.00, N'Việt Nam'),
('T035', N'Funadin - hỗ trợ cho người men gan cao, giải độc gan', 'DV001', 'DM002', 'NCC035', '2026-12-31', 576000.00, 720000.00, N'Việt Nam'),
('T036', N'Viên bổ gan Gatomax Syn hỗ trợ tăng cường chức năng gan', 'DV001', 'DM002', 'NCC036', '2026-12-31', 148000.00, 185000.00, N'Việt Nam');

INSERT INTO Thuoc (maThuoc, tenThuoc, maDonVi, maDM, maNCC, hanSuDung, giaNhap, giaBan, xuatXu) VALUES
('T037', N'Viên uống Milk Thistle Extract Puritan’s Pride 1000 mg, 180 viên', 'DV001', 'DM002', 'NCC001', '2026-12-31', 375994.40, 469993.00, N'Mỹ'),
('T038', N'Viên hỗ trợ cai rượu Kudzu Root Swanson của Mỹ', 'DV001', 'DM002', 'NCC002', '2026-12-31', 240000.00, 300000.00, N'Mỹ'),
('T039', N'Viên uống hỗ trợ thải độc gan Swisse Liver Detox Úc, 120 viên', 'DV001', 'DM002', 'NCC003', '2026-12-31', 334400.00, 418000.00, N'Úc'),
('T040', N'Nước uống hỗ trợ mát gan giải rượu Condition Hàn Quốc, 100ml', 'DV002', 'DM002', 'NCC004', '2026-12-31', 47200.00, 59000.00, N'Hàn Quốc'),
('T041', N'Xịt Kirkland Aller-Flo của Mỹ', 'DV003', 'DM001', 'NCC005', '2026-12-31', 197600.00, 247000.00, N'Mỹ'),
('T042', N'Viên uống hỗ trợ bổ gan Genki Fami Kanzou Ukon Nhật Bản', 'DV001', 'DM002', 'NCC006', '2026-12-31', 620800.00, 776000.00, N'Nhật Bản'),
('T043', N'Lốc 6 chai Nước tinh chất nghệ Matsukiyo Turmeric hỗ trợ giải rượu, mát gan', 'DV002', 'DM002', 'NCC007', '2026-12-31', 268000.00, 335000.00, N'Nhật Bản'),
('T044', N'Viên uống Taisho Pabron Gold A chính hãng của Nhật', 'DV001', 'DM001', 'NCC008', '2026-12-31', 356800.00, 446000.00, N'Nhật Bản'),
('T045', N'Zeria Hepalyse Hi Plus dạng nước hỗ trợ bổ gan của Nhật', 'DV002', 'DM002', 'NCC009', '2026-12-31', 596800.00, 746000.00, N'Nhật Bản'),
('T046', N'Taisho Pabron Gold A Nhật Bản dạng bột uống', 'DV004', 'DM001', 'NCC010', '2026-12-31', 340800.00, 426000.00, N'Nhật Bản'),
('T047', N'Viên ngậm hỗ trợ giảm ho, say tàu xe Jintan Silver của Nhật', 'DV001', 'DM001', 'NCC011', '2026-12-31', 272000.00, 340000.00, N'Nhật Bản'),
('T048', N'Tinh chất Ivy Kids Úc 20ml cho trẻ sơ sinh và trẻ nhỏ', 'DV002', 'DM001', 'NCC012', '2026-12-31', 179200.00, 224000.00, N'Úc'),
('T049', N'Viên uống hỗ trợ chức năng gan Liver Hydrolysate của Nhật, 180 viên', 'DV001', 'DM002', 'NCC013', '2026-12-31', 683200.00, 854000.00, N'Nhật Bản'),
('T050', N'Liver Detox Healthy Care 100 viên của Úc', 'DV001', 'DM002', 'NCC014', '2026-12-31', 270400.00, 338000.00, N'Úc'),
('T051', N'Dung dịch uống Arkopharma Détoxifiant Hépatique của Pháp', 'DV002', 'DM002', 'NCC015', '2026-12-31', 374400.00, 468000.00, N'Pháp'),
('T052', N'Kẹo ngậm thảo mộc không đường Ricola Schweizer Kräuterzucker', 'DV001', 'DM001', 'NCC016', '2026-12-31', 127200.00, 159000.00, N'Thụy Sĩ'),
('T053', N'Nước uống hỗ trợ thải độc gan Milical Extra Artichaut Detox', 'DV002', 'DM002', 'NCC017', '2026-12-31', 260800.00, 326000.00, N'Pháp'),
('T054', N'Viên uống hỗ trợ giải độc gan Hewel 30 viên của Mỹ', 'DV001', 'DM002', 'NCC018', '2026-12-31', 222400.00, 278000.00, N'Mỹ'),
('T055', N'Viên uống hỗ trợ gan Hep-Forte 500 viên của Mỹ, 500 viên', 'DV001', 'DM002', 'NCC019', '2026-12-31', 1884800.00, 2356000.00, N'Mỹ'),
('T056', N'Siro hỗ trợ đường hô hấp trên Sinupret Saft Bionorica', 'DV002', 'DM001', 'NCC020', '2026-12-31', 304799.20, 380999.00, N'Đức'),
('T057', N'Viên uống Milk Thistle Blackmores của Úc, hộp 42 viên', 'DV001', 'DM002', 'NCC021', '2026-12-31', 271200.00, 339000.00, N'Úc'),
('T058', N'Siro Paburon S Chính Hãng Của Nhật 120ml', 'DV002', 'DM001', 'NCC022', '2026-12-31', 172800.00, 216000.00, N'Nhật Bản'),
('T059', N'Viên uống giải độc gan Puritan''s Pride L-Arginine 1000mg', 'DV001', 'DM002', 'NCC023', '2026-12-31', 389600.00, 487000.00, N'Mỹ'),
('T060', N'Viên uống hỗ trợ bổ gan Shijimi Orihiro của Nhật', 'DV001', 'DM002', 'NCC024', '2026-12-31', 456000.00, 570000.00, N'Nhật Bản'),
('T061', N'Viên uống hỗ trợ gan Thompson''s One-A-Day Milk Thistle 42000mg', 'DV001', 'DM002', 'NCC025', '2026-12-31', 355200.00, 444000.00, N'Úc'),
('T062', N'Viên uống giải rượu CPillbox Kanpai Ukon Nhật Bản', 'DV001', 'DM002', 'NCC026', '2026-12-31', 120800.00, 151000.00, N'Nhật Bản'),
('T063', N'Viên uống hỗ trợ gan của Mỹ Olympian Labs Liver Detox', 'DV001', 'DM002', 'NCC027', '2026-12-31', 470400.00, 588000.00, N'Mỹ'),
('T064', N'Siro Prospan Đức 100ml Cho Bé Chính Hãng', 'DV002', 'DM001', 'NCC028', '2026-12-31', 218400.00, 273000.00, N'Đức'),
('T065', N'Xịt họng Kobayashi Nhật Bản Chai 15ml', 'DV003', 'DM001', 'NCC029', '2026-12-31', 168000.00, 210000.00, N'Nhật Bản'),
('T066', N'Trà diếp cá hỗ trợ thải độc Orihiro Nhật Bản', 'DV005', 'DM002', 'NCC030', '2026-12-31', 136000.00, 170000.00, N'Nhật Bản'),
('T067', N'Viên uống Swisse High Strength Milk Thistle của Úc', 'DV001', 'DM002', 'NCC031', '2026-12-31', 396000.00, 495000.00, N'Úc'),
('T068', N'Siro Muhi Nhật Bản 120ml chính hãng', 'DV002', 'DM001', 'NCC032', '2026-12-31', 171999.20, 214999.00, N'Nhật Bản'),
('T069', N'Viên uống hỗ trợ chức năng gan Hepalyse Nhật Bản', 'DV001', 'DM002', 'NCC033', '2026-12-31', 600800.00, 751000.00, N'Nhật Bản'),
('T070', N'Viên uống giải độc gan GNC Milk Thistle 200mg của Mỹ', 'DV001', 'DM002', 'NCC034', '2026-12-31', 1300000.00, 1625000.00, N'Mỹ'),
('T071', N'Siro Ho Prospan Dạng Gói Của Đức 5ml', 'DV002', 'DM001', 'NCC035', '2026-12-31', 256000.00, 320000.00, N'Đức'),
('T072', N'Siro hỗ trợ giảm ho Fortuss Otosan của Ý', 'DV002', 'DM001', 'NCC036', '2026-12-31', 199200.00, 249000.00, N'Ý');

INSERT INTO Thuoc (maThuoc, tenThuoc, maDonVi, maDM, maNCC, hanSuDung, giaNhap, giaBan, xuatXu) VALUES
('T073', N'Viên uống hỗ trợ xương khớp Osteo Bi-flex Triple Strength của Mỹ', 'DV001', 'DM003', 'NCC001', '2026-12-31', 612000.00, 765000.00, N'Mỹ'),
('T074', N'Viên uống hỗ trợ bổ sung Calcium + D3 Kirkland của Mỹ', 'DV001', 'DM003', 'NCC002', '2026-12-31', 560800.00, 701000.00, N'Mỹ'),
('T075', N'Kirkland Glucosamine HCL 1500mg With MSM 1500mg hộp 375 viên, nắp Đỏ', 'DV001', 'DM003', 'NCC003', '2026-12-31', 457600.00, 572000.00, N'Mỹ'),
('T076', N'Viên uống hỗ trợ xương khớp Herbalife Xtra-Cal Advanced', 'DV001', 'DM003', 'NCC004', '2026-12-31', 320000.00, 400000.00, N'Mỹ'),
('T077', N'Viên uống hỗ trợ bổ sung Calcium + D3 Kirkland của Mỹ', 'DV001', 'DM003', 'NCC005', '2026-12-31', 560800.00, 701000.00, N'Mỹ'),
('T078', N'Viên uống Blackmores Glucosamine 1500mg của Úc', 'DV001', 'DM003', 'NCC006', '2026-12-31', 503199.20, 628999.00, N'Úc'),
('T079', N'Dầu lạnh xoa bóp Glucosamine Hàn Quốc, 150ml', 'DV002', 'DM003', 'NCC007', '2026-12-31', 57600.00, 72000.00, N'Hàn Quốc'),
('T080', N'Schiff Move Free Ultra Triple Action của Mỹ, hộp 75 viên', 'DV001', 'DM003', 'NCC008', '2026-12-31', 610400.00, 763000.00, N'Mỹ'),
('T081', N'Viên uống hỗ trợ xương khớp Osteo Bi-flex Triple Strength của Mỹ', 'DV001', 'DM003', 'NCC009', '2026-12-31', 612000.00, 765000.00, N'Mỹ'),
('T082', N'Viên uống hỗ trợ xương khớp Davinci Disc Discovery, 180 viên của Mỹ', 'DV001', 'DM003', 'NCC010', '2026-12-31', 1024800.00, 1281000.00, N'Mỹ'),
('T083', N'Sụn Vi Cá Mập Squalene Orihiro Nhật Bản', 'DV001', 'DM003', 'NCC011', '2026-12-31', 396000.00, 495000.00, N'Nhật Bản'),
('T084', N'Viên uống Kirkland Signature Calcium Citrate Magnesium And Zinc', 'DV001', 'DM003', 'NCC012', '2026-12-31', 476800.00, 596000.00, N'Mỹ'),
('T085', N'Combo 4 túi cao dán hồng sâm Himena của Hàn Quốc', 'DV005', 'DM003', 'NCC013', '2026-12-31', 96791.20, 120989.00, N'Hàn Quốc'),
('T086', N'Kem bôi hỗ trợ giảm đau xương khớp Flekosteel 50ml của Nga', 'DV002', 'DM003', 'NCC014', '2026-12-31', 173600.00, 217000.00, N'Nga'),
('T087', N'Glucosamine HCL 1500mg Healthy Care 400 viên của Úc', 'DV001', 'DM003', 'NCC015', '2026-12-31', 465600.00, 582000.00, N'Úc'),
('T088', N'Viên uống Glucosamine MSM Puritan''s Pride Của Mỹ', 'DV001', 'DM003', 'NCC016', '2026-12-31', 504800.00, 631000.00, N'Mỹ'),
('T089', N'Viên uống Schiff Move Free Advanced Triple Strength, 200 viên', 'DV001', 'DM003', 'NCC017', '2026-12-31', 616000.00, 770000.00, N'Mỹ'),
('T090', N'Viên uống bổ sung Canxi DHA Canxibone Gold của Mỹ', 'DV001', 'DM003', 'NCC018', '2026-12-31', 358400.00, 448000.00, N'Mỹ'),
('T091', N'Viên uống Herbalife Joint Support Advanced hỗ trợ xương khớp', 'DV001', 'DM003', 'NCC019', '2026-12-31', 640000.00, 800000.00, N'Mỹ'),
('T092', N'Viên uống hỗ trợ xương khớp Healthy Beauty HB Glucosamine 3 in 1', 'DV001', 'DM003', 'NCC020', '2026-12-31', 304000.00, 380000.00, N'Mỹ'),
('T093', N'Viên uống hỗ trợ xương khớp Swisse Calcium + Vitamin D', 'DV001', 'DM003', 'NCC021', '2026-12-31', 346400.00, 433000.00, N'Úc'),
('T094', N'Viên hỗ trợ xương khớp Doppelherz Aktiv Magnesium Calcium D3', 'DV001', 'DM003', 'NCC022', '2026-12-31', 288799.20, 360999.00, N'Đức'),
('T095', N'Glucosamine Orihiro 1500mg của Nhật Bản', 'DV001', 'DM003', 'NCC023', '2026-12-31', 423999.20, 529999.00, N'Nhật Bản'),
('T096', N'Viên uống hỗ trợ xương khớp Swisse Glucosamine Sulfate, 180 viên của Úc', 'DV001', 'DM003', 'NCC024', '2026-12-31', 498400.00, 623000.00, N'Úc'),
('T097', N'Viên uống Glucosamine 1500mg & Chondroitin 1200mg Kirkland của Mỹ', 'DV001', 'DM003', 'NCC025', '2026-12-31', 603999.20, 754999.00, N'Mỹ'),
('T098', N'Dầu nóng xoa bóp Yokoyoko Nhật Bản 80ml', 'DV002', 'DM003', 'NCC026', '2026-12-31', 180800.00, 226000.00, N'Nhật Bản'),
('T099', N'Viên uống hỗ trợ bổ xương khớp Arazo Nutrition Joint Support', 'DV001', 'DM003', 'NCC027', '2026-12-31', 683200.00, 854000.00, N'Mỹ'),
('T100', N'Viên uống hỗ trợ xương khớp Glucosamine Hyaluronic Acid Orihiro, 270 viên Nhật Bản', 'DV001', 'DM003', 'NCC028', '2026-12-31', 401600.00, 502000.00, N'Nhật Bản'),
('T101', N'Bengay Ultra Strength- Dầu xoa bóp nóng của Mỹ, 113g', 'DV002', 'DM003', 'NCC029', '2026-12-31', 340000.00, 425000.00, N'Mỹ'),
('T102', N'Viên uống hỗ trợ giảm đau vai gáy Arinamin EX Plus của Nhật Bản', 'DV001', 'DM003', 'NCC030', '2026-12-31', 736000.00, 920000.00, N'Nhật Bản'),
('T103', N'Viên uống hỗ trợ cải thiện gút Uricare JpanWell, 60 viên của Nhật Bản', 'DV001', 'DM003', 'NCC031', '2026-12-31', 879200.00, 1099000.00, N'Nhật Bản'),
('T104', N'Antiphlamine - Dầu nóng Hàn Quốc 100ml hỗ trợ giảm đau xương khớp', 'DV002', 'DM003', 'NCC032', '2026-12-31', 83200.00, 104000.00, N'Hàn Quốc'),
('T105', N'Viên uống Costar Blue Shark Cartilage của Úc', 'DV001', 'DM003', 'NCC033', '2026-12-31', 478400.00, 598000.00, N'Úc'),
('T106', N'Viên uống Blackmores Joint Formula Advanced của Úc', 'DV001', 'DM003', 'NCC034', '2026-12-31', 656000.00, 820000.00, N'Úc'),
('T107', N'Viên uống hỗ trợ xương khớp ZS của Nhật', 'DV001', 'DM003', 'NCC035', '2026-12-31', 1065599.20, 1331999.00, N'Nhật Bản'),
('T108', N'Viên uống Blackmores Glucosamine & Fish Oil hỗ trợ xương khớp, lọ 90 của Úc', 'DV001', 'DM003', 'NCC036', '2026-12-31', 470400.00, 588000.00, N'Úc');

INSERT INTO Thuoc (maThuoc, tenThuoc, maDonVi, maDM, maNCC, hanSuDung, giaNhap, giaBan, xuatXu) VALUES
('T109', N'Sữa Glucerna hương Vani Chính Hãng Của Úc 850g', 'DV004', 'DM005', 'NCC001', '2026-12-31', 744000.00, 930000.00, N'Úc'),
('T110', N'Sữa Glucerna hương Vani Chính Hãng Của Úc 850g', 'DV004', 'DM005', 'NCC002', '2026-12-31', 744000.00, 930000.00, N'Úc'),
('T111', N'Viên uống hỗ trợ tiểu đường Tokaijyo Nhật Bản', 'DV001', 'DM005', 'NCC003', '2026-12-31', 499200.00, 624000.00, N'Nhật Bản'),
('T112', N'Viên uống Nature Made Diabetes Health Pack chính hãng của Mỹ', 'DV001', 'DM005', 'NCC004', '2026-12-31', 719200.00, 899000.00, N'Mỹ'),
('T113', N'Viên uống hỗ trợ đường huyết Fujina Insuna', 'DV001', 'DM005', 'NCC005', '2026-12-31', 760000.00, 950000.00, N'Việt Nam'),
('T114', N'Sữa non dinh dưỡng cho người tiểu đường DiaSure', 'DV004', 'DM005', 'NCC006', '2026-12-31', 632000.00, 790000.00, N'Việt Nam'),
('T115', N'Viên uống hỗ trợ đường huyết Diafinol', 'DV001', 'DM005', 'NCC007', '2026-12-31', 495200.00, 619000.00, N'Việt Nam'),
('T116', N'Viên hỗ trợ người tiểu đường Glucoless 500mg hộp 90 viên của Úc', 'DV001', 'DM005', 'NCC008', '2026-12-31', 520000.00, 650000.00, N'Úc'),
('T117', N'BoniDiabet - Hỗ trợ phòng ngừa biến chứng bệnh tiểu đường', 'DV001', 'DM005', 'NCC009', '2026-12-31', 388000.00, 485000.00, N'Việt Nam'),
('T118', N'Viên uống Diabetna hỗ trợ đường huyết hộp 4 vỉ x10 viên nang', 'DV001', 'DM005', 'NCC010', '2026-12-31', 104000.00, 130000.00, N'Việt Nam'),
('T119', N'Đường nho Nga Remedia cho người tiểu đường', 'DV004', 'DM005', 'NCC011', '2026-12-31', 224000.00, 280000.00, N'Nga'),
('T120', N'Đường ăn kiêng Mivolis Substoff của Đức', 'DV004', 'DM005', 'NCC012', '2026-12-31', 132799.20, 165999.00, N'Đức'),
('T121', N'Viên hỗ trợ đường huyết JpanWell Medsulin Gold Nhật Bản', 'DV001', 'DM005', 'NCC013', '2026-12-31', 872000.00, 1090000.00, N'Nhật Bản'),
('T122', N'Viên uống Puritan''s Pride Diabetic Support Formula hỗ trợ người tiểu đường', 'DV001', 'DM005', 'NCC014', '2026-12-31', 364000.00, 455000.00, N'Mỹ'),
('T123', N'Viên uống Zafatek 100mg Nhật Bản', 'DV001', 'DM005', 'NCC015', '2026-12-31', 4400000.00, 5500000.00, N'Nhật Bản'),
('T124', N'Viên uống Herbal GlucoActive hỗ trợ cân bằng đường huyết', 'DV001', 'DM005', 'NCC016', '2026-12-31', 520000.00, 650000.00, N'Việt Nam'),
('T125', N'Viên uống Metgluco 250mg hỗ trợ kiểm soát đường huyết', 'DV001', 'DM005', 'NCC017', '2026-12-31', 796000.00, 995000.00, N'Việt Nam'),
('T126', N'Viên uống Zafatek 50mg hỗ trợ tiểu đường', 'DV001', 'DM005', 'NCC018', '2026-12-31', 2543199.20, 3178999.00, N'Nhật Bản'),
('T127', N'Đường ăn kiêng cỏ ngọt Hermesetas Stevia 75g', 'DV004', 'DM005', 'NCC019', '2026-12-31', 144000.00, 180000.00, N'Đức'),
('T128', N'Kem dưỡng ẩm Eucerin Aquaphor cho da khô nứt nẻ', 'DV002', 'DM005', 'NCC020', '2026-12-31', 264000.00, 330000.00, N'Đức'),
('T129', N'Sữa mầm gạo - Sữa cho người tiểu đường hộp 200g', 'DV004', 'DM005', 'NCC021', '2026-12-31', 240000.00, 300000.00, N'Việt Nam'),
('T130', N'Viên uống MPsuno hỗ trợ sức khỏe người tiểu đường', 'DV001', 'DM005', 'NCC022', '2026-12-31', 440000.00, 550000.00, N'Việt Nam'),
('T131', N'Đường ăn kiêng cỏ ngọt Hermesetas Stevia 75g', 'DV004', 'DM005', 'NCC023', '2026-12-31', 144000.00, 180000.00, N'Đức'),
('T132', N'Sữa đặc không đường Gloria cho người ăn kiêng, tiểu đường', 'DV004', 'DM005', 'NCC024', '2026-12-31', 164000.00, 205000.00, N'Việt Nam'),
('T133', N'Viên uống hỗ trợ trị tiểu đường Trunature Advanced Strength', 'DV001', 'DM005', 'NCC025', '2026-12-31', 599200.00, 749000.00, N'Mỹ'),
('T134', N'Viên uống dây thìa canh Khang Phú', 'DV001', 'DM005', 'NCC026', '2026-12-31', 60000.00, 75000.00, N'Việt Nam'),
('T135', N'Viên uống dây thìa canh hỗ trợ điều người tiểu đường DHC', 'DV001', 'DM005', 'NCC027', '2026-12-31', 496000.00, 620000.00, N'Nhật Bản'),
('T136', N'Đường ăn kiêng Hermesetas xanh nước biển 90g', 'DV004', 'DM005', 'NCC028', '2026-12-31', 184000.00, 230000.00, N'Đức'),
('T137', N'Trà dây thìa canh Lava hộp 30 túi cho người tiểu đường', 'DV005', 'DM005', 'NCC029', '2026-12-31', 40800.00, 51000.00, N'Việt Nam'),
('T138', N'Đường ăn kiêng Nutrinose hộp 500g cho người tiểu đường', 'DV004', 'DM005', 'NCC030', '2026-12-31', 280000.00, 350000.00, N'Việt Nam'),
('T139', N'Combo 3 hộp đường ăn kiêng Huxol cho người tiểu đường', 'DV004', 'DM005', 'NCC031', '2026-12-31', 224000.00, 280000.00, N'Đức'),
('T140', N'Jarrow Chromium GTF - Viên Uống Hỗ Trợ Cân Bằng Đường Huyết', 'DV001', 'DM005', 'NCC032', '2026-12-31', 256000.00, 320000.00, N'Mỹ'),
('T141', N'Bút tiêm Insulatard FlexPen 100IU/ml hỗ trợ đường huyết', 'DV009', 'DM005', 'NCC033', '2026-12-31', 236000.00, 295000.00, N'Đan Mạch'),
('T142', N'Viên uống Blood Sugar Nature''s Way hỗ trợ người tiểu đường', 'DV001', 'DM005', 'NCC034', '2026-12-31', 240000.00, 300000.00, N'Mỹ'),
('T143', N'Viên uống hỗ trợ bệnh thiếu máu Puritan''s Pride Folic Acid 400mg', 'DV001', 'DM005', 'NCC035', '2026-12-31', 196000.00, 245000.00, N'Mỹ'),
('T144', N'Đường ăn kiêng Nutrinose hộp 500g cho người tiểu đường', 'DV004', 'DM005', 'NCC036', '2026-12-31', 280000.00, 350000.00, N'Việt Nam');


INSERT INTO Thuoc (maThuoc, tenThuoc, maDonVi, maDM, maNCC, hanSuDung, giaNhap, giaBan, xuatXu) VALUES
('T145', N'Titan Gel Gold Cao Cấp của Nga cho nam', 'DV002', 'DM007', 'NCC001', '2026-12-31', 231200.00, 289000.00, N'Nga'),
('T146', N'Viên uống Puritan''s Pride Alpha Lipoic Acid 600mg của Mỹ', 'DV001', 'DM007', 'NCC002', '2026-12-31', 328800.00, 411000.00, N'Mỹ'),
('T147', N'Viên uống Anserine Minami hộp 240 viên của Nhật chính hãng', 'DV001', 'DM007', 'NCC003', '2026-12-31', 383200.00, 479000.00, N'Nhật Bản'),
('T148', N'Viên uống hỗ trợ bổ máu, lưu thông khí huyết Faichi Kobayashi', 'DV001', 'DM007', 'NCC004', '2026-12-31', 246400.00, 308000.00, N'Nhật Bản'),
('T149', N'Viên Uống Caruso’s Veins Clear Của Úc 60 viên', 'DV001', 'DM007', 'NCC005', '2026-12-31', 475200.00, 594000.00, N'Úc'),
('T150', N'Nước cốt hầm xương Hiroshi Nhật Bản', 'DV002', 'DM007', 'NCC006', '2026-12-31', 260000.00, 325000.00, N'Nhật Bản'),
('T151', N'Faz - Hỗ trợ điều hòa mỡ máu hộp 30 viên', 'DV001', 'DM007', 'NCC007', '2026-12-31', 320000.00, 400000.00, N'Việt Nam'),
('T152', N'Viên uống hỗ trợ cải thiện say tàu xe Anerol của Nhật', 'DV001', 'DM007', 'NCC008', '2026-12-31', 311199.20, 388999.00, N'Nhật Bản'),
('T153', N'Viên uống Blackmores Celery 3000mg chính hãng của Úc [Date T08/2025]', 'DV001', 'DM007', 'NCC009', '2026-12-31', 256776.00, 320970.00, N'Úc'),
('T154', N'Nấm thái dương xanh Orihiro Nhật Bản hộp 432 viên', 'DV001', 'DM007', 'NCC010', '2026-12-31', 563200.00, 704000.00, N'Nhật Bản'),
('T155', N'Viên uống Blackmores Executive B chính hãng của Úc', 'DV001', 'DM007', 'NCC011', '2026-12-31', 361600.00, 452000.00, N'Úc'),
('T156', N'Viên uống giảm mỡ máu và cholesterol Hisamitsu Nhật Bản', 'DV001', 'DM007', 'NCC012', '2026-12-31', 378400.00, 473000.00, N'Nhật Bản'),
('T157', N'Tinh nghệ hỗ trợ giải rượu Orihiro của Nhật túi 20 gói', 'DV005', 'DM007', 'NCC013', '2026-12-31', 142400.00, 178000.00, N'Nhật Bản'),
('T158', N'Viên sữa dê Healthy Care Goat’s Milk của Úc', 'DV001', 'DM007', 'NCC014', '2026-12-31', 240000.00, 300000.00, N'Úc'),
('T159', N'Viên uống Gout Uric Acid Complex 60 viên', 'DV001', 'DM007', 'NCC015', '2026-12-31', 940000.00, 1175000.00, N'Mỹ'),
('T160', N'Tinh chất nhung hươu Siberia hỗ trợ bồi bổ cơ thể', 'DV005', 'DM007', 'NCC016', '2026-12-31', 272800.00, 341000.00, N'Nga'),
('T161', N'Viên uống hỗ trợ hạ mỡ máu Sarafine Fujina Nhật Bản', 'DV001', 'DM007', 'NCC017', '2026-12-31', 684000.00, 855000.00, N'Nhật Bản'),
('T162', N'Keo ong Healthy Care Propolis 2000mg của Úc', 'DV001', 'DM007', 'NCC018', '2026-12-31', 402399.20, 502999.00, N'Úc'),
('T163', N'Hạt nêm Youki Nhật Bản hỗ trợ bổ sung dinh dưỡng cho bé', 'DV004', 'DM007', 'NCC019', '2026-12-31', 220000.00, 275000.00, N'Nhật Bản'),
('T164', N'Viên uống thanh lọc phổi Bioglan Lung Clear của Úc', 'DV001', 'DM007', 'NCC020', '2026-12-31', 440000.00, 550000.00, N'Úc'),
('T165', N'Viên bổ huyết Siberia hỗ trợ hoạt huyết, tăng cường sức khỏe', 'DV001', 'DM007', 'NCC021', '2026-12-31', 360000.00, 450000.00, N'Nga'),
('T166', N'Hamomax - Hỗ trợ giảm mỡ máu, bền thành mạch hộp 30 viên', 'DV001', 'DM007', 'NCC022', '2026-12-31', 176000.00, 220000.00, N'Việt Nam'),
('T167', N'Viên rau xanh Forever Fields of Green bổ sung chất xơ', 'DV001', 'DM007', 'NCC023', '2026-12-31', 400000.00, 500000.00, N'Mỹ'),
('T168', N'Siro Muhi 120ml của Nhật Bản chính hãng', 'DV002', 'DM007', 'NCC024', '2026-12-31', 197600.00, 247000.00, N'Nhật Bản'),
('T169', N'Rubina - Viên uống bổ máu, hỗ trợ cho người thiếu máu', 'DV001', 'DM007', 'NCC025', '2026-12-31', 683200.00, 854000.00, N'Việt Nam'),
('T170', N'Coquelusedal - Viên đặt cho trẻ từ sơ sinh tới 30 tháng', 'DV001', 'DM007', 'NCC026', '2026-12-31', 164000.00, 205000.00, N'Pháp'),
('T171', N'PediaKid Nez-Gorge - Siro Cho Bé Từ 6 Tháng', 'DV002', 'DM007', 'NCC027', '2026-12-31', 248000.00, 310000.00, N'Pháp'),
('T172', N'Xịt Vitatree của Úc chính hãng 25ml', 'DV003', 'DM007', 'NCC028', '2026-12-31', 151200.00, 189000.00, N'Úc'),
('T173', N'Sữa Meiji số 9 hộp 30 thanh (Nhật)', 'DV004', 'DM007', 'NCC029', '2026-12-31', 459200.00, 574000.00, N'Nhật Bản'),
('T174', N'Miếng dán hỗ trợ thải độc Kenko của Nhật', 'DV005', 'DM007', 'NCC030', '2026-12-31', 261600.00, 327000.00, N'Nhật Bản'),
('T175', N'Ancan phòng ngừa và hỗ trợ điều trị ung thư hiệu quả 60 viên', 'DV001', 'DM007', 'NCC031', '2026-12-31', 1000000.00, 1250000.00, N'Việt Nam'),
('T176', N'Niệu bảo - Hỗ trợ điều trị viêm đường tiết niệu hộp 20 viên', 'DV001', 'DM007', 'NCC032', '2026-12-31', 100000.00, 125000.00, N'Việt Nam'),
('T177', N'Ích Thận Vương hỗ trợ cải thiện chức năng thận', 'DV001', 'DM007', 'NCC033', '2026-12-31', 180000.00, 225000.00, N'Việt Nam'),
('T178', N'Đường nho Pháp GDL làm tào phớ gói 100g', 'DV004', 'DM007', 'NCC034', '2026-12-31', 120000.00, 150000.00, N'Pháp'),
('T179', N'Nước ép trái nhàu Noni juice bổ dưỡng, tốt cho sức khỏe', 'DV002', 'DM007', 'NCC035', '2026-12-31', 3264000.00, 4080000.00, N'Việt Nam'),
('T180', N'Bột chùm ngây Moringa tăng cường sức khỏe 100gr', 'DV004', 'DM007', 'NCC036', '2026-12-31', 87200.00, 109000.00, N'Việt Nam');

INSERT INTO Thuoc (maThuoc, tenThuoc, maDonVi, maDM, maNCC, hanSuDung, giaNhap, giaBan, xuatXu) VALUES
('T181', N'Xịt mũi Nazal Nhật Bản cho trẻ từ 7 tuổi', 'DV006', 'DM008', 'NCC001', '2026-12-31', 148000.00, 185000.00, N'Nhật Bản'),
('T182', N'Viên uống Chikunain Kobayashi của Nhật', 'DV001', 'DM008', 'NCC002', '2026-12-31', 472000.00, 590000.00, N'Nhật Bản'),
('T183', N'Xịt mũi AG Nhật Bản', 'DV006', 'DM008', 'NCC003', '2026-12-31', 400000.00, 500000.00, N'Nhật Bản'),
('T184', N'Sinus Plus - Hỗ trợ điều trị viêm xoang, viêm mũi dị ứng', 'DV001', 'DM008', 'NCC004', '2026-12-31', 244000.00, 305000.00, N'Việt Nam'),
('T185', N'Siro Brauer Runny nose của Úc cho bé từ 6 tháng', 'DV002', 'DM008', 'NCC005', '2026-12-31', 291200.00, 364000.00, N'Úc'),
('T186', N'Dung dịch nước muối xịt mũi cho người lớn Sterimar (50ml)', 'DV006', 'DM008', 'NCC006', '2026-12-31', 84000.00, 105000.00, N'Pháp'),
('T187', N'Kem bôi Homeoplasmine Salbe Tb Boiron', 'DV002', 'DM008', 'NCC007', '2026-12-31', 413600.00, 517000.00, N'Pháp'),
('T188', N'Nước Muối Sinh Lý Trị Nghẹt Mũi Little Remedies', 'DV002', 'DM008', 'NCC008', '2026-12-31', 175200.00, 219000.00, N'Mỹ'),
('T189', N'Thuốc nhỏ mũi Nasivin cho trẻ sơ sinh, trẻ nhỏ của Đức', 'DV002', 'DM008', 'NCC009', '2026-12-31', 184000.00, 230000.00, N'Đức'),
('T190', N'Happy xoang - Hỗ trợ điều trị xoang mũi hiệu quả', 'DV001', 'DM008', 'NCC010', '2026-12-31', 100000.00, 125000.00, N'Việt Nam'),
('T191', N'Kem thoa chống sổ mũi Tampei của Nhật (8g)', 'DV002', 'DM008', 'NCC011', '2026-12-31', 152000.00, 190000.00, N'Nhật Bản'),
('T192', N'Máy trị viêm mũi dị ứng BioNase', 'DV007', 'DM008', 'NCC012', '2026-12-31', 1240000.00, 1550000.00, N'Việt Nam'),
('T193', N'Máy trị viêm mũi dị ứng Medinose Pro của Đức', 'DV007', 'DM008', 'NCC013', '2026-12-31', 1512000.00, 1890000.00, N'Đức'),
('T194', N'Xịt hỗ trợ cải thiện nghẹt mũi, sổ mũi Fess Little Noses Spray', 'DV006', 'DM008', 'NCC014', '2026-12-31', 240000.00, 300000.00, N'Úc'),
('T195', N'Bảo khí khang giảm hen suyễn, viêm phế quản', 'DV001', 'DM008', 'NCC015', '2026-12-31', 160000.00, 200000.00, N'Việt Nam'),
('T196', N'Dung dịch vệ sinh mũi Chekat Adult cho người lớn', 'DV002', 'DM008', 'NCC016', '2026-12-31', 24000.00, 30000.00, N'Việt Nam');

INSERT INTO Thuoc (maThuoc, tenThuoc, maDonVi, maDM, maNCC, hanSuDung, giaNhap, giaBan, xuatXu) VALUES
('T197', N'Viên uống CoQ10 300mg Kirkland của Mỹ', 'DV001', 'DM006', 'NCC001', '2026-12-31', 536800.00, 671000.00, N'Mỹ'),
('T198', N'Bột uống Herbalife Niteworks hỗ trợ huyết áp và sức khỏe tim mạch', 'DV005', 'DM006', 'NCC002', '2026-12-31', 1208000.00, 1510000.00, N'Mỹ'),
('T199', N'Viên uống Orihiro Nattokinase 4000FU Nhật Bản', 'DV001', 'DM006', 'NCC003', '2026-12-31', 376800.00, 471000.00, N'Nhật Bản'),
('T200', N'Viên uống Doctor''s Best Nattokinase bổ tim mạch, hỗ trợ ngăn ngừa đột quỵ', 'DV001', 'DM006', 'NCC004', '2026-12-31', 382400.00, 478000.00, N'Mỹ'),
('T201', N'Viên uống Puritan''s Pride coq10 100mg chính hãng của Mỹ', 'DV001', 'DM006', 'NCC005', '2026-12-31', 353600.00, 442000.00, N'Mỹ'),
('T202', N'Viên uống Nature Made Cholest Off Plus của Mỹ', 'DV001', 'DM006', 'NCC006', '2026-12-31', 688000.00, 860000.00, N'Mỹ'),
('T203', N'Viên uống CoQ10 Blackmores 150mg của Úc hộp 30 viên', 'DV001', 'DM006', 'NCC007', '2026-12-31', 257600.00, 322000.00, N'Úc'),
('T204', N'Sụn Vi Cá Healthy Care 750mg Chính Hãng Của Úc', 'DV001', 'DM006', 'NCC008', '2026-12-31', 368000.00, 460000.00, N'Úc'),
('T205', N'Viên uống Healthy Care Grape Seed 58000', 'DV001', 'DM006', 'NCC009', '2026-12-31', 402400.00, 503000.00, N'Úc'),
('T206', N'Viên uống hỗ trợ tim mạch CoQ10 300mg Blackmores của Úc', 'DV001', 'DM006', 'NCC010', '2026-12-31', 292000.00, 365000.00, N'Úc'),
('T207', N'Viên uống hỗ trợ giảm đau, hạ sốt 81mg Kirkland của Mỹ', 'DV001', 'DM006', 'NCC011', '2026-12-31', 164000.00, 205000.00, N'Mỹ'),
('T208', N'Viên uống Blackmores Health hỗ trợ giảm mỡ máu của Úc', 'DV001', 'DM006', 'NCC012', '2026-12-31', 455200.00, 569000.00, N'Úc'),
('T209', N'Viên uống Nattokinase 2000FU Orihiro của Nhật', 'DV001', 'DM006', 'NCC013', '2026-12-31', 322400.00, 403000.00, N'Nhật Bản'),
('T210', N'Dầu hạt lanh Healthy Care Flaxseed Oil 1000mg của Úc', 'DV002', 'DM006', 'NCC014', '2026-12-31', 396000.00, 495000.00, N'Úc'),
('T211', N'Viên uống Healthy Care Wild Krill Oil 1000mg', 'DV001', 'DM006', 'NCC015', '2026-12-31', 439200.00, 549000.00, N'Úc'),
('T212', N'Viên uống hỗ trợ tim mạch Zifam ZiQ', 'DV001', 'DM006', 'NCC016', '2026-12-31', 140000.00, 175000.00, N'Việt Nam'),
('T213', N'Viên uống Nattokinase 3000Fu tốt cho tim mạch', 'DV001', 'DM006', 'NCC017', '2026-12-31', 460800.00, 576000.00, N'Việt Nam'),
('T214', N'Viên uống hỗ trợ tai biến NattoKinase Nano Premium 60000FU', 'DV001', 'DM006', 'NCC018', '2026-12-31', 2036000.00, 2545000.00, N'Việt Nam'),
('T215', N'Toma - Vòng Tay Hỗ Trợ Điều Hòa Huyết Áp Của Nhật Bản', 'DV008', 'DM006', 'NCC019', '2026-12-31', 1216000.00, 1520000.00, N'Nhật Bản'),
('T216', N'Toma - Vòng cổ hỗ trợ điều hòa huyết áp của Nhật', 'DV008', 'DM006', 'NCC020', '2026-12-31', 1472000.00, 1840000.00, N'Nhật Bản'),
('T217', N'Viên uống hỗ trợ chống oxy hóa Puritan''s Pride Pycnogenol 30 mg', 'DV001', 'DM006', 'NCC021', '2026-12-31', 262400.00, 328000.00, N'Mỹ'),
('T218', N'Neocell’s Resveratrol Antioxidant - Viên uống hỗ trợ tim mạch', 'DV001', 'DM006', 'NCC022', '2026-12-31', 428000.00, 535000.00, N'Mỹ'),
('T219', N'Noni extra - hỗ trợ cho bệnh xương khớp, tim mạch', 'DV001', 'DM006', 'NCC023', '2026-12-31', 1832000.00, 2290000.00, N'Việt Nam'),
('T220', N'Viên uống bổ tim mạch Healthy Care Grape seed Extract 12000 mg', 'DV001', 'DM006', 'NCC024', '2026-12-31', 640000.00, 800000.00, N'Úc'),
('T221', N'Viên uống bổ tim Mamori CoQ10 Nhật Bản', 'DV001', 'DM006', 'NCC025', '2026-12-31', 544000.00, 680000.00, N'Nhật Bản'),
('T222', N'Viên uống bổ tim mạch Puritan''s Pride coq10 120mg', 'DV001', 'DM006', 'NCC026', '2026-12-31', 360000.00, 450000.00, N'Mỹ'),
('T223', N'Viên uống Giảo cổ lam Tuệ Linh lọ 60 viên', 'DV001', 'DM006', 'NCC027', '2026-12-31', 118400.00, 148000.00, N'Việt Nam'),
('T224', N'CoQ10 200mg Nature Made - Viên uống hỗ trợ tim mạch', 'DV001', 'DM006', 'NCC028', '2026-12-31', 744800.00, 931000.00, N'Mỹ'),
('T225', N'Viên uống hỗ trợ phòng đột quỵ Noguchi Nattokinase Premium 4000FU', 'DV001', 'DM006', 'NCC029', '2026-12-31', 702400.00, 878000.00, N'Nhật Bản'),
('T226', N'Hato Gold Jpanwell - Viên uống hỗ trợ tim mạch', 'DV001', 'DM006', 'NCC030', '2026-12-31', 864000.00, 1080000.00, N'Nhật Bản'),
('T227', N'Viên uống Kyushin của Nhật tốt cho tim mạch', 'DV001', 'DM006', 'NCC031', '2026-12-31', 796000.00, 995000.00, N'Nhật Bản'),
('T228', N'Viên Uống Weider Red Yeast Rice Plus 1200mg Hỗ Trợ Tim Mạch', 'DV001', 'DM006', 'NCC032', '2026-12-31', 504000.00, 630000.00, N'Mỹ'),
('T229', N'Viên uống Kissei hỗ trợ hạ mỡ máu, cải thiện sức khỏe', 'DV001', 'DM006', 'NCC033', '2026-12-31', 720000.00, 900000.00, N'Nhật Bản'),
('T230', N'Viên bổ sung Coq10 hỗ trợ tim mạch Qunol Mega Coq10 Ubiquinol 100mg', 'DV001', 'DM006', 'NCC034', '2026-12-31', 676000.00, 845000.00, N'Mỹ'),
('T231', N'Viên uống Lipixgo hỗ trợ giảm mỡ máu', 'DV001', 'DM006', 'NCC035', '2026-12-31', 506400.00, 633000.00, N'Việt Nam'),
('T232', N'Bột uống Bios Life C Unicity hỗ trợ nâng cao sức khỏe', 'DV005', 'DM006', 'NCC036', '2026-12-31', 1416000.00, 1770000.00, N'Mỹ');



--Phiếu nhập hàng
INSERT INTO PhieuNhapHang (maPhieuNhap, maNCC, ngayNhap, maNV, tongTien) VALUES
('PN001', 'NCC023', '2023-01-05', 'NV001', 12500000.00),
('PN002', 'NCC045', '2023-01-07', 'NV002', 18450000.00),
('PN003', 'NCC012', '2023-01-10', 'NV003', 32780000.00),
('PN004', 'NCC037', '2023-01-12', 'NV004', 42150000.00),
('PN005', 'NCC008', '2023-01-15', 'NV005', 15620000.00),
('PN006', 'NCC029', '2023-01-18', 'NV006', 28930000.00),
('PN007', 'NCC003', '2023-01-20', 'NV007', 38760000.00),
('PN008', 'NCC017', '2023-01-22', 'NV008', 24500000.00),
('PN009', 'NCC041', '2023-01-25', 'NV009', 31240000.00),
('PN010', 'NCC005', '2023-01-28', 'NV010', 19870000.00),
('PN011', 'NCC031', '2023-02-01', 'NV001', 27650000.00),
('PN012', 'NCC014', '2023-02-03', 'NV002', 35420000.00),
('PN013', 'NCC026', '2023-02-05', 'NV003', 41280000.00),
('PN014', 'NCC009', '2023-02-08', 'NV004', 16790000.00),
('PN015', 'NCC038', '2023-02-10', 'NV005', 29340000.00),
('PN016', 'NCC001', '2023-02-12', 'NV006', 37610000.00),
('PN017', 'NCC020', '2023-02-15', 'NV007', 22560000.00),
('PN018', 'NCC048', '2023-02-18', 'NV008', 34170000.00),
('PN019', 'NCC007', '2023-02-20', 'NV009', 40320000.00),
('PN020', 'NCC034', '2023-02-22', 'NV010', 18530000.00),
('PN021', 'NCC016', '2023-02-25', 'NV001', 26780000.00),
('PN022', 'NCC042', '2023-02-27', 'NV002', 35940000.00),
('PN023', 'NCC028', '2023-03-02', 'NV003', 42870000.00),
('PN024', 'NCC011', '2023-03-05', 'NV004', 15460000.00),
('PN025', 'NCC039', '2023-03-08', 'NV005', 27890000.00),
('PN026', 'NCC004', '2023-03-10', 'NV006', 36520000.00),
('PN027', 'NCC022', '2023-03-12', 'NV007', 21540000.00),
('PN028', 'NCC050', '2023-03-15', 'NV008', 33260000.00),
('PN029', 'NCC010', '2023-03-18', 'NV009', 39680000.00),
('PN030', 'NCC030', '2023-03-20', 'NV010', 17250000.00),
('PN031', 'NCC019', '2023-03-22', 'NV001', 25670000.00),
('PN032', 'NCC044', '2023-03-25', 'NV002', 34830000.00),
('PN033', 'NCC027', '2023-03-28', 'NV003', 41750000.00),
('PN034', 'NCC013', '2023-03-30', 'NV004', 14320000.00),
('PN035', 'NCC040', '2023-04-02', 'NV005', 26480000.00),
('PN036', 'NCC006', '2023-04-05', 'NV006', 35210000.00),
('PN037', 'NCC024', '2023-04-08', 'NV007', 20570000.00),
('PN038', 'NCC049', '2023-04-10', 'NV008', 32390000.00),
('PN039', 'NCC002', '2023-04-12', 'NV009', 38960000.00),
('PN040', 'NCC035', '2023-04-15', 'NV010', 16380000.00),
('PN041', 'NCC018', '2023-04-18', 'NV001', 24750000.00),
('PN042', 'NCC046', '2023-04-20', 'NV002', 33740000.00),
('PN043', 'NCC025', '2023-04-22', 'NV003', 40630000.00),
('PN044', 'NCC015', '2023-04-25', 'NV004', 13570000.00),
('PN045', 'NCC043', '2023-04-28', 'NV005', 25390000.00),
('PN046', 'NCC021', '2023-05-02', 'NV006', 34680000.00),
('PN047', 'NCC032', '2023-05-05', 'NV007', 19560000.00),
('PN048', 'NCC047', '2023-05-08', 'NV008', 31520000.00),
('PN049', 'NCC033', '2023-05-10', 'NV009', 38270000.00),
('PN050', 'NCC036', '2023-05-12', 'NV010', 15240000.00),
('PN051', 'NCC023', '2023-05-15', 'NV001', 23100000.00),
('PN052', 'NCC045', '2023-05-18', 'NV002', 37450000.00),
('PN053', 'NCC012', '2023-05-20', 'NV003', 28960000.00),
('PN054', 'NCC037', '2023-05-22', 'NV004', 41230000.00),
('PN055', 'NCC008', '2023-05-25', 'NV005', 16780000.00),
('PN056', 'NCC029', '2023-05-28', 'NV006', 27640000.00),
('PN057', 'NCC003', '2023-05-30', 'NV007', 35890000.00),
('PN058', 'NCC017', '2023-06-02', 'NV008', 22450000.00),
('PN059', 'NCC041', '2023-06-05', 'NV009', 34120000.00),
('PN060', 'NCC005', '2023-06-08', 'NV010', 40360000.00),
('PN061', 'NCC031', '2023-06-10', 'NV001', 18270000.00),
('PN062', 'NCC014', '2023-06-12', 'NV002', 26530000.00),
('PN063', 'NCC026', '2023-06-15', 'NV003', 34780000.00),
('PN064', 'NCC009', '2023-06-18', 'NV004', 42650000.00),
('PN065', 'NCC038', '2023-06-20', 'NV005', 15490000.00),
('PN066', 'NCC001', '2023-06-22', 'NV006', 28370000.00),
('PN067', 'NCC020', '2023-06-25', 'NV007', 36240000.00),
('PN068', 'NCC048', '2023-06-28', 'NV008', 21580000.00),
('PN069', 'NCC007', '2023-06-30', 'NV009', 33460000.00),
('PN070', 'NCC034', '2023-07-03', 'NV010', 39720000.00),
('PN071', 'NCC016', '2023-07-05', 'NV001', 17350000.00),
('PN072', 'NCC042', '2023-07-08', 'NV002', 25760000.00),
('PN073', 'NCC028', '2023-07-10', 'NV003', 34910000.00),
('PN074', 'NCC011', '2023-07-12', 'NV004', 41840000.00),
('PN075', 'NCC039', '2023-07-15', 'NV005', 14620000.00),
('PN076', 'NCC004', '2023-07-18', 'NV006', 27280000.00),
('PN077', 'NCC022', '2023-07-20', 'NV007', 35370000.00),
('PN078', 'NCC050', '2023-07-22', 'NV008', 20590000.00),
('PN079', 'NCC010', '2023-07-25', 'NV009', 32540000.00),
('PN080', 'NCC030', '2023-07-28', 'NV010', 38410000.00),
('PN081', 'NCC019', '2023-07-30', 'NV001', 16270000.00),
('PN082', 'NCC044', '2023-08-02', 'NV002', 24630000.00),
('PN083', 'NCC027', '2023-08-05', 'NV003', 33850000.00),
('PN084', 'NCC013', '2023-08-08', 'NV004', 40790000.00),
('PN085', 'NCC040', '2023-08-10', 'NV005', 13740000.00),
('PN086', 'NCC006', '2023-08-12', 'NV006', 26370000.00),
('PN087', 'NCC024', '2023-08-15', 'NV007', 35120000.00),
('PN088', 'NCC049', '2023-08-18', 'NV008', 21680000.00),
('PN089', 'NCC002', '2023-08-20', 'NV009', 33650000.00),
('PN090', 'NCC035', '2023-08-22', 'NV010', 39280000.00),
('PN091', 'NCC018', '2023-08-25', 'NV001', 17160000.00),
('PN092', 'NCC046', '2023-08-28', 'NV002', 25420000.00),
('PN093', 'NCC025', '2023-08-30', 'NV003', 34690000.00),
('PN094', 'NCC015', '2023-09-02', 'NV004', 41530000.00),
('PN095', 'NCC043', '2023-09-05', 'NV005', 14850000.00),
('PN096', 'NCC021', '2023-09-08', 'NV006', 28160000.00),
('PN097', 'NCC032', '2023-09-10', 'NV007', 35980000.00),
('PN098', 'NCC047', '2023-09-12', 'NV008', 22570000.00),
('PN099', 'NCC033', '2023-09-15', 'NV009', 34210000.00),
('PN100', 'NCC036', '2023-09-18', 'NV010', 39450000.00);

--CTPhieuNhapHang

--Thue
INSERT INTO Thue (maThue, tenThue, mucThue) VALUES
('TH001', N'Thuế giá trị gia tăng thuốc tân dược', 5.00),
('TH002', N'Thuế thu nhập doanh nghiệp nhà thuốc', 4.50),
('TH003', N'Thuế môn bài nhà thuốc hạng 1', 2.00),
('TH004', N'Thuế bảo hiểm xã hội dược sĩ', 4.50),
('TH005', N'Thuế nhập khẩu dược phẩm', 4.00),
('TH006', N'Thuế dịch vụ tư vấn sức khỏe', 3.50),
('TH007', N'Thuế bảo vệ môi trường bao bì thuốc', 2.50),
('TH008', N'Thuế dịch vụ xét nghiệm tại nhà thuốc', 4.00),
('TH009', N'Thuế trang thiết bị y tế', 3.00),
('TH010', N'Thuế dịch vụ đo huyết áp', 2.00),
('TH011', N'Thuế dịch vụ chăm sóc sức khỏe', 3.50),
('TH012', N'Thuế vật tư y tế tiêu hao', 4.00),
('TH013', N'Thuế thực phẩm chức năng', 5.00),
('TH014', N'Thuế dược mỹ phẩm', 4.50),
('TH015', N'Thuế thiết bị y tế gia đình', 3.00),
('TH016', N'Thuế dịch vụ giao thuốc tận nhà', 3.50),
('TH017', N'Thuế thuốc đặc trị', 4.00),
('TH018', N'Thuế thuốc không kê đơn', 5.00),
('TH019', N'Thuế thuốc kê đơn', 3.50),
('TH020', N'Thuế dịch vụ tư vấn dược', 4.00);
--HoaDon
-- HD001
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD001', '2023-01-07', 1103550.00, 'NV001', 'KH001', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD001', 'T044', 2, 'DV001', 446000.00), ('HD001', 'T052', 1, 'DV001', 159000.00);

-- HD002
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD002', '2023-01-14', 1150800.00, 'NV002', 'KH002', 'TH013', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD002', 'T007', 1, 'DV001', 418000.00), ('HD002', 'T016', 2, 'DV001', 339000.00);

-- HD003
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD003', '2023-01-21', 1995000.00, 'NV003', 'KH003', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD003', 'T109', 1, 'DV001', 930000.00), ('HD003', 'T117', 2, 'DV001', 485000.00);

-- HD004
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD004', '2023-01-28', 2025450.00, 'NV004', 'KH004', 'TH013', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD004', 'T073', 1, 'DV001', 765000.00), ('HD004', 'T087', 2, 'DV001', 582000.00);

-- HD005
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD005', '2023-02-04', 1760850.00, 'NV005', 'KH005', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD005', 'T197', 1, 'DV001', 671000.00), ('HD005', 'T205', 2, 'DV001', 503000.00);

-- HD006
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD006', '2023-02-11', 740250.00, 'NV006', 'KH006', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD006', 'T058', 2, 'DV001', 216000.00), ('HD006', 'T064', 1, 'DV001', 273000.00);

-- HD007
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD007', '2023-02-18', 647850.00, 'NV007', 'KH007', 'TH013', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD007', 'T023', 2, 'DV001', 185000.00), ('HD007', 'T028', 1, 'DV001', 247000.00);

-- HD008
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD008', '2023-02-25', 1267347.90, 'NV008', 'KH008', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD008', 'T117', 1, 'DV001', 485000.00), ('HD008', 'T130', 2, 'DV001', 360999.00);

-- HD009
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD009', '2023-03-04', 1369197.90, 'NV009', 'KH009', 'TH013', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD009', 'T087', 1, 'DV001', 582000.00), ('HD009', 'T094', 2, 'DV001', 360999.00);

-- HD010
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD010', '2023-03-11', 1488900.00, 'NV010', 'KH010', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD010', 'T199', 2, 'DV001', 418000.00), ('HD010', 'T209', 1, 'DV001', 582000.00);

-- HD011
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD011', '2023-03-18', 921900.00, 'NV001', 'KH011', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD011', 'T044', 1, 'DV001', 446000.00), ('HD011', 'T058', 2, 'DV001', 216000.00);

-- HD012
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD012', '2023-03-25', 1071150.00, 'NV002', 'KH012', 'TH013', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD012', 'T007', 2, 'DV001', 418000.00), ('HD012', 'T023', 1, 'DV001', 185000.00);

-- HD013
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD013', '2023-10-07', 2332048.95, 'NV003', 'KH013', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD013', 'T109', 2, 'DV001', 930000.00), ('HD013', 'T130', 1, 'DV001', 360999.00);

-- HD014
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD014', '2023-10-14', 1739850.00, 'NV004', 'KH014', 'TH013', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD014', 'T073', 1, 'DV001', 765000.00), ('HD014', 'T105', 2, 'DV001', 446000.00);

-- HD015
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD015', '2023-10-21', 1848000.00, 'NV005', 'KH015', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD015', 'T197', 2, 'DV001', 671000.00), ('HD015', 'T199', 1, 'DV001', 418000.00);

-- HD016
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD016', '2023-10-28', 907200.00, 'NV006', 'KH016', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD016', 'T052', 2, 'DV001', 159000.00), ('HD016', 'T064', 2, 'DV001', 273000.00);

-- HD017
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD017', '2023-11-04', 874650.00, 'NV007', 'KH017', 'TH013', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD017', 'T016', 1, 'DV001', 339000.00), ('HD017', 'T028', 2, 'DV001', 247000.00);

-- HD018
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD018', '2023-11-11', 2385600.00, 'NV008', 'KH018', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD018', 'T109', 1, 'DV001', 930000.00), ('HD018', 'T135', 2, 'DV001', 671000.00);

-- HD019
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD019', '2023-11-18', 1690500.00, 'NV009', 'KH019', 'TH013', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD019', 'T087', 2, 'DV001', 582000.00), ('HD019', 'T105', 1, 'DV001', 446000.00);

-- HD020
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD020', '2023-11-25', 1750350.00, 'NV010', 'KH020', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD020', 'T205', 1, 'DV001', 503000.00), ('HD020', 'T209', 2, 'DV001', 582000.00);

-- HD021
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD021', '2023-12-02', 921900.00, 'NV001', 'KH001', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD021', 'T044', 1, 'DV001', 446000.00), ('HD021', 'T058', 2, 'DV001', 216000.00);

-- HD022
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD022', '2023-12-09', 906150.00, 'NV002', 'KH002', 'TH013', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD022', 'T016', 2, 'DV001', 339000.00), ('HD022', 'T023', 1, 'DV001', 185000.00);

-- HD023
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD023', '2023-12-16', 1397548.95, 'NV003', 'KH021', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD023', 'T117', 2, 'DV001', 485000.00), ('HD023', 'T130', 1, 'DV001', 360999.00);

-- HD024
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD024', '2023-12-23', 1369197.90, 'NV004', 'KH022', 'TH013', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD024', 'T087', 1, 'DV001', 582000.00), ('HD024', 'T094', 2, 'DV001', 360999.00);

-- HD025
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD025', '2023-12-30', 1667400.00, 'NV005', 'KH023', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD025', 'T205', 2, 'DV001', 503000.00), ('HD025', 'T209', 1, 'DV001', 582000.00);

-- HD026
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD026', '2023-01-06', 907200.00, 'NV006', 'KH024', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD026', 'T052', 2, 'DV001', 159000.00), ('HD026', 'T064', 2, 'DV001', 273000.00);

-- HD027
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD027', '2023-01-13', 957600.00, 'NV007', 'KH025', 'TH013', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD027', 'T007', 1, 'DV001', 418000.00), ('HD027', 'T028', 2, 'DV001', 247000.00);

-- HD028
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD028', '2023-01-20', 2385600.00, 'NV008', 'KH026', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD028', 'T109', 1, 'DV001', 930000.00), ('HD028', 'T135', 2, 'DV001', 671000.00);

-- HD029
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD029', '2023-01-27', 1739850.00, 'NV009', 'KH027', 'TH013', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD029', 'T073', 1, 'DV001', 765000.00), ('HD029', 'T105', 2, 'DV001', 446000.00);

-- HD030
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD030', '2023-02-03', 1848000.00, 'NV010', 'KH028', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD030', 'T197', 2, 'DV001', 671000.00), ('HD030', 'T199', 1, 'DV001', 418000.00);

-- HD031
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD031', '2023-02-10', 800100.00, 'NV001', 'KH029', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD031', 'T058', 1, 'DV001', 216000.00), ('HD031', 'T064', 2, 'DV001', 273000.00);

-- HD032
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD032', '2023-02-17', 744450.00, 'NV002', 'KH030', 'TH013', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD032', 'T016', 1, 'DV001', 339000.00), ('HD032', 'T023', 2, 'DV001', 185000.00);

-- HD033
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD033', '2023-02-24', 1267347.90, 'NV003', 'KH031', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD033', 'T117', 1, 'DV001', 485000.00), ('HD033', 'T130', 2, 'DV001', 360999.00);

-- HD034
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD034', '2023-03-03', 1690500.00, 'NV004', 'KH032', 'TH013', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD034', 'T087', 2, 'DV001', 582000.00), ('HD034', 'T105', 1, 'DV001', 446000.00);

-- HD035
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD035', '2023-03-10', 1488900.00, 'NV005', 'KH033', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD035', 'T199', 2, 'DV001', 418000.00), ('HD035', 'T209', 1, 'DV001', 582000.00);

-- HD036
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD036', '2023-03-17', 1103550.00, 'NV006', 'KH034', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD036', 'T044', 2, 'DV001', 446000.00), ('HD036', 'T052', 1, 'DV001', 159000.00);

-- HD037
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD037', '2023-03-24', 1150800.00, 'NV007', 'KH035', 'TH013', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD037', 'T007', 1, 'DV001', 418000.00), ('HD037', 'T016', 2, 'DV001', 339000.00);

-- HD038
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD038', '2023-03-31', 1995000.00, 'NV008', 'KH036', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD038', 'T109', 1, 'DV001', 930000.00), ('HD038', 'T117', 2, 'DV001', 485000.00);

-- HD039
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD039', '2023-10-06', 2025450.00, 'NV009', 'KH037', 'TH013', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD039', 'T073', 1, 'DV001', 765000.00), ('HD039', 'T087', 2, 'DV001', 582000.00);

-- HD040
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD040', '2023-10-13', 1760850.00, 'NV010', 'KH038', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD040', 'T197', 1, 'DV001', 671000.00), ('HD040', 'T205', 2, 'DV001', 503000.00);

-- HD041
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD041', '2023-10-20', 740250.00, 'NV001', 'KH039', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD041', 'T058', 2, 'DV001', 216000.00), ('HD041', 'T064', 1, 'DV001', 273000.00);

-- HD042
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD042', '2023-10-27', 647850.00, 'NV002', 'KH040', 'TH013', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD042', 'T023', 2, 'DV001', 185000.00), ('HD042', 'T028', 1, 'DV001', 247000.00);

-- HD043
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD043', '2023-11-03', 1397548.95, 'NV003', 'KH041', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD043', 'T117', 2, 'DV001', 485000.00), ('HD043', 'T130', 1, 'DV001', 360999.00);

-- HD044
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD044', '2023-11-10', 1369197.90, 'NV004', 'KH042', 'TH013', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD044', 'T087', 1, 'DV001', 582000.00), ('HD044', 'T094', 2, 'DV001', 360999.00);

-- HD045
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD045', '2023-11-17', 1667400.00, 'NV005', 'KH043', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD045', 'T205', 2, 'DV001', 503000.00), ('HD045', 'T209', 1, 'DV001', 582000.00);

-- HD046
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD046', '2023-11-24', 921900.00, 'NV006', 'KH044', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD046', 'T044', 1, 'DV001', 446000.00), ('HD046', 'T058', 2, 'DV001', 216000.00);

-- HD047
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD047', '2023-12-01', 906150.00, 'NV007', 'KH045', 'TH013', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD047', 'T016', 2, 'DV001', 339000.00), ('HD047', 'T023', 1, 'DV001', 185000.00);

-- HD048
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD048', '2023-12-08', 2385600.00, 'NV008', 'KH046', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD048', 'T109', 1, 'DV001', 930000.00), ('HD048', 'T135', 2, 'DV001', 671000.00);

-- HD049
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD049', '2023-12-15', 1739850.00, 'NV009', 'KH047', 'TH013', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD049', 'T073', 1, 'DV001', 765000.00), ('HD049', 'T105', 2, 'DV001', 446000.00);

-- HD050
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD050', '2023-12-22', 1848000.00, 'NV010', 'KH048', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD050', 'T197', 2, 'DV001', 671000.00), ('HD050', 'T199', 1, 'DV001', 418000.00);

-- HD051
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD051', '2023-12-29', 1103550.00, 'NV001', 'KH049', 'TH001', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD051', 'T044', 2, 'DV001', 446000.00), ('HD051', 'T052', 1, 'DV001', 159000.00);

-- HD052
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD052', '2023-01-05', 1150800.00, 'NV002', 'KH050', 'TH013', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD052', 'T007', 1, 'DV001', 418000.00), ('HD052', 'T016', 2, 'DV001', 339000.00);

-- HD053
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD053', '2023-01-12', 1995000.00, 'NV003', 'KH051', 'TH001', N'Chuyển khoản');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD053', 'T109', 1, 'DV001', 930000.00), ('HD053', 'T117', 2, 'DV001', 485000.00);

-- HD054
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD054', '2023-01-19', 2025450.00, 'NV004', 'KH052', 'TH013', N'Quẹt thẻ');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD054', 'T073', 1, 'DV001', 765000.00), ('HD054', 'T087', 2, 'DV001', 582000.00);

-- HD055
INSERT INTO HoaDon (maHoaDon, ngayLap, tongTien, maNV, maKH, maThue, hinhThucThanhToan) 
VALUES ('HD055', '2023-01-26', 1760850.00, 'NV005', 'KH053', 'TH001', N'Tiền mặt');
INSERT INTO CTHoaDon (maHoaDon, maThuoc, soLuong, maDonVi, giaBan) 
VALUES ('HD055', 'T197', 1, 'DV001', 671000.00), ('HD055', 'T205', 2, 'DV001', 503000.00);

--PhieuDatHang

--CTPhieuDatHang

--PhieuDoiHang

--CTPhieuDoiHang

--PhieuTraHang

--CTPhieuTraHang

--KeThuoc
INSERT INTO KeThuoc (maKe, tenKe, sucChuaToDa, soLuong) VALUES
('KE001', N'Kệ A', 100, 50),
('KE002', N'Kệ B', 100, 40),
('KE003', N'Kệ C', 100, 60),
('KE004', N'Kệ D', 100, 55),
('KE005', N'Kệ E', 100, 45),
('KE006', N'Kệ F', 100, 70),
('KE007', N'Kệ G', 100, 65),
('KE008', N'Kệ H', 100, 35),
('KE009', N'Kệ I', 100, 20),
('KE010', N'Kệ J', 100, 25),
('KE011', N'Kệ K', 100, 80),
('KE012', N'Kệ L', 100, 50),
('KE013', N'Kệ M', 100, 90),
('KE014', N'Kệ N', 100, 30),
('KE015', N'Kệ O', 100, 60);


--CTKeThuoc
INSERT INTO CTKeThuoc (maKe, maThuoc, soLuong, maDonVi, maDM, hanSuDung) VALUES
('KE001', 'T044', 20, 'DV001', 'DM001', '2025-12-31'),
('KE001', 'T052', 15, 'DV001', 'DM001', '2025-12-31'),
('KE001', 'T058', 15, 'DV001', 'DM001', '2025-12-31'),
('KE002', 'T052', 10, 'DV001', 'DM001', '2025-11-30'),
('KE002', 'T058', 15, 'DV001', 'DM001', '2025-11-30'),
('KE002', 'T064', 15, 'DV001', 'DM001', '2025-11-30'),
('KE003', 'T044', 25, 'DV001', 'DM001', '2025-10-31'),
('KE003', 'T058', 20, 'DV001', 'DM001', '2025-10-31'),
('KE003', 'T064', 15, 'DV001', 'DM001', '2025-10-31'),
('KE004', 'T007', 20, 'DV001', 'DM002', '2025-09-30'),
('KE004', 'T016', 15, 'DV001', 'DM002', '2025-09-30'),
('KE004', 'T023', 10, 'DV001', 'DM002', '2025-09-30'),
('KE004', 'T028', 10, 'DV001', 'DM002', '2025-09-30'),
('KE005', 'T007', 15, 'DV001', 'DM002', '2025-08-31'),
('KE005', 'T016', 20, 'DV001', 'DM002', '2025-08-31'),
('KE005', 'T028', 10, 'DV001', 'DM002', '2025-08-31'),
('KE006', 'T007', 25, 'DV001', 'DM002', '2025-07-31'),
('KE006', 'T016', 20, 'DV001', 'DM002', '2025-07-31'),
('KE006', 'T023', 15, 'DV001', 'DM002', '2025-07-31'),
('KE006', 'T028', 10, 'DV001', 'DM002', '2025-07-31'),
('KE007', 'T109', 20, 'DV001', 'DM003', '2026-06-30'),
('KE007', 'T117', 15, 'DV001', 'DM003', '2026-06-30'),
('KE007', 'T130', 15, 'DV001', 'DM003', '2026-06-30'),
('KE007', 'T135', 15, 'DV001', 'DM003', '2026-06-30'),
('KE008', 'T109', 15, 'DV001', 'DM003', '2026-05-31'),
('KE008', 'T117', 10, 'DV001', 'DM003', '2026-05-31'),
('KE008', 'T130', 10, 'DV001', 'DM003', '2026-05-31'),
('KE009', 'T117', 10, 'DV001', 'DM003', '2026-04-30'),
('KE009', 'T130', 5, 'DV001', 'DM003', '2026-04-30'),
('KE009', 'T135', 5, 'DV001', 'DM003', '2026-04-30'),
('KE010', 'T073', 10, 'DV001', 'DM004', '2026-03-31'),
('KE010', 'T087', 10, 'DV001', 'DM004', '2026-03-31'),
('KE010', 'T094', 5, 'DV001', 'DM004', '2026-03-31'),
('KE011', 'T073', 25, 'DV001', 'DM004', '2026-02-28'),
('KE011', 'T087', 20, 'DV001', 'DM004', '2026-02-28'),
('KE011', 'T094', 15, 'DV001', 'DM004', '2026-02-28'),
('KE011', 'T105', 20, 'DV001', 'DM004', '2026-02-28'),
('KE012', 'T087', 20, 'DV001', 'DM004', '2026-01-31'),
('KE012', 'T094', 15, 'DV001', 'DM004', '2026-01-31'),
('KE012', 'T105', 15, 'DV001', 'DM004', '2026-01-31'),
('KE013', 'T197', 25, 'DV001', 'DM005', '2026-12-31'),
('KE013', 'T199', 20, 'DV001', 'DM005', '2026-12-31'),
('KE013', 'T205', 25, 'DV001', 'DM005', '2026-12-31'),
('KE013', 'T209', 20, 'DV001', 'DM005', '2026-12-31'),
('KE014', 'T197', 10, 'DV001', 'DM005', '2026-11-30'),
('KE014', 'T205', 10, 'DV001', 'DM005', '2026-11-30'),
('KE014', 'T209', 10, 'DV001', 'DM005', '2026-11-30'),
('KE015', 'T197', 20, 'DV001', 'DM005', '2026-10-31'),
('KE015', 'T199', 15, 'DV001', 'DM005', '2026-10-31'),
('KE015', 'T205', 25, 'DV001', 'DM005', '2026-10-31');
