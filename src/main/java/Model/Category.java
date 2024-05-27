package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;


public class Category {

    public int id_categoria;

    public String nombre;

    public Category(int id_categoria, String nombre) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
    }

    public Category(){

    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id_categoria=" + id_categoria +
                ", nombre='" + nombre + '\'' +
                '}';
    }


    public static String fromArrayToJson(ArrayList<Category> categories){
        String resp = "[";
        for (Category category : categories) {
            resp+= "{" +
                    "'titulo':'" + category.getId_categoria() + "', "
                    + "'trailer':'" + category.getNombre() + "',"
                    +"}";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }



    public static String toArrayJSon(ArrayList<Category> categories) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(categories);
    }




}
