package com.alura.literatura;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication {

	@Autowired
	private LibroService libroService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			boolean salir = false;

			while (!salir) {
				System.out.println("\nMenú:");
				System.out.println("1. Buscar libro por título");
				System.out.println("2. Listar todos los libros");
				System.out.println("3. Listar autores vivos en un año específico");
				System.out.println("4. Mostrar cantidad de libros en un idioma específico");
				System.out.println("5. Mostrar estadísticas avanzadas (10 libros más descargados)");
				System.out.println("6. Salir");
				System.out.print("Seleccione una opción: ");

				try {
					int opcion = scanner.nextInt();
					scanner.nextLine(); // Consumir el salto de línea

					switch (opcion) {
						case 1:
							System.out.print("Ingrese el título del libro: ");
							String titulo = scanner.nextLine();
							List<Libro> librosPorTitulo = libroService.buscarLibrosPorTitulo(titulo);
							if (librosPorTitulo.isEmpty()) {
								System.out.println("No se encontraron libros con ese título.");
							} else {
								librosPorTitulo.forEach(libro -> System.out.println(libro.getTitulo()));
							}
							break;
						case 2:
							List<Libro> todosLosLibros = libroService.listarTodosLosLibros();
							if (todosLosLibros.isEmpty()) {
								System.out.println("No hay libros almacenados.");
							} else {
								todosLosLibros.forEach(libro -> System.out.println(libro.getTitulo()));
							}
							break;
						case 3:
							System.out.print("Ingrese el año: ");
							Integer anio = scanner.nextInt();
							scanner.nextLine(); // Consumir el salto de línea
							List<Autor> autoresVivos = libroService.listarAutoresVivosEnAnio(anio);
							if (autoresVivos.isEmpty()) {
								System.out.println("No se encontraron autores vivos en ese año.");
							} else {
								autoresVivos.forEach(autor -> System.out.println(autor.getNombre()));
							}
							break;
						case 4:
							System.out.print("Ingrese el idioma: ");
							String idioma = scanner.nextLine();
							List<Libro> librosPorIdioma = libroService.listarLibrosPorIdioma(idioma);
							System.out.println("Cantidad de libros en " + idioma + ": " + librosPorIdioma.size());
							break;
						case 5:
							List<Libro> top10Libros = libroService.obtenerTop10LibrosMasDescargados();
							System.out.println("Top 10 libros más descargados:");
							top10Libros.forEach(libro -> System.out.println(libro.getTitulo() + " - Descargas: " + libro.getDescargas()));
							break;
						case 6:
							salir = true;
							break;
						default:
							System.out.println("Opción inválida. Intente nuevamente.");
					}
				} catch (Exception e) {
					System.out.println("Ocurrió un error: " + e.getMessage());
					scanner.nextLine(); // Consumir el salto de línea restante
				}
			}

			scanner.close();
		};
	}
}