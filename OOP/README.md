Cara membuka GUI:
1. Buka folder OOP lewat Intellij
2. Di File->Project Structure->Libraries tambahkan path yang menuju ke lib dari javafx sdk (\javafx-sdk-<versi>\lib)
3. Pastikan di Run->Edit Configurations sudah terdapat Add VM Options dan tertulis:

Linux/Mac: --module-path /path/to/javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml
Windows: --module-path "\path\to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml

dengan "path\to\javafx-sdk-<versi>\lib" diisi path menuju javafx sdk\lib
 
4. Isi Main program pada dengan mencari letak dari file Main.java

3,6. Jika tidak terdapat Add VM Options, tambahkan dalam Edit Configurations melalui Modify options->Add VM Options
5. Pastikan yang dijalankan adalah Class Main di folder src, akan ada tanda segitiga hijau kecil di simbol C sebagai tanda kelas yang dijalankan

Tutorial yang membantu untuk setup javafx di IntelliJ:
https://www.youtube.com/watch?v=Ope4icw6bVk&list=PLZPZq0r_RZOM-8vJA3NQFZB7JroDcMwev&index=2

https://www.youtube.com/watch?v=Ope4icw6bVk
