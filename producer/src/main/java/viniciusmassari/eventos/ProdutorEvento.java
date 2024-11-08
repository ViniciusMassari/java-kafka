package viniciusmassari.eventos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProdutorEvento {

    private final Producer<String, String> producer;

    public ProdutorEvento() {
        producer = criarProducer();
    }

    private Producer<String, String> criarProducer() {
        if (producer != null) {
            return producer;
        }

        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "<MACHINE_IP>:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<String, String>(properties);
    }

    public void executar() {
        String chave = UUID.randomUUID().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String data = sdf.format(new Date());
        String mensagem = data + "|" + chave + "| NOVA MENSAGEM";

        log.info("Iniciando envio da mensagem");
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("RegistroEvento", chave, mensagem);
        producer.send(record);
        producer.flush();
        producer.close();
        log.info("Mensagem enviada com sucesso [{}]", mensagem);
    }
}
