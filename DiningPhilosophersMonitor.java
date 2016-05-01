/*******************************************************************************
/
/      filename:  DiningPhilosophersMonitor.java
/
/   description:  Monitor for philsopher, controls the picking up and 
/                 dropping of forks to prevent starvation and deadlock
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

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class DiningPhilosophersMonitor implements DiningServer {

   int num_philosophers;

   enum State {THINKING, HUNGRY, EATING};
   State state[];

   int num_of_eats;
   int eat_freq[];

   Lock lock;
   Condition self[];

   DiningPhilosophersMonitor(int num_philosophers) {
      lock = new ReentrantLock();
      this.num_philosophers = num_philosophers;

      num_of_eats = 0;
      eat_freq = new int[num_philosophers];

      for (int i=0; i < eat_freq.length; i++)
         eat_freq[i] = 0;

      state = new State[num_philosophers];
      self = new Condition[num_philosophers];

      for (int i=0; i < num_philosophers; i++) {

         self[i] = lock.newCondition();

         // all philosophers start in the thinking state
         state[i] = State.THINKING;
      }
   }

   public void pickupForks (int id) {
      lock.lock();
      state[id] = State.HUNGRY;
      test(id);
      if (state[id] != State.EATING)
         try { self[id].await(); } 
         catch (InterruptedException ie) { }
      num_of_eats++;
      eat_freq[id]++;

      if (num_of_eats % 100 == 0)
         for (int i=0; i < eat_freq.length; i++)
            System.err.println ("philosopher " + i + ": " +  eat_freq[i]);
      
      lock.unlock();
   }

   public void releaseForks (int id) {
      lock.lock();
      state[id] = State.THINKING;
      // test left and right neighbors
      test((id+(num_philosophers-1)) % num_philosophers);
      test((id+1) % num_philosophers);

      lock.unlock();
   }

   private void test (int id) {

      int left_neighbor = (id+(num_philosophers-1)) % num_philosophers;
      int right_neighbor = (id+1) % num_philosophers;
      //check to ensure no deadlock or starvation
      if ( (state[id] == State.HUNGRY) &&
           (state[left_neighbor] != State.EATING) &&
           (state[right_neighbor] != State.EATING) &&
           (eat_freq[left_neighbor] >= eat_freq[id]) &&
           (eat_freq[right_neighbor] >= eat_freq[id]) ) {

         state[id] = State.EATING;
         self[id].signal();
      }
   }
}
