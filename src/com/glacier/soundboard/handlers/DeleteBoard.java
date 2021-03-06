package com.glacier.soundboard.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import com.glacier.soundboard.err.ErrorTypes;
import com.glacier.soundboard.err.Errors;
import com.glacier.soundboard.util.Constants;
import com.glacier.soundboard.util.UtilityMethods;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteBoard implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {
		Stage primaryStage = new Stage();
		HBox wrapthings = new HBox();
		VBox radioButtons = new VBox();
		Button btChoose = new Button("Delete This One");
		ToggleGroup toggle = new ToggleGroup();
		for(File x : new File(Constants.propertiesPath.substring(0,Constants.propertiesPath.lastIndexOf("/"))).listFiles())
		{
			if(x.getName().contains(".properties"))
			{
				Properties tempProps = new Properties();
				try {
					tempProps.load(new FileInputStream(x));
					if(tempProps.containsKey("issoundboard"))
					{
						RadioButton temp = new RadioButton(x.getName());
						temp.setToggleGroup(toggle);
						radioButtons.getChildren().add(temp);
					}
				} catch (IOException e) 
				{
					System.err.println("Error in picking a soundboard to delete at " + UtilityMethods.getCurrentTimestamp());
				}
			}
		}
		if(radioButtons.getChildren().isEmpty())
		{
			primaryStage.close();
			Errors.showErrorStage(ErrorTypes.NO_SOUNDBOARDS_AVAILABLE);
		}
		btChoose.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event)
			{
				if(toggle.getToggles().isEmpty())
				{
					primaryStage.close();
					return;
				}
				RadioButton rad = (RadioButton) toggle.getSelectedToggle();
				try {
					System.gc();
					Files.delete(new File(Constants.propertiesPath.substring(0,Constants.propertiesPath.lastIndexOf("/")+1)+ rad.getText()).toPath());
				} catch (IOException e) {
					System.err.println("Exception in deleting file at " + UtilityMethods.getCurrentTimestamp());
					e.printStackTrace();
				}	
				primaryStage.close();
			}
		});
		
		wrapthings.getChildren().add(radioButtons);
		wrapthings.getChildren().add(btChoose);
		primaryStage.setScene(new Scene(wrapthings,radioButtons.getPrefWidth()+btChoose.getPrefWidth(),radioButtons.getPrefHeight()+btChoose.getPrefHeight()));
		primaryStage.setTitle("Delete a soundboard");
		primaryStage.showAndWait();
	}

}
