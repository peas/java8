package br.com.casadocodigo.java8;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Capitulo9 {
	public static void main (String... args) {
		int x = 4;
		Runnable oldStyle = new Runnable() {
			public void run() {
				for(int i = 0; i < 10000; i++)
					System.out.println("programa 1 valor " + x);
			}
		};

		Runnable r = () -> {
			for(int i = 0; i < 10000; i++)
				System.out.println("programa 1 valor " + i);
		};

		Runnable r2 = () -> {};

		new Thread(() -> {
			for(int i = 0; i < 10000; i++)
				System.out.println("programa 1 valor " + i);
		}).start();

		int codigo = 5;

		new Thread(() -> {
			for(int i = 0; i < 10000; i++)
				System.out.println("programa " + codigo + " valor " + i);
		}).start();


		
	}
}

