package com.xforceplus.data.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xforceplus.data.bean.EquipmentManagement;
import com.xforceplus.data.bean.VehicleManagement;
import com.xforceplus.data.dao.EquipmentManagementRepository;

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
            map1.put("warehouseUnitPrice",equipmentManagement.getContent().get(i).getWarehouseUnitPrice());
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
	
}
