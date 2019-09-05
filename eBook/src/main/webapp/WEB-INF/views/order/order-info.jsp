<%--
  Created by IntelliJ IDEA.
  User: W
  Date: 2019/9/5
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        .img1{
            width: 150px;
            height: 150px;
        }

    </style>
</head>
<body>
<div class="x-body">


    <form id="ft_sub" class="layui-form" lay-filter="test1" method="post">
        <div class="layui-form-item">
            <label for="orderId" class="layui-form-label">
                <span class="x-red">*</span>订单号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="orderId" name="orderId" required="" lay-verify="required"
                       autocomplete="off" class="layui-input" value="${orderup.orderId}" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="ctdate" class="layui-form-label">
                <span class="x-red">*</span>创建日期
            </label>
            <div class="layui-input-inline">
                <input type="text" id="ctdate" name="ctdate" required="" lay-verify="required"
                       autocomplete="off" class="layui-input" value="${orderup.ctdate}" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
        <label for="user" class="layui-form-label">
            <span class="x-red">*</span>用户
        </label>
        <div class="layui-input-inline">
            <input type="text" id="user" name="password" required="required" lay-verify="required"
                   autocomplete="off" class="layui-input" value="${orderup.user.username}" disabled="disabled">
        </div>
    </div>
        <div class="layui-form-item">
            <label for="bookName" class="layui-form-label">
                <span class="x-red">*</span>图书名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="bookName" name="bookName" required="required" lay-verify="required"
                       autocomplete="off" class="layui-input" value="${orderup.book.bookName}" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="bookMoney" class="layui-form-label">
                <span class="x-red">*</span>图书单价
            </label>
            <div class="layui-input-inline">
                <input type="text" id="bookMoney" name="bookMoney" required="required" lay-verify="required"
                       autocomplete="off" class="layui-input" value="${orderup.book.bookMoney}" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="bookNum" class="layui-form-label">
                <span class="x-red">*</span>图书数量
            </label>
            <div class="layui-input-inline">
                <input type="text" id="bookNum" name="bookNum" required="required" lay-verify="required"
                       autocomplete="off" class="layui-input" value="${orderup.booknum}" disabled="disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="money" class="layui-form-label">
                <span class="x-red">*</span>订单总价
            </label>
            <div class="layui-input-inline">
                <input type="text" id="money" name="money" required="required" lay-verify="required"
                       autocomplete="off" class="layui-input" value="${orderup.money}" disabled="disabled">
            </div>
        </div>
    </form>


</div>
</body>
</html>
