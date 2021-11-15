package com.example.demo.trainExam.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.demo.common.commonRequest.UserSession;
import com.example.demo.common.doPage.PageQuery;
import com.example.demo.common.doPage.PageResult;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.exception.EmBusinessError;

import com.example.demo.trainExam.controller.param.*;
import com.example.demo.trainExam.controller.searchParam.ExamSubjectSearchParam;
import com.example.demo.trainExam.dao.*;
import com.example.demo.trainExam.model.ExamCaExam;
import com.example.demo.trainExam.model.ExamSubject;
import com.example.demo.trainExam.model.ExamTopicBank;
import com.example.demo.trainExam.service.ExamSubjectService;
import com.example.demo.trainExam.service.ExamSubjectTopicService;
import com.example.demo.trainExam.service.ExamSubmitService;
import com.example.demo.trainExam.service.ExamTrainCourseMaterialService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zcc
 * @date 2019/4/29 20:54
 */
@Service
public class ExamSubjectServiceImpl implements ExamSubjectService {
    @Autowired
    private ExamSubjectMapper examSubjectMapper;
    @Autowired
    private ExamSubjectTopicMapper examSubjectTopicMapper;
    @Autowired
    private ExamTopicBankMapper examTopicBankMapper;
    @Autowired
    private ExamCaExamMapper examCaExamMapper;
    @Autowired
    private ExamTrainCaMaterialMapper examTrainCaMaterialMapper;
    @Autowired
    private ExamSubjectTopicService examSubjectTopicService;
    @Autowired
    private ExamSubmitService examSubmitService;
    @Autowired
    private ExamTrainCourseMaterialService examTrainCourseMaterialService;
    @Autowired
    private ExamExamMapper examExamMapper;

    @Override
    public PageResult<ExamSubjectSearchParam> getPage(PageQuery pageQuery, ExamSubjectSearchParam examSubjectSearchParam) {
        int count=examSubjectMapper.countList();
        if (count > 0) {
            List<ExamSubjectSearchParam> examSubjectSearchParamList = examSubjectMapper.getPage(pageQuery, examSubjectSearchParam);
            PageResult<ExamSubjectSearchParam> pageResult = new PageResult<>();
            pageResult.setData(examSubjectSearchParamList);
            pageResult.setTotal(count);
            pageResult.setPageNo(pageQuery.getPageNo());
            pageResult.setPageSize(pageQuery.getPageSize());
            return pageResult;
        }
        PageResult<ExamSubjectSearchParam> pageResult = new PageResult<>();
        return pageResult;
    }

    @Override
    @Transactional
    public void insert(ExamSubject examSubject) {
        if(examSubjectMapper.countByIndustryAndWorkType(examSubject.getIndustry(),examSubject.getWorkType())>0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"存在相同科目");
        }

        examSubject.setOperator("操作人");
        examSubject.setOperatorIp("14.124.124");
        examSubject.setOperateTime(new Date());

        examSubjectMapper.insertSelective(examSubject);

        List<ExamTopicBank> examTopicBankList=examTopicBankMapper.selectRandomByNum(examSubject);

        examSubjectTopicService.changeSubjectTopics(examSubject,examTopicBankList);
    }

    @Override
    @Transactional
    public void update(ExamSubject examSubject) {
        ExamSubject before = examSubjectMapper.selectByPrimaryKey(examSubject.getId());
        if(before == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"待更新科目不存在");
        }
        examSubject.setOperator("操作人");
        examSubject.setOperatorIp("14.124.124");
        examSubject.setOperateTime(new Date());
        examSubjectMapper.updateByPrimaryKeySelective(examSubject);
        List<ExamTopicBank> examTopicBankList=examTopicBankMapper.selectRandomByNum(examSubject);

        examSubjectTopicMapper.deleteBySubjectId(examSubject.getId());

        examSubjectTopicService.changeSubjectTopics(examSubject,examTopicBankList);
    }

    @Override
    @Transactional
    public void delete(int id) {
        ExamSubject examSubject = examSubjectMapper.selectByPrimaryKey(id);
        if(examSubject==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"待删除的不存在，无法删除");
        }
        examSubjectMapper.deleteByPrimaryKey(id);
        examSubjectTopicService.deleteBySubjectId(id);
    }

    @Override
    public List<Integer> getSubjectTopicIds(int id) {
        return examSubjectTopicMapper.getTopicIdListBySubjectId(id);
    }

    @Override
    public TopicNumParam getNum(ExamSubjectSearchParam examSubjectSearchParam) {
        return examTopicBankMapper.getNum(examSubjectSearchParam);
    }

    @Override
    public Map<String, Object> getExamTopicList(CaTopicParam caTopicParam) {
        List<ExamCaTopic> examCaTopicList = examSubjectTopicService.getCaListBySubject(caTopicParam);
        ExamSubject examSubject = examSubjectMapper.selectByPrimaryKey(caTopicParam.getSubjectId());
        Map<String, Object> map = Maps.newHashMap();
        map.put("list",examCaTopicList);
        map.put("subject",examSubject);
        return map;
    }

    @Override
    public List<ExamSubjectParam> getWorkTypeExamList() {
        List<ExamCaExam> examCaExamList = examCaExamMapper.getByCaId(UserSession.getUserId());
        //ca workType
        int workType = 0;
        List<ExamSubjectParam> examSubjectList = examSubjectMapper.getByWorkType(workType);

        List<ExamCaExam> examPassList= examCaExamList.stream().filter(examCaExam -> examCaExam.getExamResult()==1).collect(Collectors.toList());

        List<ExamSubjectParam> newExamList = examSubjectList.stream().map(examSubject -> {
            if(checkIfTrain(UserSession.getUserId())){
                examSubject.setRemark("未完成培训");
            }
            else if(examPassList.size()>0){
                examSubject.setRemark("已完成考试");
                examSubject.setExamCaId(examPassList.get(0).getId());
            }else {
                examSubject.setRemark("点击进入考试");
            }
            return examSubject;
        }).collect(Collectors.toList());

        return newExamList;
    }


    //判断人员的培训课时是否达到40
    private boolean checkIfTrain(int caId) {
        if ((examTrainCaMaterialMapper.selectByCaId(caId))<40) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkTrain(int courseId,int caId){
        List<TrainCaMaterialParam> trainCaMaterialParamList = examTrainCourseMaterialService.getCaListByCourseId(caId,courseId);
        if(trainCaMaterialParamList.stream().filter(trainCaMaterialParam -> trainCaMaterialParam.getCompletionRate()!=1.0).collect(Collectors.toList()).size()>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamCaExam changeCaExam(int caId,ExamSubject examSubject, List<ExamCaTopic> examCaTopicList) {
        List<ExamCaTopic> examCaTopicList1 = examCaTopicList.stream()
                .filter(examCaTopic -> CollectionUtils.isEmpty(CheckAnswer(examCaTopic.getAnswer(), examCaTopic.getCheck())))
                .collect(Collectors.toList());
        float totalScore = 0;
        if (examCaTopicList1 != null && examCaTopicList1.size() != 0) {
            totalScore = examCaTopicList1.stream().map(ExamCaTopic::getScore).reduce((i, j) -> i + j).get();
        } else {
            totalScore = 0;
        }
        ExamCaExam examCaExam = new ExamCaExam();

        if(totalScore>=examSubject.getQualifiedScore()) {
            String latestCode = examCaExamMapper.selectNewestCodeByDate(new Date());
            String code = createNewDocNumber(latestCode);
            examCaExam.setCaCode(code);
        }

        examCaExam.setCaId(caId);
        examCaExam.setExamId(examSubject.getId());
        examCaExam.setExamScore(totalScore);
        examCaExam.setExamResult(totalScore >= examSubject.getQualifiedScore()?1:0);
        examCaExam.setExamDate(new Date());
        examCaExam.setOperator("操作人");
        examCaExam.setOperateIp("124.124.124");
        examCaExam.setOperateTime(new Date());
        examCaExamMapper.insertSelective(examCaExam);

        Integer examCaId=examCaExam.getId();
        examSubmitService.changeCaSubmit(caId,examCaId,examCaTopicList);
        return examCaExam;
    }

    String createNewDocNumber(String number) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        String currentTime = dateFormat.format(now);

        if (number != null && !number.equals("")) {// 在数据表中查到了，说明现在这个订单不是今天的第一单
            int num = Integer.valueOf(number.substring(5, 10));
            num++;
            if (num < 10) {
                String idNum = String.format("%06d", num);  //num<10,说明是个位数，前面要补两个0
                String date = currentTime + idNum;
                return date;
            } else if (num < 100) {
                String idNum = String.format("%06d", num);//num<100,说明是两位数，前面要补一个0
                String date = currentTime + idNum;
                return date;
            } else if (num < 1000) {
                String idNum = String.format("%06d", num);//num<100,说明是两位数，前面要补一个0
                String date = currentTime + idNum;
                return date;
            }else if (num < 10000) {
                String idNum = String.format("%06d", num);//num<100,说明是两位数，前面要补一个0
                String date = currentTime + idNum;
                return date;
            }else if (num < 100000) {
                String idNum = String.format("%06d", num);//num<100,说明是两位数，前面要补一个0
                String date = currentTime + idNum;
                return date;
            }else if (num < 1000000) {
                String idNum = String.valueOf(num);
                String date = currentTime + idNum;  //date = 20201111+124
                return date;
            } else {
                num = 1;
                String idNum = "00000" + num;
                String date = currentTime + idNum;
                return date;
            }
        } else {
            int num = 1;
            String idNum = "00000" + num;
            String date = currentTime + idNum;
            return date;
        }
    }

    private Set<String> CheckAnswer(String str1, String str2)
    {
        Set<String> set = new HashSet<>(Arrays.asList(str1.split(",")));
        if(str2==null||str2.isEmpty())
        {
            return set;
        }
        else
        {
            Set<String> set2 = new HashSet<>(Arrays.asList(str2.split(",")));
            set.removeAll(set2);
            return set;
        }
    }
}
