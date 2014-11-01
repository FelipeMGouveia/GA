package br.poli.ecomp;

import java.util.List;

public interface Problem 
{
	/**
	 * Avalia o problema para determinada entrada.
	 * @param input entrada para o problema.
	 * @return saída obtida.
	 */
	public double run(List<Integer> input);
}
