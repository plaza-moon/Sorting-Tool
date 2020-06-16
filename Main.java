package sorting;
import java.io.*;
import java.util.*;

public class Main {
    static String importFile = "./";
    static String exportFile = "./";

    public static void updateValue(Map<String, Integer> map, String key, Integer value) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + value);
        } else {
            map.put(key, value);
        }
    }

    public static HashMap<String, Integer> sortByValue(LinkedHashMap<String, Integer> hm) {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {

        int cnt0 = 0, cnt = 0 , cnt2 = 0, cnt3 = 0,
                cnt40 = 0,cnt4 = 0,  cnt5 = 0, cnt6 = 0, cnt7 = 0, cnt8 = 0, cnt9 = 0;
        if (args.length > 0) {
            for (int m = 0; m < args.length; m++) {
                if (args[m].equals("-dataType")) {
                    cnt0++;
                    if (args[m + 1].equals("long")) {
                        cnt++;
                    } else if (args[m + 1].equals("line")) {
                        cnt2++;
                    } else if (args[m + 1].equals("word")) {
                        cnt3++;
                    }
                }
                if (args[m].equals("-sortingType")) {
                    cnt40++;
                    if (args.length > 3) {
                        if (args[m + 1].equals("byCount")) {
                            cnt4++;
                        } else if (args[m + 1].equals("natural")) {
                            cnt5++;
                        }
                    }
                }
                if (args[m].equals("-abc")) {
                    cnt6++;
                } else if (args[m].equals("-cde")) {
                    cnt7++;
                }
                if (args[m].equals("-inputFile")) {
                    importFile += args[m+1];
                    cnt8++;
                }
                if (args[m].equals("-outputFile")) {
                    exportFile += args[m+1];
                    cnt9++;
                }
            }
        }
        if (cnt0 == 1 && cnt == 0 && cnt2 == 0 && cnt3 == 0) {
            System.out.println("No data type defined!");
            System.exit(0);
        } else if (cnt40 == 1 && cnt4 == 0 && cnt5 == 0) {
            System.out.println("No sorting type defined!");
            System.exit(0);
        } else if (cnt6 == 1) {
            System.out.println("\"" +"-abc" + "\"" + " isn't a valid parameter. It's skipped.");
        } else if (cnt7 == 1) {
            System.out.println("\"" +"-cde" + "\"" + " isn't a valid parameter. It's skipped.");
        }

        File file = new File(importFile);
        File file2 = new File(exportFile);

        List<Integer> list = new ArrayList<>();
        LinkedHashMap<String, Integer> map2 = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> map3 = new LinkedHashMap<>();

        Scanner scanner;
        if (cnt8 == 1) {
            scanner = new Scanner(file);
        } else {
            scanner = new Scanner(System.in);
        }
        FileWriter writer = null;
        if (cnt9 == 1) {
            writer = new FileWriter(file2);
        }

        int count = 0;
        if ((cnt == 1 || cnt8 == 1) && cnt4 == 1) {
            int value = 1;
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                updateValue(map2, String.valueOf(number), value);
                count++;
            }
            scanner.close();
            LinkedList<Integer> sortedKeys = new LinkedList<>();
            for (String key : map2.keySet()) {
                sortedKeys.add(Integer.valueOf(key));
            }
            Collections.sort(sortedKeys);
            for (Integer key : sortedKeys) {
                map3.put(String.valueOf(key), map2.get(String.valueOf(key)));
            }
            LinkedHashMap<String, Integer> map4 = (LinkedHashMap<String, Integer>) sortByValue(map3);

            if (writer != null) {
                writer.write("Total numbers: " + count + ".");
                for (String key : map4.keySet()) {
                    writer.write(key + ": " + map2.get(key) + " time(s), " + (map2.get(key) * 100 / count));
                }
                writer.close();
            } else {
                System.out.println("Total numbers: " + count + ".");
                for (String key : map4.keySet()) {
                    System.out.println(key + ": " + map2.get(key) + " time(s), " + (map2.get(key) * 100 / count));
                }
            }
        } else if ((cnt == 1 || cnt8 == 1) && (cnt5 == 1 || cnt5 == 0)) {
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                list.add(number);
                count++;
            }
            scanner.close();

            Collections.sort(list);

            if (writer != null) {
                writer.write("Total numbers: " + count + ".");
                writer.write("Sorted data: ");
                for (int i = 0; i < count; i++) {
                    writer.write(" " + list.get(i));
                }
                writer.close();
            } else {
                System.out.println("Total numbers: " + count + ".");
                System.out.print("Sorted data: ");
                for (int i = 0; i < count; i++) {
                    System.out.print(" " + list.get(i));
                }
            }
        } else if (cnt3 == 1 && cnt4 == 1) {
            int value = 1;
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                updateValue(map2, String.valueOf(number), value);
                count++;
            }
            scanner.close();

            LinkedList<String> sortedKeys = new LinkedList<>(map2.keySet());

            Collections.sort(sortedKeys);
            for (String key : sortedKeys) {
                map3.put(String.valueOf(key), map2.get(key));
            }
            LinkedHashMap<String, Integer> map4 = (LinkedHashMap<String, Integer>) sortByValue(map3);

            if (writer != null) {
                writer.write("Total words: " + count + ".");
                for (String key : map4.keySet()) {
                    writer.write(key + ": " + map2.get(key) + " time(s), " + (map2.get(key) * 100 / count));
                }
                writer.close();
            } else {
                System.out.println("Total words: " + count + ".");
                for (String key : map4.keySet()) {
                    System.out.println(key + ": " + map2.get(key) + " time(s), " + (map2.get(key) * 100 / count));
                }
            }
        } else if (cnt2 == 1 && cnt4 == 1) {
            int value = 1;
            while (scanner.hasNextLine()) {
                String number = scanner.nextLine();
                updateValue(map2, number, value);
                count++;
            }
            scanner.close();

            LinkedList<String> sortedKeys = new LinkedList<>(map2.keySet());
            Collections.sort(sortedKeys);
            for (String key : sortedKeys) {
                map3.put(String.valueOf(key), map2.get(key));
            }
            LinkedHashMap<String, Integer> map4 = (LinkedHashMap<String, Integer>) sortByValue(map3);

            if (writer != null) {
                writer.write("Total lines: " + count + ".");
                for (String key : map4.keySet()) {
                    writer.write(key + ": " + map2.get(key) + " time(s), " + (map2.get(key) * 100 / count));
                }
                writer.close();
            } else {
                System.out.println("Total lines: " + count + ".");
                for (String key : map4.keySet()) {
                    System.out.println(key + ": " + map2.get(key) + " time(s), " + (map2.get(key) * 100 / count));
                }
            }
        }
    }
}
