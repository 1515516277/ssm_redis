<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@include file="db/db.jsp" %>
<html>
<head>
    <title>利润</title>
</head>
<link href="${path }/static/bootstrap/css/bootstrap.min.css"
      rel="stylesheet">
<script type="text/javascript"
        src="${path }/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
        src="${path }/static/bootstrap/js/bootstrap.min.js"></script>
<body>
<form action="/sheetlr/sheet1" method="post" enctype="multipart/form-data">
    公司：
    <select name="company" >
        <option value="xxx">xxx</option>
    </select>
    年：
    <select name="time" >
        <option>2018</option>
    </select>
    月：
    <select name="month">
        <option>01</option>
        <option>02</option>
        <option>03</option>
        <option>04</option>
        <option>05</option>
        <option>06</option>
        <option>07</option>
        <option>08</option>
        <option>09</option>
        <option>10</option>
        <option>11</option>
        <option>12</option>
    </select>
    <input type="file" name="file">
    <input type="submit">
</form>
<div>
    <form action="/sheetlr/sheetout" method="post" id="findinfo" onsubmit="return sheetout()">
        <select name="company" id="company">
            <option value="">请选择</option>
            <option value="xxx">xxx</option>
        </select>
        <select name="time" id="year">
            <option>2018</option>
        </select>
        <select name="month" id="month">
            <option>01</option>
            <option>02</option>
            <option>03</option>
            <option>04</option>
            <option>05</option>
            <option>06</option>
            <option>07</option>
            <option>08</option>
            <option>09</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
        </select>
        <input onclick="findinfo()" type="button" value="查询">
        <input type="submit" value="导出">
    </form>
</div>

<form id="becauseval">
    <input type="hidden" value="" id="time" name="month">
    <input type="hidden" value="" id="id" name="id">
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">流动资产-变动原因分析</h4>
                </div>
                <div class="modal-body"><textarea style="width: 560px;height: 300px" name="val" id="textarea"></textarea></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <input type="button" class="btn btn-primary" onclick="sub()" value="提交">
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</form>

<div id="info">

</div>

<script>
    var m=${month};
    var y=${year};
    if(m<10){
        m="0"+m;
    }
    $("#month").val(m);
    $("#year").val(y);
    function sheetout() {
        var company =$("#company").val();

        if(company ==null ||　company=='请选择'|| company==''){
            alert("请选择公司")
            return false;
        }
        return true;

    }
    function findinfo() {
        var company =$("#company").val();
        if(company ==null ||　company=='请选择' || company==''){
            alert("请选择公司")
            return;
        }
        $.ajax({
            url:"/sheetlr/sheet",
            data:$("#findinfo").serialize(),
            success:function (data) {
                    $("#info").html(data);
            }
        })
    }
    function fx(o1, o2) {
        $("#id").val(o1);
        $("#time").val(o2);
        $.ajax({
            url: "/sheetlr/findanalyze",
            data: {
                id: o1,
                month: o2
            },
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data.code == 100) {
                    $("#textarea").val(data.extend.val);
                }
            },
            error: function () {

            }
        })
    }

    function sub() {
        $.ajax({
            url:"/sheetlr/editanalyze",
            data:$("#becauseval").serialize(),
            type:"post",
            dataType:"json",
            success:function (data) {
                $('#myModal').modal('hide')
            },
            error:function () {

            }
        })
    }

</script>
</body>
</html>
