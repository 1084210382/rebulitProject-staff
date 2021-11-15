package com.example.demo.trainExam.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ExamExam {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.id
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.name
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.subject_id
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */

    private Integer industry;

    private Integer workType;

    private Integer subjectId;

    private Integer trainCourse;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.exam_type
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    private Integer examType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.start_time
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.end_time
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.remark
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.operate_time
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    private Date operateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.operator
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    private String operator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.operator_ip
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    private String operatorIp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column exam_exam.pre_exam
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    private String preExam;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.id
     *
     * @return the value of exam_exam.id
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.id
     *
     * @param id the value for exam_exam.id
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.name
     *
     * @return the value of exam_exam.name
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.name
     *
     * @param name the value for exam_exam.name
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.subject_id
     *
     * @return the value of exam_exam.subject_id
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    public Integer getIndustry() {
        return industry;
    }

    public Integer getWorkType() {
        return workType;
    }
    public Integer getTrainCourse() {
        return trainCourse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.subject_id
     *
     * @param subjectId the value for exam_exam.subject_id
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    public void setIndustry(Integer industry) {
        this.industry = industry;
    }
    public void setWorkType(Integer workType) {
        this.workType = workType;
    }
    public void setTrainCourse(Integer trainCourse) {
        this.trainCourse = trainCourse;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.exam_type
     *
     * @return the value of exam_exam.exam_type
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public Integer getExamType() {
        return examType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.exam_type
     *
     * @param examType the value for exam_exam.exam_type
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setExamType(Integer examType) {
        this.examType = examType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.start_time
     *
     * @return the value of exam_exam.start_time
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.start_time
     *
     * @param startTime the value for exam_exam.start_time
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.end_time
     *
     * @return the value of exam_exam.end_time
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.end_time
     *
     * @param endTime the value for exam_exam.end_time
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.remark
     *
     * @return the value of exam_exam.remark
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.remark
     *
     * @param remark the value for exam_exam.remark
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.operate_time
     *
     * @return the value of exam_exam.operate_time
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.operate_time
     *
     * @param operateTime the value for exam_exam.operate_time
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.operator
     *
     * @return the value of exam_exam.operator
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public String getOperator() {
        return operator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.operator
     *
     * @param operator the value for exam_exam.operator
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.operator_ip
     *
     * @return the value of exam_exam.operator_ip
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public String getOperatorIp() {
        return operatorIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.operator_ip
     *
     * @param operatorIp the value for exam_exam.operator_ip
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp == null ? null : operatorIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column exam_exam.pre_exam
     *
     * @return the value of exam_exam.pre_exam
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public String getPreExam() {
        return preExam;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column exam_exam.pre_exam
     *
     * @param preExam the value for exam_exam.pre_exam
     *
     * @mbg.generated Mon May 13 20:30:10 CST 2019
     */
    public void setPreExam(String preExam) {
        this.preExam = preExam == null ? null : preExam.trim();
    }
}