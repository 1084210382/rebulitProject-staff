package com.example.demo.trainExam.controller.viewobject;

import com.example.demo.trainExam.model.ExamTrainCourse;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ExamTrainCourseView extends ExamTrainCourse {
    @ApiModelProperty(value = "课程的教材列表,包含教材的id", dataType = "List<Integer>", example = "[1,2,3]")
    private List<Integer>  materialIdList;

    public List<Integer> getMaterialIdList() {
        return materialIdList;
    }

    public void setMaterialIdList(List<Integer> materialIdList) {
        this.materialIdList = materialIdList;
    }
}