package application;

import java.sql.Connection;

import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class App {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		Connection conn=null;
		

		System.out.println("Nome:");
		String name=sc.nextLine();
		
		sc.close();
		
		PreparedStatement st=null;
		
		try {
			conn=DB.getConnection();
			
			st=conn.prepareStatement("insert into teste "+""
					+ "(nome)"
					+"values "
					+"(?)");
			st.setString(1,name);
			int rowsAffected=st.executeUpdate();

			System.out.println("DONE! Rows affected: "+rowsAffected);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
