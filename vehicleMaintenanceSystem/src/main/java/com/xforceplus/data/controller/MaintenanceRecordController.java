package com.xforceplus.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.xforceplus.data.bean.MaintenanceRecord;
import com.xforceplus.data.bean.Unit;
import com.xforceplus.data.dao.MaintenanceRecordRepository;
import com.xforceplus.data.dao.UnitRepository;
import com.xforceplus.data.tools.JSONResult;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
   map1.put("storeRoom",maintenanceRecordPage.getContent().get(i).getStoreRoom());
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

 @RequestMapping(value = "/insert",method = RequestMethod.POST)
 @ResponseBody
 public JSONResult equmentManagementInsert(@RequestParam("unitId") String unitId,
                                       @RequestParam("licensePlateNumber") String licensePlateNumber,
                                       @RequestParam("driverName") String driverName,
                                       @RequestParam("storeRoom") String storeRoom,
                                       @RequestParam("vehicleType") String vehicleType,
                                       @RequestParam("accessoriesId") String accessoriesId,
                                       @RequestParam("useOfAccessories") String useOfAccessories,
                                       @RequestParam("lackOfAccessories") String lackOfAccessories,
                                       @RequestParam("maintenancePrice") String maintenancePrice,
                                       @RequestParam("maintenanceTime") String maintenanceTime,
                                       @RequestParam("remark") String remark) {
    MaintenanceRecord maintenanceRecord = new MaintenanceRecord(unitId,licensePlateNumber,driverName,storeRoom,vehicleType,accessoriesId,useOfAccessories,lackOfAccessories,maintenancePrice,maintenanceTime,remark);
    maintenanceRecordRepository.save(maintenanceRecord);

   return JSONResult.build(200, "新增成功", "");
 }

 @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
 public String equmentManagementUpdate(Map<String,Object> map,@PathVariable("id") Long id) {
  MaintenanceRecord maintenanceRecord = maintenanceRecordRepository.findOne(id);

  List<Unit> unitList = new ArrayList<>();
  unitList = unitRepository.findAll();
  map.put("maintenanceRecord",maintenanceRecord);
  map.put("unitList",unitList);
  return "/maintenanceRecord_update";
 }

 @RequestMapping(value = "/update",method = RequestMethod.POST)
 @ResponseBody
 public JSONResult equmentManagementUpdate(@RequestParam("id") Long id,
                                       @RequestParam("unitId") String unitId,
                                       @RequestParam("licensePlateNumber") String licensePlateNumber,
                                       @RequestParam("driverName") String driverName,
                                       @RequestParam("storeRoom") String storeRoom,
                                       @RequestParam("vehicleType") String vehicleType,
                                       @RequestParam("accessoriesId") String accessoriesId,
                                       @RequestParam("useOfAccessories") String useOfAccessories,
                                       @RequestParam("lackOfAccessories") String lackOfAccessories,
                                       @RequestParam("maintenancePrice") String maintenancePrice,
                                       @RequestParam("maintenanceTime") String maintenanceTime,
                                       @RequestParam("remark") String remark) {
  MaintenanceRecord maintenanceRecord = new MaintenanceRecord(unitId,licensePlateNumber,driverName,storeRoom,vehicleType,accessoriesId,useOfAccessories,lackOfAccessories,maintenancePrice,maintenanceTime,remark);
  maintenanceRecord.setId(id);
  maintenanceRecordRepository.save(maintenanceRecord);
  return JSONResult.build(200, "新增成功", "");
 }

 @RequestMapping(value = "/delete",method = RequestMethod.POST)
 @ResponseBody
 public JSONResult equmentManagementDelete(@RequestParam("id") Long id) {
  maintenanceRecordRepository.delete(id);
  return JSONResult.build(200, "删除成功", "");
 }

 //导出excel
 @RequestMapping("/putout")
 @ResponseBody
 public JSONResult putout(HttpServletRequest request){
 // String path = Class.class.getClass().getResource("/").getPath()+"/static/download/";
  String path = "E://upload";
  ArrayList<MaintenanceRecord> list=new ArrayList<MaintenanceRecord>();
  list = (ArrayList<MaintenanceRecord>) maintenanceRecordRepository.findAll();
  try {
   File files=new File(path);
   if (!files.exists()) {
    files.mkdirs();
   }
   File file=new File(path+"/maintenanceRecord.xls");
   if(file.exists()){
    file.delete();
   }
   WritableWorkbook book= Workbook.createWorkbook(new File(path+"/maintenanceRecord.xls"));
   //设置表名
   WritableSheet sheet=book.createSheet("维修记录",0);
   //设置表第一行
   sheet.addCell(new Label(0,0,"单位id"));
   sheet.addCell(new Label(1,0,"车牌号"));
   sheet.addCell(new Label(2,0,"司机名称"));
   sheet.addCell(new Label(3,0,"库房号"));
   sheet.addCell(new Label(4,0,"车辆类型"));
   sheet.addCell(new Label(5,0,"配件id"));
   sheet.addCell(new Label(6,0,"配件使用情况"));
   sheet.addCell(new Label(7,0,"配件缺少情况"));
   sheet.addCell(new Label(8,0,"维修价格"));
   sheet.addCell(new Label(9,0,"维修时间"));
   sheet.addCell(new Label(10,0,"备注"));
   //添加数据

   for(int i=0;i<list.size();i++){
    sheet.addCell(new Label(0,i+1,list.get(i).getUnitId()));
    sheet.addCell(new Label(1,i+1,list.get(i).getLicensePlateNumber()));
    sheet.addCell(new Label(2,i+1,list.get(i).getDriverName()));
    sheet.addCell(new Label(3,i+1,list.get(i).getStoreRoom()));
    sheet.addCell(new Label(4,i+1,list.get(i).getVehicleType()));
    sheet.addCell(new Label(5,i+1,list.get(i).getAccessoriesId()));
    sheet.addCell(new Label(6,i+1,list.get(i).getUseOfAccessories()));
    sheet.addCell(new Label(7,i+1,list.get(i).getLackOfAccessories()));
    sheet.addCell(new Label(8,i+1,list.get(i).getMaintenancePrice()));
    sheet.addCell(new Label(9,i+1,list.get(i).getMaintenanceTime()));
    sheet.addCell(new Label(10,i+1,list.get(i).getRemark()));
   }
   book.write();//将所做的操作写入
   book.close();//关闭文件
  } catch (Exception e) {
   e.printStackTrace();
  }
  return JSONResult.build(200, "生成excel", "download/maintenanceRecord.xls");
 }

 //导入excel
 @RequestMapping(value = "/lead", method = RequestMethod.POST)
 @ResponseBody
 public String lead(MultipartFile file) {
  String path = "E://upload";
  String fielName = file.getOriginalFilename();
  File files=new File(path);
  if (!files.exists()) {
   files.mkdirs();
  }
  File tempfile = new File(path,fielName);
  if(tempfile.exists()) {
   tempfile.delete();
  }
  try {
   file.transferTo(tempfile);
   Workbook workbook = Workbook.getWorkbook(tempfile);
   Sheet sheet = workbook.getSheet(0);
   for (int i = 1; i < sheet.getRows(); i++) {
    MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
    maintenanceRecord.setUnitId(sheet.getCell(0,i).getContents());
    maintenanceRecord.setLicensePlateNumber((sheet.getCell(1,i).getContents()));
    maintenanceRecord.setDriverName(sheet.getCell(2,i).getContents());
    maintenanceRecord.setStoreRoom(sheet.getCell(3,i).getContents());
    maintenanceRecord.setVehicleType(sheet.getCell(4, i).getContents());
    maintenanceRecord.setAccessoriesId(sheet.getCell(5,i).getContents());
    maintenanceRecord.setUseOfAccessories(sheet.getCell(6,i).getContents());
    maintenanceRecord.setLackOfAccessories((sheet.getCell(7,i).getContents()));
    maintenanceRecord.setMaintenancePrice(sheet.getCell(8,i).getContents());
    maintenanceRecord.setMaintenanceTime(sheet.getCell(9,i).getContents());
    maintenanceRecord.setRemark(sheet.getCell(10,i).getContents());
    maintenanceRecordRepository.save(maintenanceRecord);
   }
  } catch (BiffException | IOException e) {
   e.printStackTrace();
  }
  JSONObject json = new JSONObject();
  JSONObject data = new JSONObject();
  data.put("src", "baidu.com");
  json.put("code", 0);
  json.put("msg", "上传成功");
  json.put("data", data);
  return json.toString();
 }
}
