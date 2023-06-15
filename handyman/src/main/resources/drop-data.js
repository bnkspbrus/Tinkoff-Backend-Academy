db = connect("mongodb://localhost:27017/handyman")
// drop collection userAccounts
db.userAccounts.drop()

// print db.stats()
printjson(db.stats())