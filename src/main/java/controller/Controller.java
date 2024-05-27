package controller;

import Model.Client;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import controller.Actions.*;
import oracle.ons.Cli;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;



@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.getMethod()
        //request.getQueryString()
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");

        PrintWriter out = response.getWriter();
        String strAction = request.getParameter("ACTION");

        //ACTION=PRODUCTOS.FIND_ALL --> HAMBURGUER.FIND_ALL // USER.FIND
        String[] arrayAction = new String[2];
        ;
        if (strAction != "") {
            arrayAction = strAction.split("\\."); //[0] PRODUCTO <-> [1] FIND_ALL
        }
        switch (arrayAction[0].toUpperCase()) {
            case "CATEGORY": {
                out.print(new CategoryAction().execute(request, response, arrayAction[1]));
                break;
            }
            case "PRODUCT": {
                out.print(new ProductAction().execute(request, response, arrayAction[1]));
                break;
            }
            case "CLIENT": {
                out.print(new ClientAction().execute(request, response, arrayAction[1]));
                break;
            }
            case "ORDER": {
                out.print(new OrderAction().execute(request, response, arrayAction[1]));
                break;
            }
            case "DETAIL": {
                out.print(new DetailAction().execute(request, response, arrayAction[1]));
                break;
            }

            default:
                System.out.println(arrayAction[0]);
                throw new ServletException("Acción " + arrayAction[0] + " no valida");
        }
        System.out.println(strAction);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        Client clientN = gson.fromJson(parser.parse(getBody(request)), Client.class);

        System.out.println(gson.toJson(clientN));

        response.getWriter().print("hola " + clientN.Usuario + "\r\n");
        //processRequest(request, response);
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
    public static String getBody(HttpServletRequest request) {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            // throw ex;
            return "";
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {

                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }



}

