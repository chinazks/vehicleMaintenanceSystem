package com.xforceplus.data.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xforceplus.data.bean.UnitInformation;
import com.xforceplus.data.dao.UnitInformationRepository;
import com.xforceplus.data.dao.UnitRepository;
import com.xforceplus.data.service.BatchImportService;
import com.xforceplus.data.tools.ExcleImportUtils;
import com.xforceplus.data.tools.JSONResult;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private BatchImportService batchImportService;

    @Value("${size}")
    private int size;
    @PostMapping(value = "/batchImport")
    @ResponseBody
    public String batchImportController(@RequestParam(value="filename") MultipartFile file,
                                        HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        //判断文件是否为空
        if(file==null){
            session.setAttribute("msg","文件不能为空！");
            return "redirect:toUserKnowledgeImport";
        }

        //获取文件名
        String fileName=file.getOriginalFilename();

        //验证文件名是否合格
        if(!ExcleImportUtils.validateExcel(fileName)){
            session.setAttribute("msg","文件必须是excel格式！");
            return "ok!";
        }

        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(StringUtils.isEmpty(fileName) || size==0){
            session.setAttribute("msg","文件不能为空！");
            return "ok!";
        }

        //批量导入
        String message = batchImportService.batchImport(fileName,file);
        session.setAttribute("msg",message);
        return "ok!";
    }
    
    //跳转到单位新增页面
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String equmentManagementInsert(Map<String,Object> map){
        map.put("status",false);
        return "/unitInformation/unitInformation_insert";
    }

    //提交新增单位
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String equmentManagementInsert(Map<String,Object> map,@ModelAttribute("form") UnitInformation unitInformation){
        boolean status = false;//用来区别一开始进入的页面有没有数据
        String response = "/unitInformation/insert";
        String msg = "";
        try{
            if(unitInformation != null){
                unitInformationRepository.save(unitInformation);
                status = true;
                response= "/unitInformation/list/0";
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
        return response;
    }
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public String equmentManagementUpdate(Map<String,Object> map,@PathVariable Long id){
        UnitInformation unitInformation=unitInformationRepository.findOne(id);
        map.put("unitInformation",unitInformation);
        return "/unitInformation/unitInformation_update";
    }
    
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String equmentManagementUpdate(Map<String,Object> map,@ModelAttribute("form")UnitInformation unitInformation){
        String response = "/unitInformation/update/"+unitInformation.getId();
        boolean status = false;//用来区别一开始进入的页面有没有数据
        String msg = "";
        //前端需要在隐藏域中加id提交到后端
        int page= (int) (unitInformation.getId()/size);
        try {
            unitInformationRepository.save(unitInformation);
            response = "/unitInformation/list/"+page;
            status = true;
            msg = "单位信息修改成功！";
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
            msg = "数据长度有误,单位信息修改失败!";
        } finally {
            map.put("unitInformation",unitInformation);//数据还原
            map.put("status",status);
            map.put("msg",msg);
        }
        return response;
    }
    @RequestMapping(value = "/delete/{page}/{id}")
    public String equmentManagementDelete(Map<String,Object> map,@PathVariable Long id,@PathVariable int page){
        String response = "/unitInformation/list/"+page;
        //前端需要在隐藏域中加id提交到后端
        boolean status = false;
        String msg = "";
        try {
            unitInformationRepository.delete(id);
            msg = "单位信息数据删除成功！";
        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
            msg = "单位信息数据删除失败！";
        }finally {
            map.put("status",status);
            map.put("msg",msg);
        }
        return response;
    }

    @RequestMapping("/list/{page}")
    @ResponseBody
    public String equmentManagementList(Map<String,Object> map, @PathVariable int page){
    	System.out.println("进去查询unitinformation方法");
        Page<UnitInformation> unitInformationPage=unitInformationRepository.findAll(new PageRequest(page,size));
        List<Map<String,String>> listMap=new ArrayList<>();//传到前端
       for(int i=0;i<unitInformationPage.getContent().size();i++){
            Map<String,String> map1=new HashMap<>();
            map1.put("id",String.valueOf(unitInformationPage.getContent().get(i).getId()));
            map1.put("unitId",String.valueOf(unitInformationPage.getContent().get(i).getUnitId()));
            map1.put("unitName",unitRepository.findByUnitId(unitInformationPage.getContent().get(i).getUnitId()).getUnitName());
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
        JSONArray array = new JSONArray();
        object.put("code", 0);
        object.put("msg", "");
        object.put("count",listMap.size());
        object.put("data", listMap);
        System.out.println("查询结果为"+object.toString());
        return  object.toString();
    }
}
