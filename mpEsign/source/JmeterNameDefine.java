import sun.misc.BASE64Decoder;

import java.io.*;
import java.security.MessageDigest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.util.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class JmeterNameDefine {

    //转base64
    public String file2Base64(String filePath) {
        FileInputStream file = new FileInputStream(filePath);
        byte[] bdata = IOUtils.toByteArray(file); 
        return Base64.encodeBase64String(bdata).replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
    }
    //文件MD5
    public String file2Md5(String filePath){
        FileInputStream file = new FileInputStream(filePath);
        byte[] bdata = IOUtils.toByteArray(file);
        return DigestUtils.md5Hex(bdata).replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
    }

    // 时间戳转换成汉字-公司用
    public String time2String(int time) {
        char[] numArray = { '拉', '扎', '斯', '造', '梦', '工', '厂', '风', '雪', '司' };
        char[] val = String.valueOf(time).toCharArray();
        String returnString="";
        for (int i=0;i<=9;i++) {
            String m = val[i] + "";
            int n = Integer.parseInt(m);
            returnString= returnString + numArray[n];
        }
        return returnString;
    }

    // 哈希值生成用
    public static byte[] createChecksum(String filename) throws Exception {
        InputStream fis =  new FileInputStream(filename);
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("SHA1");//Java Security name (such as "SHA", "MD5", and so on).
        int numRead;

        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    // 文件哈希值
    public static String getMD5Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        String result = "";
        for (int i=0; i < b.length; i++) {
            result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }

    // base64String
    public static void base64StringToPdf(String base64Content,String filePath){
        BASE64Decoder decoder = new BASE64Decoder();
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            byte[] bytes = decoder.decodeBuffer(base64Content);//base64编码内容转换为字节数组
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if(!path.exists()){
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while(length != -1){
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                bis.close();
                fos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}