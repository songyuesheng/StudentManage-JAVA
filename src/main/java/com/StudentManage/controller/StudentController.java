package com.StudentManage.controller;

import com.StudentManage.pojo.Student;
import com.StudentManage.service.StudentService;
import com.StudentManage.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/studentList")
    public CommonResult<?> findStudent() {
        return studentService.findAllStudent();
    }

    @DeleteMapping("/deleteStu/{StuId}")
    public CommonResult<?> deleteStudent(@PathVariable("StuId") Long StuId) {
        return studentService.deleteStudent(StuId);
    }

    @PostMapping("/addStu")
    public CommonResult<?> addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/stuInfo/{id}")
    public CommonResult<?> getStudentInfo(@PathVariable("id") Long id) {
        return studentService.getStudentInfo(id);
    }

    @PostMapping("/editStu")
    public CommonResult<?> editStuInfo(@RequestBody Student student) {
        return studentService.editStuInfo(student);
    }
}