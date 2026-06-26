package th.co.muangthai.testbbl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.co.muangthai.testbbl.model.UserModel;
import th.co.muangthai.testbbl.request.Request;
import th.co.muangthai.testbbl.response.Response;
import th.co.muangthai.testbbl.service.UserService;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService sampleService;

    @GetMapping(value = "/users")
    public ResponseEntity<Response> getAllUser() {
        Response response = new Response();
        try {
            response = sampleService.getAllUser();
            log.info("All users: {}", response);
        } catch (Exception e) {
            log.error("Error occurred searchSample controller: ", e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }
    
    
    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<Response> getUserById(@PathVariable Long userId) {
        Response response = new Response();
        try {
            response = sampleService.getUserById(userId);
            log.info("user id: {}", response);
        } catch (Exception e) {
            log.error("Error occurred searchSample controller: ", e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }
    

    @PostMapping(value = "/users")
    public ResponseEntity<Response> createUser(@RequestBody Request request) {
        Response response = new Response();
        try {
            UserModel user = sampleService.createUser(request);
            List<UserModel> userList = new ArrayList<UserModel>();
            userList.add(user);
            response.setResultData(userList);
            log.info("create user: {}", response);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error occurred searchSample controller: ", e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PutMapping("/users/{userId}")
    public ResponseEntity<Response> updateUser(@PathVariable Long userId, @RequestBody Request request) {
    	Response response = new Response(); 
    	try {
    		UserModel user = sampleService.updateUser(userId, request);
    		List<UserModel> userList = new ArrayList<UserModel>();
            userList.add(user);
            response.setResultData(userList);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    	}catch(Exception e) {
    		log.error("Error occurred searchSample controller: ", e.getMessage(), e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    
}
