# Selenium Webdriver Java 

## Run script

### Run 1 script
```
mvn clean test -Dtest=com.shopgiay.RegisterTest
mvn clean test -Dtest=com.shopgiay.LoginTest
mvn clean test -Dtest=com.shopgiay.EditProfileTest
mvn clean test -Dtest=com.shopgiay.AddNewProductTest
```

### Run many scripts
```
mvn clean test -Dsuite=shopgiayTestNG
```


## Report
```
target\report\test_execution.html
```

or
```
target\surefire-reports\emailable-report.html
```

## Links
- https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html