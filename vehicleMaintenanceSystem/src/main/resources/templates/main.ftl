<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>table模块快速使用</title>
    <link rel="stylesheet" href="./plugins/layui/css/layui.css" media="all">
</head>

<body>
<div style="width:500px;height:30px;padding-left:20px;"></div>
<div class="layui-btn-group demoTable">
  <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
  <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
  <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div>
    <table class="layui-table" lay-data="{height:315, url:'/unitInformation/list/0', page:true, id:'test',height: 'full-25'}" lay-filter="test" id="test">
        <thead>
            <tr>
                <th lay-data="{field:'id', width:80, sort: true}">ID</th>
                <th lay-data="{field:'unitId', width:80}">单位id</th>
                <th lay-data="{field:'unitName', width:80, sort: true}">装备型号</th>
                <th lay-data="{field:'equipmentName', width:80}">装备名称</th>
                <th lay-data="{field:'dispensingTime', width:177}">配发时间</th>
                <th lay-data="{field:'stockQuantity', width:80, sort: true}">数量</th>
                <th lay-data="{field:'technicalStatus', width:80, sort: true}">技术状况</th>
                <th lay-data="{field:'ext1', width:80}">备用字段1</th>
                <th lay-data="{field:'ext2', width:135, sort: true}">备用字段2</th>
                <th lay-data="{field:'ext3', width:135, sort: true}">备用字段3</th>
            </tr>
        </thead>
    </table>

    <script src="./plugins/layui/layui.js"></script>
    <script>
        layui.use('table', function() {
            var table = layui.table;
        });
    </script>
</body>

</html>