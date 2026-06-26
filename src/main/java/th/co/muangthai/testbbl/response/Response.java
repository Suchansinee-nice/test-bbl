package th.co.muangthai.testbbl.response;

import lombok.Data;
import th.co.muangthai.testbbl.model.UserModel;

import java.util.List;

@Data
public class Response {

    private List<UserModel> resultData;
}
