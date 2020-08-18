/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.assessment.productinfo.model.webservice;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {


    @FormUrlEncoded    // annotation that used with POST type request
    @POST("addNewProduct") // specify the sub url for our base url
    Observable<ProductAddResponse> addProductApiCall(@Field("product_name") String product_name,
                                              @Field("product_desc") String product_desc,
                                              @Field("product_quantity") int product_quantity, @Field("product_price") int product_price, @Field("user_mobile_no") String user_mobile_no);
    @FormUrlEncoded    // annotation that used with POST type request
    @POST("addNewProduct") // specify the sub url for our base url
    Call<ProductAddResponse> addProductApiCall2(@Field("product_name") String product_name,
                                                @Field("product_desc") String product_desc,
                                                @Field("product_quantity") int product_quantity, @Field("product_price") int product_price, @Field("user_mobile_no") String user_mobile_no);


//    @FormUrlEncoded    // annotation that used with POST type request
//    @POST("addNewProduct") // specify the sub url for our base url
//    Observable<ProductAddResponse> addProductApiCall(@Field("product_name") String product_name,
//                                                     @Field("product_desc") String product_desc,
//                                                     @Field("product_quantity") String product_quantity, @Field("product_price") String product_price, @Field("user_mobile_no") String user_mobile_no);

}
