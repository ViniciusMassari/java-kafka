package viniciusmassari;

import lombok.extern.slf4j.Slf4j;
import viniciusmassari.eventos.ProdutorEvento;

@Slf4j
public class AplicacaoProducer {
    public static void main(String[] args) {
        AplicacaoProducer aplicacaoProducer = new AplicacaoProducer();
        aplicacaoProducer.iniciar();
    }

    private void iniciar() {
        log.info("Iniciando a aplicação");
        ProdutorEvento produtor = new ProdutorEvento();
        produtor.executar();
    }
}