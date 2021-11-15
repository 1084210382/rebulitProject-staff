package com.example.demo.trainExam.service;



import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.trainExam.controller.param.TrainMaterialParam;
import com.example.demo.trainExam.controller.searchParam.ExamTrainMaterialSearchParam;
import com.example.demo.trainExam.model.ExamTrainMaterialWithBLOBs;

/**
 * @author zcc
 * @date 2019/4/29 21:19
 */
public interface ExamTrainMaterialService {
    PageResult<TrainMaterialParam> getPage(PageQuery pageQuery, ExamTrainMaterialSearchParam examTrainMaterialSearchParam);
    PageResult<TrainMaterialParam> getPageByType(PageQuery pageQuery,int typeId);
    void insert(ExamTrainMaterialWithBLOBs examTrainMaterial);
    void update(ExamTrainMaterialWithBLOBs examTrainMaterial);
    void delete(int id);
}
