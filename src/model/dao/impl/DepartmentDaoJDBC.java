package model.dao.impl;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao{
    private Connection conn;
    public DepartmentDaoJDBC(Connection conn){
        this.conn=conn;
    }
    public void insert(Department obj){
        PreparedStatement st=null;

        try{
            st=conn.prepareStatement("insert into department(name) values (?)",Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            
            int rowsAffected=st.executeUpdate();

            if(rowsAffected>0){
                ResultSet rs=st.getGeneratedKeys();
                if(rs.next()){
                    int id=rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }else{
                throw new DbException("Unexpected error! No rows affected");
            }

        }catch(Exception e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }
}
