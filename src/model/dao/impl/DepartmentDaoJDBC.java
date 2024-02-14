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

import javax.swing.plaf.DesktopPaneUI;

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

    @Override
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
    
    @Override
    public Department findById(Integer id) {
        PreparedStatement st=null;
        ResultSet rs=null;
        try{
            st=conn.prepareStatement("select * from department where id=?");
            st.setInt(1,id);
            rs=st.executeQuery();
            if(rs.next()){
                Department obj=new Department();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                return obj;
            }else{
                return null;
            }
        }catch(Exception e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st=null;
        ResultSet rs=null;
        try{
            st=conn.prepareStatement("select * from department order by name");
            rs=st.executeQuery();

            List<Department> list=new ArrayList<>();
            while(rs.next()){
                Department obj=new Department();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                list.add(obj);
            }
            return list;
        }catch(Exception e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    
    @Override
    public void update(Department obj){
        PreparedStatement st=null;

        try{
            st=conn.prepareStatement("update department set name=? where id=?",Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        }catch(Exception e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }
}
