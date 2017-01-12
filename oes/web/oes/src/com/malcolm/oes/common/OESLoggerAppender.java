package com.malcolm.oes.common;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

public class OESLoggerAppender extends DailyRollingFileAppender {

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        // To judge the present level is the same as this priority or not.
        return this.threshold.equals(priority);
    }
}
