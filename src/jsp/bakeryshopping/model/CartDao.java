package jsp.bakeryshopping.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CartDao {
	private static CartDao instance = new CartDao();	
	public static CartDao getInstance() {
    	return instance;
    }   
    private CartDao() {}   
    private Connection getConnection() throws Exception {
      	Connection conn=null; 	 
      	Context init = new InitialContext();
     		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
     		conn = ds.getConnection();
         return conn;
    }    
    
    public int insertCart(Cart cart)      {
    	Connection conn = null;  PreparedStatement pstmt = null;
    	String sql="";           int result = 0;
        try {  conn = getConnection();
        
     // cart_no / customer_id / cart_count / cart_price / product_id
            sql = "insert into cart (cart_no,customer_id, cart_count,cart_price,product_id) values (cart_seq.nextval,?,?, "
               + "(select product_price from products where product_id=?) ,?)";
            pstmt = conn.prepareStatement(sql);            
            pstmt.setString(1, cart.getCustomer_id());
            pstmt.setInt(2, cart.getCart_count());
            pstmt.setInt(3, cart.getProduct_id());
            pstmt.setInt(4, cart.getProduct_id()); 
            
            result = pstmt.executeUpdate();
        }catch(Exception e) {System.out.println(e.getMessage());
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return result;
    }    
    
    public int getListCount(String id)  {
        Connection conn = null;  PreparedStatement pstmt = null;
        ResultSet rs = null;     int cnt = 0;
        String sql = "select count(*) from cart where customer_id=?";
        try { conn = getConnection();            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {   cnt = rs.getInt(1);	}
        } catch(Exception e) {System.out.println(e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return cnt;
    }	 
    
    public ArrayList<Cart> getCart(String id) {
   	 Connection conn = null;  PreparedStatement pstmt = null;
        ResultSet rs = null;     Cart cart=null;
        String sql = "select * from cart where customer_id = ?";
        ArrayList<Cart> lists = null;         
        try {	 conn = getConnection();        	 
            pstmt = conn.prepareStatement(sql);             
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();             
            lists = new ArrayList<Cart>();
            while (rs.next()) {
            	
         // cart_no / customer_id / cart_count / cart_price / product_id
           	 cart = new Cart();            	 
           	 cart.setCart_no(rs.getInt("cart_no"));
           	 cart.setCustomer_id(rs.getString("customer_id"));
           	 cart.setCart_count(rs.getInt("cart_count"));
           	 cart.setCart_price(rs.getInt("cart_price"));
           	 cart.setProduct_id(rs.getInt("product_id")); 
           	 
           	 lists.add(cart);
			 }
        }catch(Exception e) {System.out.println(e.getMessage());
        }finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		 return lists;
    } 
    
    
    public Cart getCarts(String id) {
      	 Connection conn = null;  PreparedStatement pstmt = null;
           ResultSet rs = null;     Cart cart=null;
           String sql = "select * from cart where customer_id = ?";       
           try {	 conn = getConnection();        	 
               pstmt = conn.prepareStatement(sql);             
               pstmt.setString(1, id);
               rs = pstmt.executeQuery();             
               while (rs.next()) {
               	
            // cart_no / customer_id / cart_count / cart_price / product_id
              	 cart = new Cart();            	 
              	 cart.setCart_no(rs.getInt("cart_no"));
              	 cart.setCustomer_id(rs.getString("customer_id"));
              	 cart.setCart_count(rs.getInt("cart_count"));
              	 cart.setCart_price(rs.getInt("cart_price"));
              	 cart.setProduct_id(rs.getInt("product_id")); 
   			 }
           }catch(Exception e) {System.out.println(e.getMessage());
           }finally {
               if (rs != null) try { rs.close(); } catch(SQLException ex) {}
               if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
               if (conn != null) try { conn.close(); } catch(SQLException ex) {}
           }
   		 return cart;
       } 
    
    public int deleteList(int cart_id)   {
        Connection conn = null;   
        PreparedStatement pstmt = null;  
        int result = 0;
        String sql = "delete from cart where cart_no = ?";
        try { conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cart_id);             
            result = pstmt.executeUpdate();
        }catch(Exception e) {System.out.println(e.getMessage());
        }finally {             
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return result;
    }
    
    public int deleteAll(String id)  {
        Connection conn = null;   int result = 0;
        PreparedStatement pstmt = null;  
        String sql = "delete from cart where customer_id=?";
        try { conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);             
           result = pstmt.executeUpdate();
        }catch(Exception e) {System.out.println(e.getMessage());
        }finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return result;
    }
    
    public int updateCount(int cart_no, int count)  {
   	 Connection conn = null;         int result = 0;
        PreparedStatement pstmt = null;  
        String sql = "update cart set cart_count=? where cart_no=?";
        try { conn = getConnection();            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, count);
            pstmt.setInt(2, cart_no);                        
            result = pstmt.executeUpdate();
        }catch(Exception e) {System.out.println(e.getMessage());
        }finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return result;
    }
}
