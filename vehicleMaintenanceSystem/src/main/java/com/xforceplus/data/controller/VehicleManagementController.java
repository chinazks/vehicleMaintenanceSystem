package com.xforceplus.data.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xforceplus.data.bean.VehicleManagement;
import com.xforceplus.data.dao.VehicleManagementRepository;
import com.xforceplus.data.tools.JSONResult;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
@RequestMapping("/vehiclemanagement")
public class VehicleManagementController {
    private Logger logger= LoggerFactory.getLogger(UnitInformationController.class);
	
    @Autowired
    private VehicleManagementRepository vehicleManagementRepository;
    
  //显示车辆列表
    @RequestMapping("/list/vehicles")
    @ResponseBody
    public String vehiclemanagement(Map<String,Object> map, HttpServletRequest request){
    	int pages = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
        Page<VehicleManagement> vehicleManagementPage=vehicleManagementRepository.findAll(new PageRequest(pages-1,limit));
        List<Map<String,String>> listMap=new ArrayList<>();//传到前端
       for(int i=0;i<vehicleManagementPage.getContent().size();i++){
            Map<String,String> map1=new HashMap<>();
            map1.put("id",String.valueOf(vehicleManagementPage.getContent().get(i).getId()));
            map1.put("unitId",String.valueOf(vehicleManagementPage.getContent().get(i).getUnitId()));
            map1.put("equipmentName",vehicleManagementPage.getContent().get(i).getEquipmentName());
            map1.put("equipmentModel",vehicleManagementPage.getContent().get(i).getEquipmentModel());
            map1.put("licensePlateNumber",vehicleManagementPage.getContent().get(i).getLicensePlateNumber());
            map1.put("vehicleType",vehicleManagementPage.getContent().get(i).getVehicleType());
            map1.put("driverName",vehicleManagementPage.getContent().get(i).getDriverName());
            map1.put("remarke",vehicleManagementPage.getContent().get(i).getRemarke());
            listMap.add(map1);
        }
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", "");
        object.put("count",vehicleManagementRepository.findAll().size());
        object.put("data", listMap);
        return  object.toString();
    }
    
    //删除车辆信息
    @RequestMapping(value = "/deletevehicle")
    @ResponseBody
    public JSONResult deletevehicle(Map<String,Object> map,HttpServletRequest request){
        long id = Integer.parseInt(request.getParameter("id"));//--
        JSONResult response=null;
        String msg = "";
        try {
            vehicleManagementRepository.delete(id);
            msg = "单位信息数据删除成功！";
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
            msg = "单位信息数据删除失败！";
        }finally {
            response=JSONResult.build(200, msg, "");
        }
        return response;
    }
    
    //导出excel
   	@RequestMapping("/putout")
   	@ResponseBody
   	public JSONResult putout(HttpServletRequest request){
   		String path = Class.class.getClass().getResource("/").getPath()+"/static/download/";
 		ArrayList<VehicleManagement> list=new ArrayList<VehicleManagement>();
 		list = (ArrayList<VehicleManagement>) vehicleManagementRepository.findAll();
   		try {
   			File files=new File(path);
   			if (!files.exists()) {
   				files.mkdirs();
   			}
   			File file=new File(path+"/unitinformation.xls");
 			if(file.exists()){
 				file.delete();
 			}
   			WritableWorkbook book=Workbook.createWorkbook(new File(path+"/unitinformation.xls"));
   			//设置表名
   			WritableSheet sheet=book.createSheet("车辆信息表",0);
   			//设置表第一行
   			sheet.addCell(new Label(0,0,"装备名称"));
   			sheet.addCell(new Label(1,0,"装备型号"));
   			sheet.addCell(new Label(2,0,"车牌号"));
   			sheet.addCell(new Label(3,0,"车辆类型"));
   			sheet.addCell(new Label(4,0,"司机名称"));
   			sheet.addCell(new Label(5,0,"单位id"));	
   			sheet.addCell(new Label(5,0,"备注"));	
   			//添加数据
   			for(int i=0;i<list.size();i++){
   				sheet.addCell(new Label(0,i+1,list.get(i).getEquipmentName()));
   				sheet.addCell(new Label(1,i+1,list.get(i).getEquipmentModel()));
   				sheet.addCell(new Label(2,i+1,list.get(i).getLicensePlateNumber()));
   				sheet.addCell(new Label(3,i+1,list.get(i).getVehicleType()));
   				sheet.addCell(new Label(4,i+1,list.get(i).getDriverName()));
   				sheet.addCell(new Label(6,i+1,list.get(i).getUnitId()));
   				sheet.addCell(new Label(5,i+1,list.get(i).getRemarke()));
   			}
   			book.write();//将所做的操作写入
   			book.close();//关闭文件
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   		System.out.println();
   		return JSONResult.build(200, "生成excel", "download/车辆信息表.xls");
   	}

}
