package com.example.demo.trainExam.service.impl;

import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.common.BusinessException;
import com.example.demo.common.EmBusinessError;
import com.example.demo.trainExam.controller.param.TrainMaterialParam;
import com.example.demo.trainExam.controller.searchParam.ExamTrainMaterialSearchParam;
import com.example.demo.trainExam.dao.ExamTrainCourseMaterialMapper;
import com.example.demo.trainExam.dao.ExamTrainMaterialMapper;
import com.example.demo.trainExam.model.ExamTrainMaterial;
import com.example.demo.trainExam.model.ExamTrainMaterialWithBLOBs;
import com.example.demo.trainExam.service.ExamTrainMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 21:20
 */
@Service
public class ExamTrainMaterialServiceImpl implements ExamTrainMaterialService {

    @Autowired
    private ExamTrainMaterialMapper examTrainMaterialMapper;
    @Autowired
    private ExamTrainCourseMaterialMapper examTrainCourseMaterialMapper;

    @Override
    public PageResult<TrainMaterialParam> getPage(PageQuery pageQuery, ExamTrainMaterialSearchParam examTrainMaterialSearchParam) {
        int count=examTrainMaterialMapper.countList();
        if (count > 0) {
            List<TrainMaterialParam> examTrainMaterialList = examTrainMaterialMapper.getPage(pageQuery, examTrainMaterialSearchParam);
            PageResult<TrainMaterialParam> pageResult = new PageResult<>();
            pageResult.setData(examTrainMaterialList);
            pageResult.setTotal(count);
            pageResult.setPageNo(pageQuery.getPageNo());
            pageResult.setPageSize(pageQuery.getPageSize());
            return pageResult;
        }
        PageResult<TrainMaterialParam> pageResult = new PageResult<>();
        return pageResult;
    }

    @Override
    public PageResult<TrainMaterialParam> getPageByType(PageQuery pageQuery, int typeId) {
        int count=examTrainMaterialMapper.countListByType(typeId);
        if (count > 0) {
            List<TrainMaterialParam> examTrainMaterialList = examTrainMaterialMapper.getPageByType(pageQuery,typeId);
            PageResult<TrainMaterialParam> pageResult = new PageResult<>();
            pageResult.setData(examTrainMaterialList);
            pageResult.setTotal(count);
            pageResult.setPageNo(pageQuery.getPageNo());
            pageResult.setPageSize(pageQuery.getPageSize());
            return pageResult;
        }
        PageResult<TrainMaterialParam> pageResult = new PageResult<>();
        return pageResult;
    }

    @Override
    @Transactional
    public void insert(ExamTrainMaterialWithBLOBs examTrainMaterial) {
        if(examTrainMaterialMapper.countByName(examTrainMaterial.getName())>0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"????????????????????????");
        }
        examTrainMaterial.setOperateIp("125.125.125");
        examTrainMaterial.setOperator("?????????");
        examTrainMaterial.setOperateTime(new Date());
                                                                                                                                                                                                examTrainMaterialMapper.insertSelective(examTrainMaterial);
    }

    @Override
    @Transactional
    public void update(ExamTrainMaterialWithBLOBs examTrainMaterial) {
        ExamTrainMaterialWithBLOBs before = examTrainMaterialMapper.selectByPrimaryKey(examTrainMaterial.getId());
        if(before == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"????????????????????????");
        }
        examTrainMaterial.setOperateIp("125.125.125");
        examTrainMaterial.setOperator("?????????");
        examTrainMaterial.setOperateTime(new Date());
        examTrainMaterialMapper.updateByPrimaryKeySelective(examTrainMaterial);
    }

    @Override
    public void delete(int id) {
        ExamTrainMaterial examTrainMaterial = examTrainMaterialMapper.selectByPrimaryKey(id);
        if(examTrainMaterial==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"????????????????????????????????????");
        }
        examTrainMaterialMapper.deleteByPrimaryKey(id);
        examTrainCourseMaterialMapper.deleteByMaterialId(id);
    }
}
