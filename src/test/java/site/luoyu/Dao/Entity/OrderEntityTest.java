package site.luoyu.Dao.Entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import site.luoyu.TestData;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * OrderEntity Tester.
 *
 * @author ZhangYang
 * @version 1.0
 * @since <pre>09/12/2017</pre>
 */
public class OrderEntityTest {
    TestData testData;
    OrderEntity entity;
    PrintStream console;
    ByteArrayOutputStream bytes;
    @Before
    public void before() throws Exception {
        testData = new TestData();
        bytes = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(bytes));
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: canCancle(OrderEntity other)
     */
    @Test
    public void testCanCancle() throws Exception {
        OrderEntity[] list = testData.getTestData();
        entity = list[0];
        Assert.assertTrue(entity.canCancle(list[5]));
        Assert.assertFalse(entity.canCancle(list[4]));
    }

    /**
     * Method: printOrderMoney()
     */
    @Test
    public void testPrintOrderMoney() throws Exception {
        System.setOut(console);
        String expect = "2018-08-01 19:00~22:00 0元";
        OrderEntity order = testData.getTestData()[0];
        Assert.assertEquals(expect,order.printOrderMoney());
    }

    @Test
    public void testGetType() throws Exception {
        entity = testData.getTestData()[0];
        Assert.assertEquals(entity.getType(),OrderEntity.OrderType.add);
    }

} 
