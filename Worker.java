/*******************************************************************************
/
/      filename:  Worker.java
/
/   description:  Worker class; each thread simulates work and waits at wall
/                 then continues once all threads reach wall
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
class Worker implements Runnable {

    int id;
    Barrier wall;

    Worker (int id, Barrier wall) {
       this.id = id;
       this.wall = wall;
    }

    public void run() {
       work();
    }

    void work() {
       // do some work for a while
       initialWork();
       System.err.println ("Worker " + id + " has finished its initial work.");

       System.err.println ("Worker " + id + " is waiting for the pack.");

       // now wait for others
       wall.waitForOthers();

       // now do some more work
       remainderWork(); 
    }

    void initialWork() {
       System.err.println ("Worker " + id + " is doing its initial work.");
    }

    void remainderWork() {
       System.err.println ("Worker " + id + " is doing its remainder work.");
    }
}