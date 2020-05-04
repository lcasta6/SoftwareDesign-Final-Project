
import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WordGuessClient extends Application {
	@FXML
	private Button connectBtn;

	@FXML
	private TextField ipInput;

	@FXML
	private TextField portInput;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("(Client) Word Guess!!!"); 

		Parent root = FXMLLoader.load(getClass().getResource("clientGUI.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});
	}

	MediaPlayer mediaPlayer;
	@FXML
	public void initialize()
	{
		portInput.setText("5555");
		ipInput.setText("127.0.0.1");

		try{
			String path = "src/main/resources/welcome.mp3";

			Media media = new Media(new File(path).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();

		}catch(Exception ie){System.out.println(ie.getLocalizedMessage());}
	}
	
	@FXML
	private void connectToServer(ActionEvent event) {
		mediaPlayer.stop();
		String ipAddr = ipInput.getText();
		int port = Integer.parseInt(portInput.getText());

		try {
			// load the game play scene
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("gameplayGUI.fxml"));
			loader.setController(new GameplayController(ipAddr, port));
			Parent root = loader.load();
			Scene scene = new Scene(root);

			// get the stage from the source of the ActionEvent and show it
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Game Play scene not found");
		}
	}

}