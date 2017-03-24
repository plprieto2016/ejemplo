/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendaFicherosDeTextoYBinarios;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author cristina
 */
public class Repositorio {

    // me creo un arraylist y le paso el nombre de la clase que  quiero crearme la lista 
    private ArrayList<Contacto> listaContactos;

    public Repositorio() {
        //inicializo el arraylist
        listaContactos = new ArrayList<Contacto>();
    }

    public void insertaContacto(Contacto c) {// para insertar le tengo que pasar el contacto 
        //es decir le paso el objeto creado en la "vista" o main el cual contiene el contacto

        listaContactos.add(c);// y ya lo añade con el metodo add pasandole el objeto

    }

    public Contacto[] listarContactos() { // este metodo devuelve un array  con los contactos
        Contacto[] lista = new Contacto[listaContactos.size()];  // aqui creamos un array con la longitud del array list

        for (int i = 0; i < listaContactos.size(); i++) { // con el for vamos guardando cada contacto en una posicion del array
            lista[i] = listaContactos.get(i);
        }
        return lista; // devuelve el array
    }

    public boolean borraContacto(String nombre) {
        boolean comprobar = false;
        Contacto[] lista = new Contacto[listaContactos.size()];// al ser de tipo contacto puede utilizar todos sus metodos  
        //(es una copia de la clase contacto)

        for (int i = 0; i < listaContactos.size(); i++) {
            lista[i] = listaContactos.get(i);
            if (nombre.equals(lista[i].getNombre())) {
                listaContactos.remove(lista[i]);
                comprobar = true;
            }

        }
        return comprobar;
    }

    public boolean buscarContacto(String nombre) {
        boolean comprobar = false;
        Contacto[] lista = new Contacto[listaContactos.size()];// al ser de tipo contacto puede utilizar todos sus metodos  
        //(es una copia de la clase contacto)

        for (int i = 0; i < listaContactos.size(); i++) {
            lista[i] = listaContactos.get(i);
            if (nombre.equals(lista[i].getNombre())) {

                comprobar = true;

            }
        }
        return comprobar;
    }

    public void escribirEnFichero() throws IOException {
        //ESCRIBIR EN FICHERO
        //tengo que escribir el array list en el fichero el cual contiene todos los contactos introducidos
        File f = new File("FicheroContactos.txt"); //creamos el objeto de tipo fichero
        Contacto c;
        if (f.exists() == false) {
            f.createNewFile(); //comprobamos si el fichero existe, si no es asi lo crea
        }

        PrintWriter pr = new PrintWriter(new FileWriter(f)); //llama a la clase con la que escribiremos en el fichero de texto
        

        for (int i = 0; i < listaContactos.size(); i++) { // con el for vamos guardando cada contacto en una posicion del array
            c = listaContactos.get(i);
            pr.println(c.getNombre()+"|"+c.getApellidos()+"|"+c.getEmail()+"|"+
                    c.getDireccion()+"|"+c.getTelefono());
        }

        pr.close(); //Cerramos la clase printWriter

    }

    public void leerEnFichero() throws FileNotFoundException, IOException {
        BufferedReader br;
        
            File f = new File("FicheroContactos.txt");//se le indica el fichero que queremos leer poniendo el nombre
            Contacto c;
            if (!f.exists()) {
                System.out.println("Error:el fichero no existe"); //Si el fichero no existe muestra un mensaje.
                System.exit(1);
            }
            listaContactos.clear();
            br = new BufferedReader(new FileReader(f)); // llama a la clase con la que leeremos el fichero de texto

            String cad = br.readLine(); //leera una linea completa y la guardara en una variable de tipo String

            /*
             * Bucle que se repetira hasta que al intentar leer del fichero no haya nada escrito,
             * mostrara por pantalla todo lo que haya escrito en el fichero.
             */
            while (cad != null) {
                System.out.println(cad);
                String [] partes=cad.split(Pattern.quote("|"));
                c=new Contacto(partes[0],partes[1],partes[2],partes[3],partes[4]);
                listaContactos.add(c);
                cad = br.readLine();
            }
            

                br.close(); //Cerramos la clase BufferedReader

    }

    public void escribirEnUnFicheroBinario() throws IOException {
        File miFichero = new File("fichero.dat"); //crea el objeto de tipo fichero
        Contacto c;
        
        if (!miFichero.exists()) {
            miFichero.createNewFile(); //comprueba si el fichero real esta creado, si no lo crea
        }
        //ESCRIBIR 
        DataOutputStream doe = new DataOutputStream(new FileOutputStream(miFichero)); //llama a la clase con la que escribiremos en un fichero binario

        for (int i = 0; i < listaContactos.size(); i++) { // con el for vamos guardando cada contacto en una posicion del array
            c = listaContactos.get(i);
            doe.writeUTF(c.getNombre());
            doe.writeUTF(c.getApellidos());
            doe.writeUTF(c.getEmail());
            doe.writeUTF(c.getDireccion());
            doe.writeUTF(c.getTelefono());
        }

        doe.close(); //cerramos la clase DataOutputStream  

    }

    public void leerDeUnFicherBinario() throws FileNotFoundException, IOException {
        File miFichero = new File("fichero.dat"); //crea el objeto de tipo fichero
        DataInputStream die = new DataInputStream(new FileInputStream(miFichero)); //llama a la clase con la que leeremos el fichero binario
        Contacto c;
        boolean finalAlcanzado=false;
        listaContactos.clear();

        while (!finalAlcanzado){
            try {
                String cadena0 = die.readUTF();
                String cadena1 = die.readUTF();
                String cadena2 = die.readUTF();
                String cadena3 = die.readUTF();
                String cadena4 = die.readUTF();
                listaContactos.add(new Contacto(cadena0,cadena1,cadena2,cadena3,cadena4));
               
            } catch (EOFException e){
                finalAlcanzado=true;
            }
        }
        die.close(); //cerramos la clase DataInputStream

    }

}
