package com.example.demo.trainExam.dao;

import com.example.demo.common.doPage.PageQuery;
import com.example.demo.trainExam.controller.param.CaTopicParam;
import com.example.demo.trainExam.controller.param.ExamCaTopic;
import com.example.demo.trainExam.controller.param.TopicNumParam;
import com.example.demo.trainExam.controller.searchParam.ExamSubjectSearchParam;
import com.example.demo.trainExam.controller.searchParam.ExamTopicSearchParam;
import com.example.demo.trainExam.model.ExamSubject;
import com.example.demo.trainExam.model.ExamTopicBank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamTopicBankMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(ExamTopicBank record);
    int insertSelective(ExamTopicBank record);
    ExamTopicBank selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(ExamTopicBank record);
    int updateByPrimaryKey(ExamTopicBank record);

    int countList();
    List<ExamTopicBank> getList();
    List<ExamTopicBank> getPage(@Param("page") PageQuery page, @Param("search") ExamTopicSearchParam examTopicSearchParam);
    int countByTitle(@Param("title") String title);
    List<ExamTopicBank> getByIdList(@Param("idList") List<Integer> idList);
    List<ExamCaTopic> getCaExamList(@Param("paramList") CaTopicParam caTopicParam, @Param("idList") List<Integer> idList);
    TopicNumParam getNum(@Param("search") ExamSubjectSearchParam examSubjectSearchParam);
    List<ExamTopicBank> selectRandomByNum(@Param("search") ExamSubject examSubject);
}