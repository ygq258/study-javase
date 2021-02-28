package ygq.study.javase.lambda.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 6 练习
 *
 * @author ygq
 */
public class StreamSample6 {

    /**
     * 6.3 使用蒙特卡洛模拟法
     */
    public static void sample1() {
        int n = 200000000;
        double fraction = 1.0 / n;
        long l = System.currentTimeMillis();
        Map<Integer, Double> collect = IntStream.range(0, n).parallel().mapToObj(x -> {
            int value1 = (int) (new Random().nextDouble() * 6) + 1;
            int value2 = (int) (new Random().nextDouble() * 6) + 1;
            return value1 + value2;
        }).collect(Collectors.groupingBy(side -> side, Collectors.summingDouble(s -> fraction)));
        System.out.println(collect);
        System.out.println("执行时间：" + (System.currentTimeMillis() - l) / 1000.0f);
    }

    /**
     * 6.8 使用并行化数组操作初始化数组
     */
    public static void sample2() {
        int[] values = new int[Byte.MAX_VALUE];
        Arrays.parallelSetAll(values, operand -> operand);
        System.out.println(Arrays.toString(values));
    }

    /**
     * 6.9 使用并行化数组操作初始化数组
     */
    public static void sample3() {
        double[] values = {0, 1, 2, 3, 4, 3.5};
        int n = 3;
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n - 1;
        double[] doubles = IntStream.range(start, sums.length).mapToDouble(i -> {
            double prefix = i == start ? 0 : sums[i - n];
            return (sums[i] - prefix) / n;
        }).toArray();
        System.out.println(Arrays.toString(doubles));

    }

    //--------------------------practise-----------------------------

    /**
     * 练习 6.10 数字平方和
     */
    public static void practise1() {
        IntStream range = IntStream.of(0, 1, 2, 3, 4, 3);
        int sum = range.map(x -> x * x).sum();
        System.out.println("数字平方和：" + sum);
        //--并行
        IntStream range2 = IntStream.of(0, 1, 2, 3, 4, 3);
        int sum1 = range2.parallel().map(x -> x * x).sum();
        System.out.println("并行-数字平方和：" + sum);
    }

    /**
     * 练习 6.11
     */
    public static void practise2() {
        List<Integer> linkedListOfNumbers = List.of(1, 2, 3, 4, 3);
        linkedListOfNumbers.parallelStream().map(x -> x * x).reduce(0, (acc, x) -> acc * x);
    }

    /**
     * 练习 6.12
     */
    public static void practise3() {
        List<Integer> linkedListOfNumbers = List.of(1, 2, 3, 4, 3);
        Integer value = linkedListOfNumbers.stream().reduce(5, (acc, x) -> x * acc);
        System.out.println("顺序执行结果：" + value);
        //-并行
        Integer value1 = linkedListOfNumbers.parallelStream().reduce(1, (acc, x) -> x * acc) * 5;
        System.out.println("并行-执行结果：" + value1);
        int value2 = linkedListOfNumbers.parallelStream().reduce((acc, x) -> x * acc).get() * 5;
        System.out.println("并行-执行结果2：" + value2);
    }

    /**
     * 练习 6.12
     */
    public static void practise4() {
        List<Integer> linkedListOfNumbers = List.of(1, 2, 3, 4, 3);
        Integer reduce = linkedListOfNumbers.parallelStream().map(x -> x * x).reduce(1, (acc, x) -> x * acc);
        System.out.println("并行执行：" + reduce);
        int reduce1 = linkedListOfNumbers.stream().mapToInt(x -> x.intValue()).parallel().map(x -> x * x).reduce(1, (acc, x) -> x * acc);
        System.out.println("性能优化后：" + reduce1);
    }

    public static void main(String[] args) {
        practise4();
    }

}
