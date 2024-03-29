package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
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

        System.out.println("====TEST 2: find seller by id====");
        Seller seller=sellerDao.findById(2);
        System.out.println(seller);

        System.out.println("====TEST 3: find seller by department====");
        List<Seller> list=sellerDao.findByDepartment(department);
        for(Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("====TEST 4: find all sellers====");
        list=sellerDao.findAll();
        for(Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("=====TEST 5: updating seller data====");
        seller.setName("Bob Blue");
        seller.setEmail("bob_blue@mymail.com");
        sellerDao.update(seller);
        System.out.println("Finished update!");

        System.out.println("=====TEST 6: deleting seller data====");
        sellerDao.deleteById(2);
        System.out.println("Finished delete!");
    }
}
