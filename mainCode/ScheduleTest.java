

package mainCode;

import org.junit.*;
import static org.junit.Assert.*;

import java.beans.Transient;
import java.time.LocalDate;

public class ScheduleTest {
    public ScheduleTest() {
    }

    @Test
    // testAnimalConstructorForCOYOTE - tests to make sure that the Animal
    // constructor
    // creates a Coyote object properly
    public void testAnimalConstructorForCOYOTE() {
        Animal animal = new Animal(1, "Loner", "coyote");
        StringBuffer check = new StringBuffer();
        check.append("coyote, crepuscular, " + String.valueOf(5) + ", " + String.valueOf(10) + ", " + String.valueOf(5)
                + ", " + String.valueOf(19) + ", " + String.valueOf(3) + ", " + String.valueOf(false));
        StringBuffer test = new StringBuffer();
        test.append(animal.getAnimalType() + ", " + animal.getPeakActivity() + ", "
                + String.valueOf(animal.getFeedDuration()) + ", " + String.valueOf(animal.getFeedPrep()) + ", "
                + String.valueOf(animal.getCageCleanTime()) + ", " + String.valueOf(animal.getStartTime()) + ", "
                + String.valueOf(animal.getWindow()) + ", " + String.valueOf(animal.getCageCleaned()));
        assertEquals("The constructor did not create the animal object properly for coyote ", check.toString(),
                test.toString());
    }

    @Test
    // testAnimalConstructorForFOX - tests to make sure that the Animal constructor
    // creates a Fox object properly
    public void testAnimalConstructorForFOX() {
        Animal animal = new Animal(7, "Slinky", "fox");
        StringBuffer check = new StringBuffer();
        check.append("fox, nocturnal, " + String.valueOf(5) + ", " + String.valueOf(5) + ", " + String.valueOf(5) + ", "
                + String.valueOf(0) + ", " + String.valueOf(3) + ", " + String.valueOf(false));
        StringBuffer test = new StringBuffer();
        test.append(animal.getAnimalType() + ", " + animal.getPeakActivity() + ", "
                + String.valueOf(animal.getFeedDuration()) + ", " + String.valueOf(animal.getFeedPrep()) + ", "
                + String.valueOf(animal.getCageCleanTime()) + ", " + String.valueOf(animal.getStartTime()) + ", "
                + String.valueOf(animal.getWindow()) + ", " + String.valueOf(animal.getCageCleaned()));
        assertEquals("The constructor did not create the animal object properly for fox ", check.toString(),
                test.toString());
    }

    @Test
    // testAnimalConstructorForPORCUPINE - tests to make sure that the Animal
    // constructor creates a Porcupine object properly
    public void testAnimalConstructorForPORCUPINE() {
        Animal animal = new Animal(12, "Shadow", "porcupine");
        StringBuffer check = new StringBuffer();
        check.append(
                "porcupine, crepuscular, " + String.valueOf(5) + ", " + String.valueOf(0) + ", " + String.valueOf(10)
                        + ", " + String.valueOf(19) + ", " + String.valueOf(3) + ", " + String.valueOf(false));
        StringBuffer test = new StringBuffer();
        test.append(animal.getAnimalType() + ", " + animal.getPeakActivity() + ", "
                + String.valueOf(animal.getFeedDuration()) + ", " + String.valueOf(animal.getFeedPrep()) + ", "
                + String.valueOf(animal.getCageCleanTime()) + ", " + String.valueOf(animal.getStartTime()) + ", "
                + String.valueOf(animal.getWindow()) + ", " + String.valueOf(animal.getCageCleaned()));
        assertEquals("The constructor did not create the animal object properly for porcupine ", check.toString(),
                test.toString());
    }

    @Test
    // testAnimalConstructorForRACCOON - tests to make sure that the Animal
    // constructor creates a Raccoon object properly
    public void testAnimalConstructorForRACCOON() {
        Animal animal = new Animal(16, "Ruler", "raccoon");
        StringBuffer check = new StringBuffer();
        check.append("raccoon, nocturnal, " + String.valueOf(5) + ", " + String.valueOf(0) + ", " + String.valueOf(5)
                + ", " + String.valueOf(0) + ", " + String.valueOf(3) + ", " + String.valueOf(false));
        StringBuffer test = new StringBuffer();
        test.append(animal.getAnimalType() + ", " + animal.getPeakActivity() + ", "
                + String.valueOf(animal.getFeedDuration()) + ", " + String.valueOf(animal.getFeedPrep()) + ", "
                + String.valueOf(animal.getCageCleanTime()) + ", " + String.valueOf(animal.getStartTime()) + ", "
                + String.valueOf(animal.getWindow()) + ", " + String.valueOf(animal.getCageCleaned()));
        assertEquals("The constructor did not create the animal object properly for raccoon ", check.toString(),
                test.toString());
    }

    @Test
    // testAnimalConstructorForBEAVER - tests to make sure that the Animal
    // constructor
    // creates a Beaver object properly
    public void testAnimalConstructorForBEAVER() {
        Animal animal = new Animal(17, "Shot", "beaver");
        StringBuffer check = new StringBuffer();
        check.append("beaver, diurnal, " + String.valueOf(5) + ", " + String.valueOf(0) + ", " + String.valueOf(5)
                + ", " + String.valueOf(8) + ", " + String.valueOf(3) + ", " + String.valueOf(false));
        StringBuffer test = new StringBuffer();
        test.append(animal.getAnimalType() + ", " + animal.getPeakActivity() + ", "
                + String.valueOf(animal.getFeedDuration()) + ", " + String.valueOf(animal.getFeedPrep()) + ", "
                + String.valueOf(animal.getCageCleanTime()) + ", " + String.valueOf(animal.getStartTime()) + ", "
                + String.valueOf(animal.getWindow()) + ", " + String.valueOf(animal.getCageCleaned()));
        assertEquals("The constructor did not create the animal object properly for beaver ", check.toString(),
                test.toString());
    }

    @Test
    // testAnimalConstructorException - tests to make sure whether an
    // IllegalArgumentException was thrown
    // when a new Animal Object of invalid Animal type is made
    public void testAnimalConstructorException() {

        boolean passed = false;

        try {
            Animal animal = new Animal(1, "Loner", "Human");

        } catch (IllegalArgumentException e) {
            passed = true;
        }

        catch (Exception e) {
        }
        assertFalse("Invalid animal type did not throw an IllegalArgumentException", passed);

    }

    @Test
    // testAnimalConstructor - tests to make sure that the Animal constructor
    // creates an Animal object properly.
    public void testAnimalConstructor() {
        Animal animal1 = new Animal(1, "Score", "Fox");
        assertNotNull("Animal constructor did not create an object when given valid information .", animal1);
    }

    @Test
    // testAnimalConstructorCapitalization - tests to make sure that the Animal
    // constructor
    // creates an Animal object properly when animal type is capitalized.
    public void testAnimalConstructorCapitalization() {
        Animal animal1 = new Animal(1, "Score", "FOX");
        assertNotNull("Animal constructor did not create an object when given valid information .", animal1);
    }

    @Test
    // testSetName - tests the setName method by creating a new Animal object and
    // checking if setName() properly updates the animal name.
    public void testSetName() {
        Animal animal = new Animal(1, "Loner", "coyote");
        animal.setName("John");
        String correctName = "John";
        assertEquals("The correct animal name was not set ", correctName, animal.getName());
    }

    @Test
    // testGetName - tests the getName method by creating a new Animal object and
    // checking if getName() retrieves animal name properly.
    public void testGetName() {
        Animal animal = new Animal(1, "Loner", "coyote");
        String expectedName = "Loner";
        String actualName = animal.getName();
        assertEquals("The correct animal name was not retrieved ", expectedName, actualName);
    }

    @Test
    // testTasksConstructor1 - tests to make sure that the Tasks constructor
    // creates an Tasks object properly.
    public void testTasksConstructor1() {
        Tasks task = new Tasks(5, "Flush neck wound", 25, 1);
        assertNotNull("Tasks constructor did not create an object when given valid information .", task);
    }

    @Test
    // testGetTaskID - tests the getTaskID method by creating a new Task object and
    // checking if getTaskID() retrieves TaskID name properly.
    public void testGetTaskID() {
        Tasks task = new Tasks(1, "Task Description", 30, 5);
        int correctID = 1;
        assertEquals("The correct taskID was not retrieved ", correctID, task.getTaskID());
    }

    @Test
    // testsetTaskID - tests the setTaskID method by creating a new Task object and
    // checking if setTaskID() properly updates the task id.
    public void testSetTaskID() {
        Tasks task = new Tasks(1, "Task Description", 30, 5);
        task.setId(2);
        int correctID = 2;
        assertEquals("The correct taskID name was not set ", correctID, task.getTaskID());
    }

    @Test
    // testTasksConstructor - tests to make sure that the Tasks constructor
    // creates an Tasks object properly with the correct arguments.
    public void testTasksConstructor() {
        StringBuilder check = new StringBuilder();
        check.append(String.valueOf(1) + ", Task Description, " + String.valueOf(10) + ", " + String.valueOf(5));
        int id = 1;
        String desc = "Task Description";
        int duration = 10;
        int maxWindow = 5;
        Tasks task = new Tasks(id, desc, duration, maxWindow);

        StringBuilder test = new StringBuilder();
        test.append(String.valueOf(task.getTaskID()) + ", " + task.getDescription() + ", "
                + String.valueOf(task.getDuration()) + ", " + String.valueOf(task.getMaxWindow()));
        assertEquals("The constructor did not create the task object properly with correct arguments ",
                check.toString(), test.toString());
    }

    @Test
    // testScheduleAnimalIDException - tests to make sure that there are no
    // duplicate
    // animal ids.
    public void testScheduleAnimalIDException() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(1, "Animal2", "porcupine") };
        int[][] treatment = { { 6, 1, 0 }, { 6, 1, 2 } };

        boolean passed = false;
        try {
            Schedule s = new Schedule(tasks, animals, treatment);
        } catch (IllegalStateException e) {
            passed = true;
        } catch (Exception e) {
        }
        assertTrue("Schedule found duplicate animal ids and threw an IllegalStateException",
                passed);
    }

    @Test
    // testScheduleTaskIDException - tests to make sure that there are no duplicate
    // task ids.
    public void testScheduleTaskIDException() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(1, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatment = { { 6, 1, 0 }, { 6, 1, 2 } };

        boolean passed = false;
        try {
            Schedule s = new Schedule(tasks, animals, treatment);
        } catch (IllegalStateException e) {
            passed = true;
        } catch (Exception e) {
        }
        assertTrue("Schedule found duplicate task ids and threw an IllegalStateException", passed);
    }

    @Test
    // testScheduleConstructor - tests to make sure that the Schedule constructor
    // creates a schedule object properly.
    public void testScheduleConstructor() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatments = { { 6, 1, 0 }, { 6, 1, 2 } };

        Schedule schedule = new Schedule(tasks, animals, treatments);
        assertNotNull("Schedule constructor did not create an object when given valid information .", schedule);
    }

    @Test
    // testGetAnimals - tests the getAnimals method by creating a new schedule
    // object
    // and
    // checking if getAnimals() retrieves the animal properly.
    public void testGetAnimals() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatments = { { 6, 1, 0 }, { 6, 1, 2 } };

        Schedule schedule = new Schedule(tasks, animals, treatments);
        assertEquals("The correct animal was not retrieved", animals, schedule.getAnimals());
    }

    @Test
    // testSetAnimals - tests the SetAnimals method by creating a new schedule
    // object
    // and
    // checking if SetAnimals() properly updates the animal.
    public void testSetAnimal() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatments = { { 6, 1, 0 }, { 6, 1, 2 } };
        Animal[] animals1 = { new Animal(3, "Animal3", "raccoon"), new Animal(4, "Animal4", "fox") };

        Schedule schedule = new Schedule(tasks, animals, treatments);
        schedule.setAnimals(animals1);
        assertEquals("The correct animal was not set", animals1, schedule.getAnimals());
    }

    @Test
    // testScheduleConstructorException1 - tests to make sure whether an
    // IllegalArgumentException was thrown
    // when a new schedule object of invalid tasks is made.
    public void testScheduleConstructorException1() {

        Tasks[] tasks = null;
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatments = { { 6, 1, 0 }, { 6, 1, 2 } };

        boolean passed = false;

        try {
            Schedule schedule = new Schedule(tasks, animals, treatments);

        } catch (IllegalArgumentException e) {
            passed = true;
        }

        catch (Exception e) {
        }
        assertTrue("Invalid Schedule type did not throw an IllegalArgumentException", passed);

    }

    @Test
    // testScheduleConstructorException2 - tests to make sure whether an
    // IllegalArgumentException was thrown
    // when a new schedule object of invalid animals is made.
    public void testScheduleConstructorException2() {

        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = null;
        int[][] treatments = { { 6, 1, 0 }, { 6, 1, 2 } };

        boolean passed = false;

        try {
            Schedule schedule = new Schedule(tasks, animals, treatments);

        } catch (IllegalArgumentException e) {
            passed = true;
        }

        catch (Exception e) {
        }
        assertTrue("Invalid Schedule type did not throw an IllegalArgumentException", passed);

    }

    @Test
    // testScheduleConstructorException3 - tests to make sure whether an
    // IllegalArgumentException was thrown
    // when a new schedule object of empty animals is made.
    public void testScheduleConstructorException3() {

        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = new Animal[0];
        int[][] treatments = { { 6, 1, 0 }, { 6, 1, 2 } };

        boolean passed = false;

        try {
            Schedule schedule = new Schedule(tasks, animals, treatments);

        } catch (IllegalArgumentException e) {
            passed = true;
        }

        catch (Exception e) {
        }
        assertTrue("Invalid Schedule type did not throw an IllegalArgumentException", passed);

    }

    @Test
    // testScheduleConstructorException4 - tests to make sure whether an
    // IllegalArgumentException was thrown
    // when a new schedule object of invalid treatments is made.
    public void testScheduleConstructorException4() {

        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatments = null;

        boolean passed = false;

        try {
            Schedule schedule = new Schedule(tasks, animals, treatments);

        } catch (IllegalArgumentException e) {
            passed = true;
        }

        catch (Exception e) {
        }
        assertTrue("Invalid Schedule type did not throw an IllegalArgumentException", passed);

    }

    @Test
    // testScheduleConstructorException5 - tests to make sure whether an
    // IllegalArgumentException was thrown
    // when a new schedule object of empty treatments is made.
    public void testScheduleConstructorException5() {

        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(1, "Animal2", "porcupine") };
        int[][] treatments = new int[0][0];

        boolean passed = false;

        try {
            Schedule schedule = new Schedule(tasks, animals, treatments);

        } catch (IllegalArgumentException e) {
            passed = true;
        }

        catch (Exception e) {
        }
        assertTrue("Invalid Schedule type did not throw an IllegalArgumentException", passed);

    }

    @Test
    // testSchedulePopulateForced - tests to make sure that the forced array is
    // populated correctly.
    public void testSchedulePopulateForced() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatment = { { 1, 1, 8 }, { 1, 2, 9 }, { 2, 1, 10 }, { 2, 2, 11 } };

        Schedule schedule = new Schedule(tasks, animals, treatment);
        schedule.populateForcedAndRemaining();
        int[][] expectedForced = new int[][] { null, null, { 1, 2, 9 }, { 2, 2, 11 } };
        assertArrayEquals(expectedForced, schedule.getForced());
    }

    @Test
    // testSchedulePopulateRemaining - Tests to ensure that any tasks that have a
    // much more flexible time to be scheduled are put into the remaining array.
    public void testSchedulePopulateRemaining() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatment = { { 1, 1, 8 }, { 1, 2, 9 }, { 2, 1, 10 }, { 2, 2, 11 } };

        Schedule schedule = new Schedule(tasks, animals, treatment);
        schedule.populateForcedAndRemaining();
        int[][] expectedRemaining = new int[][] { { 1, 1, 8 }, { 2, 1, 10 }, null, null };
        assertArrayEquals("The array does not contain the tasks that it is expected to contain", expectedRemaining,
                schedule.getRemaining());
    }

    @Test
    // testScheduleOnlyNull - Tests to ensure that in a 2D int array a null value
    // can be properly placed
    public void testScheduleOnlyNull() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatment = { { 1, 1, 8 }, { 1, 2, 9 }, { 2, 1, 10 }, { 2, 2, 11 } };
        int[][] testArray = { { 1, 2, 3 }, { 4, 5, 0 }, { 0, 6, 7 }, { 8, 0, 0 } };

        Schedule schedule = new Schedule(tasks, animals, treatment);
        schedule.onlyNull(testArray);
        int[][] expectedArray = { { 1, 2, 3 }, { 4, 5, 0 }, null, { 8, 0, 0 } };
        assertArrayEquals("The 2D int array that should have a null value in it is not produced as expected ",
                expectedArray, testArray);
    }

    @Test
    // testScheduleNotOnlyNull - Tests to ensure that in a 2D int array if a null
    // value is not determined to be needed it is not unneccessarily placed
    public void testScheduleNotOnlyNull() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatment = { { 1, 1, 8 }, { 1, 2, 9 }, { 2, 1, 10 }, { 2, 2, 11 } };
        int[][] testArray = { { 1, 2, 3 }, { 4, 5, 0 }, { 6, 0, 7 }, { 8, 0, 0 } };

        Schedule schedule = new Schedule(tasks, animals, treatment);
        schedule.onlyNull(testArray);
        int[][] expectedArray = { { 1, 2, 3 }, { 4, 5, 0 }, { 6, 0, 7 }, { 8, 0, 0 } };
        assertArrayEquals("The 2D int array that should not have a null value in it is not produced as expected ",
                expectedArray, testArray);
    }

    @Test
    // testScheduleSortAndNull - Tests to ensure that a 2D int array can be sorted
    // properly when it should have null values.
    public void testScheduleSortAndNull() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatment = { { 1, 1, 8 }, { 1, 2, 9 }, { 2, 1, 10 }, { 2, 2, 11 } };
        int[][] testArray = { { 1, 2, 3 }, { 4, 5, 0 }, { 0, 6, 7 }, { 8, 0, 0 } };

        Schedule schedule = new Schedule(tasks, animals, treatment);
        schedule.sortAndNull(testArray);
        int[][] expectedArray = { { 4, 5, 0 }, { 8, 0, 0 }, { 1, 2, 3 }, null };
        assertArrayEquals("The sorted 2D int array with null values is not as expected ", expectedArray, testArray);
    }

    @Test
    // testScheduleNotSortAndNull - Tests to ensure that a 2D int array can be
    // sorted properly when it shouldn't have null values.
    public void testScheduleNotSortAndNull() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatment = { { 1, 1, 8 }, { 1, 2, 9 }, { 2, 1, 10 }, { 2, 2, 11 } };
        int[][] testArray = { { 1, 2, 3 }, { 4, 5, 0 }, { 6, 0, 7 }, { 8, 0, 0 } };

        Schedule schedule = new Schedule(tasks, animals, treatment);
        schedule.sortAndNull(testArray);
        int[][] expectedArray = { { 4, 5, 0 }, { 8, 0, 0 }, { 1, 2, 3 }, { 6, 0, 7 } };
        assertArrayEquals("The sorted 2D int array without null values is not as expected ", expectedArray, testArray);
    }

    @Test
    // testSchedulePopulateFeedWithoutOrphan - Tests to ensure that the 2D integer
    // array that keeps track of feeding is
    // populated correctly when orphans are not taken into consideration.
    public void testSchedulePopulateFeedWithoutOrphan() {
        Tasks[] tasks = { new Tasks(1, "Kit feeding", 30, 2),
                new Tasks(2, "Rebandage leg wound", 20, 1),
                new Tasks(3, "Apply burn ointment back", 10, 3),
                new Tasks(4, "Administer antibiotics", 5, 1),
                new Tasks(5, "Flush neck wound", 25, 1),
                new Tasks(6, "Give fluid injection", 10, 1),
                new Tasks(7, "Give vitamin injection", 5, 5),
                new Tasks(8, "Mange treatment", 15, 4),
                new Tasks(9, "Eyedrops", 25, 1),
                new Tasks(10, "Inspect broken leg", 5, 2) };
        Animal[] animals = { new Animal(1, "Loner", "coyote"), new Animal(4, "Pencil", "coyote"),
                new Animal(5, "Eraser", "coyote"), new Animal(8, "Spike", "porcupine"),
                new Animal(9, "Javelin", "porcupine") };
        int[][] treatment = { { 6, 1, 0 },
                { 6, 1, 2 },
                { 6, 1, 4 },
                { 6, 1, 6 },
                { 6, 1, 8 },
                { 6, 1, 10 },
                { 6, 1, 12 },
                { 6, 1, 14 },
                { 6, 1, 16 },
                { 6, 1, 18 },
                { 6, 1, 20 },
                { 6, 1, 22 },
                { 1, 9, 22 },
                { 4, 7, 13 },
                { 5, 7, 13 },
                { 8, 5, 6 },
                { 8, 4, 6 },
                { 8, 4, 18 },
                { 9, 9, 22 } };

        Schedule schedule = new Schedule(tasks, animals, treatment);
        schedule.populateFeed();
        int[][] expectedFeed = { { 1, 19 }, { 4, 19 }, { 5, 19 }, { 8, 19 }, { 9, 19 } };
        assertArrayEquals("The 2D feed array was not populated as expect when not considering an orphan ", expectedFeed,
                schedule.getFeed());
    }

    @Test
    // testSchedulePopulateFeedWithOrphan - Tests to ensure that the 2D integer
    // array that keeps track of feeding is
    // populated correctly when orphans are considered in the circumstance.
    public void testSchedulePopulateFeedWithOrphan() {
        Tasks[] tasks = { new Tasks(1, "Kit feeding", 30, 2),
                new Tasks(2, "Rebandage leg wound", 20, 1),
                new Tasks(3, "Apply burn ointment back", 10, 3),
                new Tasks(4, "Administer antibiotics", 5, 1),
                new Tasks(5, "Flush neck wound", 25, 1),
                new Tasks(6, "Give fluid injection", 10, 1),
                new Tasks(7, "Give vitamin injection", 5, 5),
                new Tasks(8, "Mange treatment", 15, 4),
                new Tasks(9, "Eyedrops", 25, 1),
                new Tasks(10, "Inspect broken leg", 5, 2) };
        Animal[] animals = { new Animal(1, "Loner", "coyote"), new Animal(4, "Pencil", "coyote"),
                new Animal(5, "Eraser", "coyote"), new Animal(6, "Annie, Oliver and Mowgli", "fox"),
                new Animal(8, "Spike", "porcupine"),
                new Animal(9, "Javelin", "porcupine") };
        int[][] treatment = { { 6, 1, 0 },
                { 6, 1, 2 },
                { 6, 1, 4 },
                { 6, 1, 6 },
                { 6, 1, 8 },
                { 6, 1, 10 },
                { 6, 1, 12 },
                { 6, 1, 14 },
                { 6, 1, 16 },
                { 6, 1, 18 },
                { 6, 1, 20 },
                { 6, 1, 22 },
                { 1, 9, 22 },
                { 4, 7, 13 },
                { 5, 7, 13 },
                { 8, 5, 6 },
                { 8, 4, 6 },
                { 8, 4, 18 },
                { 9, 9, 22 } };

        Schedule schedule = new Schedule(tasks, animals, treatment);
        schedule.populateFeed();
        int[][] expectedFeed = { { 1, 19 }, { 4, 19 }, { 5, 19 }, { 6, 0 }, { 8, 19 }, null };
        assertArrayEquals("The 2D feed array was not populated as expect when considering an orphan ", expectedFeed,
                schedule.getFeed());
    }

    @Test
    // testPrintScheduleConstructor - Tests to ensure that the PrintSchedule
    // constructor works as
    // expected and creats a valid PrintSchedule object.
    public void testPrintScheduleConstructor() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] forced = { null };
        int[][] remaining = { null };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { null };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { null };
        int[] volunteers = { 8 };

        PrintSchedule printschedule = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples,
                cleanCages);
        assertNotNull("PrintSchedule constructor did not create an object when given valid information .",
                printschedule);

    }

    @Test
    // testSetSchedule - Tests to ensure that once a schedule is created using the
    // PrintSchedule constructor, it can be set again through a setter
    public void testSetSchedule() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] forced = { null };
        int[][] remaining = { null };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { null };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { null };
        int[] volunteers = { 8 };
        int[][] schedule1 = { { 1, 2, 3 }, { 5, 2, 1 }, { 8, 4, 7 } };

        PrintSchedule printschedule = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples,
                cleanCages);
        printschedule.setSchedule(schedule1);
        assertEquals("The new schedule set does not meet the expected schedule that should be set ", schedule1,
                printschedule.getSchedule());
    }

    @Test
    // testGetSchedule - Tests to ensure that once a schedule is created using the
    // PrintSchedule constructor, it can be retrieved through a getter
    public void testGetSchedule() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] forced = { null };
        int[][] remaining = { null };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { null };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { null };
        int[] volunteers = { 8 };

        PrintSchedule printschedule = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples,
                cleanCages);
        assertEquals("The schedule retrieved does not match the expected schedule", schedule,
                printschedule.getSchedule());
    }

    @Test
    // testPrintScheduleAnimalIDException - Tests to ensure that none of the animals
    // have the same id. If they do,
    // an IllegalStateException is thrown.
    public void testPrintScheduleAnimalIDException() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(1, "Animal2", "porcupine") };
        int[][] forced = { null };
        int[][] remaining = { null };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { null };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { null };
        int[] volunteers = { 8 };

        boolean passed = false;
        try {
            PrintSchedule ps = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples, cleanCages);
        } catch (IllegalStateException e) {
            passed = true;
        } catch (Exception e) {
        }
        assertTrue("PrintSchedule found duplicate animal ids and threw an IllegalStateException",
                passed);
    }

    @Test
    // testPrintScheduleTaskIDException - Tests to ensure that none of the tasks
    // have the same id. If they do,
    // an IllegalStateException is thrown.
    public void testPrintScheduleTaskIDException() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(1, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] forced = { null };
        int[][] remaining = { null };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { null };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { null };
        int[] volunteers = { 8 };

        boolean passed = false;
        try {
            PrintSchedule ps = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples, cleanCages);
        } catch (IllegalStateException e) {
            passed = true;
        } catch (Exception e) {
        }
        assertTrue("PrintSchedule found duplicate task ids and threw an IllegalStateException", passed);
    }

    @Test
    // testPrintScheduleWithoutVolunteer - Tests to ensure that if a volunteer is
    // determined to not be needed, the schedule is printed without
    // any indications of a volunteers being needed at specific times.
    public void testPrintScheduleWithoutVolunteer() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] forced = { null };
        int[][] remaining = { null };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { null };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { null };
        int[] volunteers = {};

        PrintSchedule ps = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples, cleanCages);

        String expectedOutput = "Schedule for " + LocalDate.now().toString() + "\n" +
                "\n8:00\n" +
                "* Feeding - coyote(1: Animal1)\n" +
                "\n9:00\n" +
                "* Task2 - Animal1\n" +
                "\n10:00\n" +
                "* Feeding - porcupine(1: Animal2)\n";

        assertEquals("The printed schedule is not the expected schedule ", expectedOutput,
                ps.printSchedule(volunteers));
    }

    @Test
    // testPrintScheduleWithVolunteer - Tests to ensure that if a volunteer is
    // determined to be needed, it's printed to the user in the schedule that the
    // volunteer
    // is needed and it is displayed next to the hour at which the volunteer is
    // determined to be needed.
    public void testPrintScheduleWithVolunteer() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] forced = { null };
        int[][] remaining = { null };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { null };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { null };
        int[] volunteers = { 8 };

        PrintSchedule ps = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples, cleanCages);

        String expectedOutput = "Schedule for " + LocalDate.now().toString() + "\n" +
                "\n8:00 [+ backup volunteer]\n" +
                "* Feeding - coyote(1: Animal1)\n" +
                "\n9:00\n" +
                "* Task2 - Animal1\n" +
                "\n10:00\n" +
                "* Feeding - porcupine(1: Animal2)\n";

        assertEquals("The printed schedule is not the expected schedule ", expectedOutput,
                ps.printSchedule(volunteers));
    }

    @Test
    // testPrintScheduleImpossibleForced - Tests to ensure that when there are
    // conflicts in the schedule due to placements of
    // treatments that don't have a very flexible window of time to be scheduled,
    // the user is informed of the task causing conflicts and the hour
    // at which the conflict is occurring.
    public void testPrintScheduleImpossibleForced() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] forced = { { 1, 1, 8 } };
        int[][] remaining = { null };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { null };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { null };
        int[] volunteers = { 8 };

        PrintSchedule ps = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples, cleanCages);

        String expectedOutput = "Not Possible due to... Task: Task1 Can not be completed in the following hours: 8:00 9:00 ";

        assertEquals("The printed error message is not the expected message to the user ", expectedOutput,
                ps.printSchedule(volunteers));
    }

    @Test
    // testPrintScheduleImpossibleFeed - Tests to ensure that when there are
    // conflicts in the schedule due to placements of feeding tasks that
    // the user is informed of the conflict and told they need to reschedule some
    // treatments.
    public void testPrintScheduleImpossibleFeed() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] forced = { null };
        int[][] remaining = { null };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { { 1, 1 } };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { null };
        int[] volunteers = { 8 };

        PrintSchedule ps = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples, cleanCages);

        String expectedOutput = "Not Possible due to... Task: Feeding For Animal1 Please modify a task to try to reschedule.";

        assertEquals("The printed error message is not the expected message to the user ", expectedOutput,
                ps.printSchedule(volunteers));
    }

    @Test
    // testPrintScheduleImpossibleRemaining - Tests to ensure that when there are
    // conflicts in the schedule due to placements of
    // treatments that have a flexible window of time to be scheduled, the user is
    // informed of the task causing conflicts and the hour
    // at which the conflict is occurring.
    public void testPrintScheduleImpossibleRemaining() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] forced = { null };
        int[][] remaining = { { 2, 2, 9 } };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { null };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { null };
        int[] volunteers = { 8 };

        PrintSchedule ps = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples, cleanCages);

        String expectedOutput = "Not Possible due to... Task: Task2 Can not be completed in the following hours: 9:00 ";

        assertEquals("The printed error message is not the expected message to the user ", expectedOutput,
                ps.printSchedule(volunteers));
    }

    @Test
    // testPrintScheduleImpossibleCageClean - Tests to ensure that when there are
    // conflicts in the schedule due to placements of cage cleaning
    // the user is informed of the conflict and told they need to reschedule some
    // treatments.
    public void testPrintScheduleImpossibleCageClean() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] forced = { null };
        int[][] remaining = { null };
        int[][] schedule = { { 1, 0, 8 }, { 1, 2, 9 }, { 2, 0, 10 } };
        int[][] feed = { null };
        int[][] tuples = { { 1, 1, 8 } };
        int[][] cleanCages = { { 2, 1 } };
        int[] volunteers = { 8 };

        PrintSchedule ps = new PrintSchedule(tasks, animals, forced, remaining, schedule, feed, tuples, cleanCages);

        String expectedOutput = "Not Possible due to... Task: CageCleaning For Animal2 Please modify a task to try to reschedule.";

        assertEquals("The printed error message is not the expected message to the user ", expectedOutput,
                ps.printSchedule(volunteers));
    }

    @Test
	// testAddToSchedule - Test to ensure the addToSchedule function in the Schedule class is working as needed
    public void testAddToSchedule() {

        Tasks[] tasks = { new Tasks(1, "Kit feeding", 30, 1), new Tasks(2, "Apple a day", 30, 3) };

        Animal[] animals = { new Animal(1, "Loner", "coyote"), new Animal(4, "Pencil", "coyote"),
                new Animal(5, "Eraser", "coyote"), new Animal(6, "Annie, Oliver and Mowgli", "fox"),
                new Animal(8, "Spike", "porcupine"),
                new Animal(9, "Javelin", "porcupine") };
        int[][] treatment = { { 6, 1, 0 },
                { 6, 1, 2 },
                { 6, 1, 4 } };

        Schedule s = new Schedule(tasks, animals, treatment);
        s.populateForcedAndRemaining();
        int[][] forced = s.getForced();
        s.addToSchedule(forced, 60);
        int[][] forcedAc = new int[treatment.length][3];
        for (int i = 0; i < forcedAc.length; i++) {
            forcedAc[i] = null;
        }
        assertEquals("Schedule addtoSchedule function did not work as intended", forced, forcedAc);
    }

    @Test
	// testVolunteerConstructor - Test to ensure the constructor in the Volunteer class is working when given valid arguments
    public void testVolunteerConstructor() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatments = { { 6, 1, 0 }, { 6, 1, 2 } };
        Schedule schedule = new Schedule(tasks, animals, treatments);
        Volunteer vol = new Volunteer(schedule);
        assertNotNull("Volunteer constructor did not create an object when given valid information .", vol);
    }

    // testVolunteerSetScheduleObj - Tests getScheduleObj in Volunteer class to see if schedule is passed through and set in Volunteer
    @Test
    public void testVolunteerSetScheduleObj() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatments = { { 6, 1, 0 }, { 6, 1, 2 } };
        Schedule schedule = new Schedule(tasks, animals, treatments);
        Volunteer vol = new Volunteer(schedule);
        vol.setScheduleObj(schedule);
        assertEquals("Volunteer setScheduleObj getter function did not get expected schedule object.", schedule,
                vol.getScheduleObj());
    }

    @Test
    // testVolunteerGetScheduleObj - Tests getScheduleObj in Volunteer class to see if schedule can be retrieved from volunteer
    public void testVolunteerGetScheduleObj() {
        Tasks[] tasks = { new Tasks(1, "Task1", 30, 2), new Tasks(2, "Task2", 10, 1) };
        Animal[] animals = { new Animal(1, "Animal1", "coyote"), new Animal(2, "Animal2", "porcupine") };
        int[][] treatments = { { 6, 1, 0 }, { 6, 1, 2 } };
        Schedule schedule = new Schedule(tasks, animals, treatments);
        Volunteer vol = new Volunteer(schedule);
        vol.setScheduleObj(schedule);
        Schedule schedule2 = vol.getScheduleObj();
        assertEquals("Volunteer getScheduleObj getter function did not get expected schedule object.", schedule,
                schedule2);
    }
}