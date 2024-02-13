package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;

public class DaoFactory {
    public static DepartmentDaoJDBC createDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }
    public static SellerDaoJDBC createSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
}
