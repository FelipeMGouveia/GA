package br.poli.ecomp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.poli.ecomp.crossover.Crossover;
import br.poli.ecomp.mutation.Mutation;

public class Algorithm 
{
	//Inicializar população
	//=====================
	//Validação
	//Seleção
	//Cruzamento
	//Mutação
	
	private List<Individual> population;
	private int featureLimit;
	private Random rand;
	private Problem problem;
	private Individual best;
	
	private double selectionRate;
	
	private Crossover crossoverOperator;
	
	private Mutation mutationOperator;
	
	private int crossoverIndividuals;
	/**
	 * Indica que ocorreu uma evolução na última validação do algoritmo.
	 */
	private boolean evolution;
	private int epochsWithoutEvolutionLimit;
	
	public Algorithm(int populationSize, int dataSize, int bitDepth, Problem problem, double selectionRate, int epochsWithoutEvolutionLimit, Crossover crossoverOperator, Mutation mutationOperator, int crossoverIndividuals, Random rand)
	{
		this.population = new ArrayList<Individual>(populationSize);
		for(int i = 0; i < populationSize; i++)
		{
			this.population.add(new Individual(dataSize, bitDepth));
		}
		this.rand = rand;
		this.featureLimit = (int) Math.pow(2, bitDepth);
		this.selectionRate = selectionRate;
		this.best = new Individual(dataSize, bitDepth);
		this.best.setOutput(Double.MAX_VALUE);
		this.evolution = false;
		this.epochsWithoutEvolutionLimit = epochsWithoutEvolutionLimit;
		this.crossoverOperator = crossoverOperator;
		this.mutationOperator = mutationOperator;
		
		this.problem = problem;
		
		this.crossoverIndividuals = crossoverIndividuals;
	}
	
	public void init()
	{
		for (int i = 0; i < population.size(); i++) 
		{
			Individual individual = population.get(i);
			for(int j = 0; j < individual.getData().size(); j++)
			{
				Binary feature = individual.getData().get(j);
				feature.setValue(rand.nextInt(featureLimit));
			}
		}
	}
	
	public void validate()
	{
		
		for (int i = 0; i < population.size(); i++) 
		{
			Individual individual = population.get(i);
			List<Integer> input = new ArrayList<Integer>();
			for(int j = 0; j < individual.getData().size(); j++)
			{
				Binary feature = individual.getData().get(j);
				input.add(feature.getValue());
			}
			double output = problem.run(input);
			individual.setOutput(output);
			
			if(output < this.best.getOutput())
			{
				this.best = new Individual(individual);
				this.evolution = true;
			}
		}
	}
	
	public void select()
	{
		population.sort(null);
		population = population.subList(0, (int) (population.size() * selectionRate));
	}
	
	public void crossover()
	{
		crossoverOperator.crossover(population, crossoverIndividuals);
	}
	
	public void mutation()
	{
		mutationOperator.mutate(population);
	}
	
	public void run()
	{
		init();
		int epochsSinceEvolution = 0;
		while(epochsSinceEvolution < epochsWithoutEvolutionLimit)
		{
			validate();
			select();
			crossover();
			mutation();
			epochsSinceEvolution++;
			if(evolution)
			{
				epochsSinceEvolution = 0;
			}
			evolution = false;
		}
	}

	public Individual getBest() 
	{
		return best;
	}
}
