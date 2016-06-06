package ua.kas.freMove;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	@FXML Button b_login, b_signin,btn_message;
	@FXML ImageView city_log = new ImageView();
	Image image_odessaLog = new Image(getClass().getResourceAsStream("img/odessa.png"));
	static String user_name="not_enter",user_id="not_enter";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(Controller_login.correct==false && Controller_signUp.correct==false){
			b_login.setVisible(true); 
			b_signin.setVisible(true);
			hi_user.setVisible(false);
		}
		else if(Controller_login.correct==true && Controller_signUp.correct==false){
			hi_user.setText("Hi " + Controller_login.LOGIN+", how are you?");
			user_name=Controller_login.LOGIN;
			btn_message.setVisible(true);
		}
		else if(Controller_login.correct==false && Controller_signUp.correct==true){
			hi_user.setText("No you, " + Controller_signUp.LOGIN+", no party!");
			user_name=Controller_signUp.LOGIN;
			btn_message.setVisible(true);
		}
	}
	
	public void setImg_odessa(MouseEvent e){	 
		 city_log.setImage(image_odessaLog);
	}
	
	public void dellImg(MouseEvent e){
		city_log.setImage(null);
	}
	
	public void login(ActionEvent e) throws IOException{
		Controller_login.way="FXML/Main.fxml";
		Scene login = new Scene(FXMLLoader.load(getClass().getResource("FXML/Login.fxml")));
		login.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage login_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		login_stage.setScene(login);
		login_stage.show();
	}
	
	public void sign_up(ActionEvent e) throws IOException{
		Controller_signUp.way="FXML/Main.fxml";
		Scene sign_up_scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/SignUp.fxml")));
		sign_up_scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		app_stage.setScene(sign_up_scene);
		app_stage.show();
	}
	
	public void chat(ActionEvent e) throws IOException{
		Controller_chat.user_name=user_name;
		Controller_chat.way="FXML/Main.fxml";
		Scene chat = new Scene(FXMLLoader.load(getClass().getResource("FXML/Chat.fxml")));
		chat.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage chat_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		chat_stage.setScene(chat);
		chat_stage.show();
	}
	
	public void odessa(ActionEvent e) throws IOException, SQLException{
		if(Controller_login.LOGIN.equals("not_enter")==false){
			Controller_menu.user_name=Controller_login.LOGIN;
			Controller_menu.user_password=Controller_login.PASSWORD;
			Controller_menu.user_id=Controller_login.ID;
		}
		if(Controller_signUp.LOGIN.equals("not_enter")==false){
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/freemove", "root", "root");
			ResultSet myRs = null;
			java.sql.PreparedStatement myStmt;
			myStmt = myConn.prepareStatement("select * from users where user_name =? and password =?");
			myStmt.setString(1, Controller_login.LOGIN);
			myStmt.setString(2, Controller_login.PASSWORD);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				user_id = myRs.getString("id");
			}
			Controller_menu.user_id = user_id;
			Controller_menu.user_name = Controller_signUp.LOGIN;
			Controller_menu.user_password = Controller_signUp.PASSWORD;
		}
		
		Controller_menu.title_string_start="Odessa";
		Controller_menu.way="FXML/Main.fxml";
		Controller_menu.img = "img/odessa.png";
		Controller_menu.b1="Тур. мітки";
		Controller_menu.b2="Харчування";
		Controller_menu.b3="Проживання";
		Controller_menu.b4="Дозвілля";
		Controller_menu.b5="Транспорт";
		Scene odess_scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Menu.fxml")));
		odess_scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage odess_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		odess_stage.setScene(odess_scene);
		odess_stage.show();
	}
}
