<%@page import="bean.account"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.StudentInfo" %>
<%@ page import="bean.Page" %>
<%@ page import="bean.user" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>信息查询结果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/layui.css?t=1535898708529" media="all">
  <style>
    body{margin: 10px;}
    .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
  </style>
 <script type="text/javascript">
    function confirmdialog(){
       if(window.confirm("您确定要删除此条信息？")){
       return true;
       }
       else{
     //  alert("取消删除！");
       return false;
       }   
       
    }
    
 </script>
  </head>
  
  <body  bgcolor="#eeeeee">
    <div >
     <br>
   <font style="font-size: 30px; color: #393D49;">用户信息</font> <br>  <hr>    
  
     <br>
  <h3 style="font-size: 20px; color: #1E9FFF;">全部的用户信息如下：</h3><a href="addone.jsp" class="layui-icon layui-icon-add-1" style="font-size: 30px; color: #1E9FFF;"></a>
   <table class="layui-table">
   <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
    <tr>
    <td>ID</td>
    <td>用户名</td>
    <td>密码</td>
    <td>超级验证码</td>
    <td>是否超级用户</td>
    </tr>
    <%
     response.setCharacterEncoding("UTF-8");
     request.setCharacterEncoding("UTF-8");
     Page pager=(Page)request.getAttribute("pager");
     List<user> subResult=(List<user>)request.getAttribute("subResult");
       if(!subResult.isEmpty()){
       for(int i=0;i<subResult.size();i++){
            user st=subResult.get(i);
            out.print("<tr>");
            out.print("<td>"+st.getId()+"</td>");
            out.print("<td>"+st.getName()+"</td>");
            out.print("<td>"+st.getPwd()+"</td>");
            out.print("<td>"+st.getSupercode()+"</td>");
            out.print("<td>"+st.getIssuper()+"</td>"); 
            
     %>
     <td><a href="AllServlet?code=<%=st.getSupercode() %>&id=<%=st.getId() %>&methodName=<%=2 %>" onclick="return confirmdialog()">拉黑</a></td>
     
     <td><a href="AllServlet?id=<%=st.getId() %>&name=<%="" %>&methodName=<%=4 %>">修改</a></td>
    <% 
    out.print("</tr>");
         }
       }
     %>
     </table>
     <div style=" text-align:center;"><font  class="layui-icon layui-icon-face-smile" style="font-size: 15px; color: #2F4056;">总<%=pager.getTotalRecord() %>条记录|总<%=pager.getTotalPage() %>页
     |当前<%=pager.getCurrentPage()+1 %>页|每页<%=pager.getPageSize() %>条|
     <%
        int last=pager.getCurrentRecord()-pager.getPageSize();
        int next=pager.getCurrentRecord()+pager.getPageSize();
        int currentRecord;
        if(last<0){
        out.println("首页|");
        }
        else{
        out.print("<a href='AllServlet?currentRecord="+last+"&methodName=11'>上一页</a>|");
        }
        if(next>=pager.getTotalRecord()){
          out.println("尾页|");
        }
        else{
          out.print("<a href='AllServlet?currentRecord="+next+"&methodName=11'>下一页</a>|");
        }
      %>
      </font>
      </div>
  
    </div>
   
  </body>
</html>
