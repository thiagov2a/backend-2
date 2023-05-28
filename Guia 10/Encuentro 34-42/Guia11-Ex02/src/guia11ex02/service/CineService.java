/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guia11ex02.service;

import guia11ex02.entity.Asiento;
import guia11ex02.entity.Espectador;
import guia11ex02.entity.Pelicula;
import guia11ex02.entity.Sala;
import guia11ex02.enums.Apellido;
import guia11ex02.enums.Columna;
import guia11ex02.enums.Nombre;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Thiago
 */
public class CineService {

    private final int filas = 8;
    private final int columnas = 6;

    private final Scanner input;
    private final List<Sala> salas;
    private final List<Espectador> espectadores;

    public CineService() {
        this.input = new Scanner(System.in).useDelimiter("\n");
        this.salas = new ArrayList<>();
        this.espectadores = new ArrayList<>();
    }

    public void crearSala() {
        Asiento[][] asientos = generarAsientos();
        Pelicula pelicula = generarPelicula();

        System.out.print("Ingrese precio de la sala.\n> $");
        Double precio = validarDouble();

        salas.add(new Sala(asientos, pelicula, precio));
    }

    private Asiento[][] generarAsientos() {
        Asiento[][] asientos = new Asiento[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String ubicacion = (filas - i) + Columna.values()[j].toString();
                asientos[i][j] = new Asiento(ubicacion);
            }
        }
        return asientos;
    }

    private Pelicula generarPelicula() {
        System.out.print("Ingrese titulo de la pelicula.\n> ");
        String titulo = validarString();

        System.out.print("Ingrese duracion de la pelicula en horas.\n> ");
        Integer duracion = validarEntero();

        System.out.print("Ingresar edad minima de la pelicula.\n> ");
        Integer edadMinima = validarEntero();

        System.out.print("Ingrese nombre del director de la pelicula.\n> ");
        String director = validarString();

        return new Pelicula(titulo, duracion, edadMinima, director);
    }

    public void generarEspectadores() {
        int cantidad = (int) (Math.random() * (filas * columnas));
        System.out.println(cantidad);

        for (int i = 0; i < cantidad; i++) {
            String nombre = generarNombre();
            Integer edad = (int) (Math.random() * 80) + 1;
            Double dinero = Math.random() * 5000;

            espectadores.add(new Espectador(nombre, edad, dinero));
        }
        for (Espectador espectador : espectadores) {
            System.out.println(espectador);
        }
    }

    private String generarNombre() {
        int indiceNombre = (int) (Math.random() * Nombre.values().length);
        String nombre = Nombre.values()[indiceNombre].toString();

        int indiceApellido = (int) (Math.random() * Apellido.values().length);
        String apellido = Apellido.values()[indiceApellido].toString();

        return nombre + " " + apellido;
    }

    private int validarEntero() {
        while (true) {
            try {
                return Integer.parseInt(input.next());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero válido.\n> ");
            }
        }
    }

    private double validarDouble() {
        while (true) {
            try {
                String entrada = input.next().replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido.\n> ");
            }
        }
    }

    private String validarString() {
        String entrada = input.next();
        while (entrada.isEmpty()) {
            System.out.print("Ingrese un valor válido.\n> ");
            entrada = input.next();
        }
        return entrada;
    }

}
