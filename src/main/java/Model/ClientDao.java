package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientDao implements IDao{

    private final String SQL_FIND_ALL = "SELECT * FROM CLIENTE";
    private final String SQL_DELETE = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ";
    private final String SQL_ADD = "";

    MotorSQL motor = new MotorSQL();



    @Override
    public ArrayList<Client> findAll(Object bean) {

        ArrayList<Client> clients = new ArrayList<>();

        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Client)bean).getId_cliente() != 0) {
                    sql += " AND ID_CLIENTE ='" + ((Client)bean).getId_cliente() + "'";
                }
                if (((Client)bean).getUsuario() != null) {
                    sql += " AND USER  is='" + ((Client)bean).getUsuario() + "'";
                }
                if (((Client)bean).getCorreo() != null) {
                    sql += " AND CORREO is='" + ((Client)bean).getCorreo() + "'";
                }
                if (((Client)bean).getContrasena() != 0) {
                    sql += " AND CONTRASENA is='" + ((Client)bean).getContrasena() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next())
            {
                Client client = new Client();
                client.setId_cliente(rs.getInt("ID_CLIENTE"));
                client.setUsuario(rs.getString("USUARIO"));
                client.setCorreo(rs.getString("CORREO"));
                client.setContrasena(rs.getInt("CONTRASENA"));
                clients.add(client);
            }

        }catch (Exception ex)
        {
            clients.clear();
        }
        finally {
            motor.disconnect();
        }
        return clients;
    }


    public int delete(int iIdElement) {
        int iRet = 0;
        try {
            motor.connect();
            String sql = SQL_DELETE + iIdElement;

            iRet = motor.executeUpdate(sql);

        }catch (Exception ex)
        {
            iRet = -1;
        }
        finally {
            motor.disconnect();
        }
        return iRet;
    }


    public int add(Client bean) {
        int resp = 0;
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();

            String sql = SQL_ADD + "('"
                    + bean.getId_cliente() + "', '"
                    + bean.getUsuario() + "', '"
                    + bean.getContrasena() + "', "
                    + bean.getCorreo() + "');";

            resp = motor.executeUpdate(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("cliente añadido con exito");
        }
        return resp;
    }



}

