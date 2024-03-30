package Lab2Task1;

public class ComLineParser {
    private String[] keys;
    private String[] delimeters;

    public enum SwitchStatus {NoError, Error, ShowUsage}

    ;

    public ComLineParser(String[] keys) {
        this(keys, new String[]{"/", "-"});
    }

    public ComLineParser(String[] keys, String[] delimeters) {
        this.keys = keys;
        this.delimeters = delimeters;
    }

    public void OnUsage(String errorKey) {
        if (errorKey != null) {
            System.out.println(errorKey);
        }
        System.out.println("формат ком.строки: имяПрограммы [-r<input-fileName>] [-w<output-fileName>]");
        System.out.println("   -?  показать Help файл");
        System.out.println("   -r  задать имя входного файла");
        System.out.println("   -w  выполнить вывод в указанный файл");
    }

    public SwitchStatus OnSwitch(String key, String keyValue) { // имя найденного ключа в командной строке и его значение соответственно
        System.out.println(key + " " + keyValue);
        return SwitchStatus.NoError;
    }

    public boolean Parse(String[] args) {
        SwitchStatus ss = SwitchStatus.NoError;
        int argNum;
        for (argNum = 0; (ss == SwitchStatus.NoError) && (argNum < args.length); argNum++) {
//            if (args[argNum] == "/" || args[argNum] == "-") {
            boolean isDelimeter = false;
            for (int n = 0; !isDelimeter && (n < delimeters.length); n++) {
                isDelimeter = args[argNum].regionMatches(0, delimeters[n], 0, 1); // Метод regionMatches() сравнивает первый символ строки args[argNum] с первым символом строки delimeters[n]
            }
            if (isDelimeter) {
                boolean isKey = false;
                int i;
                for (i = 0; !isKey && (i < keys.length); i++) {
                    isKey = args[argNum].regionMatches(1, keys[i], 0, 1);
                    if (isKey) break;
                }
                if (!isKey) {
                    ss = SwitchStatus.Error;
                    break;
                } else {
                    ss = OnSwitch(keys[i].toLowerCase(),
                            args[argNum].substring(1 + keys[i].length()));
                }
            } else {
                ss = SwitchStatus.Error;
                break;
            }
//            }
        }
        if (ss == SwitchStatus.ShowUsage) OnUsage(null);
        if (ss == SwitchStatus.Error) OnUsage((argNum == args.length) ? null : args[argNum]);
        return ss == SwitchStatus.NoError;
    }
}
