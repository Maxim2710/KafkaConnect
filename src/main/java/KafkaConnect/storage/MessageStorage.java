package KafkaConnect.storage;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Компонент для локального хранения обработанных сообщений.
 */
@Component
public class MessageStorage {
    private final List<MessageRecord> messages = new ArrayList<>();

    /**
     * Добавляет сообщение в хранилище.
     *
     * @param key     ключ сообщения.
     * @param message содержимое сообщения.
     */
    public void addMessage(String key, String message) {
        messages.add(new MessageRecord(key, message, LocalDateTime.now()));
    }

    /**
     * Возвращает список всех сохраненных сообщений.
     *
     * @return список сообщений.
     */
    public List<MessageRecord> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    /**
     * Фильтрует сообщения по ключу.
     *
     * @param key ключ для фильтрации.
     * @return список сообщений с указанным ключом.
     */
    public List<MessageRecord> getMessagesByKey(String key) {
        return messages.stream()
                .filter(msg -> msg.getKey().equals(key))
                .collect(Collectors.toList());
    }

    /**
     * Очищает хранилище сообщений.
     */
    public void clearMessages() {
        messages.clear();
    }

    /**
     * Вспомогательный класс для хранения метаданных сообщений.
     */

    public static class MessageRecord {
        private final String key;
        private final String message;
        private final LocalDateTime timestamp;

        public MessageRecord(String key, String message, LocalDateTime timestamp) {
            this.key = key;
            this.message = message;
            this.timestamp = timestamp;
        }

        public String getKey() {
            return key;
        }

        public String getMessage() {
            return message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}
