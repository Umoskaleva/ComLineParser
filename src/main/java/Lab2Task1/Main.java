package Lab2Task1;

public class Main {
    public static void main(String[] args) {
        String outfileName = "-wOutfileName";
        String infileName = "-rInfileName";

        ComLineParser comLineParser = new ComLineParser(new String[]{"?","r","w"});

        // я не нашла в IDEA как ввести командную строку в аргументы, поэтому сделала так...
        args = new String[]{outfileName, infileName};
        comLineParser.Parse(args);

    }
}