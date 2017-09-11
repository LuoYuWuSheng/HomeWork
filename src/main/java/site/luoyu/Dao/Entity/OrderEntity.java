package site.luoyu.Dao.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * 抽象的订单只包含日期，提交日期，费用等基本信息。
 */
public abstract class OrderEntity
{
    public enum OrderType {add, cancle}

    //用户Id
    private String UID;
    //场地ID
    private String CourtId;
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
    //费用
    private double cost;

    public OrderEntity(String uid, LocalDate data, LocalTime start,
                       LocalTime end, LocalDateTime submitTime, String courtId) {
        this.UID = uid;
        this.CourtId = courtId;
        this.date = data;
        this.start = start;
        this.end = end;
        this.submitTime = submitTime;
    }

    //以下都是getter与setter方法
    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getCourtId() {
        return CourtId;
    }

    public void setCourtId(String courtId) {
        CourtId = courtId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }
}
