import javax.xml.crypto.Data;
import java.sql.Date;


public class test2 {
    private Date birthDate;
    private static Date startDate, endDate;

    static {
        startDate = Date.valueOf("1946");
        endDate = Date.valueOf("1964");
    }

    public test2(Date birthDate) {
        this.birthDate = birthDate;
    }

    boolean isBornBoomer() {
        return birthDate.compareTo(startDate) >= 0 && birthDate.compareTo(endDate) < 0;
    }

    public static void main(String[] args)
    {
        test2 a= new test2(Date.valueOf("1955"));
        System.out.println(a.isBornBoomer());
    }
}