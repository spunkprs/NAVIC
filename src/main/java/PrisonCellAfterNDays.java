import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrisonCellAfterNDays {
	
	public int[] prisonAfterNDays(int[] cells, int N) {
		
		Set<String> set = new HashSet<String>();
		int size = 0;
		boolean flag = false;
		
		for (int i = 1; i <= N; i++) {
			int nextDayVal[] =  transformationForNextDay(cells);
			String str = Arrays.toString(nextDayVal);
			if (!set.contains(str)) {
				set.add(str);
				size++;
			} else {
				flag = true;
				break;
			}
			cells = nextDayVal;
		}
		
		if (flag) {
			N = N % size;
			for (int i = 1; i <= N; i++) {
				cells = transformationForNextDay(cells);
			}
		}
        return cells;
    }

	private int[] transformationForNextDay(int[] cells) {
		int temp[] = new int[cells.length];
		
		for (int i = 1; i <= cells.length - 2; i++) {
			if (cells[i - 1] == cells[i + 1]) {
				temp[i] = 1;
			}
		}
		return temp;
	}

}
