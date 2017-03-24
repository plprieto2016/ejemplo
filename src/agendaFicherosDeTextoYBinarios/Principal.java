/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaFicherosDeTextoYBinarios;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author cristina
 */
public class Principal {

    public static void main(String[] args) throws Exception {
        Contacto[] lista;
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
                    + "4.Listar un contacto\n"
                    + "5.Escribir los datos en un fichero\n"
                    + "6.Leer los datos de un fichero\n"
                    + "7.Escribir los datos en un fichero binario\n"
                    + "8.Leer los datos de un fichero binario\n"
                    + "9.Salir");
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
                    lista = r.listarContactos();
                    for (int i = 0; i < lista.length; i++) {
                        System.out.println("Contacto " + i);
                        System.out.println(lista[i].getNombre());
                        System.out.println(lista[i].getApellidos());
                        System.out.println(lista[i].getEmail());
                        System.out.println(lista[i].getDireccion());
                        System.out.println(lista[i].getTelefono());
                    }

                    System.out.println("Introduce el nombre del contacto que desea borrar");
                    if (r.borraContacto(escaner.next())) {
                        System.out.println("El contacto se ha borrado correctamente");
                    } else {
                        System.out.println("No se ha borrado ningun contacto");
                    }

                    break;

                case 3:
                    lista = r.listarContactos();

                    System.out.println("Introduce el nombre del contacto");
                    if (r.buscarContacto(escaner.next())) {
                        for (int i = 0; i < lista.length; i++) {
                            System.out.println("Contacto " + i);
                            System.out.println(lista[i].getNombre());
                            System.out.println(lista[i].getApellidos());
                            System.out.println(lista[i].getEmail());
                            System.out.println(lista[i].getDireccion());
                            System.out.println(lista[i].getTelefono());
                        }
                    } else {
                        System.out.println("No existe ningun contacto con ese nombre");
                    }

                    break;

                case 4:
                    lista = r.listarContactos();
                    for (int i = 0; i < lista.length; i++) {
                        System.out.println("Contacto " + i);
                        System.out.println(lista[i].getNombre());
                        System.out.println(lista[i].getApellidos());
                        System.out.println(lista[i].getEmail());
                        System.out.println(lista[i].getDireccion());
                        System.out.println(lista[i].getTelefono());
                    }

                    break;
                case 5:
                    try {
                        r.escribirEnFichero();
                    } catch (IOException e) {
                        System.out.println("Error al leer del fichero");
                    }
                    break;
                case 6:
                    try {
                        r.leerEnFichero();
                    } catch (IOException e) {
                        System.out.println("Error al leer del fichero");
                    }
                    break;
                case 7:
                    try {
                        r.escribirEnUnFicheroBinario();
                    } catch (IOException e) {
                        System.out.println("Error al escribir en el fichero binario");
                    }
                    break;
                case 8:
                    try{
                        r.leerDeUnFicherBinario();
                    }catch(IOException e){
                        System.out.println("Error al leer del fichero binario");
                    }
                    break;
                case 9:
                    System.exit(0);
                    break;

            }

        } while (opcion != 9);
    }
}
