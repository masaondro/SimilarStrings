Необходимо написать консольное приложение на Java(главный класс называть Main), в которое читает из файла input.txt входные данные: <br>
n - число <br>
далее n строк <br>
m - число <br>
далее m строк <br>
#### Пример 1:<br>
##### input.txt: <br>
4
гвоздь <br>
шуруп <br>
краска синяя <br>
ведро для воды <br>
3 <br>
краска <br>
корыто для воды <br>
шуруп 3х1.5 <br>
##### ouput.txt: <br>
гвоздь:? <br>
шуруп:шуруп 3х1.5 <br>
краска синяя:краска <br>
ведро для воды:корыто для воды <br>
#### Пример 2: <br>
##### input.txt: <br>
1 <br>
Бетон с присадкой <br>
1 <br>
Цемент <br>
##### ouput.txt: <br>
Бетон с присадкой:Цемент <br>
#### Пример 3: <br>
##### input.txt: <br>
1 <br>
Бетон с присадкой <br>
2 <br>
присадка для бетона <br>
доставка <br>
ouput.txt: <br>
Бетон с присадкой:присадка бля бетона <br>
доставка:? <br>

Программа должна сопоставить максимально похожие строки из первого множества со строками из второго множества (одна к одной) и вывести результат в файл output.txt.
