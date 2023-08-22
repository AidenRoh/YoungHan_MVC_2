public class Child1 extends Parent{

    public int c;

    static int forA;



    public Child1(String forB, int c) {
        super(++forA, forB);
        this.c = c;
    }
}
