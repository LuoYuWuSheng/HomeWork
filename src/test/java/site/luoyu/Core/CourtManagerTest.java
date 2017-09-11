package site.luoyu.Core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import site.luoyu.Dao.Entity.OrderEntity;
import site.luoyu.TestData;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * CourtManager Tester.
 *
 * @author ZhangYang
 * @version 1.0
 * @since <pre>09/11/2017</pre>
 */

public class CourtManagerTest {
    CourtManager manager;
    PrintStream console;
    ByteArrayOutputStream bytes;
    TestData testData;
    OrderEntity[] testList;

    @Before
    public void before() throws Exception {
        manager = new CourtManager();
        bytes = new ByteArrayOutputStream();
        console = System.out;
        //屏幕打印重定向
        System.setOut(new PrintStream(bytes));
        testData = new TestData();
        testList = testData.getTestData();
    }

    @After
    public void after() throws Exception {
        System.setOut(console);
    }

    /**
     * Method: addOrder(OrderEntity order)
     */
    @Test
    public void testAddOrder() throws Exception {
        int index = 0;
    }

    /**
     * Method: cancleOrder(OrderEntity order)
     */
    @Test
    public void testCancleOrder() throws Exception {
        manager.addOrder(testList[0]);
        manager.cancleOrder(testList[5]);
        String result = bytes.toString();
        String expect = "Success: the booking is accepted!\r\n" +
                "Success: the booking is accepted!\r\n";
        Assert.assertEquals(expect,result);
    }

    /**
     * Method: printMoneyNow()
     */
    @Test
    public void testPrintMoneyNow() throws Exception {
//        System.setOut(console);
        manager.printMoneyNow();
        String expect = "收入汇总\n---\r\n场地:A\r\n小计: 0元\r\n\r\n场地:B\r\n小计: 0元\r\n\r\n" +
                "场地:C\r\n小计: 0元\r\n\r\n场地:D\r\n小计: 0元\r\n---\n总计: 0元\r\n";
        Assert.assertEquals(expect,bytes.toString());
    }

    /**
     * Method: check(OrderEntity order)
     */
    @Test
    public void testCheck() throws Exception {
        Assert.assertFalse(manager.check(testList[6]));
        Assert.assertTrue(manager.check(testList[0]));
    }

    /**
     * Method: setCost(OrderEntity order)
     */
    @Test
    public void testSetCost() throws Exception {
        manager.setCost(testList[0]);
        Assert.assertEquals(testList[0].getCost(),200,0.001);
    }


} 
