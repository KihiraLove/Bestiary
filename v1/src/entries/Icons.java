package entries;

public final class Icons {
	private Icons(){}
	private static final String MEMBERS_ALT_TEXT = "Members";
	private static final String F2P_ALT_TEXT = "Free-to-play";
	private static final String MEMBERS_LINK = "https://oldschool.runescape.wiki/images/Member_icon.png?1de0c";
	private static final String F2P_LINK = "https://oldschool.runescape.wiki/images/Free-to-play_icon.png?628ce";
	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;
	private static final int DATA_FILE_WIDTH = 16;
	private static final int DATA_FILE_HEIGHT = 16;
	public static String membersAltText() { return MEMBERS_ALT_TEXT; }
	public static String f2pAltText() { return F2P_ALT_TEXT; }
	public static String membersLink() { return MEMBERS_LINK; }
	public static String f2pLink() { return F2P_LINK; }
	public static int width() { return WIDTH; }
	public static int height() { return HEIGHT; }
	public static int dataFileWidth() { return DATA_FILE_WIDTH; }
	public static int dataFileHeight() { return DATA_FILE_HEIGHT; }
}
