package com.StudentManage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String studentClass;

    @NotBlank(message = "学号不能为空")
    private Long studentId;

    @NotBlank(message = "姓名不能为空")
    private String studentName;

    private String studentSex;

    private String studentMajor;

    @NotBlank(message = "身份证号不能为空")
    private String studentIdNum;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date studentBirthday;

    private String politicalOutlook;

    private String studentNation;

    private String homeAddress;

    private Integer isDelete;
}
