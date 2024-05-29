package controller.Actions;

import Model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class EmployeeAction implements IAction {

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

        EmployeeDao employeeDao = new EmployeeDao();
        ArrayList<Employee> employees = employeeDao.findAll(null);
        return Employee.toArrayJSon(employees);
    }


}
