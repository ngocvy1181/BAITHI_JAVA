### Bài thi môn lập trình Java
### Họ Tên: Phan Ngọc Vỹ
### Mã số sv: 56131590
### Lớp: 56TH2
### Giáo viên hướng dẫn: Mai Cường Thọ
# APP NGHE NHẠC OFFLINE "VyPlaylist"

<img src ="http://i.imgur.com/6fdi9Lq.jpg">

#### `1. Sơ lược về app`
Như tiêu đề, đây là ứng dụng cho phép nghe nhạc offline trên thẻ SD và bộ nhớ trong máy.
Ứng dụng này bao gồm các chức năng: back(bài trước), next(bài tiếp theo), pause(tạm dừng), play(chạy nhạc), forward(tua tới một khoảng thời gian nhỏ), backward(tua lùi một khoảng thời gian nhỏ), shuffle(Trộn các bài hát lại với nhau), repeat(lặp lại bài hát đang nghe).
Ngoài ra còn có thanh trạng thái và đồng hồ hiển thị thời gian tổng - thời gian hiện tại của bài hát, danh sách các bài hát được hiển thị ra một cửa sổ riêng.
Với tất cả các chức năng trên, ứng dụng sẽ thỏa mãn được nhu cầu của người sử dụng.
#### `2. Giao diện`
Gồm 2 trang:

***Trang 1*** (Trang chính của App)

<img src ="http://i.imgur.com/DxhIXDv.png">

Trang này gồm các nút back, next, pause, play, forward, backward, shuffle, repeat.
Thanh trạng thái, thời gian tổng - thời gian hiện tại của bài hát.
Hình ảnh ở giữa là hình ảnh của App.
Trên cùng là thanh thông tin hiển thị tên bài hát, bên cạnh là nút playlist dùng để chuyển sang trang danh sách các bài nhạc có trong thẻ SD và trong máy.

***Trang 2***

<img src ="http://i.imgur.com/cggbCXF.png">

Trang này hiển thị tất cả các bài hát được tìm thấy từ thẻ SD và trong máy.
Khi chọn vào nút playlist ở trang 1 thì trang 2 sẽ hiển thị. Đặc biệt khi đang nghe nhạc thì việc trang 2 hiển thị sẽ không ảnh hưởng tới bài nhạc đang chạy.
Chúng ta có thể chọn bài nhạc từ trang 2. Nếu không muốn chọn bài thì bấm nút back trong điện thoại.

#### `3. Tính năng`
* Nút **BACK** : Trở về bài trước đã nghe.
* Nút **NEXT** : Chuyển đến bài tiếp theo.
* Nút **PAUSE** : Dừng bài đang nghe.
* Nút **PLAY** : Chơi nhạc.
* Nút **FORWARD** : Tua tới một khoảng thời gian nhỏ bài đang nghe.
* Nút **BACKWARD** : Tua lùi một khoảng thời gian nhỏ bài đang nghe.
* Nút **SHUFFLE** : Trộn các bài nhạc lại với nhau với mục đích thay đổi thứ tự của các bài nhạc.
* Nút **REPEAT** : Lặp lại một bài đang nghe.
* **Thanh trạng thái** : Hiển thị thời lượng đang chạy của bài nhạc, người dùng có thể kéo nút trên thanh trạng thái để đi điến đoạn nhạc mình thích.
* **Thời gian** : Nằm ở trên thanh trạng thái, bên trái là thời gian thực của nhạc đang chạy, bên phải là tổng thời gian của bài nhạc.
