package eh.trade.booking.svc.generator;

import eh.trade.booking.svc.model.Position;
import eh.trade.booking.svc.model.Security;
import eh.trade.booking.svc.model.Trade;
import eh.trade.booking.svc.model.Trader;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class TradeDataGenerator {
    //position details
    private static long positionIdMin = 10000;
    private static long positionIdMax = 20000;
    private static List<String> firmAccountList = new ArrayList<String> (Arrays.asList("XAC", "FAC", "EMR50"));

    //security details
    private static long securityIdMin = 20001;
    private static long securityIdMax = 30000;
    private List<String> productList = new ArrayList<>( Arrays.asList("Stock", "Option","Exotic Option", "Interest Rate Swap", "Equity Swap") );
    private List<String> underlierList = new ArrayList<>(Arrays.asList("IBM.N", "APPLE.L", "JPC")) ;

    //trader data
    private List<String> traderSoeIdlist = new ArrayList<>(Arrays.asList("soe112233","soe987654","soe111222"));
    private List<String> traderNameList = new ArrayList<>(Arrays.asList("Dan Mahan", "Ben Panning", "Tom Newfelder"));

    //trade data
    private static long tradeIdMin = 30001;
    private static long tradeIdMax = 40000;
    private static long priceMax = 2000;
    private static long quantityMax = 1000;
    private static List<String> tradeTypeList = new ArrayList<>(Arrays.asList("Initial Trade", "UpSize", "Partial Unwind", "Exercise"));


    //Date Formatter
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-M-yyyy");

    public Trade getDynamicTradeData()
    {
        int tradeId = getRandomNumber(tradeIdMin, tradeIdMax);
        double quantity = getRandomNumber(1, quantityMax);
        double price = getRandomNumber(1, priceMax);

        int tradeTypeIndex = getRandomNumber(0, tradeTypeList.size()-1);
        String tradeType = tradeTypeList.get(tradeTypeIndex);
        String todayDate = formatter.format(new Date());

        return new Trade(tradeId, tradeType, quantity, price, todayDate, getDynamicSecurityData(), getDynamicPositionData(), getDynamicTraderData());
    }

    private Security getDynamicSecurityData(){
        long securityId = getRandomNumber(TradeDataGenerator.securityIdMin, TradeDataGenerator.securityIdMax);
        int productIndex = getRandomNumber(0,productList.size()-1);
        int underlierIndex = getRandomNumber(0,underlierList.size()-1);
        LocalDate validUntilDate = LocalDate.of(2025, Month.DECEMBER, 12);
        String validUntil = validUntilDate.format(dateTimeFormatter);

        return new Security(securityId, productList.get(productIndex), underlierList.get(underlierIndex), validUntil );

    }

    private Position getDynamicPositionData(){
        //position Data
        //position Id - between 10000 and 20000
        long positionId = getRandomNumber(TradeDataGenerator.positionIdMin, TradeDataGenerator.positionIdMax);
        int index = getRandomNumber(0, firmAccountList.size() - 1);
        return new Position(positionId, firmAccountList.get(index));
    }

    private Trader getDynamicTraderData(){
        int index = getRandomNumber(0, traderNameList.size()-1);
        return new Trader(traderNameList.get(index), traderSoeIdlist.get(index));
    }

    private int getRandomNumber(long startRange, long endRange){
        double randomNumber = Math.random();
       return  (int) (Math.ceil (randomNumber * (endRange - startRange)) + startRange);
    }
}
