package com.xforceplus.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.xforceplus.data.bean.VehicleManagement;
import com.xforceplus.data.dao.VehicleManagementRepository;
import com.xforceplus.data.tools.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vehiclemanagement")
public class VehicleManagermentController {
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
        object.put("count",vehicleManagementPage.getNumber());
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
            response=JSONResult.build(1, msg, "");
        }
        return response;
    }

}
