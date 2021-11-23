package com.example.demo.trainExam.dao;

import com.example.demo.common.doPage.PageQuery;
import com.example.demo.trainExam.controller.param.TrainPersonParam;
import com.example.demo.trainExam.controller.searchParam.TrainPersonSearchParam;
import com.example.demo.trainExam.model.ExamTrainCaMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamTrainCaMaterialMapper {

    void deleteByCaId(@Param("caId") int caId,@Param("courseId") int courseId);
    void batchInsert(@Param("caMaterialList") List<ExamTrainCaMaterial> examTrainCaMaterialList);

    int countList(@Param("search") TrainPersonSearchParam trainPersonSearchParam);
    List<TrainPersonParam> getPage(@Param("page") PageQuery page, @Param("search") TrainPersonSearchParam trainPersonSearchParam);
    int selectByCaId(@Param("caId") int caId);
}