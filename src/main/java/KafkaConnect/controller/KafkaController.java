package KafkaConnect.controller;

import KafkaConnect.producer.MessageProducer;
import KafkaConnect.storage.MessageStorage;
import KafkaConnect.storage.MessageStorage.MessageRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для взаимодействия с Kafka через REST API.
 */
@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
    private final MessageProducer messageProducer;
    private final MessageStorage messageStorage;

    public KafkaController(MessageProducer messageProducer, MessageStorage messageStorage) {
        this.messageProducer = messageProducer;
        this.messageStorage = messageStorage;
    }

    /**
     * Отправляет сообщение в Kafka с указанным ключом.
     *
     * @param key     ключ сообщения.
     * @param message содержимое сообщения.
     * @return подтверждение отправки.
     */
    @PostMapping("/send")
    public String sendMessage(@RequestParam String key, @RequestParam String message) {
        messageProducer.sendMessage(key, message);
        return "Message sent with key: " + key + " and content: " + message;
    }

    /**
     * Отправляет сообщение в указанный топик.
     *
     * @param topic   название топика.
     * @param key     ключ сообщения.
     * @param message содержимое сообщения.
     * @return подтверждение отправки.
     */
    @PostMapping("/send/{topic}")
    public String sendMessageToTopic(@PathVariable String topic, @RequestParam String key, @RequestParam String message) {
        messageProducer.sendMessageToTopic(topic, key, message);
        return "Message sent to topic: " + topic + " with key: " + key + " and content: " + message;
    }

    /**
     * Возвращает список всех сообщений, обработанных консумером.
     *
     * @return список сообщений.
     */
    @GetMapping("/messages")
    public List<MessageRecord> getMessages() {
        return messageStorage.getMessages();
    }

    /**
     * Возвращает сообщения с указанным ключом.
     *
     * @param key ключ для фильтрации.
     * @return список сообщений.
     */
    @GetMapping("/messages/filter")
    public List<MessageRecord> getMessagesByKey(@RequestParam String key) {
        return messageStorage.getMessagesByKey(key);
    }

    /**
     * Очищает локальное хранилище сообщений.
     *
     * @return подтверждение очистки.
     */
    @DeleteMapping("/messages")
    public String clearMessages() {
        messageStorage.clearMessages();
        return "Message storage cleared.";
    }
}
