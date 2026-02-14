package multithreading.problems.moviebooking.entities;

import java.util.Date;

public class TimeSlot {
    private long showStartTime;
    private long showEndTime;
    private Date showDate;

    public TimeSlot(long startTime, long endTime) {
        this.showStartTime = startTime;
        this.showEndTime = endTime;
    }

    public long getShowStartTime() {
        return showStartTime;
    }

    public long getShowEndTime() {
        return showEndTime;
    }

    public Date getShowDate() {
        return showDate;
    }
}
