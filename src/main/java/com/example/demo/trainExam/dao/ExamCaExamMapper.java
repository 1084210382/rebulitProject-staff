package com.example.demo.trainExam.dao;

import com.example.demo.common.doPage.PageQuery;
import com.example.demo.trainExam.controller.param.ExamCaQualifyParam;
import com.example.demo.trainExam.controller.param.ExamEnquiryParam;
import com.example.demo.trainExam.controller.searchParam.ExamEnquirySearchParam;
import com.example.demo.trainExam.model.ExamCaExam;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ExamCaExamMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(ExamCaExam record);
    int insertSelective(ExamCaExam record);
    ExamCaExam selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(ExamCaExam record);
    int updateByPrimaryKey(ExamCaExam record);

    void deleteByCaIdAndExamId(@Param("caId") int caId, @Param("examId") int examId);
    List<ExamCaExam> getByCaId(@Param("caId")int caId);


    List<ExamEnquiryParam> getPage(@Param("page") PageQuery page, @Param("search") ExamEnquirySearchParam examEnquirySearchParam);

    int countList(@Param("search") ExamEnquirySearchParam examEnquirySearchParam);

    int countListTrain(@Param("flag") int flag);

    int countListExam();

    String selectNewestCodeByDate(@Param("date") Date date);

    ExamCaQualifyParam getCaQualify(@Param("caId")int caId);
}