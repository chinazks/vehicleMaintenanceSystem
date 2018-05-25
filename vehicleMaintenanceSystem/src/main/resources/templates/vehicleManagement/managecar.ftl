<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>table模块快速使用</title>
    <link rel="stylesheet" href="./plugins/layui/css/layui.css" media="all">
	<style type="text/css">
	.layui-table-cell{
	    height:34px;
	    line-height: 34px;
	}
	</style>
</head>

<body>
<div style="width:500px;height:10px;"></div>
<div class="demoTable">
     <a class="layui-btn layui-btn-normal  layui-btn-sm" lay-event="detail">新增</a>
     <a class="layui-btn layui-btn-normal  layui-btn-sm" lay-event="detail">导入</a>
     <a class="layui-btn layui-btn-normal  layui-btn-sm" lay-event="detail">导出excel</a>
    <div class="layui-inline">
        <input class="layui-input" name="keyword" id="demoReload" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>
<table class="layui-hide" id="managecar" lay-filter="useruv"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary  layui-btn-sm" lay-event="detail">查看</a>
    <a class="layui-btn  layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger  layui-btn-sm" lay-event="del">删除</a>
</script>

    <script src="./plugins/layui/layui.js"></script>
    <script>
        layui.use('table', function() {
            var table = layui.table;
              //方法级渲染
        table.render({
            elem: '#managecar'
            ,url: '/vehiclemanagement/list/vehicles'
            ,cols: [[
                {checkbox: true, fixed: true}
                ,{field:'equipmentName', title:'装备名称', width:100}
                ,{field:'equipmentModel', title:'装备型号', width:100, sort: true}
                ,{field:'licensePlateNumber', title:'装备型号', width:100}
                ,{field:'vehicleType', title:'车牌号', width:100}
                ,{field:'driverName', title:'司机名称', width:100, sort: true}
                ,{field:'unitId', title:'单位id', width:100, sort: true}
                ,{field:'remarke', title:'备注', width:100, sort: true}
                ,{field:'right', title: '操作', width:300, height:80,toolbar:"#barDemo"}
            ]]
            ,id: 'testReload'
            ,page: true
            ,height: 600
        });

        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#demoReload');

                table.reload('testReload', {
                    where: {
                        keyword: demoReload.val()
                    }
                });
            }
        };

        //监听表格复选框选择
        table.on('checkbox(useruv)', function(obj){
            console.log(obj)
        });
        //监听工具条
        table.on('tool(useruv)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            } else if(obj.event === 'del'){
                layer.confirm('确定删除车牌号'+data.vehicleType+'的车辆吗', function(index){
                    $.ajax({
                        url: "/vehiclemanagement/deletevehicle",
                        type: "POST",
                        data:{"id":data.id},
                        dataType: "json",
                        success: function(data){
                            if(data.code==200){
                                obj.del();
                                layer.close(index);
                                layer.msg("删除成功", {icon: 6});
                            }else{
                                layer.msg("删除失败", {icon: 5});
                            }
                        }

                    });
                });
            } else if(obj.event === 'edit'){
                layer.prompt({
                    formType: 2
                    ,title: ''
                    ,value: data.uv
                }, function(value, index){
                    EidtUv(data,value,index,obj);
                });
            }
        });

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        function  EidtUv(data,value,index,obj) {
            $.ajax({
                url: "UVServlet",
                type: "POST",
                data:{"uvid":data.id,"memthodname":"edituv","aid":data.aid,"uv":value},
                dataType: "json",
                success: function(data){
                    if(data.state==1){
                        layer.close(index);
                        //同步更新表格和缓存对应的值
                        obj.update({
                            uv: value
                        });
                        layer.msg("修改成功", {icon: 6});
                    }else{
                        layer.msg("修改失败", {icon: 5});
                    }
                }

            });
        }


    });
</script>
</body>

</html>