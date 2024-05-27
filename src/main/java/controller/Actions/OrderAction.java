package controller.Actions;

import Model.Order;
import Model.OrderDao;
import Model.Product;
import Model.ProductDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class OrderAction implements IAction{

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

        OrderDao orderDao = new OrderDao();
        ArrayList<Order> orders = orderDao.findAll(null);
        return Order.toArrayJSon(orders);
    }


}
