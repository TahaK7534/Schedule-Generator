
 package mainCode;

 import java.util.*;
 import java.time.LocalDate;
 
 public class PrintSchedule {
     private final int[][] forced;
     private final int[][] remaining;
     private final int[][] feed;
     private final Tasks[] tasks;
     private final Animal[] animals;
     private final int[][] treatments;
     private final int[][] cleanCages;
     private int[][] schedule;

    /**
     * Constructor for the PrintSchedule class.
     * @param tasks an array of Tasks objects
     * @param animals an array of Animal objects
     * @param forced a 2D integer array of treatments that are forced to be scheduled (window of 1) 
     * @param remaining a 2D integer array of treatments that are not forced but need to be scheduled
     * @param schedule a 2D integer array of the schedule
     * @param feed a 2D integer array holding the feeding schedule for animals
     * @param treatments a 2D integer array representing all the treatments that can be scheduled
     * @param cleanCages a 2D integer array holding the schedule for cage cleaning for animals
     * @throws IllegalStateException if there are duplicate task IDs or animal IDs in the given arrays
    */
     public PrintSchedule(Tasks[] tasks, Animal[] animals, int[][] forced, int[][] remaining, int[][] schedule, int[][] feed, int[][] treatments, int[][] cleanCages) throws IllegalStateException {
         
         this.forced= forced;
         this.remaining = remaining;
         this.schedule = schedule;
         this.feed = feed;
         this.tasks = tasks;
         this.animals = animals;
         this.treatments = treatments;
         this.cleanCages = cleanCages;

        //Check for duplicate task IDs 
        Set<Integer> taskIds = new HashSet<>();
        for (Tasks task : tasks) {
            if (!taskIds.add(task.getTaskID())) {
                throw new IllegalStateException("Duplicate task ID found");
            }
        }
        //Check for duplicate animal IDs 
        Set<Integer> animalsIds = new HashSet<>();
        for (Animal animal : animals) {
            if (!animalsIds.add(animal.getAnimalID())) {
                throw new IllegalStateException("Duplicate Animal ID found");
            }
        }
     }
     
     
        /**
         * Returns a string representation of a volunteer schedule based on the provided integer array.
         * @param volunteer an integer array containing the hours a volunteer is scheduled
         * @return a string representation of the complete schedule 
         * @throws IllegalArgumentException if the volunteer array is null or has a length of 0
        */
        public String printSchedule(int[] volunteer)throws IllegalArgumentException {
            // Initializes a counter to keep track of the number of null values in the 2D array "schedule".
            int nullAmount= 0;
            // Iterates through each row of the 2D array schedule.
            for (int i =0; i< schedule.length; i++) {
                // If the current row is null or its first element is 0, increments the nullAmount counter.
                if (schedule[i]==null ||  schedule[i][0]==0) {
                    nullAmount++;
                }
                // If every row of the 2D array "schedule" is null, throws an IllegalArgumentException.
                if (nullAmount == schedule.length) {
                    throw new IllegalArgumentException("No values where added to the schedule");
                }
            }


            
                
        // Used to keep track of whether a scheduling failure has occurred (0 means no failure, 1 means failure)
        int  fail = 0;
        // Initializes a StringBuffer to construct the schedule string.
        StringBuffer sched = new StringBuffer();
            
            // Loops through each element in the "forced" array to see if any values are not null (unscheduled).
            for (int a =0; a < forced.length; a++) {
            //If a null value is found increment the integer fail to represent that the schedule is not possible
                if (forced[a] != null && fail == 0) {
                sched.append("Not Possible due to... Task: " + tasks[forced[a][1]-1].getDescription() + " Can not be completed in the following hours: " );
                    for (int i =0; i < tasks[forced[a][1]-1].getMaxWindow(); i++) {
                    sched.append((forced[a][2] + i) + ":00 ");
                    }
                    fail++;
                }
            }
            //Loops through each element in the "remaining" array to see if any values are not null (unscheduled).
            for (int a =0; a < remaining.length; a++) {
            //If a null value is found increment the integer fail to represent that the schedule is not possible
                if (remaining[a] != null && fail == 0) {
                sched.append("Not Possible due to... Task: " + tasks[remaining[a][1]-1 ].getDescription() + " Can not be completed in the following hours: " );
                    for (int i =0; i < tasks[remaining[a][1] -1].getMaxWindow(); i++) {
                    sched.append((remaining[a][2] + i) + ":00 ");
                    }
                    fail++; 
                }
            }
            //Loops through each element in the "feed" array to see if any values are not null (unscheduled).
            for (int a =0; a < feed.length; a++) {
            //If a null value is found increment the integer fail to represent that the schedule is not possible
                if (feed[a] != null && fail == 0) {
                sched.append("Not Possible due to... Task: Feeding For " + animals[feed[a][0] -1] .getName() + " Please modify a task to try to reschedule.");
                    fail++;
                }
            }
        
            //Loops through each element in the "feed" array to see if any values are not null (unscheduled).
            for (int a =0; a < cleanCages.length; a++) {
            //If a null value is found increment the integer fail to represent that the schedule is not possible
                if (cleanCages[a] != null && fail == 0) {
                sched.append("Not Possible due to... Task: CageCleaning For " +animals[cleanCages[a][0] -1].getName() + " Please modify a task to try to reschedule.");
                    fail++;
                }
            }

                
        //If the integer fail is 0, meaning there were no failures and that the schedule is possible, then construct the schedule string
        if (fail == 0) {
            //Holds the previous start hour to manage the printing of the hour in the schedule
            int previous = 25;

            //Get the current size
            LocalDate today = LocalDate.now();
            // Add the date to the beginning of the schedule string
            sched.append("Schedule for " + today.toString() + "\n");

            //Loops through each element in schedule array
             for (int i = 0; i < schedule.length; i++) {
                //Checks that the current element is not null
                 if (schedule[i] != null) {
                    //Checks that the previous variable does not equal the current hour
                     if (previous != schedule[i][2]) {

                        //Checks the task ID of the current task, builds schedule string based on the type of task (feeding)
                         if (schedule[i][1] == 0) {
                        	 
                        	 //Checks to see if there are volunteers needed in this hour
                            int checking = Arrays.binarySearch(volunteer, schedule[i][2]);
                            //If a volunteer is scheduled for this hour, build the string indicate backup is needed at the hour
                            if (checking >= 0) {
                                sched.append("\n" + String.valueOf(schedule[i][2]) + ":00 [+ backup volunteer]\n");
                            //If no volunteer is scheduled for this hour then just display the hour
                            } else {
                                sched.append("\n" + String.valueOf(schedule[i][2]) + ":00\n");
                            }
                            //Set the previous variable to the current hour
                            previous = schedule[i][2];

                            //initializes a variable that holds the number of animals that are scheduled to be fed this hour
                            int num =0;
                            //Counts the number of animals that are scheduled to be fed this hour
                            for (int j =0; j < schedule.length; j++) {
                                if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == 0
                                		) {
                                    num++;
                                }
                                
                            }
                            //Adds the feeding task with the animal type and number of animals fed
                            sched.append("*" + " Feeding - " + animals[schedule[i][0] -1].getAnimalType() +"(" + num + ": ");
                                
                             for (int j =0; j < schedule.length; j++) {
                                 if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == 0 && j != i ) {
                                    sched.append(animals[schedule[j][0]-1].getName() + " ");
                                       schedule[j] = null;
                                 }
                                 
                             }
                            //Adds the names of the animals that are being fed
                            sched.append(animals[schedule[i][0]-1].getName() + ")\n");

                         }
                        //Checks the task ID of the current task, builds schedule string based on the type of task in this case it is for Cage Cleaning
                         else if(schedule[i][1] == -1) {
                            //Checks to see if there are volunteers needed in this hour
                             int checkTwo = Arrays.binarySearch(volunteer, schedule[i][2]);
                            //If a volunteer is scheduled for this hour, build the string indicate backup is needed at the hour                
                            if (checkTwo >= 0) {
                                sched.append("\n" + String.valueOf(schedule[i][2]) + ":00 [+ backup volunteer]\n");
                            //If no volunteer is scheduled for this hour then just display the hour                                
                            } else {
                                sched.append("\n" + String.valueOf(schedule[i][2]) + ":00\n");
                            }
                            //Set the previous variable to the current hour
                            previous = schedule[i][2];
                            //initializes a variable that holds the number of animals that are scheduled to have their cage cleaned this hour
                            int num =0;
                            //Counts the number of animals that are scheduled to have their cage cleaned this hour
                            for (int j =0; j < schedule.length; j++) {
                                if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -1) {
                                    num++;
                                }
                                
                            }
                            //Adds the Cage Cleaning task with the animal type and number of animals whos cages were cleaned
                            sched.append("*" + " Cage Cleaning - " + animals[schedule[i][0] -1].getAnimalType() +"(" + num + ": ");
                                
                             for (int j =0; j < schedule.length; j++) {
                                 if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -1 && j != i ) {
                                    sched.append(animals[schedule[j][0]-1].getName() + " ");
                                       schedule[j] = null;
                                 }
                                 
                             }
                            //Adds the names of the animals that are having their cage cleaned
                                sched.append(animals[schedule[i][0]-1].getName() + ")\n");
                         }
                         
                         
                        //Checks the task ID of the current task, builds schedule string based on the type of task (volunteer feeding)
                         else if (schedule[i][1] == -2) {
                        	 
                            //Checks to see if there are volunteers needed in this hour (Special case where there will be due to the custom task ID)
                             int checking = Arrays.binarySearch(volunteer, schedule[i][2]);
                             //If a volunteer is scheduled for this hour, build the string indicate backup is needed at the hour 
                             if (checking >= 0) {
                                 sched.append("\n" + String.valueOf(schedule[i][2]) + ":00 [+ backup volunteer]\n");
                             } 
                             //If no volunteer is scheduled for this hour then just display the hour
                             else {
                                 sched.append("\n" + String.valueOf(schedule[i][2]) + ":00\n");
                             }
                             
                            //Set the previous variable to the current hour
                            previous = schedule[i][2];
                            //initializes a variable that holds the number of animals that are scheduled to be fed this hour  
                            int num =0;
                            //Counts the number of animals that are scheduled to be fed this hour
                             for (int j =0; j < schedule.length; j++) {
                                 if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -2
                                 		) {
                                     num++;
                                 }
                                 
                             }
                            //Adds the feeding task with the animal type and number of animals fed
                            sched.append("*" + " Feeding - " + animals[schedule[i][0] -1].getAnimalType() +"(" + num + ": ");
                                 
                              for (int j =0; j < schedule.length; j++) {
                                  if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -2 && j != i ) {
                                     sched.append(animals[schedule[j][0]-1].getName() + " ");
                                        schedule[j] = null;
                                  }
                                  
                              }
                            //Adds the names of the animals that are being fed
                            sched.append(animals[schedule[i][0]-1].getName() + ")\n");
                          }

                        //Checks the task ID of the current task, builds schedule string based on the type of task (volunteer cage cleaning)
                         else if(schedule[i][1] == -3) {
                            //Checks to see if there are volunteers needed in this hour (Special case where there will be due to the custom task ID)
                            int checkTwo = Arrays.binarySearch(volunteer, schedule[i][2]);
                            //If a volunteer is scheduled for this hour, build the string indicate backup is needed at the hour 
                            if (checkTwo >= 0) {
                                sched.append("\n" + String.valueOf(schedule[i][2]) + ":00 [+ backup volunteer]\n");
                            } 
                            //If no volunteer is scheduled for this hour then just display the hour
                            else {
                                sched.append("\n" + String.valueOf(schedule[i][2]) + ":00\n");
                            }
                            //Set the previous variable to the current hour
                            previous = schedule[i][2];

                            //initializes a variable that holds the number of animals that are scheduled to have their cage cleaned this hour
                            int num =0;
                            //Counts the number of animals that are scheduled to have their cage cleaned this hour
                            for (int j =0; j < schedule.length; j++) {
                                if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -3) {
                                    num++;
                                }
                                
                            }
                            //Adds the Cage Cleaning task with the animal type and number of animals whos cages were cleaned
                            sched.append("*" + " Cage Cleaning - " + animals[schedule[i][0] -1].getAnimalType() +"(" + num + ": ");
                                
                             for (int j =0; j < schedule.length; j++) {
                                 if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -3 && j != i ) {
                                    sched.append(animals[schedule[j][0]-1].getName() + " ");
                                       schedule[j] = null;
                                 }
                                 
                             }
                            //Adds the names of the animals that are having their cage cleaned
                            sched.append(animals[schedule[i][0]-1].getName() + ")\n");
                         }

                        //if the task id is not of a special case, meaning it is included in the data then run the follwoing code
                         else {
                         //Checks to see if there are volunteers needed in this hour
                         int checkThree = Arrays.binarySearch(volunteer, schedule[i][2]);
                         //If a volunteer is scheduled for this hour, build the string indicate backup is needed at the hour                

                            if (checkThree >= 0) {
                                sched.append("\n" + String.valueOf(schedule[i][2]) + ":00 [+ backup volunteer]\n");
                             //If no volunteer is scheduled for this hour then just display the hour
                            } else {
                                sched.append("\n" + String.valueOf(schedule[i][2]) + ":00\n");
                            }
                         //Set the previous variable to the current hour
                         previous = schedule[i][2];
                         //print out the task and the animals you are doing it for
                        sched.append("* " + tasks[schedule[i][1]-1].getDescription()  + " - " + animals[schedule[i][0]-1].getName() + "\n");
                     }
                         
                     
                     
                     }
                     //if this is not the first occurence of the start hour then do the following
                     else {
                        //if the task id is zero which is used for feeding the animals then do the follwoing
                         if(schedule[i][1] == 0) {
                             //set the current hour to the previous hour
                             previous = schedule[i][2];
                             //set the number of animals that are feeding fead
                             int num =0;
                             //calculate the number of animals that need to be fead
                            for (int j =0; j < schedule.length; j++) {
                                if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == 0) {
                                    num++;
                                }
                            }
                            //add the task Feading, animal type, and animal number to the result along with there names
                            sched.append("*" + " Feeding - " + animals[schedule[i][0]-1].getAnimalType() +"(" + num + ": ");
                             for (int j =0; j < schedule.length; j++) {
                                 if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == 0 && j != i ) {
                                    sched.append(animals[schedule[j][0]-1].getName() + " ");
                                       schedule[j] = null;
                                 }
                             }  
                                // add one more animal name
                                sched.append(animals[schedule[i][0]-1].getName() + ")\n");
                         }
                         
                        //Checks the task ID of the current task, builds schedule string based on the type of task in this case it is for Cage Cleaning
                         else if(schedule[i][1] == -1) {	    		
                             previous = schedule[i][2];
                            //initializes a variable that holds the number of animals that are scheduled to have their cage cleaned this hour
                             int num =0;
                            //Counts the number of animals that are scheduled to have their cage cleaned this hour
                            for (int j =0; j < schedule.length; j++) {
                                if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -1) {
                                    num++;
                                }
                                
                            }
                            //Adds the Cage Cleaning task with the animal type and number of animals whos cages were cleaned
                            sched.append("*" + " Cage Cleaning - " + animals[schedule[i][0] -1].getAnimalType() +"(" + num + ": ");
                                
                             for (int j =0; j < schedule.length; j++) {
                                 if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -1 && j != i ) {
                                    sched.append(animals[schedule[j][0]-1].getName() + " ");
                                       schedule[j] = null;
                                 }
                                 
                             }
                            //Adds the names of the animals that are having their cage cleaned
                            sched.append(animals[schedule[i][0]-1].getName() + ")\n");
                         }
                         //Checks the task ID of the current task, builds schedule string based on the type of task in this case it is for feeding if a voluntear is needed

                         else if(schedule[i][1] == -2) {
                             //set previous hour
                             previous = schedule[i][2];
                             //set number of animals fead to zero
                             int num =0;
                             //calculate number of animals that need to be fead
                            for (int j =0; j < schedule.length; j++) {
                                if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -2) {
                                    num++;
                                }
                            }
                            //add the animals, task (feading in this case) aniamls type and amount of aniamls to the result
                            sched.append("*" + " Feeding - " + animals[schedule[i][0]-1].getAnimalType() +"(" + num + ": ");
                             for (int j =0; j < schedule.length; j++) {
                                 if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -2 && j != i ) {
                                    sched.append(animals[schedule[j][0]-1].getName() + " ");
                                       schedule[j] = null;
                                 }
                             }
                                //add the final animal
                                sched.append(animals[schedule[i][0]-1].getName() + ")\n");
                         }
                         else if(schedule[i][1] == -3) {	    		
                             previous = schedule[i][2];
                             //Initializes an integer to hold the number of animals that are having their cage cleaned
                             int num =0;
                             //counts the number of animals having their cage cleaned
                             for (int j =0; j < schedule.length; j++) {
                                if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -3) {
                                    num++;
                                }
                            }
                            //Adding the cage cleaning task to the string
                            sched.append("*" + " Cage Cleaning - " + animals[schedule[i][0] -1].getAnimalType() +"(" + num + ": ");
                                
                             for (int j =0; j < schedule.length; j++) {
                                 if (schedule[j] != null && animals[schedule[i][0]-1].getAnimalType() == animals[schedule[j][0]-1].getAnimalType() && schedule[i][2] == schedule[j][2] && schedule[j][1] == -3 && j != i ) {
                                    sched.append(animals[schedule[j][0]-1].getName() + " ");
                                       schedule[j] = null;
                                 }
                                 
                             }
                                //Adds the names of the animals having their cages cleaned
                                sched.append(animals[schedule[i][0]-1].getName() + ")\n");
                         }


                         
                         // if the task id is not of any special task meaning it is from the data read then do the follwoing 
                         else {
                            //set the previous hour
                         previous = schedule[i][2];
                         //add the animal name, and task to the schedule
                        sched.append("* " + tasks[schedule[i][1]-1].getDescription()  + " - " + animals[schedule[i][0]-1].getName() + "\n");
                     }}
                 }
            }
            }
            //return the schedule string
        return sched.toString();
        
           }
         
             

    
   /**
    * Sets the schedule array.
    * @param schedule a 2D integer array representing the schedule of tasks
    */
    public void setSchedule(int[][] schedule) {
        this.schedule = schedule;
    }

    /**
     * Gets the schedule array.
     * @return a 2D integer array representing the schedule of tasks
    */
    public int[][] getSchedule() {
        return this.schedule;
    }
 
 }