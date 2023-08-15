/** 
* @author Sufyan Ayaz <a>
* href="mailto:sufyan.ayaz@ucalgary.ca">sufyan.ayaz@ucalgary.ca</a>
* @author Taha Khan <a>
* href="mailto:Taha.khan1@uaclgary.ca">Taha.khan1@uaclgary.ca</a>
* @author Hashir Naeem <a>
* href="mailto:hashir.naeem@ucalgary.ca">hashir.naeem@ucalgary.ca</a>
* @author Haris Kashif <a>
* href="mailto:muhammadharis.kashif@ucalgary.ca">muhammadharis.kashif@ucalgary.ca</a>
* @version 1.4
* @since 1.0
*/

package mainCode;

import java.util.*;

public class Volunteer {
   private Schedule scheduleObj;

   /**
    * Volunteer class constructor that initializes the Volunteer object's Schedule
    * object
    * 
    * @param schedule the Schedule object to be assigned to the Volunteer object
    */
   public Volunteer(Schedule schedule) {
       this.scheduleObj = schedule;
   }

   /**
    * Returns the Schedule object associated with the Volunteer object
    * 
    * @return the Schedule object associated with the Volunteer object
    */
   public Schedule getScheduleObj() {
       return this.scheduleObj;
   }

   /**
    * Sets the Schedule object associated with the Volunteer object
    * 
    * @param scheduleObj the Schedule object to be assigned to the Volunteer object
    */
   public void setScheduleObj(Schedule scheduleObj) {
       this.scheduleObj = scheduleObj;
   }

   /**
    * volunteerCheck method checks if a volunteer is needed
    * Goes through array of each type of task and finds incomplete tasks and calls
    * the addVolunteer() function
    * updates the private variable volunteerHours once volunteers are scheduled for
    * any remaining tasks
    * 
    * @return void
    */
   public void volunteerCheck() {
       int[][] force = scheduleObj.getForced();
       int[][] remain = scheduleObj.getRemaining();
       int[] hour = scheduleObj.getHour();

       for (int l = 0; l < hour.length; l++) {
           hour[l] = 60;
       }

       // Initializing volunteers array
       int[] volunteers = new int[0];

       // Initializing volunteer_num variable to use to track the number of volunteers
       int volunteer_num = 0;

       int[][] feed = scheduleObj.getFeed();
       // Iterating through feed array to find unscheduled feed tasks
       for (int i = 0; i < feed.length; i++) {
           if (feed[i] != null) {

               // Adding a volunteer a to a feed task
               scheduleObj.addFeedToSchedule(120);

           }
       }

       // Iterating through remaining array to find unscheduled remaining tasks
       int[][] remaining = scheduleObj.getRemaining();
       for (int i = 0; i < remaining.length; i++) {
           if (remaining[i] != null) {

               // Adding volunteer to remaining schedule
               scheduleObj.addToSchedule(remain, 120);
               scheduleObj.addToScheduleWindowCheck(remain, 120);

           }
       }

       // Iterating through forced array to find unscheduled forced tasks
       int[][] forced = scheduleObj.getForced();
       for (int i = 0; i < forced.length; i++) {
           if (forced[i] != null) {

               // Adding volunteer to forced schedule
               scheduleObj.addToSchedule(force, 120);

           }
       }

       // Iterating through cageClean array to find unscheduled cageClean tasks
       int[][] cageClean = scheduleObj.getCageClean();
       for (int i = 0; i < cageClean.length; i++) {
           if (cageClean[i] != null) {

               // Adding volunteer to a cageClean task
               scheduleObj.addCageCleaningTimes(120);

           }
       }

       // Assigning volunteers array to volunteerHours

       for (int l = 0; l < volunteers.length; l++) {

           if (volunteers[l] == 0) {
               volunteers[l] = 100;
           }

       }

       for (int k = 0; k < 24; k++) {
           if (hour[k] > 60) {
               volunteer_num++;
               volunteers = Arrays.copyOf(volunteers, volunteer_num);
               volunteers[volunteer_num - 1] = k;
           }

       }

       Set<Integer> set = new HashSet<>();
       List<Integer> list = new ArrayList<>();

       for (int i = 0; i < volunteers.length; i++) {
           if (!set.contains(volunteers[i]) && volunteers[i] != 100) {
               set.add(volunteers[i]);
               list.add(volunteers[i]);
           }
       }

       int[] result = new int[list.size()];

       for (int i = 0; i < list.size(); i++) {
           result[i] = list.get(i);
       }

       Arrays.sort(result);
       scheduleObj.setVolunteerHours(result);

   }
} 

