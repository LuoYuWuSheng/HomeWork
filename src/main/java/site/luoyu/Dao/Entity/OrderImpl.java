package site.luoyu.Dao.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * 订单记录。
 */
public class OrderImpl extends IOrder {
    //用户Id
    private String UID;
    //场地ID
    private String CourtId;

    public OrderImpl(String uid, LocalDate date, LocalTime start,
                     LocalTime end, LocalDateTime submitTime, String courtId) {
        super(date, start, end, submitTime);
        this.UID = uid;
        this.CourtId = courtId;
    }
}
