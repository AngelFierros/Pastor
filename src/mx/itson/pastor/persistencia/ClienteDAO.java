/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pastor.persistencia;

import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import mx.itson.pastor.entidades.Cliente;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


/**
 *
 * @author Angel
 */
public class ClienteDAO {

    public static List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Connection connection = Conexion.obtener();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  id, nombre, direccion, telefono, email FROM cliente");
            while (resultSet.next()) {
                Cliente c = new Cliente();
                c.setId(resultSet.getInt(1));
                c.setNombre(resultSet.getString(2));
                c.setDireccion(resultSet.getString(3));
                c.setTelefono(resultSet.getString(4));
                c.setEmail(resultSet.getString(5));
                clientes.add(c);
            }
        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return clientes;
    }

    public static boolean guardar(String nombre, String direccion, String telefono, String email) {

        boolean resultado = false;
        try {
            Connection connection = Conexion.obtener();
            String consulta = "INSERT INTO cliente (nombre,direccion, telefono, email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, direccion);
            statement.setString(3, telefono);
            statement.setString(4, email);

            resultado = statement.getUpdateCount() == 1;

        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
}
