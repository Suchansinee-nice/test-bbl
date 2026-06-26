package th.co.muangthai.testbbl.service;

import lombok.extern.log4j.Log4j2;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import th.co.muangthai.testbbl.model.UserModel;
import th.co.muangthai.testbbl.request.Request;
import th.co.muangthai.testbbl.response.Response;

import java.util.ArrayList;
import java.util.List;


@Service
@Log4j2
public class UserService {
	
	@Value("${external.api.users-url}")
    private String usersData;
	
	private final RestClient restClient = RestClient.create();

    public Response getAllUser() {
    	
        Response response = new Response();

        try {
        	List<UserModel> users =  restClient.get()
            .uri(usersData)
            .retrieve()
            .body(new ParameterizedTypeReference<List<UserModel>>() {});
        	
        	response.setResultData(users);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
    
    
    public Response getUserById(Long userId) {
    	
        Response response = new Response();
        List<UserModel> users = new ArrayList<UserModel>();

        try {
        	UserModel user = restClient.get()
                    .uri(usersData + "/" + userId)
                    .retrieve()
                    .onStatus(status -> status.value() == 404, (req, res) -> {
                        throw new RuntimeException("User not found");
                    })
                    .body(UserModel.class);
        		
        		users.add(user);
                response.setResultData(users);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public UserModel createUser(Request request) {

        UserModel user = restClient.post()
                .uri(usersData)
                .body(request)
                .retrieve()
                .body(UserModel.class);

        return user;
    }
    
    public UserModel updateUser(Long userId, Request request) {
    	UserModel user = restClient.put()
                .uri(usersData + "/" + userId)
                .body(request)
                .retrieve()
                .body(UserModel.class);
    	
    	return user;
    }

	
}
