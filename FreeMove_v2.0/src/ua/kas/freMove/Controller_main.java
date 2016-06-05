package ua.kas.freMove;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller_main implements Initializable{
	@FXML Label hi_user;
	@FXML Button b_login, b_signin;
	@FXML ImageView city_log = new ImageView();
	Image image_odessaLog = new Image(getClass().getResourceAsStream("img/odessa.png"));
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(Controller_login.correct==false && Controller_signUp.correct==false){
			b_login.setVisible(true); 
			b_signin.setVisible(true);
			hi_user.setVisible(false);
		}
		else if(Controller_login.correct==true && Controller_signUp.correct==false){
			hi_user.setText("Hi " + Controller_login.LOGIN+", how are you?");
		}
		else if(Controller_login.correct==false && Controller_signUp.correct==true){
			hi_user.setText("No you, " + Controller_login.LOGIN+", no party!");
		}
	}
	
	public void setImg_odessa(MouseEvent e){	 
		 city_log.setImage(image_odessaLog);
	}
	
	public void dellImg(MouseEvent e){
		city_log.setImage(null);
	}
	
	public void login(ActionEvent e) throws IOException{
		Scene login = new Scene(FXMLLoader.load(getClass().getResource("FXML/Login.fxml")));
		login.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage login_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		login_stage.setScene(login);
		login_stage.show();
	}
	
	public void sign_up(ActionEvent e) throws IOException{
		Scene sign_up_scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/SignUp.fxml")));
		sign_up_scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		app_stage.setScene(sign_up_scene);
		app_stage.show();
	}
}
