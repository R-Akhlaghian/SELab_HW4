# گزارش آزمایش پروفایلینگ

## فرآیند پروفایلینگ `JavaCup`
ما از ابزار YourKit برای پروفایل کردن کلاس `JavaCup` در پروژه‌ی `ProfilingTest` استفاده کردیم. این پروفایلر به برنامه در حال اجرا متصل شد و ما عملیات مختلفی را انجام دادیم تا داده‌های استفاده از منابع را جمع‌آوری کنیم.

### شناخت تابع با مصرف بالای منابع
نتایج پروفایلینگ نشان داد که تابع `temp()` در کلاس `JavaCup` بیشترین منابع را مصرف می‌کند.

### بهینه‌سازی
برای بهبود عملکرد تابع `temp()`، تغییرات زیر را اعمال کردیم:
#### پیاده‌سازی اصلی:
```
public static void temp() {
    ArrayList a = new ArrayList();
    for (int i = 0; i < 10000; i++) {
        for (int j = 0; j < 20000; j++) {
            a.add(i + j);
        }
    }
}
```
#### پیاده‌سازی بهینه‌شده:
```
public static void temp() {
    int totalSize = 10000 * 20000;
    int[] a = new int[totalSize];
    int index = 0;
    for (int i = 0; i < 10000; i++) {
        for (int j = 0; j < 20000; j++) {
            a[index++] = i + j;
        }
    }
}

```
بهینه‌سازی‌ها با هدف کاهش سربار تغییر اندازه دینامیک آرایه و کاهش عملیات غیرضروری انجام شدند.
### نتایج بهینه‌سازی
پس از بهینه‌سازی، روش `temp()` دیگر در نتایج پروفایلینگ YourKit نشان داده نمی‌شود. این نشان می‌دهد که این روش به قدری بهینه‌سازی شده که دیگر مصرف‌کننده‌ی قابل‌توجهی از منابع CPU یا حافظه نیست.
#### پیاده‌سازی اصلی:
<img width="1076" alt="image" src="https://github.com/user-attachments/assets/540f5377-4044-4b4c-8a71-e560bd1ca610">
<img width="1075" alt="image" src="https://github.com/user-attachments/assets/0f1bafa8-2a05-46fc-bfd8-53b03a00d94c">
<img width="1079" alt="image" src="https://github.com/user-attachments/assets/fe8dc5ba-450b-4afa-a23c-e5f820b44ed1">
<img width="1076" alt="image" src="https://github.com/user-attachments/assets/f06eb1b6-9198-4043-88c9-27a4fd70bf0d">

#### پیاده‌سازی بهینه‌شده:
<img width="1076" alt="image" src="https://github.com/user-attachments/assets/cad60282-2760-4924-985e-bb528d0bb1dc">
<img width="1073" alt="image" src="https://github.com/user-attachments/assets/9cbe38ad-25f6-42a8-8718-6fb21bfc22ed">
<img width="1072" alt="image" src="https://github.com/user-attachments/assets/95ef92c9-d676-47bc-a7a8-1c0fc1c02ec6">
<img width="1078" alt="image" src="https://github.com/user-attachments/assets/1d47f6e1-9b92-4c48-b226-6a4bf719327e">


## فرآیند پروفایلینگ `StringManipulation`
### بررسی کد اصلی
کد اصلی عملیات زیر را انجام می‌دهد:

- تولید تعداد مشخصی رشته‌های تصادفی.
- الحاق رشته‌های تولید شده.
- معکوس کردن رشته الحاق شده.
- جستجوی زیررشته‌ای خاص در رشته الحاق شده.
```
package profiling;

import java.util.Random;
import java.util.Scanner;

public class StringManipulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of strings to generate: ");
        int numStrings = scanner.nextInt();
        System.out.println("Enter the length of each string: ");
        int length = scanner.nextInt();

        String[] strings = generateRandomStrings(numStrings, length);

        long startTime, endTime;

        startTime = System.currentTimeMillis();
        String concatenatedString = concatenateStrings(strings);
        endTime = System.currentTimeMillis();
        System.out.println("String concatenation completed in " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        String reversedString = reverseString(concatenatedString);
        endTime = System.currentTimeMillis();
        System.out.println("String reversal completed in " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        boolean found = searchString(concatenatedString, "test");
        endTime = System.currentTimeMillis();
        System.out.println("String search completed in " + (endTime - startTime) + " ms");
        System.out.println("Search result: " + (found ? "Found" : "Not Found"));
    }

    private static String[] generateRandomStrings(int numStrings, int length) {
        Random random = new Random();
        String[] strings = new String[numStrings];
        for (int i = 0; i < numStrings; i++) {
            StringBuilder sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                sb.append((char) ('a' + random.nextInt(26)));
            }
            strings[i] = sb.toString();
        }
        return strings;
    }

    private static String concatenateStrings(String[] strings) {
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private static boolean searchString(String str, String substr) {
        return str.contains(substr);
    }
}
```
### نتایج پروفایلینگ کد اصلی
- **استفاده از CPU:** مصرف بالای CPU عمدتاً در main، به‌ویژه در زمان الحاق و معکوس کردن رشته‌ها مشاهده شد.
- **استفاده از حافظه:** مصرف حافظه پایدار و در محدوده قابل قبول بود.
<img width="1076" alt="image" src="https://github.com/user-attachments/assets/20673d59-b4e9-4a42-921f-34a1ed9b0b85">
<img width="1076" alt="image" src="https://github.com/user-attachments/assets/5c7ebe7f-1bec-4565-b51a-0a074b4ce91c">
<img width="1073" alt="image" src="https://github.com/user-attachments/assets/5730bf48-f5d4-425e-bf14-6a3025487cff">
<img width="1077" alt="image" src="https://github.com/user-attachments/assets/7ff787c3-d1c7-4fa9-a314-e0ab6c805231">



### بهینه‌سازی کد
کد بهینه‌سازی شده شامل بهبودهای زیر است:

- استفاده از StringBuilder برای الحاق کارآمد رشته‌ها.
- معکوس کردن رشته با استفاده از StringBuilder به صورت بهینه.
```
package profiling;

import java.util.Random;
import java.util.Scanner;

public class StringManipulationOptimized {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of strings to generate: ");
        int numStrings = scanner.nextInt();
        System.out.println("Enter the length of each string: ");
        int length = scanner.nextInt();

        String[] strings = generateRandomStrings(numStrings, length);

        long startTime, endTime;

        startTime = System.currentTimeMillis();
        String concatenatedString = concatenateStrings(strings);
        endTime = System.currentTimeMillis();
        System.out.println("String concatenation completed in " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        String reversedString = reverseString(concatenatedString);
        endTime = System.currentTimeMillis();
        System.out.println("String reversal completed in " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        boolean found = searchString(concatenatedString, "test");
        endTime = System.currentTimeMillis();
        System.out.println("String search completed in " + (endTime - startTime) + " ms");
        System.out.println("Search result: " + (found ? "Found" : "Not Found"));
    }

    private static String[] generateRandomStrings(int numStrings, int length) {
        Random random = new Random();
        String[] strings = new String[numStrings];
        for (int i = 0; i < numStrings; i++) {
            StringBuilder sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                sb.append((char) ('a' + random.nextInt(26)));
            }
            strings[i] = sb.toString();
        }
        return strings;
    }

    private static String concatenateStrings(String[] strings) {
        StringBuilder sb = new StringBuilder();
        for (String str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private static boolean searchString(String str, String substr) {
        return str.contains(substr);
    }
}
```

### نتایج پروفایلینگ کد بهینه‌شده
<img width="1070" alt="image" src="https://github.com/user-attachments/assets/e75aacc9-2896-4974-b0db-35fc77d8817e">
<img width="1078" alt="image" src="https://github.com/user-attachments/assets/088f1909-46a1-4b66-ad61-0de9ba851583">



