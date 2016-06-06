package ua.kas.freMove;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_login{

	@FXML TextField textLOGIN;
	@FXML PasswordField textPASSWORD;
	@FXML Label uncorrect;
	@FXML Button BACK;
	static String way;
	public static String LOGIN = "not_enter";
	public static String PASSWORD = "not_enter";
	public static String ID = "not_enter";
	public static boolean correct = false;
	
	public void log_in(ActionEvent e) throws SQLException, IOException{
		
	Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/freemove", "root", "root");
		
		LOGIN = textLOGIN.getText();
		PASSWORD = textPASSWORD.getText();
		java.sql.PreparedStatement myStmt;
		myStmt = myConn.prepareStatement("select * from users where user_name = ? and password = ? ");
		myStmt.setString(1, textLOGIN.getText());
		myStmt.setString(2, textPASSWORD.getText());
		ResultSet result = myStmt.executeQuery();
		
		if(result.next()){
			ID = result.getString("id");
			correct = true;
			Scene main = new Scene(FXMLLoader.load(getClass().getResource("FXML/Main.fxml")));
			main.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
			Stage main_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			main_stage.setScene(main);
			main_stage.show();
		}
		else{
			uncorrect.setText("Uncorrect Login or Password!");
		}
	}
	public void signup(ActionEvent e) throws IOException{
		Scene sign_up_scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/SignUp.fxml")));
		sign_up_scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		app_stage.setScene(sign_up_scene);
		app_stage.show();
	}
	
	public void back(ActionEvent e) throws IOException{
		System.out.println(way);
		Scene back = new Scene(FXMLLoader.load(getClass().getResource(way)));
		back.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage back_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		back_stage.setScene(back);
		back_stage.show();
	}
}
