<%@page import="bean.account"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bean.StudentInfo" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>账单信息修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <link rel="stylesheet" href="css/layui.css?t=1535898708529" media="all">
  <style>
    body{margin: 10px;}
    .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
  </style>
  </head>
  
  <body bgcolor="#eeeeee">
    <br>
    <font style="font-size: 30px; color: #393D49;">账单信息</font> <hr>    
    <br> 
   <h3 style="font-size: 20px; color: #1E9FFF;">要修改的账单信息如下</h3>
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
    int id=0;
    	ArrayList<account> result=new ArrayList<account>();
       result=(ArrayList<account>)request.getAttribute("result");
       if(!result.isEmpty()){
       for(int i=0;i<result.size();i++){
            account st=result.get(i);
            id=Integer.parseInt(st.getId());
            out.print("<tr>");
            out.print("<td>"+st.getId()+"</td>");
            out.print("<td>"+st.getFintype()+"</td>");
            out.print("<td>"+st.getAmount()+"</td>");
            out.print("<td>"+st.getClasx()+"</td>");
            out.print("<td>"+st.getDate()+"</td>");
            out.print("<td>"+st.getNote()+"</td>"); 
            out.print("</tr>");
            }
            }
   
       session.setAttribute("id",id);
     %>
    </table>
   <h3 style="font-size: 20px; color: #1E9FFF;">将账单信息更改为：</h3>
 
<br>
<form class="layui-form" action="AllServlet" method="post" >
 <input  type="hidden" name="methodName" value="3"/>
<div class="layui-form-item">
    <label class="layui-form-label">金额类型</label>
    <div class="layui-input-block" style="width: 25%">
    <select  name="fintype" lay-verify="required">
        <option value="">请选择金额类型</option>
        <option value="收入">收入</option>
        <option value="支出">支出</option>

      </select>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">金额</label>
    <div class="layui-input-block" style="width: 25%">
    <input   type="text" name="amount"required  lay-verify="required" placeholder="请输入金额" autocomplete="off"class="layui-input"></input>
           
    </div>
  </div>
 <div class="layui-form-item">
    <label class="layui-form-label">类型</label>
    <div class="layui-input-block" style="width: 25%">
    <select name="class" lay-verify="required">
        <option value="">请选择类型</option>
        <option value="餐饮">餐饮</option>
        <option value="交通">交通</option>
        <option value="衣服鞋包">衣服鞋包</option>
        <option value="淘宝">淘宝</option>
        <option value="红包">红包</option>

      </select>     
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">备注</label>
    <div class="layui-input-block" style="width: 25%">
          <input   type="text" name="note"required  lay-verify="required" placeholder="请输入备注" autocomplete="off"class="layui-input"></input>
         
    </div>
  </div>
 <div class="layui-form-item">
    <div class="layui-input-block" style="width: 25%">
<input   type="submit" value="添加"class="layui-input"/>         
    </div>
  </div>


  </form>
  
 <br>
<script src="layui.js"></script>
<script type="text/javascript">
    layui.use(['form','jquery','layer'], function () {
        var form   = layui.form;
        var $      = layui.jquery;
        var layer  = layui.layer;
        //添加表单失焦事件
        //验证表单
         
 
    });
</script>
  </body>
  
</html>
