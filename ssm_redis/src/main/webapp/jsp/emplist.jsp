<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@include file="db/db.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${path }</title>


    <!--
    不以/开始的相对路径 找资源 以当前自愿的路径为基准  容易出问题
    以/开始的相对路径 找资源 以服务器为准（http://localhost:8080）需要加上项目名称
     -->
    <link href="${path }/static/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <script type="text/javascript"
            src="${path }/static/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript"
            src="${path }/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<form class="form-upload" action="/file/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="确定"/>
</form>
<form class="form-upload" action="/excleIn" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="submit" value="确定"/>
</form>
<a href="/file/do_download?fileUrl=http://img.zcool.cn/community/01f09e577b85450000012e7e182cf0.jpg"><img src="http://img.zcool.cn/community/01f09e577b85450000012e7e182cf0.jpg" style="height: 200px;width: 200px;"></a>
<img width="100" src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3569479631,993103025&fm=27&gp=0.jpg"/>
<div class="container">
    <div id="fenye">
        <%@include file="emplistpage.jsp" %>
    </div>
</div>


<!-- 员工添加的模态框 -->
<div class="modal fade" id="addModel" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal formtable">
                    <input name="id" type="hidden" id="eid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" class="form-control"
                                   id="empName_add_input" placeholder="empName"> <span
                                class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control"
                                   id="email_add_input" placeholder="email@abc.com"> <span
                                class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline"> <input type="radio"
                                                                name="gender" id="gender1_add_input" value="0"
                                                                checked="checked"> 女
                            </label> <label class="radio-inline"> <input type="radio"
                                                                         name="gender" id="gender2_add_input" value="1">
                            男
                        </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-4">
                            <!-- 部门提交部门id即可 -->
                            <select class="form-control" id="demp" name="dId">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="savaemp">保存</button>
                <button type="button" class="btn btn-primary" id="editemp" style="display: none;">保存</button>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">


    //分页
    function page(obj) {
        console.info(obj);
        $.ajax({
            url: "${path }/emplist",
            data: {
                page: obj
            },
            type: "post",
            success: function (data) {
                console.info(data);
                //$("#fenye").html(data);
            },
            error: function () {
                alert(" 失败！");
            }
        })
    }

    //删除员工
    function delbyid(obj) {
        console.info(obj);
        $.ajax({
            url: "${path }/delemp",
            data: {
                id: obj
            },
            type: "get",
            success: function (data) {

                page('${pi.pageNum}');
            },
            error: function () {
                alert(" 失败！");
            }
        })
    }

    //查询员工
    function selbyid(obj) {
        console.info(obj);
        $.ajax({
            url: "${path }/selemp",
            data: {
                id: obj
            },
            dataType: "json",
            type: "get",
            success: function (data) {
                console.info(data);
                $("#empName_add_input").val(data.name);
                $("#email_add_input").val(data.email);
                $("input[name='gender']:eq("+data.gender+")").attr("checked", true);
                $("#eid").val(data.id);
                //console.info($("input[name='gender']:eq(0)").val());
                $("#savaemp").attr("style","display:none");
                $("#editemp").css("display","");
                addemp();
            },
            error: function () {
                alert(" 失败！");
            }
        })
    }

    //显示模态框
    function addemp() {
        $.ajax({
            url: "${path}/demp",
            dataType: "json",
            success: function (data) {
                var context;
                for (var i = 0; i < data.length; i++) {
                    context += "<option value='" + data[i].id + "'>"
                        + data[i].name + "</option>"
                }
                $("#demp").html(context);
                $('#addModel').modal({
                    backdrop: "static"
                })
            },
            error: function () {
                alert("失败");
            }
        })
    }

    $(function () {
        //保存员工
        $("#savaemp").click(function () {
            $.ajax({
                url: "${path}/addemp",
                type: "post",
                data: $(".formtable").serialize(),
                dataType: "json",
                success: function (data) {
                    console.info(data)
                    alert(data.name);
                    $('#addModel').modal('hide')
                    window.location.reload();
                    page('${pi.pages}');
                },
                error: function () {
                    alert("失败")
                }
            })

        })
        $("#editemp").click(function () {
            console.info($("form").serialize());
            $.ajax({
                url: "${path}/editemp",
                type: "post",
                data: $(".formtable").serialize(),
                dataType: "json",
                success: function (data) {
                    console.info(data)
                    alert(data.name);
                    window.location.reload();
                    $('#addModel').modal('hide');
                    page('${pi.pageNum}');
                },
                error: function () {
                    alert("失败")
                }
            })

        })
    })

</script>
</html>