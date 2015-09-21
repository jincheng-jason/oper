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
    <base href="<%=basePath%>"><!-- jsp文件头和头部 -->
    <%@ include file="../system/admin/top.jsp"%>
    <script type="text/javascript">


        //保存
        function save(){
            if($("#title").val()==""){
                $("#title").tips({
                    side:3,
                    msg:'请输入标题',
                    bg:'#AE81FF',
                    time:2
                });
                $("#title").focus();
                return false;
            }
            $("#begin_at").val($("#begin_at").val().substr(0,10) + " 00:00:00.0");
            $("#end_at").val($("#end_at").val().substr(0,10) + " 00:00:00.0");
            $("#Form").submit();
        }

    </script>
</head>
<body>

<div class="container-fluid" id="main-container">


    <div id="zhongxin">
        <div id="page-content" class="clearfix">
            <div class="row-fluid">
                <div class="row-fluid">
                    <form action="<%=basePath%>/event/save.do" name="Form" id="Form"  method="post">
                        <div style="width:100%">
                            <input type="text" id="title" name="title" maxlength="32" placeholder="这里输入标题" title="标题"/>
                        </div>

                        <div style="width:100%">
                            <script type="text/plain" id="myEditor" style="width:100%;height:450px"></script>
                        </div>
                        <br/>
                        <div style="width:50%">
                            <input class="span10 date-picker" name="begin_at" id="begin_at" value="${pd.begin_at}" type="text" data-date-format="yyyy-mm-dd"  readonly="readonly" auto placeholder="开始时间" title="开始时间"/>
                        </div>
                        <div style="width:50%">
                            <input class="span10 date-picker" name="end_at" id="end_at" value="${pd.end_at}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="结束时间" title="结束时间"/>
                        </div>
                        <div class="page-header position-relative">
                            <a id="submitLink" class="btn btn-mini btn-primary">保存</a>
                            <a id="cancelLink" class="btn btn-mini btn-danger">取消</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>

</div>




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
        $('.date-picker').datepicker({
            format: "yyyy-mm-dd",
            autoclose: true
        });

        var editor = UE.getEditor("myEditor");
        $('#submitLink').removeAttr("href")
                .click(function() {
                    if($("#title").val()==""){
                        $("#title").tips({
                            side:3,
                            msg:'请输入标题',
                            bg:'#AE81FF',
                            time:2
                        });
                        $("#title").focus();
                        return false;
                    }
                    $("#begin_at").val($("#begin_at").val().substr(0,10) + " 00:00:00.0");
                    $("#end_at").val($("#end_at").val().substr(0,10) + " 00:00:00.0");
                    if(editor.hasContents()){  //提交条件满足时提交内容
                        editor.sync();           //此处的editor是页面实例化出来的编辑器对象
                        $('#Form').submit();
                        $("#zhongxin").hide();
                        $("#zhongxin2").show();
                    }else{
                        alert("案例描述 不能为空！！ ");
                    }


                }).css({
                    'cursor' : 'pointer'
                });
        $('#cancelLink').removeAttr("href")
                .click(function() {
                    top.Dialog.close();
                }).css({
                    'cursor' : 'pointer'
                });

    });

</script>
</body>
</html>