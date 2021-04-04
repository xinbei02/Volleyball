package volleyball;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Method {
	
	public Pane methodPane = new Pane();
	public Button[] rBtn = new Button[6];
	public Round[] r = new Round[6];
	public int nowRound = 0;
	public Player player = new Player(methodPane);
	
	public Method() {
		
		for(int i=5;i>=0;i--) {
			r[i] = new Round(methodPane, player);
			setRound(i, i*25, 20);
		}
		rBtn[0].setBackground(Info.srbg);
		
		for(int i=0;i<6;i++) {
			player.labelName[i] = new SimpleStringProperty();
			player.labelName[i].setValue("");
			for(int j=0;j<6;j++) {
				for(int k=0;k<4;k++) {
					r[j].court[k].p[i].textProperty().bind(player.labelName[i]);
					r[j].courtPos.p[i].textProperty().bind(player.labelName[i]);
				}
			}
		}
		
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
				for(int k=0;k<4;k++) {
					r[j].court[k].p[i].setLayoutX(Info.px[(i+(6-j))%6]);
					r[j].court[k].p[i].setLayoutY(Info.py[(i+(6-j))%6]);
				}
				r[j].courtPos.p[i].setLayoutX(Info.pxPos[(i+(6-j))%6]);
				r[j].courtPos.p[i].setLayoutY(Info.pyPos[(i+(6-j))%6]+5);
			}
		}
		
		Label way_1 = new Label("發球");
		way_1.setMinWidth(Info.court_W);
		way_1.setLayoutX(Info.court_X[0]);
		way_1.setLayoutY(10);
		way_1.setAlignment(Pos.CENTER);
		way_1.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		way_1.setFocusTraversable(false);
		
		Label way_2 = new Label("接球");
		way_2.setMinWidth(Info.court_W);
		way_2.setLayoutX(Info.court_X[1]);
		way_2.setLayoutY(10);
		way_2.setAlignment(Pos.CENTER);
		way_2.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		way_2.setFocusTraversable(false);
		
		Label way_3 = new Label("位置");
		way_3.setMinWidth(Info.courtPos_W);
		way_3.setLayoutX(Info.courtPos_X);
		way_3.setLayoutY(10);
		way_3.setAlignment(Pos.CENTER);
		way_3.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		way_3.setFocusTraversable(false);
		
		player.resetLayout.setOnMouseClicked((e->{
			for(int i=0;i<6;i++) {
				for(int k=0;k<4;k++) {
					r[nowRound].court[k].p[i].setLayoutX(Info.px[(i+(6-nowRound))%6]);
					r[nowRound].court[k].p[i].setLayoutY(Info.py[(i+(6-nowRound))%6]);
				}
			}
		}));
		
		methodPane.getChildren().addAll(way_1, way_2, way_3);
		
		Main.mainPane.getChildren().addAll(methodPane);
		
	}
	
	public void setRound(int i, double x, double y) {
		rBtn[i] = new Button(String.valueOf(i+1));
		rBtn[i].setMinWidth(25);
		rBtn[i].setLayoutX(x);
		rBtn[i].setLayoutY(y);
		rBtn[i].setAlignment(Pos.CENTER);
		rBtn[i].setFocusTraversable(false);
		rBtn[i].setBackground(Info.rbg);
		rBtn[i].setOnMouseClicked((e->{
			for(int j=0;j<6;j++) {
				if(j==i) {
					for(int k=0;k<4;k++)
						r[i].court[k].pane.toFront();
					r[i].courtPos.pane.toFront();
					rBtn[i].setBackground(Info.srbg);
					nowRound = i;
				}
				else rBtn[j].setBackground(Info.rbg);
			}
		}));
		player.roundPane.getChildren().add(rBtn[i]);
	}
	
}
