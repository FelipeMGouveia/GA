package br.poli.ecomp.selection;

import java.util.ArrayList;
import java.util.List;

import br.poli.ecomp.Individual;

public class SimpleSelection implements Selection 
{
	private double selectionRate;
	
	public SimpleSelection(double selectionRate) 
	{
		super();
		this.selectionRate = selectionRate;
	}

	@Override
	public void select(List<Individual> population) 
	{
		population.sort(null);
		List<Individual> population2 = new ArrayList<Individual>(population.subList(0, (int) (population.size() * selectionRate)));
		population.clear();
		population.addAll(population2);
	}
}
