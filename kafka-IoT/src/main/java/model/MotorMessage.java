//message class
package model;

public class MotorMessage {

    public String messageid;
    public String motorname;
    public String timestamp;
    public String pressure;
    public String temperature;

    public MotorMessage(String messageid, String motorname, String timestamp, String pressure, String temperature) {
        this.messageid = messageid;
        this.motorname = motorname;
        this.timestamp = timestamp;
        this.pressure = pressure;
        this.temperature = temperature;
    }

    public MotorMessage() {
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getMotorname() {
        return motorname;
    }

    public void setMotorname(String motorname) {
        this.motorname = motorname;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

}

