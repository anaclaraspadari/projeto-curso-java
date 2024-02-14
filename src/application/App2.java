package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App2 {
    public static void main(String[] args) {

        Department department=new Department(1,null);
        
        SellerDao sellerDao=DaoFactory.createSellerDao();

        System.out.println("====TEST 1: insert seller====");
        Seller newSeller=new Seller(null,"Bob","bob@mymail.com",new Date(), 4000.0,department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id="+newSeller.getId());
    }
}
