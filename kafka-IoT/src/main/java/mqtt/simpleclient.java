package mqtt;

import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class simpleclient {


    public static void main(String args[]){

        System.out.println("MQTT client starts...");

        final Mqtt5AsyncClient client = Mqtt5Client.builder().serverHost("broker.hivemq.com").buildAsync();

        byte[] testmessage = "Publisher Test Message...".getBytes(StandardCharsets.UTF_8);
            client.connect()
                    .thenAccept(connAck -> System.out.println("connected " + connAck))
                    .thenCompose(v -> client.publishWith().topic("testtopic/hencil").payload(testmessage).qos(MqttQos.EXACTLY_ONCE).send());

        try {
                TimeUnit.MILLISECONDS.sleep(1000);
        }catch(Exception exception){
            System.out.println("Exception raised : " + exception);
        }


        System.out.println("MQTT Client Ends...");
    }
}
