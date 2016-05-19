package DS;

import DS.SeqList;

class B2SNode<T extends Comparable<T>> implements Comparable<B2SNode<T>>{

	protected T data;
	protected B2SNode<T> lchild, rchild;
	public B2SNode()
	{
		data = null;
		lchild = null;
		rchild = null;
	}
	
	public B2SNode(T key)
	{
		data = key;
		lchild = null;
		rchild = null;
	}
	
	public B2SNode(T key, B2SNode<T> lchild,B2SNode<T> rchild)
	{
		data = key;
		this.lchild = lchild;
		this.rchild = rchild;
	}
	
	public boolean equals(B2SNode<T> e)
	{
		return data.equals(e);
	}
	
	public String toString()
	{
		return data.toString();
	}
	

	@Override
	public int compareTo(B2SNode<T> arg0) {
		// TODO Auto-generated method stub
		return data.compareTo(arg0.data);
	}
	
}

public class B2STree<T extends Comparable<T>>{
    private int num;
    private B2SNode<T> root;
    public B2STree()
    {
    	root = null;
    	num = 0;
    }
    
    public B2STree(B2SNode<T> root)
    {
    	this.root = root;
    	num = 0;
    }
    
    public int size()
    {
    	return num;
    }
    
    
    
    //先序遍历 输出 二叉查找树
    public SeqList<T> preOrder()
    {
    	SeqList<T> preSeq = new SeqList<T>(size());
    	preOrderByRecursion(root,preSeq);
    	return preSeq;
    }
    
    private void preOrderByRecursion(B2SNode<T> root,SeqList<T> preSeq)
    {
    	if(root != null)
    	{
    		preSeq.add(root.data);
    		preOrderByRecursion(root.lchild,preSeq);
    		preOrderByRecursion(root.rchild,preSeq);
    	}
    }
    
    public SeqList<T> inOrder()
    {
    	SeqList<T> inSeq = new SeqList<T>(size());
    	inOrderByRecursion(root,inSeq);
    	return inSeq;
    }
    
    private void inOrderByRecursion(B2SNode<T> root,SeqList<T> inSeq)
    {
    	if(root != null)
    	{
    		inOrderByRecursion(root.lchild,inSeq);
    		inSeq.add(root.data);
    		
    		inOrderByRecursion(root.rchild,inSeq);
    	}
    }
    
    public SeqList<T> postOrder()
    {
    	SeqList<T> postSeq = new SeqList<T>(size());
    	postOrderByRecursion(root,postSeq);
    	return postSeq;
    }
    
    private void postOrderByRecursion(B2SNode<T> root,SeqList<T> postSeq)
    {
    	if(root != null)
    	{
    		postOrderByRecursion(root.lchild,postSeq);
    		postOrderByRecursion(root.rchild,postSeq);
    		postSeq.add(root.data);
    	}
    }
    
    public String toString()
    {
    	return inOrder().toString();
    }
    
    public T max()
    {
    	B2SNode<T> maxNode = findMax(this.root);
    	if(maxNode == null)
    		return null;
    	else return maxNode.data;
    }
    
    protected B2SNode<T> findMax(B2SNode<T> node)
    {
    	if(node != null)
    		while(node.rchild != null)
    			node = node.rchild;
    	return node;
    }
    
    public T min()
    {
    	B2SNode<T> minNode = findMin(this.root);
    	if(minNode == null)
    		return null;
    	else return minNode.data;
    }
    
    protected B2SNode<T> findMin(B2SNode<T> node)
    {
    	if(node != null)
    		while(node.lchild != null)
    			node = node.lchild;
    	return node;
    }   
 
    
    public T search(T key)
    {
    	B2SNode<T> d;
    	d = searchByRecursion(this.root,key);
    	if(d == null)
    		return null;
    	else
    		return d.data;
    }
    
    public boolean contains(T key)
    {
        if(searchByRecursion(this.root,key) == null)
        	return false;
        else 
        	return true;
    }
    
    public B2SNode<T> searchByRecursion(B2SNode<T> root, T key)
    {
    	if(root == null)
    		return null;
    	int cmp = key.compareTo(root.data);
    	if(cmp == 0)
    		return root;
    	else if(cmp < 0)
    		return searchByRecursion(root.lchild,key);
    	else
    		return searchByRecursion(root.rchild,key);
    	
    }
    
    public void add(T e)
    {
    	this.root = insertByRecur(this.root,e);
    }
    
    private B2SNode<T> insertByRecur(B2SNode<T> node,T key)
    {
    	
    	if(node == null)
    	{
    		B2SNode<T> p = new B2SNode<T>(key);
    	    this.num++;
    	    return p;
    	}
    	else{
    		if(key.compareTo(node.data) < 0)
    			node.lchild = insertByRecur(node.lchild,key);
    		else if(key.compareTo(node.data) > 0)
    			node.rchild = insertByRecur(node.rchild,key);
    		return node;
    	}
    }
    
    public void remove(T key)
    {
        root = deleteByRecur(this.root, key);	
    }
    
    private B2SNode<T> deleteByRecur(B2SNode<T> node,T key)
    {
    	B2SNode<T> s = null;
    	if(node == null)
    		return null;
    	else if(key.compareTo(node.data) < 0)
			node.lchild = deleteByRecur(node.lchild,key);
    	else if(key.compareTo(node.data) > 0)
			node.rchild = deleteByRecur(node.rchild,key);
    	else if(node.lchild != null && node.rchild != null)
    	{
    		s = findMin(node.rchild);
    		node.data = s.data;
    		node.rchild = deleteByRecur(node.rchild,s.data);
    	}
    	else{
    		if (node.lchild == null)
    			node = node.rchild;
    		else if (node.rchild == null)
    			node = node.lchild;
    		num--;
    	}
    	return node;
    	
    }
    
    
    public static void main(String[] args)
    {
    	B2STree<Integer> test = new B2STree<Integer>();
    	for(int i = 0;i < 500;i++)
    		test.add(i);
    	System.out.print(test);
    	for(int i = 0;i < 500;i++){
    		if(i %50 == 0)
    			System.out.println(test.search(i));
    	}
    	for(int i = 0;i < 500;i++){
    		test.remove(i);
    		if(i %50 == 0)
    			System.out.println(test);
    	}
    }
    
    
    
}
