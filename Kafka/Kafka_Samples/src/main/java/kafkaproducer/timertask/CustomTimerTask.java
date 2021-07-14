package kafkaproducer.timertask;

import java.util.TimerTask;

class CustomTimerTask extends TimerTask {
static long ONE_SECOND = 1000;
    private long startTimeInMilliSeconds;
    private long countPublishedItems;

    public CustomTimerTask(){
        startTimeInMilliSeconds = System.currentTimeMillis();
        countPublishedItems= 0;
    }

    @Override
    public void run() {
        long timeSpent = System.currentTimeMillis() - startTimeInMilliSeconds;
        long seconds = timeSpent <= 1 ? 1: (timeSpent < 1000 ? 1: timeSpent/ONE_SECOND);
        System.out.println("seconds : " + seconds);
        System.out.println("Current Transfer rate : " + countPublishedItems/seconds+ " per second");
    }

    public void updatePublishedItems(long _countPublishedItems){
        this.countPublishedItems = _countPublishedItems;
    }

}