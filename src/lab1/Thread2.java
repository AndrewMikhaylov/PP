package lab1;


public class Thread2 extends Thread{

    private int start;
    private int end;
    public Thread2(String name, int N) {
        super(name);


        Data.B = new int[N];
        Data.MA = new int[N][N];

        start = Data.N / 4;
        end = Data.N / 2;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " has started");
        try {
            Data.S0.acquire();
            Data.fillVector(Data.B, "B");
            Data.fillMatrix(Data.MA, "MA");
            Data.S0.release();


            Data.S2.release(3); // Надсилання сигнала T1, T3, T4 про закінчення введення B, MA
            Data.S1.acquire(1); // Отримання сигнала від T1 про закінчення введення C, x
            Data.S3.acquire(1); // Отримання сигнала від T3 про закінчення введення E
            Data.S4.acquire(1); // Отримання сигнала від T4 про закінчення введення D, MB

            int ai = Data.multiplyVectors(Data.getSubVector(Data.B, start, end), Data.getSubVector(Data.C, start, end));
            System.out.println("ai T2: " + ai);
            Data.a.addAndGet(ai);

            Data.S6.release(3); // Надсилання сигнала T1, T3, T4 про закінчення обрахунків ai
            Data.S5.acquire(); // Отримання сигнала від T1 про закінчення обрахунків ai
            Data.S7.acquire(); // Отримання сигнала від T3 про закінчення обрахунків ai
            Data.S8.acquire(); // Отримання сигнала від T4 про закінчення обрахунків ai

            int[] Zh = Data.addVectors(Data.multiplyNumberByVector(Data.a.get(), Data.getSubVector(Data.D, start, end)),
                    Data.multiplyNumberByVector(Data.x, Data.multiplyVectorByMatrix(Data.E, Data.multiplyMatrixByMatrix(Data.MA, Data.getSubMatrix(Data.MB, start, end)))));

            Data.S9.acquire(); // Отримання сигнала від T1 про закінчення додавання Zh
            Data.addZ(Zh);
            System.out.println(Thread.currentThread().getName() + " has finished");
            Data.S10.release(); // Надсилання сигнала T3 про закінчення додавання Zh

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
