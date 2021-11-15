package com.example.demo.trainExam.service;


import com.example.demo.trainExam.controller.param.TrainCaMaterialParam;
import com.example.demo.trainExam.controller.param.TrainMaterialParam;

import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 22:00
 */
public interface ExamTrainCourseMaterialService {
    List<TrainMaterialParam> getListByCourseId(int courseId);
    List<TrainCaMaterialParam> getCaListByCourseId(int caId, int courseId);
    void changeCourseMaterials(int courseId,List<Integer> materialIdList);
}
