package mainCode;

public enum AnimalType {

    /**
     * Represents a coyote.
     */
    COYOTE {
        /**
         * Returns a string representation of a coyote.
         * 
         * @return String "coyote"
         */
        public String toString() {
            return "coyote";
        }
    },
    /**
     * Represents a fox.
     */
    FOX {
        /**
         * Returns a string representation of a fox.
         * 
         * @return String "fox"
         */
        public String toString() {
            return "fox";
        }
    },
    /**
     * Represents a porcupine.
     */
    PORCUPINE {
        /**
         * Returns a string representation of a porcupine.
         * 
         * @return String "porcupine"
         */
        public String toString() {
            return "porcupine";
        }
    },
    /**
     * Represents a raccoon.
     */
    RACCOON {
        /**
         * Returns a string representation of a raccoon.
         * 
         * @return String "raccoon"
         */
        public String toString() {
            return "raccoon";
        }
    },
    /**
     * Represents a beaver.
     */
    BEAVER {
        /**
         * Returns a string representation of a beaver.
         * 
         * @return String "beaver"
         */
        public String toString() {
            return "beaver";
        }
    };

    /**
     * Creates an abstract function.
     * 
     * @return String
     */
    public abstract String toString();

}
