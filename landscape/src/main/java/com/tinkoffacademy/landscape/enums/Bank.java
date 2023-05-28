package com.tinkoffacademy.landscape.enums;

public enum Bank {
    CICI_BANK("Cici Bank"),
    BANK_OF_AMERICA("Bank of Emerika"),
    KREMNIY_ALLEY_BANK("Kremniy Alley Bank"),
    BANK_OF_NIKITA("Bank of Nikita"),
    BETBANK("betbank"),
    SBERBANK("sberbank"),
    TINKOFF_BANK("Tinkoff bank"),
    BANKA("banka"),
    ALPHA_BANK("Alpha bank"),
    BANK_SPB("bank spb");
    private final String name;

    Bank(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
