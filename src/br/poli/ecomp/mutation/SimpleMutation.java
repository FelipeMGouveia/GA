package br.poli.ecomp.mutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.poli.ecomp.Binary;
import br.poli.ecomp.Individual;

public class SimpleMutation implements Mutation
{
	private double mutationTax;
	private Random rand;
	
	public SimpleMutation(double mutationTax, Random rand)
	{
		this.mutationTax = mutationTax;
		this.rand = rand;
	}
	
	@Override
	public void mutate(List<Individual> population) 
	{
		for(int i = 0; i < population.size(); i++)
		{
			List<Binary> data = population.get(i).getData();
			for(int j = 0; j < data.size(); j++)
			{
				int[] bin = data.get(j).getBinary();
				for(int k = 0; k < bin.length; k++)
				{
					if(rand.nextDouble() < mutationTax)
					{
						bin[k] = Math.abs(bin[k] - 1); 
					}
				}
			}
		}
	}
	
	public static void main(String[] args) 
	{
		SimpleMutation mut = new SimpleMutation(0.1, new Random());
		List<Individual> population = new  ArrayList<Individual>();
		Individual ind = new Individual(10,10);
		population.add(ind);
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				System.out.print(ind.getData().get(i).getBinary()[j]);
			}
			System.out.println();
		}
		mut.mutate(population);
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				System.out.print(ind.getData().get(i).getBinary()[j]);
			}
			System.out.println();
		}
		
	}
}
