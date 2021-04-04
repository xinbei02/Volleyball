package volleyball;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CourtPos {
	
public Player player;
	
	public Pane pane = new Pane();
	public Label p[] = new Label[6];
	
	public CourtPos(Pane methodPane, Player player) {
		
		this.player = player;
		
		Line net = new Line(Info.netPos_sX, Info.netPos_Y, Info.netPos_eX, Info.netPos_Y);
		net.setStroke(Color.BLACK);
		net.setStrokeWidth(2);
		
		Rectangle floor = new Rectangle(Info.floorPos_W, Info.floorPos_H);
		floor.setLayoutX(Info.floorPos_X);
		floor.setLayoutY(Info.floorPos_Y);
		floor.setFill(Color.WHITE);
		floor.setStroke(Color.BLACK);
		floor.setStrokeWidth(2);
		
		Line m3 = new Line(Info.m3Pos_sX, Info.m3Pos_Y, Info.m3Pos_eX, Info.m3Pos_Y);
		m3.setStroke(Color.BLACK);
		m3.setStrokeWidth(2);
		
		pane.setLayoutX(Info.courtPos_X);
		pane.setLayoutY(Info.courtPos_Y);
		pane.setMinSize(Info.courtPos_W, Info.courtPos_H);
		pane.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		pane.getChildren().addAll(floor, net, m3);
		
		for(int i=0;i<6;i++) {
			setPlayer(i);
		}
		
		methodPane.getChildren().addAll(pane);
	}
	
	double x_= 0, y_=0;
	public void setPlayer(int i) {
		p[i] = new Label();
		p[i].setStyle("-fx-background-color: darkgray; -fx-background-radius: 50; -fx-border-radius: 30; -fx-font-size: 15;");
		p[i].setPrefSize(Info.prPos*2, Info.prPos*2);
		p[i].setAlignment(Pos.CENTER);
		p[i].setTextFill(Color.BLACK);
		p[i].addEventHandler(MouseEvent.MOUSE_PRESSED, (e->{
			if(player.playerChoose.getValue()) {
				if(player.playerSelect!=-1) {
					if(player.playerChosen.get(player.playerSelect)) {
						for(int j=0;j<6;j++) {
							if(player.labelName[j].getValue().equals(player.playerCodename.get(player.playerSelect))) {
								player.labelName[j].setValue("");
								break;
							}
						}
					}
					int tmp = player.playerCodename.indexOf(player.labelName[i].getValue());
					if(tmp!=-1) {
						player.playerChosen.set(tmp, false);
						player.playerLabel.get(tmp).setBackground(Info.unchoose);
					}
					player.labelName[i].setValue(player.playerCodename.get(player.playerSelect));
					player.playerChosen.set(player.playerSelect, true);
					player.playerLabel.get(player.playerSelect).setBackground(Info.chosen);
				}
			}
			else if(player.playerCancel.getValue()) {
				int tmp = player.playerCodename.indexOf(p[i].getText());
				if(tmp!=-1) {
					player.playerChosen.set(tmp, false);
					player.playerLabel.get(tmp).setBackground(Info.unchoose);
					player.labelName[i].setValue("");
				}
			}
			else {
				x_ = e.getSceneX()-p[i].getLayoutX();
				y_ = e.getSceneY()-p[i].getLayoutY();
			}
		}));
		pane.getChildren().add(p[i]);
	}
	
}
