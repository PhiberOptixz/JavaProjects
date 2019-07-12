package spoj;

import java.util.ArrayList;
import java.util.List;

public class FindMaxBasedOnIndex
{
    int getMaxValue(List<Integer> list) {
        List<Integer> storeLength = new ArrayList<>();
        for(int i = 0; i < list.size(); ++i) {
            storeLength.add(Integer.toString(list.get(i)).length());
        }
        List<Integer> prevList = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        for(int i = 0; i < list.size(); ++i) {
            currentList.add(i);
        }

        int index = 0;
        do {
            int max = 0;
            prevList.clear();
            for(int j = 0; j < currentList.size(); ++j) {
                prevList.add(currentList.get(j));
            }
            currentList.clear();
            for(int i = 0; i < prevList.size(); ++i){
                int temp1 = list.get(prevList.get(i)) % (int)Math.pow(10, storeLength.get(i) - index);
                if(temp1 == 0) continue;
                int numberAtIndex = temp1 / (int)Math.pow(10, storeLength.get(i) - index - 1);
                if( numberAtIndex == max ) {
                    currentList.add(i);
                } else if( numberAtIndex > max){
                    currentList.clear();
                    currentList.add(i);
                    max = numberAtIndex;
                }
            }
            ++index;
        }while(currentList.size() > 1);
        return list.get(currentList.get(0));
    }

    int getMaxValueFromList(List<String> list) {
        List<List<Integer>> matrix = new ArrayList<>();
        for(int i = 0; i < list.size(); ++i) {
            List<Integer> tempMat = new ArrayList<>();
            String[] arr = list.get(i).split(",");
            for(int j = 0; j < arr.length; ++j) {
                tempMat.add(Integer.parseInt(arr[j]));
            }
            matrix.add(tempMat);
        }
        List<Integer> prevList = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        for(int i = 0; i < matrix.size(); ++i) {
            currentList.add(i);
        }

        int index = 0;
        int max = 0;
        do {
            max = 0;
            prevList.clear();
            for(int j = 0; j < currentList.size(); ++j) {
                prevList.add(currentList.get(j));
            }
            currentList.clear();
            for(int i = 0; i < prevList.size(); ++i) {
                if(matrix.get(i).size() >= index) continue;
                if(matrix.get(i).get(index) == max) {
                    currentList.add(i);
                } else if(matrix.get(i).get(index) > max) {
                    currentList.clear();
                    currentList.add(i);
                    max = matrix.get(i).get(index);
                }
            }
            ++index;
        }while(max > 0 && currentList.size() > 1);

        StringBuilder stringBuilder = new StringBuilder();
        if(currentList.size() != 0) {
            for(int i = 0; i < matrix.get(currentList.get(0)).size(); ++i) {
                stringBuilder.append(Integer.toString(matrix.get(currentList.get(0)).get(i)));
            }
        } else {
            for(int i = 0; i < matrix.get(prevList.get(0)).size(); ++i) {
                stringBuilder.append(Integer.toString(matrix.get(prevList.get(0)).get(i)));
            }
        }

        return Integer.parseInt(stringBuilder.toString());
    }

    public static void main(String[] args) {
      FindMaxBasedOnIndex findMaxBasedOnIndex = new FindMaxBasedOnIndex();
      List<String> list = new ArrayList<>();
      list.add("50,2");
      list.add("50");
      System.out.println(findMaxBasedOnIndex.getMaxValueFromList(list));
    }
}
