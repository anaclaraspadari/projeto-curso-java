package application;

import java.sql.Connection;
import java.util.Date;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import db.DB;
import model.entities.Department;
import model.entities.Seller;

public class App {

	public static void main(String[] args) {
		
        Department obj=new Department(1,"Books");

        Seller seller=new Seller(1,"Bob", "bob@gmail.com", new Date(), 3000.0, obj);

        SellerDao sellerDao=DaoFactory.createSellerDao();

        System.out.println(seller);
		
	}

}
