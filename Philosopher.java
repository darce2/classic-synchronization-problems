/*******************************************************************************
/
/      filename:  Philosopher.java
/
/   description:  Run method for philosopher to pick up, eat and putdown sticks
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

import java.lang.Math;

class Philosopher implements Runnable {

   DiningPhilosophersMonitor dpm;
   int id;

   Philosopher (int id, DiningPhilosophersMonitor dpm) {
      this.id = id;
      this.dpm = dpm;
   }

   public void run() {
      System.err.println ("Philosopher " + id + " just started.");

      while (true) {
         dpm.pickupForks(id);
         eat();
         dpm.releaseForks(id);
      }
   }


   private void eat() {
      System.err.println ("Philosopher " + id + " is eating.");
      try { Thread.sleep((int) Math.random()*1000); } catch (Exception e) { }
   }
}
