package site.luoyu.Dao;

import site.luoyu.Dao.Entity.IOrder;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * 数据访问接口，目前只有在Memory里实现了数据访问功能
 */
public interface DBAccess {
    //如果没有冲突就添加;
    public boolean addIfNotExist(IOrder order);
    //取消订单
    public boolean cancle(IOrder order);
    //打印当前收入
    public void print();
}
