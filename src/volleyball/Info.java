package volleyball;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class Info {
	
	//scene
	public static double scene_W = 1000;
	public static double scene_H = 620;
	
	//player pane
	public static double playerPane_W = 150;
	public static double playerPane_H = scene_H;
	public static double playerLabel_W = 100;
	
	public static Background unchoose = new Background(new BackgroundFill(null, null, null));
	public static Background choose = new Background(new BackgroundFill(Color.PALETURQUOISE, null, null));
	public static Background chosen = new Background(new BackgroundFill(Color.DARKGRAY, null, null));
	
	public static Background rbg = new Background(new BackgroundFill(Color.AQUAMARINE, null, null));
	public static Background srbg = new Background(new BackgroundFill(Color.DARKCYAN, null, null));
		
	//floor
	public static double floor_W = 220;
	public static double floor_H = floor_W;
	
	public static double floor_X = floor_W/6;
	public static double floor_Y = floor_H/20;
	
	public static double net_w = floor_X/5;
	public static double net_sX = floor_X - net_w;
	public static double net_eX = floor_X + floor_W + net_w;
	public static double net_Y = floor_Y;

	public static double m3_sX = floor_X;
	public static double m3_eX = floor_X + floor_W;
	public static double m3_Y = floor_Y + floor_H/3;
	
	//court
	public static double court_W = floor_W + floor_W/3;
	public static double court_H = floor_H + floor_H/4;
	
	public static double[] court_X = {180, 500};
	public static double[] court_Y = {30, 320};
	
	//player
	public static double pr = floor_W/15; //20
	public static double prx = floor_W/6;
	public static double pry = floor_H/6;
	public static double[] px = {floor_X+prx*5-pr, floor_X+prx*5-pr, floor_X+prx*3-pr, floor_X+prx-pr, floor_X+prx-pr, floor_X+prx*3-pr};
	public static double[] py = {pry*5-pr, pry*3-pr, pry*3-pr, pry*3-pr, +pry*5-pr, pry*5-pr};
	
	
	
	
	

	//floor
	public static double floorPos_W = floor_W/2;
	public static double floorPos_H = floorPos_W;
	
	public static double floorPos_X = floorPos_W/6;
	public static double floorPos_Y = floorPos_H/20;
	
	public static double netPos_w = floorPos_X/5;
	public static double netPos_sX = floorPos_X - netPos_w;
	public static double netPos_eX = floorPos_X + floorPos_W + net_w;
	public static double netPos_Y = floorPos_Y;

	public static double m3Pos_sX = floorPos_X;
	public static double m3Pos_eX = floorPos_X + floorPos_W;
	public static double m3Pos_Y = floorPos_Y + floorPos_H/3;
	
	public static double courtPos_W = floorPos_W + floorPos_W/3;
	public static double courtPos_H = floorPos_H + floorPos_H/4;
	
	public static double courtPos_X = 820;
	public static double courtPos_Y = 30;
	
	//player
	public static double prPos = floor_W/15; //20
	public static double prxPos = floorPos_W/6;
	public static double pryPos = floorPos_H/6;
	public static double[] pxPos = {floorPos_X+prxPos*5-prPos, floorPos_X+prxPos*5-prPos, floorPos_X+prxPos*3-prPos, floorPos_X+prxPos-prPos, floorPos_X+prxPos-prPos, floorPos_X+prxPos*3-prPos};
	public static double[] pyPos = {pryPos*5-prPos, pryPos*3-prPos, pryPos*3-prPos, pryPos*3-prPos, +pryPos*5-prPos, pryPos*5-prPos};
	
	
	
	
	
}
