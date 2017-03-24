/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que contiene los atributos, los metodos de encapsulacion y los de
 * comprobacion de tipo contacto
 *
 * @author cristina
 */
public class Contacto {

    private String nombre;
    private String apellidos;
    private String email;
    private String direccion;
    private String telefono;
    private int edad;
    private static final String ExpresionRegular = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Constructor de la clase
     *
     * @param nombre
     * @param apellidos
     * @param email
     * @param direccion
     * @param telefono
     * @throws IllegalArgumentException
     */
    public Contacto(String nombre, String apellidos, String email, String direccion, String telefono) throws IllegalArgumentException {

        this.nombre = comprobarNombre(nombre);
        this.apellidos = comprobarApellidos(apellidos);
        if (comprobarEmail(email) == true) {
            this.email = email;
        }
        this.direccion = comprobarDireccion(direccion);
        this.telefono = comprobarTelefono(telefono);
    }

    /**
     * Metodo que comprueba que el nombre sea correcto
     *
     * @param nombre
     * @return nombre
     * @throws IllegalArgumentException
     */
    public String comprobarNombre(String nombre) throws IllegalArgumentException {// lanza una exeption que tengo que recoger en principal

        for (int i = 0; i < nombre.length(); i++) {

            if (!Character.isLetter(nombre.charAt(i)) || nombre.charAt(i) == '-') {
                throw new IllegalArgumentException("El nombre no debe contener numeros o (-)");
            }
        }
        return nombre;
    }

    /**
     * Metodo que comprueba que los Apellidos sean correctos
     *
     * @param apellidos
     * @return apellidos
     * @throws IllegalArgumentException
     */
    public String comprobarApellidos(String apellidos) throws IllegalArgumentException {

        for (int i = 0; i < apellidos.length(); i++) {

            if (!Character.isLetter(apellidos.charAt(i)) || apellidos.charAt(i) == '-') {
                throw new IllegalArgumentException("Los apellidos no deben contener numeros o (-)");
            }
        }
        return apellidos;
    }

    /**
     * Metodo que comprueba que el email este bien formado
     *
     * @param email
     * @return email
     * @throws IllegalArgumentException
     */
    public boolean comprobarEmail(String email) throws IllegalArgumentException {

        Pattern expresion = Pattern.compile(ExpresionRegular);

        Matcher matcher = expresion.matcher(email);// aqui me salta la excepcion
        if (matcher.matches()) {
            return matcher.matches();
        } else {
            throw new NullPointerException("El email no esta escrito correctamente");
        }
    }

    /**
     * Metodo que comprueba que la direccion sea correcta
     *
     * @param direccion
     * @return direccion
     * @throws IllegalArgumentException
     */
    public String comprobarDireccion(String direccion) throws IllegalArgumentException {

        if (direccion.length() == 0) {
            throw new IllegalArgumentException("La direccion no puede estar vacia");
        }
        return direccion;
    }

    /**
     * Metodo que comprueba que el telefono sea correcto
     *
     * @param telefono
     * @return telefono
     * @throws IllegalArgumentException
     */
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

    /**
     * Obtiene el valor de nombre
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el valor de nombre
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el valor de apellidos
     *
     * @return apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Cambia el valor de apellidos
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el valor de email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Cambia el valor de email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el valor de direccion
     *
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Cambia el valor de direccion
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el valor de telefono
     *
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Cambia el valor de telefono
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
