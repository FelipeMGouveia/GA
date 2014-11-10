package br.poli.ecomp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import br.poli.ecomp.crossover.Crossover;
import br.poli.ecomp.crossover.OnePoint;
import br.poli.ecomp.mutation.Mutation;
import br.poli.ecomp.mutation.SimpleMutation;
import br.poli.ecomp.selection.Selection;
import br.poli.ecomp.selection.SimpleSelection;

public class Program implements Problem
{

	public static void main(String[] args)
	{
		int bitsPerVariable = 8;
		int epochsWithoutBest = 100;
		int populationSize = 20;
		int crossoverPoint = 1;
		int crossoverNewIndividuals = 10;
		double mutationRate = 0.01;
		double selectionRate = 0.5;
		int times = 10;
	
		for(String arg : args)
		{
			if(arg.startsWith("bitsPerVariable"))
			{
				bitsPerVariable = Integer.parseInt(arg.split("=")[1]);
			}
			
			if(arg.startsWith("epochsWithoutBest"))
			{
				epochsWithoutBest = Integer.parseInt(arg.split("=")[1]);
			}
			
			if(arg.startsWith("populationSize"))
			{
				populationSize = Integer.parseInt(arg.split("=")[1]);
			}
			
			if(arg.startsWith("crossoverPoint"))
			{
				crossoverPoint = Integer.parseInt(arg.split("=")[1]);
			}
			
			if(arg.startsWith("crossoverNewIndividuals"))
			{
				crossoverNewIndividuals = Integer.parseInt(arg.split("=")[1]);
			}
			
			if(arg.startsWith("mutationRate"))
			{
				mutationRate = Double.parseDouble(arg.split("=")[1]);
			}
			
			if(arg.startsWith("selectionRate"))
			{
				selectionRate = Double.parseDouble(arg.split("=")[1]);
			}
			
			if(arg.startsWith("times"))
			{
				times = Integer.parseInt(arg.split("=")[1]);
			}
		}
		
		File resultFile = new File("GA_Result_File_"+ System.nanoTime() + ".txt");
		try 
		{
			FileWriter fw = new FileWriter(resultFile, false);
			fw.write("bitsPerVariable " + bitsPerVariable + "\n");
			fw.write("epochsWithoutBest " + epochsWithoutBest + "\n");
			fw.write("populationSize " + populationSize + "\n");
			fw.write("crossoverPoint " + crossoverPoint + "\n");
			fw.write("crossoverNewIndividuals " + crossoverNewIndividuals + "\n");
			fw.write("mutationRate " + mutationRate + "\n");
			fw.write("selectionRate " + selectionRate + "\n");
			fw.write("times" + times + "\n");
			for(int i = 0 ;i < times; i++)
			{
				Random rand = new Random();
				Crossover crossover = new OnePoint(crossoverPoint, crossoverNewIndividuals, rand);
				Mutation mutation = new SimpleMutation(mutationRate, rand);
				Selection selection = new SimpleSelection(selectionRate);
				
				Algorithm algo = new Algorithm(populationSize, bitsPerVariable, new Program(), epochsWithoutBest, selection, crossover, mutation, rand);
				algo.run();
				Individual indi = algo.getBest();

				for(Binary bin : indi.getData())
				{
					fw.write("Var: " + bin.getValue() + "\n");
				}
				
				fw.write("Output: "+indi.getOutput() + "\n");
			}
			fw.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public double run(List<Integer> input) 
	{
		int x = input.get(0) - 10;
		int y = input.get(1);
		return  (x * x) + (y * y);
	}

	@Override
	public int getDimensions() 
	{
		return 2;
	}
}

