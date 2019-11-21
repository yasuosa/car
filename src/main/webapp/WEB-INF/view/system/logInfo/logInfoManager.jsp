<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>日志管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/public.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/layui_ext/dtree/font/dtreefont.css">


</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">登陆名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="loginname" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">登陆ip:</label>
            <div class="layui-input-inline">
                <input type="text" name="loginip" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">登入时间:</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="startTime" id="starttime"
                       placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">登出时间:</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="endTime" id="endtime" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
        </div>

    </div>
    <div class="layui-form-item" style="text-align: center">
        <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search layui-btn-sm"
                id="doSearch">查询
        </button>
        <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh layui-btn-sm">重置
        </button>
    </div>
</form>

<!-- 搜索条件结束 -->


<!-- 数据表格开始 -->
<table class="layui-hide" id="logInfoTable" lay-filter="logInfoTable"></table>
<div style="display: none;" id="logInfoToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="batchDelete">批量删除</button>
</div>
<div id="logInfoBar" style="display: none;">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->


<script src="${pageContext.request.contextPath }/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.extend({
        dtree: '${pageContext.request.contextPath}/resources/layui_ext/dist/dtree'
    }).use(['jquery', 'layer', 'form', 'table', 'dtree', 'laydate'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var dtree = layui.dtree;
        var laypage = layui.laypage;
        var laydate = layui.laydate;

        //日期时间选择器
        laydate.render({
            elem: '#starttime'
            , type: 'datetime'
        });
        //日期时间选择器
        laydate.render({
            elem: '#endtime'
            , type: 'datetime'
        });

        //渲染数据表格
        tableIns = table.render({
            elem: '#logInfoTable'   //渲染的目标对象
            , url: '${pageContext.request.contextPath}/logInfo/loadAllLogInfo.action' //数据接口
            , title: '用户数据表'//数据导出来的标题
            , toolbar: "#logInfoToolBar"   //表格的工具条
            , height: 'full-100'
            , cellMinWidth: 100 //设置列的最小默认宽度
            , page: true  //是否启用分页
            , cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', align: 'center'}
                , {field: 'loginname', title: '登陆名称', align: 'center'}
                , {field: 'loginip', title: '登陆地址ip', align: 'center'}
                , {field: 'logintime', title: '登陆时间', align: 'center'}
                , {fixed: 'right', title: '操作', toolbar: '#logInfoBar', align: 'center'}
            ]]
        })
        //模糊查询
        $("#doSearch").click(function () {
            var params = $("#searchFrm").serialize();
            // alert(params);
            tableIns.reload({
                url: "${pageContext.request.contextPath}/logInfo/loadAllLogInfo.action?" + params
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(logInfoTable)", function (obj) {
            switch (obj.event) {
                case 'batchDelete':
                    deleteBatch();
                    break;
            }

        })
        //监听行工具事件
        table.on('tool(logInfoTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除

                layer.confirm('真的删除【' + data.loginname + '】行么', function (index) {
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/logInfo/deleteLogInfo.action", {id: data.id}, function (res) {
                        layer.msg(res.msg);
                        tableIns.reload();
                    })
                });
            }

        });


        //批量删除
        function deleteBatch() {
            //得到选中的数据行
            var checkStatus = table.checkStatus('logInfoTable')
                , data = checkStatus.data;
            var params = "";
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += "ids=" + item.id;
                }
                params += "&ids=" + item.id;
            });
            layer.confirm('真的删除选中的这些日志么', function (index) {
                //向服务端发送删除指令
                $.post("${pageContext.request.contextPath}/logInfo/deleteBatchLogInfo.action", params, function (res) {
                    layer.msg(res.msg);
                    tableIns.reload();
                })
            });
        }



    });

</script>
</body>
</html>