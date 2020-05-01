package br.com.codenation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticUtil {

	public static int average(int[] elements) {
		return Arrays.stream(elements).sum() / elements.length;
	}

	public static int mode(int[] elements) {
		return Arrays.stream(elements)
				.boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet()
				.stream()
				.max(Comparator.comparingLong(Map.Entry::getValue))
				.map(Map.Entry::getKey)
				.get();
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		int meio = elements.length / 2;
		if (elements.length % 2 == 0)
			return (elements[meio] + elements[meio - 1]) / 2;
		return elements[meio];
	}
}