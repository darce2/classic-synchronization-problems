/*******************************************************************************
/
/      filename:  Factory.java
/
/   description:  Factory to spawn worker threads and create barrier instance
/
/        author:  D'Arcy, Arlen  
/      login id:  FA_15_CPS356_21
/
/         class:  CPS 356
/    instructor:  Perugini
/    assignment:  Project #3
/
/      assigned:  Novemeber 5, 2015
/           due:  November 19, 2015
/
******************************************************************************/
class Factory {
   static final int NUM_WORKERS = 10;

   public static void main(String args[]) {
      Barrier wall = new Barrier(NUM_WORKERS);

      for (int i = 0; i < NUM_WORKERS; i++) {
         System.out.println ("Worker " + i + " just started");
         (new Thread(new Worker(i, wall))).start();
      }
   }
}