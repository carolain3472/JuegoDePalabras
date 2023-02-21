package juegoDePalabras;


public class Jugador  {
private String name; 
private int ronda;
private ManejoDeJuego conocerRonda;


public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}
	

public int rondaJugador() {
	ronda= conocerRonda.obtenerRonda();
	return ronda;
}
	
	
}
 


