package org.example.cqrs.service;

import org.example.cqrs.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductQueryService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public List<Product> searchProducts(String name) {
        Criteria criteria = Criteria.where("name").contains(name);
        CriteriaQuery query = new CriteriaQuery(criteria);

        SearchHits<Product> searchHits = elasticsearchOperations.search(query, Product.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}