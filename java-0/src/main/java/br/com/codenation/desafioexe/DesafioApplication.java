package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		int prevFib = 0;
		int fib = 1;

		List<Integer> fibSequence = new ArrayList<Integer>();
		fibSequence.add(prevFib);
		fibSequence.add(fib);

		for(int i = 2; i < 15; i++) {
			int prevTemp = fib;
			fib += prevFib;
			prevFib = prevTemp;
			fibSequence.add(fib);
		}

		return fibSequence;
	}

	public static Boolean isFibonacci(Integer a) {	
		List<Integer> fibSequence = fibonacci();
		for(Integer i : fibSequence){
			if(i.equals(a))
				return true;
		}

		return false;
	}
}