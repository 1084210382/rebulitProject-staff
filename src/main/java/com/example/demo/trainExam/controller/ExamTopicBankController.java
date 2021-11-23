package com.example.demo.trainExam.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.BusinessException;
import com.example.demo.common.CommonReturnType;
import com.example.demo.common.EmBusinessError;
import com.example.demo.common.doPage.PageQuery;
import com.example.demo.trainExam.controller.param.ExamTopicBankParam;
import com.example.demo.trainExam.controller.param.ListParam;
import com.example.demo.trainExam.controller.searchParam.ExamTopicSearchParam;
import com.example.demo.trainExam.model.ExamTopicBank;
import com.example.demo.trainExam.service.ExamTopicBankService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zcc
 * @date 2019/5/10 19:53
 */
@Controller
@Api(tags = "考试题库")
@RequestMapping("/exam/topic")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ExamTopicBankController {
    @Autowired
    private ExamTopicBankService examTopicBankService;

    @RequestMapping("/getPage")
    @ResponseBody
    public CommonReturnType getPage(PageQuery pageQuery, ExamTopicSearchParam examTopicSearchParam){
        return CommonReturnType.create(examTopicBankService.getPage(pageQuery,examTopicSearchParam));
    }

    @RequestMapping("/getList")
    @ResponseBody
    public CommonReturnType getList(){
        return CommonReturnType.create(examTopicBankService.getList());
    }

    @RequestMapping("/insert")
    @ResponseBody
    public CommonReturnType insert(@RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);
        ExamTopicBankParam topicBankParam = JSON.toJavaObject(jsonObject, ExamTopicBankParam.class);
        ExamTopicBank examTopicBank =new ExamTopicBank();
        BeanUtils.copyProperties(topicBankParam,examTopicBank);
        examTopicBank.setScore(topicBankParam.getIndustry());
        examTopicBank.setStatus(topicBankParam.getWorkType());
        examTopicBankService.insert(examTopicBank);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturnType delete(@RequestBody ListParam listParam) {
        if (listParam != null && listParam.getIdList().size() > 0) {
            examTopicBankService.delete(listParam.getIdList());
            return CommonReturnType.create(null);
        }
        throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"未获取到id");
    }

    @RequestMapping("/update")
    @ResponseBody
    public CommonReturnType update(@RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);
        ExamTopicBankParam topicBankParam = JSON.toJavaObject(jsonObject,ExamTopicBankParam.class);
        ExamTopicBank examTopicBank =new ExamTopicBank();
        BeanUtils.copyProperties(topicBankParam,examTopicBank);
        examTopicBank.setScore(topicBankParam.getIndustry());
        examTopicBank.setStatus(topicBankParam.getWorkType());
        examTopicBankService.update(examTopicBank);
        return CommonReturnType.create(null);
    }
}
