package Assignment8;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Assignment8 {
    private List<Integer> numbers = null;
    private Integer i=0;
    private Map<Integer,Integer> map = new HashMap<>();

    @Test
    public void getData(){

       // Assignment8 assignment8 = new Assignment8();

        List<CompletableFuture<Void>> taskList = new ArrayList<>();

        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService cpuBound = Executors.newFixedThreadPool(3);

        for (int i=0; i<1000; i++) {

            CompletableFuture<Void> task  = CompletableFuture.supplyAsync(() -> getNumbers(),pool)
                                                                      .thenAcceptAsync(listData -> processData(listData), cpuBound);
            taskList.add(task);


        }

        while(taskList.stream().filter(CompletableFuture::isDone).count() < 1000){

        }

        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() +" : "+ entry.getValue());
        }

    }

    private void processData(List<Integer> list){


          synchronized (map) {
              for (int i = 0; i < list.size(); i++) {
                  Integer localKey = list.get(i);
                  if (map.containsKey(localKey)) {
                      Integer value = map.get(localKey);
                      map.put(localKey, value + 1);
                  } else {
                      map.put(localKey, 1);
                  }
              }
          }

    }


    public Assignment8 () {
        try {
            // Make sure you download the output.txt file for Assignment 8
            //  and place the file in the root of your Java project
            numbers = Files.readAllLines(Paths.get("output.txt"))
                    .stream()
                    .map(n -> Integer.parseInt(n))
                    .collect(Collectors.toList());


            Map<Integer,Long> newMap  = Files.readAllLines(Paths.get("output.txt"))
                 .stream()
                 .map(n -> Integer.parseInt(n))
                 .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));


            for(Map.Entry<Integer,Long> newEntry : newMap.entrySet()){
                System.out.println("Process Count "+newEntry.getKey() +" : "+ newEntry.getValue());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will return the numbers that you'll need to process from the
     *   list of Integers. However, it can only return 1000 records at a time.
     *   You will need to call this method 1,000 times in order to retrieve all
     *   1,000,000 numbers from the list
     * @return Integers from the parsed txt file, 1,000 numbers at a time
     */
    public List<Integer> getNumbers () {
        int start, end;
        synchronized (i) {
            start = i;
            end = i+1000;
            i += 1000;
            System.out.println("Starting to fetch records " + start + " to " + (end));
        }
        // force thread to pause for half a second to simulate actual Http / API traffic delay
        try { Thread.sleep(500); } catch (InterruptedException e) { }

        List<Integer> newList = new ArrayList<>();
        IntStream.range(start, end)
                .forEach(n -> {
                    newList.add(numbers.get(n));
                });
        System.out.println("Done Fetching records " + start + " to " + (end));
        return newList;
    }


}