
package mainCode;

public class Animal {
    private String type;
    private String name;
    private int animalID;
    private String peakActivity;
    private int feedDuration;
    private int feedPrep;
    private int cageCleanTime;
    private int startTime;
    private int window;
    private boolean cageCleaned;

    /**
     * Constructs a new animal with the specified ID, name, and type.
     * 
     * @param animalID the unique ID of the animal
     * @param name     the name of the animal
     * @param type     the type of animal (e.g. "COYOTE", "FOX", "PORCUPINE", etc.)
     */
    public Animal(int animalID, String name, String type) throws IllegalArgumentException {
        this.name = name;
        this.animalID = animalID;
        if (type.toUpperCase().equals("COYOTE")) {
            this.type = AnimalType.COYOTE.toString();
            this.peakActivity = "crepuscular";
            this.feedDuration = 5;
            this.feedPrep = 10;
            this.cageCleanTime = 5;
            this.startTime = 19;
            this.window = 3;
            this.cageCleaned = false;
        } else if (type.toUpperCase().equals("FOX")) {
            this.type = AnimalType.FOX.toString();
            this.peakActivity = "nocturnal";
            this.feedDuration = 5;
            this.feedPrep = 5;
            this.cageCleanTime = 5;
            this.startTime = 0;
            this.window = 3;
            this.cageCleaned = false;
        } else if (type.toUpperCase().equals("PORCUPINE")) {
            this.type = AnimalType.PORCUPINE.toString();
            this.peakActivity = "crepuscular";
            this.feedDuration = 5;
            this.feedPrep = 0;
            this.cageCleanTime = 10;
            this.startTime = 19;
            this.window = 3;
            this.cageCleaned = false;
        } else if (type.toUpperCase().equals("RACCOON")) {
            this.type = AnimalType.RACCOON.toString();
            this.peakActivity = "nocturnal";
            this.feedDuration = 5;
            this.feedPrep = 0;
            this.cageCleanTime = 5;
            this.startTime = 0;
            this.window = 3;
            this.cageCleaned = false;
        } else if (type.toUpperCase().equals("BEAVER")) {
            this.type = AnimalType.BEAVER.toString();
            this.peakActivity = "diurnal";
            this.feedDuration = 5;
            this.feedPrep = 0;
            this.cageCleanTime = 5;
            this.startTime = 8;
            this.window = 3;
            this.cageCleaned = false;
        } else {
            throw new IllegalArgumentException("The animal was not of correct type");
        }
    }

    /**
     * Sets the name of the animal.
     * 
     * @param name the name of the animal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the animal.
     * 
     * @param animalType the type of animal (e.g. "COYOTE", "FOX", "PORCUPINE",
     *                   etc.)
     */
    public void setAnimalType(String animalType) {
        this.type = animalType;
    }

    /**
     * Sets the ID of the animal.
     * 
     * @param animalID the unique ID of the animal
     */
    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    /**
     * Sets the time of day when the animal is most active.
     * 
     * @param peakActivity the time of day when the animal is most active (e.g.
     *                     "crepuscular", "nocturnal", etc.)
     */
    public void setPeakActivity(String peakActivity) {
        this.peakActivity = peakActivity;
    }

    /**
     * Sets the amount of time (in minutes) that it takes to feed the animal.
     * 
     * @param feedDuration the amount of time (in minutes) that it takes to feed the
     *                     animal
     */
    public void setFeedDuration(int feedDuration) {
        this.feedDuration = feedDuration;
    }

    /**
     * Sets the amount of time it takes to prepare food for the animal.
     *
     * @param feedPrep the time in minutes it takes to prepare food for the animal
     */
    public void setFeedPrep(int feedPrep) {
        this.feedPrep = feedPrep;
    }

    /**
     * Sets the amount of time it takes to clean the animal's cage.
     *
     * @param cageCleanTime the time in minutes it takes to clean the animal's cage
     */
    public void setCageCleanTime(int cageCleanTime) {
        this.cageCleanTime = cageCleanTime;
    }

    /**
     * Sets the time at which the animal's daily activities begin.
     *
     * @param startTime the time in hours at which the animal's daily activities
     *                  begin
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the time window within which the animal's daily activities occur.
     *
     * @param window the length of the time window in hours within which the
     *               animal's daily activities occur
     */
    public void setWindow(int window) {
        this.window = window;
    }

    /**
     * Sets whether the animal's cage has been cleaned or not.
     *
     * @param cageCleaned true if the animal's cage has been cleaned, false
     *                    otherwise
     */
    public void setCageCleaned(boolean cageCleaned) {
        this.cageCleaned = cageCleaned;
    }

    /**
     * Returns the name of the animal.
     *
     * @return the name of the animal
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the type of the animal.
     *
     * @return the type of the animal
     */
    public String getAnimalType() {
        return this.type;
    }

    /**
     * Returns the unique ID of the animal.
     *
     * @return the unique ID of the animal
     */
    public int getAnimalID() {
        return this.animalID;
    }

    /**
     * Returns the peak activity time of the animal.
     *
     * @return the peak activity time of the animal
     */
    public String getPeakActivity() {
        return this.peakActivity;
    }

    /**
     * Returns the duration of time it takes to feed the animal.
     *
     * @return the duration of time it takes to feed the animal
     */
    public int getFeedDuration() {
        return this.feedDuration;
    }

    /**
     * Returns the amount of time it takes to prepare food for the animal.
     *
     * @return the amount of time it takes to prepare food for the animal
     */
    public int getFeedPrep() {
        return this.feedPrep;
    }

    /**
     * Returns the amount of time it takes to clean the animal's cage.
     *
     * @return the amount of time it takes to clean the animal's cage
     */
    public int getCageCleanTime() {
        return this.cageCleanTime;
    }

    /**
     * Returns the time at which the animal's daily activities begin.
     *
     * @return the time at which the animal's daily activities begin
     */
    public int getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the time window within which the animal's daily activities occur.
     *
     * @return the time window within which the animal's daily activities occur
     */
    public int getWindow() {
        return this.window;
    }

    /**
     * Returns whether the animal's cage has been cleaned or not.
     *
     * @return true if the animal's cage has been cleaned; false otherwise.
     */
    public boolean getCageCleaned() {
        return this.cageCleaned;
    }

}