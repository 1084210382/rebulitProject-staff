package com.example.demo.trainExam.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.BusinessException;
import com.example.demo.common.CommonReturnType;
import com.example.demo.common.EmBusinessError;
import com.example.demo.common.commonRequest.UserSession;
import com.example.demo.common.doPage.PageQuery;
import com.example.demo.trainExam.controller.param.TrainCaMaterialParam;
import com.example.demo.trainExam.controller.searchParam.ExamTrainCourseSearchParam;
import com.example.demo.trainExam.model.ExamTrainCourse;
import com.example.demo.trainExam.service.ExamTrainCaMaterialService;
import com.example.demo.trainExam.service.ExamTrainCourseMaterialService;
import com.example.demo.trainExam.service.ExamTrainCourseService;
import com.example.demo.util.StringUtil;
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
 * @date 2019/5/10 19:54
 */
@Controller
@Api(tags = "培训")
@RequestMapping("/exam/trainCourse")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ExamTrainCourseController {
    @Autowired
    private ExamTrainCourseService examTrainCourseService;
    @Autowired
    private ExamTrainCourseMaterialService examTrainCourseMaterialService;
    @Autowired
    private ExamTrainCaMaterialService examTrainCaMaterialService;

    @RequestMapping("/getPage")
    @ResponseBody
    public CommonReturnType getPage(PageQuery pageQuery, ExamTrainCourseSearchParam examTrainCourseSearchParam){
        return CommonReturnType.create(examTrainCourseService.getPage(pageQuery, examTrainCourseSearchParam));
    }

    @RequestMapping("/getCourseMaterialIds")
    @ResponseBody
    public CommonReturnType getCourseMaterialIds(int id){
        return CommonReturnType.create(examTrainCourseMaterialService.getListByCourseId(id));
    }

    @RequestMapping("/insert")
    @ResponseBody
    public CommonReturnType insert(@RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);
        List<Integer> topicIdList = StringUtil.splitToListInt(jsonObject.getString("materialIds"));
        ExamTrainCourse examTrainCourse = JSON.toJavaObject(jsonObject,ExamTrainCourse.class);
        examTrainCourseService.insert(examTrainCourse,topicIdList);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public CommonReturnType delete(int id) {
        examTrainCourseService.delete(id);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/update")
    @ResponseBody
    public CommonReturnType update(@RequestBody String json){
        JSONObject jsonObject = JSON.parseObject(json);
        List<Integer> materialIdList = StringUtil.splitToListInt(jsonObject.getString("materialIds"));
        ExamTrainCourse examTrainCourse = JSON.toJavaObject(jsonObject,ExamTrainCourse.class);
        examTrainCourseService.update(examTrainCourse,materialIdList);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/getCaTrainList")
    @ResponseBody
    public CommonReturnType getCaTrainList(){
        //User
        if(UserSession.getUserType()!=3){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"非企业用户");
        }
        return CommonReturnType.create(examTrainCourseService.getCaTrainList());
    }


    @RequestMapping("/getCaTrain")
    @ResponseBody
    public CommonReturnType getCaTrain(int courseId){
        //User
        if(UserSession.getUserType()!=3){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"非企业用户");
        }
        return CommonReturnType.create(examTrainCourseService.getCourseMaterialIds(courseId,UserSession.getUserId()));
    }

    @RequestMapping("/submitCaTrain")
    @ResponseBody
    public CommonReturnType submitCaTrain(@RequestBody String json){
        //User
        if(UserSession.getUserType()!=3){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"非企业用户");
        }
        JSONObject jsonObject = JSON.parseObject(json);
        List<TrainCaMaterialParam> trainCaMaterialParamList = JSONObject.parseArray(jsonObject.getString("list"),TrainCaMaterialParam.class);
        examTrainCaMaterialService.changeCaMaterials(UserSession.getUserId(),jsonObject.getInteger("courseId"),trainCaMaterialParamList);
        return CommonReturnType.create(null);
    }
}
