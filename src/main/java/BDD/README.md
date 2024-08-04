# گزارش آزمایش BDD

## گزارش بند ۲

  - علت بروز مشکل undefined در هنگام اجرای Scenario Outline:
<br> کدام موارد تست ها با مشکل undefind مواجه می‌شدند؟
    <br>تست هایی که شامل عدد منفی بودند دچار این مشکل می‌شدند.
    <br><br>علت بروز این خطا چه بود؟
    <br>علت بروز این خطا نادرستی عبارات باقاعده(regex) است. زیرا این عبارات باید قادر به تشخیص هر نوع عدد صحیح باشند اما در این جا برای تشخیص اعداد منفی عبارتی در نظر گرفته نشده است.
    <br><br> مشکل را با تغییر کد برطرف نمایید:
    <br> برای این کار باید عبارات با قاعده (\d+)$ و (\d+) را در بدنه تابع given  به (-?\d+)$ و (-?\d+)  تغییر داد تا تست ها از وضعیت undefined به passed برسند.
    <br><br>
    ![Screenshot 2024-08-04 230831](https://github.com/user-attachments/assets/dd445da6-2b79-4be6-8cbe-a8eedbaa5ee8)
    <br>
    ![Screenshot 2024-08-04 230906](https://github.com/user-attachments/assets/479c7a14-1155-4b5a-8408-87f02538cc56)




## گزارش بند ۳

  - Scenario, Scenario Outlineها برای مسئله‌ی مطرح‌شده

![Screenshot from 2024-08-04 21-08-53](https://github.com/user-attachments/assets/d0e9ad8a-830c-4246-a6ab-d54af02c4c18)

  - گام تعریف‌شده برای when (برای گام‌های given , then که برای گرفتن ورودی و سنجیدن درستی جواب بدست‌آمده با جواب مورد انتظار است، در هر دو مسئله‌ی جمع کردن و جذر تقسیم دو عدد یکسان است)
    
![Screenshot from 2024-08-04 21-10-38](https://github.com/user-attachments/assets/3dcea551-345b-4d60-a435-ea6afe575df7)

  - کد برنامه
    
![Screenshot from 2024-08-04 21-13-21](https://github.com/user-attachments/assets/c82cab70-71b5-44c5-9b75-9b5a736e9842)

  - نتیجه‌ی نهایی اجرای تست

![Screenshot from 2024-08-04 21-15-28](https://github.com/user-attachments/assets/543d0e83-e020-409b-b998-751364ee18cd)
