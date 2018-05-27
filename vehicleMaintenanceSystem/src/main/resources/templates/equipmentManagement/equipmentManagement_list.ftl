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
<a href="/addequipmentManagement" class="layui-btn">新增</a>
<button type="button" class="layui-btn" id="leadexcel"><i class="layui-icon"></i>上传文件</button>
<a href="#" id="excelurl" class="layui-btn">生成excel文件</a>
<a href="#" id="downloadexcel" class="layui-btn">下载excel文件</a>
<table class="layui-hide" id="unit" lay-filter="unitdata"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary  layui-btn-sm" lay-event="detail">查看</a>
    <a href="#" class="layui-btn  layui-btn-sm" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger  layui-btn-sm" lay-event="del">删除</a>
</script>

<script src="./plugins/layui/layui.js"></script>
 <script>
    layui.use(['table','upload'], function() {
        var table = layui.table
        ,$ = layui.jquery
  		,upload = layui.upload;
       
         upload.render({
		    elem: '#leadexcel'
		    ,method: 'post'
		    ,url: '/equipmentManagement/lead'
		    ,accept: 'file'
		    ,exts:'xls' //上传文件后缀
		    ,done: function(res){ 
			    if(res.code == 0){
			   	 	 layer.msg("文件上传成功", {icon: 6});
			    }
			 }
		    ,error: function(){
		     	layer.msg("文件上传失败", {icon: 5});
		    }
		  });
        
    //方法级渲染
    table.render({
        elem: '#unit'
        ,url: '/equipmentManagement/list'
        ,cols: [[
        	{field:'storeRoom', width:80, title:'库房号'}
            ,{field:'licensePlateNumber', width:80, title:'车牌号'}
            ,{field:'vehicleType', width:80, title:'车辆类型'}
            ,{field:'accessoriesId', width:80, title:'配件id'}
            ,{field:'accessoriesName', width:80, title:'配件名',sort: true}
            ,{field:'specifications', width:80, title:'规格'}
            ,{field:'originalFactoryNumber', width:80, title:'原厂编号'}
            ,{field:'unit', width:80, title:'单位'}
            ,{field:'warehouseUnitPrice', width:80, title:'入库单价'}
            ,{field:'stock', width:80, title:'库房总库存'}
            ,{field:'goodsNum', width:80, title:'货位号'}
            ,{field:'deliveryDate', width:80, title:'到货日期'}
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
        
      		//弹出窗编辑，已注释
         /* layer.open({
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
		//location.href="addunitinformation";
		
			location.href="/updateunitinformation/"+data.id;
           
        }
    });

	$('#excelurl').on('click', function(){
		layer.msg('正在生成excel', {
  			icon: 16
  			,shade: 0.1
		});
		$.ajax({
        	url: '/unitInformation/putout',
       	 	type: "POST",
        	dataType: "json",
        	success: function(data){
        		if(data.code==200){
        			$('#downloadexcel').attr("href",data.data);
        		}
        		 layer.msg("已生成excel文件", {icon: 6});
        	}
   		 });
    });


});

</script>
</body>

</html>