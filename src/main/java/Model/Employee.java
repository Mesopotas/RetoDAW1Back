package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Employee {

    public int Id_Empleado;
    public String Nombre, Contrasena;

    public Employee(int id_Empleado, String nombre, String contrasena) {
        Id_Empleado = id_Empleado;
        Nombre = nombre;
        Contrasena = contrasena;
    }

    public Employee() {

    }

    public int getId_Empleado() {
        return Id_Empleado;
    }

    public void setId_Empleado(int id_Empleado) {
        Id_Empleado = id_Empleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "Id_Prod=" + Id_Empleado +
                ", Nombre='" + Nombre +
                ", Descripcion='" + Contrasena +
                '}';
    }


    public static String fromArrayToJson(ArrayList<Employee> employees){
        String resp = "[";
        for (Employee employee : employees) {
            resp+= "{" +
                    "'Id_Prod':'" + employee.getId_Empleado() + "', "
                    + "'Nombre':'" + employee.getNombre() + "',"
                    + "'Descripcion':'" + employee.getContrasena() + "',"
                    +"}";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Employee> employees) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(employees);
    }


}
