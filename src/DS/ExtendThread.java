//package DS;
//
// 
//public class ExtendThread {
//    class NewThread extends Thread {
//		   NewThread(Integer[] array,int offset) {
//		      start(); // ��ʼ�߳�
//		   }
//		 
//		   // �ڶ����߳����
//		   public void run() {
//		      
//		   }
//		}
//   public static void main(String args[]) {
//       // ����һ�����߳�
//      try {
//         for(int i = 5; i > 0; i--) {
//            System.out.println("Main Thread: " + i);
//            new NewThread();
//         
//            Thread.sleep(2000);
//         }
//      } catch (InterruptedException e) {
//         System.out.println("Main thread interrupted.");
//      }
//      System.out.println("Main thread exiting.");
//   }
//}