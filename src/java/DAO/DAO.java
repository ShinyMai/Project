/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Category;
import model.Product;

/**
 *
 * @author Shiny
 */
public class DAO  extends DBContext{
    
    public List<Product> getAllProduct() throws SQLException{
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[image]\n"
                + "      ,[price]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[cateID]\n"
                + "      ,[sell_ID]\n"
                + "  FROM [Wish].[dbo].[product]";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getDouble(4), 
                        rs.getString(5), 
                        rs.getString(6)));
            }        
        }catch (SQLException e){
            System.out.println(e);
        }     
        return list;    
    }
    
    public List<Category> getAllCategory() throws SQLException{
        List<Category> list = new ArrayList<>();
        String sql = "SELECT [cid]\n"
                + "      ,[cname]\n"
                + "  FROM [Wish].[dbo].[Category]";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Category(rs.getInt(1), 
                        rs.getString(2)));
            }        
        }catch (SQLException e){
            System.out.println(e);
        }     
        return list;    
    }
    
    public Product getLast(){
        String sql = "SELECT TOP 1 [id]\n"
                + "      ,[name]\n"
                + "      ,[image]\n"
                + "      ,[price]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[cateID]\n"
                + "      ,[sell_ID]\n"
                + "  FROM [Wish].[dbo].[product] order by id desc";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getDouble(4), 
                        rs.getString(5), 
                        rs.getString(6));
            }        
        }catch(SQLException e){
        
            }        
        return null;
    }
    
    public List<Product> getProductbyCID(String cid) throws SQLException{
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[image]\n"
                + "      ,[price]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[cateID]\n"
                + "      ,[sell_ID]\n"
                + "  FROM [Wish].[dbo].[product] where cateID = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getDouble(4), 
                        rs.getString(5), 
                        rs.getString(6)));
            }            
        }catch (SQLException e){
            System.out.println(e);
        }     
        return list;    
    }
    
    public Product getProductbyID(String id) throws SQLException{
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[image]\n"
                + "      ,[price]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[cateID]\n"
                + "      ,[sell_ID]\n"
                + "  FROM [Wish].[dbo].[product] where id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getDouble(4), 
                        rs.getString(5), 
                        rs.getString(6));
            }            
        }catch (SQLException e){
            System.out.println(e);
        }     
        return null;    
    }
    
    public List<Product> searchbyName(String txtSearch) throws SQLException{
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[image]\n"
                + "      ,[price]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[cateID]\n"
                + "      ,[sell_ID]\n"
                + "  FROM [Wish].[dbo].[product] where [name] LIKE ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getDouble(4), 
                        rs.getString(5), 
                        rs.getString(6)));
            }            
        }catch (SQLException e){
            System.out.println(e);
        }     
        return list;    
    }
    
    public Account login(String user, String pass) throws SQLException{
        String sql = "SELECT  [uID]\n"
                + "      ,[user]\n"
                + "      ,[pass]\n"
                + "      ,[isSell]\n"
                + "      ,[isAdmin]\n"
                + "  FROM [Wish].[dbo].[Account]\n"
                + "  WHERE [user] = ? and pass =?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,  user);
            ps.setString(2,  pass);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3),
                        rs.getInt(4), 
                        rs.getInt(5));
            }
        }catch(SQLException e){
        }
        return null;
    }
    
    public Account checkAccountExist(String user) throws SQLException{
        String sql = "SELECT  [uID]\n"
                + "      ,[user]\n"
                + "      ,[pass]\n"
                + "      ,[isSell]\n"
                + "      ,[isAdmin]\n"
                + "  FROM [Wish].[dbo].[Account]\n"
                + "  WHERE [user] = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,  user);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3),
                        rs.getInt(4), 
                        rs.getInt(5));
            }
        }catch(SQLException e){
        }
        return null;
    }
    
    public void signup(String user, String pass){
        String sql = "insert into Account\n"
                + "values (?,?,0,0)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,  user);
            ps.setString(2,  pass);
            ps.executeUpdate();
        }catch(SQLException e){
        }
    }
    
    public List<Product> getProductbySellID(int id) throws SQLException{
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[image]\n"
                + "      ,[price]\n"
                + "      ,[title]\n"
                + "      ,[description]\n"
                + "      ,[cateID]\n"
                + "      ,[sell_ID]\n"
                + "  FROM [Wish].[dbo].[product] where sell_ID = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt(1), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getDouble(4), 
                        rs.getString(5), 
                        rs.getString(6)));
            }            
        }catch (SQLException e){
            System.out.println(e);
        }     
        return list;    
    }
    
    public void deleteProduct(String pid){
        String sql = "Delete from product\n"
                + "where id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,  pid);
            ps.executeUpdate();
        }catch(SQLException e){
        }
    }
    
    public void insertProduct(String name, String image, String price, String title, String description, String category, int sid){
        String sql = "INSERT [dbo].[product] ([name], [image], [price], [title], [description], [cateID], [sell_ID]) \n"
                + "VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,  name);
            ps.setString(2,  image);
            ps.setString(3,  price);
            ps.setString(4,  title);
            ps.setString(5,  description);
            ps.setString(6,  category);
            ps.setInt(7,  sid);
            ps.executeUpdate();
        }catch(SQLException e){
        }
    }
    
    public void editProduct(String name, String image, String price, String title, String description, String category, String pid){
        String sql = "UPDATE  [dbo].[product] \n"
                + "set [name] = ?, \n"
                + "[image] = ?,\n"
                + " [price] = ?, \n"
                + "[title] = ?, \n"
                + "[description] = ?, \n"
                + "[cateID] = ? \n"
                + "WHERE id = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,  name);
            ps.setString(2,  image);
            ps.setString(3,  price);
            ps.setString(4,  title);
            ps.setString(5,  description);
            ps.setString(6,  category);
            ps.setString(7,  pid);
            ps.executeUpdate();
        }catch(SQLException e){
        }
    }
    
    //dem so san pham trong database
    public int getTotalAccount() throws SQLException{
        String sql = "select count(*) from product";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
            }catch(SQLException e){
             }
        return 0;
    }
    
    public static void main(String[] args) throws SQLException {
            DAO dao = new DAO();
            List<Product> list = dao.getAllProduct();
            for (Product o : list) {
                System.out.println(o);
        }
    }
}
