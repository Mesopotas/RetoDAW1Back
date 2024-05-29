package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDao {

    private final String SQL_FIND_ALL = "SELECT * FROM PEDIDO";

    public ArrayList<Order> findAll(Object bean) {
        ArrayList<Order> orders = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Order) bean).getId_pedido() != 0) {
                    sql += " AND ID_PEDIDO='" + ((Order) bean).getId_pedido() + "'";
                }
                if (((Order) bean).getId_cliente() != 0) {
                    sql += " AND ID_CLIENT is='" + ((Order) bean).getId_cliente() + "'";
                }
                if (((Order) bean).getFecha() != null) {
                    sql += " AND FECHA is='" + ((Order) bean).getFecha() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next())
            {
                Order order = new Order();
                order.setId_pedido(rs.getInt("ID_PEDIDO"));
                order.setId_cliente(rs.getInt("ID_CLIENTE"));
                order.setFecha(rs.getDate("FECHA"));
                orders.add(order);
            }

        }catch (Exception ex)
        {
            orders.clear();
        }
        finally {
            motor.disconnect();
        }
        return orders;
    }
}