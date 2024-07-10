Check application

Для запуска проекта необходимо выполнить следующую команду:

java -cp out main.java.ru.clevertec.check.CheckRunner 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 pathToFile=src/main/resources/products.csv saveToFile=./error_result.csv

Перед выполнением команды, представленной выше необходимо выполлнить следующее:
1. Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
2. javac -d out src\main\java\ru\clevertec\check\CheckRunner.java src\main\java\ru\clevertec\check\Service\*.java src\main\java\ru\clevertec\check\Model\*.java src\main\java\ru\clevertec\check\DateTime\*.java src\main\java\ru\clevertec\check\Exception\*.java src\main\java\ru\clevertec\check\Model\Builder\*.java