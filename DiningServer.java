/*******************************************************************************
/
/      filename:  DiningServer.java
/
/   description:  interface for Philosopher
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

interface DiningServer {
   // called by a philosopher when the philosopher get hungry
   public void pickupForks(int id);

   // called by a philosopher when the philosopher is finished eating
   public void releaseForks(int id);
}
