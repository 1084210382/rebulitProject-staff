package com.example.demo.trainExam.controller;

import com.example.demo.common.CommonReturnType;
import com.example.demo.common.doPage.PageQuery;
import com.example.demo.trainExam.controller.searchParam.ExamSubjectSearchParam;
import com.example.demo.trainExam.model.ExamSubject;
import com.example.demo.trainExam.service.ExamSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zcc
 * @date 2019/5/10 19:52
 */
@Controller
@RequestMapping("/exam/subject")
@Api(tags = "考试试题")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ExamSubjectController {
    @Autowired
    private ExamSubjectService examSubjectService;

    @RequestMapping("/getPage")
    @ResponseBody
    public CommonReturnType getPage(PageQuery pageQuery, ExamSubjectSearchParam examSubjectSearchParam){
        return CommonReturnType.create(examSubjectService.getPage(pageQuery, examSubjectSearchParam));
    }

    @RequestMapping("/getSubjectTopicIds")
    @ResponseBody
    public CommonReturnType getSubjectTopicIds(int id){
        return CommonReturnType.create(examSubjectService.getSubjectTopicIds(id));
    }

    @RequestMapping("/getIndustryAndWorkType")
    @ResponseBody
    public CommonReturnType getIndustryAndWorkType(){
        //获取所有的行业和工作种类
        Map<String,Object> map = new HashMap();
        return CommonReturnType.create(map);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public CommonReturnType insert(ExamSubjectSearchParam examSubjectSearchParam){
        ExamSubject examSubject = new ExamSubject();
        BeanUtils.copyProperties(examSubjectSearchParam,examSubject);
        examSubjectService.insert(examSubject);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/update")
    @ResponseBody
    public CommonReturnType update(ExamSubjectSearchParam examSubjectSearchParam){
        ExamSubject examSubject = new ExamSubject();
        BeanUtils.copyProperties(examSubjectSearchParam,examSubject);
        examSubjectService.update(examSubject);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturnType delete(int id) {
        examSubjectService.delete(id);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/getNum")
    @ResponseBody
    public CommonReturnType getNum(ExamSubjectSearchParam examSubjectSearchParam){
        return CommonReturnType.create(examSubjectService.getNum(examSubjectSearchParam));
    }
}
