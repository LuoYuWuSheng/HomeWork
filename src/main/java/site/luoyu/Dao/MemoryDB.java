package site.luoyu.Dao;

import site.luoyu.Dao.Entity.IOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * DB的实现，
 */
public class MemoryDB  implements DBAccess{
    //球场管理的存储类,每个球场对应一个Map，该球场订单通过Map来管理。这里的Map都用treeMap实现类，加快查找速度。
    //每天的订单都不多，增加，删除的操作也不会很频繁。所以用ArrayList就行。
    Map<Character,Map<LocalDate,ArrayList<IOrder>>> map = new TreeMap<>();

    public boolean addIfNotExist(IOrder order) {
        return false;
    }

    public boolean cancle(IOrder order) {
        return false;
    }

    public void print() {
    }
}
