package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDao implements IDao{

    private final String SQL_FIND_ALL = "SELECT * FROM PRODUCTOS WHERE 1=1 ";


    public ArrayList<Product> findAll(Object bean) {
        ArrayList<Product> products = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Product)bean).getId_Prod() != 0) {
                    sql += " AND ID_PRODUCT='" + ((Product)bean).getId_Prod() + "'";
                }
                if (((Product)bean).getNombre() != null) {
                    sql += " AND NOMBRE is='" + ((Product)bean).getNombre() + "'";
                }
                if (((Product)bean).getDescripcion() != null) {
                    sql += " AND DESCRIPCION is='" + ((Product)bean).getDescripcion() + "'";
                }
                if (((Product)bean).getPrecio() != 0) {
                    sql += " AND PRECIO is='" + ((Product)bean).getPrecio() + "'";
                }
                if (((Product)bean).getImg() != null) {
                    sql += " AND IMG is='" + ((Product)bean).getImg() + "'";
                }
                if (((Product)bean).getId_categoria() != 0) {
                    sql += " AND ID_CATEGORIA ='" + ((Product)bean).getId_categoria() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);


            while (rs.next())
            {
                Product product = new Product();
                product.setId_Prod(rs.getInt("ID_PROD"));
                product.setNombre(rs.getString("NOMBRE"));
                product.setDescripcion(rs.getString("DESCRIPCION"));
                product.setPrecio(rs.getInt("PRECIO"));
                product.setImg(rs.getString("IMG"));
                product.setId_categoria(rs.getInt("ID_CATEGORIA"));
                products.add(product);
            }

        }catch (Exception ex)
        {
            products.clear();
        }
        finally {
            motor.disconnect();
        }
        return products;
    }





}
