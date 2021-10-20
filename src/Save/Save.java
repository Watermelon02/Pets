package Save;
import Bag.Bag;
import CoffeeShop.CoffeeShop;
import Databases.Connector;
import Layout.Homepage;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Save {
    private static CoffeeShop coffee;
    private static Bag bag;

    public Save(CoffeeShop coffee, Bag bag) throws Exception {
        this.coffee = coffee;
        this.bag = bag;

        Connection connection = new Connector("root", "ai1wei2xi3").getConnection();//获得数据库connection实例

        int total = 0;//总存档数
        ResultSet rs = connection.createStatement().executeQuery("SELECT id,date FROM petscoffee");//输出已有存档编号及存档时间
        System.out.println("有以下存档");
        while (rs.next()) {
            System.out.println("存档：" + rs.getInt(1) + "  存档时间：" + rs.getString(2));
            total++;
        }

        System.out.println("请输入要保存的存档序号");
        int position = new Scanner(System.in).nextInt();

        if (position <= total) {
            System.out.println("确认覆盖该存档？1>yes/2>no");
            if (new Scanner(System.in).nextInt() == 1) {
                saveAll(connection);
                System.out.println("进度保存中ing");
                System.out.println();
            } else {
                System.out.println("正在返回主页");
            }
        } else {
            System.out.println("创建新存档ing");
            saveAll(connection);
            System.out.println("进度已保存");
        }
        new Homepage(coffee, bag).show();
    }

    public void saveAll(Connection connection) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO petscoffee(day,time,money,pets,bag,date) VALUE(?,?,?,?,?,?)");
        pstmt.setInt(1, coffee.getDay());//保存day
        pstmt.setInt(2, coffee.getTime());//保存time
        pstmt.setFloat(3, coffee.getMoney());//保存money
        //以下保存pets[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject((Object[]) coffee.getPets().toArray());
        byte[] data = baos.toByteArray();
        pstmt.setBytes(4, data);
        //以下保存goods[]
        baos = new ByteArrayOutputStream();
        oos = new ObjectOutputStream(baos);
        oos.writeObject((Object[]) bag.getBag().toArray());
        data = baos.toByteArray();
        pstmt.setBytes(5, data);
        //以下保存存档时间
        String pattern = "yyyy年MM月dd日HH时mm分ss秒";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String str = sdf.format(new Date());//时间字符串
        pstmt.setString(6,str);
        pstmt.executeUpdate();
        oos.close();
        baos.close();
        pstmt.close();
    }
}
