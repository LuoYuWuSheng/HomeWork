package site.luoyu.Dao.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 */
public abstract class IOrder {
    public enum OrderType {add, cancle}

    //预定的日期
    private LocalDate date;
    //开始时间
    private LocalTime start;
    //结束时间
    private LocalTime end;
    //提交订单的时间
    private LocalDateTime submitTime;
    //订单类型，增加还是取消
    private OrderType type;

    public IOrder(LocalDate data, LocalTime start,
                  LocalTime end, LocalDateTime submitTime) {
        this.date = data;
        this.start = start;
        this.end = end;
        this.submitTime = submitTime;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }
}
