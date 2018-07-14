import com.hqjy.tiku.task.TaskApplication;
import com.hqjy.tiku.task.webservice.SendTeacherList;
import com.hqjy.tiku.task.webservice.SendTeacherListResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskApplication.class)
public class WebServiceTest {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Test
    public void webServiceTest() throws Exception {
        SendTeacherList sendTeacherList = new SendTeacherList();
        sendTeacherList.setInt(1);
        sendTeacherList.setInt1(1);
        sendTeacherList.setString("1");
        sendTeacherList.setString1("1");
        SendTeacherListResponse result = (SendTeacherListResponse)webServiceTemplate.marshalSendAndReceive(
                "http://nc.hqbis.com/uapws/service/TeacherStudentServer", sendTeacherList);
        System.err.println(result.getReturn());
    }
}
