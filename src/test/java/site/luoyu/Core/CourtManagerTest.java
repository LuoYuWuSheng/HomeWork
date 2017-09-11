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
        //TODO: Test goes here...
    }

    /**
     * Method: printMoneyNow()
     */
    @Test
    public void testPrintMoneyNow() throws Exception {
//        System.setOut(console);
        manager.printMoneyNow();
        Assert.assertEquals("收入汇总\n" +
                "---\n" +
                "场地:A\n" +
                "小计: 0元\n" +
                "\n" +
                "场地:B\n" +
                "小计: 0元\n" +
                "\n" +
                "场地:C\n" +
                "小计: 0元\n" +
                "\n" +
                "场地:D\n" +
                "小计: 0元\n" +
                "---\n" +
                "总计: 0元\n",bytes.toString());
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
