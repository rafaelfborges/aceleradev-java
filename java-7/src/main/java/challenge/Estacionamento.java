package challenge;

import java.util.concurrent.ArrayBlockingQueue;

public class Estacionamento {
    private final int MAX_CAPACITY = 10;
    private final int MAX_POINTS = 20;
    private final int MIN_AGE = 18;
    private final int PRIORITY_AGE = 55;
    private final ArrayBlockingQueue<Carro> estacionamento = new ArrayBlockingQueue<Carro>(MAX_CAPACITY);

    public void estacionar(Carro carro) {
        if(carro.getMotorista() != null
                || carro.getMotorista().getIdade() >= MIN_AGE
                || carro.getMotorista().getHabilitacao() != null
                || carro.getMotorista().getPontos() <= MAX_POINTS) {
            estacionamento.add(carro);
        } else if(estacionamento.remainingCapacity() == 0) {

        }
    }

    public int carrosEstacionados() {
        return estacionamento.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return estacionamento.contains(carro);
    }

    private void validarEntradaCarro(Carro carro){
        if(carro.getMotorista() != null){

        }
    }
}
