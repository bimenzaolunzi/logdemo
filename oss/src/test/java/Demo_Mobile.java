

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo_Mobile {

    public void test() throws IOException {

        String dxList = "133、149、153、173、177、180、181、189、190、191、193、199";
        String ltList = "130、131、132、145、155、156、166、167、171、175、176、185、186、196";
        String ydList = "134、135、136、137、138、139、1440、147、148、150、151、152、157、158、159、172、178、182、183、184、187、188、195、197、198";
        String xnList = "1700、1701、1702、162、1703、1705、1706、165、1704、1707、1708、1709、171、167、1349、174、140、141、144、146、148";
        String suffixStr = "46789011";


        List<String> dxCollect = Arrays.stream(dxList.split("、")).collect(Collectors.toList());
        List<String> ltCollect = Arrays.stream(ltList.split("、")).collect(Collectors.toList());
        List<String> ydCollect = Arrays.stream(ydList.split("、")).collect(Collectors.toList());
        List<String> xnCollect = Arrays.stream(xnList.split("、")).collect(Collectors.toList());

        dxCollect.addAll(ltCollect);
        dxCollect.addAll(ydCollect);
        dxCollect.addAll(xnCollect);


        for (String s : dxCollect) {
            String tel="";
            if (s.length()==4){

               tel = s + suffixStr.substring(1, suffixStr.length());
                System.out.println("当前区号为4位数 "+tel );
            }else {
              tel = s + suffixStr;
            }



            //URL url = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo?mobileCode=" + tel + "&userID=");// 从webxml.com.cn找到的

            URL url = new URL("http://www.youdao.com/smartresult-xml/search.s?jsFlag=true&type=mobile&q=" + tel);

            // 2:获取连接
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);// 可以发数据
            con.setDoInput(true);// 可以读取返回的数据
            con.connect();
            // 获取连接状态
            int code = con.getResponseCode();
            if (code == 200) {
                // 连接成功，获取服务器返回IO
                InputStream in = con.getInputStream();
                byte[] bs = new byte[1024];
                int len = 0;
                while ((len = in.read(bs)) != -1) {
                    String str = new String(bs, 0, len);
                    System.err.print(str);
                }
                in.close();
            }
            con.disconnect();// 断开连接
        }
    }

}
