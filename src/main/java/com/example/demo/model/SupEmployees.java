package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author demo
 * @since 2021-09-07
 */
@TableName("sup_employees")
public class SupEmployees extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 从业人员类型
     */
    @TableField("staff_type")
    private Integer staffType;

    /**
     * 用户类型（未使用）
     */
    @TableField("user_type")
    private Integer userType;

    /**
     * 用户名称
     */
    @TableField("username")
    private String username;

    /**
     * 身份证号
     */
    @TableField("id_number")
    private String idNumber;

    /**
     * 加密后的密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别
     */
    @TableField("user_gender")
    private String userGender;

    /**
     * 体检情况
     */
    @TableField("physical_examination")
    private String physicalExamination;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 健康证照片（证件照片）
     */
    @TableField("health_photo")
    private String healthPhoto;

    /**
     * 身份证正面照片（人脸识别照片不确定能够识别）
     */
    @TableField("id_card_photo")
    private String idCardPhoto;

    /**
     * 工作种类id（）
     */
    @TableField("wor_type_id")
    private Integer worTypeId;

    /**
     * 工作种类名字
     */
    @TableField("word_type_name")
    private String wordTypeName;

    /**
     * 教育程度
     */
    @TableField("education_level")
    private String educationLevel;

    /**
     * 资质证号
     */
    @TableField("certificate_number")
    private String certificateNumber;

    /**
     * 注册证书号（药师，作业人员）
     */
    @TableField("register_number")
    private String registerNumber;

    /**
     * 执业类别（药师，作业人员）
     */
    @TableField("practice_type")
    private String practiceType;

    /**
     * 执业范围（药师，作业人员）
     */
    @TableField("practice_range")
    private String practiceRange;

    /**
     * 执业地区（药师，作业人员）
     */
    @TableField("practice_area")
    private String practiceArea;

    /**
     * 执业证书（药师，作业人员）
     */
    @TableField("practice_cert_photo")
    private String practiceCertPhoto;

    /**
     * 执业资格证书（药师，作业人员）
     */
    @TableField("practice_quali_photo")
    private String practiceQualiPhoto;

    /**
     * 其他照片
     */
    @TableField("other_photo")
    private String otherPhoto;

    /**
     * 发证日期
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 有效期至
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 发证机关
     */
    @TableField("issuing_authority")
    private String issuingAuthority;

    /**
     * wechat的openId
     */
    @TableField("we_chat_id")
    private String weChatId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getStaffType() {
        return staffType;
    }

    public void setStaffType(Integer staffType) {
        this.staffType = staffType;
    }
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getHealthPhoto() {
        return healthPhoto;
    }

    public void setHealthPhoto(String healthPhoto) {
        this.healthPhoto = healthPhoto;
    }
    public String getIdCardPhoto() {
        return idCardPhoto;
    }

    public void setIdCardPhoto(String idCardPhoto) {
        this.idCardPhoto = idCardPhoto;
    }
    public Integer getWorTypeId() {
        return worTypeId;
    }

    public void setWorTypeId(Integer worTypeId) {
        this.worTypeId = worTypeId;
    }
    public String getWordTypeName() {
        return wordTypeName;
    }

    public void setWordTypeName(String wordTypeName) {
        this.wordTypeName = wordTypeName;
    }
    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }
    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }
    public String getPracticeType() {
        return practiceType;
    }

    public void setPracticeType(String practiceType) {
        this.practiceType = practiceType;
    }
    public String getPracticeRange() {
        return practiceRange;
    }

    public void setPracticeRange(String practiceRange) {
        this.practiceRange = practiceRange;
    }
    public String getPracticeArea() {
        return practiceArea;
    }

    public void setPracticeArea(String practiceArea) {
        this.practiceArea = practiceArea;
    }
    public String getPracticeCertPhoto() {
        return practiceCertPhoto;
    }

    public void setPracticeCertPhoto(String practiceCertPhoto) {
        this.practiceCertPhoto = practiceCertPhoto;
    }
    public String getPracticeQualiPhoto() {
        return practiceQualiPhoto;
    }

    public void setPracticeQualiPhoto(String practiceQualiPhoto) {
        this.practiceQualiPhoto = practiceQualiPhoto;
    }
    public String getOtherPhoto() {
        return otherPhoto;
    }

    public void setOtherPhoto(String otherPhoto) {
        this.otherPhoto = otherPhoto;
    }
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }
    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    @Override
    public String toString() {
        return "SupEmployees{" +
            "id=" + id +
            ", staffType=" + staffType +
            ", userType=" + userType +
            ", username=" + username +
            ", idNumber=" + idNumber +
            ", password=" + password +
            ", userGender=" + userGender +
            ", physicalExamination=" + physicalExamination +
            ", phone=" + phone +
            ", healthPhoto=" + healthPhoto +
            ", idCardPhoto=" + idCardPhoto +
            ", worTypeId=" + worTypeId +
            ", wordTypeName=" + wordTypeName +
            ", educationLevel=" + educationLevel +
            ", certificateNumber=" + certificateNumber +
            ", registerNumber=" + registerNumber +
            ", practiceType=" + practiceType +
            ", practiceRange=" + practiceRange +
            ", practiceArea=" + practiceArea +
            ", practiceCertPhoto=" + practiceCertPhoto +
            ", practiceQualiPhoto=" + practiceQualiPhoto +
            ", otherPhoto=" + otherPhoto +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", issuingAuthority=" + issuingAuthority +
            ", weChatId=" + weChatId +
        "}";
    }
}
