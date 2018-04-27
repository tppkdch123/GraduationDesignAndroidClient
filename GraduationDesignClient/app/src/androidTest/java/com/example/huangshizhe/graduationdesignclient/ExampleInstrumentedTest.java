package com.example.huangshizhe.graduationdesignclient;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.huangshizhe.graduationdesignclient.utils.CommenUtil;
import com.example.huangshizhe.graduationdesignclient.vo.Room;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.huangshizhe.graduationdesignclient", appContext.getPackageName());
    }

    public void test() throws Exception{
        String s="[{\"id\":1098730,\"providerId\":3736,\"title\":\"相见甚欢1.2\",\"defaultPrice\":17000,\"maxCapacity\":2,\"usableArea\":70,\"structure\":\"公卫\",\"createTime\":\"2018-01-01T06:00:00.000+0000\",\"updateTime\":\"2018-03-16T02:25:02.000+0000\",\"realCheck\":false,\"bedCount\":1,\"commentNumber\":0,\"hostMessage\":\"单个房间\",\"latitude\":39.949,\"longitude\":116.346,\"street\":\"北京海淀区交大东路31号院\",\"isDelete\":false,\"isOnsale\":true,\"cityId\":1,\"provinceId\":110000,\"districtId\":110108,\"block\":\"适中：入住的前5天12:00前退订，可获100%退款。之后退订不退款\",\"areaId\":13010,\"moveUpCancelDays\":5,\"earliestCheckInTime\":\"12:00\",\"lastestCheckInTime\":\"21:00\",\"lastestCheckOutTime\":\"12:00\"},{\"id\":1102111,\"providerId\":6430,\"title\":\"住进三里屯的家-Orange\",\"defaultPrice\":21900,\"maxCapacity\":2,\"usableArea\":68,\"structure\":\"公卫\",\"createTime\":\"2018-01-01T06:00:00.000+0000\",\"updateTime\":\"2018-03-16T02:25:02.000+0000\",\"realCheck\":false,\"bedCount\":1,\"commentNumber\":9,\"hostMessage\":\"单个房间\",\"latitude\":39.9322,\"longitude\":116.456,\"street\":\"北京朝阳区三里屯南路四号楼\",\"isDelete\":false,\"isOnsale\":true,\"cityId\":1,\"provinceId\":110000,\"districtId\":110105,\"block\":\"适中：入住的前5天12:00前退订，可获100%退款。之后退订不退款\",\"areaId\":12946,\"moveUpCancelDays\":5,\"earliestCheckInTime\":\"13:00\",\"lastestCheckInTime\":\"22:00\",\"lastestCheckOutTime\":\"12:00\"},{\"id\":1117852,\"providerId\":20244,\"title\":\"三里屯工体太古里酒吧街,精装主卧文艺范,地铁附近,吃喝玩乐一应俱全\",\"defaultPrice\":29600,\"maxCapacity\":2,\"usableArea\":60,\"structure\":\"公卫\",\"createTime\":\"2018-01-01T06:00:00.000+0000\",\"updateTime\":\"2018-03-16T02:25:06.000+0000\",\"realCheck\":false,\"bedCount\":1,\"commentNumber\":0,\"hostMessage\":\"单个房间\",\"latitude\":39.9399,\"longitude\":116.455,\"street\":\"北京朝阳区 三里屯北29号楼 三里屯北小区\",\"isDelete\":false,\"isOnsale\":true,\"cityId\":1,\"provinceId\":110000,\"districtId\":110105,\"block\":\"严格：入住的前7天12:00前退订，可获50%退款。之后退订不退款\",\"areaId\":12946,\"moveUpCancelDays\":7,\"earliestCheckInTime\":\"13:00\",\"lastestCheckInTime\":\"不限时间\",\"lastestCheckOutTime\":\"12:00\"}";
        List<Object> objects=CommenUtil.toBean(s, List.class);
        for(Object object:objects){
            System.out.println(object);
        }
    }
}
