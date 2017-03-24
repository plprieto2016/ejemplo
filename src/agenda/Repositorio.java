/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import java.util.ArrayList;

/**
 * Clase con el funcionamiento de cada opcion del menu(metodos)
 * @author cristina
 */
public class Repositorio {

    // me creo un arraylist y le paso el nombre de la clase que  quiero crearme la lista 
    private ArrayList<Contacto> listaContactos;


    public Repositorio() {
        //inicializo el arraylist
        listaContactos = new ArrayList<Contacto>();
    }
    
    /**
     * Metodo que inserta un contacto en un arraylist
     * @param c
     */
    public void insertaContacto(Contacto c) {// para insertar le tengo que pasar el contacto 
        //es decir le paso el objeto creado en la "vista" o main el cual contiene el contacto

        listaContactos.add(c);// y ya lo añade con el metodo add pasandole el objeto
    }

    /**
     * Metodo que muestra los contactos almacenados en el arraylist
     * @return lista
     */
    public Contacto[] listarContactos() { // este metodo devuelve un array  con los contactos
        Contacto[] lista = new Contacto[listaContactos.size()];  // aqui creamos un array con la longitud del array list

        for (int i = 0; i < listaContactos.size(); i++) { // con el for vamos guardando cada contacto en una posicion del array
            lista[i] = listaContactos.get(i);
        }
        return lista; // devuelve el array
    }

    /**
     * Metodo que borra un contacto de la lista mediante el nombre
     * @param nombre
     * @return comprobar
     */
    public boolean borraContacto(String nombre) {
        boolean encontrado = false;
        int i=0;
        
        while (!encontrado && i<listaContactos.size()){
            if (nombre.equals(listaContactos.get(i).getNombre())){
                encontrado=true;
                listaContactos.remove(i);
            } else {
                i++;
            }
        }
       
        return encontrado;
    }

    /**
     * Metodo que busca un contacto concreto en la lista 
     * @param nombre
     * @return posicion
     */
    public int buscarContacto(String nombre) {
        int posicion = -1;
        Contacto[] lista = new Contacto[listaContactos.size()];// al ser de tipo contacto puede utilizar todos sus metodos  
        //(es una copia de la clase contacto)

        for (int i = 0; i < listaContactos.size(); i++) {
            lista[i] = listaContactos.get(i);
            if (nombre.equals(lista[i].getNombre())) {
                posicion = i;
            }
        }
        return posicion;
    }
}
