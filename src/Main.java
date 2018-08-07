

public class Main {
    public static void main(String[] arg){
        System.out.println();
        test2 a = new test2(String.valueOf("1946"));
        System.out.println(a.isBornBoomer());
        System.out.println(String.valueOf("1946"));
    }
}

class test2 {
    private String birthString;
    private static String startString, endString;

    static {

        startString = String.valueOf("1946");
        endString = String.valueOf("1964");
    }

    public test2(String birthString) {
        this.birthString = birthString;
    }

    boolean isBornBoomer() {
        return birthString.compareTo(startString) >= 0 && birthString.compareTo(endString) < 0;
    }

    public static void main(String[] args)
    {
        test2 a= new test2(String.valueOf("1955"));
        System.out.println(a.isBornBoomer());
    }
}