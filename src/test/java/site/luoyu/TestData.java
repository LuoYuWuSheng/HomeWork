package site.luoyu;

import site.luoyu.Dao.Entity.OrderEntity;
import site.luoyu.Exception.InputFormatException;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/12.
 * 避免每次测试都要写数据，将测试封装成一个完整的类
 */
public class TestData {
    OrderEntity[] testList;
    String[] testData = {
            //0.测试订单冲突
            "U002 2018-08-01 19:00~22:00 A",
            //1.不冲突
            "U002 2018-08-01 09:00~12:00 A",
            //2.冲突订单
            "U003 2018-08-01 11:00~12:00 A",
            //3
            "U003 2018-08-01 11:00~13:00 A",
            //4.取消不存在的订单
            "U003 2018-08-01 19:00~22:00 A C",
            //5.取消订单
            "U002 2018-08-01 19:00~22:00 A C",
            //6.订单超出球场开放时间
            "U003 2018-08-01 01:00~13:00 A",
    };
    public TestData() {
        testList = new OrderEntity[testData.length];
        //准备测试数据
        for (int i = 0; i < testData.length; i++) {
            try {
                testList[i] = App.handleInput(testData[i]);
            } catch (InputFormatException e) {
                e.printStackTrace();
            }
        }
    }
    public OrderEntity[] getTestData(){
        return testList;
    }
}
