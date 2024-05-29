package controller.Actions;

import Model.*;
import Model.Client;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ClientAction implements IAction{


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {

        String strReturn ="";
        switch (action){
            case "SQL_FIND_ALL":
                strReturn = findAll();
                break;
            case "DELETE":
                strReturn = delete(request);
                break;

            case "ADD":
                Client client = new Client();
                client.setId_cliente(Integer.parseInt(request.getParameter("ID_CLIENTE")));
                client.setUsuario(request.getParameter("USUARIO"));
                client.setContrasena(request.getParameter("CONTRASENA"));

                strReturn = add(client);
                break;

            case "UPDATE":
                    strReturn = update(request);
                break;

            default:
                strReturn = "ERROR. Invalid Action aqui cabron";
        }
        return strReturn;
    }

    private String findAll() {

        ClientDao clientDao = new ClientDao();
        ArrayList<Client> clients = clientDao.findAll(null);
        return Client.toArrayJSon(clients);
    }

    private String delete(HttpServletRequest request) {

        ClientDao clientDao = new ClientDao();

        int clientId = Integer.parseInt(request.getParameter("ID_CLIENTE"));

        Integer iRet = clientDao.delete(clientId);
        return iRet.toString();
    }

    String add(Client client){
        MotorSQL motorSQL = new MotorSQL();
        ClientDao clientDao = new ClientDao();
        clientDao.add(client);
        return "";
    }

    private String update(HttpServletRequest request) {
        String id_cliente = request.getParameter("ID_CLIENTE");
        String usuario = request.getParameter("USUARIO");
        String contrasena = request.getParameter("CONTRASENA");



        Client client = new Client();
        client.setId_cliente(Integer.parseInt(id_cliente));
        client.setUsuario(usuario);
        client.setContrasena(contrasena);

        ClientDao clientDao = new ClientDao();
        int Realizado = clientDao.update(client);

        if (Realizado >= 0){
            return "Ha ido mal";

        }
        else {
            return "Ha ido bien";
        }

    }








}
