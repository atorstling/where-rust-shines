package se.cygni.npe;

public class App
{
    static String cleanup(final String message) {
        return message.replaceAll("\n", "");
    }

    public static void main( String[] args )
    {
        String username = System.getenv("NPE_USERNAME");
        System.out.println(cleanup(username));
    }
}
