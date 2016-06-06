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
import javafx.stage.Stage;

public class Controller_menu implements Initializable{

	@FXML Button btn_1,btn_2,btn_3,btn_3_4,btn_4,btn_5,btn_message,BACK;
	@FXML Label user,title;
	@FXML ImageView city_log;
	static String b1="",b2="",b3="",b3_4="",b4="",b5="",img,user_id="not_enter",user_name="not_enter",user_password="not_enter",title_string="",title_string_start="",whith_btn="";
	static boolean bool_b1=true,bool_b2=true,bool_b3=true,bool_b4=true,bool_b5=true,bool_b3_4=false;
	Image image;
	static String way;
	Stage stage;
	Scene scene;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		title.setText(title_string_start);
		
		if(title_string.equals("")!=true){
			title.setText(title_string);
		}
		btn_1.setVisible(bool_b1);
		btn_2.setVisible(bool_b2);
		btn_3.setVisible(bool_b3);
		btn_3_4.setVisible(bool_b3_4);
		btn_4.setVisible(bool_b4);
		btn_5.setVisible(bool_b5);
		
		if(user_name.equals("not_enter")){
			btn_message.setVisible(false);
		}
		else{
			user.setText(user_name);
		}
		
		image = new Image(getClass().getResourceAsStream(img));
		city_log.setImage(image);
		btn_1.setText(b1);
		btn_2.setText(b2);
		btn_3.setText(b3);
		btn_3_4.setText(b3_4);
		btn_4.setText(b4);
		btn_5.setText(b5);
	}	
	
	public void back(ActionEvent e) throws IOException{
		
		if(whith_btn.equals("")||whith_btn.equals("")||whith_btn.equals("")||whith_btn.equals("")||whith_btn.equals("")){
			scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Main.fxml")));
		}
		
		if(whith_btn.equals("first")||whith_btn.equals("second")||whith_btn.equals("third")||whith_btn.equals("fourth")||whith_btn.equals("fifth")){
			title_string = "";
			way = "FXML/Menu.fxml";
			img = "img/odessa.png";
			b1="Тур. мітки";
			b2="Харчування";
			b3="Проживання";
			b4="Дозвілля";
			b5="Транспорт";
			bool_b3_4 = false;
			bool_b3 = true;
			bool_b4 = true;
			bool_b5 = true;
			whith_btn = "";
			scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Menu.fxml")));
		}
		
		if(whith_btn.equals("fourth first") || whith_btn.equals("fourth second")){
			title_string = "Lesure";
			way = "FXML/Menu.fxml";
			img = "img/odessa.png";
			b1 = "Розваги";
			b2 = "Мистецтво";
			b3_4 = "Афіша подій";
			bool_b3_4 = true;
			bool_b3 = false;
			bool_b4 = false;
			bool_b5 = false;
			whith_btn = "fourth";
			scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Menu.fxml")));
		}
		
		scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void chat(ActionEvent e) throws IOException{
		Controller_chat.user_name=user_name;
		Controller_chat.way="FXML/Menu.fxml";
		scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Chat.fxml")));
		scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	public void firstButton(ActionEvent e) throws IOException {
		
		if(whith_btn.equals("first")){
			
			Controller_tourism.type="must_see";
			Controller_tourism.user_name=user_name;
			Controller_tourism.user_password=user_password;
			Controller_tourism.user_id=user_id;
			
			if(user_name.equals("Admin") && user_password.equals("Admin")){	
				System.out.println("Admin");
			}
			else{
				scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Tourism.fxml")));	
			}	
		}
		
		if (whith_btn.equals("")) {
			title_string = "Tourism";
			way = "FXML/Menu.fxml";
			img = "img/odessa.png";
			b1 = "Обов'язкові";
			b2 = "Колоритні";
			b3_4 = "Тематичні";
			bool_b3_4 = true;
			bool_b3 = false;
			bool_b4 = false;
			bool_b5 = false;
			whith_btn = "first";
			scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Menu.fxml")));
		}	
		
		if(whith_btn.equals("fourth")){
			title_string = "Fun";
			way = "FXML/Menu.fxml";
			img = "img/odessa.png";
			b1 = "Активні";
			b2 = "Сімейні";
			b3 = "Релакс";
			b4 = "Клуби/бари";
			bool_b3_4 = false;
			bool_b3 = true;
			bool_b4 = true;
			bool_b5 = false;
			whith_btn = "fourth first";
			scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Menu.fxml")));	
		}
		
		scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
	}

	public void secondButton(ActionEvent e) throws IOException {
		
		if(whith_btn.equals("first")){
			
			Controller_tourism.type="colorful";
			Controller_tourism.user_name=user_name;
			Controller_tourism.user_password=user_password;
			Controller_tourism.user_id=user_id;
			
			if(user_name.equals("Admin") && user_password.equals("Admin")){	
				System.out.println("Admin");
			}
			else{
				scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Tourism.fxml")));	
			}	
		}
		
		if (whith_btn.equals("")) {
			title_string = "Food";
			way = "FXML/Menu.fxml";
			img = "img/odessa.png";
			b1 = "Fast-food";
			b2 = "Кафе";
			b3 = "Ресторани";
			b4 = "Бари";
			bool_b3 = true;
			bool_b3_4 = false;
			bool_b5 = false;
			whith_btn = "second";
			scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Menu.fxml")));
		}
		if(whith_btn.equals("fourth")){
			title_string = "Art";
			way = "FXML/Menu.fxml";
			img = "img/odessa.png";
			b1 = "Театри";
			b2 = "Галереї";
			b3_4 = "Кінотеатри";
			b4 = "Клуби/бари";
			bool_b3_4 = true;
			bool_b3 = false;
			bool_b4 = false;
			bool_b5 = false;
			whith_btn = "fourth second";
			scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Menu.fxml")));
		}
		
		
		scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void thirdButton(ActionEvent e) throws IOException {
		if (whith_btn.equals("")) {
			title_string = "Live";
			way = "FXML/Menu.fxml";
			img = "img/odessa.png";
			b1 = "Готелі";
			b2 = "Квартири";
			b3_4 = "Хостелі";
			bool_b3_4 = true;
			bool_b3 = false;
			bool_b4 = false;
			bool_b5 = false;
			whith_btn = "third";
			scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Menu.fxml")));
			scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
	}
	
	public void third_fourthButton(ActionEvent e) throws IOException {
		
		if(whith_btn.equals("first")){
			
			Controller_tourism.type="themed";
			Controller_tourism.user_name=user_name;
			Controller_tourism.user_password=user_password;
			Controller_tourism.user_id=user_id;
			
			if(user_name.equals("Admin") && user_password.equals("Admin")){	
				System.out.println("Admin");
			}
			else{
				scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Tourism.fxml")));	
			}	
		}
		scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Tourism.fxml")));
		scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	public void fouthButton(ActionEvent e) throws IOException {
		if (whith_btn.equals("")) {
			title_string = "Lesure";
			way = "FXML/Menu.fxml";
			img = "img/odessa.png";
			b1 = "Розваги";
			b2 = "Мистецтво";
			b3_4 = "Афіша подій";
			bool_b3_4 = true;
			bool_b3 = false;
			bool_b4 = false;
			bool_b5 = false;
			whith_btn = "fourth";
			scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Menu.fxml")));
			scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
	}

	public void fifthButton(ActionEvent e) throws IOException {
		if (whith_btn.equals("")) {
			title_string = "Transport";
			way = "FXML/Menu.fxml";
			img = "img/odessa.png";
			b1 = "Залізниця";
			b2 = "Автобуси";
			b3 = "Літаки";
			b4 = "Taxi";
			bool_b3 = true;
			bool_b4 = true;
			bool_b5 = false;
			whith_btn = "fifth";
			scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Menu.fxml")));
			scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
	}
}