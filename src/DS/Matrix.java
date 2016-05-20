package DS;

public interface Matrix {
    public int rows();
    public int cols();
    public int size();
    public void set(int i,int j,double value);
    public double get(int i,int j);
    public void add(int i,int j,double value);
    public  Triple remove(int i,int j);
    public Matrix transpose();
    
//    public Matrix plus(Matrix B);
//    public Ma
}
