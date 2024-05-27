package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import oracle.ons.Cli;

import java.util.ArrayList;

public class Client {

    public int Id_cliente;
    public String Usuario;
    public int Contrasena;
    public String Correo;


    public Client(int id_cliente, String usuario, int contrasena, String correo) {
        Id_cliente = id_cliente;
        Usuario = usuario;
        Contrasena = Contrasena;
        Correo = correo;
    }

    public Client(){

    }


    public int getId_cliente() {
        return Id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        Id_cliente = id_cliente;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public int getContrasena() {
        return Contrasena;
    }

    public void setContrasena(int contrasena) {
        Contrasena = contrasena;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }


    @Override
    public String toString() {
        return "Client{" +
                "Id_cliente=" + Id_cliente +
                ", Usuario='" + Usuario + '\'' +
                ", Contraseña=" + Contrasena +
                ", Correo='" + Correo + '\'' +
                '}';
    }

    public static String fromArrayToJson(ArrayList<Client> clients){
        String resp = "[";
        for (Client client : clients) {
            resp+= "{" +
                    "'Id_Cliente':'" + client.getId_cliente() + "', "
                    + "'Usuario':'" + client.getUsuario() + "',"
                    + "'Correo':'" + client.getCorreo() + "',"
                    + "'Contrasena':'" + client.getContrasena() + "',"
                    +"}";
            resp+=",";
        }
        resp = resp.substring(0, resp.length()-1);
        resp+="]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Client> clients) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(clients);
    }



}
