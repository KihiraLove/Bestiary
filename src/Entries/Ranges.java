package Entries;

public class Ranges {
	record Range(int lower, int upper) {}
	private static Ranges instance = null;
	public static synchronized Ranges getInstance(){
		if(instance == null){
			instance = new Ranges();
		}
		return instance;
	}
	private final Range[] ranges;
	private Ranges() {
		this.ranges = new Range[]{ new Range(1, 10),
									new Range(11, 20),
									new Range(21, 30),
									new Range(31, 40),
									new Range(41, 50),
									new Range(51, 60),
									new Range(61, 70),
									new Range(71, 80),
									new Range(81, 90),
									new Range(91, 100),
									new Range(101, 110),
									new Range(111, 120),
									new Range(121, 130),
									new Range(131, 140),
									new Range(141, 150),
									new Range(151, 160),
									new Range(161, 170),
									new Range(171, 180),
									new Range(181, 190),
									new Range(191, 200),
									new Range(201, 400),
									new Range(401, 2000)};
	}

	public int GetLower(int index){
		return ranges[index].lower();
	}

	public int GetUpper(int index){
		return ranges[index].upper();
	}

	public int GetLengthOfRanges(){
		return  ranges.length;
	}
}