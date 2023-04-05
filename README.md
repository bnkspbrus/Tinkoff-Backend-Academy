![](https://v1.padlet.pics/1/image.webp?t=c_limit%2Cdpr_1%2Ch_381%2Cw_381&url=https%3A%2F%2Fstorage.googleapis.com%2Fpadlet-uploads%2F1793358948%2F7f48c4faa0745a1760263d4424f3925f%2Fistockphoto_1225677840_170667a.jpeg%3FExpires%3D1677149983%26GoogleAccessId%3D778043051564-q79bsd8mc40b0bl82ikkrtc3jdofe4dg%2540developer.gserviceaccount.com%26Signature%3DB4p4O%252BUhx%252BQDaEM5CCfqveUQxTtdgO3IpvOjVDlUrHFEYFsdXAcvH4Yo9xnQLEiU1UdzaznXboPoHMv4zeD2ulMs3WhWa%252F1GN2a8AMbyjzyWNpJE6rY1x%252B4%252FGnXhl5Vvq1PtvNIAMaYdxpK315Xxd40Z%252BeRsHRiP7Pfe0TKScqw%253D%26original-url%3Dhttps%253A%252F%252Fpadlet-uploads.storage.googleapis.com%252F1793358948%252F7f48c4faa0745a1760263d4424f3925f%252Fistockphoto_1225677840_170667a.jpeg)

## VOgorode

### Пререквезиты

* JDK версии 17 или выше, настроенный `JAVA_HOME`, `docker`, `minikube`, `kubectl`

### Использование

* Все команды выполняйте в одном терминале
* Выполните `sudo systemctl start docker`
* Выполните `eval $(minikube -p minikube docker-env)`
* Выполните `./setup.sh`
* Выполните `kubectl apply -f kube`
* Запустите нужный сервис `minikube service $SERVICE`, где `SERVICE` это handyman, rancher или landscape
* Выполняйте свои запросы в открывшемся окне браузера
* Выполните `minikube stop`, когда надоест делать запросы

### Ссылки

* HW 1: [описание](/docs/hw1/DESC.md) [тз](/docs/hw1/TOR.md)
* HW 2: [описание](/docs/hw2/DESC.md) [тз](/docs/hw2/TOR.md)
* HW 5: [описание](/docs/hw5/DESC.md) [тз](/docs/hw5/TOR.md)