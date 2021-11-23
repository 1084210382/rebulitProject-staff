package com.example.demo.trainExam.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.demo.common.doPage.PageResult;
import com.google.common.collect.Lists;
import com.example.demo.common.doPage.PageQuery;
import com.example.demo.trainExam.controller.param.TrainCaMaterialParam;
import com.example.demo.trainExam.controller.param.TrainPersonParam;
import com.example.demo.trainExam.controller.searchParam.TrainPersonSearchParam;
import com.example.demo.trainExam.dao.ExamTrainCaMaterialMapper;
import com.example.demo.trainExam.model.ExamTrainCaMaterial;
import com.example.demo.trainExam.service.ExamTrainCaMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author zcc
 * @date 2019/4/29 21:57
 */
@Service
public class ExamTrainCaMaterialServiceImpl implements ExamTrainCaMaterialService {
    @Autowired
    private ExamTrainCaMaterialMapper examTrainCaMaterialMapper;

    @Override
    @Transactional
    public void changeCaMaterials(int caId, int courseId,List<TrainCaMaterialParam> caMaterialParamList) {
        examTrainCaMaterialMapper.deleteByCaId(caId,courseId);

        if (CollectionUtils.isEmpty(caMaterialParamList)) {
            return;
        }
        List<ExamTrainCaMaterial> examTrainCaMaterialList = Lists.newArrayList();
        for (TrainCaMaterialParam trainCaMaterialParam : caMaterialParamList) {
            ExamTrainCaMaterial examTrainCaMaterial = new ExamTrainCaMaterial();
            examTrainCaMaterial.setCaId(caId);
            examTrainCaMaterial.setCourseId(courseId);
            examTrainCaMaterial.setTrainMaterialId(trainCaMaterialParam.getId());
            examTrainCaMaterial.setCompletionRate(trainCaMaterialParam.getCompletionRate());
            examTrainCaMaterial.setStartTime(new Date());
            examTrainCaMaterial.setEndTime(new Date());
            examTrainCaMaterial.setOperator("操作人");
            examTrainCaMaterial.setOperateIp("124.124.124");
            examTrainCaMaterial.setOperateTime(new Date());
            examTrainCaMaterialList.add(examTrainCaMaterial);
        }
        examTrainCaMaterialMapper.batchInsert(examTrainCaMaterialList);
    }

    @Override
    public PageResult<TrainPersonParam> getPage(PageQuery pageQuery, TrainPersonSearchParam trainPersonSearchParam) {
        int count=examTrainCaMaterialMapper.countList(trainPersonSearchParam);
        if (count > 0) {
            List<TrainPersonParam> trainPersonParamList = examTrainCaMaterialMapper.getPage(pageQuery,trainPersonSearchParam);
            PageResult<TrainPersonParam> pageResult = new PageResult<>();
            pageResult.setData(trainPersonParamList);
            pageResult.setTotal(count);
            pageResult.setPageNo(pageQuery.getPageNo());
            pageResult.setPageSize(pageQuery.getPageSize());
            return pageResult;
        }
        PageResult<TrainPersonParam> pageResult = new PageResult<>();
        return pageResult;
    }
}
