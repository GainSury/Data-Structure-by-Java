package DS;

class Huffman
{

	int num;
	char[] character;
	double[] weigh;
	String[] code;
	public Huffman(char[] character_,double[] weigh_){
		num = character_.length;
		this.character = new char[num];
		this.weigh = new double[num];
		this.code = new String[num];
		for(int i =0;i < num;i++)
		{
			this.weigh[i] = weigh_[i];
			this.character[i] = character[i];
			
		}
	}
	public String toString()
	{
		StringBuilder strb = new StringBuilder();
		strb = strb.append("symbol" + "   "+"weight" + "   "+"code\n");
		for(int i =0;i < num;i++)
			strb = strb.append(String.format(" %-7c    %9.1f     %s\n", 
					                         character[i],
					                         weigh[i],
					                         code[i]));
		return new String(strb);
	}
}
