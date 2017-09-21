package se.cygni.npe;

public class App
{
    private static void safePrint(final String message) {
        String cleaned = message.replaceAll("\n", "");
        System.out.println(cleaned);
    }

    public static void main( String[] args )
    {
        safePrint(System.getenv("DESKTOP"));
    }
}
