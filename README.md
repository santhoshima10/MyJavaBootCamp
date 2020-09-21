Assignment #7 (formerly Assignment #8)

In this assignment you'll be leveraging your new found knowledge of multi-threading in Java.

What you'll need in order to get started is the Assignment 8 code from this GitHub link:

https://github.com/tp02ga/java-bootcamp/tree/master/Assignment8


This code consists of one Java class file called "Assignment8.java", and (once instantiated) it will ingest data from an "output.txt" file and insert this data into a simple ArrayList called "numbers".


Step 1
Your job is to write asynchronous code that will call the "getNumbers()" method within the "Assignment8.java" class.


For example, here's the incorrect synchronous code to fetch the data:

    @Test

    public void getData () {

        Assignment8 assignment = new Assignment8();

        
        for (int i=0; i<1000; i++) {

            List<Integer> numbersList = assignment.getNumbers();

            System.out.println(numbersList);

        }

    }


You'll need to make sure to use asynchronous code to fetch all the numbers from the "numbers" ArrayList. The code above will fetch the correct total number of records, but it will do it very slowly.


Step 2
Once you are able to asynchronously fetch the data, you'll need to determine the number of times each unique number appears. So if you find the number 2 in the whole set of data 6,000 times, then you'll need to output that onto the console.


For example, given this list of data:

1, 5, 10, 3, 1, 2, 9, 4, 6, 1, 9, 10, 5

This is the desired output:

1=3, 2=1, 3=1, 4=1, 5=2, 6=1, 7=0, 8=0, 9=2, 10=2


Important Note: Do NOT modify any of the existing code that you start with (from the GitHub repo). You are allowed to add new code to the "Assignment 8" Java file if you like, but do not change any of the existing code that came with the project.
