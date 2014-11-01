package br.poli.ecomp;

import java.util.List;
import java.util.Random;

import br.poli.ecomp.crossover.Crossover;
import br.poli.ecomp.crossover.OnePoint;
import br.poli.ecomp.mutation.Mutation;
import br.poli.ecomp.mutation.SimpleMutation;

public class Program implements Problem{

	public static void main(String[] args) 
	{
		for(int i = 0 ;i < 10; i++)
		{
			Random rand = new Random();
			Crossover crossover = new OnePoint(1, rand);
			Mutation mutation = new SimpleMutation(0.01, rand);
			
			Algorithm algo = new Algorithm(20, 2, 8, new Program(), 0.5, 100, crossover, mutation, 10, rand);
			algo.run();
			Individual indi = algo.getBest();
			
			System.out.println(" " + indi.getData().get(0).getValue());
			System.out.println(" " + indi.getData().get(1).getValue());
			System.out.println(indi.getOutput());
		}
	}

	@Override
	public double run(List<Integer> input) 
	{
		int x = input.get(0);
		int y = input.get(1);
		
		return x * x + y * y - 10;
	}

}
