package com.metsoft.grpcproject.server;

import com.metsoft.grpcproject.Category;
import com.metsoft.grpcproject.ProductGetRequest;
import com.metsoft.grpcproject.ProductGetResponse;
import com.metsoft.grpcproject.ProductServiceGrpc;
import com.metsoft.grpcproject.model.Product;
import com.metsoft.grpcproject.repository.ProductRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


@GrpcService
public class ProductGrpcServer extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductRepository productRepository;
    @Autowired
    public ProductGrpcServer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void getProducts(ProductGetRequest request,
                           StreamObserver<ProductGetResponse> responseObserver) {
        Product product=getProductById(request.getId());
        ProductGetResponse productGetResponse=ProductGetResponse.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setCategory(Category.newBuilder()
                                .setId(product.getCategory().getId())
                                .setName(product.getCategory().getName()))
                .build();
                responseObserver.onNext(productGetResponse);
                responseObserver.onCompleted();

    }
    public Product getProductById(int id){
        Optional<Product> optionalProduct=productRepository.findById(id);
        Product product=null;
        if (optionalProduct.isPresent()){
            product=optionalProduct.get();
        }
        return product;
    }
}
