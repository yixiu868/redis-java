package com.ww.redis.client.redisson.complex.obj;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaohua
 * @description TODO
 * @date 2021-9-6 22:57
 */
public class Student implements Serializable {

    private String name;

    private String grade;

    private List<Teacher> teachers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", teachers=" + teachers +
                '}';
    }
}
