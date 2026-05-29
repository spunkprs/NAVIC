package microsoft.dp;


/**
Problem : 3147
Link : https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all

In a mystic dungeon, n magicians are standing in a line. Each magician has an attribute that gives you energy. Some magicians can
give you negative energy, which means taking energy from you.

You have been cursed in such a way that after absorbing energy from magician i, you will be instantly transported to magician (i + k).
This process will be repeated until you reach the magician where (i + k) does not exist.

In other words, you will choose a starting point and then teleport with k jumps until you reach the end of the magicians' sequence,
absorbing all the energy during the journey.

You are given an array energy and an integer k. Return the maximum possible energy you can gain.

Note that when you reach a magician, you must take energy from them, whether it is negative or positive energy.

Constraints:

a.) 1 <= energy.length <= 10^5
b.) -1000 <= energy[i] <= 1000
c.) 1 <= k <= energy.length - 1

Time Complexity = O(N), where N being number of elements in the array
Space Complexity = O(N)
 * */

public class TakingMaximumEnergyFromMysticDungeon {

    public static void main(String ar[]) {
        TakingMaximumEnergyFromMysticDungeon unit = new TakingMaximumEnergyFromMysticDungeon();
        int energy[] = {5,2,-10,-5,1};
        int k = 3;
        System.out.print("Maximum energy from mystic dungeon is " + unit.maximumEnergy(energy, k));
    }

    public int maximumEnergy(int[] energy, int k) {
        int maxEnergy = -1001; //Reason for keeping maxEnergy as -1001 in the start

        int helper[] = new int[energy.length];

        int index = 0;
        while (index < energy.length) {
            if (index - k < 0) {
                helper[index] = energy[index];
            } else {
                if (energy[index] + helper[index - k] >= energy[index]) {
                    helper[index] = energy[index] + helper[index - k];
                } else {
                    helper[index] = energy[index];
                }
            }

            if (index + k >= energy.length) {
                maxEnergy = helper[index] > maxEnergy ? helper[index] : maxEnergy;
            }
            index++;
        }
        return maxEnergy;
    }
}
