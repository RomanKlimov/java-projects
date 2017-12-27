//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class regex_tester {
//    public static void main(String[] args) {
//        URL url;
//        InputStream inputStream = null;
//        BufferedReader bufferedReader;
//        String line, html = "";
//        String channal_id = "";
//        Pattern pattern = Pattern.compile("(https:\\/\\/i\\.ytimg\\.com\\/vi\\/)(.*?)(\\/hqdefault\\.jpg)");
//        Matcher matcher;
//        Scanner sc = new Scanner(System.in);
//        channal_id = sc.nextLine();
//
//
//        try {
//            url = new URL("https://www.youtube.com/user/" + channal_id + "/videos?sort=p&view=0&flow=grid");
//            inputStream = url.openStream();
//            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//            while ((line = bufferedReader.readLine()) != null) {
//                matcher = pattern.matcher(line);
//
//                if (matcher.find()) {
//                    System.out.println(matcher.group(2));
//                }
//            }
//        } catch (MalformedURLException mue) {
//            mue.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            }
//        }
//    }}
//
