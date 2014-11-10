package br.poli.ecomp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.poli.ecomp.crossover.Crossover;
import br.poli.ecomp.mutation.Mutation;
import br.poli.ecomp.selection.Selection;

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
	
	private Crossover crossoverOperator;
	
	private Mutation mutationOperator;
	
	/**
	 * Indica que ocorreu uma evolução na última validação do algoritmo.
	 */
	private boolean evolution;
	private int epochsWithoutEvolutionLimit;
	private Selection selectionOperator;
	
	public Algorithm(int populationSize, int bitDepth, Problem problem, int epochsWithoutEvolutionLimit, Selection selectionOperator, Crossover crossoverOperator, Mutation mutationOperator, Random rand)
	{
		this.population = new ArrayList<Individual>(populationSize);
		for(int i = 0; i < populationSize; i++)
		{
			this.population.add(new Individual(problem.getDimensions(), bitDepth));
		}
		this.rand = rand;
		this.featureLimit = (int) Math.pow(2, bitDepth);
		this.best = new Individual(problem.getDimensions(), bitDepth);
		this.best.setOutput(Double.MAX_VALUE);
		this.evolution = false;
		this.epochsWithoutEvolutionLimit = epochsWithoutEvolutionLimit;
		this.selectionOperator = selectionOperator;
		this.crossoverOperator = crossoverOperator;
		this.mutationOperator = mutationOperator;

		this.problem = problem;
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
		selectionOperator.select(population);
	}
	
	public void crossover()
	{
		crossoverOperator.crossover(population);
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
