package application;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * QuickNote main class.
 * @author Yarií Soto - Nasera Boulehoual
 * @version 1.0, 24 May 2023
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("/view/NotePadView.fxml"));
			
			Pane windows = (Pane) loader.load();
			
			Scene scene = new Scene(windows);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			/*BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();*/
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}