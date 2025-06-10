package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class Welcome extends Application {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"));
		
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// create the scene
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		//BorderPane root = new BorderPane();
		Scene scene = new Scene(root);
		//String css = this.getClass().getResource("CalendarStyle.css").toExternalForm();
		//scene.getStylesheets().add(css);
		
		//System.out.print(scene);
		
//		//icon image
		Image icon = new Image("img/PumpIn_window_image.jpg");
		
		primaryStage.setTitle("PumpIn - Home");
		primaryStage.getIcons().add(icon);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}
