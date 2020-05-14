package com.eam;

import com.eam.mybatis.dao.ReceiptMapper;
import com.eam.mybatis.model.R5PropertyValues;
import com.eam.utils.MyUtils;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@ContextConfiguration({"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class SteveTest {
    //@Resource
    //ReceiptMapper mapper;

    @Test
    public void TestGetNextFlag() throws CloneNotSupportedException {
        //service = GetService.getInstance().getMybatisReceiptService();
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("TableId","U5OBJBATCERESULTFLAGID");
//        map.put("FlagId","");
//
//        List<String> List1 = new ArrayList<>();
//        ArrayList<String> List2 = new ArrayList<>();
//
//        List1.add("abc");
////        List2.add("123");
//        List1.add("123");
////        List2.add("abc");
//        List1.add("!@#");
//
//        System.out.println(List1 == (List2));

        //System.out.println(mapper.getNextFlagId(map));

//        System.out.println(List1);
//        System.out.println(List1.subList(1,3));
//        String date = "20190807";
//
//        String date1 = "20190906";
//
//        System.out.println(date.compareTo(date1));

//        int totalDay = Integer.parseInt(date.substring(1,4)) * (((Integer.parseInt(date.substring(1,4))%4 == 0)?366:365) + Integer.parseInt(date.substring(5,6))*
//        List1.add("abc");
//        List1.add("def");List1.add("ghi");
//        List1.add("jkl");
        //List1.add("mno");

//        System.out.println(List1.subList(0,4).toString());
//        System.out.println(List1.subList(4,4).size()==0);

//        String a = "1";
//        String b = "12345";
//        //System.out.println(a.substring(1,2));
//        System.out.println(a.substring(0,1));
//        System.out.println(b.substring(1,2));
//        System.out.println(b.substring(0,1));

        System.out.println(MyUtils.isLegalDateFormat("2019-12-27"));



    }
}
