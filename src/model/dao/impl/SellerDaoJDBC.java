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
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
    private Connection conn;
    public SellerDaoJDBC(Connection conn){
        this.conn=conn;
    }

    public void insert(Seller obj){
        PreparedStatement st=null;

        try{
            st=conn.prepareStatement("insert into seller(name, email, birthdate,basesalary,department) values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5,obj.getDepartment().getId());

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

    public Seller findById(Integer id){
        PreparedStatement st=null;
        ResultSet rs=null;
        try{
            st=conn.prepareStatement("select seller.*, department.name as depName from seller inner join department on seller.department=department.id where seller.id=?");
            
            st.setInt(1,id);
            rs=st.executeQuery();
            if(rs.next()){
                Department dep=new Department();
                dep.setId(rs.getInt("id"));
                dep.setName(rs.getString("depName"));
                Seller obj=new Seller();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setEmail(rs.getString("email"));
                obj.setBirthDate(rs.getDate("birthdate"));
                obj.setBaseSalary(rs.getDouble("basesalary"));
                obj.setDepartment(dep);
                
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
}
