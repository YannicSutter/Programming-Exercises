import java.util.ArrayList;
import java.util.List;

public class MergeSortUtil {

    public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
        // Checking list size
        if (list.size() == 0) {
            System.out.println("List is of size 0");
            return null;
        }

        // Splitting the list into pairs of 2
        List<List<T>> split = new ArrayList<>();

        for (int i = 0; i < list.size(); i += 2) {
            List<T> pair = new ArrayList<>();
            pair.add(list.get(i));
            if (i + 1 < list.size()) {
                pair.add(list.get(i + 1));
            }
            split.add(pair);
        }

        // Sort the pairs of 2
        for (int i = 0; i < split.size(); i++) {
            if (split.get(i).size() == 1) {
                continue;
            }
            if (split.get(i).get(0).compareTo(split.get(i).get(1)) > 0) {
                T temp = split.get(i).get(0);
                split.get(i).set(0, split.get(i).get(1));
                split.get(i).set(1, temp);
            }
        }

        // Merge the splits
        List<List<T>> temp = new ArrayList<>();
        while (split.size() > 1) {
            // Adding empty lists
            for (int i = 0; i < (split.size() + 1) / 2; i++) {
                temp.add(new ArrayList<>());
            }

            for (int i = 0; i < split.size(); i += 2) {
                int pointer1 = 0;
                int pointer2 = 0;

                if (i + 1 >= split.size()) {
                    temp.get(i / 2).addAll(split.get(i));
                    continue;
                }

                while (pointer1 < split.get(i).size() && pointer2 < split.get(i + 1).size()) {
                    if (split.get(i).get(pointer1).compareTo(split.get(i + 1).get(pointer2)) < 0) {
                        temp.get(i / 2).add(split.get(i).get(pointer1));
                        pointer1++;
                    }
                    else {
                        temp.get(i / 2).add(split.get(i + 1).get(pointer2));
                        pointer2++;
                    }
                }

                // Leftovers
                while (pointer1 < split.get(i).size()) {
                    temp.get(i / 2).add(split.get(i).get(pointer1));
                    pointer1++;
                }
                while (pointer2 < split.get(i + 1).size()) {
                    temp.get(i / 2).add(split.get(i + 1).get(pointer2));
                    pointer2++;
                }

                split = temp;
                temp = new ArrayList<>();
            }
        }
        
        return split.get(0);
    }
}
