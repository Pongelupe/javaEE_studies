package br.com.pongelupe.kafka.tutorial2.consumer;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ElasticSearchBulkConsumer {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static RestHighLevelClient createClient() {
		return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
	}

	public static KafkaConsumer<String, String> createConsumer() {
		Properties properties = new Properties();

		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "kafka-demo-elasticsearch");
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
		kafkaConsumer.subscribe(Arrays.asList("twitter_tweets"));
		return kafkaConsumer;

	}

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = createClient();

		KafkaConsumer<String, String> consumer = createConsumer();

		while (true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));

			BulkRequest bulkRequest = new BulkRequest();

			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				try {
					String tweet = consumerRecord.value();
					IndexRequest indexRequest = new IndexRequest("twitter", "tweets", getTweetId(tweet)).source(tweet,
							XContentType.JSON);

					bulkRequest.add(indexRequest);
					System.out.println(tweet);
				} catch (Exception e) {
					System.out.println("bad data");
				}

			}

			if (bulkRequest.numberOfActions() > 0) {
				client.bulk(bulkRequest, RequestOptions.DEFAULT);
				consumer.commitSync();
			}
		}

//		client.close();
	}

	private static String getTweetId(String tweet) {
		try {
			Map<String, String> map = mapper.readValue(tweet, Map.class);
			return map.get("id_str");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
