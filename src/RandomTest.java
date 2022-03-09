/**
 * Created by Grant on 2/28/2017.
 */
public class RandomTest {

    public static void main(String [] args){
        int rand = 0;
        for(int i = 0; i <= 10; i++){

            rand =  (int)(Math.random()*4);
            System.out.println(rand);
        }
    }
}
