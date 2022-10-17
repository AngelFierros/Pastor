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
            statement.execute();

            resultado = statement.getUpdateCount() == 1;

        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return resultado;
    }
    
    public static boolean verificarExistencia(String email) {
        boolean existencia = false;
        try {
            Connection connection = Conexion.obtener();
            String consulta = "SELECT * FROM cliente WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            existencia = resultSet.next();

        } catch (Exception ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
        return existencia;
    }
    
    public static List<String> nombreCliente() {
        
        List<String> listadoCliente = new ArrayList();
        
        try{
            Connection con = Conexion.obtener();
            
            String consulta = "SELECT  nombre FROM cliente WHERE id NOT IN (SELECT idCliente FROM cuenta)";
            PreparedStatement st = con.prepareStatement (consulta);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                String nombre = new String();
                
                nombre = rs.getString("nombre");
                
                listadoCliente.add(nombre);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    return listadoCliente;
    }
    
    public static Cliente obtenerCliente(String busqueda) {

        Cliente cliente = new Cliente();
        try {

            Connection con = Conexion.obtener();
            String consulta = "SELECT * FROM cliente WHERE id=? OR nombre=? OR direccion=? OR telefono=? OR email=?";
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, busqueda);
            st.setString(2, busqueda);
            st.setString(3, busqueda);
            st.setString(4, busqueda);
            st.setString(5, busqueda);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setDireccion(rs.getString(3));
                cliente.setTelefono(rs.getString(4));
                cliente.setEmail(rs.getString(5));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return cliente;
    }
}