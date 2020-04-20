package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		double salarioLiquido = 0;
		if(salarioBase > 1039){
			salarioBase -= calcularInss(salarioBase);
			salarioBase -= calcularIrrf(salarioBase);
			salarioLiquido = salarioBase;
		}

		return Math.round(salarioLiquido);
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salario) {
		if(salario <= 1500){
			return salario * 0.08;
		} else if(salario > 1500 && salario <= 4000){
			return salario * 0.09;
		} else if(salario > 4000){
			return salario * 0.11;
		}
		return 0.0;
	}

	private double calcularIrrf(double salario){
		if(salario > 3000 && salario <= 6000){
			return salario * 0.075;
		} else if(salario > 6000){
			return salario * 0.15;
		}
		return 0.0;
	}
}
