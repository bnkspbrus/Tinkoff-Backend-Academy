![](https://v1.padlet.pics/1/image.webp?t=c_limit%2Cdpr_1%2Ch_381%2Cw_381&url=https%3A%2F%2Fstorage.googleapis.com%2Fpadlet-uploads%2F1793358948%2F7f48c4faa0745a1760263d4424f3925f%2Fistockphoto_1225677840_170667a.jpeg%3FExpires%3D1677149983%26GoogleAccessId%3D778043051564-q79bsd8mc40b0bl82ikkrtc3jdofe4dg%2540developer.gserviceaccount.com%26Signature%3DB4p4O%252BUhx%252BQDaEM5CCfqveUQxTtdgO3IpvOjVDlUrHFEYFsdXAcvH4Yo9xnQLEiU1UdzaznXboPoHMv4zeD2ulMs3WhWa%252F1GN2a8AMbyjzyWNpJE6rY1x%252B4%252FGnXhl5Vvq1PtvNIAMaYdxpK315Xxd40Z%252BeRsHRiP7Pfe0TKScqw%253D%26original-url%3Dhttps%253A%252F%252Fpadlet-uploads.storage.googleapis.com%252F1793358948%252F7f48c4faa0745a1760263d4424f3925f%252Fistockphoto_1225677840_170667a.jpeg)

## VOgorode

### Пререквезиты

* `docker`, `minikube`, `kubectl`

### Описание

Система VOgorode представляет собой набор сервисов, которые позволяют садоводам и копателям взаимодействовать друг с другом.
Опсания сервисов можно найти в соответствующих папках.
* [Rancher](rancher/README.md)
* [Handyman](handyman/README.md)
* [Landscape](landscape/README.md)

### Использование

#### docker compose

* Выполните `sudo systemctl start docker`
* Создайте директорию `landscape/src/main/resources/db/data`, если не существует
* Положите в `landscape/src/main/resources/db/data` файл https://drive.google.com/file/d/1oO6ipKpazhdddr1qrfzQ1SMSdxY0CTy-/view?usp=sharing
* Выполните `./setup.sh`
* Выполните `docker compose -f dev/docker-compose.yml up`

### Ссылки

* HW 1: [описание](/docs/hw1/DESC.md) [тз](/docs/hw1/TOR.md)
* HW 2: [описание](/docs/hw2/DESC.md) [тз](/docs/hw2/TOR.md)
* HW 5: [описание](/docs/hw5/DESC.md) [тз](/docs/hw5/TOR.md)

### Мониторинг

После нескольких запусков скриптов в папке `http` в сервисе `handyman` можно заметить, что суммарное время увеличивается и максимальное время тоже увеличивается.

![](docs/pics/handyman.png)

Аналогично после нескольких запусков скриптов в папке `http` в сервисе `rancher` можно заметить, что суммарное время увеличивается и максимальное время тоже увеличивается.

![](docs/pics/rancher.png)

Если паралельно запустить скрипты в папке `http` в сервисе `landscape`, то можно заметить, что и его максимальное время тоже увеличивается, так как становится больше записей в базе данных.

![](docs/pics/landscape.png)