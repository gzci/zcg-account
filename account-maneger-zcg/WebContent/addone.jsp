<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <link rel="stylesheet" href="css/layui.css">
    <link rel="stylesheet" href="css/style.css">
<head>

    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>注册页</title>
   
    <link rel="icon" href="image/code.png">
</head>

<body>
<div class="login-main">
<header class="layui-elip" style="width: 82%">添加一条账单信息哦！！</header>

<form class="layui-form" action="AllServlet" method="post" >
 <input  type="hidden" name="methodName" value="7"/>

 <div class="layui-input-inline">
            <div class="layui-inline" style="width: 85%">
            <select name="fintype" lay-verify="required">
        <option value="">请选择金额类型</option>
        <option value="收入">收入</option>
        <option value="支出">支出</option>

      </select>
            </div>
 </div>
  <div class="layui-input-inline">
            <div class="layui-inline" style="width: 85%">
            <input   type="text" name="amount"required  lay-verify="required" placeholder="请输入金额" autocomplete="off"class="layui-input"></input>
            </div>
 </div>

 <div class="layui-input-inline">
            <div class="layui-inline" style="width: 85%">
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
  
  <div class="layui-input-inline">
            <div class="layui-inline" style="width: 85%">
            <input   type="text" name="note"required  lay-verify="required" placeholder="请输入备注" autocomplete="off"class="layui-input"></input>
            </div>
 </div>
<div class="layui-input-inline">
            <div class="layui-inline" style="width: 85%">
             <input   type="submit" value="添加"class="layui-input"/>
            </div>
 </div>

  </form>
  </div>
  <script src="layui.js"></script>
<script type="text/javascript">
    layui.use(['form','jquery','layer'], function () {
        var form   = layui.form;
        var $      = layui.jquery;
        var layer  = layui.layer;
        //添加表单失焦事件
        //验证表单
         $('#user').blur(function() {
            var user = $(this).val();
 			
            //alert(user);
            $.ajax({
                url:'checkuserinput.jsp',
                type:'post',
                dataType:'text',
                data:{user:user},
                //验证用户名是否可用
                success:function(data){
                	
                    if (data ==1 ) {
                        $('#ri').removeAttr('hidden');
                        $('#wr').attr('hidden','hidden');
 
 
                    } else {
                    	
                        $('#wr').removeAttr('hidden');
                        $('#ri').attr('hidden','hidden');
                        layer.msg('当前用户名已被占用! ')
 
                    }
 
                }
            })
 
        }); 
 
        // you code ...
        // 为密码添加正则验证
        $('#pwd').blur(function() {
                var reg = /^[\w]{6,12}$/;
                if(!($('#pwd').val().match(reg))){
                    //layer.msg('请输入合法密码');
                    $('#pwr').removeAttr('hidden');
                    $('#pri').attr('hidden','hidden');
                    layer.msg('请输入合法密码');
                }else {
                    $('#pri').removeAttr('hidden');
                    $('#pwr').attr('hidden','hidden');
                }
        });
 
        //验证两次密码是否一致
        $('#rpwd').blur(function() {
                if($('#pwd').val() != $('#rpwd').val()){
                    $('#rpwr').removeAttr('hidden');
                    $('#rpri').attr('hidden','hidden');
                    layer.msg('两次输入密码不一致!');
                }else {
                    $('#rpri').removeAttr('hidden');
                    $('#rpwr').attr('hidden','hidden');
                };
        });
 
        //
        //添加表单监听事件,提交注册信息
        /* form.on('submit(sub)', function() {
            $.ajax({
                url:'reg.php',
                type:'post',
                dataType:'text',
                data:{
                    user:$('#user').val(),
                    pwd:$('#pwd').val(),
                },
                success:function(data){
                    if (data == 1) {
                        layer.msg('注册成功');
                        ///location.href = "login.html";
                    }else {
                        layer.msg('注册失败');
                    }
                }
            })
            //防止页面跳转
            return false;
        }); */
 
    });
</script>
</body>


</html>