<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.sql.*" %>
       

<%


String driver = "com.mysql.jdbc.Driver";

// URL指向要访问的数据库名test1

String url = "jdbc:mysql://localhost:3306/book";

// MySQL配置时的用户名

// Java连接MySQL配置时的密码

String password = "root";
String user ="root";

try {

// 1 加载驱动程序

Class.forName(driver);

// 2 连接数据库

Connection conn = DriverManager.getConnection(url, user, password);

// 3 用来执行SQL语句

Statement statement = conn.createStatement();

// 要执行的SQL语句
String name =request.getParameter("user"); 
String sql = "select * from user where name='"+name+"'";

ResultSet rs = statement.executeQuery(sql);

if(!rs.next())
{
	out.print(1);
}else
{
	out.print(0);
}



request.setCharacterEncoding("UTF-8");




rs.close();
conn.close();
} catch (ClassNotFoundException e) {
System.out.println("Sorry,can`t find the Driver!");
e.printStackTrace();
} catch (SQLException e) {
e.printStackTrace();
} catch (Exception e) {
e.printStackTrace();
}

%>

