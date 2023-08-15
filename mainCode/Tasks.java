
package mainCode;

public class Tasks {

    private int taskID;
    private String taskDescription;
    private int taskDuration;
    private int maxWindow;

    /**
     * Constructs a new task with the given ID, description, duration, and maximum
     * window.
     * 
     * @param id  the ID of the task
     * @param des the description of the task
     * @param dur the duration of the task in minutes
     * @param max the maximum time window in which the task can be completed
     * @throws IllegalArgumentException if the duration or maximum window is
     *                                  negative
     */
    public Tasks(int id, String des, int dur, int max) throws IllegalArgumentException {
        if (dur < 0 || max < 0) {
            throw new IllegalArgumentException("Duration and maximum window must be non-negative.");
        }
        this.taskID = id;
        this.taskDescription = des;
        this.taskDuration = dur;
        this.maxWindow = max;
    }

    /**
     * Sets the ID of the task.
     * 
     * @param id the new ID of the task
     */
    public void setId(int id) {
        this.taskID = id;
    }

    /**
     * Sets the description of the task.
     * 
     * @param desc the new description of the task
     */
    public void setDescription(String desc) {
        this.taskDescription = desc;
    }

    /**
     * Sets the duration of the task.
     * 
     * @param duration the new duration of the task in minutes
     * @throws IllegalArgumentException if the duration is negative
     */
    public void setDuration(int duration) throws IllegalArgumentException {
        if (duration < 0) {
            throw new IllegalArgumentException("Duration must be non-negative.");
        }
        this.taskDuration = duration;
    }

    /**
     * Sets the maximum time window in which the task can be completed.
     * 
     * @param max the new maximum time window in minutes
     * @throws IllegalArgumentException if the maximum window is negative
     */
    public void setMaxWindow(int max) throws IllegalArgumentException {
        if (max < 0) {
            throw new IllegalArgumentException("Maximum window must be non-negative.");
        }
        this.maxWindow = max;
    }

    /**
     * Gets the ID of the task.
     * 
     * @return the ID of the task
     */
    public int getTaskID() {
        return this.taskID;
    }

    /**
     * Gets the description of the task.
     * 
     * @return the description of the task
     */
    public String getDescription() {
        return this.taskDescription;
    }

    /**
     * Gets the duration of the task.
     * 
     * @return the duration of the task in minutes
     */
    public int getDuration() {
        return this.taskDuration;
    }

    /**
     * Gets the maximum time window in which the task can be completed.
     * 
     * @return the maximum time window in minutes
     */
    public int getMaxWindow() {
        return this.maxWindow;
    }

}
