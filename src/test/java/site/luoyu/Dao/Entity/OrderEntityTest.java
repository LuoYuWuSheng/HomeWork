package site.luoyu.Dao.Entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import site.luoyu.TestData;

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
    @Before
    public void before() throws Exception {
        testData = new TestData();
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
        for (int i = 0; i < testData.getTestData().length; i++) {
            OrderEntity order = testData.getTestData()[i];
            System.out.println(order.printOrderMoney());
        }
    }

    @Test
    public void testGetType() throws Exception {
        entity = testData.getTestData()[0];
        Assert.assertEquals(entity.getType(),OrderEntity.OrderType.add);
    }

} 
