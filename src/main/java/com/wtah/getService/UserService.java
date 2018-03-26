package com.wtah.getService;

import com.wtah.model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserService {
    @WebMethod
    public void addUser(User user);

    @WebMethod
    public List<User> getUsers();
}
