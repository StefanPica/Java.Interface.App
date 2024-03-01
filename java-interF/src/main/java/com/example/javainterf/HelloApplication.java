package com.example.javainterf;

import com.example.javainterf.Repository.ActivitateFizicaRepository;
import com.example.javainterf.Repository.ActivitateRepository;
import com.example.javainterf.Service.ActivitateService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javainterf/hello-view.fxml"));
        Parent root = loader.load();
        HelloController controller = loader.getController();

        Properties properties = new Properties();
        properties.load(new FileReader("bd.config"));


        ActivitateRepository repository = new ActivitateRepository(properties);
        ActivitateFizicaRepository repositoryFizica = new ActivitateFizicaRepository(properties);
        ActivitateService service = new ActivitateService(repository, repositoryFizica);
        controller.setService(service);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
