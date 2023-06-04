import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        StringTokenizer tokens = new StringTokenizer(input);
        System.out.println(tokens.countTokens());
        reader.close();
    }
}