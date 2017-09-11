package site.luoyu.Core;

import site.luoyu.Dao.DBAccess;
import site.luoyu.Dao.Entity.OrderEntity;
import site.luoyu.Dao.MemoryDB;
import site.luoyu.Exception.CourtNotExistException;
import site.luoyu.Exception.TimeConfictException;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * 负责校验用户订单的时间是否是球场的开放时间，
 * 以及计算费用计算。
 */
public class CourtManager {


    DBAccess db = new MemoryDB();

    String[] courtIds = {"A", "B", "C", "D"};

    public CourtManager() {
        db.initDB(courtIds);
    }

    public void addOrder(OrderEntity order) {
        if (check(order)) {
            setCost(order);
            boolean res = false;
            try {
                res = db.addIfNotExist(order);
            } catch (CourtNotExistException e) {
                System.out.println("Error: the booking is invalid!");
            }
            if(!res){
                System.out.println("Error: the booking conflicts with existing bookings!");
            }else {
                System.out.println("Success: the booking is accepted!");
            }
        } else {
            System.out.println("Error: the booking is invalid!");
        }
    }

    public void cancleOrder(OrderEntity order) {
        if (check(order)) {
            boolean result = db.cancle(order);
            if (result) {
                System.out.println("Success: the booking is accepted!");
            } else System.out.println("Error: the booking being cancelled does not exist!");
        } else {
            System.out.println("Error: the booking is invalid!");
        }
    }

    //todo 最后一个，打印场地费
    public void printMoneyNow() {
        db.print(courtIds);
    }

    //检验是否是在球场工作时间内
    public boolean check(OrderEntity order) {
        if (order.getStart().compareTo(LocalTime.parse("09:00")) < 0 ||
                order.getEnd().compareTo(LocalTime.parse("22:00")) > 0) {
            return false;
        } else return true;
    }

    //计算花费，单独函数。如果将来收费策略改变方便修改。
    public void setCost(OrderEntity order) {
        //订单收费
        if (order.getType() == OrderEntity.OrderType.add) {
            DayOfWeek day = order.getDate().getDayOfWeek();
            int cost = 0;
            int start = order.getStart().getHour();
            int end = order.getEnd().getHour();
            //星期六，星期天
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                for (int i = start; i < end; i++) {
                    if (9 <= i && i < 12) cost += 40;
                    else if (12 <= i && i < 18) cost += 50;
                    else if (18 <= i && i < 22) cost += 60;
                }
            } else {
                for (int i = start; i < end; i++) {
                    if (9 <= i && i < 12) cost += 30;
                    else if (12 <= i && i < 18) cost += 50;
                    else if (18 <= i && i < 20) cost += 80;
                    else if (20 <= i && i < 22) cost += 60;
                }
            }
            order.setCost(cost);
        }
    }
}
