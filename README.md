![](https://v1.padlet.pics/1/image.webp?t=c_limit%2Cdpr_1%2Ch_381%2Cw_381&url=https%3A%2F%2Fstorage.googleapis.com%2Fpadlet-uploads%2F1793358948%2F7f48c4faa0745a1760263d4424f3925f%2Fistockphoto_1225677840_170667a.jpeg%3FExpires%3D1677149983%26GoogleAccessId%3D778043051564-q79bsd8mc40b0bl82ikkrtc3jdofe4dg%2540developer.gserviceaccount.com%26Signature%3DB4p4O%252BUhx%252BQDaEM5CCfqveUQxTtdgO3IpvOjVDlUrHFEYFsdXAcvH4Yo9xnQLEiU1UdzaznXboPoHMv4zeD2ulMs3WhWa%252F1GN2a8AMbyjzyWNpJE6rY1x%252B4%252FGnXhl5Vvq1PtvNIAMaYdxpK315Xxd40Z%252BeRsHRiP7Pfe0TKScqw%253D%26original-url%3Dhttps%253A%252F%252Fpadlet-uploads.storage.googleapis.com%252F1793358948%252F7f48c4faa0745a1760263d4424f3925f%252Fistockphoto_1225677840_170667a.jpeg)

## VOgorode

### Пререквезиты

* JDK версии 17 или выше. Настроенный `JAVA_HOME`. `docker`.

### Использование

* Порты сервисов `7070`, `8080`, `9090` для handyman, landscape, rancher соответственно.
* Запустите скрипт [setup.sh](setup.sh) командой `./setup.sh` он создаст jar на каждый сервис.
* Запустите `docker daemon` командой `sudo systemctl start docker`.
* Чтобы запустить сервисы выполните `docker compose -f dev/docker-compose.yml up --build`
* Чтобы остановить сервисы выпольните `^C` и `docker compose -f dev/docker-compose.yml down`.

### Ссылки

* HW 1: [описание](/docs/hw1/DESC.md) [тз](/docs/hw1/TOR.md)
* HW 2: [описание](/docs/hw2/DESC.md) [тз](/docs/hw2/TOR.md)