package com.StudentManage.service.Impl;

import com.StudentManage.enums.AppHttpCodeEnum;
import com.StudentManage.mapper.StudentMapper;
import com.StudentManage.pojo.Student;
import com.StudentManage.service.StudentService;
import com.StudentManage.utils.CommonResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public CommonResult<?> findAllStudent() {
        List<Student> studentList = studentMapper.selectList(null);
        return CommonResult.okResult(studentList);
    }

    @Override
    public CommonResult<?> deleteStudent(Long StuId) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStudentId, StuId);
        int delete = studentMapper.delete(queryWrapper);
        if (delete == 1) {
            return CommonResult.okResult();
        }
        return CommonResult.errorResult(AppHttpCodeEnum.DELETE_FAIL);
    }

    @Override
    public CommonResult<?> addStudent(Student student) {
        student.setIsDelete(0);
        int insert = studentMapper.insert(student);
        if (insert == 1) {
            return CommonResult.okResult();
        }
        return CommonResult.errorResult(AppHttpCodeEnum.ADD_STUDENT_FAIL);
    }

    @Override
    public CommonResult<?> getStudentInfo(Long id) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStudentId, id);
        List<Student> studentList = studentMapper.selectList(queryWrapper);
        return CommonResult.okResult(studentList);
    }

    @Override
    public CommonResult<?> editStuInfo(Student student) {
        if (student.getStudentId() != null) {
            LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Student::getStudentId, student.getStudentId());
            int i = studentMapper.update(student, queryWrapper);
            if (i == 1) {
                return CommonResult.okResult();
            }
        }
        return CommonResult.errorResult(AppHttpCodeEnum.ADD_STUDENT_FAIL);
    }
}