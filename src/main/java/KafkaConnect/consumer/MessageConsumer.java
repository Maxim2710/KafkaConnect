package KafkaConnect.consumer;

import KafkaConnect.storage.MessageStorage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Сервис для обработки входящих сообщений из Kafka.
 */
@Service
public class MessageConsumer {
    private final MessageStorage messageStorage;

    public MessageConsumer(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    /**
     * Метод слушает тему "example-topic" и обрабатывает каждое полученное сообщение.
     *
     * @param message полученное сообщение.
     */
    @KafkaListener(topics = "example-topic", groupId = "example-group")
    public void consumeExampleTopic(String message) {
        System.out.println("Received from example-topic: " + message);
        messageStorage.addMessage("defaultKey", message);
    }

    /**
     * Метод слушает тему "custom-topic" и обрабатывает каждое полученное сообщение.
     *
     * @param message полученное сообщение.
     */
    @KafkaListener(topics = "custom-topic", groupId = "example-group")
    public void consumeCustomTopic(String message) {
        System.out.println("Received from custom-topic: " + message);
        messageStorage.addMessage("customKey", message);
    }



}
