package lab1;


public class Thread3 extends Thread{

    private int start;
    private int end;

    public Thread3(String name, int N) {
        super(name);

        Data.E = new int[N];

        start = Data.N / 2;
        end = (int) Math.floor(3 * (Data.N / (double) 4));
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " has started");
        try {
            Data.S0.acquire();
            Data.fillVector(Data.E, "E");
            Data.S0.release();


            Data.S3.release(3); // Надсилання сигнала T1, T2, T4 про закінчення введення E
            Data.S1.acquire(1); // Отримання сигнала від T1 про закінчення введення C, x
            Data.S2.acquire(1); // Отримання сигнала від T2 про закінчення введення B, MA
            Data.S4.acquire(1); // Отримання сигнала від T4 про закінчення введення D, MB

            int ai = Data.multiplyVectors(Data.getSubVector(Data.B, start, end), Data.getSubVector(Data.C, start, end));
            System.out.println("ai T3: " + ai);
            Data.a.addAndGet(ai);

            Data.S7.release(3); // Надсилання сигнала T1, T2, T4 про закінчення обрахунків ai
            Data.S5.acquire(); // Отримання сигнала від T1 про закінчення обрахунків ai
            Data.S6.acquire(); // Отримання сигнала від T2 про закінчення обрахунків ai
            Data.S8.acquire(); // Отримання сигнала від T4 про закінчення обрахунків ai

            int[] Zh = Data.addVectors(Data.multiplyNumberByVector(Data.a.get(), Data.getSubVector(Data.D, start, end)),
                    Data.multiplyNumberByVector(Data.x, Data.multiplyVectorByMatrix(Data.E, Data.multiplyMatrixByMatrix(Data.MA, Data.getSubMatrix(Data.MB, start, end)))));

            Data.S10.acquire(); // Отримання сигнала від T2 про закінчення додавання Zh
            Data.addZ(Zh);
            System.out.println(Thread.currentThread().getName() + " has finished");
            Data.S11.release(); // Надсилання сигнала T4 про закінчення додавання Zh

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
