package com.itheima.logdemo.utils;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

public class KafkaAppender extends AppenderBase<ILoggingEvent> {
    //定义属性，可以从logback.xml配置⽂文件中获取
    private String topic, brokers;

    private KafkaTemplate kafkaTemplate;

    @Override
    public void start() {
        Map<String, Object> props = new HashMap<>();
        //连接地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        //键的序列列化⽅方式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        //值的序列列化⽅方式
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //重试， 0为不不启⽤用重试机制
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        //控制批处理理⼤大⼩小，单位为字节
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //批量量发送，延迟为1毫秒，启⽤用该功能能有效减少⽣生产者发送消息次数，从⽽而提⾼高并发量量
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        //⽣生产者可以使⽤用的最⼤大内存字节来缓冲等待发送到服务器器的记录
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 1024000);

        this.kafkaTemplate = new KafkaTemplate(new DefaultKafkaProducerFactory(props));
        super.start();
    }

    public String getTopic() {
        return topic;
    }

    public String getBrokers() {
        return brokers;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setBrokers(String brokers) {
        this.brokers = brokers;
    }

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        kafkaTemplate.send(topic, iLoggingEvent.getMessage());
    }
}
