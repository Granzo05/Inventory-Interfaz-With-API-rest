package org.granzo.inventory.controller;

import org.granzo.inventory.entities.Product;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;


public class ApiController {
    private final ProductService productService;
    private final ConstantData constantData = new ConstantData();

    public ApiController() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(constantData.getAPI_URL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        productService = retrofit.create(ProductService.class);
    }

    public void uploadProduct(Product product) throws IOException {
        Call<Void> call = productService.uploadProduct(product);
        System.out.println("Se acaba de enviar un producto");
        System.out.println(product);
        call.execute();
    }

    public void updateProduct(Product product) throws IOException {
        Call<Void> call = productService.updateProduct(product);
        call.execute();
    }

    public void deleteProduct(String productCode) throws IOException {
        Call<Void> call = productService.deleteProduct(productCode);
        call.execute();
    }

    public Product getProduct(String productCode) throws IOException {
        Call<Product> call = productService.getProduct(productCode);
        Response<Product> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException("The product doesn't exist: " + response.code() + " - " + response.message());
        }
    }
}
