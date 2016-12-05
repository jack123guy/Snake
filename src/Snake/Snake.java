package Snake;

import java.util.ArrayList;
import java.io.*;

public class Snake {
	
	
	public static void main(String[] args){
		System.out.println("Snake Start;");
	}
}

class MapCreate{
	int[][]map;
	
}

class Walk extends WalkRule{
	
}

class WalkRule{
	void firstColRule(){
		
	}
	
	void secColRule(){
		
	}
	
	void lastColRule(){
		
	}
}

class Track{
	int x,y;
}

class BreakPoint{
	int x,y;
}

class SumUp{
	int sum;
	ArrayList<Track> track = new ArrayList<Track>();
}