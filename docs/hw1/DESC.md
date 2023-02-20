## HW 1

* Существуют три отдельных модуля [handyman](/handyman), [rancher](/rancher) и [landscape](/landscape).
* Каждый модуль имеет контроллер, чтобы отвечать на GET запросы  `/system/liveness` и `/system/readiness`.
* `/metrics` мапится на `/actuator/prometheus` это прописано в `application.yml` в каждом из модулей.