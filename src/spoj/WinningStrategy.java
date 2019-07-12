package spoj;

import java.util.Arrays;
import java.util.Scanner;

public class WinningStrategy
{
    private static boolean didMaxiWin(int[] heroes, int[] villains) {
        Arrays.sort(heroes);
        Arrays.sort(villains);
        for(int i = 0; i < heroes.length; ++i) {
            if(villains[i] >= heroes[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfTestCases = scanner.nextInt();
        for(int i = 0; i < numOfTestCases; ++i) {
            int numOfGames = scanner.nextInt();
            int[] heroes = new int[numOfGames];
            int[] villains = new int[numOfGames];
            for(int j = 0; j < numOfGames; ++j) {
                villains[j] = scanner.nextInt();
            }
            for(int j = 0; j < numOfGames; ++j) {
                heroes[j] = scanner.nextInt();
            }
            System.out.println(WinningStrategy.didMaxiWin(heroes, villains) ? "WIN": "LOSE");
        }
    }
}
