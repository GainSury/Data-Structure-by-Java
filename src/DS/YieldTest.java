package DS;

public class YieldTest{

	static class mergeThread1 extends Thread {
		
	    mergeThread1(String name) {
	    	super(name);
		  
	    }
	   // �߳����
	   public void run() 
	   {
		   for (int i = 1; i <= 50; i++) {
			   for(double j =0;j < Integer.MAX_VALUE;j++);
				
			   if (i == 3) {
					Thread.yield();
				}
			   System.out.println("" + this.getName() + "-----" + i);
				
				// ��iΪ30ʱ�����߳̾ͻ��CPUʱ���õ��������������Լ����߳�ִ�У�Ҳ����˭������˭ִ�У�
//				if (i == 2) {
//					this.yield();
//				}
			}
	   }
		   
	}
	
	static class  mergeThread2 extends Thread {
		
	    mergeThread2(String name) {
	    	super(name);
		   
	    }
	   // �߳����
	   public void run() 
	   {
		   for (int i = 1; i <= 50; i++) {
			   for(double j =0;j < Integer.MAX_VALUE;j++);
			   
			   
				System.out.println("" + this.getName() + "-----" + i);

			}
	   }
		   
	}

	public static void main(String[] args) {
		mergeThread1 yt1 = new mergeThread1("1");
		mergeThread2 yt2 = new mergeThread2("2");
		yt1.start();
		
		for(double j =0;j < Integer.MAX_VALUE/2;j++);
		yt2.start();
	}
}