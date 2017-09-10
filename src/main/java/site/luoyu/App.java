package site.luoyu;

import site.luoyu.Core.CourtManager;
import site.luoyu.Dao.Entity.IOrder;
import site.luoyu.Dao.Entity.OrderImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        while (sc.hasNext()) {
            String line = sc.nextLine();
            //空行打印当前收入
            if (line.equals("")) {
                manager.printMoneyNow();
            } else {
                //其他订单则判断输入合法性并插入记录
                IOrder order = null;
                try {
                    order = handleInput(line);
                } catch (InputFormatException e) {
                    System.out.println("Error: the booking is invalid!");
                }
                if (order.getType() == IOrder.OrderType.add) {
                    manager.addOrder(order);
                } else manager.cancleOrder(order);
            }
        }
    }

    public static IOrder handleInput(String input) throws InputFormatException {

        String[] temp = input.split("\\s{1,}");
        if (temp.length < 4 || temp.length > 5) {
            throw new InputFormatException();
        } else {
            String uid = temp[0];
            //UID为空则报错
            if (uid.equals("")) throw new InputFormatException();
            DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter HourFormat = DateTimeFormatter.ofPattern("HH:mm");
            LocalDate date = LocalDate.parse(temp[1], DateFormat);
            //不能按照~分割则输入异常
            String[] StringTime = temp[2].split("~");
            if (StringTime.length != 2) throw new InputFormatException();
            LocalTime start;
            LocalTime end;
            try {
                //利用java8时间类来检测输入格式
                start = LocalTime.parse(StringTime[0], HourFormat);
                end = LocalTime.parse(StringTime[1], HourFormat);
                //检测输入是否是整点，至于是否在合法预定时间内则需要在CourtManage中处理，因为这样方便修改球场开放时间
                //开始时间比结束时间晚，报错。
                if (start.getMinute() != 0 || end.getMinute() != 0 ||
                        start.compareTo(end) >= 0)
                    throw new InputFormatException();
            } catch (DateTimeParseException ex) {
                throw new InputFormatException();
            }
            LocalDateTime submitTime = LocalDateTime.now();
            //判断输入的时间是不是过去时
            if(submitTime.compareTo(LocalDateTime.of(date,start))>0){
                throw new InputFormatException();
            }
            String courtId = temp[3];
            IOrder order = new OrderImpl(uid, date, start, end, submitTime, courtId);
            //判断是下单还是取消，不满足情况则抛出异常
            if (temp.length == 4) {
                order.setType(IOrder.OrderType.add);
            } else if (temp[4].equals("C")) {
                order.setType(IOrder.OrderType.cancle);
            } else {
                throw new InputFormatException();
            }
            return order;
        }
    }
}
