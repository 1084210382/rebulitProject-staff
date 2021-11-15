package com.example.demo.trainExam.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.BusinessException;
import com.example.demo.common.CommonReturnType;
import com.example.demo.common.EmBusinessError;
import com.example.demo.common.commonRequest.UserSession;
import com.example.demo.common.doPage.PageQuery;
import com.example.demo.trainExam.controller.param.CaTopicParam;
import com.example.demo.trainExam.controller.param.ExamCaTopic;
import com.example.demo.trainExam.controller.param.ExamExamParam;
import com.example.demo.trainExam.model.ExamExam;
import com.example.demo.trainExam.model.ExamSubject;
import com.example.demo.trainExam.service.ExamCaExamService;
import com.example.demo.trainExam.service.ExamExamService;
import com.example.demo.trainExam.service.ExamSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zcc
 * @date 2019/5/10 19:49
 * 考试页面
 */
@Controller
@Api(tags = "考试")
@RequestMapping("/exam/examType")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ExamExamController {
    @Autowired
    private ExamExamService examExamService;
    @Autowired
    private ExamSubjectService examSubjectService;
    @Autowired
    private ExamCaExamService examCaExamService;

    @RequestMapping("/getPage")
    @ResponseBody
    public CommonReturnType getPage(PageQuery pageQuery, ExamExamParam examExamParam){
        return CommonReturnType.create(examExamService.getPage(pageQuery,examExamParam));
    }
    @RequestMapping("/insert")
    @ResponseBody
    public CommonReturnType insert(ExamExam examExam){
        System.out.println(examExam.getEndTime());
        examExamService.insert(examExam);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/update")
    @ResponseBody
    public CommonReturnType update(ExamExam examExam){
        examExamService.update(examExam);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturnType delete(int id){
        examExamService.delete(id);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/getCaExamList")
    @ResponseBody
    public CommonReturnType getCaExamList(){
        if(UserSession.getUserType()!=3){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"非企业用户");
        }
        //User
        return CommonReturnType.create(examSubjectService.getWorkTypeExamList());
    }

    @RequestMapping("/getCaTopicList")
    @ResponseBody
    public CommonReturnType getCaTopicList(int examCaId,int subjectId){
        //User
        if(UserSession.getUserType()!=3){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"非企业用户");
        }
        CaTopicParam caTopicParam = new CaTopicParam();
        caTopicParam.setCaId(UserSession.getUserId());
        caTopicParam.setExamCaId(examCaId);
        caTopicParam.setSubjectId(subjectId);
        return CommonReturnType.create(examSubjectService.getExamTopicList(caTopicParam));
    }
    /*提交考试答题内容*/
    @RequestMapping("/submitCaTopic")
    @ResponseBody
    public CommonReturnType submitCaTopic(@RequestBody String json){
        if(UserSession.getUserType()!=3){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"非企业用户");
        }
        JSONObject jsonObject = JSON.parseObject(json);
        List<ExamCaTopic> examCaTopicList = JSONObject.parseArray(jsonObject.getString("list"),ExamCaTopic.class);
        if(jsonObject.getInteger("examId")!=null) {
            int examId = jsonObject.getInteger("examId");
        }
        ExamSubject examSubject = JSONObject.parseObject(jsonObject.getString("subjectInfo"),ExamSubject.class);
        //User
        return CommonReturnType.create(examSubjectService.changeCaExam(UserSession.getUserId(),examSubject,examCaTopicList));
    }

    @RequestMapping("/getCaQualify")
    @ResponseBody
    public CommonReturnType getCaQualify(){
        //User
        return CommonReturnType.create(examCaExamService.getCaQualify(UserSession.getUserId()));
    }

    @RequestMapping("/getCaExamScore")
    @ResponseBody
    public CommonReturnType getCaExamScore(){
        //User
        return CommonReturnType.create(examCaExamService.getCaExamByCaId(UserSession.getUserId()));
    }
}
