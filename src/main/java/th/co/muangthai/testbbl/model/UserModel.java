package th.co.muangthai.testbbl.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserModel {

    private Long id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}
