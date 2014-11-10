package br.poli.ecomp.selection;

import java.util.List;

import br.poli.ecomp.Individual;

public interface Selection 
{
	public void select(List<Individual> individuals);
}
