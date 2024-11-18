package KafkaConnect.exception;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * Обработчик ошибок для Kafka Listener.
 */
@Component
public class KafkaErrorHandler implements CommonErrorHandler {

    /**
     * Обрабатывает исключения, не связанные с записями Kafka.
     *
     * @param thrownException исключение, вызвавшее ошибку.
     * @param consumer        экземпляр Consumer.
     * @param container       контейнер прослушивателя сообщений.
     * @param batchListener   признак пакетной обработки.
     */
    @Override
    public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer,
                                     MessageListenerContainer container, boolean batchListener) {
        System.err.println("Error in consumer: " + consumer);
        System.err.println("Error in container: " + container);
        System.err.println("Batch listener: " + batchListener);
        System.err.println("Exception message: " + thrownException.getMessage());
    }
}
