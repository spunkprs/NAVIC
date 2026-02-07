package exceptionWrapping;

public class Runner {

    public static void main(String ar[]) {
        try {
            ExceptionWrappingImplementation.testExceptionFLow();
        } catch (Exception e) {
            System.out.println("Exception Cause " + e.getMessage());
            System.out.println("Hidden Exception " + e.getCause().getMessage());
            e.getCause().printStackTrace();
            for (Throwable th : e.getSuppressed()) {
                System.out.println("Suppressed Exception " + th.getMessage());
            }
        }

    }
}
