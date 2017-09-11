package site.luoyu.Core;

import site.luoyu.Dao.DBAccess;
import site.luoyu.Dao.Entity.OrderEntity;
import site.luoyu.Dao.MemoryDB;
import site.luoyu.Exception.CourtNotExistException;
import site.luoyu.Exception.TimeConfictException;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * 负责校验用户订单的时间是否是球场的开放时间，
 * 以及计算费用计算。
 */
public class CourtManager {


    DBAccess db = new MemoryDB();

    public CourtManager() {
        char[] courtIds = {'A', 'B', 'C', 'D'};
        db.initDB(courtIds);
    }

    public void addOrder(OrderEntity order) {
        try {
            boolean res = db.addIfNotExist(order);
        } catch (CourtNotExistException e) {
            System.out.println("Error: the booking is invalid!");
        } catch (TimeConfictException e) {
            System.out.println("Error: the booking conflicts with existing bookings!");
        }
        System.out.println("Success: the booking is accepted!");
    }

    public void cancleOrder(OrderEntity order) {
        db.cancle(order);
    }

    public void printMoneyNow() {

    }
    //计算花费，单独函数。如果将来收费策略改变方便修改。
    public void calcCost(OrderEntity order){
        //订单收费
        if(order.getType()==OrderEntity.OrderType.add){

        }else {
            //取消订单产生的罚金

        }
    }
}
