package DS;

class CodeTable
{

	int num;
	char[] character;
	double[] weight;
	String[] code;
	CodeTable(char[] character_,double[] weight_){
		num = character_.length;
		this.character = new char[num];
		this.weight = new double[num];
		this.code = new String[num];
		for(int i =0;i < num;i++)
		{
			this.weight[i] = weight_[i];
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
					                         weight[i],
					                         code[i]));
		return new String(strb);
	}
}


class HfNode
{
	public double weight;
	public int parent,lchild,rchild;
	public HfNode(double weight_,int parent_,int lchild_,int rchild_)
	{
		weight = weight_;
		parent = parent_;
		lchild = lchild_;
		rchild = rchild_;
		
	}
	
	public String toString()
	{
		String str;
		str = String.format("%5.1f    %3d    %3d   %3d", 
				            weight,parent,lchild,rchild);
		return str;
	}
}



public class Huffman
{
	private int symbNum,nodeTotalNum;
	private HfNode[] hfNodeTable;
	private CodeTable codeTable;
	Huffman(char[] character_, double[] weight_)
	{
        symbNum = character_.length;
        nodeTotalNum = symbNum * 2 - 1;
        codeTable = new CodeTable(character_, weight_);
        hfNodeTable = new HfNode[nodeTotalNum];
        for(int i = 0; i < nodeTotalNum; i++)
        {
        	hfNodeTable[i] = new HfNode(0,-1,-1,-1);
        }
	}
	
}