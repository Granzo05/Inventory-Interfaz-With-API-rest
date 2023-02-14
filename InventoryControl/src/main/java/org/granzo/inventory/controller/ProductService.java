package org.granzo.inventory.controller;

import org.granzo.inventory.entities.Product;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {
    @POST("/product")
    Call<Void> uploadProduct(@Body Product product);

    @PUT("/product")
    Call<Void> updateProduct(@Body Product product);

    @DELETE("/product/{code}")
    Call<Void> deleteProduct(@Path("code") String code);

    @GET("/product/{code}")
    Call<Product> getProduct(@Path("code") String code);
}
