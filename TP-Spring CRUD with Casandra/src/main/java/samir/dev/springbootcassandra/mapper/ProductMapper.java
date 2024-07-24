package samir.dev.springcassandra.mapper;

import samir.dev.springcassandra.dto.ProductDTO;
import samir.dev.springcassandra.entity.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductMapper implements Function<ProductDTO, Product> {
    @Override
    public Product apply(ProductDTO productDTO) {
        return new Product(productDTO.id(), productDTO.name(), productDTO.description(), productDTO.price());
    }
}
