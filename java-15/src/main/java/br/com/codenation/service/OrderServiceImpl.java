package br.com.codenation.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.codenation.model.OrderItem;
import br.com.codenation.model.Product;
import br.com.codenation.repository.ProductRepository;
import br.com.codenation.repository.ProductRepositoryImpl;

public class OrderServiceImpl implements OrderService {

	private final ProductRepository productRepository = new ProductRepositoryImpl();

	/**
	 * Calculate the sum of all OrderItems
	 */
	@Override
	public Double calculateOrderValue(List<OrderItem> items) {
		return items.stream()
				.mapToDouble(orderItem -> {
					Product product = productRepository.findById(orderItem.getProductId()).orElse(null);
					if(product != null)
						return product.getIsSale()
								? 0.8 * (orderItem.getQuantity() * product.getValue())
								: orderItem.getQuantity() * product.getValue();
					return 0.0;
				}).sum();
	}

	/**
	 * Map from idProduct List to Product Set
	 */
	@Override
	public Set<Product> findProductsById(List<Long> ids) {
		return ids.stream()
				.filter(id -> productRepository.findById(id).isPresent())
				.map(id -> productRepository.findById(id).orElse(null))
				.collect(Collectors.toSet());
	}

	/**
	 * Calculate the sum of all Orders(List<OrderIten>)
	 */
	@Override
	public Double calculateMultipleOrders(List<List<OrderItem>> orders) {
		return orders.stream().mapToDouble(this::calculateOrderValue).sum();
	}

	/**
	 * Group products using isSale attribute as the map key
	 */
	@Override
	public Map<Boolean, List<Product>> groupProductsBySale(List<Long> productIds) {

		return productIds.stream()
				.map(id -> productRepository.findById(id).orElse(null))
				.collect(Collectors.groupingBy(Product::getIsSale));
	}

}