<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="QLCongNhan.KetNoi"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tìm kiếm Công Nhân</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }
        h2, h3 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        .search-form {
            text-align: center;
            margin: 20px 0;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .search-form input[type="text"] {
            padding: 10px;
            width: 300px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-right: 10px;
        }
        .search-form button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .search-form button:hover {
            background-color: #45a049;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f8f8f8;
            color: #333;
            font-weight: bold;
        }
        td {
            background-color: #f9f9f9;
        }
        tr:nth-child(even) td {
            background-color: #f1f1f1;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .no-results {
            text-align: center;
            color: red;
            font-size: 18px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h2>Tìm kiếm thông tin Công Nhân</h2>

    <!-- Form tìm kiếm -->
    <div class="search-form">
        <form action="TimKiemCN_LeHoaiKhiem.jsp" method="get">
            <label for="hoten">Nhập tên công nhân:</label>
            <input type="text" id="hoten" name="hoten" placeholder="Nhập tên công nhân" value="${param.hoten}" required>
            <button type="submit">Tìm kiếm</button>
        </form>
    </div>


    <% 
        String hoTen = request.getParameter("hoten");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = KetNoi.KNCSDL();

            // Xử lý tìm kiếm nếu người dùng nhập tên công nhân
            if (hoTen != null && !hoTen.trim().isEmpty()) {
                String queryTimKiem = "SELECT * FROM CongNhan WHERE HoTen LIKE ?";
                ps = con.prepareStatement(queryTimKiem);
                ps.setString(1, "%" + hoTen + "%"); // Sử dụng LIKE để tìm kiếm theo tên công nhân
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    String ten = rs.getString("HoTen");
                    String gioiTinh = rs.getString("GioiTinh");
                    int soGioLamVuot = rs.getInt("SoGioLamVuot");
                    int tienThuong = soGioLamVuot >= 40 ? 500000 : (soGioLamVuot >= 30 ? 300000 : (soGioLamVuot >= 20 ? 200000 : 0));
    %>
    
    <h3>Danh sách công nhân tên: <%= hoTen %></h3>
    <table>
        <thead>
            <tr>
                <th>Mã CN</th>
                <th>Họ và tên</th>
                <th>Giới tính</th>
                <th>Số giờ làm vượt</th>
                <th>Tiền thưởng</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><%= rs.getString("MaCN") %></td>
                <td><%= ten %></td>
                <td><%= gioiTinh %></td>
                <td><%= soGioLamVuot %></td>
                <td><%= tienThuong > 0 ? tienThuong + " VNĐ" : "Không có thưởng" %></td>
            </tr>
        </tbody>
    </table>
    <% 
                } else { 
    %>
    <div class="no-results">Không tìm thấy công nhân với tên: <%= hoTen %></div>
    <% 
                }
            } 
            
            // Hiển thị danh sách toàn bộ công nhân
            String queryDanhSach = "SELECT * FROM CongNhan";
            ps = con.prepareStatement(queryDanhSach);
            rs = ps.executeQuery();
    %>

    <h3>DANH SÁCH CÔNG NHÂN</h3>
    <table>
        <thead>
            <tr>
                <th>Mã CN</th>
                <th>Họ và tên</th>
                <th>Giới tính</th>
                <th>Số giờ làm vượt</th>
                <th>Tiền thưởng</th>
            </tr>
        </thead>
        <tbody>
            <% 
                while (rs.next()) {
                    String maCN = rs.getString("MaCN");
                    String ten = rs.getString("HoTen");
                    String gioiTinh = rs.getString("GioiTinh");
                    int soGioLamVuot = rs.getInt("SoGioLamVuot");
                    
                    // Tính tiền thưởng dựa trên số giờ làm vượt
                    int tienThuong = soGioLamVuot >= 40 ? 500000 : (soGioLamVuot >= 30 ? 300000 : (soGioLamVuot >= 20 ? 200000 : 0));
            %>
            <tr>
                <td><%= maCN %></td>
                <td><%= ten %></td>
                <td><%= gioiTinh %></td>
                <td><%= soGioLamVuot %></td>
                <td><%= tienThuong > 0 ? tienThuong + " VNĐ" : "Không có thưởng" %></td>
            </tr>
            <% 
                } 
            %>
        </tbody>
    </table>

    <% 
        } catch (SQLException e) { 
    %>
    <div class="no-results">Lỗi kết nối cơ sở dữ liệu: <%= e.getMessage() %></div>
    <% 
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    %>
</body>
</html>
