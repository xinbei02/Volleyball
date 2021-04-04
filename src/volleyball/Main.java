package volleyball;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static Pane mainPane = new Pane();
	public ObservableList<String> methodName = FXCollections.observableArrayList();
	public ObservableList<Method> method = FXCollections.observableArrayList();
	public static Pane methodPane = new Pane();
	
	@Override
	public void start(Stage mainStage) throws Exception {
		
		Scene mainScene = new Scene(mainPane, Info.scene_W, Info.scene_H);
		
		methodName.add("方案一");
		method.add(new Method());
		
		Label methodLabel = new Label("方案");
		methodLabel.setMinWidth(150);
		methodLabel.setLayoutX(0);
		methodLabel.setLayoutY(0);
		methodLabel.setAlignment(Pos.CENTER);
		methodLabel.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		methodLabel.setFocusTraversable(false);
		
		ComboBox<String> methodChoice = new ComboBox<String>();
		methodChoice.setItems(methodName);
		methodChoice.setLayoutX(5);
		methodChoice.setLayoutY(20);
		methodChoice.setPrefWidth(140);
		methodChoice.setStyle("-fx-text-align: center;");
		methodChoice.setFocusTraversable(false);
		methodChoice.getSelectionModel().selectFirst();
		methodChoice.getSelectionModel().selectedItemProperty().addListener((ov, o, n)->{
			int index = methodName.indexOf(n);
			for(int i=0;i<method.size();i++) {
				if(i!=index) method.get(i).methodPane.setVisible(false);
				else method.get(index).methodPane.setVisible(true);
			}
			methodPane.toFront();
		});
		
		TextField name = new TextField();
		name.setPrefWidth(140);
		name.setLayoutX(5);
		name.setLayoutY(50);
		name.setPromptText("方案名");
		name.setAlignment(Pos.CENTER);
		name.setFocusTraversable(false);
		
		Button add = new Button("新增");
		add.setMinWidth(140);
		add.setLayoutX(5);
		add.setLayoutY(80);
		add.setAlignment(Pos.CENTER);
		add.setFocusTraversable(false);
		add.setOnMouseClicked((e->{
			if(!name.getText().isEmpty()) {
				if(methodName.indexOf(name.getText())==-1) {
					method.get(methodChoice.getSelectionModel().getSelectedIndex()).methodPane.setVisible(false);
					methodName.add(name.getText());
					method.add(new Method());
					methodChoice.getSelectionModel().select(method.size()-1);
					methodPane.toFront();
				}
				name.setText("");
			}
		}));
		
		Button del = new Button("刪除");
		del.setMinWidth(140);
		del.setLayoutX(5);
		del.setLayoutY(110);
		del.setAlignment(Pos.CENTER);
		del.setFocusTraversable(false);
		del.setOnMouseClicked((e->{
			int index = methodChoice.getSelectionModel().getSelectedIndex();
			Main.mainPane.getChildren().remove(method.get(index).methodPane);
			methodName.remove(index);
			method.remove(index);
			if(method.size()==0) {
				methodName.add("方案一");
				method.add(new Method());
			}
			methodChoice.getSelectionModel().selectFirst();
			methodPane.toFront();
		}));
		
		Label helpStr = new Label("# 可點選不同的方案\n"
				+ "# 新增 : 新增方案(名為「方案名」)\n"
				+ "# 刪除 : 刪除當前方案\n");
		helpStr.setMinWidth(150);
		helpStr.setLayoutX(-10);
		helpStr.setLayoutY(-50);
		helpStr.setAlignment(Pos.CENTER);
		helpStr.setFocusTraversable(false);
		helpStr.setBackground(new Background(new BackgroundFill(Color.CORAL, null, null)));
		helpStr.setVisible(false);
		
		Label help = new Label("?");
		help.setPrefSize(15, 10);
		help.setLayoutX(135);
		help.setLayoutY(0);
		help.setAlignment(Pos.CENTER);
		help.setStyle("-fx-margin: 0px; -fx-font-size: 9; -fx-border-color: black;");
		help.setFocusTraversable(false);
		help.setOnMouseClicked((e->{
			if(helpStr.isVisible()) helpStr.setVisible(false);
			else helpStr.setVisible(true);
		}));
		
		methodPane.setLayoutX(820);
		methodPane.setLayoutY(200);
		methodPane.setPrefSize(150, 140);
		methodPane.setStyle("-fx-background-color: rgb(25, 125, 50, 0.7);");
		
		methodPane.getChildren().addAll(methodLabel, methodChoice, name, del, add, help, helpStr);
		mainPane.getChildren().add(methodPane);
		
		//Stage
		mainStage.setResizable(false);
		mainStage.setAlwaysOnTop(true);
		mainStage.setScene(mainScene);
		mainStage.setTitle("VolleyBall");
		mainStage.show();
		Main.methodPane.toFront();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
