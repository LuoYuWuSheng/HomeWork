package site.luoyu;

import site.luoyu.Core.CourtManager;
import site.luoyu.Dao.Entity.OrderEntity;
import site.luoyu.Exception.InputFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * 应用入口
 */
public class App {
    //管理类
    private static CourtManager manager = new CourtManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            //空行打印当前收入
            if (line.equals("")) {
                manager.printMoneyNow();
            } else {
                //其他订单则判断输入合法性并插入记录
                OrderEntity order = null;
                try {
                    order = handleInput(line);
                    if (order.getType() == OrderEntity.OrderType.add) {
                        manager.addOrder(order);
                    } else manager.cancleOrder(order);
                } catch (InputFormatException e) {
                    System.out.println("Error: the booking is invalid!");
                }
            }
        }
    }

    public static OrderEntity handleInput(String input) throws InputFormatException {

        String[] temp = input.split("\\s{1,}");
        if (temp.length < 4 || temp.length > 5) {
            throw new InputFormatException();
        } else {
            String uid = temp[0];
            //UID为空则报错
            if (uid.equals("")) throw new InputFormatException();

            LocalDate date = LocalDate.parse(temp[1], OrderEntity.DateFormat);
            //不能按照~分割则输入异常
            String[] StringTime = temp[2].split("~");
            if (StringTime.length != 2) throw new InputFormatException();
            LocalTime start;
            LocalTime end;
            try {
                //利用java8时间类来检测输入格式
                start = LocalTime.parse(StringTime[0], OrderEntity.HourFormat);
                end = LocalTime.parse(StringTime[1], OrderEntity.HourFormat);
                //检测输入是否是整点，至于是否在合法预定时间内则需要在CourtManage中处理，因为这样方便修改球场开放时间
                //开始时间比结束时间晚，报错。
                if (start.getMinute() != 0 || end.getMinute() != 0 ||
                        start.compareTo(end) >= 0)
                    throw new InputFormatException();
            } catch (DateTimeParseException ex) {
                throw new InputFormatException();
            }
            LocalDateTime submitTime = LocalDateTime.now();
            //判断输入的时间是不是过去时。还有比如五点半的时候用户预订五点到六点的场次本系统结果为订单不成功
            //如果想改变这种情况只需要将结束时间与当前时间对比即可，这个在作业描述中没有明确说明处理方式，我觉得拒绝更合理。所以实现的是拒绝
            if(submitTime.compareTo(LocalDateTime.of(date,start))>0){
                throw new InputFormatException();
            }
            String courtId = temp[3];
            OrderEntity order = new OrderEntity(uid, date, start, end, submitTime, courtId);
            //判断是下单还是取消，不满足情况则抛出异常
            if (temp.length == 4) {
                order.setType(OrderEntity.OrderType.add);
            } else if (temp[4].equals("C")) {
                order.setType(OrderEntity.OrderType.cancle);
            } else {
                throw new InputFormatException();
            }
            return order;
        }
    }
}
