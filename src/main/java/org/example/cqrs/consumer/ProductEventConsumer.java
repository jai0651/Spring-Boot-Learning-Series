package org.example.cqrs.consumer;


import org.example.cqrs.event.ProductCreatedEvent;
import org.example.cqrs.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventConsumer {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @KafkaListener(topics = "product-created", groupId = "cqrs-group")
    public void handleProductCreatedEvent(ProductCreatedEvent event) {
        Product product = new Product();
        product.setId(event.getId());
        product.setName(event.getName());
        product.setPrice(event.getPrice());
        product.setQuantity(event.getQuantity());

        elasticsearchOperations.save(product);
    }
}