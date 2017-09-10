package site.luoyu.Dao;

import site.luoyu.Dao.Entity.IOrder;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * DB的实现，
 */
public class MemoryDB  implements DBAccess{

    public boolean addIfNotExist(IOrder order) {
        return false;
    }

    public boolean cancle(IOrder order) {
        return false;
    }
    public MemoryDB() {

    }
    public void print() {

    }
}
