package com.xforceplus.data.controller;

import com.alibaba.fastjson.JSON;
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
 public String equmentManagementList(Map<String,Object> map, @RequestParam("page") int pages,@RequestParam("page") int limit){
  Page<MaintenanceRecord> maintenanceRecordPage=maintenanceRecordRepository.findAll(new PageRequest(pages-1,limit));
  return  JSON.toJSONString(maintenanceRecordPage);
 }
}
