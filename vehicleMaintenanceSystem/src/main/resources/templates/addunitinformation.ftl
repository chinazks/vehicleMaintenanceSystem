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
        <legend>新增unitinformation</legend>
    </fieldset>
	<form class="layui-form layui-form-pane" action="/unitInformation/insert" method="post">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">单位ID</label>
                <div class="layui-input-block">
                    <input type="text" name="unitid" autocomplete="off" class="layui-input" lay-verify="unitid">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">装备型号</label>
                <div class="layui-input-inline">
                    <input type="text" name="equipmentModel" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">装备名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="equipmentName" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">配发时间</label>
                <div class="layui-input-block">
                    <input type="text" name="dispensingTime" id="date" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">数量</label>
                <div class="layui-input-inline">
                    <input type="number" name="stockQuantity" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">技术状况</label>
                <div class="layui-input-inline">
                    <input type="text" name="technicalStatus" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
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
                unitid: function(value) {
                    if (value.length < 1) {
                        return '单位id未填写';
                    }
                },
                pass: [/(.+){6,12}$/, '密码必须6到12位'],
                
                content: function(value) {
                    layedit.sync(editIndex);
                }
            });

            //监听提交
           /* form.on('submit(demo1)', function(data) {
                layer.alert(JSON.stringify(data.field), {
                    title: '最终的提交信息'
                })
                return false;
            });*/
        });
    </script>

</body>

</html>