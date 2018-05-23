<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>unit</title>
    <link rel="stylesheet" href="./plugins/layui/css/layui.css" media="all">
	<style type="text/css">
	.layui-table-cell{
	    height:34px;
	    line-height: 34px;
	}
	</style>
</head>

<body>
<div style="width:500px;height:30px;padding-left:20px;"></div>
<a href="addunitinformation" class="layui-btn">新增</a>
<table class="layui-hide" id="unit" lay-filter="unitdata"></table>

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
        elem: '#unit'
        ,url: '/unitInformation/list/unitinformations'
        ,cols: [[
            {field:'unitName', width:150, title:'单位名称'}
            ,{field:'equipmentName', width:150, title:'装备名称'}
            ,{field:'dispensingTime', width:150, title:'配发时间'}
            ,{field:'stockQuantity', width:80, title:'数量',sort: true}
            ,{field:'technicalStatus', width:150, title:'技术状况'}
            ,{field:'right', title: '操作', width:250, height:80, toolbar:"#barDemo"}
        ]]
        ,id: 'testReload'
        ,page: true
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

    //监听工具条
    table.on('tool(unitdata)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.id + ' 的查看操作');
            layer.open({
  				title: '详细信息'
 				 ,content: '<div>单位id:'+data.unitId+'</div><div>单位名称:'+data.unitName+'</div><div>装备名称:'+data.equipmentName+'</div><div>配发时间:'+data.dispensingTime+'</div><div>数量：'+data.stockQuantity+'</div><div>技术状况:'+data.technicalStatus+'</div>'
				});   
        } else if(obj.event === 'del'){
            layer.confirm('确定删除“'+data.unitName+'”的数据吗', function(index){
                console.log(data);
                $.ajax({
                    url: '/unitInformation/deleteunitinformation',
                    type: "POST",
                    data:{"id":data.id},
                    dataType: "json",
                    success: function(data){
                        if(data.code==1){
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
         /*  layer.open({
 				 type: 1,
 				 title:'编辑',
				 skin: 'layui-layer-rim', //加上边框
				 area: ['500px', '500'], //宽高
				 content: '<form class="layui-form" action=""><table class="layui-table">'+
				 '<tr><td>单位名称</td><td><input type="text" name="unitId" lay-verify="required" class="layui-input"></td><tr>'+
				 '<tr><td>装备名称</td><td><input type="text" name="unitId" lay-verify="required" class="layui-input"></td><tr>'+
				 '<tr><td>配发时间</td><td><input type="text" name="unitId" lay-verify="required" class="layui-input"></td><tr>'+
				 '<tr><td>数量</td><td><input type="text" name="unitId" lay-verify="required" class="layui-input"></td><tr>'+
				 '<tr><td>技术状况</td><td><input type="text" name="unitId" lay-verify="required" class="layui-input"></td><tr></table></form>'
				});*/
		location.href="addunitinformation"
        }
    });

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    
    //编辑信息
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