package com.aliens.demorest;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AlienRepository {

	Connection con = null;

	public AlienRepository() {


		String url = "jdbc:sqlserver://DESKTOP-237OF0F;Database=RESTAPI;username=sa;password=krishna";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url);
            System.out.println(con);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Alien> getAliens() {
		List<Alien> aliens = new ArrayList<>();
		String sql = "Select  * from Aliens";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				aliens.add(a);
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return aliens;
	}

 public Alien getAlien(int id) 
 
 { 
	 Alien a=new Alien();
	 
	 String sql = "Select  * from Aliens where id="+id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
		if(rs.next()) {
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));	
		}
		
		} catch (Exception e) {
			System.out.print(e);
		}
	
   return a;
 }


	 public void createAlien(Alien al)
	 { 
		 String sql = "Insert into Aliens values(?,?,?)";
			try {
				PreparedStatement st =con.prepareStatement(sql) ;
				st.setInt(1, al.getId());
				st.setString(2, al.getName());
				st.setInt(3, al.getPoints());
				st.executeUpdate();
			
			} catch (Exception e) {
				System.out.print(e);
			}
	 }
	 

	 public void updateAlien(Alien al)
	 { 
		 String sql = "update aliens set name =?,points=? where id =? ";
			try {
				PreparedStatement st =con.prepareStatement(sql) ;
				st.setString(1, al.getName());
				st.setInt(2, al.getPoints());
				st.setInt(3, al.getId());
				st.executeUpdate();
			
			} catch (Exception e) {
				System.out.print(e);
			}
	 }
	 
	 
	 public String  killAlien(int id) 
	 
	 { 

		 String sql = "delete from Aliens where id=?";
			try {
				PreparedStatement st =con.prepareStatement(sql);
				st.setInt(1, id);
				st.executeUpdate();
			
			} catch (Exception e) {
				System.out.print(e);
			}
		
	   return "successfully deleted ";
	 }


	 
}
