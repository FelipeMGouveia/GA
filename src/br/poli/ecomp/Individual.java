package br.poli.ecomp;

import java.util.ArrayList;
import java.util.List;

public class Individual implements Comparable<Individual>
{
	private List<Binary> data;
	private double output;
	
	private int bitDepth;
	
	public Individual(int dataSize, int bitDepth)
	{
		this.data = new ArrayList<Binary>(dataSize);
		this.bitDepth = bitDepth;
		for(int i = 0; i < dataSize; i++)
		{
			this.data.add(new Binary(bitDepth));
		}
	}

	public Individual(Individual individual) 
	{
		this.data = new ArrayList<Binary>();
		for(Binary bin : individual.getData())
		{
			this.data.add(new Binary(bin));
		}
		this.bitDepth = individual.getBitDepth();
		this.output = individual.getOutput();
	}

	public List<Binary> getData() 
	{
		return data;
	}

	public void setData(List<Binary> data) 
	{
		this.data = data;
	}

	public double getOutput() 
	{
		return output;
	}

	public void setOutput(double output) 
	{
		this.output = output;
	}

	@Override
	public int compareTo(Individual o) 
	{
		Double to = new Double(output);
		Double oo = new Double(o.getOutput());
		return to.compareTo(oo); //Minimos primeiros
		//return oo.compareTo(to); //Máximos primeiros
	}
	
	
	public static void main(String[] args) 
	{
		List<Individual> ind = new ArrayList<Individual>();
		for(int i = 0; i < 10; i++)
		{
			Individual i2 = new Individual(10, 10);
			i2.setOutput(i);
			ind.add(i2);
		}
		
		ind.sort(null);
		
		for(int i = 0; i < 10; i++){
			System.out.println(ind.get(i).output);
		}
	}

	public int getBitDepth() 
	{
		return bitDepth;
	}

	public void setBitDepth(int bitDepth) 
	{
		this.bitDepth = bitDepth;
	}
	
	@Override
	public String toString() 
	{
		String str = "";
		for(Binary bin : this.data)
			str += bin.toString();
		return str;
	}
}
