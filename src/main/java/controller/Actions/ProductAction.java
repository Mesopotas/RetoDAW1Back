package controller.Actions;

import Model.Category;
import Model.CategoryDao;
import Model.Product;
import Model.ProductDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ProductAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {

        String strReturn ="";

        String strFiltro = request.getParameter("CATEGORY_ID");

        switch (action){
            case "SQL_FIND_ALL":
                strReturn = findAll(strFiltro);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll(String strFiltro) {

        ProductDao productDao = new ProductDao();
        Product p = new Product();

        if (strFiltro != null)
        {
            p.setId_categoria(Integer.parseInt(strFiltro));
        }

        ArrayList<Product> products = productDao.findAll(p);
        return Product.toArrayJSon(products);
    }

}
