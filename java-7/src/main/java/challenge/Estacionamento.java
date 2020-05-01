package challenge;

import java.util.LinkedList;
import java.util.Queue;

public class Estacionamento {
    private final int MAX_CAPACITY = 10;
    private final int MAX_POINTS = 20;
    private final int MIN_AGE = 18;
    private final int PRIORITY_AGE = 55;
    private final Queue<Carro> estacionamento = new LinkedList<>();

    public void estacionar(Carro carro) {
        validarEntradaCarro(carro);
        if(carrosEstacionados() == MAX_CAPACITY){
            Carro car = estacionamento
                    .stream()
                    .filter(c -> c.getMotorista().getIdade() < PRIORITY_AGE)
                    .findFirst()
                    .orElseThrow(() -> new EstacionamentoException("Estacionamento lotado!"));

            estacionamento.remove(car);
        }
        estacionamento.add(carro);
    }

    public int carrosEstacionados() {
        return estacionamento.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return estacionamento.contains(carro);
    }

    private void validarEntradaCarro(Carro carro){
        if(carro.getMotorista() == null) {
            throw new EstacionamentoException("Não é permitido carro autônomo!");
        } else if(carro.getMotorista().getIdade() < MIN_AGE){
            throw new EstacionamentoException("Motorista não pode ser de menor!");
        } else if(carro.getMotorista().getPontos() > MAX_POINTS){
            throw new EstacionamentoException("Habilitação suspensa, superior 20 pontos!");
        }
    }
}
