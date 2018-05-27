package com.xforceplus.data.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import com.xforceplus.data.bean.EquipmentManagement;
import com.xforceplus.data.dao.EquipmentManagementRepository;
import com.xforceplus.data.tools.JSONResult;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


@Controller
@RequestMapping("/equipmentManagement")
public class EquipmentManagementController {
	
	@Autowired
	private EquipmentManagementRepository equipmentManagementRepository;
	
	@RequestMapping("/list")
	@ResponseBody
	public String equipmentManagementlist(Map<String,Object> map, HttpServletRequest request) {
		int pages = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
        Page<EquipmentManagement> equipmentManagement=equipmentManagementRepository.findAll(new PageRequest(pages-1,limit));
        List<Map<String,String>> listMap=new ArrayList<>();//传到前端
       for(int i=0;i<equipmentManagement.getContent().size();i++){
            Map<String,String> map1=new HashMap<>();
            map1.put("id",String.valueOf(equipmentManagement.getContent().get(i).getId()));
            map1.put("storeRoom",String.valueOf(equipmentManagement.getContent().get(i).getStoreRoom()));
            map1.put("licensePlateNumber",String.valueOf(equipmentManagement.getContent().get(i).getLicensePlateNumber()));
            map1.put("vehicleType",equipmentManagement.getContent().get(i).getVehicleType());
            map1.put("accessoriesId",equipmentManagement.getContent().get(i).getAccessoriesId());
            map1.put("accessoriesName",equipmentManagement.getContent().get(i).getAccessoriesName());
            map1.put("specifications",equipmentManagement.getContent().get(i).getSpecifications());
            map1.put("originalFactoryNumber",equipmentManagement.getContent().get(i).getOriginalFactoryNumber());
            map1.put("unit",equipmentManagement.getContent().get(i).getUnit());
            map1.put("warehouseUnitPrice",String.valueOf(equipmentManagement.getContent().get(i).getWarehouseUnitPrice()));
            map1.put("stock",equipmentManagement.getContent().get(i).getStock()+"");
            map1.put("goodsNum",equipmentManagement.getContent().get(i).getGoodsNum());
            map1.put("deliveryDate",equipmentManagement.getContent().get(i).getDeliveryDate());
            listMap.add(map1);
        }
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", "");
        object.put("count",equipmentManagementRepository.findAll().size());
        object.put("data", listMap);
        return  object.toString();
	}
	
	//新增器材
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult equmentManagementInsert(Map<String,Object> map,
    		@RequestParam(value = "storeRoom", required = false) String storeRoom,
    		@RequestParam(value = "materialIssuingUnit", required = false) String materialIssuingUnit,
    		@RequestParam(value = "licensePlateNumber", required = false) String licensePlateNumber,
    		@RequestParam(value = "vehicleType", required = false) String vehicleType,
    		@RequestParam(value = "accessoriesId", required = false) String accessoriesId,
    		@RequestParam(value = "accessoriesName", required = false) String accessoriesName,
    		@RequestParam(value = "specifications", required = false) String specifications,
    		@RequestParam(value = "originalFactoryNumber", required = false) String originalFactoryNumber,
    		@RequestParam(value = "unit", required = false) String unit,
    		@RequestParam(value = "warehouseUnitPrice", required = false) String warehouseUnitPrice,
    		@RequestParam(value = "stock", required = false) int stock,
    		@RequestParam(value = "goodsNum", required = false) String goodsNum,
    		@RequestParam(value = "deliveryDate", required = false) String deliveryDate){
        if(equipmentManagementRepository.findAllByAccessoriesIdAndStoreRoom(accessoriesId, storeRoom)==null) {
    		EquipmentManagement equipmentManagement = new EquipmentManagement(storeRoom, materialIssuingUnit, licensePlateNumber, vehicleType, accessoriesId, accessoriesName, specifications, originalFactoryNumber, warehouseUnitPrice, warehouseUnitPrice, stock, goodsNum, deliveryDate);
    		equipmentManagementRepository.save(equipmentManagement);
    	}else {
    		EquipmentManagement equipmentManagement = new EquipmentManagement(storeRoom, materialIssuingUnit, licensePlateNumber, vehicleType, accessoriesId, accessoriesName, specifications, originalFactoryNumber, warehouseUnitPrice, warehouseUnitPrice, stock+1, goodsNum, deliveryDate);
    		equipmentManagement.setId(equipmentManagementRepository.findAllByAccessoriesIdAndStoreRoom(accessoriesId, storeRoom).getId());
    		equipmentManagementRepository.save(equipmentManagement);
    	}
        return JSONResult.build(200, "新增成功", "");
    }
    
    //导入
    @RequestMapping(value = "/lead", method = RequestMethod.POST)
    @ResponseBody
    public String lead(MultipartFile file) {
    	String path = Class.class.getClass().getResource("/").getPath()+"static/upload/";
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
				 EquipmentManagement equipmentManagement = new EquipmentManagement();
				 equipmentManagement.setStoreRoom(sheet.getCell(0,i).getContents());
				 equipmentManagement.setMaterialIssuingUnit((sheet.getCell(1,i).getContents()));
				 equipmentManagement.setLicensePlateNumber(sheet.getCell(2,i).getContents());
				 equipmentManagement.setVehicleType(sheet.getCell(3,i).getContents());
				 equipmentManagement.setAccessoriesId(sheet.getCell(4, i).getContents());
				 equipmentManagement.setAccessoriesName(sheet.getCell(5,i).getContents());
				 equipmentManagement.setSpecifications(sheet.getCell(6,i).getContents());
				 equipmentManagement.setOriginalFactoryNumber(sheet.getCell(7,i).getContents());
				 equipmentManagement.setUnit(sheet.getCell(8,i).getContents());
				 equipmentManagement.setWarehouseUnitPrice(sheet.getCell(9,i).getContents());
				 equipmentManagement.setStock(Integer.parseInt(sheet.getCell(10,i).getContents()));
				 equipmentManagement.setGoodsNum(sheet.getCell(11,i).getContents());
				 equipmentManagement.setDeliveryDate(sheet.getCell(12,i).getContents());
				 equipmentManagementRepository.save(equipmentManagement);
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

