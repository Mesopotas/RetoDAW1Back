package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Detail {

    public int Id_detalle;
    public int Id_producto;
    public int Id_pedido;
    public int Cantidad;
    public double Precio;

    public Detail(int id_detalle, int id_producto, int id_pedido, int cantidad, int precio) {
        Id_detalle = id_detalle;
        Id_producto = id_producto;
        Id_pedido = id_pedido;
        Cantidad = cantidad;
        Precio = precio;
    }

    public Detail(){

    }

    public int getId_detalle() {
        return Id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        Id_detalle = id_detalle;
    }

    public int getId_producto() {
        return Id_producto;
    }

    public void setId_producto(int id_producto) {
        Id_producto = id_producto;
    }

    public int getId_pedido() {
        return Id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        Id_pedido = id_pedido;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "Id_detalle=" + Id_detalle +
                ", Id_producto=" + Id_producto +
                ", Id_pedido=" + Id_pedido +
                ", Cantidad=" + Cantidad +
                ", Precio=" + Precio +
                '}';
    }

    public static String fromArrayToJson(ArrayList<Detail> details){
        String resp = "[";
        for (Detail detail : details) {
            resp+= "{" +
                    "'Id_detalle':'" + detail.getId_detalle() + "', "
                    + "'Id_producto':'" + detail.getId_producto() + "',"
                    + "'Id_pedido':'" + detail.getId_pedido() + "',"
                    + "'getCantidad':'" + detail.getCantidad() + "',"
                    + "'getPrecio':'" + detail.getPrecio() + "',"
                    +"}";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Detail> details) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(details);
    }


}
