package com.example.demo.trainExam.service.impl;

import com.example.demo.common.BusinessException;
import com.example.demo.common.EmBusinessError;
import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.trainExam.controller.searchParam.ExamTopicSearchParam;
import com.example.demo.trainExam.dao.ExamSubjectTopicMapper;
import com.example.demo.trainExam.dao.ExamTopicBankMapper;
import com.example.demo.trainExam.model.ExamTopicBank;
import com.example.demo.trainExam.service.ExamTopicBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 20:19
 */
@Service
public class ExamTopicBankServiceImpl implements ExamTopicBankService {
    @Autowired
    private ExamTopicBankMapper examTopicBankMapper;
    @Autowired
    private ExamSubjectTopicMapper examSubjectTopicMapper;

    @Override
    public PageResult<ExamTopicBank> getPage(PageQuery pageQuery, ExamTopicSearchParam examTopicSearchParam) {
        int count=examTopicBankMapper.countList();
        if (count > 0) {
            List<ExamTopicBank> examTopicBankList = examTopicBankMapper.getPage(pageQuery,examTopicSearchParam);
            PageResult<ExamTopicBank> pageResult = new PageResult<>();
            pageResult.setData(examTopicBankList);
            pageResult.setTotal(count);
            pageResult.setPageNo(pageQuery.getPageNo());
            pageResult.setPageSize(pageQuery.getPageSize());
            return pageResult;
        }
        PageResult<ExamTopicBank> pageResult = new PageResult<>();
        return pageResult;
    }

    @Override
    public List<ExamTopicBank> getList() {
        return examTopicBankMapper.getList();
    }

    @Override
    @Transactional
    public void insert(ExamTopicBank examTopicBank) {
      if(examTopicBankMapper.countByTitle(examTopicBank.getTitle())>0){
          throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"??????????????????");
      }
      examTopicBank.setOperateIp("124.124.124");
      examTopicBank.setOperateTime(new Date());
      examTopicBank.setOperator("?????????");
      examTopicBankMapper.insertSelective(examTopicBank);
    }

    @Override
    @Transactional
    public void update(ExamTopicBank examTopicBank) {
        ExamTopicBank before = examTopicBankMapper.selectByPrimaryKey(examTopicBank.getId());
        if(before==null){
          throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"????????????????????????");
        }
        examTopicBank.setOperateIp("124.124.124");
        examTopicBank.setOperateTime(new Date());
        examTopicBank.setOperator("?????????");
        examTopicBankMapper.updateByPrimaryKeySelective(examTopicBank);
    }

    @Override
    @Transactional
    public void delete(List<Integer> ids) {
        for (Integer id : ids) {
            ExamTopicBank examTopicBank = examTopicBankMapper.selectByPrimaryKey(id);
            if(examTopicBank==null){
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"????????????????????????????????????");
            }
            examTopicBankMapper.deleteByPrimaryKey(id);
            examSubjectTopicMapper.deleteByTopicId(id);
        }
    }
}
