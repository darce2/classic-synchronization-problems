/*******************************************************************************
/
/      filename:  Table.java
/
/   description:  Spawns 5 philosophers and the monitor 
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


class Table {
   static final int NUM_PHILOSOPHERS = 5;

   public static void main(String args[]) {
      DiningPhilosophersMonitor dpm =
      new DiningPhilosophersMonitor(NUM_PHILOSOPHERS);

      for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
         System.err.println (i + " just started");
         (new Thread(new Philosopher(i, dpm))).start();
      }
   }
}
