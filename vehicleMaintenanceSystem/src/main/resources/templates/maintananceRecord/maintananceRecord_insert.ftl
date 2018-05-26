<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="./plugins/layui/css/layui.css" media="all">
</head>

<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>新增unit信息</legend>
</fieldset>
<form class="layui-form layui-form-pane" action="">
    <div class="layui-form-item">
        <table>
            <tr>
                <td>
                    <label class="layui-form-label">单位ID</label>
                    <div class="layui-input-inline">
                        <select name="unitid" lay-verify="required">
                            <option value=""></option>
                            <option value="101">单位1</option>
                            <option value="102">单位2</option>
                            <option value="103">单位3</option>
                            <option value="104">单位4</option>
                            <option value="105">单位5</option>
                        </select>
                    </div>
                </td>
                /*{field:'unitId', width:60, title:'单位id'}
                ,{field:'unitName', width:120, title:'单位名称'}
                ,{field:'licensePlateNumber', width:120, title:'车牌号'}
                ,{field:'', width:120, title:'司机名称'}
                ,{field:'', width:120, title:'库房号',sort: true}
                ,{field:'', width:120, title:'车辆类型'}
                ,{field:'', width:120, title:'配件id'}
                ,{field:'', width:120, title:'配件使用情况'}
                ,{field:'', width:120, title:'配件缺少情况'}
                ,{field:'', width:120, title:'维修价格'}
                ,{field:'', width:120, title:'维修时间',sort: true}
                ,{field:'right', title: '操作', width:200, height:80, toolbar:"#barDemo"}*/
                <td>
                    <label class="layui-form-label">车牌号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="licensePlateNumber" autocomplete="off" maxlength="10" class="layui-input" lay-verify="require|lengthLess50">
                    </div>
                </td>
                <td>
                    <label class="layui-form-label">司机名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="driverName" autocomplete="off" maxlength="20"  class="layui-input" lay-verify="require|lengthLess20">
                    </div>
                </td>
            </tr>
            <tr><td colspan="3" height="10"></td></tr>
            <tr>
                <td>
                    <label class="layui-form-label">库房号</label>
                    <div class="layui-input-inline">
                        <input type="text" name="storeRoom" id="date" maxlength="4" autocomplete="off" class="layui-input" lay-verify="require" >
                    </div>
                </td>
                <td>
                    <label class="layui-form-label">车辆类型</label>
                    <div class="layui-input-inline">
                        <input type="number" name="vehicleType" autocomplete="off" maxlength="10" class="layui-input" lay-verify="require|stockQuantity">
                    </div>
                </td>
                <td>
                    <label class="layui-form-label">配件id</label>
                    <div class="layui-input-inline">
                        <input type="text" name="accessoriesId" autocomplete="off" maxlength="10" class="layui-input" lay-verify="require|technicalStatus">
                    </div>
                </td>
            </tr>

            <tr>
                <td>
                    <label class="layui-form-label">配件使用情况</label>
                    <div class="layui-input-inline">
                        <input type="text" name="useOfAccessories" id="date" autocomplete="off" maxlength="100" class="layui-input" lay-verify="require" >
                    </div>
                </td>
                <td>
                    <label class="layui-form-label">配件缺少情况</label>
                    <div class="layui-input-inline">
                        <select name="lackOfAccessories" lay-verify="required">
                            <option value=""></option>
                            <option value="0">正常</option>
                            <option value="1">缺少</option>
                        </select>
                    </div>
                </td>
                <td>
                    <label class="layui-form-label">维修价格</label>
                    <div class="layui-input-inline">
                        <input type="text" name="maintenancePrice" autocomplete="off" maxlength="100" class="layui-input" lay-verify="require|price">
                    </div>
                </td>
            </tr>

            <tr>
                <td>
                    <label class="layui-form-label">维修时间</label>
                    <div class="layui-input-inline">
                        <input type="text" name="maintenanceTime" id="date" autocomplete="off" class="layui-input" lay-verify="require" readonly>
                    </div>
                </td>
                <td>
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-inline">
                        <input type="number" name="remark" autocomplete="off" maxlength="500" class="layui-input">
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="unitsubmit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <a href="main" class="layui-btn layui-btn-normal">返回</a>
        </div>
    </div>
</form>

<script src="./plugins/layui/layui.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form,
                layer = layui.layer,
                layedit = layui.layedit,
                laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
        });

        //自定义验证规则
        form.verify({
            require:[
                /^.{1,}$/
                ,'字段必填'
            ],
            lengthLess20:[
                /^.{1,20}$/
                ,'长度小于50'
            ],
            stockQuantity:[
                /^[0-9]{8}$/
                ,'数量不能大于1亿'
            ],
            technicalStatus:[
                /^.{1,100}$/
                ,'长度小于100'
            ],
            price:[
                /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/
                    ,'价格填写有误'
            ]
        });

        //监听提交
        var $ = layui.$;
        form.on('submit(unitsubmit)', function(datas) {
            $.ajax({
                url: '/unitInformation/insert',
                type: "POST",
                data:datas.field,
                dataType: "json",
                success: function(data){
                    if(data.code==200){
                        layer.msg(data.msg, {icon: 6});
                    }else{
                        layer.msg("新增失败", {icon: 5});
                    }
                    location.href="main";
                }
            });
            return false;
        });
    });
</script>

</body>

</html>