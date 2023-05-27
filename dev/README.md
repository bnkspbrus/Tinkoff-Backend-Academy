## Проверка корректности

* Проверьте, что все работает после запуска докера.
```
$ curl -X GET localhost:8080/servers/statuses

{
  "HandymanService": [
    {
      "host": "handyman:9898",
      "status": "OK",
      "artifact": "HandymanService",
      "name": "HandymanService",
      "group": "com.tinkoffacademy",
      "version": "0.0.1-SNAPSHOT"
    }
  ],
  "RancherService": [
    {
      "host": "rancher:8989",
      "status": "OK",
      "artifact": "RancherService",
      "name": "RancherService",
      "group": "com.tinkoffacademy",
      "version": "0.0.1-SNAPSHOT"
    }
  ]
}

```