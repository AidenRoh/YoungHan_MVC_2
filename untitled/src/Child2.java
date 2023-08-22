public class Child2 extends Parent {

    public int d;

    static int forA;


    public Child2(String forB, int d) {
        super(++forA, forB);
        this.d = d;
    }
}
