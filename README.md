# Pengujian Membandingkan User Interface (UI)

Latar Belakang:
Ketika melakukan End to End testing dimana Tester melakukan functional test and visual test secara bersamaan. Selenium dapat melakukan funtional test dengan baik namun cukup terbatas untuk melakukan visual test. Framework ini dibuat untuk membantu proses pengecekan secara fungsional dan sekaligus bersamaan pengecekan secara visual yang terotomasi.  


### Run the Test
```
$ mvn clean test
$ mvn clean test -Dcucumber.options="src/test/java/com/oktaliem/features --plugin io.qameta.allure.cucumberjvm.AllureCucumberJvm --tags @Selenium"
```
