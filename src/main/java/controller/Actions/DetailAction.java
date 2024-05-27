package controller.Actions;

import Model.Client;
import Model.ClientDao;
import Model.Detail;
import Model.DetailDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class DetailAction implements IAction{


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {

        String strReturn ="";
        switch (action){
            case "SQL_FIND_ALL":
                strReturn = findAll();
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {

        DetailDao detailDao = new DetailDao();
        ArrayList<Detail> details = detailDao.findAll(null);
        return Detail.toArrayJSon(details);
    }

}
