package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
public class App {

        public static void main(String[] args) {

                DepartmentDao departmentDao=DaoFactory.createDepartmentDao();
                        
                // System.out.println("====TEST 1: insert department=====");

                // Department newDepartment=new Department(null,"Books");
                // departmentDao.insert(newDepartment);
                // System.out.println("Inserted! New id="+newDepartment.getId());

                // System.out.println("====TEST 2: find department by id=====");

                // Department dep=departmentDao.findById(1);
                // System.out.println(dep);

                // System.out.println("====TEST 3: find all departments=====");

                // List<Department> list=departmentDao.findAll();
                // for(Department obj : list){
                //         System.out.println(obj);
                // }
        }
}
