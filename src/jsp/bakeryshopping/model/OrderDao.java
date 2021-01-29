package jsp.bakeryshopping.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp.bakerymaster.model.Product;
import jsp.bakerymaster.model.ProductDao;

public class OrderDao {
	private static OrderDao instance = new OrderDao();	
	public static OrderDao getInstance() {
    	return instance;
    }   
    private OrderDao() {}   
    private Connection getConnection() throws Exception {
      	Connection conn=null; 	 
      	Context init = new InitialContext();
     		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
     		conn = ds.getConnection();
         return conn;
    }    
    
    public ArrayList<String> getAccount(){
    	Connection conn = null; PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<String> accountList = null;
        String sql = "select * from bank";
        try {conn = getConnection();                        
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();            
            accountList = new ArrayList<String>();            
            while (rs.next()) {
            	String account = new String(rs.getString("account")
            	   +" "+ rs.getString("bank")+" "+rs.getString("name"));
           	 	accountList.add(account);
			 }
        }catch(Exception e) {System.out.println(e.getMessage());
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return accountList;
    }
    
    public void insertOrder(ArrayList<Cart> lists,String id, String account, String deliveryName, String deliveryPhone,String deliveryAddress) 
    	    throws Exception {
    	    	Connection conn = null;  PreparedStatement pstmt = null;
    	        ResultSet rs = null;     
    	        String sql = ""; 
    	        String sql2 = "";
    	        int orderId = 0;       
    	        int nowCount ;
    	        try {
    	            conn = getConnection();

    	            sql = "select nvl(max(order_id),0) from orders";
    	            pstmt = conn.prepareStatement(sql);            
    	            rs = pstmt.executeQuery();
    	            if (rs.next()) orderId =  rs.getInt(1) + 1;
    	            // 여러 테미를 입력/수정/삭제 하기 때문에 모든 정리가 성공한 후에 commit
    	            conn.setAutoCommit(false);
    	            for(int i=0; i<lists.size();i++){
    	            	//해당 아이디에 대한 cart테이블 레코드를을 가져온후 buy테이블에 추가
    	            	Cart cart = (Cart)lists.get(i);
    	            	 ProductDao pd = ProductDao.getInstance();
    	               	 Product product  = pd.getProduct(cart.getProduct_id());
    	            	
    	            	sql = "insert into orders (order_id,customer_id,order_date,order_price,";
    	                sql += "order_state,account,deliveryName,deliveryPhone,deliveryAddress,order_count,product_id)";
    	                sql += " values (?,?,sysdate,?,0,?,?,?,?,?,?)";
    	                pstmt = conn.prepareStatement(sql);
    	                pstmt.setInt(1, orderId);
    	                pstmt.setString(2, id);
    	                pstmt.setInt(3, cart.getCart_price());
    	                pstmt.setString(4, account);
    	                pstmt.setString(5, deliveryName);
    	                pstmt.setString(6, deliveryPhone);
    	                pstmt.setString(7, deliveryAddress);
    	                pstmt.setInt(8, cart.getCart_count());
    	                pstmt.setInt(9, cart.getProduct_id());

    	                pstmt.executeUpdate();
    	                
    	                //상품이 구매되었으므로 book테이블의 상품수량을 재조정함
    	                pstmt.close();
    	                // 책 재고수량을 수량을 읽어서 
    	                pstmt = conn.prepareStatement("select product_stock from products where product_id=?");
    	                pstmt.setInt(1, cart.getProduct_id());
    	                rs = pstmt.executeQuery();
    	                rs.next(); 
    	                // 재고 - 판매수량 한후에 책의 재고 수정
    	                nowCount = rs.getInt(1) - product.getProduct_stock();                
    	                sql = "update products set product_stock=? where product_id=?";
    	                pstmt = conn.prepareStatement(sql);
    	            
    	                pstmt.setInt(1, nowCount);
    	    			pstmt.setInt(2, cart.getProduct_id());

    	                pstmt.executeUpdate(); 
    	                
    	    			sql2 = "update products set product_state=2 where product_id=? and product_stock=0";
    	    			pstmt = conn.prepareStatement(sql2);
    	    			pstmt.setInt(1, cart.getProduct_id());
    	    			pstmt.executeUpdate(); 
    	    			
    	                orderId++;
    	            }
    	            // 장바구니 비우기
    	            sql = "delete from cart where customer_id=?";
    	            pstmt = conn.prepareStatement(sql);
    	            pstmt.setString(1, id);
    	          
    	            pstmt.executeUpdate();
    	            conn.commit();
    	            conn.setAutoCommit(true);
    	         }catch(Exception e) {System.out.println(e.getMessage());
    	        } finally {
    	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
    	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	        }
    	    }
    
    public int getListCount(String id) {
        Connection conn = null;  PreparedStatement pstmt = null;
        ResultSet rs = null;     int count = 0;
        try { conn = getConnection();
            String sql = "select count(*) from orders where customer_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
               count = rs.getInt(1);
			}
        } catch(Exception e) { System.out.println(e.getMessage());
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return count ;
    }
    
    public int getListCount()
    	    throws Exception {
    	        Connection conn = null;
    	        PreparedStatement pstmt = null;
    	        ResultSet rs = null;

    	        int x=0;

    	        try {
    	            conn = getConnection();
    	            
    	            pstmt = conn.prepareStatement("select count(*) from orders");
    	            rs = pstmt.executeQuery();

    	            if (rs.next()) {
    	               x= rs.getInt(1);
    				}
    	        } catch(Exception ex) {
    	            ex.printStackTrace();
    	        } finally {
    	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
    	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
    	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	        }
    			return x;
    	    }
    
    public ArrayList<Order> getOrderList(String id)  {
   	    Connection conn = null;  PreparedStatement pstmt = null;
        ResultSet rs = null;     Order order=null;
        String sql = "";         
        ArrayList<Order> lists = null;        
        try { conn = getConnection();       	 
       	 sql = "select * from orders where customer_id = ? order by order_id";
            pstmt = conn.prepareStatement(sql);            
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();            
            lists = new ArrayList<Order>(); 
            while (rs.next()) {
            	 order = new Order();
           	 
            	 order.setOrder_id(rs.getInt("order_id"));
            	 order.setOrder_date(rs.getDate("order_date"));
            	 order.setOrder_price(rs.getInt("order_price"));
            	 order.setOrder_state(rs.getInt("order_state"));
            	 order.setCustomer_id(rs.getString("customer_id"));
            	 order.setOrder_count(rs.getInt("order_count"));
            	 order.setDeliveryAddress(rs.getString("deliveryAddress"));
            	 order.setDeliveryName(rs.getString("deliveryName"));
            	 order.setDeliveryPhone(rs.getString("deliveryPhone"));
            	 order.setAccount(rs.getNString("account"));
            	 order.setProduct_id(rs.getInt("product_id"));
            	 
           	 
           	     lists.add(order);
			 }
        }catch(Exception e) {System.out.println(e.getMessage());
        }finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		 return lists;
    }
    
		  public ArrayList<Order> getOrderList()    {
		   	    Connection conn = null;  PreparedStatement pstmt = null;
		        ResultSet rs = null;     Order order=null;
		        String sql = "select * from orders order by order_id desc";
		        ArrayList<Order> lists = null;        
		        try { conn = getConnection();
		            pstmt = conn.prepareStatement(sql);
		            rs = pstmt.executeQuery();            
		            lists = new ArrayList<Order>();
		            while (rs.next()) {
		            	 order = new Order();
		            	 
		            	 order.setOrder_id(rs.getInt("order_id"));
		            	 order.setOrder_date(rs.getDate("order_date"));
		            	 order.setOrder_price(rs.getInt("order_price"));
		            	 order.setOrder_state(rs.getInt("order_state"));
		            	 order.setCustomer_id(rs.getString("customer_id"));
		            	 order.setOrder_count(rs.getInt("order_count"));
		            	 order.setDeliveryAddress(rs.getString("deliveryAddress"));
		            	 order.setDeliveryName(rs.getString("deliveryName"));
		            	 order.setDeliveryPhone(rs.getString("deliveryPhone"));
		            	 order.setAccount(rs.getNString("account"));
		            	 order.setProduct_id(rs.getInt("product_id"));
		           	 
		           	     lists.add(order);
					 }
		        }catch(Exception e) {System.out.println(e.getMessage());
		        }finally {
		            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		        }
				 return lists;
		    }
    }
    
    
