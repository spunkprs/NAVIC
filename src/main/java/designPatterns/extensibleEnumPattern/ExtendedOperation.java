package designPatterns.extensibleEnumPattern;

public enum ExtendedOperation implements Operation {

    MD5_ENCODE {
        @Override
        public String apply(String input) {
            System.out.print("Performing mock encoding !! ");
            return input;
        }
    }
}
