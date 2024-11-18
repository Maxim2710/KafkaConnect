package KafkaConnect.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Сервис для отправки сообщений в Kafka.
 */
@Service
public class MessageProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Отправляет сообщение в Kafka с указанным ключом.
     *
     * @param key     ключ сообщения.
     * @param message содержимое сообщения.
     */
    public void sendMessage(String key, String message) {
        kafkaTemplate.send("example-topic", key, message);
    }

    /**
     * Отправляет сообщение в указанный топик.
     *
     * @param topic   название топика.
     * @param key     ключ сообщения.
     * @param message содержимое сообщения.
     */
    public void sendMessageToTopic(String topic, String key, String message) {
        kafkaTemplate.send(topic, key, message);
    }
}
