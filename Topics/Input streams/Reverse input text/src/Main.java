import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        /*char[] try1 = input.toCharArray();
        for (int i = try1.length - 1; i >= 0; i--) {
            System.out.print(try1[i]);
        }*/
        for (int i = input.length() - 1; i >= 0; i--) {
            System.out.print(input.charAt(i));
        }
        reader.close();
    }
}