package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDao implements IDao {

    private final String SQL_FIND_ALL = "SELECT * FROM EMPLEADOS";

    public ArrayList<Employee> findAll(Object bean) {
        ArrayList<Employee> employees = new ArrayList<>();
        MotorSQL motor = new MotorSQL();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (((Employee)bean).getId_Empleado() != 0) {
                    sql += " AND Id_Empleado='" + ((Employee)bean).getId_Empleado() + "'";
                }
                if (((Employee)bean).getNombre() != null) {
                    sql += " AND Nombre is='" + ((Employee)bean).getNombre() + "'";
                }
                if (((Employee)bean).getContrasena() != null) {
                    sql += " AND Contrasena is='" + ((Employee)bean).getContrasena() + "'";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next())
            {
                Employee employee = new Employee();
                employee.setId_Empleado(rs.getInt("ID_EMPLEADO"));
                employee.setNombre(rs.getString("Nombre"));
                employee.setContrasena(rs.getString("CONTRASENA"));
                employees.add(employee);
            }

        }catch (Exception ex)
        {
            employees.clear();
        }
        finally {
            motor.disconnect();
        }
        return employees;
    }


}
