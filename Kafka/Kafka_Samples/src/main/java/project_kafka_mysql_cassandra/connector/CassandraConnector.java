/*
 * Author : J Hencil Peter
 * Program : Kafka Cassandra Connector class
 * */


package project_kafka_mysql_cassandra.connector;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraConnector {
    private Cluster cluster;
    private Session session;

    public void connect(final String node, final int port)
    {
        this.cluster = Cluster.builder()
                .addContactPoint(node).withPort(port).build();
        session = cluster.connect();
    }

    public Session getSession()
    {
        return this.session;
    }

    public void close()
    {
        //cluster.close();
    }
}
