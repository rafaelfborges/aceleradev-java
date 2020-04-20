package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        if(texto == null){
            throw new NullPointerException("Texto nulo");
        } else if(texto.isEmpty()) {
            throw new IllegalArgumentException("Texto vazio");
        } else {
            texto = texto.toLowerCase();
            int qtdCasas = 3;
            char[] textoVet = texto.toCharArray();

            for(int i = 0; i < texto.length(); i++) {
                int temp;
                char caracter = texto.charAt(i);

                if((int)caracter == 32 || (int)caracter >= 48 && (int)caracter <= 57) {
                    temp = caracter;
                } else if(((int)caracter + qtdCasas) > 122) {
                    int diferenca = ((int)caracter + qtdCasas) - 122;
                    temp = 96 + diferenca;
                } else {
                    temp = (int)caracter + qtdCasas;
                }

                caracter = (char)temp;
                textoVet[i] = caracter;
            }

            return new String(textoVet);
        }
    }

    @Override
    public String descriptografar(String texto) {
        if(texto == null){
            throw new NullPointerException("Texto nulo");
        } else if(texto.isEmpty()) {
            throw new IllegalArgumentException("Texto vazio");
        } else {
            texto = texto.toLowerCase();
            int qtdCasas = 3;
            char[] vetorChar = texto.toCharArray();

            for(int i = 0; i < texto.length(); i++) {
                int temp;
                char caracter = texto.charAt(i);

                if((int)caracter == 32 || (int)caracter >= 48 && (int)caracter <= 57) {
                    temp = caracter;
                } else if( ((int)caracter - qtdCasas) < 97) {
                    int diferenca = ( (int)caracter-qtdCasas ) - 97;
                    temp = 123 + diferenca;
                } else {
                    temp = (int)caracter - qtdCasas;
                }

                caracter = (char)temp;
                vetorChar[i] = caracter;
            }

            return new String(vetorChar);
        }
    }
}
