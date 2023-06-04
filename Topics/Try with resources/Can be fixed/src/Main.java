import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) {

        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in))){
            String language;
            language = buffReader.readLine();
            System.out.println("I'm learning " + language);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}