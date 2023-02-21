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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import misComponentes.Titulos;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaGUI.
 */
public class VistaGUI extends JFrame {

	/** The linea 2. */
	private JTextField linea2;
	
	/** The panel scroll. */
	private JPanel panelInicial,panelScroll;
	
	/** The imagen juego. */
	private JLabel imagenJuego; 
	
	/** The valor ronda. */
	private JTextArea areaTextoPalabras,valorRonda;
	
	/** The manejo juego. */
	private ManejoDeJuego manejoJuego;
	
	/** The escucha. */
	private Escucha escucha;
	
	/** The temporizador. */
	private Temporizador temporizador;
	
	/** The iterador serie dos. */
	private int iterador,iteradorSerieDos;
	
	/** The inicio. */
	private JButton otraSerie, verificar,salir, inicio;
	
	/** The botones. */
	private Titulos titulo,ronda,botones;
	
	/** The botones 1. */
	private String [] botones1 = { "Acepto las reglas", "Adios"};
	
	/** The botones 2. */
	private String [] botones2 = { "Volver a jugar", "salir"};
	
	/** The scroll 1. */
	private JScrollPane scroll1;
	
	/** The reconocer segunda serie. */
	private TimerTask turnoSerieUno,turnoRondaDos, reconocerPrimerSerie, reconocerSegundaSerie;
	
	/** The fuente. */
	public final Font FUENTE = new Font(Font.DIALOG,Font.BOLD+Font.CENTER_BASELINE,45);
	
	/** The fuente botones. */
	public final Font fuenteBotones= new Font(Font.DIALOG,Font.BOLD+Font.CENTER_BASELINE,20);

	/**
	 * Instantiates a new vista GUI. Pone la ventana por defecto del juego. 
	 */
	public VistaGUI() {
		initGUI();

		setTitle("Juego de palabras");
		pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Inits the GUI. Se crean los componentes graficos del juego.
	 */
	private void initGUI() {
		// TODO Auto-gehacenerated method stub

		//Define el contenedor y el layaout
		
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contenedor = new GridBagConstraints();

		//Pone escuchas e inicializa los objetos. 
		manejoJuego = new ManejoDeJuego();
		escucha= new Escucha();
		temporizador= new Temporizador();

		//pone el panel del juego
		manejoJuego.setBorder((new MatteBorder(2, 2, 2, 2,new Color  (208, 54, 136))));
		contenedor.gridx=0;
		contenedor.gridy=1 ;
		contenedor.gridwidth=2;
		contenedor.fill= GridBagConstraints.NONE;
		manejoJuego.setVisible(false);
		add(manejoJuego,contenedor);


		//Titulos

		titulo =new Titulos("Juego de Palabras",30,new Color (238, 179, 211));
		titulo.setBorder((new MatteBorder(6, 6, 6, 6,new Color  (208, 54, 136))));

		contenedor.gridx=0;
		contenedor.gridy=0;
		contenedor.gridwidth=20;
		contenedor.fill= GridBagConstraints.HORIZONTAL;
		add(titulo,contenedor); 


		//Area de escritura
		linea2 = new JTextField(30);
		linea2.addActionListener(escucha);
		linea2.addKeyListener(escucha);
		linea2.setPreferredSize((new Dimension(30,35)));

		linea2.setToolTipText("Escriba aqui las palabras");
		linea2.setVisible(false);

		contenedor.gridx=0;
		contenedor.gridy=2 ;
		contenedor.gridwidth=1;
		linea2.setBackground(new Color(163, 213, 252));
		linea2.setEditable(false);
		contenedor.fill= GridBagConstraints.NONE;
		add(linea2,contenedor);

		panelScroll= new JPanel();
		panelScroll.setPreferredSize(new Dimension(350,180));
		areaTextoPalabras= new JTextArea(10,30);
		areaTextoPalabras.setEditable(false);
		scroll1 = new JScrollPane(areaTextoPalabras);

		panelScroll.add(scroll1);
		panelScroll.setVisible(false);
		contenedor.gridx=0;
		contenedor.gridy=3 ;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(panelScroll,contenedor);

		//Titulos ronda

		ronda =new Titulos("Ronda",30,new Color (244, 21, 139));
		ronda.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		ronda.setVisible(false);

		contenedor.gridx=1;
		contenedor.gridy=2;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.HORIZONTAL;
		add(ronda,contenedor); 

		//Valor ronda
		valorRonda = new JTextArea();
		valorRonda.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		valorRonda.setPreferredSize((new Dimension(45,75)));
		valorRonda.setFont(FUENTE);
		valorRonda.setEditable(false);

		valorRonda.setVisible(false);
		contenedor.gridx=1;
		contenedor.gridy=3;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(valorRonda,contenedor);

		//Imagen Inicial
		panelInicial = new JPanel();
		panelInicial();

		contenedor.gridx=0;
		contenedor.gridy=1 ;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(panelInicial,contenedor);
		//botones

		botones =new Titulos("Area de botones de juego",30,new Color (238, 179, 211));
		botones.setBorder((new MatteBorder(6, 6, 6, 6,new Color  (208, 54, 136))));
		botones.setVisible(false);
		contenedor.gridx=0;
		contenedor.gridy=5;
		contenedor.gridwidth=2;
		contenedor.fill= GridBagConstraints.HORIZONTAL;
		add(botones,contenedor); 


		salir = new JButton ("Salir del juego");
		salir.setPreferredSize(new Dimension(330,30));
		salir.setBackground(new Color (244, 21, 139));
		salir.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		salir.addActionListener(escucha);
		salir.setForeground(new Color (255, 255, 255));
		salir.setFont(fuenteBotones);
		salir.setVisible(false);
		contenedor.gridx=0;
		contenedor.gridy=8;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(salir,contenedor);


		otraSerie = new JButton ("Otra serie");
		otraSerie.setPreferredSize(new Dimension(120,100));
		otraSerie.addActionListener(escucha);
		otraSerie.setBackground(new Color (244, 21, 139));
		otraSerie.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		otraSerie.setForeground(new Color (255, 255, 255));
		otraSerie.setFont(fuenteBotones);
		otraSerie.setVisible(false);
		contenedor.gridx=1;
		contenedor.gridy=8 ;
		contenedor.gridheight=0;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(otraSerie,contenedor);

		verificar = new JButton ("Verificar");
		verificar.setPreferredSize(new Dimension(120,100));
		verificar.addActionListener(escucha);
		verificar.setBackground(new Color (244, 21, 139));
		verificar.setForeground(new Color (255, 255, 255));
		verificar.setVisible(false);
		verificar.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		verificar.setFont(fuenteBotones);


		contenedor.gridx=1;
		contenedor.gridy=8 ;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(verificar,contenedor);

		inicio = new JButton ("Inicio");
		inicio.setPreferredSize(new Dimension(120,30));
		inicio.addActionListener(escucha);
		inicio.setBackground(new Color (244, 21, 139));
		inicio.setForeground(new Color (255, 255, 255));

		inicio.setBorder((new MatteBorder(2, 2, 2, 2, Color.BLACK)));
		inicio.setFont(fuenteBotones);


		contenedor.gridx=0;
		contenedor.gridy= 2;
		contenedor.gridwidth=1;
		contenedor.fill= GridBagConstraints.NONE;
		add(inicio,contenedor);

	}

	/**
	 * The Class Escucha. .Es la clase que contiene los escuchas del teclado y del ActionListener
	 */
	private class Escucha implements ActionListener, KeyListener{

		/**
		 * Action performed. Un elemento es clickeado y se reconoce. 
		 *
		 * @param e the e el evento de accion
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub



			if(e.getSource()==otraSerie) {
				reconocerPrimerSerie.cancel();
				temporizador.tiempoDosSerie();
				otraSerie.setVisible(false);
				verificar.setVisible(true);
				areaTextoPalabras.setText(" ");
				linea2.setEditable(false);
				linea2.setBorder(null);
				panelScroll.setVisible(true);
				manejoJuego.borrarCrearArchivo();
				pack();
			}



			if(e.getSource()==verificar) {

				reconocerSegundaSerie.cancel();
				manejoJuego.borrarCrearArchivo();
				areaTextoPalabras.setText(" ");
				manejoJuego.determinarRonda();
				cambiarPartidaJuego();
				System.out.println(manejoJuego.obtenerRonda());
				otraSerie.setVisible(true);
				verificar.setVisible(false);
				linea2.setBorder(null);
				linea2.setEditable(false);
				System.out.println(manejoJuego.getPalabrasDeUsuario());
				valorRonda.setText(String.valueOf(manejoJuego.obtenerRonda()));
			}

			if(e.getSource()==inicio) {
				panelInicial.setVisible(false);
				inicio.setVisible(false);
				titulo.setVisible(false);

				ImageIcon informativa = new ImageIcon("src/panelInicial/informativa.png"); 

				String mensaje2 = " Reglas del Juego:\n"+"Encontrarás una serie de palabras aleatorias, deberás recordar la mayoria\n"+"Son dos series por cada ronda, una vez que termine la primer serie,\n"+
						"seleccionas el boton otra serie, cuando termine cada serie ingresa las\n"+"palabras que recuerdes en el campo de texto, finalmente, selecciona\n"+
						"el boton verificar y veremos si pasas de ronda.\n"+"Tienes 30 sg para ingresar las palabras que recuerdes por serie.\n"+"Animo, buena suerte";


				int result= JOptionPane.showOptionDialog (null, mensaje2, "Reglas del juego", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, informativa, botones1, botones1[0]);

				if(result == JOptionPane.YES_OPTION) {
					titulo.setVisible(true);
					inicializar();
					pack();
				}
				if(result == JOptionPane.NO_OPTION) {
					System.exit(0); 
				}

			}


			if(e.getSource()== salir) {
				String mensaje = "Estas en la ronda: "+ manejoJuego.obtenerRonda()+ "\n¿Deseas volver a jugar?,Es un juego muy divertido";

				int result= JOptionPane.showOptionDialog (null, mensaje, "Resultado", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones2, botones2[0]);

				if(result == JOptionPane.YES_OPTION) {

					PrincipalJuegoDePalabras.main(null); //reinicia el juego automaticamente 
				}

				if(result == JOptionPane.NO_OPTION) {
					System.exit(0); 
				}
			}
		}

		/**
		 * Key typed.
		 *
		 * @param ek the ek 
		 */
		@Override
		public void keyTyped(KeyEvent ek) {
			// TODO Auto-generated method stub

		}

		/**
		 * Key pressed.Una letra del teclado es presionada. 
		 *
		 * @param ek the ek, es el evento del teclado. 
		 */
		@Override
		public void keyPressed(KeyEvent ek) {
			// TODO Auto-generated method stub
			if(ek.getKeyCode()==10) {

				manejoJuego.escribirArchivo(linea2.getText());
				linea2.setText(null);
				linea2.requestFocus();
				areaTextoPalabras.setText(manejoJuego.abrirArchivo());

			}

		}

		/**
		 * Key released.
		 *
		 * @param ek the ek
		 */
		@Override
		public void keyReleased(KeyEvent ek) {
			// TODO Auto-generated method stub

		}



	}



	/**
	 * Cambiar partida juego. Establece las condiciones iniciales del juego
	 * cuando la ronda cambia.
	 */
	public void cambiarPartidaJuego() {

		if(manejoJuego.obtenerRonda()==1) {
			porRonda();
		}
		if(manejoJuego.obtenerRonda()==2) {
			porRonda();
		}if(manejoJuego.obtenerRonda()==3) {
			porRonda();
		}if(manejoJuego.obtenerRonda()==4) {
			porRonda();
		}
		if(manejoJuego.obtenerRonda()==5) {
			porRonda();
		}
		if(manejoJuego.obtenerRonda()==6) {
			salir.setVisible(false);
			verificar.setVisible(false);
			mensajeGanador();
		}
	}

	/**
	 * Mensaje ganador.Establece el mensaje en una ventana que se 
	 * le anunciará al usuario ganador.
	 */
	public void mensajeGanador() {

		ImageIcon ganador = new ImageIcon("src/panelInicial/ganador.png"); 

		String mensaje2 = "FELICITACIONES \n"+"Has ganado el juego, tienes una excelente memoria\n"+"¿Qué deseas hacer?\n";


		int result= JOptionPane.showOptionDialog (null, mensaje2, "Ganador", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, ganador, botones2, botones2[0]);

		if(result == JOptionPane.YES_OPTION) {
			PrincipalJuegoDePalabras.main(null); 
		}
		if(result == JOptionPane.NO_OPTION) {
			System.exit(0); 
		}

	}

	/**
	 * Por ronda.Son las condiciones que el juego debe tener por 
	 * cada ronda.
	 */
	public void porRonda() {
		iterador=0;
		int numeroIterador=manejoJuego.retornarPalabras().size()/2;
		iteradorSerieDos= numeroIterador;
		temporizador.tiempoUnaSerie();
	}


	/**
	 * Inicializar.Codiciones iniciales del juego, cuando este apenas 
	 * inicia y es abierto por el usuario. 
	 */
	public void inicializar() {
		temporizador.tiempoUnaSerie();
		valorRonda.setText(String.valueOf(manejoJuego.obtenerRonda()));
		iterador= 1;

		manejoJuego.listaDePalabrasEnJuego();
		manejoJuego.palabrasPorSerie();
		manejoJuego.ponerLaPrimerPalabra();
		int otro=(manejoJuego.retornarPalabras().size()/2);
		iteradorSerieDos= otro;

		manejoJuego.setVisible(true);
		linea2.setVisible(true); 
		panelScroll.setVisible(true);
		ronda.setVisible(true);
		valorRonda.setVisible(true);
		botones.setVisible(true);
		salir.setVisible(true);
		otraSerie.setVisible(true);
	}


	/**
	 * The Class Temporizador. Clase que controla los Timer, para determinar
	 * el tiempo que se le da a cada usuario en el ingreso de palabras. 
	 */
	public class Temporizador 
	{
		
		/**
		 * Tiempo una serie. Establece el pintado de todas las palabras que aparecen en la serie uno. 
		 * además establece lo que debe pasar al transcurrir el tiempo de ingreso de palabras.
		 */
		public  void tiempoUnaSerie() 
		{
			Timer timer = new Timer();

			turnoSerieUno= new TimerTask() 
			{

				@Override
				public void run() {

					String palabra= manejoJuego.retornarPalabras().get(iterador);
					manejoJuego.pintarPalabras(palabra);
					if (iterador < (manejoJuego.retornarPalabras().size()/2)) {
						iterador++;
					}


					if(iterador==manejoJuego.retornarPalabras().size()/2) {

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						String ingresePalabras= "Palabras: ";
						manejoJuego.pintarPalabras(ingresePalabras);
						linea2.setEditable(true);
						linea2.setFocusable(true);
						linea2.setBorder(new MatteBorder(2, 2, 2, 2,new Color  (208, 54, 136)));

						tiempoReconocer();			
					}
					else {
						temporizador.tiempoUnaSerie();
					} 
				}
			};

			timer.schedule(turnoSerieUno,1500);
		}


		/**
		 * Tiempo dos serie.Establece el pintado de todas las palabras que aparecen en la serie dos,
		 * además establece lo que debe pasar al transcurrir el tiempo de ingreso de palabras de la 
		 * serie dos.
		 */
		public  void tiempoDosSerie() 
		{
			Timer timer = new Timer();

			turnoRondaDos= new TimerTask() 
			{
				@Override
				public void run() {

					String palabra= manejoJuego.retornarPalabras().get(iteradorSerieDos);
					manejoJuego.pintarPalabras(palabra);

					if(iteradorSerieDos <  manejoJuego.retornarPalabras().size()) {
						iteradorSerieDos++;
					}

					if(iteradorSerieDos==manejoJuego.retornarPalabras().size()) {

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String ingresePalabras= "Palabras: ";
						manejoJuego.pintarPalabras(ingresePalabras);

						linea2.setEditable(true);
						linea2.setBorder(new MatteBorder(2, 2, 2, 2,new Color  (208, 54, 136)));
						tiempoReconocerSegundaSerie(); 
					}
					else {
						temporizador.tiempoDosSerie();
					} 
				}

			};

			timer.schedule(turnoRondaDos,1500);
		}


		/**
		 * Tiempo reconocer.Establece lo que debe suceder despues de 30 sg
		 * si el usuario no oprime el botón de otra serie. 
		 */
		public  void tiempoReconocer() 
		{
			Timer timer = new Timer();
			reconocerPrimerSerie= new TimerTask() 
			{

				@Override
				public void run() {

					otraSerie.setVisible(false);
					verificar.setVisible(true);
					areaTextoPalabras.setText(" ");
					linea2.setEditable(false);
					linea2.setBorder(null);
					panelScroll.setVisible(true);
					manejoJuego.borrarCrearArchivo();
					temporizador.tiempoDosSerie();
					pack();


				}

			};

			timer.schedule(reconocerPrimerSerie,30000);
		}


		/**
		 * Tiempo reconocer segunda serie.Establece lo que debe suceder despues de 30 sg
		 * si el usuario no oprime el botón de validar. 
		 */
		public  void tiempoReconocerSegundaSerie() 
		{
			Timer timer = new Timer();

			reconocerSegundaSerie= new TimerTask() 
			{

				@Override
				public void run() {



					manejoJuego.borrarCrearArchivo();
					areaTextoPalabras.setText(" ");
					manejoJuego.determinarRonda();
					cambiarPartidaJuego();
					System.out.println(manejoJuego.obtenerRonda());
					otraSerie.setVisible(true);
					verificar.setVisible(false);
					linea2.setBorder(null);
					linea2.setEditable(false);
					valorRonda.setText(String.valueOf(manejoJuego.obtenerRonda()));
				}

			};

			timer.schedule(reconocerSegundaSerie,30000);
		}
	}

	/**
	 * Panel inicial.Ingresa la imagen inicial del juego. 
	 */
	public void panelInicial() {

		panelInicial.setPreferredSize(new Dimension(600,450));
		imagenJuego= new JLabel();
		ImageIcon icon = new ImageIcon("src/panelInicial/inicioJuego.jpg"); 
		imagenJuego.setIcon(icon);
		panelInicial.add(imagenJuego);

	}
}