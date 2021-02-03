package Main;



import java.sql.Date;
import movie.show;

public class Ticket{
	show myshow;
    Date nowTime;
    int count;
    int seatingList[];
    
    public Ticket() {
    	nowTime = new Date(0);
    }
    
   public void Ticketing(show myshow, int count, int seatingList[]) {
	   this.myshow = myshow;
	   this.count = count;
	   this.seatingList = seatingList;
	   for(int i = 0 ; i<seatingList.length;i++) {
		   myshow.addR(seatingList[i]);
	   }
   }

}
