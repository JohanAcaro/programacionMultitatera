package com.clases;

import java.time.LocalDateTime;
import java.util.Random;

public class Examinador implements Runnable {
 	private Thread hilo;
 	BufferExamenes buffer;
 	private static int numeroExamen = 0;
 	public Thread getHilo() {
           	return hilo;
 	}

 	public Examinador(String alumno, BufferExamenes generador) {
 		numeroExamen++;
           	// Construye el hilo. El nombre será el nombre del alumno.
 		hilo = new Thread(this,alumno);

           	// Establece el valor de la propiedad buffer
 		this.buffer = generador;

           	// Inicia el hilo.
 		hilo.start();

 	}
 	
 	@Override
 	public void run() {
           	String codigoExamen = this.buffer.consumirExamen();
           	if (codigoExamen != null) {
           		int aa = LocalDateTime.now().getYear();
                // Generación del código de examen.
                String codigo= "E"+numeroExamen+"-" +aa+";"+this.hilo.getName();
                Random r_method = new Random();
           			// Simula aquí un examen de 10 preguntas
                    // cuyas respuestas se seleccionarán al azar
                    // entre A, B, C, D o – (sin contestar).
                String respuestas[]= {"A","B","C","D","-"};
                
                for (int j = 1; j <= 10; j++) {
                	int e = r_method.nextInt(respuestas.length);
					System.out.println(codigo+";Pregunta "+j+";"+respuestas[e]);
				}
           		

           	}
           	else {
                    	System.out.println("Agotado tiempo de espera y " +
                    			"no hay más exámenes");
           	}
 	}
}
