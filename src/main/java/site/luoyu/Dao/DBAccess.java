package site.luoyu.Dao;

import site.luoyu.Dao.Entity.OrderEntity;
import site.luoyu.Exception.CourtNotExistException;
import site.luoyu.Exception.TimeConfictException;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * 数据访问接口，目前只有在Memory里实现了数据访问功能
 */
public interface DBAccess {
    //如果没有冲突就添加;
    public boolean addIfNotExist(OrderEntity order) throws CourtNotExistException;
    //取消订单
    public boolean cancle(OrderEntity order);
    //打印当前收入
    public void print(String[] courtIds);
    //初始化数据库
    public void initDB(String[] courtIds);
}
