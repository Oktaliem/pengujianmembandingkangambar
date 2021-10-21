# Pengujian Membandingkan User Interface (UI)

Latar Belakang:

Ketika melakukan End to End testing biasanya Tester melakukan functional test and visual test secara bersamaan baik manual ataupun otomasi. Dari sisi otomasi, Selenium/Appium dapat melakukan funtional test dengan baik namun cukup terbatas untuk melakukan visual test dengan pustaka-pustaka yang tersedia. Framework ini dibuat untuk membantu proses testing secara fungsional dan sekaligus bersamaan pengecekan secara visual yang terotomasi dengan bantuan Selenium/Appium dan Framework pendukung lainnya.

Sambil menyelam minum air, sambil menjalankan functional regression test dengan Selenium/Appium sambil menjalankan visual test juga.


### Installation
- [Visual Regression Tracker](https://github.com/Visual-Regression-Tracker/Visual-Regression-Tracker)
- [Micoo](https://github.com/Mikuu/Micoo)
- [aShot](https://github.com/pazone/ashot)


### Run the Test
```
$ mvn clean test
$ mvn clean test -Dcucumber.options="src/test/java/com/oktaliem/features --plugin io.qameta.allure.cucumberjvm.AllureCucumberJvm --tags @selenium"
$ mvn clean test -Dbrowser=grid -Dgrid_url=http://localhost:4444/wd/hub

```

### Sample Visual Regression Test in Cucumber Feature

#### Ashot + Allure
- SeleniumWebsiteVisualTest.feature

#### Visual Regression Tracker
- BlibliVisualTest.feature
- BukalapakVisualTest.feature


#### Micoo
- YahooFinanceVisualTest.feature


