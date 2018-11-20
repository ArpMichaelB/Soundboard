package com.glacier.soundboard.handlers;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import com.glacier.soundboard.util.UtilityMethods;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ShowMakeSounds implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Stage primaryStage = new Stage();
		HBox wrapThings = new HBox();
		VBox buttons = new VBox();
		Properties props = UtilityMethods.getProperties();
		String[] keys = UtilityMethods.getKeysList();
		ArrayList<Button> buttonList = new ArrayList<Button>();
		HBox row = new HBox();
		buttons.getChildren().add(row);
		//we have to add the initial row first
		for(String key : keys)
		{
			if(!key.toLowerCase().contains(".photo"))
			{
				Media media = new Media(new File(props.getProperty(key)).toURI().toString());
				MediaPlayer mediaPlayer = new MediaPlayer(media);
				Button btnItem = new Button(key);
				btnItem.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{
						mediaPlayer.seek(mediaPlayer.getStartTime());
						mediaPlayer.play();
						System.out.println("Playing item " +mediaPlayer.getMedia().getSource()+" at " + UtilityMethods.getCurrentTimestamp());
					}
				});
				if(UtilityMethods.hasPhoto(key))
				{
					Image img = new Image(new File(props.getProperty(key.substring(0,key.lastIndexOf("."))+".photo")).toURI().toString());
					BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			        Background background = new Background(backgroundImage);
			        btnItem.setBackground(background);
			        btnItem.setText(" ");
			        btnItem.setMaxSize(img.getWidth(),img.getHeight());
			        btnItem.setPrefSize(img.getWidth(),img.getHeight());
			        btnItem.setMinSize(img.getWidth(),img.getHeight());
				}
				buttonList.add(btnItem);
				if(buttonList.size()%5 == 1 && buttonList.size()>5)
				{
					row = new HBox();
					buttons.getChildren().add(row);
				}
				row.getChildren().add(btnItem);
				System.out.println("Button for file " + props.getProperty(key) + " created at " + UtilityMethods.getCurrentTimestamp());
			}
		}
		for(Node x : buttons.getChildren())
		{
			System.out.println(x.getParent());
		}
		wrapThings.getChildren().add(buttons);
		//TODO: make the scene respond to the size of things
		Scene primaryScene = new Scene(wrapThings,200,200);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}

}
