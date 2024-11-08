package viniciusmassari.eventos;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsumidorEvento {

    private final KafkaConsumer<String, String> consumer;

    public ConsumidorEvento() {
        this.consumer = criarConsumer();
    }

    private KafkaConsumer<String, String> criarConsumer() {
        if (consumer != null) {
            return consumer;
        }
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "<MACHINE_IP>:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "default");

        return new KafkaConsumer<String, String>(properties);
    }

    public void executar() {
        List<String> topicos = new ArrayList<>();
        topicos.add("RegistroEvento");
        consumer.subscribe(topicos);

        log.info("Iniciando consumer...");
        boolean continuar = true;
        while (continuar) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
            for (ConsumerRecord<String, String> record : records) {
                gravarMensagem(record.topic(), record.partition(), record.value());
                if (record.value().equals("FECHAR")) {
                    continuar = false;
                }
            }
        }
        consumer.close();
    }

    private void gravarMensagem(String topico, int particao, String mensagem) {
        log.info("Topico:{}, Partição:{}, Mensagem:{}", topico, particao, mensagem);
    }
}
