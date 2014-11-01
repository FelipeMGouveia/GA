package br.poli.ecomp.mutation;

import java.util.List;

import br.poli.ecomp.Individual;

public interface Mutation 
{
	void mutate(List<Individual> population);
}
