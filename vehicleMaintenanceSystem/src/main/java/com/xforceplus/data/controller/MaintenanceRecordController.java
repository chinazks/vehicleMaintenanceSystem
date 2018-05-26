package com.xforceplus.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.xforceplus.data.bean.MaintenanceRecord;
import com.xforceplus.data.dao.MaintenanceRecordRepository;
import com.xforceplus.data.dao.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/26 0026.
 */
@Controller
@RequestMapping("/maintenanceRecord")
public class MaintenanceRecordController {

 @Autowired
 private MaintenanceRecordRepository maintenanceRecordRepository;
 @Autowired
 private UnitRepository unitRepository;
 //获取所有unitinformation数据
 @RequestMapping("/list")
 @ResponseBody
 public String equmentManagementList(@RequestParam("page") int pages,@RequestParam("limit") int limit){
  Page<MaintenanceRecord> maintenanceRecordPage=maintenanceRecordRepository.findAll(new PageRequest(pages-1,limit));
  List<Map<String,String>> listMap=new ArrayList<>();//传到前端
  for(int i=0;i<maintenanceRecordPage.getContent().size();i++){
   Map<String,String> map1=new HashMap<>();
   map1.put("id",String.valueOf(maintenanceRecordPage.getContent().get(i).getId()));
   map1.put("unitId",String.valueOf(maintenanceRecordPage.getContent().get(i).getUnitId()));
   map1.put("unitName",unitRepository.findByUnitId(maintenanceRecordPage.getContent().get(i).getUnitId()).getUnitName()+" "+String.valueOf(maintenanceRecordPage.getContent().get(i).getUnitId()));
   map1.put("licensePlateNumber",maintenanceRecordPage.getContent().get(i).getLicensePlateNumber());
   map1.put("driverName",maintenanceRecordPage.getContent().get(i).getDriverName());
   map1.put("storeRoom",maintenanceRecordPage.getContent().get(i).getStoreRoom().toString());
   map1.put("vehicleType",String.valueOf(maintenanceRecordPage.getContent().get(i).getVehicleType()));
   map1.put("accessoriesId",maintenanceRecordPage.getContent().get(i).getAccessoriesId());
   String yn = maintenanceRecordPage.getContent().get(i).getLackOfAccessories();
   String lackOfAccessories = "";
   if(yn.equals("0")){
    lackOfAccessories = "正常";
   }else {
    lackOfAccessories = "缺少";
   }
   map1.put("vehicleType",maintenanceRecordPage.getContent().get(i).getVehicleType());
   map1.put("accessoriesId",maintenanceRecordPage.getContent().get(i).getAccessoriesId());
   map1.put("useOfAccessories",maintenanceRecordPage.getContent().get(i).getUseOfAccessories());
   map1.put("lackOfAccessories",lackOfAccessories);
   map1.put("maintenancePrice",maintenanceRecordPage.getContent().get(i).getMaintenancePrice());
   map1.put("maintenanceTime",maintenanceRecordPage.getContent().get(i).getMaintenanceTime());//remark
   map1.put("remark",maintenanceRecordPage.getContent().get(i).getRemark());//
   listMap.add(map1);
  }
  JSONObject object = new JSONObject();
  object.put("code", 0);
  object.put("msg", "");
  object.put("count",maintenanceRecordPage.getNumber());
  object.put("data", listMap);
  return  object.toString();
 }

 @RequestMapping("/insert")
 public String equmentManagementInsert() {
  return super.toString();
 }
}
