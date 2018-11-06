<%@page import="bean.account"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.StudentInfo" %>
<%@ page import="bean.Page" %>
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
    function change(value){
    	window.location.href='AllServlet?methodName=10';
    }
    function checknull(){
    	
    	var start=document.forms[0].start.value;
        var end=document.forms[0].end.value;
        if(start.length>0&&end.length>0){
        return true;
        }
        else{
        alert("请输入日期！");
        return false;
        }  
    }
 </script>
  </head>
  
  <body  bgcolor="#eeeeee">
    <div >
     <br>
   <font style="font-size: 30px; color: #393D49;">用户账单信息</font> <br>  <hr>    
  
     <br>
  <h3 style="font-size: 20px; color: #1E9FFF;">全部的账单信息如下：</h3>
  <br>
  
   <div class="layui-form-item">
   <a href="addone.jsp" class="layui-icon layui-icon-add-1" style="font-size: 30px; color: #1E9FFF;"></a>
   
    <div class="layui-form-item">
    <label class="layui-form-label">请选择类型</label>
    <div class="layui-input-block" style="width: 55%">
    <select name="classfy" lay-verify="required" onchange="change(this.value)">
        <option value="">请选择类型</option>
        <option value="餐饮">餐饮</option>
        <option value="交通">交通</option>
        <option value="衣服鞋包">衣服鞋包</option>
        <option value="淘宝">淘宝</option>
        <option value="红包">红包</option>

      </select>     
    </div>
  </div>
   
   <table class="layui-table">
   <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
    <tr>
    <td>ID</td>
    <td>账务类型</td>
    <td>数额</td>
    <td>类型</td>
    <td>日期</td>
    <td>备注</td>
    </tr>
    <%
     response.setCharacterEncoding("UTF-8");
     request.setCharacterEncoding("UTF-8");
     Page pager=(Page)request.getAttribute("pager");
     List<account> subResult=(List<account>)request.getAttribute("subResult");
       if(!subResult.isEmpty()){
       for(int i=0;i<subResult.size();i++){
            account st=subResult.get(i);
            out.print("<tr>");
            out.print("<td>"+st.getId()+"</td>");
            out.print("<td>"+st.getFintype()+"</td>");
            out.print("<td>"+st.getAmount()+"</td>");
            out.print("<td>"+st.getClasx()+"</td>");
            out.print("<td>"+st.getDate()+"</td>");
            out.print("<td>"+st.getNote()+"</td>"); 
            
     %>
     <td><a href="AllServlet?id=<%=st.getId() %>&methodName=<%=2 %>" onclick="return confirmdialog()">删除</a></td>
     
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
        out.print("<a href='AllServlet?currentRecord="+last+"&methodName=1'>上一页</a>|");
        }
        if(next>=pager.getTotalRecord()){
          out.println("尾页|");
        }
        else{
          out.print("<a href='AllServlet?currentRecord="+next+"&methodName=1'>下一页</a>|");
        }
      %>
      </font>
      </div>
     <br>
    <form action="AllServlet" method="post" onSubmit="return checknull()">
    <input type="hidden" name="methodName" value="5"/>
    <br>
    <br>
      <h3 >按时间段查询总支出和收入:</h3>
      <br>
      	<h3>格式如：2018-1-1-------2018-2-2</h3>
      	<br>
               <h3> 请输入开始年月日：</h3><div class="layui-input-inline"><input class="layui-input" type="text" name="start"  value="" title="" ></input></div>
                              <br>
               <h3>请输入结束年月日：</h3><div class="layui-input-inline"> <input class="layui-input" type="text" name="end" value="" title=""></input></div>
      <input type="submit" value="查询" class="layui-btn layui-btn-normal" />
    </form>
 <br>

<br>
    </div>
   
  </body>
</html>
