package lab1;


public class Thread4 extends Thread{

    private int start;
    private int end;

    public Thread4(String name, int N) {
        super(name);

        Data.D = new int[N];
        Data.MB = new int[N][N];

        start = (int) Math.floor(3 * (Data.N / (double) 4));
        end = (int) Math.floor(Data.N);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " has started");
        try {
            Data.S0.acquire();
            Data.fillVector(Data.D, "D");
            Data.fillMatrix(Data.MB, "MB");
            Data.S0.release();


            Data.S4.release(3); // Надсилання сигнала T1, T2, T3 про закінчення введення D, MB
            Data.S1.acquire(1); // Отримання сигнала від T1 про закінчення введення C, x
            Data.S2.acquire(1); // Отримання сигнала від T2 про закінчення введення B, MA
            Data.S3.acquire(1); // Отримання сигнала від T3 про закінчення введення E

            int ai = Data.multiplyVectors(Data.getSubVector(Data.B, start, end), Data.getSubVector(Data.C, start, end));
            System.out.println("ai T4: " + ai);
            Data.a.addAndGet(ai);

            Data.S8.release(3); // Надсилання сигнала T1, T2, T3 про закінчення обрахунків ai
            Data.S5.acquire(); // Отримання сигнала від T1 про закінчення обрахунків ai
            Data.S6.acquire(); // Отримання сигнала від T2 про закінчення обрахунків ai
            Data.S7.acquire(); // Отримання сигнала від T3 про закінчення обрахунків ai

            int[] Zh = Data.addVectors(Data.multiplyNumberByVector(Data.a.get(), Data.getSubVector(Data.D, start, end)),
                    Data.multiplyNumberByVector(Data.x, Data.multiplyVectorByMatrix(Data.E, Data.multiplyMatrixByMatrix(Data.MA, Data.getSubMatrix(Data.MB, start, end)))));

            Data.S11.acquire(); // Отримання сигнала від T3 про закінчення додавання Zh
            Data.addZ(Zh);
            System.out.println(Thread.currentThread().getName() + " has finished");
            Data.S12.release(); // Надсилання сигнала T1 про закінчення додавання Zh

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
