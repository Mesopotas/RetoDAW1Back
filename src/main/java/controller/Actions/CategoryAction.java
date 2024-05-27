package controller.Actions;

import Model.Category;
import Model.CategoryDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;

import java.util.ArrayList;


public class CategoryAction implements IAction{

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

        CategoryDao categoryDao = new CategoryDao();
        ArrayList<Category> categories = categoryDao.findAll(null);
        return Category.toArrayJSon(categories);
    }



}
