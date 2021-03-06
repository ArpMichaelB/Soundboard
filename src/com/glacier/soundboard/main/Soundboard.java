package com.glacier.soundboard.main;

import com.glacier.soundboard.handlers.AddPhotoHandler;
import com.glacier.soundboard.handlers.AddSoundsHandler;
import com.glacier.soundboard.handlers.ChooseABoard;
import com.glacier.soundboard.handlers.DeleteBoard;
import com.glacier.soundboard.handlers.DeleteItem;
import com.glacier.soundboard.handlers.ShowHowTo;
import com.glacier.soundboard.handlers.ShowMakeSounds;
import com.glacier.soundboard.util.Constants;
import com.glacier.soundboard.util.UtilityMethods;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Soundboard extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox wrapThings = new VBox();
		HBox buttons = new HBox();
		HBox buttonsTwo = new HBox();
		Text question = new Text("What would you like to do?");
		Button btSound = new Button("Add Sounds");
		Button btPhoto = new Button("Add Photos");
		Button btChoose = new Button("Choose a Board");
		Button btHow = new Button("Help me!");
		Button btStart = new Button("Start Soundboard");
		Button btDeleteBoard = new Button("Delete a Board");
		Button btDeleteSound = new Button("Delete an Item");
		btSound.setOnAction(new AddSoundsHandler());
		btPhoto.setOnAction(new AddPhotoHandler());
		btChoose.setOnAction(new ChooseABoard());
		btHow.setOnAction(new ShowHowTo());
		btStart.setOnAction(new ShowMakeSounds());
		btDeleteBoard.setOnAction(new DeleteBoard());
		btDeleteSound.setOnAction(new DeleteItem());
		buttons.getChildren().addAll(btSound,btPhoto,btChoose,btHow);
		buttonsTwo.getChildren().addAll(btStart,btDeleteBoard,btDeleteSound);
		wrapThings.getChildren().addAll(question,buttons,buttonsTwo);
		Scene primaryScene = new Scene(wrapThings,Constants.openingWidth,Constants.openingHeight);
		primaryStage.setScene(primaryScene);
		primaryStage.setTitle("Soundboard Menu");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
        UtilityMethods.setupLogs();
		launch(args);
    }

}
