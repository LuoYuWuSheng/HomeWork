package site.luoyu.Core;

import site.luoyu.Dao.DBAccess;
import site.luoyu.Dao.Entity.IOrder;
import site.luoyu.Dao.MemoryDB;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * 负责校验用户订单的时间是否是球场的开放时间，
 * 以及计算费用计算。
 */
public class CourtManager {

    DBAccess db = new MemoryDB();

    public void addOrder(IOrder order) {
        boolean res = db.addIfNotExist(order);
        if (res) {
            System.out.println("Error: the booking is invalid!");
        } else {
            System.out.println("Success: the booking is accepted!");
        }
    }

    public void cancleOrder(IOrder order) {
        db.cancle(order);
    }

    public void printMoneyNow() {

    }
}
