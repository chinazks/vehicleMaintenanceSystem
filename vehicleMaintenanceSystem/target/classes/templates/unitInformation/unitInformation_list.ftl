<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
    <title>Table</title>
    <#--<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/plugins/layui/css/layui.css" media="all" />-->
    <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/css/global.css" media="all" />
    <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/css/table.css" />
    <style>
        .pageTest{
            text-align: center;
        }
    </style>
</head>

<body>
<div class="admin-main">

    <blockquote class="layui-elem-quote">
        <a href="/unitInformation/insert" class="layui-btn layui-btn-small" id="add">
            <i class="layui-icon">&#xe608;</i> 添加信息
        </a>
        <a href="#" class="layui-btn layui-btn-small" id="import">
            <i class="layui-icon">&#xe608;</i> 导入信息
        </a>
        <a href="#" class="layui-btn layui-btn-small">
            <i class="fa fa-shopping-cart" aria-hidden="true"></i> 导出信息
        </a>
        <a href="#" class="layui-btn layui-btn-small" id="getSelected">
            <i class="fa fa-shopping-cart" aria-hidden="true"></i> 获取全选信息
        </a>
        <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
            <i class="layui-icon">&#xe615;</i> 搜索
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>单位信息</legend>
        <div class="layui-field-box layui-form" style="height: 1500px">
            <table class="layui-table admin-table">
                <thead>
                <tr>
                    <th style="width: 30px;">序号</th>
                    <th>单位id</th>
                    <th>单位名称</th>
                    <th>装备型号</th>
                    <th>装备名称</th>
                    <th>配发时间</th>
                    <th>数量</th>
                    <th>技术状况</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="content" style="height: 60%;">
                <#if page1?exists>
                    <#list page1 as item>
                    <tr>
                        <td>${ item_index }</td>
                        <td>${ item.unitId }</td>
                        <td>${ item.unitName}</td>
                        <td>${ item.equipmentModel }</td>
                        <td>${ item.equipmentName }</td>
                        <td>${ item.dispensingTime }</td>
                        <td>${ item.stockQuantity }</td>
                        <td>${ item.technicalStatus }</td>
                        <td>
                            <a href="/detail-1" target="_blank" class="layui-btn layui-btn-normal layui-btn-mini" style="display:inline-block;height:19px;line-height:19px;padding:1px 10px;background-color:#1E9FFF;color:#fff;white-space:nowrap;text-align:center;font-size:14px;border:none;border-radius:2px;cursor:pointer;opacity:.9;filter:alpha(opacity=90)">预览</a>
                            <a href="/unitInformation/update/${item.id}" data-name="${item.id}" data-opt="edit" class="layui-btn layui-btn-mini" style="display:inline-block;height:19px;line-height:19px;padding:1px 10px;background-color:#009688;color:#fff;white-space:nowrap;text-align:center;font-size:14px;border:none;border-radius:2px;cursor:pointer;opacity:.9;filter:alpha(opacity=90)">编辑</a>
                            <a href="/unitInformation/delete/${unitInformationPage.number}/${item.id}" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini" style="display:inline-block;height:19px;line-height:19px;padding:1px 10px;background-color:##FF5722;color:#fff;white-space:nowrap;text-align:center;font-size:14px;border:none;border-radius:2px;cursor:pointer;opacity:.9;filter:alpha(opacity=90)">删除</a>
                        </td>
                    </tr>
                    </#list>
                </#if>
                </tbody>
                <tfoot style="">
                    <tr>
                        <div id="page"></div>
                    </tr>
                </tfoot>
            </table>
        </div>
    </fieldset>
</div>
<input type="hidden" id="pageSize" value="${unitInformationPage.totalPages}">
<input type="hidden" id="content" value="" />
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/js/jquery-2.1.1.min.js"></script>
<script src="${springMacroRequestContext.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/layui/lay/modules/laypage.js"></script>
<script>
    layui.use(['laypage', 'layer'], function(){
        var laypage = layui.laypage
                ,layer = layui.layer;

        var pageSize=$("#pageSize").val();

        //完整功能
        laypage.render({
            elem: 'page'
            ,count: 5
            ,layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            ,jump: function(obj){
                console.log(obj)
            }
        });




    });
</script>
</script>
</body>

