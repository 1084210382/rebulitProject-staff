package com.example.demo.trainExam.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.example.demo.trainExam.controller.param.TrainCaMaterialParam;
import com.example.demo.trainExam.controller.param.TrainMaterialParam;
import com.example.demo.trainExam.dao.ExamTrainCourseMaterialMapper;
import com.example.demo.trainExam.dao.ExamTrainMaterialMapper;
import com.example.demo.trainExam.model.ExamTrainCourseMaterial;
import com.example.demo.trainExam.service.ExamTrainCourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author zcc
 * @date 2019/4/29 22:01
 */
@Service
public class ExamTrainCourseMaterialServiceImpl implements ExamTrainCourseMaterialService {
    @Autowired
    private ExamTrainMaterialMapper examTrainMaterialMapper;
    @Autowired
    private ExamTrainCourseMaterialMapper examTrainCourseMaterialMapper;

    @Override
    public List<TrainMaterialParam> getListByCourseId(int courseId) {
        List<Integer> materialIdList = examTrainCourseMaterialMapper.getMaterialIdListByCourseId(courseId);
        if (CollectionUtils.isEmpty(materialIdList)) {
            return Lists.newArrayList();
        }
        return examTrainMaterialMapper.getByIdList(materialIdList);
    }

    @Override
    public List<TrainCaMaterialParam> getCaListByCourseId(int caId, int courseId) {
        List<Integer> materialIdList = examTrainCourseMaterialMapper.getMaterialIdListByCourseId(courseId);
        if (CollectionUtils.isEmpty(materialIdList)) {
            return Lists.newArrayList();
        }
        return examTrainMaterialMapper.getCaTrainList(caId,materialIdList,courseId);
    }

    @Override
    public void changeCourseMaterials(int courseId, List<Integer> materialIdList) {
        List<Integer> originMaterialIdList = examTrainCourseMaterialMapper.getMaterialIdListByCourseId(courseId);
        if (originMaterialIdList.size() == materialIdList.size()) {
            Set<Integer> originMaterialIdSet = Sets.newHashSet(originMaterialIdList);
            Set<Integer> materialIdSet = Sets.newHashSet(materialIdList);
            originMaterialIdSet.removeAll(materialIdSet);
            if (CollectionUtils.isEmpty(originMaterialIdSet)) {
                return;
            }
        }
        updateCourseMaterials(courseId, materialIdList);
    }

    @Transactional
    public void updateCourseMaterials(int courseId, List<Integer> materialIdList) {
        examTrainCourseMaterialMapper.deleteByCourseId(courseId);

        if (CollectionUtils.isEmpty(materialIdList)) {
            return;
        }
        List<ExamTrainCourseMaterial> examTrainCourseMaterialList = Lists.newArrayList();
        for (Integer materialId : materialIdList) {
            ExamTrainCourseMaterial examTrainCourseMaterial = new ExamTrainCourseMaterial();
            examTrainCourseMaterial.setCourseId(courseId);
            examTrainCourseMaterial.setMaterialId(materialId);
            examTrainCourseMaterial.setOperator("操作人");
            examTrainCourseMaterial.setOperateIp("124.124.124");
            examTrainCourseMaterial.setOperateTime(new Date());
            examTrainCourseMaterialList.add(examTrainCourseMaterial);
        }
        examTrainCourseMaterialMapper.batchInsert(examTrainCourseMaterialList);
    }
}
