package spoj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptimalTicketNumber
{
    private List<String> getMaxTicketNumberList(int[] ticketNumbers) {
        int[] mem = new int[ticketNumbers.length];
        int[] storePrevIndex = new int[ticketNumbers.length];

        int maxValAtIndex = 0;
        if(ticketNumbers.length > 1) {
            maxValAtIndex = ticketNumbers[0] > ticketNumbers[1] ? 0: 1;
        }
        for(int i = 0; i < ticketNumbers.length; ++i) {
            mem[i] = ticketNumbers[i];
            storePrevIndex[i] = i;

            for( int j = 2; j <= i; ++j ) {
                if(ticketNumbers[i] + mem[j-2] > mem[i]) {
                    mem[i] = ticketNumbers[i] + mem[j-2];
                    storePrevIndex[i] = j-2;
                }
            }
            maxValAtIndex = mem[maxValAtIndex] < mem[i] ? i: maxValAtIndex;
        }

        List<String> maxTicketLists = new ArrayList<>();
        for(int i = 0; i < ticketNumbers.length; ++i) {
            if(ticketNumbers[i] == 0) continue;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(ticketNumbers[i] + ",");
            if(mem[i] == mem[maxValAtIndex]) {
                for(int j = i; storePrevIndex[j] != j; ) {
                    j = storePrevIndex[j];
                    stringBuilder.append(ticketNumbers[j] + ",");
                }
                maxTicketLists.add(stringBuilder.toString());
            }
        }
        return maxTicketLists;
    }

    private String getMaxValueFromList(List<String> list) {
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
        do {
            int max = 0;
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
        }while(currentList.size() > 1);

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
        return stringBuilder.toString();
    }

    public static void main(String args[] ) throws Exception {
        OptimalTicketNumber optimalTicketNumber = new OptimalTicketNumber();
        Scanner scanner = new Scanner(System.in);
        int numOfTestCases = scanner.nextInt();
        for(int i = 0; i < numOfTestCases; ++i) {
            int numOfTickets = scanner.nextInt();
            int[] tickets = new int[numOfTickets];
            for(int j = 0; j < tickets.length; ++j) {
                tickets[j] = scanner.nextInt();
            }
            System.out.println(optimalTicketNumber.getMaxValueFromList(
                    optimalTicketNumber.getMaxTicketNumberList(tickets)));
        }
    }
}
