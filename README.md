### Bài thi môn l?p trình Java
### H? Tên: Phan Ng?c V?
### Mã s? sv: 56131590
### L?p: 56TH2
### Giáo viên hu?ng d?n: Mai Cu?ng Th?
# APP NGHE NH?C OFFLINE "VyPlaylist"

<img src ="http://i.imgur.com/6fdi9Lq.jpg">

#### `1. So lu?c v? app`
Nhu tiêu d?, dây là ?ng d?ng cho phép nghe nh?c offline trên th? SD và b? nh? trong máy.
?ng d?ng này bao g?m các ch?c nang: back(bài tru?c), next(bài ti?p theo), pause(t?m d?ng), play(ch?y nh?c), forward(tua t?i m?t kho?ng th?i gian nh?), backward(tua lùi m?t kho?ng th?i gian nh?), shuffle(Tr?n các bài hát l?i v?i nhau), repeat(l?p l?i bài hát dang nghe).
Ngoài ra còn có thanh tr?ng thái và d?ng h? hi?n th? th?i gian t?ng - th?i gian hi?n t?i c?a bài hát, danh sách các bài hát du?c hi?n th? ra m?t c?a s? riêng.
V?i t?t c? các ch?c nang trên, ?ng d?ng s? th?a mãn du?c nhu c?u c?a ngu?i s? d?ng.
#### `2. Giao di?n`
G?m 2 trang:
***Trang 1*** (Trang chính c?a App)

<img src ="http://i.imgur.com/DxhIXDv.png">

Trang này g?m các nút back, next, pause, play, forward, backward, shuffle, repeat.
Thanh tr?ng thái, th?i gian t?ng - th?i gian hi?n t?i c?a bài hát.
Hình ?nh ? gi?a là hình ?nh c?a App.
Trên cùng là thanh thông tin hi?n th? tên bài hát, bên c?nh là nút playlist dùng d? chuy?n sang trang danh sách các bài nh?c có trong th? SD và trong máy.
***Trang 2***

<img src ="http://i.imgur.com/cggbCXF.png">

Trang này hi?n th? t?t c? các bài hát du?c tìm th?y t? th? SD và trong máy.
Khi ch?n vào nút playlist ? trang 1 thì trang 2 s? hi?n th?. Ð?c bi?t khi dang nghe nh?c thì vi?c trang 2 hi?n th? s? không ?nh hu?ng t?i bài nh?c dang ch?y.
Chúng ta có th? ch?n bài nh?c t? trang 2. N?u không mu?n ch?n bài thì b?m nút back trong di?n tho?i.

#### `3. Tính nang`
* Nút **BACK** : Tr? v? bài tru?c dã nghe.
* Nút **NEXT** : Chuy?n d?n bài ti?p theo.
* Nút **PAUSE** : D?ng bài dang nghe.
* Nút **PLAY** : Choi nh?c.
* Nút **FORWARD** : Tua t?i m?t kho?ng th?i gian nh? bài dang nghe.
* Nút **BACKWARD** : Tua lùi m?t kho?ng th?i gian nh? bài dang nghe.
* Nút **SHUFFLE** : Tr?n các bài nh?c l?i v?i nhau v?i m?c dích thay d?i th? t? c?a các bài nh?c.
* Nút **REPEAT** : L?p l?i m?t bài dang nghe.
* **Thanh tr?ng thái** : Hi?n th? th?i lu?ng dang ch?y c?a bài nh?c, ngu?i dùng có th? kéo nút trên thanh tr?ng thái d? di di?n do?n nh?c mình thích.
* **Th?i gian** : N?m ? trên thanh tr?ng thái, bên trái là th?i gian th?c c?a nh?c dang ch?y, bên ph?i là t?ng th?i gian c?a bài nh?c.