package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    public int Id_pedido;
    public int Id_cliente;
    public Date Fecha;

    public Order(int id_pedido, int id_cliente, Date fecha) {
        Id_pedido = id_pedido;
        Id_cliente = id_cliente;
        Fecha = fecha;
    }

    public Order(){

    }

    public int getId_pedido() {
        return Id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        Id_pedido = id_pedido;
    }

    public int getId_cliente() {
        return Id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        Id_cliente = id_cliente;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Id_pedido=" + Id_pedido +
                ", Id_cliente=" + Id_cliente +
                ", Fecha=" + Fecha +
                '}';
    }

    public static String fromArrayToJson(ArrayList<Order> orders){
        String resp = "[";
        for (Order order : orders) {
            resp+= "{" +
                    "'Id_pedido':'" + order.getId_pedido() + "', "
                    + "'Id_Cliente':'" + order.getId_cliente() + "',"
                    + "'fecha':'" + order.getFecha() + "',"
                    +"}";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Order> orders) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(orders);
    }


}
