package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DetailDao implements IDao {

    private final String SQL_FIND_ALL = "SELECT * FROM DETALLE";


    @Override
    public ArrayList<Detail> findAll(Object bean) {

        ArrayList<Detail> details = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Detail)bean).getId_detalle() != 0) {
                    sql += " AND Id_detalle ='" + ((Detail)bean).getId_detalle() + "'";
                }
                if (((Detail)bean).getId_producto() != 0) {
                    sql += " AND Id_producto  is='" + ((Detail)bean).getId_producto() + "'";
                }
                if (((Detail)bean).getId_pedido() != 0) {
                    sql += " AND Id_pedido is='" + ((Detail)bean).getId_pedido() + "'";
                }
                if (((Detail)bean).getCantidad() != 0) {
                    sql += " AND Cantidad is='" + ((Detail)bean).getCantidad() + "'";
                }
                if (((Detail)bean).getPrecio() != 0) {
                    sql += " AND Precio is='" + ((Detail)bean).getPrecio() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next())
            {
                Detail detail = new Detail();
                detail.setId_detalle(rs.getInt("ID_DETALLE"));
                detail.setId_producto(rs.getInt("ID_PROD"));
                detail.setId_pedido(rs.getInt("ID_PEDIDO"));
                detail.setCantidad(rs.getInt("CANTIDAD"));
                detail.setPrecio(rs.getInt("PRECIO"));
                details.add(detail);
            }

        }catch (Exception ex)
        {
            details.clear();
        }
        finally {
            motor.disconnect();
        }
        return details;
    }



}
