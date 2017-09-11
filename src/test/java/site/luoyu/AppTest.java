package site.luoyu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.luoyu.Exception.InputFormatException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
    InputStream in;
    String testCase1=
            "abcdefghijklmnopqrst1234567890\n" +
            "U001 2016-06-02 22:00~22:00 A\n" +
            "U002 2018-08-01 19:00~22:00 A\n" +
            "U003 2018-08-02 13:00~17:00 B\n" +
            "U004 2018-08-03 15:00~16:00 C\n" +
            "U005 2018-08-05 09:00~11:00 D\n" +
            "\n";
    String testCase2=
            "U002 2018-08-01 19:00~22:00 A\n" +
            "U003 2018-08-01 19:00~20:00 A\n" +
            "U002 2018-08-01 19:00~22:00 A C\n" +
            "U002 2018-08-01 19:00~22:00 A C\n" +
            "U003 2018-08-01 18:00~20:00 A\n" +
            "U003 2018-08-02 13:00~17:00 B\n" +
            "\n";

    @Before
    public void before() throws Exception {
        app = new App();
        in = System.in;
        System.setIn(StringIn);
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void testMain() throws Exception {

    }

    /**
     * Method: handleInput(String input)
     */
    @Test
    public void testHandleInput(){
        String[] testData = {
                "salfjaogelgj;asdjkfoiakdl;g;ldfadge",
                //测试正确输入情况
                "U123 2018-06-02 20:00~22:00 A",
                "U123 2018-06-02 20:00~22:00 C",
                "U123    2018-06-02     20:00~22:00     A",
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
            try {
                App.handleInput(testData[i]);
                System.out.println("Success: the booking is accepted!");
            } catch (InputFormatException e) {
                System.out.println("Error: the booking is invalid!");
            }
        }
    }


}
