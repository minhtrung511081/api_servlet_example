package ecommerce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

@WebServlet(name = "getdata", urlPatterns = "/sinhvien")
public class Home extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "trung98N@");
			PreparedStatement pre = null;
			String id = req.getParameter("id");
			if(req.getParameter("id")==null) {
				String sql = "select * from sinhvien";
			    pre = conn.prepareStatement(sql);
			} else if (req.getParameter("id")!=null) {
				String sql = "select * from sinhvien where id = ?";
			    pre = conn.prepareStatement(sql);
				pre.setString(1, id);
			}
			ResultSet rs = pre.executeQuery();
			ArrayList<sinhvienModel> svlist = new ArrayList<sinhvienModel>();
			while (rs.next()) {
				sinhvienModel sv = new sinhvienModel();
				sv.setId(rs.getLong("id"));
				sv.setTen(rs.getString("ten")) ;
				sv.setLop(rs.getString("lop"));
				svlist.add(sv);
			} 
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			for( int i=0; i<= svlist.size()-1;i++) {
					PrintWriter out = resp.getWriter();
					out.print(svlist.get(i).getLop());
					out.print(" ");
					out.print(svlist.get(i).getTen());
					out.println("");
					
			}		
			PrintWriter out = resp.getWriter();
			String json = new Gson().toJson(svlist);
			out.print(json);
			out.flush();
			
			
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "trung98N@");
			String ten = req.getParameter("ten");
			String lop = req.getParameter("lop");
			String sql = "insert into sinhvien values (id,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, ten);
			pre.setString(2, lop);
			int rs = pre.executeUpdate();
			StringBuilder js = new StringBuilder();
			js.append("ban da them ten : " + ten);
			js.append(" lop: " + lop);
			resp.getWriter().print(js);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "trung98N@");
			String id = req.getParameter("id");
			String ten = req.getParameter("ten");
			String lop = req.getParameter("lop");
			String sql = "update sinhvien set ten=?, lop=? where id=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, ten);
			pre.setString(2, lop);
			pre.setString(3, id);
			int rs = pre.executeUpdate();
			StringBuilder js = new StringBuilder();
			js.append("Bạn đã sửa thành công");
			resp.getWriter().print(js);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "trung98N@");
			String ten = req.getParameter("ten");
			String sql = "delete from sinhvien where ten=?";
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1, ten);
			int rs = pre.executeUpdate();
			StringBuilder js = new StringBuilder();
			js.append("you delete successfully");
			resp.getWriter().print(js);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
