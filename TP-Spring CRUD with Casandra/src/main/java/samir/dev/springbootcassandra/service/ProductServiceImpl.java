package samir.dev.springcassandra.service;


import samir.dev.springcassandra.dto.ProductDTO;
import samir.dev.springcassandra.entity.Product;
import samir.dev.springcassandra.exception.ProductNotFoundException;
import samir.dev.springcassandra.mapper.ProductDTOMapper;
import samir.dev.springcassandra.mapper.ProductMapper;
import samir.dev.springcassandra.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final ProductMapper productMapper;
    public ProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
        this.productMapper = productMapper;

    }

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(UUID id){
        Product product = productRepository.findById(id).orElse(null);
        ProductDTO productDTO = null;
        if(product != null)
            productDTO = productDTOMapper.apply(product);

        return  productDTO;
    }
    public ProductDTO saveProduct(ProductDTO productDTO){
        Product product = productMapper.apply(productDTO);
        productRepository.insert(product);
        return productDTOMapper.apply(product);
    }

    public ProductDTO updateProduct(UUID id, ProductDTO productDTO) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElse(null);


        if (product == null)
            throw new ProductNotFoundException("Product not found");

        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());

        productRepository.save(product);

        return productDTO;
    }

    @Override
    public void deleteProduct(UUID id){
        productRepository.deleteById(id);
    }
}
