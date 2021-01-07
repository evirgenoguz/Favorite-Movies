# Favorite-Movies

Amacım JavaFx öğrenmekti bu doğrultuda bu programı yazdım.

Arayüz ü JavaFx ile yaptım basit bir arayüz oldu Jfoenix de kullanmayı denedim fakat bir kaç bugla karşılaştığım için tüm arayüzü javaFx ile yaptım.

SceneBuilder kullanmayı öğrendim küçük bir program yazdım yazdığım programda yapılanlar ise;
Film ekleye tıkladığında yeni pencere açılıyor film bilgilerini girip Apply butonuna tıklarsan
bu bilgiler ObservableListte tutuluyor program kapandığında bu listedeki elemanlar metinler.txt dosyasına yazılıyor. 
Daha önce yazılan filmleri almak içinse init fonksiyonu çalıştıgında yani arayüz açılırken gerçekleştirilen olay; 
metinler.txt dosyasındaki metnin okunup observableListe eklenmesi program çalışırken dosyalama işlemi yapılmıyor
sadece açılıp kapanırken yapılıyor. Program çalışırken observableList üzerinden hareket ediliyor.  
(Konuya pek hakim değilim anladığım kadarıyla anlatmak istedim.)

JavaFx kurulumda sıkıntı yaşıyorsanız şu linkten yararlanabilirsiniz : https://www.youtube.com/watch?v=BHj6zbH3inI&t=511s

JavaFx kullanmak için yapmanız gerekenler;
javafx dosyasını indirip rardan çıkardıktan sonra (bilgisyarın herhangi bir yerinde olabilir)
(İntellij için)
File -> Project Structure ya da Ctrl+Alt+Shift+S ile buraya gidebilirsin
Libraries -> New Project Library ya da Alt+Insert
Buradan javayı seçip rardan çıkardığımız dosyanın libini seçip ilerliyoruz.
Daha sonrasında Run kısmından Edit Configurations buradan sonra ise Modify Options -> Add Vm Options Bu kısma ise
(JavaFX Documentation daki Getting Started JAvaFx İntellij kısmında)
--module-path "\path\to\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml  (Windows için diğer işletim sistemleri için dökümanda bulunmakta)
bunu ekliyoruz 
"" (tırnak arasındaki path e ) kendi javaFx lib pathini ekliyoruz. Bir sorun olursa Dökümanı inceleyebilirsiniz her şey açıklanmış


