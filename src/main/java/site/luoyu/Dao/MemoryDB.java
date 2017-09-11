package site.luoyu.Dao;

import site.luoyu.Dao.Entity.OrderEntity;
import site.luoyu.Exception.CourtNotExistException;
import site.luoyu.Exception.TimeConfictException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 * Computer user xd
 * Created by 张洋 on 2017/9/10.
 * DB的实现，
 * 负责管理订单，主要包括时间是否冲突。场地产生的收入等。
 * 计费，罚金等则由{@CourtManager}
 */
public class MemoryDB  implements DBAccess{
    //球场管理的存储类,每个球场对应一个Map，该球场订单通过Map来管理。这里的Map都用treeMap实现类，加快查找速度。
    //每天的订单都不多，增加，删除的操作也不会很频繁。所以用ArrayList就行。
    private Map<String,Map<LocalDate,ArrayList<OrderEntity>>> map = new TreeMap<>();

    public Map<String, Map<LocalDate, ArrayList<OrderEntity>>> getMap() {
        return map;
    }

    //是否添加成功，成功返回true，有冲突则false
    public boolean addIfNotExist(OrderEntity newOrder) throws CourtNotExistException {
        Map<LocalDate,ArrayList<OrderEntity>> court = map.get(newOrder.getCourtId());
        if(court==null)throw new CourtNotExistException();
        ArrayList<OrderEntity> orderList = court.get(newOrder.getDate());
        //该日期下没有订单则初始化
        if(orderList==null){
            orderList = new ArrayList<>();
            orderList.add(newOrder);
            court.put(newOrder.getDate(),orderList);
        }else {
            //该日期下有订单，遍历（由于每天订单量不会很大），看新增的订单是否合法。
            boolean confilct = false;
            for (OrderEntity oldOrder:orderList) {
                //发现是取消订单则跳过
                if(oldOrder.getType()==OrderEntity.OrderType.cancle)continue;
                if(oldOrder.getStart().compareTo(newOrder.getEnd())>=0||
                        oldOrder.getEnd().compareTo(newOrder.getStart())<=0){
                    confilct=false;
                }else {
                    confilct=true;
                    break;
                }
            }
            //有冲突抛出异常
//            if(confilct)throw new TimeConfictException();
            if(confilct)return false;
            else {
                //没冲突加入订单。
                orderList.add(newOrder);
            }
        }
        return true;
    }

    public void initDB(String[] courtIds){
        for (int i = 0; i < courtIds.length; i++) {
            //这里使用TreeMap加快日期查找速度。
            map.put(courtIds[i],new TreeMap<>());
        }
    }

    public boolean cancle(OrderEntity newOrder) {
        Map<LocalDate,ArrayList<OrderEntity>> court = map.get(newOrder.getCourtId());
        ArrayList<OrderEntity> orderList = court.get(newOrder.getDate());
        for (OrderEntity order:orderList) {
            if(order.canCancle(newOrder)){
                LocalDate date = order.getDate();
                double cost = order.getCost();
                if(date.getDayOfWeek()== DayOfWeek.SATURDAY||date.getDayOfWeek()==DayOfWeek.SUNDAY){
                    cost*=0.5;
                }else {
                    cost*=0.25;
                }
                order.setCost(cost);
                order.setType(OrderEntity.OrderType.cancle);
                return true;
            }
        }
        return false;
    }

    public void print(String[] CourtIds) {
        System.out.println("收入汇总\n---");
        int all = 0;
        for (int i = 0; i < CourtIds.length; i++) {
            String id = CourtIds[i];
            System.out.println("场地:"+id);
            Map<LocalDate,ArrayList<OrderEntity>> court = map.get(id);
            int oneCort = 0;
            for (Map.Entry<LocalDate,ArrayList<OrderEntity>> entry :court.entrySet()) {
                ArrayList<OrderEntity> orderList = entry.getValue();
                //lambuda表达式，对订单进行排序
                orderList.sort(Comparator.comparing(OrderEntity::getStart));
                for (OrderEntity order : orderList) {
                    oneCort+=order.getCost();
                    System.out.println(order.printOrderMoney());
                }
            }
            System.out.println("小计: "+oneCort+"元");
            if(i!=CourtIds.length-1) System.out.println();
            all+=oneCort;
        }
        System.out.println("---\n总计: "+all+"元");
    }
}
