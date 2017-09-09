package site.luoyu.Dao;

import java.util.Date;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * 订单记录。
 */
public class MemOrderImpl implements IOrder {
    private Date start;
    private Date end;

    public MemOrderImpl(Date start, Date end) {
        this.start = start;
        this.end = end;
    }
}
