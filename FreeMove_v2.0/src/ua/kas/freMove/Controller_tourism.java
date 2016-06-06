package ua.kas.freMove;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller_tourism implements Initializable{

	@FXML
	ImageView imv_pic;
	@FXML
	ImageView imv_map;
	@FXML
	Button like_list;
	@FXML
	Label l_name;
	@FXML
	Label l_web;
	@FXML
	Label l_address;
	@FXML
	Label l_number;
	@FXML
	Label l_rating;
	@FXML
	ListView<String> listView;
	@FXML
	ListView<String> comments;
	@FXML
	Button photo_list;
	static String message = "",user_name="not_enter",user_password="not_enter",user_id="not_enter";
	static String type;
	String[] words;
	int likes;
	@FXML
	Button b_close,btn_message,BACK;
	String likes_user;

	public void chat(ActionEvent e) throws IOException{
		Controller_chat.user_name=user_name;
		Controller_chat.way="FXML/Menu.fxml";
//		scene = new Scene(FXMLLoader.load(getClass().getResource("FXML/Chat.fxml")));
//		scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
//		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//		stage.setScene(scene);
//		stage.show();
	}
	
	public void back(ActionEvent e) throws IOException{
//		Scene back = new Scene(FXMLLoader.load(getClass().getResource(way)));
//		back.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
//		Stage back_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//		back_stage.setScene(back);
//		back_stage.show();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		try {
//			Image imageDecline = new Image(getClass().getResourceAsStream("../img/photo.png"));
//			photo_list.setGraphic(new ImageView(imageDecline));

			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/freemove", "root", "root");
			ResultSet myRs = null;
			java.sql.PreparedStatement myStmt;
			myStmt = myConn.prepareStatement("select * from must_see where type =?");
			myStmt.setString(1, type);
			myRs = myStmt.executeQuery();
			listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			while (myRs.next()) {
				listView.getItems().addAll(myRs.getString("name"));

				// System.out.print("string input > ");
				// String line = new Scanner(System.in).nextLine();
				// words = line.split("\\s+");
				// System.out.println("Unsorted array: " +
				// Arrays.toString(words));
				// Arrays.sort(words);
				// System.out.println("Sorted array: " +
				// Arrays.toString(words));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void like(ActionEvent e) throws SQLException {

		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/freemove", "root", "root");
		ResultSet myRs = null;

		java.sql.PreparedStatement myStmt;
		myStmt = myConn.prepareStatement("select * from must_see where name =?");
		myStmt.setString(1, message);
		myRs = myStmt.executeQuery();

		while (myRs.next()) {
			likes = myRs.getInt("rating");
			likes_user = myRs.getString("likes_user");
		}

		if(likes_user.contains(Integer.parseInt(user_id)+", ") != true){
			
			likes++;
			
			java.sql.PreparedStatement myStmtUPD = myConn.prepareStatement("update must_see set rating=? where name=?");
			myStmtUPD.setInt(1, likes);
			myStmtUPD.setString(2, message);
			myStmtUPD.executeUpdate();
			
			java.sql.PreparedStatement myStmtUPD_userLIKES = myConn.prepareStatement("update must_see set likes_user=? where name=?");
			myStmtUPD_userLIKES.setString(1, likes_user + Integer.parseInt(user_id)+ ", ");
			myStmtUPD_userLIKES.setString(2, message);
			myStmtUPD_userLIKES.executeUpdate();
	
			ResultSet myRs2 = null;
			java.sql.PreparedStatement myStmt2;
			myStmt2 = myConn.prepareStatement("select * from must_see where name =?");
			myStmt2.setString(1, message);
			myRs2 = myStmt2.executeQuery();
	
			while (myRs2.next()) {
				like_list.setText(myRs2.getString("rating"));
			}
		}
		else{
			
			likes--;
			
			java.sql.PreparedStatement myStmtUPD = myConn.prepareStatement("update must_see set rating=? where name=?");
			myStmtUPD.setInt(1, likes);
			myStmtUPD.setString(2, message);
			myStmtUPD.executeUpdate();
			
			java.sql.PreparedStatement myStmtUPD_userLIKES = myConn.prepareStatement("update must_see set likes_user=? where name=?");
			String dellID = likes_user.replace(Integer.parseInt(user_id) +", ", "");
			myStmtUPD_userLIKES.setString(1, dellID);
			myStmtUPD_userLIKES.setString(2, message);
			myStmtUPD_userLIKES.executeUpdate();
			
			ResultSet myRs2 = null;
			java.sql.PreparedStatement myStmt2;
			myStmt2 = myConn.prepareStatement("select * from must_see where name =?");
			myStmt2.setString(1, message);
			myRs2 = myStmt2.executeQuery();
	
			while (myRs2.next()) {
				like_list.setText(myRs2.getString("rating"));
			}
		}
	}

	public void work() throws SQLException, IOException {

		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/freemove", "root", "root");
		ResultSet myRs = null;
		ObservableList<String> movies;
		movies = listView.getSelectionModel().getSelectedItems();

		for (String m : movies) {
			message = m;

			java.sql.PreparedStatement myStmt;

			myStmt = myConn.prepareStatement("select * from must_see where name =?");
			myStmt.setString(1, message);
			myRs = myStmt.executeQuery();

			Blob img_pic;
			Blob img_map;
			byte[] imgData_pic = null;
			byte[] imgData_map = null;

			while (myRs.next()) {
				l_name.setText(myRs.getString("name"));
				l_web.setText(myRs.getString("web"));
				l_address.setText(myRs.getString("address"));
				l_number.setText(myRs.getString("number"));

				if ("not_enter".equals(user_name) && "not_enter".equals(user_password)) {
					like_list.setText(myRs.getString("rating"));
					System.out.println("You are not login");
					like_list.setDisable(true);
				}

				else {
					like_list.setText(myRs.getString("rating"));
				}

				img_pic = myRs.getBlob("pic");
				img_map = myRs.getBlob("map");
				imgData_pic = img_pic.getBytes(1, (int) img_pic.length());
				imgData_map = img_map.getBytes(1, (int) img_map.length());
				BufferedImage imag_pic = ImageIO.read(new ByteArrayInputStream(imgData_pic));
				BufferedImage imag_map = ImageIO.read(new ByteArrayInputStream(imgData_map));
				imv_pic.setImage(SwingFXUtils.toFXImage(imag_pic, null));
				imv_map.setImage(SwingFXUtils.toFXImage(imag_map, null));
				// message = "";
			}

			try {
				System.out.println(message);
				java.sql.PreparedStatement myStmtCom;
				ResultSet myRsCom = null;
				myStmtCom = myConn.prepareStatement("select * from comments where name =?");
				myStmtCom.setString(1, message);
				myRsCom = myStmtCom.executeQuery();

				while (myRsCom.next()) {
					comments.getItems().addAll("      " + myRsCom.getString("user_name") + "\n"
							+ myRsCom.getString("comment") + "\n" + "\n");
				}
			} catch (Exception ex) {
			}
		}
	}

	public void photo(ActionEvent e) throws IOException {
		Scene photo_scene = new Scene(FXMLLoader.load(getClass().getResource("Photo.fxml")));
		photo_scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		app_stage.setScene(photo_scene);
		app_stage.show();
	}
}
