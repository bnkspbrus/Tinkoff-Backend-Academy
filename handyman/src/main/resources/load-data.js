db = connect("mongodb://localhost:27017/handyman")

db.userAccounts.insertOne(
    {
        "bank": "Tinkoff",
        "cardId": 1234567890123456,
        "paymentSystem": "Visa"
    }
)

banks = ["Sberbank", "Tinkoff", "Alpha", "VTB", "Gazprombank", "Otkritie", "Promsvyazbank", "Raiffeisen", "Rosbank", "Sovcombank", "Unicredit", "Uralsib"]
paymentSystems = ["Visa", "MasterCard", "Mir", "UnionPay"]

userAccounts = []
for (let i = 0; i < 100000; i++) {
    userAccounts.push(
        {
            "bank": banks[Math.floor(Math.random() * banks.length)],
            "cardId": Math.floor(Math.random() * 10000000000000000),
            "paymentSystem": paymentSystems[Math.floor(Math.random() * paymentSystems.length)]
        }
    )
}

db.userAccounts.insertMany(userAccounts)

// print db.stats()
printjson(db.stats())