package designPatterns.extensibleEnumPattern;

public enum BasicOperation implements Operation {

    TRIM {
        @Override
            public String apply(String input) {
                return input.trim();
            }
    },

    LOWER_CASE {
        @Override
        public String apply(String input) {
            return input.toLowerCase();
        }
    },

    UPPER_CASE {
        @Override
        public String apply(String input) {
            return input.toUpperCase();
        }
    };

}
