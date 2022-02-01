package microservices.book.library.model;

import microservices.book.library.LibraryMicroservice;
import org.springframework.boot.SpringApplication;
import org.springframework.util.StreamUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class hih {

    public static void main(String[] args) {

        //way 1
//        int size = 100;
//        int lowBound = 1;
//        int highBound = 11;
//
//        int[] ints1 = new Random().ints(size,lowBound, highBound).toArray();

        //way 2

        int[] ints2 = IntStream.range(0, 100).map(x -> (int) (Math.random() * 10) + 1).toArray();
        List<Integer> ints2List = IntStream.range(0, 100).map(x -> (int) (Math.random() * 10) + 1).boxed().collect(Collectors.toList());
        List<Integer> numbers = ints2List;
        //best way frequency to map
        Map<Integer, Long> collect = ints2List.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        //Frequency to array 0 - 9
        int[] ints = IntStream.range(1, 11).map(x -> Collections.frequency(ints2List, x)).toArray();
        //Frequency to list 0 - 9
        List<Integer> collect1 = IntStream.range(1, 11).map(x -> Collections.frequency(ints2List, x)).boxed().collect(Collectors.toList());
        //Frequency to map 1 - 10
        Map<Integer, Integer> collect2 = IntStream.range(1, 11).boxed().collect(Collectors.toMap(e -> e, e -> Collections.frequency(ints2List, e)));

        //nothing really
        Integer reduce = collect2.values().stream().reduce(0, (a, b) -> a + b);

        //way 3

//        List<Integer> numbers = new ArrayList<>();
//
//        for(int i = 0; i < 100 ; i++){
//            numbers.add((int)(Math.random() * 10 + 1));
//
//        }
//
//        int[] frequency = new int[11];
//
//        for (Integer number : numbers) {
//
//            frequency[number.intValue()]++;
//        }


        int[] ints1 = IntStream.range(1, 11).map(e -> Collections.frequency(numbers, e)).toArray();
        Map<Integer, Integer> collect3 = IntStream.range(1, 11).boxed().collect(Collectors.toMap(e -> e, e -> Collections.frequency(numbers, e)));
        Map<Integer, Long> collect4 = numbers.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));


        Map<Integer, Integer> collect6 = IntStream.range(1, 11).boxed().collect(Collectors.toMap(x -> x, x -> Collections.frequency(numbers, x)));
        Map<Integer, Long> collect5 = numbers.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));


        System.out.println();
    }
    
  
}
