package ua.kas.freMove;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller_signUp {
	@FXML
	TextField textLOGIN, textEMAIL, textPASSWORD, textREPEAT_PASSWORD;
	@FXML
	Label login_uncorrect, password_uncorrect, email_uncorrect, login_correct, email_correct, password_correct, all;
	@FXML Button BACK;
	public static String LOGIN = "not_enter";
	public static String PASSWORD = "not_enter";
	public static boolean correct = false;
	static String way;

	public void login(ActionEvent e) throws IOException{
		Scene login = new Scene(FXMLLoader.load(getClass().getResource("FXML/Login.fxml")));
		login.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage login_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		login_stage.setScene(login);
		login_stage.show();
	}
	
	public void sign_up(ActionEvent e) throws SQLException, IOException {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/freemove", "root", "root");
		java.sql.PreparedStatement myStmt = myConn
				.prepareStatement("insert into users(user_name,password,email) values (?,?,?)");

		myStmt.setString(1, textLOGIN.getText());
		myStmt.setString(2, textPASSWORD.getText());
		myStmt.setString(3, textEMAIL.getText());

		if ((textLOGIN.getText().equals("") != true && textEMAIL.getText().equals("") != true
				&& textPASSWORD.getText().equals("") != true && textREPEAT_PASSWORD.getText().equals("") != true)) {

			if (textLOGIN.getText().contains(" ")) {
				all.setVisible(false);
				login_uncorrect.setVisible(true);
				login_correct.setVisible(false);

			} else {
				all.setVisible(false);
				login_uncorrect.setVisible(false);
				login_correct.setVisible(true);

				if (textPASSWORD.getText().contains(" ") || textREPEAT_PASSWORD.getText().contains(" ") || textREPEAT_PASSWORD.getText().equals("")|| textPASSWORD.getText().equals("")) {
					password_uncorrect.setVisible(true);
					password_correct.setVisible(false);
				}
				else {

					if (textPASSWORD.getText().equals(textREPEAT_PASSWORD.getText())) {

						password_correct.setVisible(true);
						password_uncorrect.setVisible(false);

						if (textEMAIL.getText().contains(" ") || (textEMAIL.getText().contains("@") != true)) {
							email_uncorrect.setVisible(true);
							email_correct.setVisible(false);
						}

						else if (textEMAIL.getText().contains(" ") != true && textEMAIL.getText().contains("@") == true) {

							LOGIN = textLOGIN.getText();
							PASSWORD = textPASSWORD.getText();
							email_uncorrect.setVisible(false);
							email_correct.setVisible(true);

							myStmt.executeUpdate();													
							correct = true;
							
							Scene main = new Scene(FXMLLoader.load(getClass().getResource("FXML/Main.fxml")));
							main.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
							Stage main_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
							main_stage.setScene(main);
							main_stage.show();
						}
					}
				}
			}
		}
		else{
			all.setVisible(true);
		}
	}
	
	public void back(ActionEvent e) throws IOException{
		Scene back = new Scene(FXMLLoader.load(getClass().getResource(way)));
		back.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage back_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		back_stage.setScene(back);
		back_stage.show();
	}
}
