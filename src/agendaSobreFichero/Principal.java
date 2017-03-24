/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaSobreFichero;

import agenda.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal que contiene el menu 
 * @author cristina
 */
public class Principal {

    public static void main(String[] args) throws Exception {
        ArrayList<Contacto> l;
        boolean sino = false;
        String respuesta;
        String nombre, apellidos, email, direccion, telefono;
        int opcion = 0;

        Scanner escaner = new Scanner(System.in);

        Repositorio r = new Repositorio();
        do {
            System.out.println("Elige una opcion: \n"
                    + "1.Añadir un contacto \n"
                    + "2.Borrar un contacto \n"
                    + "3.Buscar un contacto \n"
                    + "4.Listar contactos\n"
                    + "5.Salir");
            opcion = escaner.nextInt();

            switch (opcion) {

                case 1:
                    do {
                        System.out.println("Introduce el nombre del contacto");
                        nombre = escaner.next();
                        System.out.println("Introduce los apellidos del contacto");
                        apellidos = escaner.next();
                        System.out.println("Introduce el email del contacto");
                        email = escaner.next();
                        System.out.println("Introduce la direccion del contacto");
                        direccion = escaner.next();
                        System.out.println("Introduce el telefono del contacto");
                        telefono = escaner.next();

                        // hacerlo aqui try catch
                        try {
                            Contacto c = new Contacto(nombre, apellidos, email, direccion, telefono);
                            r.insertaContacto(c);
                        } catch (IllegalArgumentException | NullPointerException e) {
                            System.out.println(e.getMessage());//el getMessage te devuelve el mensaje de excepcion que tenga cada metodo comprobar
                        }

                        System.out.println("¿Desea introducir otro contacto?");
                        respuesta = escaner.next();
                        sino = respuesta.equals("si");

                    } while (sino);

                    break;
                case 2:
                    l = r.listarContactos();
                    for (int i = 0; i < l.size(); i++) {
                        System.out.println("Contacto " + i);
                        System.out.println(l.get(i).getNombre());
                        System.out.println(l.get(i).getApellidos());
                        System.out.println(l.get(i).getEmail());
                        System.out.println(l.get(i).getDireccion());
                        System.out.println(l.get(i).getTelefono());
                    }

                    System.out.println("Introduce el nombre del contacto que desea borrar");
                    if (r.borraContacto(escaner.next())) {
                        System.out.println("El contacto se ha borrado correctamente");
                    } else {
                        System.out.println("No se ha borrado ningun contacto");
                    }
                    break;

                case 3:
               
                    System.out.println("Introduce el nombre del contacto");
                    Contacto c = r.buscarContacto(escaner.next());
                    if (c != null) {
                        System.out.println("Datos del Contacto: ");
                        System.out.println(c.getNombre());
                        System.out.println(c.getApellidos());
                        System.out.println(c.getEmail());
                        System.out.println(c.getDireccion());
                        System.out.println(c.getTelefono());
                    } else {
                        System.out.println("No existe ningun contacto con ese nombre");
                    }
                    break;

                case 4:
                    System.out.println("\u001B[31mListado de contactos de la Agenda:\u001B[0m");
                    l = r.listarContactos();
                    for (int i = 0; i < l.size(); i++) {
                        System.out.println("Contacto " + i);
                        System.out.println(l.get(i).getNombre());
                        System.out.println(l.get(i).getApellidos());
                        System.out.println(l.get(i).getEmail());
                        System.out.println(l.get(i).getDireccion());
                        System.out.println(l.get(i).getTelefono());
                    }

                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        } while (opcion != 5);
    }
}
