package exceptionWrapping;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
Reference -->
a.) https://jenkov.com/tutorials/java-exception-handling/exception-wrapping.html
b.) https://jenkov.com/tutorials/java-exception-handling/try-with-resources.html
 * */

public class ExceptionWrappingImplementation {

    public static void testExceptionFLow() {
        InputStream input = null;

        try{
            input = new FileInputStream("myFile111.txt");
            //do something with the stream
        } catch(IOException e) {
            throw new WrapperException("IOExceptionOne", e);
        } finally {
            try{
                input.close();
            } catch(IOException e){
                throw new WrapperException("IOExceptionTwo", e);
            } catch (Exception e) {
                throw new WrapperException("GenericException", e);
            }
        }
    }
}
