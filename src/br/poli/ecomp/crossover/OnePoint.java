package br.poli.ecomp.crossover;

import java.util.List;
import java.util.Random;

import br.poli.ecomp.Binary;
import br.poli.ecomp.Individual;

public class OnePoint implements Crossover 
{
	private int crossoverIndividuals;
	private int point;
	private Random rand;
	
	public OnePoint(int point, int crossoverIndividuals, Random rand)
	{
		this.crossoverIndividuals = crossoverIndividuals;
		this.point = point;
		this.rand = rand;
	}
	
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public void crossover(List<Individual> population) 
	{
		int size = population.get(0).getData().size();// * population.get(0).getBitDepth();
		for(int i = 0; i < crossoverIndividuals / 2; i++)
		{
			Individual father = population.get((int) ((population.size() - 1) * rand.nextDouble()));
			Individual mother = population.get((int) ((population.size() - 1)* rand.nextDouble()));
			Individual newIndividual1 = new Individual(father.getData().size(), father.getBitDepth());
			Individual newIndividual2 = new Individual(father.getData().size(), father.getBitDepth());
			
			for(int j = 0; j < point; j++)
			{
				newIndividual1.getData().set(j, new Binary(father.getData().get(j)));
				newIndividual2.getData().set(j, new Binary(mother.getData().get(j)));
			}
			
			for(int j = point; j < size; j++)
			{
				newIndividual1.getData().set(j, new Binary(mother.getData().get(j)));
				newIndividual2.getData().set(j, new Binary(father.getData().get(j)));
			}

			
			population.add(newIndividual1);
			population.add(newIndividual2);
		}
	}
}
