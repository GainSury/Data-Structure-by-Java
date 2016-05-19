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
			this.character[i] = character_[i];
			
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
	public void createHuffmanTree()
	{
		int i,s[] = new int[2];
		for(i =0;i <symbNum;i++)
			hfNodeTable[i] = new HfNode(codeTable.weight[i],-1,-1,-1);
		for(i = symbNum;i<nodeTotalNum;i++)
		{
			select(i-1,s);
			hfNodeTable[i].lchild = s[0];
			hfNodeTable[i].rchild = s[1];
			hfNodeTable[s[0]].parent = i;
			hfNodeTable[s[1]].parent = i;
			hfNodeTable[i].weight = hfNodeTable[s[0]].weight + hfNodeTable[s[1]].weight;
		}
	}
	
	private void select(int low, int minw[])
	{
		double minL,minR;
		int indexL,indexR;
		minL = minR = Double.MAX_VALUE;
		indexL = indexR = -1;
		for (int i = 0; i <= low; i++)
		{
			if(hfNodeTable[i].parent == -1 && hfNodeTable[i].weight < minL){
				minR = minL;
				indexR = indexL;
				minL = hfNodeTable[i].weight;
				indexL = i;
			}
			else if(hfNodeTable[i].parent == -1 && hfNodeTable[i].weight < minR){
				minR = hfNodeTable[i].weight;
				indexR = i;
				
			}
			minw[0] = indexL;
			minw[1] = indexR;
		}
	}
	
	public String charCode(char ch)
	{
		char[] cd = new char[symbNum];
		int i,start;
		for(i = 0;i < symbNum;i++)
		{
			if(codeTable.character[i] == ch)
				break;
		}
		if(i >= symbNum)
			return null;
		start = symbNum -1;
		int j = i;
		int p = hfNodeTable[i].parent;
		while(p != -1)
		{
			if(hfNodeTable[p].lchild == j)
				cd[--start] = '0';
			else
				cd[--start] = '1';
			j = p;
			p = hfNodeTable[p].parent;
		}
		return new String(cd,start,symbNum-start-1);
		
	}
	
	public void createCodeTable()
	{
		for(int i = 0;i < symbNum;i++)
			codeTable.code[i] = new String(charCode(codeTable.character[i]));
	}
	
	public String HuffmanCode(String str)
	{
		StringBuilder codeStr = new StringBuilder();
		for(int i = 0; i<str.length();i++)
			codeStr = codeStr.append(charCode(str.charAt(i)));
		return new String(codeStr);
	}
	
	
	public String HuffmanDecode(String codestr)
	{
		int p,i=0;
		StringBuilder strb = new StringBuilder();
		while(i < codestr.length())
		{
			p = nodeTotalNum-1;
			while(true)
			{
				if(codestr.charAt(i)=='0'){
					p = hfNodeTable[p].lchild;
				    i++;
				}
				else if(codestr.charAt(i)=='1'){
					p = hfNodeTable[p].rchild;
				    i++; 
				}
				else return null;
				if (hfNodeTable[p].lchild == -1 && hfNodeTable[p].rchild == -1){
					strb = strb.append(codeTable.character[p]);
					break;
							
				}
			}
		}
		return new String(strb);
	}
	
	public void outputTree(){
		System.out.println("===Huffman树===");
	    for(int i =0;i < nodeTotalNum;i++ )
	    {
	    	System.out.println(i + "  | " + hfNodeTable[i]);
	    }
	    System.out.println("===编码表===");
	    System.out.println(codeTable);
	}
	
	
	public static void main(String[] args){
		char[] sym = {'北', 
			          '京',
			          '天',
			          '很',
			          '蓝',
			          '人',
			          '好'};
		double[] weight = {8,8,2,7,3,6,6};
		Huffman ht = new Huffman(sym,weight);
		ht.createHuffmanTree();
		ht.createCodeTable();
		ht.outputTree();
		String x = "北京天很蓝北京人很好很好很蓝";
		String code = ht.HuffmanCode(x);
		System.out.println(code);
		String y ="01110"
				+ "00100"
				+ "00010"
				+ "11001"
				+ "10010"
				+ "01111"
				+ "01001"
				+ "10011"
				+ "01000"
				+ "10001"
				+ "11010"
				+ "00101"
				+ "10011"
				+ "10011"
				+ "1101";
		System.out.println(ht.HuffmanDecode(y));
	}
	
}