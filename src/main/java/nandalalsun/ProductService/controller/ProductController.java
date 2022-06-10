package nandalalsun.ProductService.controller;

import lombok.RequiredArgsConstructor;
import nandalalsun.ProductService.dto.ProductRequest;
import nandalalsun.ProductService.dto.ProductResponse;
import nandalalsun.ProductService.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productDto){
        productService.createProduct(productDto);
        return new ResponseEntity<String>("Product has been created", HttpStatus.CREATED);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<String>("Record deleted", HttpStatus.OK);
    }
}
