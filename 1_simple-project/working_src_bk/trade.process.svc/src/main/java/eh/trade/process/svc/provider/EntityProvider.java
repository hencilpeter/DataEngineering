package eh.trade.process.svc.provider;

import eh.trade.process.svc.model.Position;
import eh.trade.process.svc.model.Security;
import eh.trade.process.svc.model.Trade;
import eh.trade.process.svc.model.Trader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
public class EntityProvider  implements IEntityProvider {
    private static final Logger logger = LogManager.getLogger(EntityProvider.class);

    public EntityProvider(){

    }

    public Trade getTradeEntity(String tradeData){
        logger.info("Extracting Trade object from JSON Object.");
        Trade trade = new Trade();
        //removed the first additional word.
        String tradeJsonData = tradeData.substring(10);

        try{
            JSONObject jsonObject = new JSONObject(tradeJsonData);
            JSONObject jsonPosition = jsonObject.getJSONObject("position");


            trade.setTradeId(jsonObject.getInt("tradeId"));
            trade.setPositionId(jsonPosition.getInt("id"));
            trade.setTradeType(jsonObject.getString("tradeType"));
            trade.setPrice((float)jsonObject.getDouble("price"));
            trade.setQuantity((float)jsonObject.getDouble("quantity"));

            //timestamp
            String timestampStr = jsonObject.getString("timeStamp");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            java.util.Date tradeDate = dateFormat.parse(timestampStr);

            //TO-DO change the logic
            //java.util.Date temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            //        .parse(timestampStr);


            trade.setTimeStamp(new Timestamp(tradeDate.getTime()));

            logger.info("Trade object extracted from JSON Object.");
        }
        catch (Exception exception) {
        logger.error("Exception while parsing received trade object.");
        logger.error("Exception : "+ exception);
        }

        return trade;
    }

    public Trader getTraderEntity(String tradeData){
        logger.info("Extracting Trader object from JSON Object.");
        Trader trader = new Trader();

        //removed the first additional word.
        String tradeJsonData = tradeData.substring(10);

        try {
            JSONObject jsonObject = new JSONObject(tradeJsonData);
            JSONObject jsonTrader = jsonObject.getJSONObject("trader");
            trader.setSoeId(jsonTrader.getString("soeId"));
            trader.setTraderName(jsonTrader.getString("traderName"));

            logger.info("Trader object extracted from JSON Object.");
        }
        catch(Exception exception){
            logger.error("Exception while parsing received trader object.");
            logger.error("Exception : "+ exception);
        }

        return trader;
    }

    public Position getPositionEntity(String tradeData){
        logger.info("Extracting Position object from JSON Object.");
        Position position = new Position();
        //removed the first additional word.
        String tradeJsonData = tradeData.substring(10);

        try {
            JSONObject jsonObject = new JSONObject(tradeJsonData);
            JSONObject jsonPosition = jsonObject.getJSONObject("position");
            JSONObject jsonSecurity = jsonObject.getJSONObject("security");

            position.setPositionId(jsonPosition.getInt("id"));
            position.setSecurityId(jsonSecurity.getInt("id"));
            position.setFirmAccount(jsonPosition.getString("firmAccount"));

            logger.info("Trader object extracted from JSON Object.");

        }
        catch (Exception exception){
            logger.error("Exception while parsing received position object.");
            logger.error("Exception : "+ exception);
        }

        return position;
    }

    public Security getSecurityEntity(String tradeData){
        logger.info("Extracting Security object from JSON Object.");
        Security security = new Security();
        //removed the first additional word.
        String tradeJsonData = tradeData.substring(10);

        try {
            JSONObject jsonObject = new JSONObject(tradeJsonData);
            JSONObject jsonSecurity = jsonObject.getJSONObject("security");

            security.setSecurityId(jsonSecurity.getInt("id"));
            security.setType(jsonSecurity.getString("type"));
            security.setUnderlier(jsonSecurity.getString("underlier"));


            String dateStr = jsonSecurity.getString("validUntil");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date tradeDate = dateFormat.parse(dateStr);


            security.setValidUntil(tradeDate);

            logger.info("Trader object extracted from JSON Object.");

        }
        catch (Exception exception){
            logger.error("Exception while parsing received security object.");
            logger.error("Exception : "+ exception);
        }

        return security;
    }

}
