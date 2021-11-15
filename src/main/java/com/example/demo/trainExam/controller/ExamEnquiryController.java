package com.example.demo.trainExam.controller;

import com.example.demo.common.CommonReturnType;
import com.example.demo.common.doPage.PageQuery;
import com.example.demo.trainExam.controller.param.CaTopicParam;
import com.example.demo.trainExam.controller.searchParam.ExamEnquirySearchParam;
import com.example.demo.trainExam.controller.searchParam.TrainPersonSearchParam;
import com.example.demo.trainExam.service.ExamCaExamService;
import com.example.demo.trainExam.service.ExamSubjectService;
import com.example.demo.trainExam.service.ExamTrainCaMaterialService;
import com.example.demo.trainExam.service.ExamTrainCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zcc
 * @date 2019/6/11 17:00
 */
@Controller
@Api(tags = "培训考试查询")
@RequestMapping("/exam/enquiry")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ExamEnquiryController {
    @Autowired
    private ExamCaExamService examCaExamService;
    @Autowired
    private ExamTrainCaMaterialService examTrainCaMaterialService;
    @Autowired
    private ExamTrainCourseService examTrainCourseService;
    @Autowired
    private ExamSubjectService examSubjectService;

//    @RequestMapping("/getCaInfo")
//    @ResponseBody
//    public void getCaInfo(){
//
//    }

    /*在线培训人员*/
    @PostMapping("/getTrainPage")
    @ResponseBody
    public CommonReturnType getTrainPage(PageQuery pageQuery,@RequestBody TrainPersonSearchParam trainPersonSearchParam){
        return CommonReturnType.create(examTrainCaMaterialService.getPage(pageQuery,trainPersonSearchParam));
    }

    @GetMapping("/getCaTrainCourseResult")
    @ResponseBody
    public CommonReturnType getCaTrainCourseResult(int caId){
        return CommonReturnType.create(examTrainCourseService.getCourseIds(caId));
    }

    @GetMapping("/getCaTrainMaterialResult")
    @ResponseBody
    public CommonReturnType getCaTrainMaterialResult(int courseId,int caId){
        return CommonReturnType.create(examTrainCourseService.getCourseMaterialIds(courseId,caId));
    }

    /*在线考试人员*/
    @PostMapping("/getPage")
    @ResponseBody
    public CommonReturnType getPage(PageQuery pageQuery,@RequestBody ExamEnquirySearchParam examEnquirySearchParam){
        return CommonReturnType.create(examCaExamService.getPage(pageQuery,examEnquirySearchParam));
    }

    @GetMapping("/getCaExamResult")
    @ResponseBody
    public CommonReturnType getCaExamResult(int caId){
        return CommonReturnType.create(examCaExamService.getCaExamByCaId(caId));
    }

    @PostMapping("/getCaTopicResult")
    @ResponseBody
    public CommonReturnType getCaTopicResult(@RequestBody CaTopicParam caTopicParam){
        return CommonReturnType.create(examSubjectService.getExamTopicList(caTopicParam));
    }
}
