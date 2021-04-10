# PAPB-Kel1
Linguistik App

## Anggota Kelompok
* Dede Trimulya             (18/425304/TK/46999)
* Luqman Rofif Muntashir    (18/425314/TK/47009)
* Taufan Bagus Grahita      (18/425324/TK47109)

## Deskripsi Aplikasi
Linguistik App adalah aplikasi android yang berguna untuk memudahkan belajar bahasa inggris. Fitur yang dimiliki aplikasi ini adalah:
1. Update kata KBBI dan vocabulary
    Memberikan updata kata KBBI dan vocabulary, sehingga diharapkan bisa menambah pengetahuan kosa kata pengguna
2. Translator
    Menyediakan translate dari bahasa indonesia ke bahasa inggris dan sebaliknya. Riwayat dari text yang di-translate akan tersimpan secara otamatis ke dalam history
3. KBBI
    Memberikan layanan untuk mencari kata tertentu sesuai dengan Kamus Besar Bahasa Indonesia (KBBI)
4. Vocabulary
    Pengguna juga dapat menyimpan vocab yang ingin diingatnya ke dalam fitur ini, Sehingga memudahkan kedepannya untuk menghapal vocab-vocab tersebut. 
5. History
    Berisi list riwayat dari hasil translate yang dilakukan pengguna pada fitur translator.

## Dokumentasi
<p float='left'>
  <img src="/Documentation/home.jpg" width="200" >
  <img src="/Documentation/Menu.jpg" width="200" >
  <img src="/Documentation/KBBI.jpg" width="200" >
  <img src="/Documentation/history.jpg" width="200" >
  <img src="/Documentation/about_me.jpg" width="200" >
  <img src="/Documentation/detail_bio.jpg" width="200" >
  <img src="/Documentation/translator.jpg" width="200" >
</p>

## Lifecycle Aplikasi
Aplikasi ini menggunakan 3 jenis lifecycle, yaitu:
* onCreate(), merupakan lifecycle yang terjadi ketika activity atau fragmen dibuat dan ditampilkan. Pada aplikasi ini semua activity dan fragment terdapat lifecycle ini
* onResume(), merupakan lifecycle yang yang dieksekusi setelah activity atau fragmen mengalami lifecycle onPause() atau setelah mengalami lifecycle onStart(). Skenario override pada lifecycle ini adalah ketika membuka aplikasi ini, maka pada halaman ini akan memuat data vocabulary dan kbbi. Lalu ketika membuka menu lain atau membuka activity lain, setelah kembali ke menu home akan memuat ulang dan mengganti data vocabulary dan kbbi yang ditampilkan. Update tersebut juga terjadi ketika pengguna keluar dari aplikasi sementara kemudian kembali lagi
* onDestroy(), merupakan lifecycle ketika fragmen atau activity dihancurkan dari memori. Skenario override pada lifecycle ini adalah ketika activity DetailBiografi dihancurkan maka akan keluar Toast yang bertuliskan "Jangan lupa kunjungi sosial media kami"

## Arsitektur Pattern
Aplikasi ini menggunakan arsitektur MVVM (Model View ViewModel). Alasan kami menggunakan arsitektur karena beberapa hal, yaitu:
* Memisahkan antara view/design, logika yang terjadi pada view, dan data yang dibutuhkan. 
* Menghandle Live Data
* Memudahkan debugging 
* Jumlah baris code pada satu file menjadi lebih sedikit
* Memudahkan dalam membagi tugas antar anggota
* Arsitektur yang paling dipahami diantara semua anggota kelompok

Pada aplikasi ini setiap fragmen menu dan activity detail biografi memiliki ViewModel. Tetapi untuk saat ini ViewModel yang benar-benar berisi logika hanya pada activity detail biografi. Sedangkan untuk model yang digunakan berada pada folder Model. Saat ini model yang digunakan adalah model untuk data detail biografi dari anggota kelompok. Untuk kedepannya akan dibuat ViewModel dan Model untuk penggunaan API translator dan KBBI


## Instalasi
1. clone proyek ini 
   
   ```
   git clone https://github.com/taufanbagus/PAPB-Kel1.git
   ```
2. build menggunakan android studio untuk membuat apk
3. install pada smartphone anda
