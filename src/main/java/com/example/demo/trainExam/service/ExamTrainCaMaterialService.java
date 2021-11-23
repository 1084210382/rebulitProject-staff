package com.example.demo.trainExam.service;


import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.trainExam.controller.param.TrainCaMaterialParam;
import com.example.demo.trainExam.controller.param.TrainPersonParam;
import com.example.demo.trainExam.controller.searchParam.TrainPersonSearchParam;

import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 21:56
 */
public interface ExamTrainCaMaterialService {
    void changeCaMaterials(int caId, int courseId,List<TrainCaMaterialParam> caMaterialParamList);
    PageResult<TrainPersonParam> getPage(PageQuery pageQuery, TrainPersonSearchParam trainPersonSearchParam);
}
