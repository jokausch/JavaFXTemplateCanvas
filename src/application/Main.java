package application;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {

	static Canvas canvas = new Canvas();
	static float HEIGHT, WIDTH;
	static GraphicsContext g;
	double frameRate;
	double old;
	int cycleCount;
	double elapsedTime, lastNanoTime = System.nanoTime();

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		Pane centerPane = new Pane();
		centerPane.autosize();
		centerPane.setStyle("-fx-background-color: #ADD8E6;");
		centerPane.getChildren().add(canvas);
		root.setCenter(centerPane);

		canvas.widthProperty().bind(centerPane.widthProperty());
		canvas.heightProperty().bind(centerPane.heightProperty());

		Scene scene = new Scene(root, 1024, 768);
		// Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFX Stuff");
		// primaryStage.setFullScreen(true);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		g = canvas.getGraphicsContext2D();

		HEIGHT = (float) canvas.getHeight();
		WIDTH = (float) canvas.getWidth();

		canvas.setOnMouseDragged((MouseEvent event) -> {

		});

		canvas.setOnMouseClicked((MouseEvent event) -> {

		});

		AnimationTimer animator = new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {

				elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
				lastNanoTime = currentNanoTime;

				cycleCount++;

				if (currentNanoTime - old >= 1000000000) {
					frameRate = 1000000000 / ((currentNanoTime - old) / cycleCount);
					cycleCount = 0;
					old = System.nanoTime();
				}

				g.clearRect(0, 0, WIDTH, HEIGHT);
				
				
				g.setFill(Color.rgb(255, 0, 0, 1));
                g.setFont(new Font(14));
                g.fillText("Curent Framerate: " + Math.round(frameRate), 10, Main.HEIGHT - 10);

			}
		};
		animator.start();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
