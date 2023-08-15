

 package mainCode;

 import java.util.*;
 import java.util.Comparator;
 
 public class Schedule {
     private Tasks[] tasks; // the variable tasks is used to store the tasks information from the database
     private Animal[] animals; // the variable animals is used to store the animals information from the
                               // database
     private int[][] treatments; // the variable treatments is used to store information of all the treatments
                                 // from the database
     private int[] manditory; // the varaible manditory is used to store a list of all task id's where the max
                              // window is one
     private int[][] rest; // the variable rest is used to store a list of the remaning
     private int[][] forced; // the forced varaible is used to store the treatments information regarding
     private int[][] remaining; //
     private int[][] schedule;
     private int scheduledNum;
     private int[] hour;
     private int[][] feed;
     private int[][] cageClean;
     private int[] volunteerHours;
 
     public Schedule(Tasks[] tasks, Animal[] animals, int[][] treatments)
             throws IllegalArgumentException, IllegalStateException {
 
         if (tasks == null || tasks.length == 0) {
             throw new IllegalArgumentException("Tasks array cannot be null or empty");
         }
         if (animals == null || animals.length == 0) {
             throw new IllegalArgumentException("Animals array cannot be null or empty");
         }
         if (treatments == null || treatments.length == 0) {
             throw new IllegalArgumentException("Treatments array cannot by null or empty");
         }
 
         Set<Integer> taskIds = new HashSet<>();
         for (Tasks task : tasks) {
             if (!taskIds.add(task.getTaskID())) {
                 throw new IllegalStateException("Duplicate task ID found");
             }
         }
 
         Set<Integer> animalsIds = new HashSet<>();
         for (Animal animal : animals) {
             if (!animalsIds.add(animal.getAnimalID())) {
                 throw new IllegalStateException("Duplicate Animal ID found");
             }
         }
 
         this.volunteerHours = new int[0];
         this.tasks = tasks;
         this.animals = animals;
         this.treatments = treatments;
         this.forced = new int[treatments.length][3];
         this.remaining = new int[treatments.length][3];
         int[][] test = new int[treatments.length + animals.length + animals.length + animals.length
                 + animals.length][3];
         this.schedule = test;
         this.scheduledNum = 0;
         this.hour = new int[24];
         this.feed = new int[animals.length][2];
         this.cageClean = new int[animals.length][2];
         Tasks[] taskList = this.tasks;
         this.rest = new int[tasks.length][2];
         this.manditory = new int[tasks.length];
         int tasknumber = taskList.length;
         int x = 0;
 
         int loop = 0;
         for (int i = 0; i < tasknumber; i++) {
             if (taskList[i].getMaxWindow() == 1) {
                 this.manditory[x] = taskList[i].getTaskID();
                 x++;
             } else {
                 this.rest[loop][0] = taskList[i].getTaskID();
                 this.rest[loop][1] = taskList[i].getMaxWindow();
 
                 loop++;
             }
 
         }
         Arrays.sort(rest, Comparator.comparingInt(a -> a[1]));
     }
 
     /**
      * Returns the hour array
      * 
      * @return the hour array
      */
     public int[] getHour() {
         return hour;
     }
 
     /**
      * Sets the hour array to the given array
      * 
      * @param hour the array to set the hour to
      */
     public void setHour(int[] hour) {
         this.hour = hour;
     }
 
     /**
      * Returns the scheduledNum variable
      * 
      * @return the scheduledNum variable
      */
     public int getScheduledNum() {
         return scheduledNum;
     }
 
     /**
      * Sets the scheduledNum variable to the given value
      * 
      * @param scheduledNum the value to set the scheduledNum to
      */
     public void setScheduledNum(int scheduledNum) {
         this.scheduledNum = scheduledNum;
     }
 
     /**
      * Returns the tasks array
      * 
      * @return the tasks array
      */
     public Tasks[] getTasks() {
         return tasks;
     }
 
     /**
      * Sets the tasks array to the given array
      * 
      * @param tasks the array to set the tasks to
      */
     public void setTasks(Tasks[] tasks) {
         this.tasks = tasks;
     }
 
     /**
      * Returns the animals array
      * 
      * @return the animals array
      */
     public Animal[] getAnimals() {
         return animals;
     }
 
     /**
      * Sets the animals array to the given array
      * 
      * @param animals the array to set the animals to
      */
     public void setAnimals(Animal[] animals) {
         this.animals = animals;
     }
 
     /**
      * Returns the treatments 2D array
      * 
      * @return the treatments 2D array
      */
     public int[][] gettreatments() {
         return treatments;
     }
 
     /**
      * Sets the treatments 2D array to the given array
      * 
      * @param treatments the array to set the treatments to
      */
     public void settreatments(int[][] treatments) {
         this.treatments = treatments;
     }
 
     /**
      * Returns the manditory array
      * 
      * @return the manditory array
      */
     public int[] getManditory() {
         return manditory;
     }
 
     /**
      * Sets the manditory array to the given array
      * 
      * @param manditory the array to set the manditory to
      */
     public void setManditory(int[] manditory) {
         this.manditory = manditory;
     }
 
     /**
      * Returns the rest 2D array
      * 
      * @return the rest 2D array
      */
     public int[][] getRest() {
         return rest;
     }
 
     /**
      * Sets the rest 2D array to the given array
      * 
      * @param rest the array to set the rest to
      */
     public void setRest(int[][] rest) {
         this.rest = rest;
     }
 
     /**
      * Returns the forced 2D array
      * 
      * @return the forced 2D array
      */
     public int[][] getForced() {
         return this.forced;
     }
 
     /**
      * Sets the forced 2D array to the given array
      * 
      * @param forced the array to set the forced to
      */
     public void setForced(int[][] forced) {
         this.forced = forced;
     }
 
     /**
      * Returns the remaining 2D array
      * 
      * @return the remaining 2D array
      */
     public int[][] getRemaining() {
         return this.remaining;
     }
 
     /**
      * Sets the remaining 2D array to the given array
      * 
      * @param remaining the array to set the remaining to
      */
     public void setRemaining(int[][] remaining) {
         this.remaining = remaining;
     }
 
     /**
      * Returns the schedule 2D array
      * 
      * @return the schedule 2D array
      */
     public int[][] getSchedule() {
         return this.schedule;
     }
 
     /**
      * Sets the schedule 2D array to the given array
      * 
      * @param schedule the array to set the schedule to
      */
     public void setSchedule(int[][] schedule) {
         this.schedule = schedule;
     }
 
     /**
      * Gets the 2D array of feed schedule.
      * 
      * @return The 2D array of feed schedule.
      */
     public int[][] getFeed() {
         return this.feed;
     }
 
     /**
      * Sets the 2D array of feed schedule.
      * 
      * @param feed The 2D array of feed schedule to be set.
      */
     public void setFeed(int[][] feed) {
         this.feed = feed;
     }
 
     /**
      * Gets the 2D array of cage cleaning schedule.
      * 
      * @return The 2D array of cage cleaning schedule.
      */
     public int[][] getCageClean() {
         return this.cageClean;
     }
 
     /**
      * Sets the 2D array of cage cleaning schedule.
      * 
      * @param cageClean The 2D array of cage cleaning schedule to be set.
      */
     public void setCageClean(int[][] cageClean) {
         this.cageClean = cageClean;
     }
 
     /**
      * Sets the array of volunteer hours.
      * 
      * @param volunteerHours The array of volunteer hours to be set.
      */
     public void setVolunteerHours(int[] volunteerHours) {
         this.volunteerHours = volunteerHours;
     }
 
     /**
      * Gets the array of volunteer hours.
      * 
      * @return The array of volunteer hours.
      */
     public int[] getVolunteerHours() {
         return this.volunteerHours;
     }
 
     /**
      * Populates two two-dimensional arrays of integers, 'forced' and 'remaining',
      * based on values in the 'manditory', 'rest', and 'treatments' arrays.
      * The 'forced' array is populated with rows from the 'treatments' array that
      * match values in the 'manditory' array.
      * The 'remaining' array is populated with rows from the 'treatments' array that
      * match values in the 'rest' array.
      * The 'forced' array is then sorted by the third column in ascending order and
      * any rows in the 'remaining' array with a 0 in the first column are set to
      * null.
      * 
      * @return void
      */
     public void populateForcedAndRemaining() {
         int y = 0;
         for (int z = 0; z < manditory.length; z++) {
             for (int i = 0; i < treatments.length; i++) {
                 if (treatments[i][1] == manditory[z]) {
                     forced[y] = treatments[i];
                     y++;
                 }
             }
         }
         int loopTwo = 0;
         for (int z = 0; z < rest.length; z++) {
             for (int i = 0; i < treatments.length; i++) {
                 if (treatments[i][1] == rest[z][0]) {
                     remaining[loopTwo] = treatments[i];
                     loopTwo++;
                 }
             }
         }
         sortAndNull(forced);
         onlyNull(remaining);
     }
 
     /**
      * Sets any rows in a two-dimensional array of integers that have a 0 in the
      * first column to null.
      * 
      * @param value the two-dimensional array of integers to be modified
      * @return void
      */
     public void onlyNull(int[][] value) {
 
         for (int i = 0; i < value.length; i++) {
             if (value[i][0] == 0) {
                 value[i] = null;
             }
         }
     }
 
     /**
      * Sorts a two-dimensional array of integers by the third column in ascending
      * order
      * and sets any rows with a 0 in the first column to null.
      * 
      * @param value the two-dimensional array of integers to be sorted and
      *              potentially set to null
      * @return void
      */
     public void sortAndNull(int[][] value) {
         Arrays.sort(value, Comparator.comparingInt(a -> a[2]));
 
         for (int i = 0; i < value.length; i++) {
             if (value[i][0] == 0) {
                 value[i] = null;
             }
         }
 
     }
 
     /**
      * Passes in an array with information on tasks
      * Iterates through values array and adds tasks to schedule array, setting tasks
      * in values array to null each time
      * 
      * @param values a two-dimensional array of integers holding the animalID,
      *               taskID, and starting hour
      * 
      */
     public void addToSchedule(int[][] values, int min) {
         // iterate through the values
         for (int i = 0; i < values.length; i++) {
             // check if the value is not null
             if (values[i] != null) {
                 // add the duration of the task to the hour
                 hour[values[i][2]] += tasks[values[i][1] - 1].getDuration();
                 // check if the hour exceeds 60 minutes to see if there is enough time in the
                 // hour to schedule the task
                 if (hour[values[i][2]] > min) {
                     // if it exceeds, subtract the duration because the task cannot fit in the hour
                     hour[values[i][2]] -= tasks[values[i][1] - 1].getDuration();
                 } else {
                     // if it does not exceed, add the value to the schedule, mark the value as null
                     // to remove the task from the array
                     // increment the scheduledNum counter for the next task to be added to the end
                     // of the array
                     schedule[scheduledNum] = values[i];
                     values[i] = null;
                     scheduledNum++;
                 }
             }
         }
     }
 
     /**
      * Passes in an array with information on tasks
      * If addToSchedule does not work, check the other hours in the window
      * 
      * @param values a two-dimensional array of integers holding the animalID,
      *               taskID, and starting hour
      * 
      */
     public boolean addToScheduleWindowCheck(int[][] values, int min) {
         for (int i = 0; i < values.length; i++) {
             if (values[i] != null) {
                 int window = tasks[values[i][1] - 1].getMaxWindow();
                 for (int j = 1; j < window; j++) {
                     if (values[i][2] + j >= 24) {
                         return false;
                     }
                     hour[values[i][2] + j] += tasks[values[i][1] - 1].getDuration();
                     if (hour[values[i][2] + j] > min) {
                         hour[values[i][2] + j] -= tasks[values[i][1] - 1].getDuration();
                     } else {
                         values[i][2] += j;
                         schedule[scheduledNum] = values[i];
                         values[i] = null;
                         scheduledNum++;
 
                         break;
                     }
                 }
             }
         }
 
         int val = 0;
         for (int k = 0; k < values.length; k++) {
             if (values[k] == null) {
                 val++;
             }
         }
         if (val == values.length) {
             return true;
         }
         return false;
 
     }
 
     /**
      * Populates the feed array with feeding tasks for all animals aside from
      * orphans
      * 
      * @return void
      */
     public void populateFeed() {
         List<Integer> orphanIndices = new ArrayList<>();
         for (int i = 0; i < treatments.length; i++) {
             if (treatments[i][1] == 1) {
                 orphanIndices.add(treatments[i][0] - 1); // subtract 1 to convert to 0-based index
             }
         }
 
         int[][] feedTemp = new int[animals.length][2];
         int feedloop = 0;
         for (int i = 0; i < animals.length; i++) {
             if (orphanIndices.contains(i)) {
                 continue; // skip this index if it's an orphan, because kit feeding is handled differently
             }
             // Add information from animal to feedTemp array
             feedTemp[feedloop][0] = animals[i].getAnimalID();
             feedTemp[feedloop][1] = animals[i].getStartTime();
             feedloop++;
         }
 
         for (int i = 0; i < feedTemp.length; i++) {
             if (feedTemp[i][0] == 0) {
                 feedTemp[i] = null;
             }
         }
 
         this.feed = feedTemp;
     }
 
     /**
      * Schedules the feeding times for each animal to the schedule array.
      * The feed times are determined by the window and feed duration for each
      * animal.
      * It is more efficient to feed animals of the same type together, in the same
      * hour, so the schedule looks to group the animals of the same type in the same
      * hour.
      * The feed times are added to the 2D array 'schedule'.
      *
      * @return void
      */
     public void addFeedToSchedule(int min) {
 
         int first = 0;
         int num = 1;
         for (int i = 0; i < feed.length; i++) {
             if (feed[i] != null) {
                 int window = animals[feed[i][0] - 1].getWindow();
                 if (first == 0) {
                     num = 0;
                     for (int m = 0; m < animals.length; m++) {
                         if (feed[m] != null) {
                             if (animals[feed[i][0] - 1].getAnimalType() == animals[feed[m][0] - 1].getAnimalType()) {
                                 num++;
                             }
                         }
                     }
                 }
 
                 for (int j = 0; j < window; j++) {
 
                     if (num == 0) {
                         continue;
                     }
 
                     if (feed[i][1] + j >= 24) {
                         break;
                     }
 
                     hour[feed[i][1] + j] += num * (animals[feed[i][0] - 1].getFeedDuration())
                             + animals[feed[i][0] - 1].getFeedPrep();
 
                     if (hour[feed[i][1] + j] > min) {
                         hour[feed[i][1] + j] -= num * (animals[feed[i][0] - 1].getFeedDuration())
                                 + animals[feed[i][0] - 1].getFeedPrep();
                         first++;
 
                         if (j == window - 1) {
                             num--;
                             j = -1;
                             if (num == 0) {
                                 first = 0;
                                 break;
                             }
                         }
                     } else {
                         schedule[scheduledNum][0] = feed[i][0];
                         if (min == 120) {
                             schedule[scheduledNum][1] = -2;
                         } else {
                             schedule[scheduledNum][1] = 0;
                         }
                         schedule[scheduledNum][2] = feed[i][1] + j;
                         scheduledNum++;
                         int del = 0;
                         int store = num;
                         for (int a = 0; a < feed.length; a++) {
                             if (feed[a] != null) {
                                 if (animals[feed[i][0] - 1].getAnimalType() == animals[feed[a][0] - 1].getAnimalType()
                                         && a != i && del < (store - 1)) {
                                     schedule[scheduledNum][0] = feed[a][0];
                                     if (min == 120) {
                                         schedule[scheduledNum][1] = -2;
                                     } else {
                                         schedule[scheduledNum][1] = 0;
                                     }
                                     schedule[scheduledNum][2] = feed[a][1] + j;
                                     scheduledNum++;
                                     feed[a] = null;
                                     del++;
                                 }
                             }
                         }
                         feed[i] = null;
                         first = 0;
                         break;
                     }
                 }
             }
         }
     }
 
     /**
      * Schedules cage cleaning times for each animal to the schedule array.
      * The cleaning times are determined by the duration of time it takes to clean
      * each animal's cage.
      * The schedule is stored in the 3D array 'schedule'.
      *
      * @return void
      */
     public void addCageCleaningTimes(int min) {
         int[][] CagesToClean = new int[animals.length][3];
         for (int i = 0; i < animals.length; i++) {
             CagesToClean[i][0] = animals[i].getAnimalID();
             CagesToClean[i][1] = -1;
             CagesToClean[i][2] = 0;
         }
         int first = 0;
         for (int i = 0; i < CagesToClean.length; i++) {
             if (CagesToClean[i] != null) {
                 int window = 24;
                 int num = 0;
                 if (first == 0) {
                     for (int m = 0; m < animals.length; m++) {
                         if (CagesToClean[m] != null) {
                             if (animals[CagesToClean[i][0] - 1].getAnimalType() == animals[CagesToClean[m][0] - 1]
                                     .getAnimalType()) {
                                 num++;
                             }
                         }
                     }
                 }
                 for (int j = 0; j < window; j++) {
 
                     if (num == 0) {
                         continue;
                     }
 
                     if (CagesToClean[i][1] + j >= 24) {
                         break;
                     }
                     hour[CagesToClean[i][2] + j] += num * (animals[CagesToClean[i][0] - 1].getCageCleanTime());
                     if (hour[CagesToClean[i][2] + j] > min) {
                         hour[CagesToClean[i][2] + j] -= num * (animals[CagesToClean[i][0] - 1].getCageCleanTime());
                         first++;
                         if (j == window - 1) {
                             num--;
                             j = -1;
                             if (num == 0) {
                                 break;
                             }
                         }
                     } else {
                         schedule[scheduledNum][0] = CagesToClean[i][0];
 
                         if (min == 120) {
                             schedule[scheduledNum][1] = -3;
                         } else {
                             schedule[scheduledNum][1] = -1;
                         }
                         schedule[scheduledNum][2] = CagesToClean[i][2] + j;
                         scheduledNum++;
                         int del = 0;
                         int store = num;
                         for (int a = 0; a < CagesToClean.length; a++) {
                             if (CagesToClean[a] != null) {
                                 if (animals[CagesToClean[i][0] - 1].getAnimalType() == animals[CagesToClean[a][0] - 1]
                                         .getAnimalType() && a != i && del < (store - 1)) {
                                     schedule[scheduledNum][0] = CagesToClean[a][0];
                                     if (min == 120) {
                                         schedule[scheduledNum][1] = -3;
                                     } else {
                                         schedule[scheduledNum][1] = -1;
                                     }
                                     schedule[scheduledNum][2] = CagesToClean[a][2] + j;
                                     scheduledNum++;
                                     CagesToClean[a] = null;
                                     del++;
                                 }
                             }
                         }
                         CagesToClean[i] = null;
                         first = 0;
                         this.cageClean = CagesToClean;
                         break;
                     }
                 }
             }
         }
     }
 
 }
 