/*
 * Programación Interactiva. 
 * 
 * Autores: Carolain Jimenez Bedoya - 2071368 
 *          Natalia Lopez Osorio  - 2025618
 *          Hernando Lopez Rincón - 2022318
 *          
 * Mini-proyecto 3: Juego de palabras. 
 */

package juegoDePalabras;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.JPanel;


// TODO: Auto-generated Javadoc
/**
 * The Class ManejoDeJuego. Clase que controla todas las condiciones y reglas de juego.
 */
public class ManejoDeJuego extends JPanel{

	/** The leer ronda. */
	private FileReader leerTodasLasPalabras, leerPalabrasDeValidacion;
	
	/** The ronda words. */
	private BufferedReader almacenarPalabras,palabrasRonda;
	
	/** The palabras usuario. */
	private FileWriter palabrasParaValidar,palabrasUsuario;
	
	/** The archivo borrar. */
	private File archivoBorrar;
	
	/** The salidas usuario. */
	private BufferedWriter salidasUsuario;
	
	/** The validar. */
	private ArrayList<String> palabrasPorSerie, listaPalabras,palabrasDelUsuario,validar;
	
	/** The hs. */
	private HashSet<String> hs;
	
	/** The punto. */
	private int ronda,palabras,punto;
	
	/** The palabra. */
	private String salida, palabrasSalir,palabra;
	
	/** The fuente. */
	public final Font FUENTE = new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,65);




	/**
	 * Es el constructor de la clase, donde se inicializan todas las implementaciones del código, los valores iniciales que se tienen al iniciar el juego.
	 */
	
	public ManejoDeJuego() {  
		ronda=1;
		listaPalabras = new ArrayList<String>(); //Todas las palabras a usar 
		palabrasPorSerie =new ArrayList<String>(); //Palabras que se mandaran por ronda
		palabrasDelUsuario =new ArrayList<String>(); //Palabras que ingresa el usuario 
		hs =new HashSet<String>(); //Validar palabras repetidas
		validar =new ArrayList<String>(); //Palabras por ronda e ingresadas por el usuario para compararlas
		palabra = " ";
		this.setPreferredSize(new Dimension(500,160));
		punto=0;
	}


	/**
	 * Mezclar lista. Esta función mezcla todas las palabras que hay dentro del array de palabras totales,
	 *  para que siempre todas aparezcan en distintas posiciones. 
	 */
	public void mezclarLista() {
		Collections.shuffle(listaPalabras);
	}

	
	/**
	 * Lista de palabras en juego. La función lee todas las palabras que están dentro del archivo que contiene 
	 *todas las palabras totales del juego y las ingresa en un array.  
	 */
	public  void  listaDePalabrasEnJuego() {

		try {

			leerTodasLasPalabras = new FileReader("src/archivos/palabrasAUsar");
			almacenarPalabras = new BufferedReader(leerTodasLasPalabras);
			String texto = almacenarPalabras.readLine();
			listaPalabras.add(texto);

			while(texto!=null) {
				texto = almacenarPalabras.readLine();
				listaPalabras.add(texto);
				listaPalabras.removeIf(Objects::isNull);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}	


	/**
	 * Determinar condiciones. Determina cuantas palabras deben aparecer en el panel dependiendo de la ronda
	 * en la que esté el jugador. 
	 *
	 * @return the int Retorna la cantidad de palabras que se muestran por ronda. 
	 */
	public int determinarCondiciones() { 
		if (ronda==1) {
			palabras=8;
		}else if(ronda==2) {
			palabras=12;
		}else if(ronda==3) {
			palabras=16;
		}else if(ronda==4) {
			palabras=20;
		}else if(ronda==5) {
			palabras=24;
		}
		return palabras;
	}



	/**
	 * Palabras por serie. Ingresa las palabras por ronda dentro de un array en distintas
	 *  posiciones, dependiendo de la cantidad de palabras que sean permitidas. 
	 */
	public void palabrasPorSerie(){
		mezclarLista();
		determinarCondiciones();

		for(int i=0;i<palabras; i++ ) {
			String word = listaPalabras.get(i);
			palabrasPorSerie.add(word);
		}

	}


	/**
	 * Retornar palabras.Retorna las palabras por ronda.
	 *
	 * @return the array list Retorna una lista dinamica con las palabras por ronda. 
	 */
	public ArrayList<String> retornarPalabras(){
		return palabrasPorSerie;
	}


	/**
	 * Pintar palabras. Este método invoca el dibujado que se desea hacer, se le ingresa
	 * una palabra para ser pintada con ciertas características.
	 *
	 * @param palabraMostrar the palabra mostrar, es la palabra que se ingresa. 
	 * @return the string Retorna la pabra pintada. 
	 */
	public String pintarPalabras(String palabraMostrar) {
		this.palabra= palabraMostrar;
		repaint();
		return palabra;
	}


	/**
	 * Paint component. Es el método donde se realiza el dibujado con las caracteristicas que se
	 * deseeb, en este caso para pintar una palabra con determinado tamaño y fuente. 
	 *
	 * @param g the g
	 */
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);
		g.setColor(new Color (231, 231, 231 ));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setFont(FUENTE);
		g.setColor(new Color (38, 32, 53));
		g.drawString(palabra,this.getWidth()/2-160, this.getHeight()/2+14);

	}




	/**
	 * Poner la primer palabra. Pinta la primer palabra del array que contiene
	 * las palabras por ronda
	 */
	public void ponerLaPrimerPalabra() {
		String palabra= palabrasPorSerie.get(0);
		pintarPalabras(palabra);
	}

	/**
	 * Abrir archivo. //Lee el archivo con las palabras que ingrese el usuario 
	 * y con la intención de conocer la ruta donde se ubica el archivo de texto. 
	 *
	 * @return the string Retorna las palabras que ingresa el usuario.
	 */
	
	public String abrirArchivo() {
		palabrasSalir = "";
		try {
			leerPalabrasDeValidacion = new FileReader("src/archivos/palabrasAValidar");
			palabrasRonda = new BufferedReader(leerPalabrasDeValidacion);

			String texto = palabrasRonda.readLine();

			while(texto!=null) {
				palabrasSalir+=texto;
				palabrasSalir+="\n";
				texto = palabrasRonda.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				palabrasRonda.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return palabrasSalir;		
	}


	/**
	 * Escribir archivo. Escribe cada una de las palabras que ingresa el
	 * usuario, dentro del archivo de texto anterior. 
	 *
	 * @param linea2 the linea 2
	 */

	public void escribirArchivo(String linea2) {
		try {
			palabrasUsuario = new FileWriter("src/archivos/palabrasAValidar",true);
			salidasUsuario = new BufferedWriter(palabrasUsuario);
			salidasUsuario.write(linea2);
			salidasUsuario.newLine();
			palabrasDelUsuario.add(linea2);
			palabrasDelUsuario.removeIf(Objects::isNull);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				salidasUsuario.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	/**
	 * Condiciones array. Este método evalua cuantas palabras son iguales
	 * el Hash pone todas las palabras sin repetir entre las palabras por 
	 * ronda y las ingresadas por el usuario, y evalua la diferencia entre
	 * todas y las no repetidas, si el resultado es 2, significa que existen
	 * 2 palabras que se repitieron. 
	 *
	 * @return the int Retorna los puntos que obtiene el usuario. 
	 */
	public int condicionesArray() {
		validar.addAll(palabrasPorSerie);
		validar.addAll(palabrasDelUsuario);
		validar.replaceAll( e -> e.trim().toLowerCase() );
		hs.addAll(validar);
		punto= validar.size()-hs.size();

		return punto;


	}

	/**
	 * Determinar ronda. Determina la ronda y kas condiciones del juego 
	 * por cada ronda.
	 */
	public void determinarRonda () {

		condicionesArray();

		if(ronda==1 && punto>=7) {
			ronda=2;
			cambiarRonda();
		}else if(ronda==1 && punto<7){
			ronda=1;
			cambiarRonda();
		}

		if(ronda==2 && punto>=9) {
			ronda=3;
			cambiarRonda();
		} else if(ronda==2 && punto<9){
			ronda=2;
			cambiarRonda();
		}

		if(ronda==3 && punto>=12) {
			ronda=4;
			cambiarRonda();
		} else if(ronda==3 && punto<12){
			ronda=3;
			cambiarRonda();
		}

		if(ronda==4 && punto>=15) {
			ronda=5;
			cambiarRonda();
		}else if(ronda==4 && punto<15){
			ronda=4;
			cambiarRonda();
		}
		if(ronda==5 && punto>=18) {
			ronda=6;

		}else if(ronda==5 && punto<18){
			ronda=5;
			cambiarRonda();
		}
	}


	/**
	 * Obtener ronda. Obtiene la ronda del juego.
	 *
	 * @return the int Retorna la ronda en la que se encuentra el  usuario.
	 */
	public int obtenerRonda() {
		return ronda;

	}


	/**
	 * Cambiar ronda. Establece las condiciones iniciales cuando se cambia de ronda,
	 */
	public void cambiarRonda() {
		punto=0;
		validar.clear();
		palabrasPorSerie.clear();
		palabrasDelUsuario.clear();
		hs.clear();
		palabrasPorSerie();

	}

	/**
	 * Gets the palabras de usuario. Obtiene las palabras que ingresó 
	 * el usuario en un array
	 *
	 * @return the palabras de usuario Retorna en un array
	 * las palabras ingresadas por el usuario. 
	 */
	public ArrayList<String> getPalabrasDeUsuario(){
		return palabrasDelUsuario;

	}
	
	/**
	 * Borrar crear archivo.Borra el archivo de palabras a validar para 
	 * que se limpie cada vez que sea necesario.

	 */
	public void borrarCrearArchivo() {
		String archivoABorrar = "src/archivos/palabrasAValidar";
		File archivoBorrar = new File(archivoABorrar);

		archivoBorrar.delete();
		try {
			archivoBorrar.createNewFile();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}
