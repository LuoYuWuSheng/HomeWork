package site.luoyu;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * App Tester.
 *
 * @author ZhangYang
 * @version 1.0
 * @since <pre>09/10/2017</pre>
 */
public class AppTest {

    App app;

    @Before
    public void before() throws Exception {
        app = new App();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {
//TODO: Test goes here...
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
