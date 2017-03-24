/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaSobreFichero;

import agenda.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Clase con el funcionamiento de cada opcion del menu(metodos)
 * @author cristina
 */
public class Repositorio {
    final String nombreFichero = "agenda.txt";


    public Repositorio() throws IOException {
        File miFichero = new File(nombreFichero); //crea el objeto de tipo fichero
        
        if (!miFichero.exists()) {
            miFichero.createNewFile(); //comprueba si el fichero real esta creado, si no lo crea
        }
    }
    
    /**
     * Metodo que inserta un contacto en un arraylist
     * @param c
     */
    public boolean insertaContacto(Contacto c) {// para insertar le tengo que pasar el contacto 
        //es decir le paso el objeto creado en la "vista" o main el cual contiene el contacto
        boolean resultado=true;
        PrintWriter pr=null;
        
        try {
            File f = new File(nombreFichero); //creamos el objeto de tipo fichero
            pr = new PrintWriter(new FileWriter(f,true)); //llama a la clase con la que escribiremos en el fichero de texto

            pr.println(c.getNombre()+"|"+c.getApellidos()+"|"+c.getEmail()+"|"+
                        c.getDireccion()+"|"+c.getTelefono());
          
            
        } catch (IOException e){
            resultado=false;
        } catch (Exception e2){
            
        }finally {
            if (pr!=null){
                pr.close();
            } //Cerramos la clase printWriter
        }
        return resultado;
    }

    /**
     * Metodo que muestra los contactos almacenados en el arraylist
     * @return lista
     */
    public ArrayList<Contacto> listarContactos() { // este metodo devuelve un array  con los contactos
        ArrayList<Contacto> lista = new ArrayList();  // aqui creamos un array con la longitud del array list

        File f = new File(nombreFichero);//se le indica el fichero que queremos leer poniendo el nombre
        Contacto c;
        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(f)); // llama a la clase con la que leeremos el fichero de texto

            String cad = br.readLine(); 
            while (cad != null){
                String [] partes=cad.split(Pattern.quote("|"));
                c=new Contacto(partes[0],partes[1],partes[2],partes[3],partes[4]);
                lista.add(c);
                cad = br.readLine();
            }
        } catch (IOException e){
            return null;
        } 
        finally {
            if (br != null){
                try {
                   br.close();
                } catch (Exception e2){
                    return null;
                }
            }
        }
        return lista; // devuelve el array
    }

    /**
     * Metodo que borra un contacto de la lista mediante el nombre
     * @param nombre
     * @return comprobar
     */
    public boolean borraContacto(String nombre) {
        File f = new File(nombreFichero);//se le indica el fichero que queremos leer poniendo el nombre
        File f2 = new File("temp.txt");
        Contacto c=null;
        BufferedReader br=null;
        PrintWriter pr=null;
        boolean encontrado=false;
            try {
                if (!f2.exists()) {
                f2.createNewFile(); 
            }
            pr = new PrintWriter(new FileWriter(f2,true)); //llama a la clase con la que escribiremos en el fichero de texto
            br = new BufferedReader(new FileReader(f)); // llama a la clase con la que leeremos el fichero de texto

            String cad = br.readLine(); 
            while (cad != null){
                String [] partes=cad.split(Pattern.quote("|"));
                if (nombre.equals(partes[0])){
                   encontrado=true;
                } else {
                    pr.println(cad);
                }
                cad = br.readLine();
            }
        } catch (IOException e){
            return false;
        } 
        finally {
            if (br != null){
                try {
                   br.close();
                } catch (Exception e2){
                    return false;
                }
            }
            if (pr != null){
                try {
                  pr.close();
                } catch (Exception e3){
                    return false;
                }
            }
        }
        if (encontrado){
            try {
                f.delete();
                f2.renameTo(new File(nombreFichero));
            } catch (Exception e){
                return false;
            } 
        }    
        return encontrado;
    }

    /**
     * Metodo que busca un contacto concreto en la lista 
     * @param nombre
     * @return posicion
     */
    public Contacto buscarContacto(String nombre) {
        File f = new File(nombreFichero);//se le indica el fichero que queremos leer poniendo el nombre
        Contacto c=null;
        BufferedReader br=null;
        boolean encontrado=false;
        try {
            br = new BufferedReader(new FileReader(f)); // llama a la clase con la que leeremos el fichero de texto

            String cad = br.readLine(); 
            while (!encontrado && cad != null){
                String [] partes=cad.split(Pattern.quote("|"));
                if (nombre.equals(partes[0])){
                   c=new Contacto(partes[0],partes[1],partes[2],partes[3],partes[4]);
                   encontrado=true;
                }
                cad = br.readLine();
            }
        } catch (IOException e){
            return null;
        } 
        finally {
            if (br != null){
                try {
                   br.close();
                } catch (Exception e2){
                    return null;
                }
            }
        }
        return c;

    }
}
