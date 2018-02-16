package com.ciyou.edu.mapper

import com.ciyou.edu.entity.Student
import com.github.pagehelper.Page
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.One
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Select

/**
 * @Author C.
 * @Date 2018-02-15 9:59
 */
interface StudentMapper {

    @Select("select * from Student where  isAvalible = 1 order by sid")
    @Results([
            //查询关联对象
            @Result(property = "classes",
                    column = "classesId",
                    one = @One(select = "com.ciyou.edu.mapper.ClassesMapper.getClasses"))])
    Page<Student> findAllStudent()

    @Select("select * from Student where  isAvalible = 1 and studentId = #{studentId}")
    Student findByStudentId(@Param("studentId")String studentId)

    @Insert("insert into Student(studentId,name,password,sex,age,createTime,mobile,parentMobile,email,parentEmail,picImg,classesId) values(#{studentId},#{name},#{password},#{sex},#{age},#{createTime},#{mobile},#{parentMobile},#{email},#{parentEmail},#{picImg},#{classes.classesId})")
    int addStudent(Student student)

    @Select("select * from Student where  isAvalible = 1 and sid = #{sid}")
    @Results([
            //查询关联对象
            @Result(property = "classes",
                    column = "classesId",
                    one = @One(select = "com.ciyou.edu.mapper.ClassesMapper.getClasses"))])
    Student getStudentById(@Param("sid")String sid)
}
