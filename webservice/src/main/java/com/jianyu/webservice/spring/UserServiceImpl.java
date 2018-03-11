package com.jianyu.webservice.spring;

import java.util.List;

public class UserServiceImpl implements UserService {
    public List<User> getUsers() {
        return Storage.users;
    }

    public Response delete(int id) {
        Storage.users.remove(id);
        Response response = new Response();
        response.setCode("00");
        response.setMsg("success");
        return response;
    }

    public User getUser(int id) {
        return Storage.users.get(id);
    }

    public Response insert(User user) {
        return null;
    }

    public Response update(User user) {
        return null;
    }
}
