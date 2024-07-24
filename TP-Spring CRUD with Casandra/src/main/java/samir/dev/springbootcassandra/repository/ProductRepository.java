package samir.dev.springcassandra.repository;

import samir.dev.springcassandra.entity.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ProductRepository extends CassandraRepository<Product, UUID> {
}
