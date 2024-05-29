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
                Client clientUp = new Client();
                clientUp.setId_cliente(Integer.parseInt(request.getParameter("ID_CLIENTE")));
                clientUp.setUsuario(request.getParameter("USUARIO"));
                clientUp.setContrasena(request.getParameter("CONTRASENA"));

                strReturn = update(clientUp);
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

    String update(Client client){
        MotorSQL motorSQL = new MotorSQL();
        ClientDao clientDao = new ClientDao();
        clientDao.update(client);
        return "";
    }








}
