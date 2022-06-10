package nandalalsun.ProductService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nandalalsun.ProductService.domain.Product;
import nandalalsun.ProductService.dto.ProductRequest;
import nandalalsun.ProductService.dto.ProductResponse;
import nandalalsun.ProductService.productDao.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductDao productDao;


    public void createProduct(ProductRequest productDto){
        Product newProduct = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();

        productDao.save(newProduct);
        log.info("Product {} is saved" + newProduct.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> productList = productDao.findAll();

        List<ProductResponse> productResponse = productList.stream().map(product ->{
            return ProductResponse.builder()
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();
        }).collect(Collectors.toList());

        return productResponse;
    }

    public void delete(Long id){
      productDao.deleteById(id);
      log.info("Product {} has deleted"+ productDao.findById(id).get().getName());
    }

    public ProductResponse getProduct(Long id){
        Optional<Product> fromDatabase = productDao.findById(id);
        if(fromDatabase.isPresent()){
            ProductResponse foundProduct = ProductResponse.builder()
                    .name(fromDatabase.get().getName())
                    .description(fromDatabase.get().getDescription())
                    .price(fromDatabase.get().getPrice())
                    .build();
            return foundProduct;
        }
        else{
            return null;
        }
    }

    public ProductResponse update(ProductRequest productRequest){
        Product newProduct = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productDao.save(newProduct);

        return ProductResponse.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }

}
