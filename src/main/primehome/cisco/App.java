package com.primehome.cisco;/**
 * Created by USER on 4/29/2017.
 */
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.client.ApiKeyClient;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.net.URI;
import java.net.URISyntaxException;

public class App extends Application {

    Stage window;
    Scene scene1;
    VBox layout, layout2;

    Label generalLabel;
    // Login Scene control:
    Button login;
    TextField username;
    PasswordField password;

    // Rally Search Criteria Scene:
    Button search;
    TextField iterationStart;
    TextField iterationEnd;
    TextField iterationName;
    TextField releaseName;
    Scene scene2;

    // Rally Toolkit data
    ApiKeyClient apiKeyClient;
    String apiKey = "_YCJB1XfNSvKJBlyYBZEiXIO6PD0qvUt6p4lVGB3vtk";

    URI uri;
    {
        try {
            new URI("https://rally1.rallydev.com");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Rally Extractor");

//      there are 3 levels to creating a UI:
//        1. Define the Stage (window, which will contains all the scenes [screens])
//        2. Define the Controls (buttons, testfields, combo etc.)
//        3. Define the Layout Manager (how everything to be organized on the Stage
//        4. Add all the controls to the Layout
//        5. Add the Layout to the Scene (the actual scene/screen)
//        6. Make the Stage (window), visible.

        // general items
        generalLabel = new Label();

        // create some controls to be added
        login = new Button("Login");
        login.setFocusTraversable(true);
        username = new TextField();
        username.setPromptText("Enter your User Name");
        password = new PasswordField();
        password.setPromptText("Enter your Password");

        // For Rally Search
        search = new Button("Search Rally");
        iterationStart = new TextField();
        iterationStart.setPromptText("Insert Start Iteration");
        iterationEnd = new TextField();
        iterationEnd.setPromptText("Insert End Iteration");
        releaseName = new TextField();
        releaseName.setPromptText("Insert Release to filter");

        apiKeyClient = new ApiKeyClient(new URI("https://rally1.rallydev.com"), apiKey);

        search.setOnAction(e ->
                {
                    // if Iteration Start/End && Release are empty:
                    if(iterationStart.getText() == null || releaseName.getText() == null)
                    {
                        window.setScene(scene1);
                        window.show();
                        System.out.println("Empty Iteration Start or Release Name!");
                    }
                    // send Rally request
                }
        );
        /*
        Construct the apiKey object in order to communicate with Rally
        Need to add input of user for this apiKey (each user should follow Rally creation of said Key
        */

        generalLabel.setText(String.valueOf(apiKeyClient));
        generalLabel.setTooltip(new Tooltip(String.valueOf(apiKey)));

        // define out Layout Manager (stackpane)
        layout = new VBox(10);
        //Attribute.Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        //Attribute.Layout 2 (Rally Search Screen)
        VBox layout2 = new VBox(10);
        layout2.setPadding(new Insets(20, 20, 20, 20));

        // add to the layout, after getting all Children (controls)
        layout.getChildren().addAll(username, password, login);

        // Rally Search Layout
        layout2.getChildren().addAll(releaseName, iterationStart, iterationEnd, search, generalLabel);

        // add to the Scene
        scene1 = new Scene(layout, 300, 250);

        // Rally Search Scene (2)
        scene2 = new Scene(layout2, 300, 250);

        // set the scene to display on start
        window.setScene(scene2);
        window.show();

    }


}
