import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by nadav on 30-Apr-17.
 */
public class SimpleSetPerformanceAnalyzer {
    public static SimpleSet[] declareDataSet() {
        SimpleSet[] dataSet = {new OpenHashSet(), new ClosedHashSet(),
                new CollectionFacadeSet(new TreeSet<String>()),
                new CollectionFacadeSet(new LinkedList<String>()),
                new CollectionFacadeSet(new HashSet<String>())};
        return dataSet;
    }

    public static long[] search(SimpleSet[] dataSet, String searchVal) {
        long[] times = new long[dataSet.length];
        for (int i = 0; i < dataSet.length; i++) {
            if (i == 3) {
                long timeBefore = System.nanoTime();
                for (int j = 0; j < 7000; j++) {
                    dataSet[i].contains(searchVal);
                }
                long timeAfter = (System.nanoTime() - timeBefore) / 7000;
                times[i] = timeAfter;
            } else {
                for (int j = 0; j < 70000; j++) {
                    dataSet[i].contains(searchVal);
                }
                long timeBefore = System.nanoTime();
                for (int j = 0; j < 70000; j++) {
                    dataSet[i].contains(searchVal);
                }
                times[i] = (System.nanoTime() - timeBefore) / 70000;
            }
        }
        return times;
    }

    public static void sizeTest(SimpleSet[] dataSet) {
        for (int i = 0; i < dataSet.length; i++) {
            System.out.println("data structure " + i + "size is: " + dataSet[i].size());
        }
    }

    public static SimpleSet[] defaultAdd(SimpleSet[] dataSet, String[] stringArray) {
        for (int i = 0; i < dataSet.length; i++) {
            long timeBefore = System.nanoTime();
            for (int j = 0; j < stringArray.length; j++) {
                dataSet[i].add(stringArray[j]);
            }
            long timeAfter = System.nanoTime() - timeBefore;
            System.out.println("data structure " + i + " took: " + timeAfter / 1000000 + " ms");
        }
        return dataSet;
    }

    public static void printTimes(long[] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.println("data structure " + i + " took:" + result[i] + " ns");
        }
    }

    public static void main(String[] args) {

        String[] data1 = Ex3Utils.file2array("C:\\Users\\nadav\\Documents\\School\\2nd semester\\" +
                "oop\\ex3\\src\\data1.txt");
        String[] data2 = Ex3Utils.file2array("C:\\Users\\nadav\\Documents\\School\\2nd semester\\" +
                "oop\\ex3\\src\\data2.txt");
        System.out.println("Creating data1");
        SimpleSet[] dataSet1 = defaultAdd(declareDataSet(), data1);
        System.out.println("creating data2");
        SimpleSet[] dataSet2 = defaultAdd(declareDataSet(), data2);
        System.out.println("searching 'hi' in data1");
        long[] hi1 = search(dataSet1, "hi");
        printTimes(hi1);
        System.out.println("searching '-13170890158' in data 1");
        long[] number1 = search(dataSet1, "-13170890158");
        printTimes(number1);
        System.out.println("searching '23' in data2");
        long[] number2 = search(dataSet2, "23");
        printTimes(number2);
        System.out.println("searching 'hi' in data 2");
        long[] hi2 = search(dataSet2, "hi");
        printTimes(hi2);


    }
}
