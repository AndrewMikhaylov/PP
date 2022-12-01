package lab1;


public class Thread2 extends Thread{

    private int start;
    private int end;
    private Data data;
    private Monitor monitor;

    int aT2;
    int dT2;
    int pT2;
    int[][] MDT2;

    public Thread2(String name, int N) {
        super(name);

        monitor = new Monitor();
        data=new Data();
        MDT2 = new int[N][N];

        Data.MX=new int[N][N];
        start = Data.N / 4;
        end = Data.N / 2;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " has started");

        Data.fillMatrix(Data.MX, "MX");

        monitor.signal_1();
        System.out.println("Signal T2 input ended");
        monitor.wait_1();

        aT2 = Data.getLowestValueFromVector(Data.getSubVector(Data.Z, start, end));

        monitor.signal_2();
        System.out.println("T2 a calculations ended");
        monitor.wait_2();

        data.set_a(data.max_a_Value(aT2));
        pT2 =data.get_p();
        dT2 =data.get_d();
        MDT2 =data.get_MD();

        monitor.signal_3();
        System.out.println("T2 a insertion ended");
        monitor.wait_3();

        Data.writeToMA(Data.addMatrixAndMatrix(Data.multiplyNumberByMatrix(Data.multiplyMatrixByMatrix(MDT2, Data.getSubMatrix(Data.MC,start,end)),dT2),
                Data.multiplyNumberByMatrix(Data.getSubMatrix(Data.MX,start,end), Data.multiplyNumberByNumber(aT2,pT2))),start,end);

        monitor.signal_4();
        System.out.println("T2 ended");
    }
}
