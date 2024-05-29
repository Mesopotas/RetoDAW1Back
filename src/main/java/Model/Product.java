package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Product {

    public int Id_Prod;
    public String Nombre;
    public String Descripcion;
    public double Precio;
    public String Img;
    public int id_categoria;

    public Product(int id_Prod, String nombre, String descripcion, double precio, String img, int id_categoria) {
        Id_Prod = id_Prod;
        Nombre = nombre;
        Descripcion = descripcion;
        Precio = precio;
        Img = img;
        this.id_categoria = id_categoria;
    }

    public Product(){

    }

    public int getId_Prod() {
        return Id_Prod;
    }

    public void setId_Prod(int id_Prod) {
        Id_Prod = id_Prod;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "Id_Prod=" + Id_Prod +
                ", Nombre='" + Nombre + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", Precio=" + Precio +
                ", Img='" + Img + '\'' +
                ", id_categoria=" + id_categoria +
                '}';
    }

    public static String fromArrayToJson(ArrayList<Product> products){
        String resp = "[";
        for (Product product : products) {
            resp+= "{" +
                    "'Id_Prod':'" + product.getId_Prod() + "', "
                    + "'Nombre':'" + product.getNombre() + "',"
                    + "'Descripcion':'" + product.getDescripcion() + "',"
                    + "'Precio':'" + product.getPrecio() + "',"
                    + "'Img':'" + product.getImg() + "',"
                    + "'Id_categoria':'" + product.getId_categoria() + "',"
                    +"}";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Product> products) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(products);
    }





}
