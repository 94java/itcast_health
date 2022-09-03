import org.junit.Test;
import top.hellocode.utils.SMSUtils;
import top.hellocode.utils.ValidateCodeUtils;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月24日 18:05
 */
public class SMSTest {
    @Test
    public void send(){
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        SMSUtils.sendShortMessage("18391794828",code+"");
    }
}
