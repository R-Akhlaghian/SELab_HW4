# گزارش آزمایش پروفایلینگ

## فرآیند پروفایلینگ `JavaCup`
ما از ابزار YourKit برای پروفایل کردن کلاس `JavaCup` در پروژه‌ی `ProfilingTest` استفاده کردیم. این پروفایلر به برنامه در حال اجرا متصل شد و ما عملیات مختلفی را انجام دادیم تا داده‌های استفاده از منابع را جمع‌آوری کنیم.

### شناخت تابع با مصرف بالای منابع
نتایج پروفایلینگ نشان داد که تابع `temp()` در کلاس `JavaCup` بیشترین منابع را مصرف می‌کند، به طور خاص 96% از زمان CPU.

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





