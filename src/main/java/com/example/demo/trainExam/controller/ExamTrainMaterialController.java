package com.example.demo.trainExam.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.CommonReturnType;
import com.example.demo.common.doPage.PageQuery;
import com.example.demo.trainExam.controller.searchParam.ExamTrainMaterialSearchParam;
import com.example.demo.trainExam.model.ExamTrainMaterialWithBLOBs;
import com.example.demo.trainExam.service.ExamTrainMaterialService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zcc
 * @date 2019/5/10 19:55
 */
@Controller
@Api(tags = "教材")
@RequestMapping("/exam/trainMaterial")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ExamTrainMaterialController {
    @Autowired
    private ExamTrainMaterialService examTrainMaterialService;

    @RequestMapping("/getPage")
    @ResponseBody
    public CommonReturnType getPage(PageQuery pageQuery, ExamTrainMaterialSearchParam examTrainMaterialSearchParam){
        return CommonReturnType.create(examTrainMaterialService.getPage(pageQuery, examTrainMaterialSearchParam));
    }


    @RequestMapping("/getPageByType")
    @ResponseBody
    public CommonReturnType getPageByType(PageQuery pageQuery,int typeId){
        return CommonReturnType.create(examTrainMaterialService.getPageByType(pageQuery,typeId));
    }

    @RequestMapping("/insert")
    @ResponseBody
    public CommonReturnType insert(@RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);
        ExamTrainMaterialWithBLOBs examTrainMaterial = JSON.toJavaObject(jsonObject,ExamTrainMaterialWithBLOBs.class);
        examTrainMaterialService.insert(examTrainMaterial);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/update")
    @ResponseBody
    public CommonReturnType update(@RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);
        ExamTrainMaterialWithBLOBs examTrainMaterial = JSON.toJavaObject(jsonObject,ExamTrainMaterialWithBLOBs.class);
        examTrainMaterialService.update(examTrainMaterial);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturnType delete(int id){
        examTrainMaterialService.delete(id);
        return CommonReturnType.create(null);
    }
}
