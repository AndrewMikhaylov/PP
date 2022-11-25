package lab1;



import java.util.Scanner;

public class Lab1 {


    public static void main(String[] args) {
        System.out.println("Main thread has started");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter dimension: ");
        Data.N = scanner.nextInt();


        Thread1 T1 = new Thread1("T1", Data.N);
        Thread2 T2 = new Thread2("T2", Data.N);
        Thread3 T3 = new Thread3("T3", Data.N);
        Thread4 T4 = new Thread4("T4", Data.N);

        T1.start();
        T2.start();
        T3.start();
        T4.start();

        try{
            T1.join();
            T2.join();
            T3.join();
            T4.join();
        }
        catch(InterruptedException e){
            System.out.print(e.getMessage());
        }
        System.out.println("Main thread has finished");
        System.out.println("A = " + Data.a);
    }
}
