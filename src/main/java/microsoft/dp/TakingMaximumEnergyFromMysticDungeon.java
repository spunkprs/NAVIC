package microsoft.dp;

public class TakingMaximumEnergyFromMysticDungeon {

    public static void main(String ar[]) {
        TakingMaximumEnergyFromMysticDungeon unit = new TakingMaximumEnergyFromMysticDungeon();
        int energy[] = {5,2,-10,-5,1};
        int k = 3;
        System.out.print("Maximum energy from mystic dungeon is " + unit.maximumEnergy(energy, k));
    }

    public int maximumEnergy(int[] energy, int k) {
        int maxEnergy = -1001;

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
