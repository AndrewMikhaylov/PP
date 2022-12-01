package lab1;


public class Thread3 extends Thread{

    private int start;
    private int end;
    private Data data;
    private Monitor monitor;

    int aT3;
    int dT3;
    int pT3;
    int[][] MDT3;

    public Thread3(String name, int N) {
        super(name);

        monitor = new Monitor();
        data=new Data();
        MDT3 = new int[N][N];
        Data.MC=new int[N][N];
        start = Data.N / 2;
        end = (int) Math.floor(3 * (Data.N / (double) 4));
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " has started");

        Data.fillMatrix(Data.MC, "MC");
        data.set_p(Data.enterNumber("p"));

        monitor.signal_1();
        System.out.println("Signal T3 input ended");
        monitor.wait_1();

        aT3 = Data.getLowestValueFromVector(Data.getSubVector(Data.Z, start, end));

        monitor.signal_2();
        System.out.println("T3 a calculations ended");
        monitor.wait_2();

        data.set_a(data.max_a_Value(aT3));
        pT3 =data.get_p();
        dT3 =data.get_d();
        MDT3 =data.get_MD();

        monitor.signal_3();
        System.out.println("T3 a insertion ended");
        monitor.wait_3();

        Data.writeToMA(Data.addMatrixAndMatrix(Data.multiplyNumberByMatrix(Data.multiplyMatrixByMatrix(MDT3, Data.getSubMatrix(Data.MC,start,end)),dT3),
                Data.multiplyNumberByMatrix(Data.getSubMatrix(Data.MX,start,end), Data.multiplyNumberByNumber(aT3,pT3))),start,end);

        monitor.signal_4();
        System.out.println("T3 ended");
    }
}
