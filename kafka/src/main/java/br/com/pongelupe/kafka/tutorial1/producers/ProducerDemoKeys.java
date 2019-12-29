package br.com.pongelupe.kafka.tutorial1.producers;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemoKeys {

	public static void main(String[] args) {

		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

		for (int i = 0; i < 10; i++) {
			producer.send(new ProducerRecord<>("first_topic", "id_" + i, "hello world" + i), (metadata, e) -> {
				if (e == null) {
					System.out.println("Received new metadata: " + metadata);
				} else {
					e.printStackTrace();
				}
			});
		}

		producer.flush();
		producer.close();
	}

}
