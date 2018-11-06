package dbservlet;
 
 
import java.awt.print.Printable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import com.sun.corba.se.impl.protocol.AddressingDispositionException;

import bean.Page;
import bean.StudentInfo;
import bean.account;
import bean.user;
import sun.security.timestamp.TSRequest;
 
public class AllServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static  String MAINname;
	private static float MAINamount=0;
	private static float MAXFARE=10000;
	 //doPost方法
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    request.setCharacterEncoding("UTF-8");
   		    response.setCharacterEncoding("UTF-8");
			String methodName=request.getParameter("methodName");
			int method=Integer.parseInt(methodName);
		try {  
			switch(method)
		       {
		    	case 0:
					insert(request,response);
					break;
		        case 1:
                    difpage(request,response);
			        break;    
		    	case 2:
  				    delete(request,response);
  			        break;       
		        case 3:
  				    update(request,response);
  				    break;
		        case 4:
		        	update1(request,response);
			        break;
		        case 5:
		        	sumall(request,response);
			        break;
		        case 6:
		        	checkuser(request,response);
		        	break;
		        case 7:
		        	addone(request,response);
		        	break;
		        case 8:
//		        	loginUsercheck(request,response);
		        	break;
		        case 9:
		        	suinsert(request,response);
		        	break;
		        case 10:
		        	checksuser(request,response);
		        	break;
		        case 11:
		        	difuserpage(request,response);
		       }
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	private void difuserpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		ArrayList<user> result=selectuser("","");
		 
		 Page pager=new Page();
		 pager=setsupage(request,response);
 	     List<user> subResult=null;
 	     int currentRecord=pager.getCurrentRecord();
        if(currentRecord==0){
        	if(pager.getTotalRecord()<4){
        		subResult=(List<user>) result.subList(0,pager.getTotalRecord());
        	}
        	else{
        		subResult=(List<user>) result.subList(0,pager.getPageSize());
        	}         
        }
        else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
        {
              subResult=(List<user>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
        }
        else
        {
             subResult=(List<user>) result.subList(pager.getCurrentRecord(),result.size());
        }
         request.setAttribute("pager", pager);
	     request.setAttribute("subResult", subResult);
		 request.getRequestDispatcher("userlist.jsp").forward(request, response);
		
	}
	private ArrayList<user> selectuser(String string, String string2) throws SQLException, ClassNotFoundException {
		Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=connect();
		stat=conn.createStatement();
    	ArrayList<user> result=new ArrayList<user>();
    	
    	
    	rs=stat.executeQuery("select * from user"); 
    	
    	
    	while(rs.next())
        {
    		
    		user account= new user();
    		account.setId(rs.getString("id"));
    		account.setName(rs.getString("name"));
    		account.setPwd(rs.getString("pwd"));
    		account.setIssuper(rs.getString("issuper"));
    		account.setSupercode(rs.getString("supercode"));
        	result.add(account);	
        }
	    if(rs!=null){
	    	  rs.close();
	       }
	    close(stat,conn);
    	return result;
	}
	private void checksuser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		String pwd=request.getParameter("pwd");
	     String namee=request.getParameter("name");
		 if (!check(pwd,namee,true)) {
			 request.getRequestDispatcher("erro.jsp").forward(request, response);
			    
		}else {
			//这里显示的是此刻的user表
			difuserpage(request,response);
		}
		
	}
	private void suinsert(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
		Connection conn=null;
    	Statement stat=null;
        String name=request.getParameter("name");
       
        String pwd = request.getParameter("pwd");
        int issuper=1;
        String supercode=request.getParameter("code");
        
		conn=connect();
		stat=conn.createStatement();
		stat.execute("insert into user(name,pwd,issuper,supercode) values('"+name+"','"+pwd+"',"+issuper+",'"+supercode+"')"); 
    	close(stat,conn);
    	request.getRequestDispatcher("succeed.jsp").forward(request, response);
			
	}
	private void loginUsercheck(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=connect();
		stat=conn.createStatement();
    	String name = request.getParameter("user");
    	if(name!=""){
      	    rs=stat.executeQuery("select * from user where name='"+name+"'"); 
      	}
    	if(!rs.next())
        {
    		
        }else
        {
        	 
        }
	    if(rs!=null){
	    	  rs.close();
	       }
	    close(stat,conn);
	   
		
	}
	private void sumall(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		int start=Integer.parseInt(request.getParameter("start").replaceAll("-", ""));
        int end=Integer.parseInt(request.getParameter("end").replaceAll("-", ""));	
        
        Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=connect();
		stat=conn.createStatement();
    	ArrayList<account> result=new ArrayList<account>();
    	
    	  
    	rs=stat.executeQuery("select * from account where name='"+MAINname+"'"); 
    	
    	
    	while(rs.next())
        {
    		
    		account account= new account();
    		account.setId(rs.getString("id"));
    		account.setFintype(rs.getString("fintype"));
    		account.setAmount(rs.getString("amount"));
    		account.setDate(rs.getString("date"));
    		account.setNote(rs.getString("note"));
    		int now=Integer.parseInt(account.getDate().split(" ")[0].replaceAll("-", ""));
    		if (now<=end&&now>=start) {
    			result.add(account);
			}
        		
        }
    	int income=0;
    	int outcome=0;
    	for (account account : result) {
			if (account.getFintype().equals("收入")) 
				income+=Integer.parseInt(account.getAmount());
			else
				outcome+=Integer.parseInt(account.getAmount());
		}
    	System.out.println(income);
    	System.out.println(outcome);
    	request.setAttribute("income", income+"");
    	request.setAttribute("outcome", outcome+"");
    	 request.getRequestDispatcher("sumall.jsp").forward(request, response);
    	
	    if(rs!=null){
	    	  rs.close();
	       }
	    close(stat,conn);
        
	}
	private void addone(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		Connection conn=null;
    	Statement stat=null;
        String clasx=request.getParameter("class");
        String fintype=request.getParameter("fintype");
        String amount=request.getParameter("amount");
        String note=request.getParameter("note");
    	conn=connect();
 		stat=conn.createStatement();
		stat.execute("insert into account(fintype,amount,note,name,class) values('"+fintype+"',"+amount+",'"+note+"','"+MAINname+"','"+clasx+"')");
		
		//查看
		difpage(request,response);
//		 
		
	}
		
	
	//doGet方法
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
       doPost(request,response);
    }
	
	
    //数据库连接方法
	public static Connection connect() throws ClassNotFoundException, SQLException{
    	Connection conn=null; 
	    Class.forName("com.mysql.jdbc.Driver");
	    String url = "jdbc:mysql://localhost:3306/book?"
                + "user=root&password=root&characterEncoding=UTF8";
		conn=DriverManager.getConnection(url); 
		return conn;
	}
	//关闭数据库资源
	public  static void close(Statement stat,Connection conn) throws SQLException{
		if(stat!=null){
	    	   stat.close();
	    }
	    if(conn!=null){
	    	   conn.close();
	    }
	}
	
	//插入方法
	public void insert(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
		Connection conn=null;
    	Statement stat=null;
        String name=request.getParameter("name");
       
        String pwd = request.getParameter("pwd");
		conn=connect();
		stat=conn.createStatement();
		stat.execute("insert into user(name,pwd,issuper,supercode) values('"+name+"','"+pwd+"',"+0+",'')"); 
    	close(stat,conn);
    	request.getRequestDispatcher("succeed.jsp").forward(request, response);
			
    }
	
	//check
	public boolean check(String pwd,String name,Boolean issupercheck) throws ClassNotFoundException, SQLException{
    	Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=connect();
		stat=conn.createStatement();
    	ArrayList<StudentInfo> result=new ArrayList<StudentInfo>();
    	if(!issupercheck) {
    		if(pwd!=""&&name!=""){
          	    rs=stat.executeQuery("select * from user where pwd='"+pwd+"' and name='"+name+"'"); 
          	}
        	if(!rs.next())
            {
            	return false;
            }
    	}else {
    		
    		if(pwd!=""&&name!=""){
          	    rs=stat.executeQuery("select * from user where pwd='"+pwd+"' and name='"+name+"' and issuper=1" ); 
          	}
        	if(!rs.next())
            {
            	return false;
            }
    		
    	}
    	
	    if(rs!=null){
	    	  rs.close();
	       }
	    close(stat,conn);
    	return true;
    }
    //查询方法
	public ArrayList<account> selectment(String clasx) throws ClassNotFoundException, SQLException{
    	Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=connect();
		stat=conn.createStatement();
    	ArrayList<account> result=new ArrayList<account>();
    	   
    	rs=stat.executeQuery("select * from account where class='"+clasx+"'"); 
    	
    	while(rs.next())
        {
    		
    		account account= new account();
    		account.setId(rs.getString("id"));
    		account.setFintype(rs.getString("fintype"));
    		account.setAmount(rs.getString("amount"));
    		account.setDate(rs.getString("date"));
    		account.setNote(rs.getString("note"));
    		account.setClasx(rs.getString("class"));
        	result.add(account);	
        }
	    if(rs!=null){
	    	  rs.close();
	       }
	    close(stat,conn);
    	return result;
    }
    public ArrayList<account> select(String id,String name) throws ClassNotFoundException, SQLException{
    	Connection conn=null;
    	Statement stat=null;
	    ResultSet rs=null;
	    conn=connect();
		stat=conn.createStatement();
    	ArrayList<account> result=new ArrayList<account>();
    	
    	if(id!="")
    		rs=stat.executeQuery("select * from account where id="+id);
    	else    
    		rs=stat.executeQuery("select * from account where name='"+name+"'"); 
    	
    	
    	while(rs.next())
        {
    		
    		account account= new account();
    		account.setId(rs.getString("id"));
    		account.setFintype(rs.getString("fintype"));
    		account.setAmount(rs.getString("amount"));
    		account.setDate(rs.getString("date"));
    		account.setNote(rs.getString("note"));
    		account.setClasx(rs.getString("class"));
        	result.add(account);	
        }
	    if(rs!=null){
	    	  rs.close();
	       }
	    close(stat,conn);
    	return result;
    }
    public Page setsupage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
		String crd=request.getParameter("currentRecord");
		//String id=request.getParameter("id");
      //  String name=request.getParameter("name");
    	ArrayList<user> result=selectuser("","");
    	Page pager=new Page();
    	pager.setTotalRecord(result.size()); 
    	pager.setTotalPage(result.size(),pager.getPageSize());
    	if(crd!=null)
        {
    		int currentRecord=Integer.parseInt(crd);
            pager.setCurrentRecord(currentRecord);
            pager.setCurrentPage(currentRecord,pager.getPageSize());
        }
    	return pager;
	}
    //设置分页相关参数方法
	public Page setpage(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException{
		String crd=request.getParameter("currentRecord");
		//String id=request.getParameter("id");
//        String name=request.getParameter("name");
    	ArrayList<account> result=select("",MAINname);
    	Page pager=new Page();
    	pager.setTotalRecord(result.size()); 
    	pager.setTotalPage(result.size(),pager.getPageSize());
    	if(crd!=null)
        {
    		int currentRecord=Integer.parseInt(crd);
            pager.setCurrentRecord(currentRecord);
            pager.setCurrentPage(currentRecord,pager.getPageSize());
        }
    	return pager;
	}
	
	//获得分页显示的子集
	 public void difpage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
		 String classfy = request.getParameter("classfy");
		 ArrayList<account> result=select("",MAINname);
		 float tempsum=0;
		 for (account account : result) {
			 tempsum+=Float.parseFloat(account.getAmount());
		}
		 if (tempsum>= MAINamount) {
			MAINamount=tempsum;
		}
		 if (MAINamount>MAXFARE) {
			
		}
		 Page pager=new Page();
		 pager=setpage(request,response);
  	     List<account> subResult=null;
  	     int currentRecord=pager.getCurrentRecord();
         if(currentRecord==0){
         	if(pager.getTotalRecord()<4){
         		subResult=(List<account>) result.subList(0,pager.getTotalRecord());
         	}
         	else{
         		subResult=(List<account>) result.subList(0,pager.getPageSize());
         	}         
         }
         else if(pager.getCurrentRecord()+pager.getPageSize()<result.size())
         {
               subResult=(List<account>) result.subList(pager.getCurrentRecord(),pager.getCurrentRecord()+pager.getPageSize());
         }
         else
         {
              subResult=(List<account>) result.subList(pager.getCurrentRecord(),result.size());
         }
         request.setAttribute("pager", pager);
	     request.setAttribute("subResult", subResult);
		 request.getRequestDispatcher("layout.jsp").forward(request, response);
     }
	 
	 public void checkuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
		 String pwd=request.getParameter("pwd");
	     String namee=request.getParameter("name");
		 if (!check(pwd,namee,false)) {
			 request.getRequestDispatcher("erro.jsp").forward(request, response);
			    
		}else {
			MAINname=namee;
			difpage(request,response);
		}
		 }
    //信息删除方法
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
    	Connection conn=null;
    	Statement stat=null;
    	conn=connect();
 		stat=conn.createStatement();
 		String id2=request.getParameter("id");
 		String code=request.getParameter("code");
 		if (code!=null) {
 			stat.execute("delete from user where id="+id2+"");
 			difuserpage(request,response);
 			
		}else
		stat.execute("delete from account where id="+id2+"");
 		difpage(request,response);
    } 
    //信息修改方法
    public void update1(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
    	String id4=request.getParameter("id");
	    request.setAttribute("result", select(id4,""));
	    request.setAttribute("id", id4);
        request.getRequestDispatcher("update1.jsp").forward(request, response);
    }   
    public void update(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
    	Connection conn=null;
    	Statement stat=null;
    	
    	int id3 = (int) request.getSession().getAttribute("id");
        String clasx=request.getParameter("class");
        String fintype=request.getParameter("fintype");
        String amount=request.getParameter("amount");
        String note=request.getParameter("note");
    	conn=connect();
 		stat=conn.createStatement();
		stat.execute("update account set class='"+clasx+"',fintype='"+fintype+"',amount="+amount+",note='"+note+"' where id="+id3+"");
		 difpage(request,response);
    } 
   
}
