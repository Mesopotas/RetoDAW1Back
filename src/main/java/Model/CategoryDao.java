package Model;

import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.util.ArrayList;


public class CategoryDao implements IDao {

    private final String SQL_FIND_ALL = "SELECT * FROM CATEGORIA";

    public ArrayList<Category> findAll(Object bean) {
        ArrayList<Category> categories = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Category)bean).getId_categoria() != 0) {
                    sql += " AND ID_CATEGORIA='" + ((Category)bean).getId_categoria() + "'";
                }
                if (((Category)bean).getNombre() != null) {
                    sql += " AND CATEGORY is='" + ((Category)bean).getNombre() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next())
            {
                Category category = new Category();
                category.setId_categoria(rs.getInt("Id_Categoria"));
                category.setNombre(rs.getString("Nombre"));
                categories.add(category);
            }

        }catch (Exception ex)
        {
            categories.clear();
        }
        finally {
            motor.disconnect();
        }
        return categories;
    }



}
