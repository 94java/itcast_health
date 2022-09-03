import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月21日 18:30
 */
public class QiNiuTest {
    @Test
    public void test1(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = "DI6e4xkacGtlp2JX1o6tX_9hNKTwe9K3uGAe8HDf";
        String secretKey = "MIvT-2l_xAXl0uOyxI9aCY4pPFB8G6zuVHPgM0kB";
        String bucket = "hellocode";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "C:\\Users\\lihao\\Pictures\\壁纸\\wallhaven-z8wl3w_1920x1080.png";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "abc.png";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    @Test
    public void test2(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释
        String accessKey = "DI6e4xkacGtlp2JX1o6tX_9hNKTwe9K3uGAe8HDf";
        String secretKey = "MIvT-2l_xAXl0uOyxI9aCY4pPFB8G6zuVHPgM0kB";
        String bucket = "hellocode";
        String key = "abc.png";

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
