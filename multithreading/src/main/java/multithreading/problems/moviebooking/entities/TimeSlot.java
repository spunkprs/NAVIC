package multithreading.problems.moviebooking.entities;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return showStartTime == timeSlot.showStartTime && showEndTime == timeSlot.showEndTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(showStartTime, showEndTime);
    }
}
