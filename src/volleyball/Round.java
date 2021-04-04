package volleyball;

import javafx.scene.layout.Pane;

public class Round {
	
	public Court[] court = new Court[4];
	public CourtPos courtPos;
	
	public Round(Pane methodPane, Player player) {
				
		for(int i=0;i<4;i++) {
			court[i] = new Court(methodPane, player, Info.court_X[i/2], Info.court_Y[(i%2==0)? 0:1]);
		}
		courtPos = new CourtPos(methodPane, player);
		
	}
	
}
