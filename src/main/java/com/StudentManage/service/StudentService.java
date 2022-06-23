package com.StudentManage.service;

import com.StudentManage.pojo.Student;
import com.StudentManage.utils.CommonResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface StudentService extends IService<Student> {
    CommonResult<?> findAllStudent();

    CommonResult<?> deleteStudent(Long StuId);

    CommonResult<?> addStudent(Student student);

    CommonResult<?> getStudentInfo(Long id);

    CommonResult<?> editStuInfo(Student student);
}
