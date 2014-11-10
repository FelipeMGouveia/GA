package br.poli.ecomp.crossover;

import java.util.List;

import br.poli.ecomp.Individual;

public interface Crossover 
{
	void crossover(List<Individual> population);
}
