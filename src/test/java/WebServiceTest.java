import com.hqjy.tiku.task.TaskApplication;
import com.hqjy.tiku.task.webservice.SendTeacherList;
import com.hqjy.tiku.task.webservice.SendTeacherListResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringSource;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

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
                "http://nc.hqbis.com/uapws/service/TeacherStudentServer", sendTeacherList,
                new WebServiceMessageCallback() {
                    @Override
                    public void doWithMessage(WebServiceMessage webServiceMessage) throws IOException, TransformerException {
                        ((SoapMessage)webServiceMessage).writeTo(System.err);
                    }
                });
        System.err.println(result.getReturn());


    }
}
