package lab1;


public class Thread1 extends Thread{

    private int start;
    private int end;

    public Thread1(String name, int N) {
        super(name);

        Data.C = new int[N];

        end = Data.N / 4;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " has started");
        try {
            Data.S0.acquire();
            Data.fillVector(Data.C, "C");
            Data.x = Data.enterNumber("x");
            Data.S0.release();


            Data.S1.release(3); // Надсилання сигнала T2, T3, T4 про закінчення введення C, x
            Data.S2.acquire(1); // Отримання сигнала від T2 про закінчення введення B, MA
            Data.S3.acquire(1); // Отримання сигнала від T3 про закінчення введення E
            Data.S4.acquire(1); // Отримання сигнала від T4 про закінчення введення D, MB

            int ai = Data.multiplyVectors(Data.getSubVector(Data.B, start, end), Data.getSubVector(Data.C, start, end));
            System.out.println("ai T1: " + ai);
            Data.a.addAndGet(ai);

            Data.S5.release(3); // Надсилання сигнала T2, T3, T4 про закінчення обрахунків ai
            Data.S6.acquire(); // Отримання сигнала від T2 про закінчення обрахунків ai
            Data.S7.acquire(); // Отримання сигнала від T3 про закінчення обрахунків ai
            Data.S8.acquire(); // Отримання сигнала від T4 про закінчення обрахунків ai

            int[] Zh = Data.addVectors(Data.multiplyNumberByVector(Data.a.get(), Data.getSubVector(Data.D, start, end)),
                    Data.multiplyNumberByVector(Data.x, Data.multiplyVectorByMatrix(Data.E, Data.multiplyMatrixByMatrix(Data.MA, Data.getSubMatrix(Data.MB, start, end)))));

            Data.addZ(Zh);
            Data.S9.release(); // Надсилання сигнала T2 про закінчення додавання Zh


            Data.S12.acquire(); // Отримання сигнала від T4 про закінчення додавання Zh
            System.out.print("Z = ");
            Data.printVector(Data.Z);
            System.out.println(Thread.currentThread().getName() + " has finished");

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
