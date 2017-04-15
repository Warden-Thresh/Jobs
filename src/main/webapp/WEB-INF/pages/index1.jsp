<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>SpringMVC Demo 首页</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<h1>这里是SpringMVC Demo首页44444</h1>

<h3>出现此页面，说明配置成功。</h3>
<tr>
    <td>
        <a href="/admin/users" type="button" class="btn btn-sm btn-success"><h4>admin</h4></a>
    </td>
    <td>
        <a href="/login" type="button" class="btn btn-sm btn-success"><h4>login</h4></a>
    </td>
</tr>
<div class="container">
    <h1>内容管理</h1>
    <hr/>

    <h3>所有内容 <a href="/admin/users/add" type="button" class="btn btn-primary btn-sm">添加</a></h3>

    <!-- 如果用户列表为空 -->
    <c:if test="${empty jobList}">
        <div class="alert alert-warning" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>Job表为空，请<a href="/admin/users/add" type="button" class="btn btn-primary btn-sm">添加</a>
        </div>
    </c:if>

    <!-- 如果用户列表非空 -->
    <c:if test="${!empty jobList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <th>名称</th>
                <th>详情</th>
                <th>发布者</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${jobList}" var="job">
                <tr>
                    <td>${job.jobId}</td>
                    <td>${job.jobName}</td>
                    <td>${job.jobDetial}</td>
                    <td>${job.userId}</td>
                    <td>
                        <a href="/admin/users/show/${job.jobId}" type="button" class="btn btn-sm btn-success">详情</a>
                        <a href="/admin/users/update/${job.jobId}" type="button" class="btn btn-sm btn-warning">修改</a>
                        <a href="/admin/users/delete/${job.jobId}" type="button" class="btn btn-sm btn-danger">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>



<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>