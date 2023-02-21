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

import java.awt.EventQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class PrincipalJuegoDePalabras. Clase que contiene el metodo main()
 */
public class PrincipalJuegoDePalabras {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run () {

					VistaGUI myVista= new VistaGUI();
				}
			});	


		}

	}


