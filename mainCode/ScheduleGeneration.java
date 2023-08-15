

package mainCode;

import java.sql.*;
import java.util.*;
import java.util.Arrays;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ScheduleGeneration is the main class for this wildlife rescue center
 * scheduling
 * project. The purpose of this class is to utilize an SQL datatbase and use the
 * information stored within the databse (including information on animals,
 * tasks,
 * and the treatments that need to be executed) to procure a schedule that the
 * staff
 * at the rescue center can utilize to plan their day accordingly.
 * 
 * The class conatins various methods that can be used to access and extract
 * the information from the database, and then uses methods from other classes
 * to generate the schedule itself, dealing with any complications that can
 * arise.
 * The schedule is then displayed using a graphical user interface also created
 * within
 * this class. The GUI is responsible for displaying the schedule, ensuring the
 * need for
 * any backup volunteers is met, and the user has the ability to update the
 * schedule through
 * the GUI and the methods within the class to deal with any scheduling
 * conflicts. Once the user closes the GUI, the schedule is automatically turned
 * into a .txt file and uploaded to the user's device outside the edu folder.
 */
public class ScheduleGeneration implements ActionListener {
    // Private variables used to interact with the SQL database
    private Connection dbConnect;
    private ResultSet results;

    // Private variables used for the GUI
    private static String scheduleString;
    private static JLabel title;
    private static JLabel updateQuestion;
    private static JLabel continuousUpdates;
    private static JLabel updateValueOne;
    private static JLabel updateValueTwo;
    private static JLabel updateValueThree;
    private static JLabel updateValueFour;
    private static JTextField updateAnswerOne;
    private static JTextField updateAnswerTwo;
    private static JTextField updateAnswerThree;
    private static JTextField updateAnswerFour;
    private static JTextArea displayScheduleFirstHalf;
    private static JTextArea displayScheduleSecondHalf;
    private static JTextArea impossibleSchedule;
    private static JTextArea volunteerCallList;
    private static JButton scheduleBuilder;
    private static JButton closeButton;
    private static JButton volunteerConfirmation;
    private static JButton confirm;
    private static JButton decline;
    private static JButton submission;

    /**
     * @summary Creates instance of ScheduleGeneration
     * @description Simply used to create a new instance of ScheduleGeneration
     * @param None
     * @return None
     */
    public ScheduleGeneration() {
    }

    /**
     * @summary Connects to the SQL Database
     * @description Uses a driver to establish a connection with the MySQL database
     *              ewr on the local
     *              device using oop as the username and password as the password.
     *              If the connection fails, then a stack
     *              trace is printed to allow developer to pinpoint the error and
     *              fix it.
     * @param None
     * @return None
     */
    public void createConnection() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/ewr", "oop", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @summary Gets the ids of all the tasks in the database
     * @description Return an integer list that contains the ids of all the tasks
     *              that rescue the center carries out in a day. The method queries
     *              and gets all the
     *              rows from the TASKS table and the goes through each row, adding
     *              the id in each to
     *              the integer list.
     * @param None
     * @return Returns an integer list containing the ids of all tasks stored in the
     *         databse.
     */
    public List<Integer> selectTaskId() {
        List<Integer> ids = new ArrayList<>();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TASKS");

            while (results.next()) {
                ids.add(results.getInt("TaskID"));
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ids;
    }

    /**
     * @summary Gets the ids of all the animals in the database
     * @description Return an integer list that contains the ids of all the animals
     *              that the rescue center facilitates. The method queries and gets
     *              all the
     *              rows from the ANIMALS table and the goes through each row,
     *              adding the id in each to
     *              the integer list.
     * @param None
     * @return Returns an integer list containing the ids of all animals stored in
     *         the databse.
     */
    public List<Integer> selectAnimalId() {
        List<Integer> ids = new ArrayList<>();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM ANIMALS");

            while (results.next()) {
                ids.add(results.getInt("AnimalID"));
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ids;
    }

    /**
     * @summary Gets the ids of the tasks executed in the treatments to get the
     *          number of treatments
     *          carried out in the day.
     * @description Return an integer list that contains the ids of all the tasks
     *              that the rescue center carries out in the days' treatments. The
     *              method queries and gets all the
     *              rows from the TREATMENTS table and the goes through each row,
     *              adding the task id in each to
     *              the integer list. This list is then used to know the number of
     *              treatments in the day
     * @param None
     * @return Returns an integer list containing the ids of all tasks used in the
     *         treatments.
     */
    public List<Integer> getTreatmentSize() {
        List<Integer> size = new ArrayList<>();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TREATMENTS");

            while (results.next()) {
                size.add(results.getInt("TaskID"));
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return size;
    }

    /**
     * @summary Gets the description of the tasks.
     * @description Return a string of the description of the specific task
     *              of which the id is passed in. The method queries the TASKS table
     *              and finds the
     *              task which has the same id corresponding to the one passed in,
     *              and returns the
     *              description of that task.
     * @param id which is an integer of the id of the task of which we want the
     *           description
     * @return Returns a string of the description of the chosen task
     */
    public String selectTaskDescription(int id) {
        StringBuffer taskDescription = new StringBuffer();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TASKS WHERE TaskID=" + id);

            while (results.next()) {
                taskDescription.append(results.getString("Description"));
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return taskDescription.toString();
    }

    /**
     * @summary Gets the duration of the tasks.
     * @description Return a integer of the duration of the specific task
     *              of which the id is passed in. The method queries the TASKS table
     *              and finds the
     *              task which has the same id corresponding to the one passed in,
     *              and returns the
     *              duration of that task.
     * @param id which is an integer of the id of the task of which we want the
     *           duration
     * @return Returns a integer of the duration of the chosen task
     */
    public int selectTaskDuration(int id) {
        int duration = 0;

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TASKS WHERE TaskID=" + id);

            while (results.next()) {
                duration = duration + results.getInt("Duration");
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return duration;
    }

    /**
     * @summary Gets the max window of the tasks.
     * @description Return a integer of the max window of the specific task
     *              of which the id is passed in. The method queries the TASKS table
     *              and finds the
     *              task which has the same id corresponding to the one passed in,
     *              and returns the
     *              max window of that task.
     * @param id which is an integer of the id of the task of which we want the
     *           max window
     * @return Returns a integer of the max duration of the chosen task
     */
    public int selectTaskMax(int id) {
        int max = 0;
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TASKS WHERE TaskID=" + id);

            while (results.next()) {
                max = max + results.getInt("MaxWindow");
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return max;
    }

    /**
     * @summary Gets the name of the animal.
     * @description Return a string of the name of the specific animal
     *              of which the id is passed in. The method queries the ANIMALS
     *              table
     *              and finds the
     *              animal which has the same id corresponding to the one passed in,
     *              and returns the
     *              name of that animal.
     * @param id which is an integer of the id of the animal of which we want the
     *           name
     * @return Returns a string of the name of the chosen animal
     */
    public String selectAnimalName(int id) {
        StringBuffer animalName = new StringBuffer();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM ANIMALS WHERE AnimalID=" + id);

            while (results.next()) {
                animalName.append(results.getString("AnimalNickname"));
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animalName.toString();
    }

    /**
     * @summary Gets the species of the animal.
     * @description Return a string of the species of the specific animal
     *              of which the id is passed in. The method queries the ANIMALS
     *              table
     *              and finds the
     *              animal which has the same id corresponding to the one passed in,
     *              and returns the
     *              species of that animal.
     * @param id which is an integer of the id of the animal of which we want the
     *           species
     * @return Returns a string of the species of the chosen animal
     */
    public String selectAnimalSpecies(int id) {
        StringBuffer animalSpecies = new StringBuffer();

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM ANIMALS WHERE AnimalID=" + id);

            while (results.next()) {
                animalSpecies.append(results.getString("AnimalSpecies"));
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animalSpecies.toString();
    }

    /**
     * @summary Gets all the treatments and their corresponding information.
     * @description Return a 2D integer array that contains all the treatments that
     *              the
     *              rescue center has to carry out in the day in the first
     *              dimension, and the information
     *              corresponding to each treatment in the second dimension. The
     *              method queries the
     *              TREATMENTS table to extract all the information.
     * @param size which is an integer that is the number of treatments in the
     *             TREATMENTS
     *             table and is used to determine the size of the first dimension of
     *             the array.
     * @return Returns a 2D integer array of the treatments and the corresponding
     *         information
     *         of the treatments.
     */
    public int[][] populateTreatments(int size) {
        int[][] treatments = new int[size][3];
        int i = 0;

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TREATMENTS");

            while (results.next()) {
                treatments[i][0] = results.getInt("AnimalID");
                treatments[i][1] = results.getInt("TaskID");
                treatments[i][2] = results.getInt("StartHour");
                i++;
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return treatments;
    }

    /**
     * @summary Method that updates the start hour of a treatment.
     * @description Method that is meant to allow the user to update a treatment
     *              in the TREATMENTS table by changing the start hour of the
     *              treatment. This
     *              is meant to allow the user to make modifications to the
     *              treatments so that any conflicts
     *              that occur in the schedule can be solved.
     * @param animalID     which is an integer that identifies the animal to which
     *                     the treatment applies to.
     * @param taskID       which is an integer that identifies the task which is
     *                     executed in the treatment.
     * @param oldStartHour which is an integer that represents the starting hour
     *                     which the treatment used to start at.
     * @param newStartHour which is an integer that represents the new starting hour
     *                     that the treatment should start at.
     * @return None
     */
    public void updateTreatment(int animalID, int taskID, int oldStartHour, int newStartHour) {

        try {

            String query = "UPDATE TREATMENTS SET AnimalID=?, TaskID=?, StartHour=? WHERE AnimalID = ? AND TaskID = ? AND StartHour = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            myStmt.setInt(1, animalID);
            myStmt.setInt(2, taskID);
            myStmt.setInt(3, newStartHour);
            myStmt.setInt(4, animalID);
            myStmt.setInt(5, taskID);
            myStmt.setInt(6, oldStartHour);

            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @summary Method used to close anything corresponding to SQL databse queries
     * @description Closes the varibles results and dbConnect.
     * @param None
     * @return None
     */
    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @summary Method used to close anything corresponding to SQL databse queries
     * @description This is the main function of the class that accesses the SQL
     *              database and extracts
     *              all the necessary information using the methods defined in the
     *              ScheduleGeneration class.
     *              The main then uses the extracted information in collaboration
     *              with methods from other classes
     *              to generate the schedule and verify it to ensure the schedule is
     *              valid and any backup volunteers
     *              are recognized. The main is also used to create the GUI that
     *              will display the schedule, inform the
     *              user of any backup volunteers the need to contact - ensuring
     *              that they do contact them, and also
     *              inform the user of any scheduling conflicts, giving them the
     *              opportunity to update the database to fix the conflict.
     * @param args which is an argument passed in through the command line but isn't
     *             used.
     * @return None
     */
    public static void main(String args[]) {

        // Create a new instance and connect to the ewr SQL database.
        ScheduleGeneration mySchedule = new ScheduleGeneration();
        mySchedule.createConnection();

        // Gets a integer list of the ids of each task in the database
        List<Integer> allIds = mySchedule.selectTaskId();

        // Create a Task array that creates new instances of Tasks for each task.
        Tasks[] task = new Tasks[allIds.size()];
        // For each task, use its id to gets its description, duration, and max window
        // and use it to create a new instance of Tasks.
        for (int i = 1; i <= allIds.size(); i++) {
            String description = mySchedule.selectTaskDescription(i);
            int duration = mySchedule.selectTaskDuration(i);
            int max = mySchedule.selectTaskMax(i);
            task[i - 1] = new Tasks(i, description, duration, max);

        }

        // Gets a integer list of the ids of each animal in the database
        List<Integer> allAnimalIds = mySchedule.selectAnimalId();

        // Create a Animal array that creates new instances of Animal for each animal.
        Animal[] animals = new Animal[allAnimalIds.size()];

        // For each animal, use its id to get its name and species and use them to
        // create a new instance of Animal.
        for (int j = 1; j <= allAnimalIds.size(); j++) {
            String nickname = mySchedule.selectAnimalName(j);
            String species = mySchedule.selectAnimalSpecies(j);
            animals[j - 1] = new Animal(j, nickname, species);
        }

        // Get the number of treatments in the database.
        List<Integer> treatmentSize = mySchedule.getTreatmentSize();
        // Use the size to create a 2D array with each treatment and its corresponding
        // information.
        int[][] treatmentInfo = mySchedule.populateTreatments(treatmentSize.size());

        // Create a new instance of Schedule using the animals, tasks, and treatments
        Schedule scheduleValues = new Schedule(task, animals, treatmentInfo);

        // Use the new instance of Schedule and the methods from the Schedule class to
        // create the schedule.
        int[] i = scheduleValues.getManditory();
        scheduleValues.populateForcedAndRemaining();
        int[][] rest = scheduleValues.getForced();
        int[][] remain = scheduleValues.getRemaining();
        scheduleValues.addToSchedule(rest, 60);
        scheduleValues.addToSchedule(remain, 60);
        boolean forced = scheduleValues.addToScheduleWindowCheck(rest, 60);
        boolean rem = scheduleValues.addToScheduleWindowCheck(remain, 60);
        scheduleValues.populateFeed();
        int[][] feed = scheduleValues.getFeed();
        scheduleValues.addFeedToSchedule(60);
        scheduleValues.addCageCleaningTimes(60);
        // scheduleValues.volunteerCheck();
        Volunteer vol = new Volunteer(scheduleValues);
        vol.volunteerCheck();

        int[] volunteers = scheduleValues.getVolunteerHours();
        int schedule[][] = scheduleValues.getSchedule();
        scheduleValues.sortAndNull(schedule);

        // Get the variables corresponding to the created schedule and send them to the
        // PrintSchedule class.
        // The PrintSchedule will then create the schedule that will be displayed to the
        // user. This can either be a schedule without volunteers, a schedule with
        // volunteers, or a schedule that has conflict that needs to be resolved.
        int[][] forcedFinal = scheduleValues.getForced();
        int[][] remainingFinal = scheduleValues.getRemaining();
        int[][] scheduleFinal = scheduleValues.getSchedule();
        int[][] feedFinal = scheduleValues.getFeed();
        int[][] cageClean = scheduleValues.getCageClean();
        PrintSchedule print = new PrintSchedule(task, animals, forcedFinal, remainingFinal, scheduleFinal, feedFinal,
                treatmentInfo, cageClean);
        scheduleString = print.printSchedule(volunteers);

        // When printing the schedule, the schedule could potentially be too long, so it
        // is split into two halves that can be displayed more clearly and in a visually
        // appealing way to the user.
        String[] lines = scheduleString.split("\\r?\\n");
        int numLines = lines.length;
        int halfNumLines = numLines / 2;
        String firstHalf = String.join("\n", Arrays.copyOfRange(lines, 0, halfNumLines));
        String secondHalf = String.join("\n", Arrays.copyOfRange(lines, halfNumLines, numLines));

        // Create the frame, panel, and declare/instantiate the elements that will be
        // used at any point in the GUI.
        JFrame exteriorFrame = new JFrame();
        JPanel initialPanel = new JPanel();
        exteriorFrame.setSize(1000, 1000);
        exteriorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        exteriorFrame.add(initialPanel);
        exteriorFrame.setTitle("ENSF 380 Final Project - Group 26");
        initialPanel.setLayout(null);
        title = new JLabel("Welcome to the Example Wildlife Resuce Schedule Generator!");

        updateQuestion = new JLabel("Enter the required information for the task you want to update: ");
        updateValueOne = new JLabel("AnimalID");
        updateValueTwo = new JLabel("TaskID");
        updateValueThree = new JLabel("Old StartHour");
        updateValueFour = new JLabel("New StartHour");
        continuousUpdates = new JLabel("Do you want to execute another update or not? ");

        displayScheduleFirstHalf = new JTextArea();
        displayScheduleFirstHalf.setText(firstHalf);
        displayScheduleSecondHalf = new JTextArea();
        displayScheduleSecondHalf.setText(secondHalf);

        impossibleSchedule = new JTextArea();
        volunteerCallList = new JTextArea();

        updateAnswerOne = new JTextField(20);
        updateAnswerTwo = new JTextField(20);
        updateAnswerThree = new JTextField(20);
        updateAnswerFour = new JTextField(20);

        scheduleBuilder = new JButton("Press Me to Generate Your Schedule");
        volunteerConfirmation = new JButton("I have called the volunteer(s)");
        confirm = new JButton("Yes");
        decline = new JButton("No");
        submission = new JButton("Submit");
        closeButton = new JButton("Close Schedule");

        // Setting bounds for the some of the elements for the initial display of the
        // GUI.
        closeButton.setBounds(2000, 2000, 0, 0);
        title.setBounds(300, 0, 500, 50);
        scheduleBuilder.setBounds(350, 300, 250, 40);
        volunteerConfirmation.setBounds(2000, 2000, 0, 0);
        confirm.setBounds(2000, 2000, 0, 0);
        decline.setBounds(2000, 2000, 0, 0);
        submission.setBounds(2000, 2000, 0, 0);

        // Adding the elements to the panel of the GUI that the user will see.
        initialPanel.add(title);
        initialPanel.add(continuousUpdates);
        initialPanel.add(updateQuestion);
        initialPanel.add(updateValueOne);
        initialPanel.add(updateValueTwo);
        initialPanel.add(updateValueThree);
        initialPanel.add(updateValueFour);
        initialPanel.add(updateAnswerOne);
        initialPanel.add(updateAnswerTwo);
        initialPanel.add(updateAnswerThree);
        initialPanel.add(updateAnswerFour);
        initialPanel.add(scheduleBuilder);
        initialPanel.add(displayScheduleFirstHalf);
        initialPanel.add(displayScheduleSecondHalf);
        initialPanel.add(volunteerCallList);
        initialPanel.add(volunteerConfirmation);
        initialPanel.add(confirm);
        initialPanel.add(decline);
        initialPanel.add(submission);
        initialPanel.add(impossibleSchedule);
        initialPanel.add(closeButton);

        // Set the visibilities of the elements that need to be shown or hidden in the
        // intial display of the GUI.
        volunteerConfirmation.setVisible(false);
        volunteerCallList.setVisible(false);
        impossibleSchedule.setVisible(false);
        continuousUpdates.setVisible(false);
        updateQuestion.setVisible(false);
        updateValueOne.setVisible(false);
        updateValueTwo.setVisible(false);
        updateValueThree.setVisible(false);
        updateValueFour.setVisible(false);
        updateAnswerOne.setVisible(false);
        updateAnswerTwo.setVisible(false);
        updateAnswerThree.setVisible(false);
        updateAnswerFour.setVisible(false);
        confirm.setVisible(false);
        submission.setVisible(false);
        closeButton.setVisible(false);

        // Add in a mechanism to the GUI such that if a backup volunteer is found to be
        // used at some point in the schedule, the user is informed of what hours the
        // backup volunteer needs to be called, and can only continue if they have
        // called the backup volunteers and confirmed that they have.
        if (volunteers.length > 0) {
            StringBuffer str = new StringBuffer();
            closeButton.setVisible(false);
            volunteerConfirmation.setVisible(true);
            str.append("Please Call Backup Volunteers for the following times: ");
            for (int s = 0; s < volunteers.length; s++) {
                str.append(volunteers[s] + ":00");

                if (s == volunteers.length - 1) {
                    str.append(".");
                } else {
                    str.append(", ");
                }
            }
            volunteerCallList.setText(str.toString());
            volunteerCallList.setVisible(true);

        } else {
            volunteerConfirmation.setVisible(false);
            volunteerCallList.setVisible(false);
            closeButton.setVisible(true);
        }

        // Adding action events to the buttons of the GUI so that once they are clicked
        // an action is eexecuted.
        scheduleBuilder.addActionListener(new ScheduleGeneration());
        closeButton.addActionListener(new ScheduleGeneration());
        volunteerConfirmation.addActionListener(new ScheduleGeneration());
        confirm.addActionListener(new ScheduleGeneration());
        decline.addActionListener(new ScheduleGeneration());
        submission.addActionListener(new ScheduleGeneration());

        // Actually display the GUI to the user.
        exteriorFrame.setVisible(true);

        // Close the connection to the ewr SQL database.
        mySchedule.close();

    }

    /**
     * @summary Method used to execute actions once specific buttons are clicked.
     * @description This is an override method that executes/turns off specific
     *              features
     *              of the GUI once specific actions are executed in the GUI such as
     *              the click of a button.
     * @param e which is the event that has occurred and is recorded once a button
     *          as been clicked.
     * @return None
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // If the button to generate the schedule, on the initial display of the GUI, is
        // pressed...
        if (e.getSource() == scheduleBuilder) {
            // If the schedule that is generated is conflicting...
            if (scheduleString.contains("Not Possible")) {
                // Hide the textAreas that would show the schedule, and show to the user a
                // message that tells them what hours are having a conflict and due to what
                // tasks the conflict is occurring.
                displayScheduleFirstHalf.setVisible(false);
                displayScheduleSecondHalf.setVisible(false);
                displayScheduleFirstHalf.setBounds(2000, 2000, 0, 0);
                displayScheduleSecondHalf.setBounds(2000, 2000, 0, 0);
                impossibleSchedule.setVisible(true);
                impossibleSchedule.setText(scheduleString);
                impossibleSchedule.setBounds(0, 0, 1000, 250);

                // Display to the user the prompts and textFields in which they can enter values
                // to update the treatments in the database to resolve the conflict.
                updateQuestion.setVisible(true);
                updateQuestion.setBounds(50, 300, 500, 50);
                updateValueOne.setVisible(true);
                updateValueOne.setBounds(100, 350, 200, 50);
                updateValueTwo.setVisible(true);
                updateValueTwo.setBounds(100, 400, 200, 50);
                updateValueThree.setVisible(true);
                updateValueThree.setBounds(100, 450, 200, 50);
                updateValueFour.setVisible(true);
                updateValueFour.setBounds(100, 500, 200, 50);

                updateAnswerOne.setVisible(true);
                updateAnswerOne.setBounds(300, 350, 50, 50);
                updateAnswerTwo.setVisible(true);
                updateAnswerTwo.setBounds(300, 400, 50, 50);
                updateAnswerThree.setVisible(true);
                updateAnswerThree.setBounds(300, 450, 50, 50);
                updateAnswerFour.setVisible(true);
                updateAnswerFour.setBounds(300, 500, 50, 50);

                // Display the submission button the user can press to execute the update.
                submission.setVisible(true);
                submission.setBounds(100, 550, 150, 40);
                scheduleBuilder.setVisible(false);
                title.setVisible(false);
            } else {
                // If the schedule generated is conflict-free, it is displayed to the user.
                displayScheduleFirstHalf.setBounds(0, 0, 500, 650);
                displayScheduleFirstHalf.setEditable(false);
                displayScheduleSecondHalf.setEditable(false);
                displayScheduleSecondHalf.setBounds(500, 0, 500, 650);
                volunteerCallList.setBounds(0, 650, 1000, 25);
                volunteerConfirmation.setBounds(700, 900, 250, 40);
                closeButton.setBounds(700, 900, 200, 40);
                scheduleBuilder.setVisible(false);
                title.setVisible(false);
            }
        } // If the user presses the button to confirm they have called the backup
          // volunteers...
        else if (e.getSource() == volunteerConfirmation) {
            // The button to confirm whether the backup volunteers have been called is gone
            // and is replaced with the button to close the schedule.
            volunteerConfirmation.setVisible(false);
            volunteerCallList.setVisible(false);
            closeButton.setVisible(true);
        } // If the user presses the button to submit the information to update the
          // database...
        else if (e.getSource() == submission) {
            // Create a new connection to the database.
            ScheduleGeneration myScheduleConnect = new ScheduleGeneration();
            myScheduleConnect.createConnection();

            // Get the values that the user entered for the update and execute the update.
            String textOne = updateAnswerOne.getText();
            String textTwo = updateAnswerTwo.getText();
            String textThree = updateAnswerThree.getText();
            String textFour = updateAnswerFour.getText();
            int aID = Integer.parseInt(textOne);
            int tID = Integer.parseInt(textTwo);
            int oldSH = Integer.parseInt(textThree);
            int newSH = Integer.parseInt(textFour);
            myScheduleConnect.updateTreatment(aID, tID, oldSH, newSH);

            // Hide the submission button and display a yes or no button and message to
            // enable the user to make more than one update if they want.
            submission.setVisible(false);
            submission.setBounds(2000, 2000, 0, 0);
            continuousUpdates.setVisible(true);
            continuousUpdates.setBounds(100, 550, 500, 50);
            confirm.setVisible(true);
            confirm.setBounds(100, 600, 150, 40);
            decline.setVisible(true);
            decline.setBounds(250, 600, 150, 40);

            // Close the connection.
            myScheduleConnect.close();

        } // If the close button is pressed...
        else if (e.getSource() == closeButton) {
            // Once the user closes the GUI, the program then creates a schedule.txt file if
            // it doesn't exist) and writes the the schedule into the file, putting the file
            // in the current directory they are in.
            try {
                File currentDir = new File(".");
                File newSchedFile = new File(currentDir.getCanonicalPath() + File.separator + "schedule.txt");

                if (newSchedFile.exists()) {
                    FileWriter writer = new FileWriter(newSchedFile);
                    writer.write("");
                    writer.close();
                } else {
                    if (newSchedFile.createNewFile()) {
                        System.out.println("File created");
                    } else {
                        System.out.println("File already exists.");
                    }
                }

                FileWriter writer = new FileWriter(newSchedFile);
                writer.write(scheduleString);
                writer.close();

            } catch (IOException event) {
                event.printStackTrace();
            }
            // The GUI and the schedule are closed.
            System.exit(0);
        } // If the yes button is pressed for more updates...
        else if (e.getSource() == confirm) {
            // The yes or no buttons are hidden, the submission button is displayed again,
            // and the textFields for the update information is cleared.
            confirm.setVisible(false);
            confirm.setBounds(2000, 2000, 0, 0);
            decline.setVisible(false);
            decline.setBounds(2000, 2000, 0, 0);
            continuousUpdates.setVisible(false);
            continuousUpdates.setBounds(2000, 2000, 0, 0);
            submission.setVisible(true);
            submission.setBounds(100, 550, 150, 40);
            updateAnswerOne.setText("");
            updateAnswerTwo.setText("");
            updateAnswerThree.setText("");
            updateAnswerFour.setText("");
        } // If the no button is pressed for more updates...
        else if (e.getSource() == decline) {
            // Get rid of all the buttons and textFields and regenerate the schedule.
            confirm.setVisible(false);
            confirm.setBounds(2000, 2000, 0, 0);
            decline.setVisible(false);
            decline.setBounds(2000, 2000, 0, 0);
            submission.setVisible(false);
            submission.setBounds(2000, 2000, 0, 0);
            continuousUpdates.setVisible(false);
            continuousUpdates.setBounds(2000, 2000, 0, 0);
            updateValueOne.setVisible(false);
            updateValueTwo.setVisible(false);
            updateValueThree.setVisible(false);
            updateValueFour.setVisible(false);
            updateAnswerOne.setVisible(false);
            updateAnswerTwo.setVisible(false);
            updateAnswerThree.setVisible(false);
            updateAnswerFour.setVisible(false);
            ScheduleGeneration.main(new String[] {});

        }
    }
}