package com.ww.redis.client.redisson.complex.obj;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaohua
 * @description TODO
 * @date 2021-9-6 22:57
 */
public class Teacher implements Serializable {

    private String name;

    private String subject;

    private List<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", students=" + students +
                '}';
    }
}
