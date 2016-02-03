/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBase {
    
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/db";
    private Connection con;

    public DataBase() {
        
      try {
          Class.forName(JDBC_DRIVER);
          con = DriverManager.getConnection(DB_URL,"root","");
          
      } 
       catch (ClassNotFoundException ex) {
        ex.printStackTrace();
      }
      catch (SQLException ex) {
          ex.printStackTrace();
      }
    }
    
    
    public String authontocation(String name ,String pass)//log in ok
    {
           String  result=null;
      try {
       
          PreparedStatement pre = con.prepareStatement("select * from users where Name=? and Password=? ");
          pre.setString(1,name);
          pre.setString(2,pass);
          ResultSet rset = pre.executeQuery();
          
          if(rset.next())
          {
             String pr= rset.getString("Priv");
             if(pr.equals("n"))
             {
               result="normal";
               
               System.out.println("normal");
             }
             if(pr.equals("a"))
             {
                result="admin";  
                System.out.println("admin");
             }   
          }
          else
          {
                result="notUser"; 
                System.out.println("notuser");
          }
         
      } 
      catch (SQLException ex) {
          ex.printStackTrace();
      }
       System.out.println("authontocation done");
      return result;
    }
    
   public void addUser(User u)//sign up ok m4kla id
    {
        
      try {
         PreparedStatement pre = con.prepareStatement("INSERT INTO users (Name,Email,Password,Phone,Gender,Nationality,DOB,Address,Credit,Job,Interest,Priv) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                 
                        
         	        pre.setString(1,u.getName());
                        pre.setString(2,u.getEmail());
                        pre.setString(3,u.getPassword());
                        pre.setString(4,u.getPhone());
                        pre.setString(5,u.getGender());
                        pre.setString(6,u.getNationality());
                        pre.setString(7,u.getdOB());
                        pre.setString(8,u.getAddress());
                        pre.setString(9,u.getCredit());
			pre.setString(10,u.getJob());
                        pre.setString(11,u.getInterest());
			pre.setString(12,u.getPriv());
                        //pre.setInt(13,u.getId());
                        pre.executeUpdate();
            System.out.println("user is added to data base"); 
          
         
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
    } 
   
   
   
   
  
    public void deleteUser(int idUser)//delete user ok
    {
         
      try {
         PreparedStatement pre = con.prepareStatement("DELETE FROM users WHERE id=?");
                 
                   
         	        pre.setInt(1,idUser);
                        pre.executeUpdate();
               System.out.println("delete user from data base"); 
          
         
      } catch (SQLException ex) {
         ex.printStackTrace();
      } 
    }
    
 
    
    
        public User infoUser(int idUser) //get information about user OK
    {
        
      User u= new User();
    
      try {
         
         Statement st=  con.createStatement();
         ResultSet rset=st.executeQuery("select * from users where id="+ idUser);
     
        
          while(rset.next()){
          
                       u.setId(rset.getInt("Id"));
         	       u.setName(rset.getString("Name"));
                       u.setEmail(rset.getString("Email"));
                       u.setPassword(rset.getString("Password"));
                       u.setPhone(rset.getString("Phone"));
                       u.setGender(rset.getString("Gender"));
                       u.setNationality(rset.getString("Nationality"));
                       u.setdOB(rset.getString("DOB"));
                       u.setAddress(rset.getString("Address"));
                       u.setCredit(rset.getString("Credit"));
		       u.setJob(rset.getString("Job"));
                       u.setInterest(rset.getString("Interest"));
		       u.setPriv(rset.getString("Priv"));
          }
          }
      catch (SQLException ex) {
          ex.printStackTrace();
                              }
        System.out.println(" user  info from database");   
       return u;
    }
        
          public void updateUser(User u) //update information about user ok
          {
        
      try {

                      Statement st=  con.createStatement();
                      PreparedStatement pre = con.prepareStatement("UPDATE users SET Name=?,Email=?,Password=?,"
                              + "Phone=?,Gender=?,Nationality=?,DOB=?,Address=?,Credit=?,Job=?,Interest=?,Priv=? WHERE Id=?");
                        
         	        pre.setString(1,u.getName());
                        pre.setString(2,u.getEmail());
                        pre.setString(3,u.getPassword());
                        pre.setString(4,u.getPhone());
                        pre.setString(5,u.getGender());
                        pre.setString(6,u.getNationality());
                        pre.setString(7,u.getdOB());
                        pre.setString(8,u.getAddress());
                        pre.setString(9,u.getCredit());
			pre.setString(10,u.getJob());
                        pre.setString(11,u.getInterest());
			pre.setString(12,u.getPriv());
                        pre.setInt(13,u.getId());
                        
                        pre.executeUpdate();
      } 
          
   
        catch (SQLException ex) {
          
      }     
          
      
    }    
 
              
   public void addItem(Product p)//add item ok m4kla id
    {
        
      try {
         PreparedStatement pre = con.prepareStatement("INSERT INTO product (Name,Price,Category,Description,Brand,Company) VALUES (?,?,?,?,?,?)");
                 
                        
         	        pre.setString(1,p.getName());
                        pre.setString(2,p.getPrice());
                        pre.setString(3,p.getCategory());
                        pre.setString(4,p.getDescription());
                        pre.setString(5,p.getBrand());
                        pre.setString(6,p.getCompany());
                        
                     
                        pre.executeUpdate();
            System.out.println("item is added to data base"); 
          
         
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
    } 
   
       public void deleteItem(int idItem)//delete item ok
    {
         
      try {
         PreparedStatement pre = con.prepareStatement("DELETE FROM product WHERE id=?");
                 
                   
         	        pre.setInt(1,idItem);
                        pre.executeUpdate();
               System.out.println("delete item from data base"); 
          
         
      } catch (SQLException ex) {
         ex.printStackTrace();
      } 
    }
       
       public Product infoItem(int idItem) //get information about item ok
    {
        
      Product p= new Product();
    
      try {
         
         Statement st=  con.createStatement();
         ResultSet rset=st.executeQuery("select * from product where id="+idItem);
     
        
          while(rset.next()){
          
                       p.setId(rset.getInt("id"));
                       p.setName(rset.getString("name"));
                       p.setPrice(rset.getString("price"));
                       p.setCategory(rset.getString("category")); 
                       p.setDescription(rset.getString("description"));
                       p.setBrand(rset.getString("brand"));
                       p.setCompany(rset.getString("company"));
                        
          }
          }
      catch (SQLException ex) {
          ex.printStackTrace();
                              }
        System.out.println(" item  info from database");   
       return p;
    }
 
          public void updateItem(Product p) //update information about item ok 
          {
        
      try {

                      Statement st=  con.createStatement();
                      PreparedStatement pre = con.prepareStatement("UPDATE product SET Name=?,Price=?,Category=?,"
                              +"Description=?,Brand=?,Company=? WHERE Id=?");
                        
         	        pre.setString(1,p.getName());
                        pre.setString(2,p.getPrice());
                        pre.setString(3,p.getCategory());
                      
                        pre.setString(4,p.getDescription());
                        pre.setString(5,p.getBrand());
                        pre.setString(6,p.getCompany());
                        pre.setInt(7,p.getId());
                       
                        
                        pre.executeUpdate();
                            System.out.println(" item  updated in database");  
      } 
          
   
        catch (SQLException ex) {
          
      }     
          
      
    }   
          public static void main( String args[])
          {
              User u = new User();
              u.setAddress("nasr street-cairo");
              u.setCredit("1000&");
              u.setEmail("w.ali.gmail.com");
              u.setdOB("1993-08-03");
              u.setPriv("n");
              u.setPhone("0122258865");
              u.setName("ali");
              u.setPassword("852");
              u.setNationality("egyption");
              u.setJob("doctor");
              u.setInterest("reading");
              u.setGender("M"); 
           
              
              Product p =new Product();
              
              p.setName("mobile");
              p.setPrice("1000");
              p.setDescription("descr hi");
              p.setCategory("mob");
              p.setBrand("soo");
              p.setCompany("sony comp");
              p.setId(11);
              
             DataBase d=  new DataBase();
       d.updateItem(p);
              
          }
          
       
}
