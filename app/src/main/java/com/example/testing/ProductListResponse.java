package com.example.testing;



import java.util.ArrayList;
import java.util.Date;
public class ProductListResponse {

    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
    public class Category{
        public int id;
        public String name;
        public String image;
        public String status;
        public Date created_at;
        public Date updated_at;
    }

    public class Categorylist{
        public int id;
        public String name;
        public String image;
        public String status;
        public Date created_at;
        public Date updated_at;
    }

    public class Futureproduct{
        public int id;
        public String productname;
        public String productdetails;
        public String producttype;
        public String status;
        public String prices;
        public String c_id;
        public ArrayList<Image> images;
        public ArrayList<Category> categorys;
    }

    public class Image{
        public int id;
        public String p_id;
        public String images;
        public Date created_at;
        public Date updated_at;
    }

    public class Otherproduct{
        public int id;
        public String productname;
        public String productdetails;
        public String producttype;
        public String status;
        public String prices;
        public String c_id;
        public ArrayList<Image> images;
        public ArrayList<Category> categorys;
    }

    public class Response{
        public String status;
        public String message;
        public ArrayList<Futureproduct> futureproduct;
        public ArrayList<Otherproduct> otherproduct;
        public ArrayList<Sliderlist> sliderlist;
        public ArrayList<Categorylist> categorylist;
    }

    public class Root{
        public Response response;
    }

    public class Sliderlist{
        public int id;
        public String slider_image;
        public String status;
        public Date created_at;
        public Date updated_at;
    }


}


