package site.luoyu.Dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import site.luoyu.Dao.Entity.OrderEntity;
import site.luoyu.Exception.CourtNotExistException;
import site.luoyu.TestData;

/**
 * MemoryDB Tester.
 *
 * @author ZhangYang
 * @version 1.0
 * @since <pre>09/12/2017</pre>
 */
public class MemoryDBTest {
    TestData testData;
    MemoryDB db;
    @Before
    public void before() throws Exception {
        testData = new TestData();
        db = new MemoryDB();
        String[] court={"A","B","C","D"};
        db.initDB(court);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addIfNotExist(OrderEntity newOrder)
     */
    @Test
    public void testAddIfNotExist() throws CourtNotExistException {
        OrderEntity[] list = testData.getTestData();
        Assert.assertTrue(db.addIfNotExist(list[0]));
        Assert.assertTrue(db.addIfNotExist(list[1]));
        //第二个数据应该产生冲突
        Assert.assertFalse(db.addIfNotExist(list[2]));
        Assert.assertFalse(db.addIfNotExist(list[3]));
    }

    /**
     * Method: initDB(String[] courtIds)
     */
    @Test
    public void testInitDB() throws Exception {
        String[] court={"A","B","C","D"};
        db.initDB(court);
        Assert.assertNotNull(db.getMap());
        Assert.assertEquals(db.getMap().size(),4);
    }

    /**
     * Method: cancle(OrderEntity newOrder)
     */
    @Test
    public void testCancle() throws Exception {
        OrderEntity[] list = testData.getTestData();
        db.addIfNotExist(list[0]);
        Assert.assertFalse(db.cancle(list[4]));
        Assert.assertTrue(db.cancle(list[5]));
    }

    /**
     * Method: print(String[] CourtIds)
     */
    @Test
    public void testPrint() throws Exception {
        String[] court={"A","B","C","D"};
        db.print(court);
    }


} 
