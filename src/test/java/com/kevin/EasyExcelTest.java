package com.kevin;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kevin.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * @description: test
 * @author: Mr.Liu
 * @version: v1.0
 * @create: 2019年10月9日15:34:37
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyExcelTest.class)
@Slf4j
public class EasyExcelTest {
 
    @Test
    public void excelImport() {
        List<Object> list = EasyExcelFactory.read(getInputStream("D:\\kevin\\data.xlsx"), new Sheet(1));
        String listString = JSONObject.toJSONString(list);
        log.info("list:{}",  listString);
        JSONArray arryList = JSONObject.parseArray(listString);
        // 处理数据
        List<Map<String, Object>> listMap = new ArrayList<>();
        for (int i = 1; i < arryList.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            JSONArray rowData = JSONObject.parseArray(JSONObject.toJSONString(arryList.get(i)));
            map.put("id", rowData.get(0));
            map.put("name", rowData.get(1));
            map.put("sex", rowData.get(2));
            int birthday = Integer.parseInt(rowData.get(3).toString());
            map.put("birthday", formatExcelDate(birthday));
            listMap.add(map);
        }
        log.info("data:{}", JSONObject.toJSONString(listMap));
    }
 
    private InputStream getInputStream(String fileName) {
        try {
            return new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化Excel时间
     * @param day
     * @return yyyy-MM-dd
     */
    private String formatExcelDate(int day) {
        Calendar calendar = new GregorianCalendar(1900,0,-1);
        Date gregorianDate = calendar.getTime();
        String formatExcelDate = DateUtils.format(DateUtils.addDay(gregorianDate, day), DateUtils.YYYYMMDD);
        return formatExcelDate;
    }

}
