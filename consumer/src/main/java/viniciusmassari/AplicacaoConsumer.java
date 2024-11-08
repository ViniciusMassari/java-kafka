package viniciusmassari;

import lombok.extern.slf4j.Slf4j;
import viniciusmassari.eventos.ConsumidorEvento;

@Slf4j
public class AplicacaoConsumer {
    public static void main(String[] args) {
        AplicacaoConsumer aplicacaoConsumer = new AplicacaoConsumer();
        aplicacaoConsumer.iniciar();
    }

    public void iniciar() {
        ConsumidorEvento consumidor = new ConsumidorEvento();

        consumidor.executar();

    }
}