package br.poli.ecomp;

public class Binary 
{
	//LSB na posição 0
	private int[] binary;
	private int limit;
	
	public Binary(int size)
	{
		this.binary = new int[size];
		this.limit = (int) Math.pow(2, this.binary.length);
	}
	
	public Binary(Binary bin) 
	{
		this.binary = new int[bin.binary.length];
		this.limit = bin.limit;
		for(int i = 0; i < this.binary.length; i++)
		{
			this.binary[i] = bin.binary[i];
		}
	}

	public int getValue()
	{
		int result = 0;
		
		for(int i = 0; i < binary.length; i++)
		{
			result += binary[i] * Math.pow(2, i);
		}
		
		return result;
	}
	
	public void setValue(int value)
	{
		if( value > this.limit)
			throw new IllegalArgumentException("Overflow " + value + " " + this.limit);
		for(int i = 0; i < this.binary.length; i++)
		{
			this.binary[i] = value % 2;
			value = value / 2;
		}
	}

	public int[] getBinary() {
		return binary;
	}

	public void setBinary(int[] binary) {
		this.binary = binary;
	}

	@Override
	public String toString() 
	{
		String str = "";
		for(int b : binary)
			str += b;
		return str;
	}
	
	public static void main(String[] args) 
	{
		Binary bin = new Binary(4);
		bin.setValue(17);
		for (int i = 0; i < bin.getBinary().length; i++) 
		{
			System.out.print(bin.getBinary()[i]);
		}
	}
}
