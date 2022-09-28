/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pastor.negocio;

import mx.itson.pastor.persistencia.ClienteDAO;

/**
 *
 * @author Angel
 */
public class ClienteNegocio {
    
    public static boolean guardar(String nombre, String direccion, String telefono, String email){
        boolean resultado = false;
        try {
            resultado = ClienteDAO.guardar(nombre, direccion, telefono, email);
        }catch (Exception ex){
          System.err.println(ex.getMessage());
                  }
        
        return resultado;
                  
    }
    
}
