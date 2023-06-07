package com.example.testing;

import java.util.Date;

public class LoginResponse {
    public class Response{
        public String status;
        public String message;
        public String token;
        public User user;
    }

    public class Root{
        public Response response;
    }

    public class User{
        public int id;
        public Object name;
        public String email;
        public Object contact_no;
        public Object lastname;
        public Object gender;
        public Object role;
        public String user_image;
        public String status;
        public Object bod;
        public Object device_token;
        public Date created_at;
        public Date updated_at;
    }
}


