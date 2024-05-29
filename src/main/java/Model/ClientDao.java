package Model;

import java.sql.*;
import java.util.ArrayList;
public class ClientDao implements IDao{

    private final String SQL_FIND_ALL = "SELECT * FROM CLIENTE";
    private final String SQL_DELETE = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ";
    private final String SQL_ADD = "INSERT INTO CLIENTE (ID_CLIENTE, USUARIO, CONTRASENA) VALUES (";
    private final String SQL_UPDATE = "UPDATE CLIENTE SET USUARIO = ?, CONTRASENA = ? WHERE ID_CLIENTE = ?";

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
                if (((Client)bean).getContrasena() != null) {
                    sql += " AND CONTRASENA is='" + ((Client)bean).getContrasena() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next())
            {
                Client client = new Client();
                client.setUsuario(rs.getString("USUARIO"));
                client.setContrasena(rs.getString("CONTRASENA"));
                client.setId_cliente(rs.getInt("ID_CLIENTE"));
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
        int iRet = 0;
        try {
            motor.connect();
            String sql = SQL_ADD + bean.getId_cliente() + ",'" + bean.getUsuario() + "','" + bean.getContrasena() + "')" ;

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


    public int update(Client bean) {
        int resp = 0;
        String sql;
        MotorSQL motor = new MotorSQL();
        try {
            motor.conectStatement();
            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {
                PreparedStatement preparedStatement = motor.prepareStatement(SQL_UPDATE);

                preparedStatement.setInt(1, bean.getId_cliente());
                preparedStatement.setString(2, bean.getUsuario());
                preparedStatement.setString(3, bean.getContrasena());

                resp = motor.execute(preparedStatement);
            }

        } catch (Exception e) {

        } finally {
            motor.disconnect();
        }

        if (resp > 0) {
            System.out.println("Cliente actualizado con éxito.");
        } else {
            System.out.println("No se pudo actualizar.");
        }
        return resp;
    }





/*    public int update(Client bean) {
        int resp = 0;
        String sql;
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            if (bean == null) {
                System.out.println("Introduzca datos a modificar");
            } else {

                sql = SQL_UPDATE;


                if (bean.getUsuario() != null) {
                    sql += "USUARIO='" + bean.getUsuario() + "', ";
                }

                if (bean.getContrasena() != null) {
                    sql += "CONTRASENA='" + bean.getContrasena() + "'";
                }

                sql += " WHERE ID_CLIENTE=" + bean.getId_cliente() + ";";
                System.out.println(sql);
                resp = motor.executeUpdate(sql);
            }

        } catch (Exception e) {

        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Producto actualizada con éxito.");
        } else {
            System.out.println("No se pudo actualizar tUS MUERTOS.");
        }
        return resp;
    }*/





}

