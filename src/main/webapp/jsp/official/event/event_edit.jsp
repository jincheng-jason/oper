<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>--%>
<%--<%--%>
    <%--String path = request.getContextPath();--%>
    <%--String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";--%>
<%--%>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
    <%--<base href="<%=basePath%>">--%>

    <%--<meta charset="utf-8" />--%>
    <%--<title></title>--%>

    <%--<meta name="description" content="overview & stats" />--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0" />--%>
    <%--<link href="css/bootstrap.min.css" rel="stylesheet" />--%>
    <%--<link href="css/bootstrap-responsive.min.css" rel="stylesheet" />--%>
    <%--<link rel="stylesheet" href="css/font-awesome.min.css" />--%>
    <%--<!--[if IE 7]><link rel="stylesheet" href="css/font-awesome-ie7.min.css" /><![endif]-->--%>
    <%--<!--[if lt IE 9]><link rel="stylesheet" href="css/ace-ie.min.css" /><![endif]-->--%>
    <%--<!-- 下拉框 -->--%>
    <%--<link rel="stylesheet" href="css/chosen.css" />--%>

    <%--<link rel="stylesheet" href="css/ace.min.css" />--%>
    <%--<link rel="stylesheet" href="css/ace-responsive.min.css" />--%>
    <%--<link rel="stylesheet" href="css/ace-skins.min.css" />--%>

    <%--<link rel="stylesheet" href="css/datepicker.css" /><!-- 日期框 -->--%>
    <%--<script type="text/javascript" src="js/jquery-1.7.2.js"></script>--%>
    <%--<script type="text/javascript" src="js/jquery.tips.js"></script>--%>

    <%--<script type="text/javascript">--%>



        <%--//保存--%>
        <%--function save(){--%>
            <%--if($("#title").val()==""){--%>
                <%--$("#title").tips({--%>
                    <%--side:3,--%>
                    <%--msg:'请输入标题',--%>
                    <%--bg:'#AE81FF',--%>
                    <%--time:2--%>
                <%--});--%>
                <%--$("#title").focus();--%>
                <%--return false;--%>
            <%--}--%>
            <%--if($("#url").val()==""){--%>
                <%--$("#url").tips({--%>
                    <%--side:3,--%>
                    <%--msg:'请输入活动',--%>
                    <%--bg:'#AE81FF',--%>
                    <%--time:2--%>
                <%--});--%>
                <%--$("#url").focus();--%>
                <%--return false;--%>
            <%--}--%>
            <%--if($("#img").val()==""){--%>
                <%--$("#img").tips({--%>
                    <%--side:3,--%>
                    <%--msg:'请输入图片',--%>
                    <%--bg:'#AE81FF',--%>
                    <%--time:2--%>
                <%--});--%>
                <%--$("#img").focus();--%>
                <%--return false;--%>
            <%--}--%>
            <%--if($("#isInProcess").val()==""){--%>
                <%--$("#isInProcess").tips({--%>
                    <%--side:3,--%>
                    <%--msg:'请输入是否进行',--%>
                    <%--bg:'#AE81FF',--%>
                    <%--time:2--%>
                <%--});--%>
                <%--$("#isInProcess").focus();--%>
                <%--return false;--%>
            <%--}--%>
            <%--if($("#beginAt").val()==""){--%>
                <%--$("#beginAt").tips({--%>
                    <%--side:3,--%>
                    <%--msg:'请输入开始时间',--%>
                    <%--bg:'#AE81FF',--%>
                    <%--time:2--%>
                <%--});--%>
                <%--$("#beginAt").focus();--%>
                <%--return false;--%>
            <%--}--%>
            <%--if($("#endAt").val()==""){--%>
                <%--$("#endAt").tips({--%>
                    <%--side:3,--%>
                    <%--msg:'请输入结束时间',--%>
                    <%--bg:'#AE81FF',--%>
                    <%--time:2--%>
                <%--});--%>
                <%--$("#endAt").focus();--%>
                <%--return false;--%>
            <%--}--%>
            <%--$("#Form").submit();--%>
            <%--$("#zhongxin").hide();--%>
            <%--$("#zhongxin2").show();--%>
        <%--}--%>

    <%--</script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form action="event/${msg }.do" name="Form" id="Form" method="post">--%>
    <%--<input type="hidden" name="EVENT_ID" id="EVENT_ID" value="${pd.EVENT_ID}"/>--%>
    <%--<div id="zhongxin">--%>
        <%--<table>--%>
            <%--<tr>--%>
                <%--<td><input type="text" name="title" id="title" value="${pd.title}" maxlength="32" placeholder="这里输入标题" title="标题"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><input type="text" name="url" id="url" value="${pd.url}" maxlength="32" placeholder="这里输入活动" title="活动"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><input type="text" name="img" id="img" value="${pd.img}" maxlength="32" placeholder="这里输入图片" title="图片"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><input type="text" name="isInProcess" id="isInProcess" value="${pd.isInProcess}" maxlength="32" placeholder="这里输入是否进行" title="是否进行"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><input class="span10 date-picker" name="beginAt" id="beginAt" value="${pd.beginAt}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="开始时间" title="开始时间"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><input class="span10 date-picker" name="endAt" id="endAt" value="${pd.endAt}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="结束时间" title="结束时间"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td style="text-align: center;">--%>
                    <%--<a class="btn btn-mini btn-primary" onclick="save();">保存</a>--%>
                    <%--<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</div>--%>

    <%--<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>--%>

<%--</form>--%>


<%--<!-- 引入 -->--%>
<%--<script type="text/javascript">window.jQuery || document.write("<script src='js/jquery-1.9.1.min.js'>\x3C/script>");</script>--%>
<%--<script src="js/bootstrap.min.js"></script>--%>
<%--<script src="js/ace-elements.min.js"></script>--%>
<%--<script src="js/ace.min.js"></script>--%>
<%--<script type="text/javascript" src="js/chosen.jquery.min.js"></script><!-- 下拉框 -->--%>
<%--<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->--%>
<%--<script type="text/javascript">--%>
    <%--$(window.parent.hangge());--%>
    <%--$(function() {--%>

        <%--//单选框--%>
        <%--$(".chzn-select").chosen();--%>
        <%--$(".chzn-select-deselect").chosen({allow_single_deselect:true});--%>

        <%--//日期框--%>
        <%--$('.date-picker').datepicker();--%>

    <%--});--%>

<%--</script>--%>
<%--</body>--%>
<%--</html>--%>