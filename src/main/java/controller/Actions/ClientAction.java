package controller.Actions;

import Model.Client;
import Model.ClientDao;
import Model.Client;
import Model.ProductDao;
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

            default:
                strReturn = "ERROR. Invalid Action";
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





}
