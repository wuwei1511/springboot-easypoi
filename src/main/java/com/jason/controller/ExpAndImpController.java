package com.jason.controller;

import com.jason.entity.Person;
import com.jason.util.ExpAndImpUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @author jason
 * @date 2019-02-18 17:19
 */
@Controller
public class ExpAndImpController {
    Logger logger = LoggerFactory.getLogger(ExpAndImpController.class);


    @RequestMapping("export")
    public void export(HttpServletResponse response){

        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("路飞","1",new Date());
        Person person2 = new Person("娜美","2", DateUtils.addDays(new Date(),3));
        Person person3 = new Person("索隆","1", DateUtils.addDays(new Date(),10));
        Person person4 = new Person("小狸猫","1", DateUtils.addDays(new Date(),-10));
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        ExpAndImpUtil.exportExcel(personList,"花名册","草帽一伙",Person.class,"海贼王.xls",response);
    }

    @RequestMapping("importExcel")
    public void importExcel(){
//        String filePath = "C:/Users/MAIBENBEN/Desktop/海贼王.xls";
//        String filePath = "F:/prodemo/springboot-easypoi/src/main/resources/impTemp/海贼王.xls";
        String filePath = "F:/海贼王.xls";
        File f = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //解析excel
        List<Person> personList = ExpAndImpUtil.importExcel(filePath,1,1,Person.class);
        //也可以使用MultipartFile,使用 ExpAndImpUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        logger.info(personList.toString());
        System.out.println("导入数据一共【"+personList.size()+"】行");

        //TODO 保存数据库
        //...
    }

}
