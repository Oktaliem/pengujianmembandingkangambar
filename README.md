# Pengujian Membandingkan User Interface (UI)

Latar Belakang:

Ketika melakukan End to End testing biasanya Tester melakukan functional test and visual test secara bersamaan baik manual ataupun otomasi. Dari sisi otomasi, Selenium/Appium dapat melakukan funtional test dengan baik namun cukup terbatas untuk melakukan visual test dengan pustaka-pustaka yang tersedia. Framework ini dibuat untuk membantu proses testing secara fungsional dan sekaligus bersamaan pengecekan secara visual yang terotomasi dengan bantuan Selenium.  


### Run the Test
```
$ mvn clean test
$ mvn clean test -Dcucumber.options="src/test/java/com/oktaliem/features --plugin io.qameta.allure.cucumberjvm.AllureCucumberJvm --tags @Selenium"
```
