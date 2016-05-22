//package DS;
//
// 
//public class ExtendThread {
//    class NewThread extends Thread {
//		   NewThread(Integer[] array,int offset) {
//		      start(); // 开始线程
//		   }
//		 
//		   // 第二个线程入口
//		   public void run() {
//		      
//		   }
//		}
//   public static void main(String args[]) {
//       // 创建一个新线程
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