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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xforceplus.data.bean.UnitInformation;
import com.xforceplus.data.dao.UnitInformationRepository;
import com.xforceplus.data.dao.UnitRepository;
import com.xforceplus.data.tools.JSONResult;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by Administrator on 2018/5/6 0006.
 */
@Controller
@RequestMapping("/unitInformation")
public class UnitInformationController {
    private Logger logger= LoggerFactory.getLogger(UnitInformationController.class);
    @Autowired
    private UnitInformationRepository unitInformationRepository;
    

    @Autowired
    private UnitRepository unitRepository;

    
    //获取所有unitinformation数据
    @RequestMapping("/list/unitinformations")
    @ResponseBody
    public String equmentManagementList(Map<String,Object> map, HttpServletRequest request){
    	int pages = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
        Page<UnitInformation> unitInformationPage=unitInformationRepository.findAll(new PageRequest(pages-1,limit));
        List<Map<String,String>> listMap=new ArrayList<>();//传到前端
       for(int i=0;i<unitInformationPage.getContent().size();i++){
            Map<String,String> map1=new HashMap<>();
            map1.put("id",String.valueOf(unitInformationPage.getContent().get(i).getId()));
            map1.put("unitId",String.valueOf(unitInformationPage.getContent().get(i).getUnitId()));
            map1.put("unitName",unitRepository.findByUnitId(unitInformationPage.getContent().get(i).getUnitId()).getUnitName()+String.valueOf(unitInformationPage.getContent().get(i).getUnitId()));
            map1.put("equipmentModel",unitInformationPage.getContent().get(i).getEquipmentModel());
            map1.put("equipmentName",unitInformationPage.getContent().get(i).getEquipmentName());
            map1.put("dispensingTime",unitInformationPage.getContent().get(i).getDispensingTime());
            map1.put("stockQuantity",String.valueOf(unitInformationPage.getContent().get(i).getStockQuantity()));
            map1.put("technicalStatus",unitInformationPage.getContent().get(i).getTechnicalStatus());
            listMap.add(map1);
        }
        map.put("page1",listMap);
        map.put("jsonAll", JSON.toJSONString(listMap));
        map.put("unitInformationPage",unitInformationPage);
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("msg", "");
        object.put("count",unitInformationRepository.findAll().size());
        object.put("data", listMap);
        return  object.toString();
    }
    
  //导出excel
  	@RequestMapping("putout")
  	public JSONResult putout(HttpServletRequest request){
  		String path = request.getSession().getServletContext().getRealPath("putout"); 
  		System.out.println(path+"是路径");
		ArrayList<UnitInformation> list=new ArrayList<UnitInformation>();
		list = (ArrayList<UnitInformation>) unitInformationRepository.findAll();
  		try {
  			File files=new File(path+"/unitinformation.xls");
  			if (!files.exists()) {
  				files.mkdirs();
  			}
  			WritableWorkbook book=Workbook.createWorkbook(new File(path+"/unitinformation.xls"));
  			//设置表名
  			WritableSheet sheet=book.createSheet("单位信息表",0);
  			//设置表第一行
  			sheet.addCell(new Label(0,0,"单位id"));
  			sheet.addCell(new Label(1,0,"装备型号"));
  			sheet.addCell(new Label(2,0,"装备名称"));
  			sheet.addCell(new Label(3,0,"配发时间"));
  			sheet.addCell(new Label(4,0,"数量"));
  			sheet.addCell(new Label(5,0,"技术状况"));
  			
  			//添加数据
  			for(int i=0;i<list.size();i++){
  				sheet.addCell(new Label(0,i+1,list.get(i).getUnitId()));
  				sheet.addCell(new Label(1,i+1,list.get(i).getEquipmentModel()));
  				sheet.addCell(new Label(2,i+1,list.get(i).getEquipmentName()));
  				sheet.addCell(new Label(3,i+1,list.get(i).getDispensingTime()));
  				sheet.addCell(new jxl.write.Number(4,i+1,list.get(i).getStockQuantity()));
  				sheet.addCell(new Label(5,i+1,list.get(i).getTechnicalStatus()));
  			}
  			book.write();//将所做的操作写入
  			book.close();//关闭文件
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return JSONResult.build(200, "生成excel", "");
  	}

    //提交新增单位
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String equmentManagementInsert(Map<String,Object> map,@ModelAttribute("form") UnitInformation unitInformation){
        boolean status = false;//用来区别一开始进入的页面有没有数据
        String msg = "";
        try{
            if(unitInformation != null){
                unitInformationRepository.save(unitInformation);
                status = true;
                msg = "单位信息新增成功！";
            }
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
            msg = "数据长度有误，请核实修改！";
        }finally {
            map.put("unitInformation",unitInformation);//数据还原
            map.put("status",status);
            map.put("msg",msg);
        }
        return "main";
    }
    
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String equmentManagementUpdate(Map<String,Object> map,@PathVariable Long id){
        UnitInformation unitInformation=unitInformationRepository.findOne(id);
        map.put("unitInformation",unitInformation);
        return "/unitInformation/unitInformation_update";
    }
    
    //删除单位信息
    @RequestMapping(value = "deleteunitinformation")
    @ResponseBody
    public JSONResult equmentManagementDelete(Map<String,Object> map,HttpServletRequest request){
    	System.out.println("进入删除行的方法");
        long id = Integer.parseInt(request.getParameter("id"));//--
        JSONResult response=null;
        String msg = "";
        try {
            unitInformationRepository.delete(id);
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
