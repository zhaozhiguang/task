package com.hqjy.tiku.task.support;

import com.hqjy.tiku.task.entity.*;
import com.hqjy.tiku.task.schedule.TaskWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

/**
 * 定时任务
 * @return
 */
public class DefaultTask implements TaskWrapper.Task {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Override
    public void schedule() {
        //StreamSource source = new StreamSource(new StringReader(""));
        //StreamResult result = new StreamResult(System.out);
        //webServiceTemplate.sendSourceAndReceiveToResult(source, result);
    }

    /**
     * 1.同步有更改的用户列表
     */
    private void doSyncUser(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/ITeacherStudent", User.class);
    }

    /**
     * 2.同步有更改的校区列表
     */
    private void doSyncSchool(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/IOrgsList", School.class);
    }

    /**
     * 3.同步有更改的课程列表
     */
    private void doSyncCourse(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/ISubjList", Course.class);
    }

    /**
     * 4.同步有更改的班型列表
     */
    private void doSyncClassTemplate(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/IClassList", ClassTemplate.class);
    }

    /**
     * 5.同步有更改的(班型中包含的)课程列表
     */
    private void doSyncCourseOfClassTemplate(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/IClassCourse", CourseOfClassTemplate.class);
    }

    /**
     * 6.同步有更改的开班列表
     */
    private void doSyncClassInfo(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/IOpenClass", ClassInfo.class);
    }

    /**
     * 7.同步有更改的(开班中包含的)学员列表
     */
    private void doSyncStudentOfClass(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/IOpeningStu", StudentOfClass.class);
    }

    /**
     * 10.同步有更改的考勤列表
     */
    private void doSyncCheckIn(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/IStuArrangedplanH", CheckIn.class);
    }

    /**
     * 11.同步有更改的(考勤表中包含的)学员列表
     */
    private void doSyncStudentOfCheckIn(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/IStuArrangedplanB", StudentOfCheckIn.class);
    }

    /**
     * 8.同步有更改的老师授课列表
     */
    private void doSyncTeaching(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/ITeacList", Teaching.class);
    }

    /**
     * 9.同步有更改的老师授课列表
     */
    private void doSyncStudentOfClassTemplate(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/IStuClassTemplate", StudentOfClassTemplate.class);
    }

    /**
     * 12.同步有更改的考试通过的信息
     */
    private void doSyncExamPass(){
        restTemplate.getForObject("http://stuArrang.webservice.hq.itf.nc/IExamPassStu", ExamPass.class);
    }


}
