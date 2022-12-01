package lab1;

public class Monitor {
    private static int F1 =0;
    private static int F2 =0;
    private static int F3 =0;
    private static int F4 =0;

    public synchronized void wait_1(){
            try{
                if (F1 !=4) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    public synchronized void signal_1(){
        F1++;
        System.out.println("F1 is now " + F1);
        if (F1 ==4) {
            notifyAll();
        }
    }

    public synchronized void wait_2(){
            try {
                if (F2 !=4) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    public synchronized void signal_2(){
        F2++;
        System.out.println("F2 is now " + F2);
        if (F2 ==4) {
            notifyAll();
        }
    }

    public synchronized void wait_3(){
            try{
                if (F3 !=4) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    public synchronized void signal_3(){
        F3++;
        System.out.println("F3 is now " + F3);
        if (F3 ==4) {
            notifyAll();
        }
    }
    public synchronized void wait_4(){
        try{
            if (F4 !=4) {
                wait();
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    public synchronized void signal_4(){
        F4++;
        System.out.println("F4 is now " + F4);
        if (F4 == 4) {
            notifyAll();
        }
    }
}
