import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.input.KeyCode.ENTER;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);

        }
    @Override
    public void start(Stage primaryStage) throws Exception {
        WebView webView = new WebView();
        URL url;
        InputStream inputStream = null;
        BufferedReader bufferedReader;
        String line, html = "";
        String channel_id = "";
        Pattern pattern = Pattern.compile("(https:\\/\\/i\\.ytimg\\.com\\/vi\\/)(.*?)(\\/hqdefault\\.jpg)");
        Matcher matcher;
        System.out.println("Enter the username of channel, please");
        Scanner sc = new Scanner(System.in);
        channel_id = sc.nextLine();
        List<String> linkslist = new ArrayList<>();


        try {
            url = new URL("https://www.youtube.com/user/" + channel_id + "/videos?sort=p&view=0&flow=grid");
            inputStream = url.openStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine()) != null) {
                matcher = pattern.matcher(line);

                if (matcher.find()) {
                    linkslist.add(matcher.group(2));
//                    System.out.println(linkslist);
                }
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        System.out.println("Now You can see the main page of channel, press ENTER to see the top videos of this channel");
        Iterator<String> iterator = linkslist.iterator();
//        String location = "E:\Univ\java_projects\YouTubePlayer\src\html.html";
        webView.getEngine().load("https://www.youtube.com/user/" + channel_id);
//        Pane canvas = new Pane();
//        canvas.getChildren().addAll(webView);
        webView.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case ENTER:
                    if (iterator.hasNext()) {
//                        System.out.println("2");
                        String currentLink = iterator.next();

                        try(FileWriter writer = new FileWriter("E:\\Univ\\java_projects\\YouTubePlayer\\src\\html.html", false))
                        {

                            String text = "<iframe width=\"640\" height=\"360\" src=\"https://www.youtube.com/embed/" + currentLink + "\" frameborder=\"0\" gesture=\"media\" allow=\"encrypted-media\" allowfullscreen></iframe>";
                            writer.write(text);

                            writer.flush();
                        }
                        catch(IOException ex){

                            System.out.println(ex.getMessage());
                        }
                        webView.getEngine().load("file:///E:/Univ/java_projects/YouTubePlayer/src/html.html");
                    }
            }
        });
        webView.setPrefSize(640, 375);
        primaryStage.setScene(new Scene(webView));
        primaryStage.show();


    }


}
