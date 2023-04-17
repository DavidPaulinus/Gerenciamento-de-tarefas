package br.com.gerenciador.service.util;

public class Conversor {
	public static String converter(Boolean boo) {
		if(boo) return "Concluído";
		return "A fazer";
	}
}
