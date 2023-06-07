package com.example.testing;

import java.util.ArrayList;

public interface UpdateListtoDetail {
    public default void callback(int position, ArrayList<ProductListResponse.Futureproduct> item){}
}
