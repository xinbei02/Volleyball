package volleyball;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Court {
	
	public Player player;
	
	public Pane pane = new Pane();
	public Group g[] = new Group[6];
	public Label p[] = new Label[6];
	
	public Court(Pane methodPane, Player player, double px, double py) {
		
		this.player = player;
		
		Line net = new Line(Info.net_sX, Info.net_Y, Info.net_eX, Info.net_Y);
		net.setStroke(Color.BLACK);
		net.setStrokeWidth(2);
		
		Rectangle floor = new Rectangle(Info.floor_W, Info.floor_H);
		floor.setLayoutX(Info.floor_X);
		floor.setLayoutY(Info.floor_Y);
		floor.setFill(Color.WHITE);
		floor.setStroke(Color.BLACK);
		floor.setStrokeWidth(2);
		
		Line m3 = new Line(Info.m3_sX, Info.m3_Y, Info.m3_eX, Info.m3_Y);
		m3.setStroke(Color.BLACK);
		m3.setStrokeWidth(2);
		
		pane.setLayoutX(px);
		pane.setLayoutY(py);
		pane.setMinSize(Info.court_W, Info.court_H);
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
		p[i].setPrefSize(Info.pr*2, Info.pr*2);
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
		p[i].addEventHandler(MouseEvent.MOUSE_DRAGGED, (e->{
			if(!player.playerChoose.getValue() && !player.playerCancel.getValue()) {
				double tmpX = (e.getSceneX()-x_)<0? 0: (e.getSceneX()-x_)>Info.court_W-Info.pr*2? Info.court_W-Info.pr*2: (e.getSceneX()-x_);
				double tmpY = (e.getSceneY()-y_)<0? 0: (e.getSceneY()-y_)>Info.court_H-Info.pr*2? Info.court_H-Info.pr*2: (e.getSceneY()-y_);
				p[i].setLayoutX(tmpX);
				p[i].setLayoutY(tmpY);
			}
		}));
		pane.getChildren().add(p[i]);
	}
	
}
