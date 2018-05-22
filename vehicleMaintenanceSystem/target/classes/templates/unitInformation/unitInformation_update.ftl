<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/unitInformation/update" name="unitInformation" method="post">
    <input type="hidden" name="id" value="${unitInformation.id}">
    单位id,3:<input name ="unitId" type="text" value="${unitInformation.unitId}"><br/>
    装备型号,50:<input name ="equipmentModel" type="text" value="${unitInformation.equipmentModel}"><br/>
    装备名称,50:<input name ="equipmentName" type="text" value="${unitInformation.equipmentName}"><br/>
    配发时间,10:<input name ="dispensingTime" type="text" value="${unitInformation.dispensingTime}"><br/>
    数量,int:<input name ="stockQuantity" type="text" value="${unitInformation.stockQuantity}"><br/>
    技术状况,100:<input name ="technicalStatus" type="text" value="${unitInformation.technicalStatus}"><br/>
    <button type="submit">提交</button>
</form>
</body>
</html>