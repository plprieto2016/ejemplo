/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaGuardaObjeto;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author cristina
 */
public class Principal {
    
      public static void main(String[] args) {
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
                    + "5.Guardar un objeto de tipo contacto\n"
                    + "6.Leer un objeto de tipo contacto\n"
                    + "7.Salir");
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
                    int posicion = r.buscarContacto(escaner.next());
                    if (posicion != -1) {
                        System.out.println("Contacto " + posicion);
                        System.out.println(lista[posicion].getNombre());
                        System.out.println(lista[posicion].getApellidos());
                        System.out.println(lista[posicion].getEmail());
                        System.out.println(lista[posicion].getDireccion());
                        System.out.println(lista[posicion].getTelefono());
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
                    try{
                          r.guardaObjeto();
                          System.out.println("Se ha guardado correctamente");
                    }catch(IOException e){
                        System.out.println("Error al guardar el objeto");
                    }
                         
                    break;
                case 6: 
                      try{
                        lista =  r.leerDeUnFicheroUnObjeto();
                        
                        for(int i=0; i<lista.length;i++){
                            System.out.println(lista[i].getNombre());
                            System.out.println(lista[i].getApellidos());
                            System.out.println(lista[i].getEmail());
                            System.out.println(lista[i].getDireccion());
                            System.out.println(lista[i].getTelefono());
                        }
                        
                    }catch(IOException e){
                        System.out.println("Error al guardar el objeto");
                    }catch(ClassNotFoundException c){
                        System.out.println("Error al guardar el objeto");
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        } while (opcion != 7);
    }
}
