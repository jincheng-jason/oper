<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		
		<meta charset="utf-8" />
		<title></title>
		
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="css/bootstrap.min.css" rel="stylesheet" />
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<!--[if IE 7]><link rel="stylesheet" href="css/font-awesome-ie7.min.css" /><![endif]-->
		<!--[if lt IE 9]><link rel="stylesheet" href="css/ace-ie.min.css" /><![endif]-->
		<!-- 下拉框 -->
		<link rel="stylesheet" href="css/chosen.css" />
		
		<link rel="stylesheet" href="css/ace.min.css" />
		<link rel="stylesheet" href="css/ace-responsive.min.css" />
		<link rel="stylesheet" href="css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	
	//保存
	function save(){
			if($("#TITLE").val()==""){
			$("#TITLE").tips({
				side:3,
	            msg:'请输入标题',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TITLE").focus();
			return false;
		}
		if($("#URL").val()==""){
			$("#URL").tips({
				side:3,
	            msg:'请输入文章地址',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#URL").focus();
			return false;
		}
		if($("#IMG_LINK").val()==""){
			$("#IMG_LINK").tips({
				side:3,
	            msg:'请输入图片',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#IMG_LINK").focus();
			return false;
		}
		if($("#HEAD_IMAGE").val()==""){
			$("#HEAD_IMAGE").tips({
				side:3,
	            msg:'请输入标题图片',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#HEAD_IMAGE").focus();
			return false;
		}
		if($("#ACCOUNT_NAME").val()==""){
			$("#ACCOUNT_NAME").tips({
				side:3,
	            msg:'请输入来源',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ACCOUNT_NAME").focus();
			return false;
		}
		if($("#CONTENT168").val()==""){
			$("#CONTENT168").tips({
				side:3,
	            msg:'请输入概要',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTENT168").focus();
			return false;
		}
		if($("#DATE").val()==""){
			$("#DATE").tips({
				side:3,
	            msg:'请输入发布日期',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DATE").focus();
			return false;
		}
		if($("#LAST_MODIFIED").val()==""){
			$("#LAST_MODIFIED").tips({
				side:3,
	            msg:'请输入最后修改',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#LASTMODIFIED").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="article/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input type="text" name="TITLE" id="TITLE" value="${pd.TITLE}" maxlength="32" placeholder="这里输入标题" title="标题"/></td>
			</tr>
			<tr>
				<td>
                    <input type="text" name="URL" id="URL" value="${pd.URL}" maxlength="32" placeholder="这里输入文章地址" title="文章地址"/>
                </td>
			</tr>
			<tr>
				<td><input type="text" name="IMG_LINK" id="IMG_LINK" value="${pd.IMG_LINK}" maxlength="32" placeholder="这里输入图片" title="图片"/></td>
			</tr>
			<tr>
				<td><input type="text" name="HEAD_IMAGE" id="HEAD_IMAGE" value="${pd.HEAD_IMAGE}" maxlength="32" placeholder="这里输入标题图片" title="标题图片"/></td>
			</tr>
			<tr>
				<td><input type="text" name="ACCOUNT_NAME" id="ACCOUNT_NAME" value="${pd.ACCOUNT_NAME}" maxlength="32" placeholder="这里输入来源" title="来源"/></td>
			</tr>
			<tr>
				<td><input type="text" name="CONTENT168" id="CONTENT168" value="${pd.CONTENT168}" maxlength="32" placeholder="这里输入概要" title="概要"/></td>
			</tr>
			<tr>
				<td><input type="text" name="DATE" id="DATE" value="${pd.DATE}" maxlength="32" placeholder="这里输入发布日期" title="发布日期"/></td>
			</tr>
			<tr>
				<td><input type="text" name="LAST_MODIFIED" id="LAST_MODIFIED" value="${pd.LAST_MODIFIED}" maxlength="32" placeholder="这里输入最后修改" title="最后修改"/></td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/ace-elements.min.js"></script>
		<script src="js/ace.min.js"></script>
		<script type="text/javascript" src="js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
        <script type="text/javascript" src="js/ueditor/ueditor.config.js"></script>
        <script type="text/javascript" src="js/ueditor/ueditor.all.min.js"></script>
        <script type="text/javascript" src="js/ueditor/lang/zh-cn/zh-cn.js"></script>
		<script type="text/javascript">
		$(window.parent.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>