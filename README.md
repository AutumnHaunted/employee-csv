# Employee CSV
***a program for converting a .csv of employee data to a mysql database***

## CSV Format
The program expects CSV files with records in the following format:

|Emp ID|Name Prefix|First Name|Middle Initial|Last Name|Gender|E Mail|Date of Birth|Date of Joining|Salary
|---|---|---|---|---|---|---|---|---|---|
|198429|Mrs.|Serafina|I|Bumgarner|F|serafina.bumgarner@exxonmobil.com|9/21/1982|2/1/2008|69294|
|178566|Mrs.|Juliette|M|Rojo|F|juliette.rojo@yahoo.co.uk|5/8/1967|6/4/2011|193912|
|647173|Mr.|Milan|F|Krawczyk|M|milan.krawczyk@hotmail.com|4/4/1980|1/19/2012|123681|
|847634|Mr.|Elmer|R|Jason|M|elmer.jason@yahoo.com|4/9/1996|5/28/2017|93504|
|...|

The file *can* have a header, but the first entry in the header row must be `Emp ID` exactly.

## Technologies used

The following technologies & libraries were used in the creation of this software:
- JetBrains IntelliJ Ultimate
- GitHub
- JUnit 5 Jupiter
- Apache Log4j 2

## 