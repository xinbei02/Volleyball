package volleyball;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Player {
	
	public Pane playerPane = new Pane();
	public GridPane player = new GridPane();
	public StringProperty[] labelName = new SimpleStringProperty[6];
	public ObservableList<String> playerName = FXCollections.observableArrayList();
	public ObservableList<String> playerCodename = FXCollections.observableArrayList();
	public ObservableList<Label> playerLabel = FXCollections.observableArrayList(); 
	public ObservableList<Boolean> playerChosen = FXCollections.observableArrayList();
	public SimpleBooleanProperty playerChoose = new SimpleBooleanProperty();
	public int playerSelect = -1;
	public SimpleBooleanProperty playerCancel = new SimpleBooleanProperty();
	
	public Pane resetPane = new Pane();
	public Button resetLayout = new Button("����");
	
	public Pane roundPane = new Pane();
	
	
	public Player(Pane methodPane) {
		
		Label playerhelpStr = new Label("# �k�����Ǧ���I���W�����y���A�H�N�����\n"
				+ "# ��uChoose�v�M�uDelete�v����ܬ��զ�ɡA�i���ʥk�����y��\n"
				+ "# �s�W : �s�W�y��(Name ( �N�� ))\n"
				+ "# Choose :\n"
				+ "\t����ܬ�����ɡA�i�I��U�誺�y���A�åi�N��t�m��k�����y����\n"
				+ "# Delete : \n"
				+ "\t����ܬ�����ɡA\n"
				+ "\t\t- �I��U�誺�y���A�i�N�y���q�y���W�椤�R��\n"
				+ "\t\t- �I��k�����W���y���A�i�N�y���q�W���y��������\n");
		playerhelpStr.setMinWidth(150);
		playerhelpStr.setLayoutX(160);
		playerhelpStr.setLayoutY(0);
		playerhelpStr.setAlignment(Pos.CENTER);
		playerhelpStr.setFocusTraversable(false);
		playerhelpStr.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
		playerhelpStr.setVisible(false);
		
		Label playerhelp = new Label("?");
		playerhelp.setPrefSize(15, 10);
		playerhelp.setLayoutX(135);
		playerhelp.setLayoutY(0);
		playerhelp.setAlignment(Pos.CENTER);
		playerhelp.setStyle("-fx-margin: 0px; -fx-font-size: 9; -fx-border-color: black;");
		playerhelp.setFocusTraversable(false);
		playerhelp.setOnMouseClicked((e->{
			if(playerhelpStr.isVisible()) playerhelpStr.setVisible(false);
			else playerhelpStr.setVisible(true);
			playerPane.toFront();
		}));
		
		Label addPlayer = new Label("�y��");
		addPlayer.setMinWidth(150);
		addPlayer.setLayoutX(0);
		addPlayer.setLayoutY(0);
		addPlayer.setAlignment(Pos.CENTER);
		addPlayer.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		
		TextField name = new TextField();
		name.setPrefWidth(100);
		name.setLayoutX(5);
		name.setLayoutY(20);
		name.setPromptText("�W�r");
		name.setAlignment(Pos.CENTER);
		name.setFocusTraversable(false);
		
		TextField codename = new TextField();
		codename.setPrefWidth(40);
		codename.setLayoutX(105);
		codename.setLayoutY(20);
		codename.setPromptText("�N��");
		codename.setAlignment(Pos.CENTER);
		codename.setFocusTraversable(false);
		
		Button add = new Button("�s�W");
		add.setMinWidth(140);
		add.setLayoutX(5);
		add.setLayoutY(45);
		add.setAlignment(Pos.CENTER);
		add.setFocusTraversable(false);
		add.setOnMouseClicked((e->{
			if(!name.getText().isEmpty() && !codename.getText().isEmpty()) {
				if(playerName.indexOf(name.getText())==-1 || playerCodename.indexOf(codename.getText())==-1) {
					addPlayer(name.getText(), codename.getText());
				}
				name.setText("");
				codename.setText("");
			}
		}));
		
		Label list = new Label("�y���W��");
		list.setMinWidth(150);
		list.setLayoutX(0);
		list.setLayoutY(75);
		list.setAlignment(Pos.CENTER);
		list.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		list.setFocusTraversable(false);
		
		Label cho = new Label("Choose");
		Label del = new Label("Delete");
		
		playerChoose.addListener(b->{
			if(playerChoose.getValue()) {
				cho.setStyle("-fx-background-color: red; -fx-border-color: black;");
				playerCancel.setValue(false);
			}
			else cho.setStyle("-fx-background-color: white; -fx-border-color: black;");
		});
		playerCancel.addListener(b->{
			if(playerCancel.getValue()) {
				del.setStyle("-fx-background-color: red; -fx-border-color: black;");
				playerChoose.setValue(false);
			}
			else del.setStyle("-fx-background-color: white; -fx-border-color: black;");
		});
		
		del.setMinWidth(70);
		del.setLayoutX(75);
		del.setLayoutY(95);
		del.setAlignment(Pos.CENTER);
		del.setFocusTraversable(false);
		del.setStyle("-fx-background-color: white; -fx-border-color: black;");
		del.setOnMouseClicked((e->{
			if(!playerCancel.getValue()) {
				playerCancel.setValue(true);
				if(playerSelect!=-1) {
					if(playerChosen.get(playerSelect)) playerLabel.get(playerSelect).setBackground(Info.chosen);
					else playerLabel.get(playerSelect).setBackground(Info.unchoose);
				}
				playerSelect = -1;
			}
			else playerCancel.setValue(false);
		}));
		
		cho.setMinWidth(70);
		cho.setLayoutX(5);
		cho.setLayoutY(95);
		cho.setAlignment(Pos.CENTER);
		cho.setFocusTraversable(false);
		cho.setStyle("-fx-background-color: white; -fx-border-color: black;");
		cho.setOnMouseClicked((e->{
			if(playerChoose.getValue()) {
				playerChoose.setValue(false);
				if(playerSelect!=-1) {
					if(playerChosen.get(playerSelect)) playerLabel.get(playerSelect).setBackground(Info.chosen);
					else playerLabel.get(playerSelect).setBackground(Info.unchoose);
				}
				playerSelect = -1;
			}
			else playerChoose.setValue(true);
		}));
		
		
		player.setHgap(0);
		player.setMinWidth(140);
		player.setLayoutX(5);
		player.setLayoutY(125);
		
		playerPane.setPrefSize(Info.playerPane_W, Info.playerPane_H);
		playerPane.setStyle("-fx-background-color: rgb(25, 125, 50, 0.7);");
		playerPane.getChildren().addAll(addPlayer, name, codename, add, list, del, cho, player, playerhelpStr, playerhelp);
		
		Label resethelpStr = new Label("# ���� : ���]��e���઺�y������\n"
				+ "# �y�� : ���]�Ҧ��y������m\n");
		resethelpStr.setMinWidth(150);
		resethelpStr.setLayoutX(-10);
		resethelpStr.setLayoutY(-35);
		resethelpStr.setAlignment(Pos.CENTER);
		resethelpStr.setFocusTraversable(false);
		resethelpStr.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
		resethelpStr.setVisible(false);
		
		Label resethelp = new Label("?");
		resethelp.setPrefSize(15, 10);
		resethelp.setLayoutX(135);
		resethelp.setLayoutY(0);
		resethelp.setAlignment(Pos.CENTER);
		resethelp.setStyle("-fx-margin: 0px; -fx-font-size: 9; -fx-border-color: black;");
		resethelp.setFocusTraversable(false);
		resethelp.setOnMouseClicked((e->{
			if(resethelpStr.isVisible()) resethelpStr.setVisible(false);
			else {
				resethelpStr.setVisible(true);
				resethelpStr.toFront();
			}
		}));
		
		Label resetLabel = new Label("���]");
		resetLabel.setMinWidth(150);
		resetLabel.setLayoutX(0);
		resetLabel.setLayoutY(0);
		resetLabel.setAlignment(Pos.CENTER);
		resetLabel.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		resetLabel.setFocusTraversable(false);
		
		resetLayout.setMinWidth(140);
		resetLayout.setLayoutX(5);
		resetLayout.setLayoutY(20);
		resetLayout.setAlignment(Pos.CENTER);
		resetLayout.setFocusTraversable(false);
		
		Button resetPlayer = new Button("�y��");
		resetPlayer.setMinWidth(140);
		resetPlayer.setLayoutX(5);
		resetPlayer.setLayoutY(50);
		resetPlayer.setAlignment(Pos.CENTER);
		resetPlayer.setFocusTraversable(false);
		resetPlayer.setOnMouseClicked((e->{
			for(int i=0;i<6;i++) {
				if(!labelName[i].getValue().isEmpty()) {
					int tmp = playerCodename.indexOf(labelName[i].getValue());
					playerChosen.set(tmp, false);
					playerLabel.get(tmp).setBackground(Info.unchoose);
					labelName[i].setValue(null);
				}
			}
		}));
		
		resetPane.setLayoutX(820);
		resetPane.setLayoutY(500);
		resetPane.setPrefSize(150, 80);
		resetPane.setStyle("-fx-background-color: rgb(25, 125, 50, 0.7);");
		resetPane.getChildren().addAll(resetLabel, resetLayout, resetPlayer, resethelpStr, resethelp);
		
		
		Label roundhelpStr = new Label("# �i�I�藍�P�������m\n");
		roundhelpStr.setLayoutX(10);
		roundhelpStr.setLayoutY(-20);
		roundhelpStr.setAlignment(Pos.CENTER);
		roundhelpStr.setFocusTraversable(false);
		roundhelpStr.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
		roundhelpStr.setVisible(false);
		
		Label roundhelp = new Label("?");
		roundhelp.setPrefSize(15, 10);
		roundhelp.setLayoutX(135);
		roundhelp.setLayoutY(0);
		roundhelp.setAlignment(Pos.CENTER);
		roundhelp.setStyle("-fx-margin: 0px; -fx-font-size: 9; -fx-border-color: black;");
		roundhelp.setFocusTraversable(false);
		roundhelp.setOnMouseClicked((e->{
			if(roundhelpStr.isVisible()) roundhelpStr.setVisible(false);
			else roundhelpStr.setVisible(true);
		}));
		
		Label roundLabel = new Label("����");
		roundLabel.setMinWidth(150);
		roundLabel.setLayoutX(0);
		roundLabel.setLayoutY(0);
		roundLabel.setAlignment(Pos.CENTER);
		roundLabel.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		
		roundPane.setLayoutX(820);
		roundPane.setLayoutY(390);
		roundPane.setPrefSize(150, 50);
		roundPane.setStyle("-fx-background-color: rgb(25, 125, 50, 0.7);");
		roundPane.getChildren().addAll(roundLabel, roundhelpStr, roundhelp);
		
		methodPane.getChildren().addAll(playerPane, resetPane, roundPane);
		Main.methodPane.toFront();
	}
	
	public Label setLabel(String name) {
		Label label = new Label(name);
		label.setMinWidth(140);
		label.setAlignment(Pos.CENTER);
		label.setFocusTraversable(false);
		label.setOnMouseClicked((e->{
			if(playerChoose.getValue()) {
				if(playerSelect!=-1) {
					if(playerChosen.get(playerSelect)) playerLabel.get(playerSelect).setBackground(Info.chosen);
					else playerLabel.get(playerSelect).setBackground(Info.unchoose);
				}
				playerSelect = playerLabel.indexOf(label);
				label.setBackground(Info.choose);
			}
			else if(playerCancel.getValue()) {
				playerSelect = playerLabel.indexOf(label);
				deletePlayer();
				playerSelect = -1;
			}
		}));
		player.add(label, 0, player.getChildren().size());
		return label;
	}
	
	public void deletePlayer() {
		if(playerChosen.get(playerSelect)) {
			for(int i=0;i<6;i++) {
				if(labelName[i].getValue().equals(playerCodename.get(playerSelect))) {
					labelName[i].setValue("");
					break;
				}
			}
		}
		for(int i=playerSelect;i<playerLabel.size();i++)
			player.getChildren().remove(playerLabel.get(i));
		playerLabel.remove(playerSelect);
		playerName.remove(playerSelect);
		playerChosen.remove(playerSelect);
		for(int i=playerSelect;i<playerLabel.size();i++)
			player.add(playerLabel.get(i), 0, player.getChildren().size());
	}
	
	public void addPlayer(String name, String codename) {
		playerLabel.add(setLabel(name + " ( " + codename + " )"));
		playerName.add(name);
		playerCodename.add(codename);
		playerChosen.add(false);
	}
	
}
