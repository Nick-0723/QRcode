package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexp {
    public static void main(String[] args) {
        String benName = "350102197811282436";
        String paySeq = "xxx@scl@xxxxxx";

        // 按指定模式在字符串查找
        String pattern = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(benName);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
//            System.out.println("Found value: " + m.group(1) );
//            System.out.println("Found value: " + m.group(2) );
//            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }

    }
}
