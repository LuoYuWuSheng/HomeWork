package site.luoyu.Core;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * CourtManager Tester.
 *
 * @author ZhangYang
 * @version 1.0
 * @since <pre>09/11/2017</pre>
 */
public class CourtManagerTest {
    CourtManager manager;

    @Before
    public void before() throws Exception {
        manager = new CourtManager();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addOrder(OrderEntity order)
     */
    @Test
    public void testAddOrder() throws Exception {
        String[] testData = {
                //测试订单冲突
                "U002 2018-08-01 19:00~22:00 A",
                //不冲突
                "U002 2018-08-01 09:00~12:00 A",
                //冲突订单
                "U003 2018-08-01 11:00~12:00 A",
                "U003 2018-08-01 11:00~13:00 A",
        };
    }

    /**
     * Method: cancleOrder(OrderEntity order)
     */
    @Test
    public void testCancleOrder() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: printMoneyNow()
     */
    @Test
    public void testPrintMoneyNow() throws Exception {
        //TODO: Test goes here...
    }


} 
