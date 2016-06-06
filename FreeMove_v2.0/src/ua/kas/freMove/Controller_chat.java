package ua.kas.freMove;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Controller_chat implements Initializable{
	
	static String way;
	@FXML ComboBox<String> with;
	@FXML TextArea message;
	@FXML ListView<String> list;
	@FXML Button BACK;
	static String users,time,M,user_name="not_enter";
	static Boolean bool = false;
	static int id,refresh = 0,click;
	static Timer t = new Timer();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/freemove", "root", "root");
			Statement rstat = conn.createStatement();
			ResultSet result = rstat.executeQuery("select user_name from users");
			
			while(result.next()){
				with.getItems().addAll(result.getString("user_name"));
			}
		} catch (SQLException e) {}
	}
	
	public void send(ActionEvent e) throws SQLException {
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/freemove", "root", "root");
			Statement rstat = conn.createStatement();
			ResultSet result = rstat.executeQuery("select * from message");
			while(result.next()){
				users = result.getString("users");
				
				if(users.contains(user_name) && users.contains(with.getEditor().getText())){
					click = result.getInt("click");
					M = result.getString("message");
					id = result.getInt("id");
					time = result.getString("time");
					click++;
					
					java.sql.PreparedStatement myStmt = conn
							.prepareStatement("UPDATE message SET message=?, click=?, time=? WHERE id=?");
					myStmt.setString(1, M + "**" + click + user_name +  click + "**" + "&" + click + message.getText() + click + "&");
					myStmt.setInt(2, click);
					String time_now = find_time();
					myStmt.setString(3, time + "&" + click + time_now + click + "&");
					myStmt.setInt(4, id);
					myStmt.executeUpdate();
					bool = true;
					refresh(e);
				}
			}	
			
			if(bool == false){
				java.sql.PreparedStatement pstat = conn.prepareStatement("insert into message (users,time,message, click) values (?,?,?,?)");
				pstat.setString(1, user_name + "&" + with.getEditor().getText());
				String time_now = find_time();
				pstat.setString(2, "&"+ 1 +time_now+ 1 + "&");
				pstat.setString(3, "**1" + user_name + "1**" + "&"+ 1 + message.getText() + 1 + "&");
				pstat.setInt(4, 1);
				pstat.executeUpdate();
				refresh(e);
			}	
		} catch (Exception ex) {}	
		message.setText("");
	}
	
	public void refresh(ActionEvent e) throws SQLException{
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/freemove", "root", "root");
		Statement rstat = conn.createStatement();
		ResultSet result = rstat.executeQuery("select * from message");
	
		while(result.next()){
			
			users = result.getString("users");
			
			if(users.contains(user_name) && users.contains(with.getEditor().getText())){
			
				list.getItems().clear();
				ResultSet myRs = null;
				java.sql.PreparedStatement myStmt = conn
						.prepareStatement("select * from message where users = ?");
				myStmt.setString(1, users);
				myRs = myStmt.executeQuery();
				while (myRs.next()) {
					
					for(int i = 1; i<=myRs.getInt("click"); i++){
				
						String message =myRs.getString("message");
						String time =myRs.getString("time");
						list.getItems().addAll("      " + message.substring((message.indexOf("**"+i))+3, message.indexOf(i+"**")) + "           " + time.substring((time.indexOf("&"+i))+2, time.indexOf(i+"&"))+ "\n"
								+ message.substring((message.indexOf("&"+i))+2, message.indexOf(i+"&")) + "\n" + "\n");
							
//						try{
//	
//							ResultSet myRs_second = null;
//							java.sql.PreparedStatement myStmt_second = conn
//									.prepareStatement("select * from message where users=?");
//							myStmt_second.setString(1, users);
//							myRs_second = myStmt_second.executeQuery();
//							
//							while (myRs_second.next()) {
//								
//								String message_second =myRs_second.getString("message");
//								
//								list.getItems().addAll("      " + myRs_second.getString("users") + "\n"
//										+ message_second.substring((message_second.indexOf("&"+i))+2, message_second.indexOf((i+"&"))) + "\n" + "\n");
//							}
//						}catch(Exception ex){}
					}
				}
			}
		}
		message.setText("");
	}
	
	public static String find_time(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String t = sdf.format(cal.getTime());
		return t;
	}
	
	public void back(ActionEvent e) throws IOException{
		Scene back = new Scene(FXMLLoader.load(getClass().getResource(way)));
		back.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
		Stage back_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		back_stage.setScene(back);
		back_stage.show();
	}
}
