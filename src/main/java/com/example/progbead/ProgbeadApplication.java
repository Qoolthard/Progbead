package com.example.progbead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.progbead.repository.*;

import java.util.Scanner;

@SpringBootApplication
public class ProgbeadApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("progbead");

		boolean nevek;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Adjon meg egy új nevet és nyomjon entert\nVagy kiírja a korábban beírt összes nevet [kiir] és nyomjon entert\nVagy kitörölni a régi adatbázist, és újat létrehozni? [del] és nyomjon entert\nVagy kilépni? [kilep] és nyomjon entert");
			String nname = scanner.nextLine();
			if (nname.equals("kiir")) {
				DbRead.main();
				nevek = true;
			}
			else if (nname.equals("kilep")) {
				nevek = false;
			}
			else if (nname.equals("del")) {
				DbDelete.main();
				DatabaseCreate.main();
				nevek = true;
			}
			else {
				DbInsert.main(nname);
				nevek = true;
			}
		} while (nevek == true);
	}
}
