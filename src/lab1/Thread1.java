package lab1;


public class Thread1 extends Thread{

    private int start;
    private int end;
    private Data data;
    private Monitor monitor;

    int aT1;
    int dT1;
    int pT1;
    int[][] MDT1;

    public Thread1(String name, int N) {
        super(name);

        monitor = new Monitor();
        data=new Data();
        MDT1 = new int[N][N];

        Data.MA=new int[N][N];
        end = Data.N / 4;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " has started");

        Data.fillMatrix(Data.MA, "MA");
        data.set_d(Data.enterNumber("d"));

        monitor.signal_1();
        System.out.println("Signal T1 input ended");
        monitor.wait_1();

        aT1 = Data.getLowestValueFromVector(Data.getSubVector(Data.Z, start, end));

        monitor.signal_2();
        System.out.println("T1 a calculations ended");
        monitor.wait_2();

        data.set_a(data.max_a_Value(aT1));
        pT1 =data.get_p();
        dT1 =data.get_d();
        MDT1 =data.get_MD();

        monitor.signal_3();
        System.out.println("T1 a insertion ended");
        monitor.wait_3();

        Data.writeToMA(Data.addMatrixAndMatrix(
                Data.multiplyNumberByMatrix(Data.multiplyMatrixByMatrix(MDT1, Data.getSubMatrix(Data.MC,start,end)),dT1),
                            Data.multiplyNumberByMatrix(Data.getSubMatrix(Data.MX,start,end),
                                Data.multiplyNumberByNumber(aT1,pT1))),start,end);

        monitor.signal_4();
        System.out.println("T1 ended");
        monitor.wait_4();

        System.out.println("MA:");
        Data.printMatrix(Data.MA);
    }
}