package site.luoyu;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.luoyu.Exception.InputFormatException;

import java.io.*;

/**
 * App Tester.
 *
 * @author ZhangYang
 * @version 1.0
 * @since <pre>09/10/2017</pre>
 */
public class AppTest {
    App app;
    ByteArrayInputStream StringIn;
    ByteArrayOutputStream bytes;
    InputStream in;
    OutputStream out;
    String testCase1=
            "abcdefghijklmnopqrst1234567890\n" +
            "U001 2016-06-02 22:00~22:00 A\n" +
            "U002 2018-08-01 19:00~22:00 A\n" +
            "U003 2018-08-02 13:00~17:00 B\n" +
            "U004 2018-08-03 15:00~16:00 C\n" +
            "U005 2018-08-05 09:00~11:00 D\n" +
            "\n";
    String testCase1Expect="Error: the booking is invalid!\r\n" +
            "Error: the booking is invalid!\r\n" +
            "Success: the booking is accepted!\r\n" +
            "Success: the booking is accepted!\r\n" +
            "Success: the booking is accepted!\r\n" +
            "Success: the booking is accepted!\r\n" +
            "收入汇总\n" +
            "---\r\n" +
            "场地:A\r\n" +
            "2018-08-01 19:00~22:00 200元\r\n" +
            "小计: 200元\r\n" +
            "\r\n" +
            "场地:B\r\n" +
            "2018-08-02 13:00~17:00 200元\r\n" +
            "小计: 200元\r\n" +
            "\r\n" +
            "场地:C\r\n" +
            "2018-08-03 15:00~16:00 50元\r\n" +
            "小计: 50元\r\n" +
            "\r\n" +
            "场地:D\r\n" +
            "2018-08-05 09:00~11:00 80元\r\n" +
            "小计: 80元\r\n" +
            "---\n" +
            "总计: 530元\r\n";
    String testCase2=
            "U002 2018-08-01 19:00~22:00 A\n" +
            "U003 2018-08-01 18:00~20:00 A\n" +
            "U002 2018-08-01 19:00~22:00 A C\n" +
            "U002 2018-08-01 19:00~22:00 A C\n" +
            "U003 2018-08-01 18:00~20:00 A\n" +
            "U003 2018-08-02 13:00~17:00 B\n" +
            "\n";
    String testCase2Expect="Success: the booking is accepted!\r\n" +
            "Error: the booking conflicts with existing bookings!\r\n" +
            "Success: the booking is accepted!\r\n" +
            "Error: the booking being cancelled does not exist!\r\n" +
            "Success: the booking is accepted!\r\n" +
            "Success: the booking is accepted!\r\n" +
            "收入汇总\n" +
            "---\r\n" +
            "场地:A\r\n" +
            "2018-08-01 18:00~20:00 160元\r\n" +
            "2018-08-01 19:00~22:00 违约金 100元\r\n" +
            "小计: 260元\r\n" +
            "\r\n" +
            "场地:B\r\n" +
            "2018-08-02 13:00~17:00 200元\r\n" +
            "小计: 200元\r\n" +
            "\r\n" +
            "场地:C\r\n" +
            "小计: 0元\r\n" +
            "\r\n" +
            "场地:D\r\n" +
            "小计: 0元\r\n" +
            "---\n" +
            "总计: 460元\r\n";
    @Before
    public void before() throws Exception {
        app = new App();
        in = System.in;
        out = System.out;
        bytes = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bytes));
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void testMain() throws Exception {
        //作业中要求的测试用例都在这里了
        String[] args ={""};
        StringIn = new ByteArrayInputStream(testCase1.getBytes());
        System.setIn(StringIn);
        App.main(args);
        String result = bytes.toString();
        Assert.assertEquals(testCase1Expect,result);
        bytes.reset();

        StringIn = new ByteArrayInputStream(testCase2.getBytes());
        System.setIn(StringIn);
        App.main(args);
        result = bytes.toString();
        Assert.assertEquals(testCase2Expect,result);
    }

    /**
     * Method: handleInput(String input)
     */
    @Test
    public void testHandleInput(){
        String[] testData = {
                //测试正确输入情况
                "U123 2018-06-02 20:00~22:00 A",
                "U123 2018-06-02 20:00~22:00 C",
                "U123    2018-06-02     20:00~22:00     A",
                //以下是错误数据的测试
                "salfjaogelgj;asdjkfoiakdl;g;ldfadge",
                //空格有问题
                " U123 2016-06-02 20:00~22:00 A",
                " U123 2016-06-02 20:00~22:00 A",
                //非整点
                "U123 2016-06-02 20:11~22:00 A",
                "U123 2016-06-02 20:00~22:33 A",
                //测试结束时间比开始时间晚的情况
                "U123 2016-06-02 22:00~20:00 A",
                //测试过期时间
                "U123 2011-06-02 20:00~22:00 A",
                ""
        };
        for (int i = 0; i < testData.length; i++) {
            if(i<3){
                //不应该抛出异常的用例
                try {
                    App.handleInput(testData[i]);
                } catch (InputFormatException e) {
                    Assert.fail("Shouldn't Throw Exception");
                }
            }else {
                //应该抛出格式异常的用例
                try {
                    App.handleInput(testData[i]);
                    Assert.fail("FormatException is not thrown as expected");
                } catch (Exception e) {
                    Assert.assertTrue(e instanceof InputFormatException);
                }
            }
        }
    }


}
