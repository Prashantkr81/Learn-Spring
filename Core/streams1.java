import java.util.List;
import java.util.Arrays;

public class streams1 {
    public static void main(String[] args) {

        List<Integer> li= Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        long count = li.stream()
                .filter(n-> n%2 == 0)
                .count();

        System.out.println(count);


    }
}
