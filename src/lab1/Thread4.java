package lab1;


public class Thread4 extends Thread{

    private int start;
    private int end;
    private Data data;
    private Monitor monitor;

    int aT4;
    int dT4;
    int pT4;
    int[][] MDT4;

    public Thread4(String name, int N) {
        super(name);

        monitor = new Monitor();
        data=new Data();
        MDT4 = new int[N][N];
        data.set_MD(new int[N][N]);
        Data.Z = new int[N];
        start = (int) Math.floor(3 * (Data.N / (double) 4));
        end = (int) Math.floor(Data.N);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " has started");

        Data.fillMatrix(data.get_MD(), "MD");
        Data.fillVector(Data.Z, "Z");

        monitor.signal_1();
        System.out.println("Signal T4 input ended");
        monitor.wait_1();

        aT4 = Data.getLowestValueFromVector(Data.getSubVector(Data.Z, start, end));

        monitor.signal_2();
        System.out.println("T4 a calculations ended");
        monitor.wait_2();

        data.set_a(data.max_a_Value(aT4));
        pT4=data.get_p();
        dT4=data.get_d();
        MDT4=data.get_MD();

        monitor.signal_3();
        System.out.println("T4 a insertion ended");
        monitor.wait_3();

        Data.writeToMA(Data.addMatrixAndMatrix(Data.multiplyNumberByMatrix(Data.multiplyMatrixByMatrix(MDT4, Data.getSubMatrix(Data.MC,start,end)),dT4),
                                               Data.multiplyNumberByMatrix(Data.getSubMatrix(Data.MX,start,end), Data.multiplyNumberByNumber(aT4,pT4))),start,end);
        monitor.signal_4();
        System.out.println("T4 ended");
    }
}
