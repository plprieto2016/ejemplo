/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaFicherosDeTextoYBinarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author cristina
 */
public class Contacto {

    private String nombre;
    private String apellidos;
    private String email;
    private String direccion;
    private String telefono;
    private static final String ExpresionRegular = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public Contacto(String nombre, String apellidos, String email, String direccion, String telefono) throws IllegalArgumentException {

        this.nombre = comprobarNombre(nombre);
        this.apellidos = comprobarApellidos(apellidos);
        if (comprobarEmail(email) == true) {
            this.email = email;
        }
        this.direccion = comprobarDireccion(direccion);
        this.telefono = comprobarTelefono(telefono);
    }

    public String comprobarNombre(String nombre) throws IllegalArgumentException {// lanza una exeption que tengo que recoger en principal

        for (int i = 0; i < nombre.length(); i++) {

            if (!Character.isLetter(nombre.charAt(i)) || nombre.charAt(i) == '-') {
                throw new IllegalArgumentException("El nombre no debe contener numeros o (-)");
            }
        }

        return nombre;
    }

    public String comprobarApellidos(String apellidos) throws IllegalArgumentException {

        for (int i = 0; i < apellidos.length(); i++) {

            if (!Character.isLetter(apellidos.charAt(i)) || apellidos.charAt(i) == '-') {
                throw new IllegalArgumentException("Los apellidos no deben contener numeros o (-)");
            }
        }

        return apellidos;
    }

    public boolean comprobarEmail(String email) throws IllegalArgumentException {

        Pattern expresion = Pattern.compile(ExpresionRegular);

        Matcher matcher = expresion.matcher(email);// aqui me salta la excepcion
        if (matcher.matches()) {
            return matcher.matches();
        } else {
            throw new NullPointerException("El email no esta escrito correctamente");
        }

    }

    public String comprobarDireccion(String direccion) throws IllegalArgumentException {

        if (direccion.length() == 0) {
            throw new IllegalArgumentException("La direccion no puede estar vacia");
        }
        return direccion;

    }

    public String comprobarTelefono(String telefono) throws IllegalArgumentException {

        if (telefono.length() != 9 || telefono.length() == 0) {
            throw new IllegalArgumentException("El telefono no esta correcto o esta vacio");
        } else {

            for (int i = 0; i == 9; i++) {

                if (Character.isLetter(telefono.charAt(i)) || telefono.charAt(i) == '-') {
                    throw new IllegalArgumentException("El telefono no esta correcto o esta vacio");
                }
            }
        }
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
