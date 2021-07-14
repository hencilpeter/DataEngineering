package kafkaconsumer.simple;

public final class time_consumption {
    public static void printTimeDifferenceInMilliSeconds(long startMilliSeconds, long endMilliSeconds, long recordCount){
     long milliSecondDifference = endMilliSeconds - startMilliSeconds;
     long seconds = milliSecondDifference / 1000;
     System.out.printf("\nRecord Count : %d, Time In Sec : %d, Consumption Rate : %f seconds",
             recordCount, seconds, (double)recordCount/(double) seconds);
    }

    public static void printTimeDifferenceInSeconds(long startMilliSeconds, long endMilliSeconds, long recordCount){
        long milliSecondDifference = endMilliSeconds - startMilliSeconds;
        System.out.printf("\nRecord Count : %d, Time In ms : %d, Consumption Rate : %f ms",
                recordCount, milliSecondDifference, (double)recordCount/(double)milliSecondDifference);
    }
}
