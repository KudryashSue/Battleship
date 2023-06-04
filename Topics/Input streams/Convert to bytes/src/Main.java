import java.io.InputStream;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        byte[] b = new byte[50];
        int read = inputStream.read(b);
        for (int i = 0; i < read; i++) {
            System.out.print(b[i]);
        }
        inputStream.close();
    }
}