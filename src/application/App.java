package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
public class App {

        public static void main(String[] args) {

                DepartmentDao departmentDao=DaoFactory.createDepartmentDao();
                        
                System.out.println("====TEST 1: insert department=====");

                Department newDepartment=new Department(null,"Books");
                departmentDao.insert(newDepartment);
                System.out.println("Inserted! New id="+newDepartment.getId());

                System.out.println("====TEST 2: find department by id=====");

                Department dep=departmentDao.findById(2);
                System.out.println(dep);

                System.out.println("====TEST 3: find all departments=====");

                List<Department> list=departmentDao.findAll();
                for(Department obj : list){
                        System.out.println(obj);
                }

                System.out.println("====TEST 4: update department data====");
                dep=departmentDao.findById(2);
                dep.setName("Clothes");
                departmentDao.update(dep);
                System.out.println("Finished update!");

                System.out.println("====TEST 5: delete department data====");
                departmentDao.deleteById(2);
                System.out.println("Finished delete!");
        }
}
