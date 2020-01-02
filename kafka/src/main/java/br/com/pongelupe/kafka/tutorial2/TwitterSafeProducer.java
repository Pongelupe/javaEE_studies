package br.com.pongelupe.kafka.tutorial2;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

public class TwitterSafeProducer {

	private static final String SECRET = "OiXjNqVXU8nrYOEBhU4vGTb4HeQrXeLBefp7hgFdgEzNQ";
	private static final String TOKEN = "329940201-FDuiWL0CZQSqdlJ2wzRJjrYcSYIOro41BnanuGLA";
	private static final String CONSUMER_SECRET = "tsF1SnMLsGGIJrfVC93A41maaq1UkLv6ciiIojdhKnFKMTWESQ";
	private static final String CONSUMER_KEY = "Ba5EjywUMSMsBPxlzolejoB2J";

	public static void main(String[] args) {
		new TwitterSafeProducer().run();
	}

	public void run() {
		BlockingQueue<String> msgQueue = new LinkedBlockingQueue<>(1000);

		Client client = createTwitterClient(msgQueue);
		client.connect();
		KafkaProducer<String, String> producer = createKafkaProducer();

		while (!client.isDone()) {
			try {
				String msg = msgQueue.poll(5, TimeUnit.SECONDS);
				System.out.println(msg);

				producer.send(new ProducerRecord<>("twitter_tweets", null, msg), (metadata, e) -> {
					if (e != null) {
						e.printStackTrace();
					}
				});

			} catch (InterruptedException e) {
				client.stop();
			}
		}
		producer.flush();
		producer.close();

	}

	private KafkaProducer<String, String> createKafkaProducer() {
		Properties properties = new Properties();

		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		//Safe Producer
		properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
		properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
		properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
		properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");

		return new KafkaProducer<>(properties);
	}

	public Client createTwitterClient(BlockingQueue<String> msgQueue) {

		Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
		StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
		List<String> terms = Lists.newArrayList("star wars");
		hosebirdEndpoint.trackTerms(terms);

		Authentication hosebirdAuth = new OAuth1(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, SECRET);

		return new ClientBuilder().name("Hosebird-Client-01") // optional: mainly for the logs
				.hosts(hosebirdHosts).authentication(hosebirdAuth).endpoint(hosebirdEndpoint)
				.processor(new StringDelimitedProcessor(msgQueue)).build();
	}

}
