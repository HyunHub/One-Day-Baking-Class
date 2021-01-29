package jsp.bakerymaster.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsp.bakeryshopping.model.Cart;

public class ProductDao {
	 private static ProductDao instance;
	    public static ProductDao getInstance() {
	    	if (instance==null) {	instance = new ProductDao();  	}
	        return instance;
	    }    
	    private ProductDao() {} 
	    
	    private Connection getConnection() throws Exception {
	        Connection conn=null; 	 
	      	Context init = new InitialContext();
	     		DataSource ds = 
	     			(DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
	     		conn = ds.getConnection();
	         return conn;
	    }   
	    
	    public int masterCheck(String id, String passwd) {
			Connection conn = null;   PreparedStatement pstmt = null;
			ResultSet rs= null;       String dbpasswd="";		int x=-1;        
			try { conn = getConnection();            
	            pstmt = conn.prepareStatement(
	            	"select master_pw from master where master_id=?");
	            pstmt.setString(1, id);            
	            rs= pstmt.executeQuery();
				if(rs.next()){
					dbpasswd= rs.getString("master_pw"); 
					if(dbpasswd.equals(passwd))		x= 1; 
					else			x= 0; 
				}else				x= -1;		
	        } catch(Exception e) {System.out.println(e.getMessage());
	        } finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
			return x;
		}
	    
	    public int insertProduct(Product product) {
	        Connection conn = null;     int result = 0;
	        PreparedStatement pstmt = null;      
	        try {  conn = getConnection();
	            String str ="insert into products values ("
	            	+ "product_seq.nextval,?,?,?,?,?,?)";
	            pstmt = conn.prepareStatement(str);

	            pstmt.setString(1, product.getProduct_name());
	            pstmt.setInt(2, product.getProduct_price());
	            pstmt.setInt(3, product.getProduct_state());
	            pstmt.setInt(4, product.getProduct_stock());
	            pstmt.setString(5, product.getProduct_img());
				pstmt.setString(6, product.getProduct_content());
				
	            result = pstmt.executeUpdate();
	            
	        } catch(Exception e) { System.out.println(e.getMessage());
	           
	        } finally {
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
	        return result;
	    }
	    
	    public Product getProduct(int productId) {
	        Connection conn = null;     PreparedStatement pstmt = null;
	        ResultSet rs = null;        Product product =null; 
	        String sql = "select * from products where product_id = ?";
	        try { conn = getConnection();            
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, productId); rs = pstmt.executeQuery();
	            if (rs.next()) {
	            	product = new Product();   
	            	
	            	product.setProduct_id(rs.getInt("product_id"));
	            	product.setProduct_name(rs.getString("product_name"));
	            	product.setProduct_price(rs.getInt("product_price"));
	            	product.setProduct_state(rs.getInt("product_state"));
	            	product.setProduct_stock(rs.getInt("product_stock"));
	            	product.setProduct_img(rs.getString("product_img"));
	                product.setProduct_content(rs.getString("product_content"));

				}
	        } catch(Exception e) { System.out.println(e.getMessage());
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
			return product;
	    }
	    
		public int getProductCount()   {
	        Connection conn = null; 
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;    
	        int x = 0;
	        try { conn = getConnection();            
	            pstmt = conn.prepareStatement("select count(*) from products");
	            rs = pstmt.executeQuery();
	            if (rs.next()) {   
	            	x = rs.getInt(1);
	            }
	        } catch(Exception e) { System.out.println(e.getMessage());
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
			return x;
	    }
		
		public ArrayList<Product> getProducts() {
	        Connection conn = null;     
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;        
	        ArrayList<Product> productList=new ArrayList<Product>(); 
	        String sql="select * from products order by product_id desc";
	        try { conn = getConnection();           
	            	 pstmt = conn.prepareStatement(sql);
	        	rs = pstmt.executeQuery();
	        	while(rs.next()){

	        		Product product= new Product();
	        		product.setProduct_id(rs.getInt("product_id"));
	        		product.setProduct_name(rs.getString("product_name"));
	        		product.setProduct_price(rs.getInt("product_price"));
	        		product.setProduct_state(rs.getInt("product_state"));
	        		product.setProduct_stock(rs.getInt("product_stock"));
	        		product.setProduct_img(rs.getString("product_img"));
	        		product.setProduct_content(rs.getString("product_content"));
	                    
	                productList.add(product);
	        	}
	        } catch(Exception e) {System.out.println(e.getMessage());
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
			return productList;
	    }
		
		 public int deleteProduct(int productId)   {
		        Connection conn = null;  PreparedStatement pstmt = null;
		        int result = 0;  
		        String sql = "delete from products where product_id=?";
		        try { 
		        	conn = getConnection();
		            pstmt = conn.prepareStatement(sql);
		            pstmt.setInt(1, productId);            
		            result = pstmt.executeUpdate();            
		        } catch(Exception e) {System.out.println(e.getMessage()); 
		        } finally {           
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn  != null) try { conn.close();  } catch(SQLException ex) {}
		        }
		        return result;
		    }
		 
		 public int updateProduct(Product product, int productId) {
		        Connection conn = null;    PreparedStatement pstmt = null;
		        String sql, sql1;                int result = 0;        

		        try { conn = getConnection();            
		            sql = "update products set product_name=?,product_price=?,product_stock=?,product_img=?,product_content=?,product_state=? "; 
		            sql += " where product_id=?"; 
		            
		            sql1 = "update products set product_name=?,product_price=?,product_stock=?,product_content=?,product_state=? "; 
		            sql1 += " where product_id=?";  
		            if (product.getProduct_img()==null) {
		            	pstmt = conn.prepareStatement(sql1);
		            	pstmt.setString(1, product.getProduct_name());
		            	pstmt.setInt(2, product.getProduct_price());
		            	pstmt.setInt(3, product.getProduct_stock());
		            	pstmt.setString(4,product.getProduct_content());
		            	pstmt.setInt(5, product.getProduct_state());
		    			pstmt.setInt(6, productId);
		            } else {
		            	pstmt = conn.prepareStatement(sql);
		            	pstmt.setString(1, product.getProduct_name());
		            	pstmt.setInt(2, product.getProduct_price());
		            	pstmt.setInt(3, product.getProduct_stock());
		            	pstmt.setString(4, product.getProduct_img());
		            	pstmt.setString(5,product.getProduct_content());
		            	pstmt.setInt(6, product.getProduct_state());
		    			pstmt.setInt(7, productId);
		            }            
		            
		            result = pstmt.executeUpdate();
		            
		        } catch(Exception e) {System.out.println(e.getMessage());
		        } finally {
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		        }
		        return result;
		    }
		 

		 public Product[] getProducts(int count) {
		        Connection conn = null;  PreparedStatement pstmt = null;
		        ResultSet rs = null;
		        Product productList[]=null;       int i = 0;        
		        try { conn = getConnection();     
		       
		            pstmt = conn.prepareStatement(
		               "select * from "
		               + "(select * from products order by product_id desc) where rownum <=?");
		            pstmt.setInt(1, count);          
		        	rs = pstmt.executeQuery();
		            if (rs.next()) {
		                productList = new Product[count];
		                do{  Product product = new Product();
		                product.setProduct_id(rs.getInt("product_id"));
		        		product.setProduct_name(rs.getString("product_name"));
		        		product.setProduct_price(rs.getInt("product_price"));
		        		product.setProduct_state(rs.getInt("product_state"));
		        		product.setProduct_stock(rs.getInt("product_stock"));
		        		product.setProduct_img(rs.getString("product_img"));
		        		product.setProduct_content(rs.getString("product_content"));
		                     productList[i]=product;
		                     i++;
					    }while(rs.next());
					}
		        } catch(Exception e) { System.out.println(e.getMessage());  } finally {
		            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		        }
				return productList;
		    }
			public ArrayList<Product> getProducts(String product_id) {
				Connection conn = null;  PreparedStatement pstmt = null;
		        ResultSet rs = null;
		        ArrayList<Product> productList = new ArrayList<Product>();   
		        try { conn = getConnection();     
		       
		            pstmt = conn.prepareStatement(
		               "select * from "
		               + "products where product_id =? desc");
		            pstmt.setString(1, product_id);          
		        	rs = pstmt.executeQuery();
		            while(rs.next()) {
		            	Product product = new Product();
		                product.setProduct_id(rs.getInt("product_id"));
		        		product.setProduct_name(rs.getString("product_name"));
		        		product.setProduct_price(rs.getInt("product_price"));
		        		product.setProduct_state(rs.getInt("product_state"));
		        		product.setProduct_stock(rs.getInt("product_stock"));
		        		product.setProduct_img(rs.getString("product_img"));
		        		product.setProduct_content(rs.getString("product_content"));

		        		productList.add(product);
					    
					}
		        } catch(Exception e) { System.out.println(e.getMessage());  } finally {
		            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		        }
				return productList;
		    }
			
			public ArrayList<Product> getProducts2(String id) {
		      	 Connection conn = null;  PreparedStatement pstmt = null;
		           ResultSet rs = null;     Cart cart= null;
		           Product product = null;
		           String sql = "select * from products where product_id="
		        		   +"(select product_id from cart where customer_id = ?)";
		           ArrayList<Product> lists = null;         
		           try {	 conn = getConnection();        	 
		               pstmt = conn.prepareStatement(sql);             
		               pstmt.setString(1, id);
		               rs = pstmt.executeQuery();             
		               lists = new ArrayList<Product>();
		               while (rs.next()) {
		               	
    
		              	 product = new Product();
		              	 product.setProduct_id(rs.getInt("product_id"));
		              	product.setProduct_name(rs.getString("product_name"));
		            	product.setProduct_price(rs.getInt("product_price"));
		            	product.setProduct_state(rs.getInt("product_state"));
		            	product.setProduct_stock(rs.getInt("product_stock"));
		            	product.setProduct_img(rs.getString("product_img"));
		                product.setProduct_content(rs.getString("product_content"));
		              	 
		              	 lists.add(product);
		   			 }
		           }catch(Exception e) {System.out.println(e.getMessage());
		           }finally {
		               if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		               if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		               if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		           }
		   		 return lists;
		       } 
			
			public List<Product> list(int startRow, int endRow) throws Exception {
				Connection conn = getConnection(); 	
				List<Product> list = new ArrayList<Product>();
				PreparedStatement pstmt = null;		
				ResultSet rs = null;

				String sql="select * from (select rowNum rn, a.* from ("
					+ "select * from products order by product_id desc) a)"
					+ " where rn between ? and ?";
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						
						Product product = new Product();
		              	 product.setProduct_id(rs.getInt("product_id"));
		              	product.setProduct_name(rs.getString("product_name"));
		            	product.setProduct_price(rs.getInt("product_price"));
		            	product.setProduct_state(rs.getInt("product_state"));
		            	product.setProduct_stock(rs.getInt("product_stock"));
		            	product.setProduct_img(rs.getString("product_img"));
		                product.setProduct_content(rs.getString("product_content"));
		              	 
		              	 list.add(product);
					}
				}catch(Exception e){System.out.println(e.getMessage());
				}finally {
					try{if (rs != null) rs.close();
						if (pstmt != null) pstmt.close();
						if (conn  != null) conn.close();
					}catch (Exception e) {		}
				}
				return list;
			}
			public int total() throws Exception {
				int total = 0;
				Connection conn = getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;       
				String sql = "select count(*) from products";
				try{pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					total = rs.getInt(1);			
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}finally {
					try{if (rs    != null) rs.close();
						if (pstmt != null) pstmt.close();
						if (conn  != null) conn.close();
					}catch (Exception e) {	}
				}
				return total;
			}
			
			public ArrayList<Product> lists(HashMap<String, Object> listOpt) throws Exception {
		        ArrayList<Product> list = new ArrayList<Product>();
		        	Connection con = getConnection();
		            PreparedStatement pstmt = null;
					ResultSet rs = null;       
					
					String opt = (String)listOpt.get("opt"); 
			        String condition = (String)listOpt.get("condition"); 
			        int start = (Integer)listOpt.get("start");
		        try {
		           
		        	//전체
		            if (opt.equals("0")) {
		            	String sql = "SELECT product_name, product_content from products where"
		            			+ " product_name LIKE ? OR product_content LIKE ? order by product_id DESC";
		                pstmt = con.prepareStatement(sql);
		                pstmt.setString(1, "%"+condition+"%");
		                pstmt.setString(2, "%"+condition+"%");
		             //이름
		            } else {
		            	String sql = "SELECT product_name from products where"
		            			+ " product_name LIKE ? order by product_id DESC";
		                pstmt = con.prepareStatement(sql);
		                pstmt.setString(1, "%"+condition+"%");
		            } 
		 
		            rs = pstmt.executeQuery(); 
		 
		            while (rs.next() == true) {
		                Product product = new Product();
		 
		                product.setProduct_id(rs.getInt("product_id"));
		              	product.setProduct_name(rs.getString("product_name"));
		            	product.setProduct_price(rs.getInt("product_price"));
		            	product.setProduct_state(rs.getInt("product_state"));
		            	product.setProduct_stock(rs.getInt("product_stock"));
		            	product.setProduct_img(rs.getString("product_img"));
		                product.setProduct_content(rs.getString("product_content"));
		 
		                list.add(product);
		            }
		 
		        } catch (Exception e) {
					System.out.println(e.getMessage());
				}finally {
					try{if (rs    != null) rs.close();
						if (pstmt != null) pstmt.close();
						if (con  != null) con.close();
					}catch (Exception e) {	}
				}
		 
		        return list;
		    }
			

}
